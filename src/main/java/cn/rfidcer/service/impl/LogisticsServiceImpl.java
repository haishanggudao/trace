package cn.rfidcer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Logistics;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.LogisticsMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.LogisticsService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class LogisticsServiceImpl implements LogisticsService {

	private Logger logger = LoggerFactory.getLogger(LogisticsServiceImpl.class);

	@Autowired
	private LogisticsMapper logisticsDao;

	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private SysVariableDao variableDao;

	@Override
	public List<Logistics> findAll(Page page, Logistics logistics) {
		return logisticsDao.findAll(page, logistics);
	}

	@Override
	public List<Logistics> findByUserCompanyId(String companyId) {
		return logisticsDao.findByUserCompanyId(companyId);
	}

	@Override
	public ResultMsg createOrUpdateLogistics(Logistics logistics, User user) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int num = logisticsDao.checkLogisticsExistsByLogisticsName(logistics);
			if (num != 0) {
				resultMsg.setCode("2");
				resultMsg.setMsg("物流企业已存在");
			} else {
				String companyName = logistics.getLogisticsCompanyName();
				Company company = companyMapper.selectCompanyByName(companyName);
				logistics.setLogisticsAlias(companyName);
				if (StringUtils.isEmpty(logistics.getLogisticsId())) {
					logger.info("新建物流企业信息 ");
					if (company == null) {
						company = new Company();
						company.setCompanyid(UUIDGenerator.generatorUUID());
						company.setName(companyName);
						CommonImportUtils.setCreateAndUpdateTime(company, user);// 设置创建日期和修改日期
						company.setStatus("0");
						companyMapper.insert(company);
					}
					logistics.setLogisticsCompanyId(company.getCompanyid());
					logistics.setLogisticsId(UUIDGenerator.generatorUUID());
					logistics.setStatus("0");
					CommonImportUtils.setCreateAndUpdateTime(logistics, user);// 设置创建日期和修改日期
					logisticsDao.insertSelective(logistics);
					resultMsg.setMsg("新增物流企业成功");
				} else {
					if (company != null) {
						logistics.setLogisticsCompanyId(company.getCompanyid());
					} else {
						logistics.setLogisticsCompanyId(null);
					}
					CommonImportUtils.setUpdateTime(logistics, user);// 设置修改日期
					logisticsDao.updateByPrimaryKeySelective(logistics);
					resultMsg.setMsg("修改物流企业成功");
				}
				resultMsg.setCode("1");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg("物流信息异常");
			logger.error("物流信息异常", e);
		}
		return resultMsg;
	}

	@Override
	public ResultMsg deleteByLogisticsId(Logistics logistics) {
		int res = 0;
		String msg = null;
		msg = "删除物流企业信息";

		// action: delete the record
		res = logisticsDao.deleteWithStatusByPrimaryKey(logistics.getLogisticsId());

		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public void createOrUpdate(List<Logistics> ps, User currentUser) {
		for (Logistics logistics : ps) {
			try {
				createOrUpdateLogistics(logistics, currentUser);
			} catch (Exception e) {
			}
		}
	}

	@Override
	public List<Logistics> getLogisticsCompanys(String companyId) {
		return logisticsDao.getLogisticsCompanys(companyId);
	}

	@Override
	public ResultMsg addImportLogistics(MultipartFile importFile, User user, String companyId) {
		StringBuffer sb = new StringBuffer();
		int successNum = 0;
		int failNum = 0;
		// 检查文件是否合法
		ResultMsg msg = CommonImportUtils.checkImportFile(importFile);
		if (msg.getCode() != null) {
			return msg;
		}
		try {
			Workbook workbook = new HSSFWorkbook(importFile.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			List<Row> list = new ArrayList<Row>();
			List<String> firstRowList = getFirstRowList();
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					/*
					 * 验证首行内容
					 */
					CommonImportUtils.validateFirstRow(row, firstRowList);
					continue;
				}
				if (CommonImportUtils.isBlankRow(row, firstRowList.size())) {
					continue;
				}
				/*
				 * 验证excel格式
				 */
				validateOtherRow(row);
				list.add(row);
			}
			for (Row row : list) {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				Logistics logistics = new Logistics();
				logistics.setCompanyId(companyId);
				String companyName = row.getCell(0).toString();
				logistics.setCompanyName(companyName);
				int num = logisticsDao.checkLogisticsExistsByLogisticsName(logistics);
				if (num != 0) {
					failNum++;
					sb.append("第" + (row.getRowNum() + 1) + "行物流企业名称已存在<br>");
				} else {
					Company company = companyMapper.selectCompanyByName(companyName);
					if (company == null) {
						company = new Company();
						company.setCompanyid(UUIDGenerator.generatorUUID());
						company.setName(companyName);
						String companyfieldid = variableDao.getValByKey("DefaultCompanyFieldId");
						company.setCompanyfieldid(companyfieldid);
						CommonImportUtils.setCreateAndUpdateTime(company, user);// 设置创建日期和修改日期
						company.setStatus("0");
						if (!CommonImportUtils.isBlankCell(row.getCell(5))) {
							company.setShortname(row.getCell(5).toString());
						}
						if (!CommonImportUtils.isBlankCell(row.getCell(6))) {
							company.setAddress(row.getCell(6).toString());
						}
						if (!CommonImportUtils.isBlankCell(row.getCell(7))) {
							company.setContact(row.getCell(7).toString());
						}
						if (!CommonImportUtils.isBlankCell(row.getCell(8))) {
							company.setEmail(row.getCell(8).toString());
						}
						if (!CommonImportUtils.isBlankCell(row.getCell(9))) {
							company.setTel(row.getCell(9).toString());
						}
						if (!CommonImportUtils.isBlankCell(row.getCell(10))) {
							company.setLicense(row.getCell(10).toString());
						}
						if (!CommonImportUtils.isBlankCell(row.getCell(11))) {
							company.setFoodSafetyCode(row.getCell(11).toString());
						}
						companyMapper.insert(company);
					}
					logistics.setLogisticsCompanyId(company.getCompanyid());
					logistics.setLogisticsId(UUIDGenerator.generatorUUID());
					logistics.setLogisticsAlias(companyName);
					logistics.setStatus("0");
					CommonImportUtils.setCreateAndUpdateTime(logistics, user, time);
					if (!CommonImportUtils.isBlankCell(row.getCell(1))) {
						logistics.setContact(row.getCell(1).toString());
					}
					if (!CommonImportUtils.isBlankCell(row.getCell(2))) {
						logistics.setAltContact(row.getCell(2).toString());
					}
					if (!CommonImportUtils.isBlankCell(row.getCell(3))) {
						logistics.setTel(row.getCell(3).toString());
					}
					if (!CommonImportUtils.isBlankCell(row.getCell(4))) {
						logistics.setLogisticsAddress(row.getCell(4).toString());
					}
					int res = logisticsDao.insert(logistics);
					if (res == 1) {
						successNum++;
					} else {
						failNum++;
						sb.append("第" + (row.getRowNum() + 1) + "行物流企业导入失败<br>");
					}
				}
			}
			if (!sb.equals("")) {
				sb.append("<br>");
			}
			sb.append("导入结果：<br>");
			sb.append("成功导入" + successNum + "行物流企业,");
			sb.append("失败" + failNum + "行");
			msg.setCode("1");
			msg.setMsg(sb.toString());
		} catch (Exception e) {
			msg.setCode("-3");
			msg.setMsg(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return msg;
	}

	private void validateOtherRow(Row row) throws Exception {
		for (int j = 0; j < 7; j++) {
			Cell cell = row.getCell(j);
			switch (j) {
			case 0:
				if (cell == null || cell.getStringCellValue().trim().length() == 0) {
					throw new Exception("第" + (row.getRowNum() + 1) + "行" + getFirstRowList().get(j) + "不能为空");
				}
				break;
			default:
				break;
			}
		}
	}

	private List<String> getFirstRowList() {
		List<String> list = new ArrayList<String>();
		list.add("物流企业名称(必填)");
		list.add("物流企业联系人");
		list.add("物流企业联系人别名");
		list.add("物流企业电话");
		list.add("物流企业地址");
		list.add("企业简称");
		list.add("企业地址");
		list.add("企业联系人");
		list.add("电子邮件");
		list.add("电话");
		list.add("营业执照注册号");
		list.add("食品安全许可证号");
		return list;
	}
}

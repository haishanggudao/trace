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
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Slaughterhouse;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.SlaughterhouseMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SlaughterhouseService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class SlaughterhouseServiceImpl implements SlaughterhouseService {

	private Logger logger = LoggerFactory.getLogger(SlaughterhouseService.class);

	@Autowired
	private SlaughterhouseMapper slaughterhouseDao;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private SysVariableDao variableDao;
	
	@Override
	public ResultMsg addOrUpdate(Slaughterhouse slaughterhouse,User user) {
		logger.info("invoke addOrUpdate ,id is {} .", slaughterhouse.getSlaughterhouseId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			int num = slaughterhouseDao.checkSlaughterhouseExistsBySlaughterhouseName(slaughterhouse);
			if(num!=0){
				resultMsg.setCode("2");
				resultMsg.setMsg("屠宰场已存在");
			}else{
				String companyName = slaughterhouse.getCompanyName();
				Company company = companyMapper.selectCompanyByName(companyName);
				CommonImportUtils.setCreateAndUpdateTime(slaughterhouse, user);//设置修改时间和创建时间
				if (StringUtils.isEmpty(slaughterhouse.getSlaughterhouseId())) {
					if(company==null){
						company=new Company();
						company.setCompanyid(UUIDGenerator.generatorUUID());
						company.setName(companyName);
						CommonImportUtils.setCreateAndUpdateTime(company, user);//设置创建日期和修改日期
						company.setStatus("0");
						companyMapper.insert(company);
					}
					slaughterhouse.setSlaughterhouseCompanyId(company.getCompanyid());
					slaughterhouse.setSlaughterhouseId(UUIDGenerator.generatorUUID());
					CommonImportUtils.setCreateAndUpdateTime(slaughterhouse, user);//设置修改时间和创建时间
					slaughterhouse.setSlaughterhouseName(companyName);
					slaughterhouse.setStatus("0");
					slaughterhouseDao.insert(slaughterhouse);
					resultMsg.setMsg("新增屠宰场成功");
				} else {
					slaughterhouse.setSlaughterhouseName(companyName);
					if(company!=null){
						slaughterhouse.setSlaughterhouseCompanyId(company.getCompanyid());
					}else{
						slaughterhouse.setSlaughterhouseCompanyId(null);
					}
					slaughterhouseDao.updateByPrimaryKeySelective(slaughterhouse);
					resultMsg.setMsg("修改屠宰场成功");
				}
				resultMsg.setCode("1");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
			logger.error("屠宰场异常",e);
		}
		return resultMsg;
	}

	@Override
	public List<Slaughterhouse> list(Page page, Slaughterhouse slaughterhouse) {
		return slaughterhouseDao.list(page, slaughterhouse);
	}

	@Override
	public ResultMsg deleteByKey(String id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int deleteByPrimaryKey = slaughterhouseDao.deleteWithStatusByPrimaryKey(id);
			if(deleteByPrimaryKey == 1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("删除成功");
			} else {
				resultMsg.setCode("0");
				resultMsg.setMsg("删除失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}


	@Override
	public void createOrUpdate(List<Slaughterhouse> ps, User currentUser) {
		for (Slaughterhouse slaughterhouse : ps) {
			addOrUpdate(slaughterhouse,currentUser);
		}
	}

	@Override
	public List<Slaughterhouse> getslaughterhouseCompanys(String companyId) {
		return slaughterhouseDao.getslaughterhouseCompanys(companyId);
	}
	@Override
	public ResultMsg addImportSlaughterhouse(MultipartFile uploadImportFile, User currentUser, String companyId) {
		StringBuffer sb = new StringBuffer();
		int successNum = 0;
		int failNum = 0;
		ResultMsg msg = CommonImportUtils.checkImportFile(uploadImportFile);
		if (msg.getCode() != null) {
			return msg;
		}
		try {
			Workbook workbook = new HSSFWorkbook(uploadImportFile.getInputStream());
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
				Slaughterhouse slaughterhouse = new Slaughterhouse();
				slaughterhouse.setCompanyId(companyId);
				String companyName = row.getCell(0).toString();
				slaughterhouse.setCompanyName(companyName);
				int num = slaughterhouseDao.checkSlaughterhouseExistsBySlaughterhouseName(slaughterhouse);
				if (num != 0) {
					failNum++;
					sb.append("第" + (row.getRowNum() + 1) + "行屠宰场名称已存在<br>");
				} else {
					Company company = companyMapper.selectCompanyByName(companyName);
					if (company == null) {
						company = new Company();
						company.setCompanyid(UUIDGenerator.generatorUUID());
						company.setName(companyName);
						String companyfieldid = variableDao.getValByKey("DefaultCompanyFieldId");
						company.setCompanyfieldid(companyfieldid);
						CommonImportUtils.setCreateAndUpdateTime(company, currentUser);//设置创建日期和修改日期
						company.setStatus("0");
						if(!CommonImportUtils.isBlankCell(row.getCell(5))){
							company.setShortname(row.getCell(5).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(6))){
							company.setAddress(row.getCell(6).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(7))){
							company.setContact(row.getCell(7).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(8))){
							company.setEmail(row.getCell(8).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(9))){
							company.setTel(row.getCell(9).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(10))){
							company.setLicense(row.getCell(10).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(11))){
							company.setFoodSafetyCode(row.getCell(11).toString());
						}
						companyMapper.insert(company);
					}
					slaughterhouse.setSlaughterhouseCompanyId(company.getCompanyid());
					slaughterhouse.setSlaughterhouseId(UUIDGenerator.generatorUUID());
					slaughterhouse.setSlaughterhouseName(companyName);
					slaughterhouse.setStatus("0");
					CommonImportUtils.setCreateAndUpdateTime(slaughterhouse, currentUser, time);
					if (!CommonImportUtils.isBlankCell(row.getCell(1))) {
						slaughterhouse.setContact(row.getCell(1).toString());
					}
					if (!CommonImportUtils.isBlankCell(row.getCell(2))) {
						slaughterhouse.setAltContact(row.getCell(2).toString());
					}
					if (!CommonImportUtils.isBlankCell(row.getCell(3))) {
						slaughterhouse.setTel(row.getCell(3).toString());
					}
					if (!CommonImportUtils.isBlankCell(row.getCell(4))) {
						slaughterhouse.setSlaughterhouseAddress(row.getCell(4).toString());
					}
					int res = slaughterhouseDao.insert(slaughterhouse);
					if (res == 1) {
						successNum++;
					} else {
						failNum++;
						sb.append("第" + (row.getRowNum() + 1) + "行屠宰场导入失败<br>");
					}
				}
			}
			if (!sb.equals("")) {
				sb.append("<br>");
			}
			sb.append("导入结果：<br>");
			sb.append("成功导入" + successNum + "行屠宰场,");
			sb.append("失败" + failNum + "行");
			msg.setCode("1");
			msg.setMsg(sb.toString());
		} catch (Exception e) {
			msg.setCode("-3");
			msg.setMsg(e.getMessage());
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return msg;
	}

	private void validateOtherRow(Row row) throws Exception {
		for (int j = 0; j < 7; j++) {
			Cell cell = row.getCell(j);
			switch (j) {
			case 0:
				if (cell == null || cell.toString().trim().length() == 0) {
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
		list.add("屠宰场名称(必填)");
		list.add("屠宰场联系人");
		list.add("屠宰场联系人别名");
		list.add("屠宰场电话");
		list.add("屠宰场地址");
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

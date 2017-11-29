package cn.rfidcer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.SupplierMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SupplierService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class SupplierServiceImpl implements SupplierService {

	private Logger logger = LoggerFactory.getLogger(SupplierService.class);

	@Autowired
	private SupplierMapper supplierDao;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private SysVariableDao variableDao;
	@Autowired
	private CommonVariableMapper comvarMapper;
	
	@Override
	public ResultMsg addOrUpdate(Supplier supplier,User user) {
		logger.info("invoke addOrUpdate ,id is {} .", supplier.getSupplierId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			int num = supplierDao.checkSupplierExistsBySupplierName(supplier);
			if (num != 0) {
				resultMsg.setCode("2");
				resultMsg.setMsg("供应商已存在");
			} else {
				String companyName = supplier.getCompanyName();
				Company company = companyMapper.selectCompanyByName(companyName);
				if(company == null) {
					company = companyMapper.selectByPrimaryKey(supplier.getSupplierCompanyId());
				}
				if (StringUtils.isEmpty(supplier.getSupplierId())) {
					if (company == null) {
						company = new Company();
						company.setCompanyid(UUIDGenerator.generatorUUID());
						company.setName(companyName);
						CommonImportUtils.setCreateAndUpdateTime(company, user);//设置修改日期
						company.setStatus("0");
						companyMapper.insert(company);
					}
					supplier.setSupplierCompanyId(company.getCompanyid());
					supplier.setSupplierId(UUIDGenerator.generatorUUID());
					CommonImportUtils.setCreateAndUpdateTime(supplier, user);//设置修改日期
					supplier.setSupplierAlias(companyName);
					supplier.setStatus("0");
					supplier.setSregisteredperson(user.getId());
					supplier.setSregiesteredtime(new Date());
					if(StringUtils.isEmpty(supplier.getSupplierCode()) 
							&& !org.apache.commons.lang3.StringUtils.isEmpty(company.getCode())) {
						supplier.setSupplierCode(company.getCode() + RandomStringUtils.randomNumeric(3));
					}
					supplierDao.insert(supplier);
					resultMsg.setMsg("新增供应商成功");
				} else {
					supplier.setSupplierAlias(companyName);
					if (company != null) {
						if(StringUtils.isEmpty(supplier.getSupplierCode()) 
								&& !org.apache.commons.lang3.StringUtils.isEmpty(company.getCode())) {
							supplier.setSupplierCode(org.apache.commons.lang3.StringUtils.trimToEmpty(company.getCode()) + RandomStringUtils.randomNumeric(3));
						}
						supplier.setSupplierCompanyId(company.getCompanyid());
					} else {
						supplier.setSupplierCompanyId(null);
					}
					CommonImportUtils.setUpdateTime(supplier, user);//设置修改日期
					supplierDao.updateByPrimaryKeySelective(supplier);
					resultMsg.setMsg("修改供应商成功");
				}
				Company com = supplier.getCompany();
				// 2016.06.16 新加需求
				if (company != null && com != null) {
					company.setCbusinessaddress(com.getCbusinessaddress());
					company.setCbusinesslicense(com.getCbusinesslicense());
					company.setCbusinessscope(com.getCbusinessscope());
					company.setCcustomercategories(com.getCcustomercategories());
					company.setCode(com.getCode());
					company.setCeffectivedate1(com.getCeffectivedate1());
					company.setCeffectivedate2(com.getCeffectivedate2());
					company.setChygienelicense(com.getChygienelicense());
					company.setCidnumb(com.getCidnumb());
					company.setClegalpersonaddress(com.getClegalpersonaddress());
					company.setCliquorbusinesslicense(com.getCliquorbusinesslicense());
					company.setCname(com.getCname());
					company.setCnature(com.getCnature());
					company.setCregistrationnumber(com.getCregistrationnumber());
					companyMapper.updateByPrimaryKeySelective(company);
				}
				
				resultMsg.setCode("1");

			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public List<Supplier> list(Page page,Supplier supplier) {
		return supplierDao.list(page,supplier);
	}

	@Override
	public ResultMsg deleteByKey(Supplier supplier,User user) {
		ResultMsg resultMsg = new ResultMsg();
		try {
				supplier.setIsDeleted(-1);
				CommonImportUtils.setUpdateTime(supplier, user);
				supplierDao.updateByPrimaryKeySelective(supplier);
				resultMsg.setCode("1");
				resultMsg.setMsg("删除成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}


	@Override
	public List<Supplier> getSupplierCompanys(String companyId) {
		return supplierDao.getSupplierCompanys(companyId);
	}

	@Override
	public ResultMsg addImportSupplier(MultipartFile importFile, User user, String companyId) {
		StringBuffer sb=new StringBuffer();
		int successNum=0;
		int failNum=0;
		ResultMsg msg = CommonImportUtils.checkImportFile(importFile);
		if(msg.getCode()!=null){
			return msg;
		}
		try {
			Workbook workbook = new HSSFWorkbook(importFile.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);
				List<Row> list=new ArrayList<Row>();
				List<String> firstRowList = getFirstRowList();
				for (Row row : sheet) {
					if(row.getRowNum()==0){
						/*
						 * 验证首行内容
						 */
						CommonImportUtils.validateFirstRow(row,firstRowList);
						continue;
					}
					if(CommonImportUtils.isBlankRow(row, firstRowList.size())){
						continue;
					}
					/*
					 * 验证excel格式
					 */
					validateOtherRow(row);
					list.add(row);
				}
				for (Row row:list) {
					Timestamp time = new Timestamp(System.currentTimeMillis());
					Supplier supplier = new Supplier();
					supplier.setCompanyId(companyId);
					String companyName = row.getCell(0).toString();
					supplier.setCompanyName(companyName);
					int num = supplierDao.checkSupplierExistsBySupplierName(supplier);
					if(num!=0){
						failNum++;
						sb.append("第"+(row.getRowNum()+1)+"行供应商名称已存在<br>");
					}else{
						Company company = companyMapper.selectCompanyByName(companyName);
						if (company == null) {
							company = new Company();
							company.setCompanyid(UUIDGenerator.generatorUUID());
							company.setName(companyName);
							String companyfieldid = variableDao.getValByKey("DefaultCompanyFieldId");
							company.setCompanyfieldid(companyfieldid);
							CommonImportUtils.setCreateAndUpdateTime(company, user);//设置创建日期和修改日期
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
						supplier.setSupplierCompanyId(company.getCompanyid());
						supplier.setSupplierId(UUIDGenerator.generatorUUID());
						supplier.setSupplierAlias(companyName);
						supplier.setStatus("0");
						CommonImportUtils.setCreateAndUpdateTime(supplier, user, time);//设置创建日期和修改日期
						if(!CommonImportUtils.isBlankCell(row.getCell(1))){
							supplier.setContact(row.getCell(1).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(2))){
							supplier.setAltContact(row.getCell(2).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(3))){
							supplier.setTel(row.getCell(3).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(4))){
							supplier.setSupplierAddress(row.getCell(4).toString());
						}
						int res=supplierDao.insert(supplier);
						if(res==1){
							successNum++;
						}else{
							failNum++;
							sb.append("第"+(row.getRowNum()+1)+"行供应商导入失败<br>");
						}
					}
				}
				if(!sb.equals("")){
					sb.append("<br>");
				}
				sb.append("导入结果：<br>");
				sb.append("成功导入"+successNum+"行供应商,");
				sb.append("失败"+failNum+"行");
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
		for(int j=0;j<7;j++){
			Cell cell = row.getCell(j);
			switch (j) {
			case 0:
				if(cell==null||cell.toString().trim().length()==0){
					throw new Exception("第"+(row.getRowNum()+1)+"行"+getFirstRowList().get(j)+"不能为空");
				}
				break;
			default:
				break;
			}
		}
	}
	
	private List<String> getFirstRowList(){
		List<String> list = new ArrayList<String>();
		list.add("供应商名称(必填)");
		list.add("供应商联系人");
		list.add("供应商联系人别名");
		list.add("供应商电话");
		list.add("供应商地址");
		list.add("企业简称");
		list.add("企业地址");
		list.add("企业联系人");
		list.add("电子邮件");
		list.add("电话");
		list.add("营业执照注册号");
		list.add("食品安全许可证号");
		return list;
	}

	@Override
	public List<CommonVariable> getCNatures() {
		CommonVariable cv = new CommonVariable();
		cv.setVarGroup("getCNatures");
		List<CommonVariable> list = comvarMapper.selectByBean(cv);
		return list;
	}

	@Override
	public List<CommonVariable> getCCustomerCategories() {
		CommonVariable cv = new CommonVariable();
		cv.setVarGroup("getCCustomerCategories");
		List<CommonVariable> list = comvarMapper.selectByBean(cv);
		return list;
	}

	@Override
	public List<Supplier> list(Page page, Supplier supplier, Company company) {
		return supplierDao.list(page,supplier,company);
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.SupplierService#getCAdministrativeDivision()
	 */
	@Override
	public List<CommonVariable> getCAdministrativeDivision() {
		CommonVariable cv = new CommonVariable();
		cv.setVarGroup("getCAdministrativeDivision");
		List<CommonVariable> list = comvarMapper.selectByBean(cv);
		return list;
	}
}

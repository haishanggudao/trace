package cn.rfidcer.service.impl;

import java.util.ArrayList;
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

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.CustomersMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CustomerService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class CustomersServiceImpl implements CustomerService {
	
	private Logger logger = LoggerFactory.getLogger(CustomersServiceImpl.class);
	
	@Autowired
	private CustomersMapper customersmapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private SysVariableDao variableDao;
	
	@Override
	public ResultMsg addOrUpdate(Customers customers,User user) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int num = customersmapper.checkCustomersExistsByCustomersName(customers);
			if(num!=0 && !org.apache.commons.lang3.StringUtils.isEmpty(customers.getCustomerId())){
				resultMsg.setCode("2");
				resultMsg.setMsg("客户企业已存在");
			}else{
				String companyName = customers.getCustCompanyName();
				Company company = companyMapper.selectCompanyByName(companyName);
				if(company == null) {
					company = companyMapper.selectByPrimaryKey(customers.getCustCompanyId());
				}
				logger.info("新建客户企业信息 ");
				if(company==null){
					company=new Company();
					company.setCompanyid(UUIDGenerator.generatorUUID());
					company.setName(companyName);
					CommonImportUtils.setCreateAndUpdateTime(company, user);//设置创建日期和修改日期
					company.setStatus("0");
					companyMapper.insert(company);
				}
				customers.setCustomerAlias(companyName);
				if (StringUtils.isEmpty(customers.getCustomerId())) {
					customers.setCustCompanyId(company.getCompanyid());
					customers.setCustomerId(UUIDGenerator.generatorUUID());
					customers.setStatus("0");
					CommonImportUtils.setCreateAndUpdateTime(customers, user);//设置创建日期和修改日期
					if(!org.apache.commons.lang3.StringUtils.isEmpty(company.getCode())) {
						customers.setCustCode(company.getCode() + RandomStringUtils.randomNumeric(3));
					}
					customersmapper.insertSelective(customers);
					resultMsg.setMsg("新增客户企业成功");
				} else {
					if (StringUtils.isEmpty(customers.getCustCode())
							&& !org.apache.commons.lang3.StringUtils.isEmpty(company.getCode())) {
						customers.setCustCode(org.apache.commons.lang3.StringUtils.trimToEmpty(company.getCode())
								+ RandomStringUtils.randomNumeric(3));
					}
					customers.setCustCompanyId(company.getCompanyid());
					CommonImportUtils.setUpdateTime(customers, user);//设置修改日期
					customersmapper.updateByPrimaryKeySelective(customers);
					resultMsg.setMsg("修改客户企业成功");
				}
				
				Company com = customers.getCompany();
				// 2016.06.16 新加需求
				if (company != null && com != null) {
					company.setCode(com.getCode());
					company.setCbusinessaddress(com.getCbusinessaddress());
					company.setCbusinesslicense(com.getCbusinesslicense());
					company.setCbusinessscope(com.getCbusinessscope());
					company.setCcustomercategories(com.getCcustomercategories());
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
			resultMsg.setMsg("客户信息异常");
			logger.error("客户信息异常",e);
		}
		return resultMsg;
	}

	@Override
	public List<Customers> list(Page page,Customers customers,Company company) {
		return customersmapper.list(page,customers,company);
	}

	@Override
	public ResultMsg deleteByKey(String id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			Customers item = customersmapper.selectByPrimaryKey(id);
			if(item != null) {
				item.setIsDeleted(-1);
				customersmapper.updateByPrimaryKeySelective(item);
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
	public List<Customers> getCustomersCompanys(String companyId) {
		return customersmapper.getCustomersCompanys(companyId);
	}
	@Override
	public List<Customers> findCustomerList(String companyId) {
		return customersmapper.findCustomerList(companyId);
	}
	

	@Override
	public List<Customers> findCustomerListIncludeCompany(String companyId) {
		List<Customers> myCustomers = new ArrayList<Customers>();
		
		myCustomers = customersmapper.findCustomerList(companyId);
		
		Company myCompany = companyMapper.selectByPrimaryKey(companyId);
		
		Customers myCustomer = new Customers();
		myCustomer.setCustomerId(myCompany.getCompanyid() );
		myCustomer.setCustCompanyId(myCompany.getCompanyid());
		myCustomer.setCustomerAlias(myCompany.getName());
		
		myCustomers.add(0, myCustomer);
		
		return myCustomers;
	}
	

	@Override
	public ResultMsg addImportCustomers(MultipartFile importFile, User user, String companyId) {
		StringBuffer sb=new StringBuffer();
		int successNum=0;
		int failNum=0;
		//检查文件是否合法
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
					Customers customers = new Customers();
					customers.setCompanyId(companyId);
					String companyName = row.getCell(0).toString();
					customers.setCompanyName(companyName);
					int num = customersmapper.checkCustomersExistsByCustomersName(customers);
					if(num!=0){
						failNum++;
						sb.append("第"+(row.getRowNum()+1)+"行客户企业名称已存在<br>");
					}else{
						Company company = companyMapper.selectCompanyByName(companyName);
						if (company == null) {
							company = new Company();
							company.setCompanyid(UUIDGenerator.generatorUUID());
							company.setName(companyName);
							String companyfieldid = variableDao.getValByKey("DefaultCompanyFieldId");
							company.setCompanyfieldid(companyfieldid);
							CommonImportUtils.setCreateAndUpdateTime(company, user);
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
						customers.setCustCompanyId(company.getCompanyid());
						customers.setCustomerId(UUIDGenerator.generatorUUID());
						customers.setCustomerAlias(companyName);
						customers.setStatus("0");
						CommonImportUtils.setCreateAndUpdateTime(customers, user);
						if(!CommonImportUtils.isBlankCell(row.getCell(1))){
							customers.setContact(row.getCell(1).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(2))){
							customers.setAltContact(row.getCell(2).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(3))){
							customers.setTel(row.getCell(3).toString());
						}
						if(!CommonImportUtils.isBlankCell(row.getCell(4))){
							//customers.setLogisticsAddress(row.getCell(4).toString());
						}
						int res=customersmapper.insert(customers);
						if(res==1){
							successNum++;
						}else{
							failNum++;
							sb.append("第"+(row.getRowNum()+1)+"行客户企业导入失败<br>");
						}
					}
				}
				if(!sb.equals("")){
					sb.append("<br>");
				}
				sb.append("导入结果：<br>");
				sb.append("成功导入"+successNum+"行客户企业,");
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
		list.add("客户企业名称(必填)");
		list.add("客户企业联系人");
		list.add("客户企业联系人别名");
		list.add("客户企业电话");
		list.add("客户企业地址");
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
	public Customers selectByPrimaryKey(String customerId) {
		return customersmapper.selectByPrimaryKey(customerId);
	}

	@Override
	public List<Customers> findFilterCustomers(ProductCompany productCompany) {
		return customersmapper.findFilterCustomers(productCompany);
	}

	@Override
	public List<Customers> findCustomers(Page page,Customers customers) {
		return customersmapper.findCustomers(page,customers);
	}

	
	
	
	
	
	
	
}

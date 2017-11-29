package cn.rfidcer.service.impl;

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
import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.AreaInfoMapper;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CompanyService;
import cn.rfidcer.util.CodeGenerator;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private CompanyMapper companyDao;
	
	@Autowired
	private SysVariableDao variableDao;
	
	@Autowired
	private AreaInfoMapper areaInfoMapper;

	@Override
	public ResultMsg addOrUpdateCompany(Company company,User user) {
		logger.info("invoke addOrUpdateCompany ,id is {} ", company.getCompanyid());
		ResultMsg resultMsg = new ResultMsg();
		try {
			if (StringUtils.isEmpty(company.getCompanyid())) {
				company.setCompanyid(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(company, user);//设置修改时间和创建时间
				if(StringUtils.isEmpty(company.getCode())){
					AreaInfo areaInfo = new AreaInfo();
					areaInfo.setProvince(company.getProvince());
					areaInfo.setCity(company.getCity());
					areaInfo.setArea(company.getArea());
					areaInfo=areaInfoMapper.selectOne(areaInfo);
					if(areaInfo==null){
						resultMsg.setCode("-1");
						resultMsg.setMsg("找不见地区信息");
						return resultMsg;
					}
					// 生成企业代码
					String companyCode = genCompanyCode(areaInfo);
					company.setCode(companyCode);
				}
				
				// DAO action: insert a new company
				companyDao.insert(company);
			} else {
				if(StringUtils.isEmpty(company.getCode())){
					AreaInfo areaInfo = new AreaInfo();
					areaInfo.setProvince(company.getProvince());
					areaInfo.setCity(company.getCity());
					areaInfo.setArea(company.getArea());
					areaInfo=areaInfoMapper.selectOne(areaInfo);
					if(areaInfo==null){
						resultMsg.setCode("-1");
						resultMsg.setMsg("找不见地区信息");
						return resultMsg;
					}
					// 生成企业代码
					String companyCode = genCompanyCode(areaInfo);
					company.setCode(companyCode);
				}
				// DAO action: update the company
				CommonImportUtils.setUpdateTime(company, user);
				companyDao.updateByPrimaryKeySelective(company);
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public String genCompanyCode(AreaInfo areaInfo) {
		String companyCode = CodeGenerator.getCompanyCode(areaInfo.getId());
		while(companyDao.findByCode(companyCode)!=null){
			companyCode = CodeGenerator.getCompanyCode(areaInfo.getId());
		}
		return companyCode;
	}

	@Override
	public List<Company> list(Page page) {
		if(page != null) {
			return companyDao.list(page);
		} else {
			return companyDao.list();
		}
	}

	@Override
	public ResultMsg deleteByKey(String id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int deleteByPrimaryKey = companyDao.deleteWithStatusByPrimaryKey(id);
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
	public List<Company> getCompanys(String userId) {
		return companyDao.getCompanys(userId);
	}

	@Override
	public List<Company> listQueryByCompany(Page page, Company company) {
		return companyDao.listQueryByCompany(page,company);
	}

	@Override
	public List<Company> listQueryByBlurOr(Page page, Company company) {
		return companyDao.listQueryByBlurOr(page,company);
	}
	
	@Override
	public List<Company> listQueryByBlurAnd(Page page, Company company) {
		return companyDao.listQueryByBlurAnd(page,company);
	}

	@Override
	public ResultMsg addImportCompanies(MultipartFile importFile, User user, String companyId) { 
		ResultMsg msg = new ResultMsg();
		int columnNumber = 11;
		
		StringBuffer sb=new StringBuffer();
		int successNum=0;
		int failNum=0;
		
		// check: is the file empty?
		if (importFile == null || importFile.getSize() == 0) {
			msg.setCode("-1");
			msg.setMsg("导入文件为空");
			return msg;
		}
		
		// check: is the type of file .xls ?
		String name = importFile.getOriginalFilename();
		if (!name.endsWith(".xls")) {
			msg.setCode("-2");
			msg.setMsg("文件格式不为xls");
			return msg;
		}
		
		try {
			Workbook workbook = new HSSFWorkbook(importFile.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			List<Row> list=new ArrayList<Row>();
			List<String> firstRowList = getFirstRowList();
			
			for (Row row : sheet) {
				if(row.getRowNum()==0){
					// 验证首行内容
					CommonImportUtils.validateFirstRow(row,firstRowList);
					continue;
				}
				if(CommonImportUtils.isBlankRow(row, columnNumber)){
					continue;
				}
				// 验证excel格式
				validateOtherRow(row);
				list.add(row);
			}
			
			// loop the list
			for (Row row : list) { 				
				// 导入企业信息 
				Company company = new Company();
				// 企业名称
				company.setName(row.getCell(0).toString());
				company.setCompanyid(companyId);
				int num = companyDao.checkCompanyExistsByCompanyName(company);
				if ( num != 0) {
					failNum++;
					sb.append("第"+(row.getRowNum()+1)+"企业名称已存在<br>");
				} else {
					Company myCompany = companyDao.selectCompanyByName(row.getCell(0).toString());
					if (myCompany == null) {
						company.setCompanyid(UUIDGenerator.generatorUUID());
						// 获取默认的企业领域值
						String companyfieldid = variableDao.getValByKey("DefaultCompanyFieldId");
						company.setCompanyfieldid(companyfieldid);
						CommonImportUtils.setCreateAndUpdateTime(company, user);//设置修改时间和创建时间
						company.setStatus("0");
						// 企业简称
						if(!CommonImportUtils.isBlankCell(row.getCell(1))){
							company.setShortname(row.getCell(1).toString());
						}
						// 企业地址
						if(!CommonImportUtils.isBlankCell(row.getCell(2))){
							company.setAddress(row.getCell(2).toString());
						}
						
						// 联系人
						if(!CommonImportUtils.isBlankCell(row.getCell(3))){
							company.setContact(row.getCell(3).toString());
						}
						
						// 电子邮件（可为空）
						if(!CommonImportUtils.isBlankCell(row.getCell(4))){
							company.setEmail(row.getCell(4).toString());
						}
						
						// 电话
						if(!CommonImportUtils.isBlankCell(row.getCell(5))){
							company.setTel(row.getCell(5).toString());
						}

						// 营业执照注册号
						if(!CommonImportUtils.isBlankCell(row.getCell(6))){
							company.setLicense(row.getCell(6).toString());
						}
						
						// 食品安全许可证号（可为空）
						if(!CommonImportUtils.isBlankCell(row.getCell(7))){
							company.setFoodSafetyCode(row.getCell(7).toString());
						}
						
						// DAO action: insert a new company
						int res = companyDao.insert(company);
						
						if(res==1){
							successNum++;
						}else{
							failNum++;
							sb.append("第"+(row.getRowNum()+1)+"行企业导入失败<br>");
						}
					}
				}
			}
			if(!sb.equals("")){
				sb.append("<br>");
			}
			sb.append("导入结果：<br>");
			sb.append("成功导入"+successNum+"行企业,");
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
			case 4: 
				break;
			case 7:
				break; 
			default:
				if(cell==null||cell.getStringCellValue().trim().length()==0){
					throw new Exception("第"+(row.getRowNum()+1)+"行"+getFirstRowList().get(j)+"不能为空");
				}
				break;
			}
		}
	}
	
	/**
	 * 获取excel的标题; created by jie.jia at 2016-04-20 17:24
	 * @return
	 */
	private List<String> getFirstRowList(){
		List<String> list = new ArrayList<String>();
		list.add("企业名称");
		list.add("企业简称");
		list.add("企业地址");
		list.add("联系人");
		list.add("电子邮件（可为空）");
		
		list.add("电话");
		list.add("营业执照注册号");
		list.add("食品安全许可证号（可为空）");

		return list;
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.CompanyService#getCompanyById(java.lang.String)
	 */
	@Override
	public Company getCompanyById(String companyId)  {
		return companyDao.selectByPrimaryKey(companyId);
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.CompanyService#list(cn.rfidcer.interceptor.Page, cn.rfidcer.bean.Company)
	 */
	@Override
	public List<Company> list(Page page, Company company) {
		return companyDao.listQueryByCompany(page,company);
	}

}

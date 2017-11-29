package cn.rfidcer.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface CompanyService {
	
	/**
	 * 批量导入企业信息; created by jie.jia at 2016-04-20 16:58
	 * @param uploadImportFile
	 * @param user
	 * @param companyId
	 * @return
	 */
	ResultMsg addImportCompanies(MultipartFile importFile, User user, String companyId);

	/**
	 * 新增或者修改企业信息; 
	 * @param company
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdateCompany(Company company,User user);

	List<Company> list(Page page);

	ResultMsg deleteByKey(String id);
	

	/**获得用户相关的公司
	 * @param company
	 * @return
	 */
	List<Company> getCompanys(String userId);
	
	List<Company> listQueryByCompany(Page page, Company company);

	/**
	 * @param page
	 * @param company
	 * @return
	 */
	List<Company> listQueryByBlurOr(Page page, Company company);
	
	
	/**
	 * @param page
	 * @param company
	 * @return
	 */
	List<Company> listQueryByBlurAnd(Page page, Company company);

	/**
	 * 生成公司编码
	 * @param areaInfo
	 * @return
	 */
	String genCompanyCode(AreaInfo areaInfo);
	
	
	Company getCompanyById(String companyId);

	/**
	 * @param page
	 * @param company
	 * @return
	 */
	List<Company> list(Page page, Company company);

	
}
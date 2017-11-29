package cn.rfidcer.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**   
* @Title: CustomerService.java 
* @Package cn.rfidcer.service 
* @Description: Service 客户信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月14日 上午10:49:52 
* @version V1.0   
*/
public interface CustomerService {

	ResultMsg addOrUpdate(Customers customers,User user);

	List<Customers> list(Page page,Customers customers,Company company);

	ResultMsg deleteByKey(String id);
	

	List<Customers> getCustomersCompanys(String companyId);

	/**
	 * @param importFile
	 * @param user
	 * @param companyId
	 * @return
	 */
	ResultMsg addImportCustomers(MultipartFile importFile, User user, String companyId);

	/**
	 * 查询企业的客户列表
	 * @param companyId
	 * @return
	 */
	List<Customers> findCustomerList(String companyId);
	
	/**
	 * 根据产品关联企业过滤没关联的企业ID
	 * @param productCompany
	 * @return
	 */
	List<Customers> findFilterCustomers(ProductCompany productCompany);
	/**
	 * 查询企业的客户列表, 包括自身所属公司; added by jie.jia at 2016-07-07 19:29 
	 * @param companyId
	 * @return
	 */
	List<Customers> findCustomerListIncludeCompany(String companyId);
	
	/**
	 * 依据customerId来获取客户信息; 
	 * @param customerId
	 * @return
	 */
	Customers selectByPrimaryKey(String customerId);
	
	List<Customers> findCustomers(Page page,Customers customers);
	
}
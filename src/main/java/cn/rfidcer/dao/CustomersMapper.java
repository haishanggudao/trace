package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.interceptor.Page;

public interface CustomersMapper {
	
    int deleteByPrimaryKey(String customerId);

    int insert(Customers record);

    int insertSelective(Customers record);

    /**
     * 依据customerId来获取客户信息; 
     * @param customerId
     * @return
     */
    Customers selectByPrimaryKey(String customerId);
    
    Customers selectByCustCompanyId(Customers customers);
    
	/**
	 * 根据产品关联企业过滤没关联的企业ID
	 * @param productCompany
	 * @return
	 */
    List<Customers> findFilterCustomers(ProductCompany productCompany);
    
    int updateByPrimaryKeySelective(Customers record);

    int updateByPrimaryKey(Customers record);

	List<Customers> list(Page page,@Param("customers") Customers customers,@Param("company")Company company);

	List<Customers> getCustomersCompanys(String companyId);
	/***
	 * 查询企业的客户列表
	 * @param companyId
	 * @return
	 */
	List<Customers> findCustomerList(String companyId);
	
	/**
	 * 根据出库单ID查询客户信息
	 */
	Customers getCustomersByOutstockMainId (String outstockMainId);
	
	/**根据别名查询客户
	 * @param customers
	 * @return
	 */
	int checkCustomersExistsByCustomersName(Customers customers);
	
	Customers getCustomersByCustomerCompanyId(String custCompanyId);

	List<Customers> findCustomers(Page page,@Param("customers") Customers customers);
}
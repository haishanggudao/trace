package cn.rfidcer.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface SupplierService {

	ResultMsg addOrUpdate(Supplier supplier,User user);

	List<Supplier> list(Page page,Supplier supplier, Company company);

	ResultMsg deleteByKey(Supplier supplier,User user);
	
	List<Supplier> getSupplierCompanys(String companyId);
	
	/**导入供应商
	 * @param importFile
	 * @param user
	 * @param companyId
	 * @return
	 */
	ResultMsg addImportSupplier(MultipartFile importFile, User user, String companyId);

	/**
	 * 获取所有的企业性质
	 */
	List<CommonVariable> getCNatures();

	List<CommonVariable> getCCustomerCategories();

	/**
	 * @param page
	 * @param supplier
	 * @return
	 */
	
	List<Supplier> list(Page page, Supplier supplier);

	/**
	 * @return
	 */
	List<CommonVariable> getCAdministrativeDivision();
}
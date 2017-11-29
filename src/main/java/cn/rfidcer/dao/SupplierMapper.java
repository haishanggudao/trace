package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.dto.ProductParam;
import cn.rfidcer.interceptor.Page;

public interface SupplierMapper {
	
    int deleteByPrimaryKey(String supplierId);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(String supplierId);
    
    int updateByPrimaryKeySelective(Supplier record);

	List<Supplier> list(Page page,@Param("supplier") Supplier supplier);
	
	List<Supplier> getSupplierCompanys(String companyId);

	/**根据供应商ID和CompanyId检查供应商是否存在
	 * @param supplierId
	 * @return
	 */
	int checkSupplierExistsBySupplierId(Supplier supplier);
	
	/**根据别名查找供应商
	 * @param supplier
	 * @return
	 */
	int checkSupplierExistsBySupplierName(Supplier supplier);

	/**
	 * @param page
	 * @param supplier
	 * @param company
	 * @return
	 */
	List<Supplier> list(Page page,@Param("supplier") Supplier supplier,@Param("company") Company company);
	
	List<Supplier> getSupplierByCompanyId(ProductParam productParam);
}
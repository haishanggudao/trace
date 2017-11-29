package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.dto.ProductCompanyParam;
import cn.rfidcer.interceptor.Page;

/**   
* @Title: ProductCompanyService.java 
* @Package cn.rfidcer.service 
* @Description: service 产品企业关系 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月1日 下午5:26:07 
* @version V1.0   
*/
public interface ProductCompanyService {
	/**
	 * 根据产品ID查询关联企业信息
	 * @param page 分页对象
	 * @return
	 */
	List<ProductCompany> findAllProductCompanyByProductId(ProductCompany productCompany);
	
	/**
	 * 根据参数删除关联企业
	 * @param productCompany
	 * @return
	 */
	ResultMsg deleteByParam(ProductCompany productCompany);
	
	
	/**
	 * 添加产品关联企业
	 * @param productCompany
	 * @return
	 */
	ResultMsg add(ProductCompany productCompany);

	List<Product> findAllNotCheckedProducts(Page page,ProductCompanyParam productCompanyParam);

	ResultMsg addProducts(String products, String companyId);
}

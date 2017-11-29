package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.dto.ProductCompanyParam;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

/**   
* @Title: ProductCompanyMapper.java 
* @Package cn.rfidcer.dao 
* @Description: Mapper 产品企业关系
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月1日 下午5:23:24 
* @version V1.0   
*/
public interface ProductCompanyMapper extends Mapper<ProductCompany> {
	List<ProductCompany> findAllProductCompanyByProductId(ProductCompany productCompany);

	List<Product> findAllNotCheckedProducts(Page page,@Param("productCompanyParam") ProductCompanyParam productCompanyParam);
	
}

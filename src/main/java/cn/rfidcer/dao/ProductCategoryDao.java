package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.interceptor.Page;

/**
* @Title: ProductCategoryDao.java 
* @Package cn.rfidcer.dao 
* @Description: DAO 产品类别 
* @author xzm & jie.jia
* @Copyright Copyright
* @date 2016年6月23日 上午10:16:07 
* @version V1.0
 */
public interface ProductCategoryDao {

	
	/** 查询产品分类
	 * @param page 分页对象
	 * @param category 查询条件
	 * @return
	 */
	List<ProductCategory> findAll(Page page,@Param("category") ProductCategory productCategory);
	 
	/**新建产品类别
	 * @param category
	 * @return
	 */
	int createProductCategory(ProductCategory productCategory);
	
	/**删除产品类别
	 * @param category
	 * @return
	 */
	int delProductCategory(ProductCategory productCategory);
	
	/**
	 * 通过更改状态来移除产品; created by jie.jia at 2016-04-07 16:07
	 * @param productId
	 * @return
	 */
	int deleteWithStatusByPrimaryKey(String productCategoryId);
	
	/**更新产品类别
	 * @param category
	 * @return
	 */
	int updateProductCategory(ProductCategory productCategory);
	
	int insertSelective(ProductCategory pc);
	
	/**根据companyId和分类名称查询分类信息
	 * @param productCategory
	 * @return
	 */
	ProductCategory findProductCategoryByName(ProductCategory productCategory);
}

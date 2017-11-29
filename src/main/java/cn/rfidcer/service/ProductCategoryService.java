package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * 
* @Title: ProductCategoryService.java 
* @Package cn.rfidcer.service 
* @Description: Service 产品类别业务层
* @author xzm & jie.jia
* @Copyright Copyright
* @date 2016年6月23日 上午10:17:32 
* @version V1.0
 */
public interface ProductCategoryService {
	/**
	 * 查找产品分类，可分页
	 * @param page 分页对象
	 * @return
	 */
	List<ProductCategory> findAll(Page page,ProductCategory productCategory);
	
	/**
	 * 新增或修改产品类别
	 * @param productCategory
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateProductCategory(ProductCategory productCategory,User user);
	/**
	 * 删除产品类别
	 * @param category
	 * @return
	 */
	ResultMsg delProductCategory(ProductCategory productCategory);
	/**
	 * 更新产品类别
	 * @param category
	 * @return
	 */
	ResultMsg updateProductCategory(ProductCategory productCategory);
	/**
	 * 创建类别
	 * @param pcs
	 * @throws Exception
	 */
	void createOrUpdate(List<ProductCategory> pcs) throws Exception;
	
	void createAPC(ProductCategory pc);
	
}

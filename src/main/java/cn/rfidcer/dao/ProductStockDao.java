package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.interceptor.Page;
 
/**
 * 
* @Title: ProductStockDao.java 
* @Package cn.rfidcer.dao 
* @Description: 产品库存DAO
* @author xzm & jie.jia
* @Copyright Copyright
* @date 2016年6月15日 下午6:39:12 
* @version V1.0
 */
public interface ProductStockDao {

	/**新增产品库存
	 * @param productStock
	 * @return
	 */
	int addProductStock(ProductStock productStock);
	
	/**根据产品ID和明细ID查询产品库存
	 * @param productStock
	 * @return
	 */
	ProductStock findByProductIdAndDetailId(ProductStock productStock);
	
	/**
	 * 查询全部产品库存记录；created by jie.jia at 2016-06-15 19:53
	 * @param page
	 * @param productStock
	 * @return
	 */
	List<ProductStock> list(Page page,@Param("productStock")ProductStock productStock);
	
	/**更新产品库存
	 * @param productStock
	 * @return
	 */
	int updateProductStockById(ProductStock productStock);
	
	/**更新产品库存数量
	 * @param productStock
	 * @return
	 */
	int updateStockNumById(ProductStock productStock);
}

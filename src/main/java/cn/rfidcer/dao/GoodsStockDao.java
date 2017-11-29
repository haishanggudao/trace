package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.interceptor.Page;

/**
 * 
* @Title: GoodsStockDao.java 
* @Package cn.rfidcer.dao 
* @Description: DAO 商品库存
* @author xzm & jie.jia
* @Copyright Copyright
* @date 2016年6月16日 下午1:51:20 
* @version V1.0
 */
public interface GoodsStockDao {

	/**新增商品库存
	 * @param goodsStock
	 * @return
	 */
	int addGoodsStock(GoodsStock goodsStock);
	
	/**根据条件查询商品库存
	 * @param goodsStock
	 * @return
	 */
	GoodsStock findByGoodsStockByCase(GoodsStock goodsStock);
	
	/**
	 * 根据产品ID和规格明细ID来查询商品库存；created by jie.jia at 2016-06-16 14:15
	 * @param page
	 * @param goodsStock
	 * @return
	 */
	List<GoodsStock> findByProductIdStandDetailId(Page page,@Param("goodsStock")GoodsStock goodsStock);
	
	/**更新商品库存
	 * @param goodsStock
	 * @return
	 */
	int updateGoodsStockById(GoodsStock goodsStock);
	
	/**更新商品库存数量
	 * @param goodsStock
	 * @return
	 */
	int updateStockById(GoodsStock goodsStock);
}

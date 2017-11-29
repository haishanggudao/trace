package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.interceptor.Page;

/**
 * 
* @Title: GoodsStockService.java 
* @Package cn.rfidcer.service 
* @Description: Service 商品库存 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月16日 上午11:37:52 
* @version V1.0
 */
public interface GoodsStockService {
	
	/**
	 * 根据产品ID和规格明细ID查询产品库存记录；created by jie.jia at 2016-06-16 14:55
	 * @param page
	 * @param productId
	 * @param productStandardDetailId
	 * @return
	 */
	List<GoodsStock> findByProductIdStandDetailId(Page page, GoodsStock goodsStock);
	
	ResultMsg updateGoodsStock(User user,GoodsStock goodsStock);
	
	ResultMsg addOrupdateStock(User user, OperationType operationType,GoodsStock goodsStock,boolean updateProduct);

}

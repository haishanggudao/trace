package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.enums.ChangeType;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.interceptor.Page;

/**
 * 
* @Title: ProductStockService.java 
* @Package cn.rfidcer.service 
* @Description: 产品库存service 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月15日 下午6:35:24 
* @version V1.0
 */
public interface ProductStockService {
	
	/**
	 * 查询产品库存记录；created by jie.jia at 2016-06-16 10:33
	 * @param page
	 * @param productStock
	 * @return
	 */
	List<ProductStock> list(Page page, ProductStock productStock);
	
	/**
	 * 高级查询产品库存记录； created by jie.jia at 2016-06-16 19:35 
	 * @param page
	 * @param productStock
	 * @return
	 */
	List<ProductStock> listByAdvancedQuery(Page page, ProductStock productStock);
	
	ResultMsg addOrupdateStock(User user, OperationType operationType,ChangeType changeType, ProductStock productStock);
	
	

}

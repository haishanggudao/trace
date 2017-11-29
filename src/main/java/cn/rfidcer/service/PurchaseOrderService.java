package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
 
/**
 * 
* @Title: PurchaseOrderService.java 
* @Package cn.rfidcer.service 
* @Description: 采购订单
* @author xzm & jie.jia
* @Copyright Copyright
* @date 2016年6月14日 上午11:57:29 
* @version V1.0
 */
public interface PurchaseOrderService {

	/**
	 * 新增或修改采购单
	 * 
	 * @param purchaseOrder
	 * @return
	 */
	ResultMsg addOrUpdate(PurchaseOrder purchaseOrder, User user,String items);

	/**
	 * 查询采购单
	 * 
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrder> list(Page page, PurchaseOrder purchaseOrder);
	
	/**
	 * advanced query the purchase order; created by jie.jia at 2016-06-14 16:55
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrder> listByAdvancedQuery(Page page, PurchaseOrder purchaseOrder);

	/**
	 * 删除采购单
	 * 
	 * @param purchaseOrder
	 * @return
	 */
	ResultMsg deletePurchaseOrder(PurchaseOrder purchaseOrder);

	/**
	 * 根据采购单ID查询采购明细
	 * 
	 * @param id
	 * @return
	 */
	List<PurchaseItem> findPurchaseItemsByOrderId(String purchaseOrderId);

	List<PurchaseOrder> listAll();

	/**
	 * @param page
	 * @param purchaseOrderId
	 * @return
	 */
	List<PurchaseItem> findPurchaseItemsByOrderIdOderPage(Page page, String purchaseOrderId);

	/**
	 * @param companyId
	 * @return
	 */
	List<PurchaseOrder> findAllPurchaseOrderId(String companyId);

}
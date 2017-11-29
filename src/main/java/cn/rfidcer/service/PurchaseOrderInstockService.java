package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.InstockPurchaseItem;
import cn.rfidcer.bean.PurchaseInstockItem;
import cn.rfidcer.bean.PurchaseInstockOrder;
import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface PurchaseOrderInstockService {

	/**
	 * 新增或修改采购商品
	 * 
	 * @param purchaseOrder
	 * @param InstockMain
	 * @return
	 */
	ResultMsg addOrUpdate(PurchaseInstockOrder purchaseInstockOrder, User user);

	/**
	 * 根据采购单ID查询采购明细
	 * 
	 * @param id
	 * @return
	 */
	List<PurchaseItem> findPurchaseItemsByOrderId(String purchaseOrderId);

	List<PurchaseOrder> listAll();

	ResultMsg delPurchaseInstockOrder(String purchaseOrderId);

	List<PurchaseInstockItem> findPurchaseInstockItemsByOrderId(String purchaseOrderId);

	ResultMsg deletePurchaseItem(String purchaseItemId, String instockItemId, String goodsId, String num);

	/**
	 * @param page
	 * @param companyId
	 * @param instockType
	 * @return
	 */
	List<PurchaseInstockOrder> listPurchaseInstockOrder(Page page, String companyId, String instockType);

	/**
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseInstockOrder> listPurchaseInstockOrder(Page page, PurchaseOrder purchaseOrder);

	/**
	 * @param page
	 * @param purchaseOrderId
	 * @return
	 */
	List<InstockPurchaseItem> findInstockItemsByOrderId(Page page, String purchaseOrderId);

}

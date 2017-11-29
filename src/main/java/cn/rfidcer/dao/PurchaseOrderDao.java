package cn.rfidcer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.PurchaseInstockItem;
import cn.rfidcer.bean.PurchaseInstockOrder;
import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.interceptor.Page;

/**
 * 
* @Title: PurchaseOrderDao.java 
* @Package cn.rfidcer.dao 
* @Description: 采购单数据库层
* @author xzm & jie.jia
* @Copyright Copyright
* @date 2016年6月14日 上午11:28:20 
* @version V1.0
 */
public interface PurchaseOrderDao {

	
	/**查询采购单
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrder> list(Page page,@Param("purchaseOrder")PurchaseOrder purchaseOrder);
	
	List<PurchaseOrder> listAll();
	
	/**
	 * 高级查询；created by jie.jia at 2016-06-14 15:28
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrder> listByAdvancedQuery(Page page,@Param("purchaseOrder")PurchaseOrder purchaseOrder);
	
	/**采购入库
	 * @param page
	 * @param map
	 * @return
	 */
	List<PurchaseInstockOrder> listPurchaseInstockOrder(Page page,@Param("purchaseOrder")Map<String, String> map);
	
	/**
	 * @param page
	 * @param companyId
	 * @param instockType
	 * @return @Param("corpId")int corpId, @Param("addrId")int addrId
	 */
	List<PurchaseInstockOrder> listPurchaseInstockOrder(Page page, @Param("companyId")String companyId, @Param("instockType")String instockType);
	
	List<PurchaseOrder> findAllPurchaseOrderId(@Param("companyId")String companyId);
	
	/**根据采购单ID查询采购明细
	 * @param purchaseOrderId
	 * @return
	 */
	List<PurchaseItem> findPurchaseItemsByOrderId(String purchaseOrderId);
	
	/**
	 * 根据采购主单来查询采购明细; created by jie.jia at 2016-06-21 17:23
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseItem> listItemsByPurchaseOrder(Page page,@Param("purchaseOrder")PurchaseOrder purchaseOrder);
	
	/**查询采购明细
	 * @param purchaseOrderId
	 * @return
	 */
	List<PurchaseItem> findPurchaseItemsByOrderIdOderPage(Page page, @Param("purchaseOrderId")String purchaseOrderId);
	
	/**
	 * 查询重复数据
	 * @param purchaseOrder
	 * @return
	 */
	int findRepeat(PurchaseOrder purchaseOrder);
	
	/**查询采购入库明细
	 * @param purchaseOrderId
	 * @return
	 */
	List<PurchaseInstockItem> findPurchaseInstockItemsByOrderId(String purchaseOrderId);
	
	/**新增采购单
	 * @param purchaseOrder
	 * @return
	 */
	int addPurchaseOrder(PurchaseOrder purchaseOrder);
	
	/**新增采购单明细
	 * @param purchaseItem
	 * @return
	 */
	int addPurchaseItem(PurchaseItem purchaseItem);
	
	/**修改采购单
	 * @param purchaseOrder
	 * @return
	 */
	int updatePurchaseOrder(PurchaseOrder purchaseOrder);
	
	
	/**删除采购单
	 * @param purchaseOrder
	 * @return
	 */
	int deletePurchaseOrder(String purchaseOrderId);
	
	
	/**删除采购明细
	 * @param purchaseOrderId
	 * @return
	 */
	int deletePurchaseItemsByPurchaseOrderId(String purchaseOrderId);
	  
}

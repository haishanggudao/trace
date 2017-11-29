package cn.rfidcer.bean;

import java.util.List;

/**   
  * @Title: 采购入库bean
  * @author jgx
  * @date 2016年6月28日 下午1:27:19 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class PurchaseInstockOrder {
	/**采购单ID*/
	private String purchaseOrderId;
	/**入库单ID*/
	private InstockMain instockMain;
	/**采购单*/
	private PurchaseOrder purchaseOrder;
	/**商品明细*/
	private List<Goods> lstgoods;
	/**
	 * 获取 采购单ID
	 * @return purchaseOrderId
	 */
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	/**
	 * 设置 采购单ID
	 * @param purchaseOrderId 采购单ID
	 */
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	/**
	 * 获取 入库单ID
	 * @return instockMain
	 */
	public InstockMain getInstockMain() {
		return instockMain;
	}
	/**
	 * 设置 入库单ID
	 * @param instockMain 入库单ID
	 */
	public void setInstockMain(InstockMain instockMain) {
		this.instockMain = instockMain;
	}
	/**
	 * 获取 采购单
	 * @return purchaseOrder
	 */
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	/**
	 * 设置 采购单
	 * @param purchaseOrder 采购单
	 */
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	/**
	 * 获取 商品明细
	 * @return lstgoods
	 */
	public List<Goods> getLstgoods() {
		return lstgoods;
	}
	/**
	 * 设置 商品明细
	 * @param lstgoods 商品明细
	 */
	public void setLstgoods(List<Goods> lstgoods) {
		this.lstgoods = lstgoods;
	}
	
	@Override
	public String toString() {
		return "PurchaseInstockOrder [purchaseOrderId=" + purchaseOrderId
				+ ", instockMain=" + instockMain + ", purchaseOrder="
				+ purchaseOrder + ", lstgoods=" + lstgoods + "]";
	}
	
}

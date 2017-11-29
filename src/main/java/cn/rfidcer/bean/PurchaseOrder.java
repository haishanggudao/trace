package cn.rfidcer.bean;

import java.util.List;
/**   
  * @Title: 采购单
  * @author xzm & jie.jia
  * @date 2016年6月28日 下午1:47:36 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class  PurchaseOrder extends BaseEntity {
	/**采购单ID*/
	private String purchaseOrderId;
	/**所属企业ID*/
	private String companyId;
	/**订单日期*/
	private String orderTime;			
	/**采购单号*/
	private String purchaseOrderNo; 
	/**登记人*/
	private String registrant;	 
	/**供应商ID */
	private String supplierId;
	/**供应商ID */
	private String originId;
	/**#供应商名称 */
	private String supplierAlias;	
	/**#搜索订单日期开始*/
	private String orderTimeStart;		
	/**#搜索订单日期结束*/
	private String orderTimeEnd;	
	/**#采购明细*/
	private List<PurchaseItem> purchaseItems; 
	/**#产品名称 */
	private String productName;
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
	 * 获取 所属企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 所属企业ID
	 * @param companyId 所属企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 订单日期
	 * @return orderTime
	 */
	public String getOrderTime() {
		return orderTime;
	}
	/**
	 * 设置 订单日期
	 * @param orderTime 订单日期
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	/**
	 * 获取 采购单号
	 * @return purchaseOrderNo
	 */
	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}
	/**
	 * 设置 采购单号
	 * @param purchaseOrderNo 采购单号
	 */
	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}
	/**
	 * 获取 登记人
	 * @return registrant
	 */
	public String getRegistrant() {
		return registrant;
	}
	/**
	 * 设置 登记人
	 * @param registrant 登记人
	 */
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	/**
	 * 获取 供应商ID
	 * @return supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置 供应商ID
	 * @param supplierId 供应商ID
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取 #供应商名称
	 * @return supplierName
	 */
	public String getSupplierAlias() {
		return supplierAlias;
	}
	/**
	 * 设置 #供应商名称
	 * @param supplierName #供应商名称
	 */
	public void setSupplierAlias(String supplierAlias) {
		this.supplierAlias = supplierAlias;
	}
	/**
	 * 获取 #搜索订单日期开始
	 * @return orderTimeStart
	 */
	public String getOrderTimeStart() {
		return orderTimeStart;
	}
	/**
	 * 设置 #搜索订单日期开始
	 * @param orderTimeStart #搜索订单日期开始
	 */
	public void setOrderTimeStart(String orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}
	/**
	 * 获取 #搜索订单日期结束
	 * @return orderTimeEnd
	 */
	public String getOrderTimeEnd() {
		return orderTimeEnd;
	}
	/**
	 * 设置 #搜索订单日期结束
	 * @param orderTimeEnd #搜索订单日期结束
	 */
	public void setOrderTimeEnd(String orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}
	/**
	 * 获取 #采购明细
	 * @return purchaseItems
	 */
	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}
	/**
	 * 设置 #采购明细
	 * @param purchaseItems #采购明细
	 */
	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
	/**
	 * 获取 #产品名称
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 #产品名称
	 * @param productName #产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [purchaseOrderId=" + purchaseOrderId + ", companyId=" + companyId + ", orderTime="
				+ orderTime + ", purchaseOrderNo=" + purchaseOrderNo + ", registrant=" + registrant + ", supplierId="
				+ supplierId + ", originId=" + originId + ", supplierAlias=" + supplierAlias + ", orderTimeStart="
				+ orderTimeStart + ", orderTimeEnd=" + orderTimeEnd + ", purchaseItems=" + purchaseItems
				+ ", productName=" + productName + "]";
	}
	


	

}

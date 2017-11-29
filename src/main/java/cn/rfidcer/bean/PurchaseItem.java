package cn.rfidcer.bean;

import java.math.BigDecimal;

/**   
  * @Title:  POJO 采购明细 
  * @author jie.jia
  * @date 2016年6月28日 下午1:26:41 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class PurchaseItem extends BaseEntity {
	/**采购明细ID*/
	private String purchaseItemId;
	/**采购单ID*/
	private String purchaseOrderId;
	/**#产品*/
	private Product product;
	/**采购数量*/
	private String quantity;
	/**#产品规格明细*/
	private ProductStandardDetail standardDetail;
	/**采购单*/
	private PurchaseOrder purchaseOrder;
	/**产品ID*/
	private String productId;
	/**#产品名称*/
	private String productName;
	/**#产品规格名称*/
	private String fullStandardName;	
	/**产品规格明细ID*/
	private String productStandardDetailId;
	/**状态*/
	private int status;
	
	/**
	 * 产品进货价格
	 */
	private BigDecimal purchasePrice;
	
	/**获取产品进货价格
	 * @return the 产品进货价格
	 */
	public BigDecimal getPurchasePrice() {
		if(standardDetail!=null&&standardDetail.getPurchasePrice()!=null){
			purchasePrice=standardDetail.getPurchasePrice();
			return purchasePrice;
		}
		return new BigDecimal(0);
	}
	/**设置产品进货价格
	 * @param purchasePrice the 产品进货价格 to set
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * 获取 采购明细ID
	 * @return purchaseItemId
	 */
	public String getPurchaseItemId() {
		return purchaseItemId;
	}
	/**
	 * 设置 采购明细ID
	 * @param purchaseItemId 采购明细ID
	 */
	public void setPurchaseItemId(String purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}
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
	 * 获取 #产品
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * 设置 #产品
	 * @param product #产品
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * 获取 进货数量
	 * @return quantity
	 */
	public String getInstockNum() {
		return quantity;
	}
	/**
	 * 获取 采购数量
	 * @return quantity
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 * 设置 采购数量
	 * @param quantity 采购数量
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	/**
	 * 获取 #产品规格明细
	 * @return standardDetail
	 */
	public ProductStandardDetail getStandardDetail() {
		return standardDetail;
	}
	/**
	 * 设置 #产品规格明细
	 * @param standardDetail #产品规格明细
	 */
	public void setStandardDetail(ProductStandardDetail standardDetail) {
		this.standardDetail = standardDetail;
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
	 * 获取 产品ID
	 * @return productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置 产品ID
	 * @param productId 产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
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
	/**
	 * 获取 #产品规格名称
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		return standardDetail.getFullStandardName();
	}
	/**
	 * 设置 #产品规格名称
	 * @param fullStandardName #产品规格名称
	 */
	public void setFullStandardName(String fullStandardName) {
		this.fullStandardName = fullStandardName;
	}
	/**
	 * 获取 产品规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 产品规格明细ID
	 * @param productStandardDetailId 产品规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	/**
	 * 获取 状态
	 * @return status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * 设置 状态
	 * @param status 状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "PurchaseItem [purchaseItemId=" + purchaseItemId
				+ ", purchaseOrderId=" + purchaseOrderId + ", product="
				+ product + ", quantity=" + quantity + ", standardDetail="
				+ standardDetail + ", purchaseOrder=" + purchaseOrder
				+ ", productId=" + productId + ", productName=" + productName
				+ ", fullStandardName=" + fullStandardName
				+ ", productStandardDetailId=" + productStandardDetailId
				+ ", status=" + status + "]";
	}





}

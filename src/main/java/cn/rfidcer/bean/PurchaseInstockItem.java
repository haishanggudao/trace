package cn.rfidcer.bean;

/**   
  * @Title: #采购入库明细
  * @author jgx
  * @date 2016年6月28日 下午1:20:25 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class PurchaseInstockItem extends BaseEntity {
	/**商品ID*/
	private String goodsId;
	/**入库单明细ID*/
	private String instockItemId;
	/**商品批次*/
	private String goodsBatch;
	/**产地*/
	private String areaInfoId;
	/**分类名称*/
	private String catgName;
	/**屠宰场ID*/
	private String slaughterhouseId;
	/**屠宰场名称*/
	private String slaughterhouseName;
	/**采购单明细ID*/
	private String purchaseItemId;
	/**采购单ID*/
	private String purchaseOrderId;
	/**产品*/
	private Product product;
	/**采购数量*/
	private String quantity;
	/**产品规格明细*/
	private ProductStandardDetail standardDetail;
	/**
	 * 获取 商品ID
	 * @return goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置 商品ID
	 * @param goodsId 商品ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取 入库单明细ID
	 * @return instockItemId
	 */
	public String getInstockItemId() {
		return instockItemId;
	}
	/**
	 * 设置 入库单明细ID
	 * @param instockItemId 入库单明细ID
	 */
	public void setInstockItemId(String instockItemId) {
		this.instockItemId = instockItemId;
	}
	/**
	 * 获取 商品批次
	 * @return goodsBatch
	 */
	public String getGoodsBatch() {
		return goodsBatch;
	}
	/**
	 * 设置 商品批次
	 * @param goodsBatch 商品批次
	 */
	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
	}
	/**
	 * 获取 产地
	 * @return areaInfoId
	 */
	public String getAreaInfoId() {
		return areaInfoId;
	}
	/**
	 * 设置 产地
	 * @param areaInfoId 产地
	 */
	public void setAreaInfoId(String areaInfoId) {
		this.areaInfoId = areaInfoId;
	}
	/**
	 * 获取 分类名称
	 * @return catgName
	 */
	public String getCatgName() {
		return catgName;
	}
	/**
	 * 设置 分类名称
	 * @param catgName 分类名称
	 */
	public void setCatgName(String catgName) {
		this.catgName = catgName;
	}
	/**
	 * 获取 屠宰场ID
	 * @return slaughterhouseId
	 */
	public String getSlaughterhouseId() {
		return slaughterhouseId;
	}
	/**
	 * 设置 屠宰场ID
	 * @param slaughterhouseId 屠宰场ID
	 */
	public void setSlaughterhouseId(String slaughterhouseId) {
		this.slaughterhouseId = slaughterhouseId;
	}
	/**
	 * 获取 屠宰场名称
	 * @return slaughterhouseName
	 */
	public String getSlaughterhouseName() {
		return slaughterhouseName;
	}
	/**
	 * 设置 屠宰场名称
	 * @param slaughterhouseName 屠宰场名称
	 */
	public void setSlaughterhouseName(String slaughterhouseName) {
		this.slaughterhouseName = slaughterhouseName;
	}
	/**
	 * 获取 采购单明细ID
	 * @return purchaseItemId
	 */
	public String getPurchaseItemId() {
		return purchaseItemId;
	}
	/**
	 * 设置 采购单明细ID
	 * @param purchaseItemId 采购单明细ID
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
	 * 获取 产品
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * 设置 产品
	 * @param product 产品
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	 * 获取 产品规格明细
	 * @return standardDetail
	 */
	public ProductStandardDetail getStandardDetail() {
		return standardDetail;
	}
	/**
	 * 设置 产品规格明细
	 * @param standardDetail 产品规格明细
	 */
	public void setStandardDetail(ProductStandardDetail standardDetail) {
		this.standardDetail = standardDetail;
	}
	
	@Override
	public String toString() {
		return "PurchaseInstockItem [goodsId=" + goodsId + ", instockItemId="
				+ instockItemId + ", goodsBatch=" + goodsBatch + ", areaInfoId="
				+ areaInfoId + ", catgName=" + catgName + ", slaughterhouseId="
				+ slaughterhouseId + ", slaughterhouseName="
				+ slaughterhouseName + ", purchaseItemId=" + purchaseItemId
				+ ", purchaseOrderId=" + purchaseOrderId + ", product="
				+ product + ", quantity=" + quantity + ", standardDetail="
				+ standardDetail + "]";
	}
	
}

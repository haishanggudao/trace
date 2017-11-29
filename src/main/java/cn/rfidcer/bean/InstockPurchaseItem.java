package cn.rfidcer.bean;

/**   
  * @Title: #采购入库单
  * @author jgx
  * @date 2016年6月27日 下午3:46:19 
  * @Copyright Copyright
  * @version V1.0   
*/
public class InstockPurchaseItem extends BaseEntity {
	/**商品ID*/
	private String goodsId;
	/**入库明细ID*/
	private String instockItemId;
	/**商品批次*/
	private String goodsBatch;
	/**商品名称*/
	private String goodsName;
	/**地区ID*/
	private String areaInfoId;
	/**分类名称*/
	private String catgName;
	/**屠宰场ID*/
	private String slaughterhouseId;
	/**屠宰场名称*/
	private String slaughterhouseName;
	/**采购明细单ID*/
	private String purchaseItemId;
	/**采购单ID*/
	private String purchaseOrderId;
	/**源头信息*/
	private String originId;
	private String originName;


	
	
	private Product product;
	/**入库数量*/
	private String instockNum;
	/**产品规格明细*/
	private ProductStandardDetail standardDetail;
	/**产品名称+市场商品码*/
	private String productNameMarketCode;
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
	 * 获取 入库明细ID
	 * @return instockItemId
	 */
	public String getInstockItemId() {
		return instockItemId;
	}
	/**
	 * 设置 入库明细ID
	 * @param instockItemId 入库明细ID
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
	 * 获取 规格名称
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		return standardDetail.getFullStandardName();
	}
	/**
	 * 获取产品名称
	 * @return productName
	 */	
	public String getProductName() {
		return product.getProductName();
	}
	/**
	 * 获取 产品ID
	 * @return productId
	 */
	public String getProductId() {
		return product.getProductId();
	}
	/**
	 * 获取 商品名称
	 * @return goodsName
	 */
	public String getGoodsName() {
		if (product != null && standardDetail != null) {
			goodsName = product.getProductName() + "(" + standardDetail.getFullStandardName() + ")" + "-" + goodsBatch;
		}
		return goodsName;
	}
	/**
	 * 获取 规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return standardDetail.getProductStandardDetailId();
	}
	/**
	 * 设置 商品名称
	 * @param goodsName 商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取 地区ID
	 * @return areaInfoId
	 */
	public String getAreaInfoId() {
		return areaInfoId;
	}
	/**
	 * 设置 地区ID
	 * @param areaInfoId 地区ID
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
	 * 获取 采购明细单ID
	 * @return purchaseItemId
	 */
	public String getPurchaseItemId() {
		return purchaseItemId;
	}
	/**
	 * 设置 采购明细单ID
	 * @param purchaseItemId 采购明细单ID
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
	 * 获取 入库数量
	 * @return instockNum
	 */
	public String getInstockNum() {
		return instockNum;
	}
	/**
	 * 设置 入库数量
	 * @param instockNum 入库数量
	 */
	public void setInstockNum(String instockNum) {
		this.instockNum = instockNum;
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
	
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getProductNameMarketCode() {
		return productNameMarketCode;
	}
	public void setProductNameMarketCode(String productNameMarketCode) {
		this.productNameMarketCode = productNameMarketCode;
	}
	@Override
	public String toString() {
		return "InstockPurchaseItem [goodsId=" + goodsId + ", instockItemId="
				+ instockItemId + ", goodsBatch=" + goodsBatch + ", goodsName="
				+ goodsName + ", areaInfoId=" + areaInfoId + ", catgName="
				+ catgName + ", slaughterhouseId=" + slaughterhouseId
				+ ", slaughterhouseName=" + slaughterhouseName
				+ ", purchaseItemId=" + purchaseItemId + ", purchaseOrderId="
				+ purchaseOrderId + ", product=" + product + ", instockNum="
				+ instockNum + ", standardDetail=" + standardDetail + "]";
	}


}

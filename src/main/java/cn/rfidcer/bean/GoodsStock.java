package cn.rfidcer.bean;

import java.math.BigDecimal;

/**   
  * @Title: POJO 商品库存
  * @author xzm & jie.jia
  * @date 2016年6月27日 下午3:19:05 
  * @Copyright Copyright
  * @version V1.0   
*/
public class GoodsStock extends BaseEntity{
	/**商品库存ID*/
	private String goodsStockId;
	/**商品ID*/
	private String goodsId;
	/**库位号ID*/
	private String storageLocationId;
	/**库存数量*/
	private BigDecimal stockNum;
	/**企业ID*/
	private String companyId;
	/**预警数量*/
	private BigDecimal warningNum;
	/**是否删除 0-正常 1-已删除*/
	private String isDeleted; 
	/**#产品ID*/
	private String productId; 
	/**#产品明细ID*/
	private String productStandardDetailId;
	/**#商品批次*/
	private String goodsBatch;
	/**#产品*/
	private Product product;
	/**#产品规格明细*/
	private ProductStandardDetail productStandardDetail;
	//无参构造器
	public GoodsStock() {}
	//有参构造器
	public GoodsStock(String goodsId, String companyId) {
		this.goodsId = goodsId;
		this.companyId = companyId;
	}
	/**
	 * 获取 商品库存ID
	 * @return goodsStockId
	 */
	public String getGoodsStockId() {
		return goodsStockId;
	}
	/**
	 * 设置 商品库存ID
	 * @param goodsStockId 商品库存ID
	 */
	public void setGoodsStockId(String goodsStockId) {
		this.goodsStockId = goodsStockId;
	}
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
	 * 获取 库位号ID
	 * @return storageLocationId
	 */
	public String getStorageLocationId() {
		return storageLocationId;
	}
	/**
	 * 设置 库位号ID
	 * @param storageLocationId 库位号ID
	 */
	public void setStorageLocationId(String storageLocationId) {
		this.storageLocationId = storageLocationId;
	}
	/**
	 * 获取 库存数量
	 * @return stockNum
	 */
	public BigDecimal getStockNum() {
		return stockNum;
	}
	/**
	 * 设置 库存数量
	 * @param stockNum 库存数量
	 */
	public void setStockNum(BigDecimal stockNum) {
		this.stockNum = stockNum;
	}
	/**
	 * 获取 企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 企业ID
	 * @param companyId 企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 预警数量
	 * @return warningNum
	 */
	public BigDecimal getWarningNum() {
		return warningNum;
	}
	/**
	 * 设置 预警数量
	 * @param warningNum 预警数量
	 */
	public void setWarningNum(BigDecimal warningNum) {
		this.warningNum = warningNum;
	}
	/**
	 * 获取 是否删除 0-正常 1-已删除
	 * @return isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置 是否删除 0-正常 1-已删除
	 * @param isDeleted 是否删除 0-正常 1-已删除
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取 #产品ID
	 * @return productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置 #产品ID
	 * @param productId #产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取 #产品明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 #产品明细ID
	 * @param productStandardDetailId #产品明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	/**
	 * 获取 #商品批次
	 * @return goodsBatch
	 */
	public String getGoodsBatch() {
		return goodsBatch;
	}
	/**
	 * 设置 #商品批次
	 * @param goodsBatch #商品批次
	 */
	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
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
	 * 获取 #产品规格明细
	 * @return productStandardDetail
	 */
	public ProductStandardDetail getProductStandardDetail() {
		return productStandardDetail;
	}
	/**
	 * 设置 #产品规格明细
	 * @param productStandardDetail #产品规格明细
	 */
	public void setProductStandardDetail(
			ProductStandardDetail productStandardDetail) {
		this.productStandardDetail = productStandardDetail;
	}
	@Override
	public String toString() {
		return "GoodsStock [goodsStockId=" + goodsStockId + ", goodsId="
				+ goodsId + ", storageLocationId=" + storageLocationId
				+ ", stockNum=" + stockNum + ", companyId=" + companyId
				+ ", warningNum=" + warningNum + ", isDeleted=" + isDeleted
				+ ", productId=" + productId + ", productStandardDetailId="
				+ productStandardDetailId + ", goodsBatch=" + goodsBatch
				+ ", product=" + product + ", productStandardDetail="
				+ productStandardDetail + "]";
	}
}

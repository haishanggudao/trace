package cn.rfidcer.bean;

import java.math.BigDecimal;

public class InstockItem extends BaseEntity {
	/**入库明细单ID*/
	private String instockItemId;
	/**入库单ID*/
	private String instockMainId;
	/**商品ID*/
	private String goodsId;
	/**#商品名称*/
	private String goodsName;
	/**入库数量*/
	private String instockNum;
	/**#产品ID*/
	private String productId;
	/**#产品明细ID*/
	private String productStandardDetailId;
	/**#商品批次*/
	private String goodsBatch;
	/**源头信息*/
	private String originId;
	/**
	 * 产品进货价格
	 */
	private BigDecimal purchasePrice;
	
	/**获取产品进货价格
	 * @return the 产品进货价格
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	/**设置产品进货价格
	 * @param purchasePrice the 产品进货价格 to set
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * 获取 入库明细单ID
	 * @return instockItemId
	 */
	public String getInstockItemId() {
		return instockItemId;
	}
	/**
	 * 设置 入库明细单ID
	 * @param instockItemId 入库明细单ID
	 */
	public void setInstockItemId(String instockItemId) {
		this.instockItemId = instockItemId;
	}
	/**
	 * 获取 入库单ID
	 * @return instockMainId
	 */
	public String getInstockMainId() {
		return instockMainId;
	}
	/**
	 * 设置 入库单ID
	 * @param instockMainId 入库单ID
	 */
	public void setInstockMainId(String instockMainId) {
		this.instockMainId = instockMainId;
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
	 * 获取 #商品名称
	 * @return goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置 #商品名称
	 * @param goodsName #商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	@Override
	public String toString() {
		return "InstockItem [instockItemId=" + instockItemId
				+ ", instockMainId=" + instockMainId + ", goodsId=" + goodsId
				+ ", goodsName=" + goodsName + ", instockNum=" + instockNum
				+ ", productId=" + productId + ", productStandardDetailId="
				+ productStandardDetailId + ", goodsBatch=" + goodsBatch + "]";
	}
	

}
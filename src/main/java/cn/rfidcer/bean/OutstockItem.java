package cn.rfidcer.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.rfidcer.enums.ProductType;
import cn.rfidcer.util.StringUtil;

/**   
  * @Title: 商品出库明细表
  * @author jgx
  * @date 2016年6月27日 下午4:24:53 
  * @Copyright Copyright
  * @version V1.0   
*/
public class OutstockItem extends BaseEntity {
	/**出库明细单ID*/
	private String outstockItemId;
	/**出库单ID*/
	private String outstockMainId;
	/**销售单ID*/
	private String saleOrderId;
	/**商品ID*/
	private String goodsId;
	/**#商品明细ID*/
	private String goodsDetailId;
	/**#商品名称*/
	private String goodsName;
	/**#商品批次*/
	private String goodsBatch;
	/**#产品*/
	private Product product;
	/**#产品ID*/
	private String productId;
	/**#产品名称*/
	private String productName;
	/**#产品规格明细ID*/
	private String productStandardDetailId;
	/**#产品类型名称*/
	private String productTypeName;
	/**#产品类型*/
	private String productType;	
	/**#产品规格名称*/
	private String fullStandardName;
	/**#产品规格明细*/
	private ProductStandardDetail standardDetail;
	/**出库数量*/
	private BigDecimal outstockNum;
	/**追溯码*/
	private String traceCode;
	/**#商品里的配送类型*/
	private String deliverType;
	/**配送时间*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp deliveryTime;
	/**配送人*/
	private String deliveryBy;
	/**#库存数量*/
	private BigDecimal stockNum;
	/**商品表*/
	private Goods goods;
	/**
	 * 获取商品表
	 * @return
	 */
	public Goods getGoods() {
		return goods;
	}
	/**
	 * 设置商品表
	 * @param goods
	 */
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	/**
	 * 获取 出库明细单ID
	 * @return outstockItemId
	 */
	public String getOutstockItemId() {
		return outstockItemId;
	}
	/**
	 * 设置 出库明细单ID
	 * @param outstockItemId 出库明细单ID
	 */
	public void setOutstockItemId(String outstockItemId) {
		this.outstockItemId = outstockItemId;
	}
	/**
	 * 获取 出库单ID
	 * @return outstockMainId
	 */
	public String getOutstockMainId() {
		return outstockMainId;
	}
	/**
	 * 设置 出库单ID
	 * @param outstockMainId 出库单ID
	 */
	public void setOutstockMainId(String outstockMainId) {
		this.outstockMainId = outstockMainId;
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
	 * 获取 #商品明细ID
	 * @return goodsDetailId
	 */
	public String getGoodsDetailId() {
		return goodsDetailId;
	}
	/**
	 * 设置 #商品明细ID
	 * @param goodsDetailId #商品明细ID
	 */
	public void setGoodsDetailId(String goodsDetailId) {
		this.goodsDetailId = goodsDetailId;
	}
	/**
	 * 获取 #商品名称
	 * @return goodsName
	 */
	public String getGoodsName() {
		if (product != null && standardDetail != null) {
			goodsName = product.getProductName() + "(" + standardDetail.getFullStandardName() + ")" + "-" + goodsBatch;
		}else{
			goodsName = "";
		}
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
	 * 获取 #产品规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 #产品规格明细ID
	 * @param productStandardDetailId #产品规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	/**
	 * 获取 #产品类型名称
	 * @return productTypeName
	 */
	public String getProductTypeName() {
		String typeName = "";
		if(!StringUtil.isBlank(this.getProductType()))
		{
			typeName = ProductType.Enums.getName(Integer.parseInt(this.getProductType()));
		}
		this.setProductTypeName(typeName);
		return productTypeName;
	}
	/**
	 * 设置 #产品类型名称
	 * @param productTypeName #产品类型名称
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	/**
	 * 获取 #产品类型
	 * @return productType
	 */
	public String getProductType() {
		if(StringUtil.isBlank(productType)){
			productType = "1";
		}
		return productType;
	}
	/**
	 * 设置 #产品类型
	 * @param productType #产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取 #产品规格名称
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		if(standardDetail!=null){
			fullStandardName=standardDetail.getFullStandardName();
		}
		return fullStandardName;
	}
	/**
	 * 设置 #产品规格名称
	 * @param fullStandardName #产品规格名称
	 */
	public void setFullStandardName(String fullStandardName) {
		this.fullStandardName = fullStandardName;
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
	 * 获取 出库数量
	 * @return outstockNum
	 */
	public BigDecimal getOutstockNum() {
		return outstockNum;
	}
	/**
	 * 设置 出库数量
	 * @param outstockNum 出库数量
	 */
	public void setOutstockNum(BigDecimal outstockNum) {
		this.outstockNum = outstockNum;
	}
	/**
	 * 获取 追溯码
	 * @return traceCode
	 */
	public String getTraceCode() {
		return traceCode;
	}
	/**
	 * 设置 追溯码
	 * @param traceCode 追溯码
	 */
	public void setTraceCode(String traceCode) {
		this.traceCode = traceCode;
	}
	/**
	 * 获取 #商品里的配送类型
	 * @return deliverType
	 */
	public String getDeliverType() {
		return deliverType;
	}
	/**
	 * 设置 #商品里的配送类型
	 * @param deliverType #商品里的配送类型
	 */
	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}
	/**
	 * 获取 配送时间
	 * @return deliveryTime
	 */
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * 设置 配送时间
	 * @param deliveryTime 配送时间
	 */
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * 获取 配送人
	 * @return deliveryBy
	 */
	public String getDeliveryBy() {
		return deliveryBy;
	}
	/**
	 * 设置 配送人
	 * @param deliveryBy 配送人
	 */
	public void setDeliveryBy(String deliveryBy) {
		this.deliveryBy = deliveryBy;
	}
	/**
	 * 获取 #库存数量
	 * @return stockNum
	 */
	public BigDecimal getStockNum() {
		if(stockNum==null){
			stockNum =new BigDecimal(0);
		}
		return stockNum;
	}
	/**
	 * 设置 #库存数量
	 * @param stockNum #库存数量
	 */
	public void setStockNum(BigDecimal stockNum) {
		this.stockNum = stockNum;
	}
	/**
	 * 获取 销售单ID
	 * @return saleOrderId
	 */
	public String getSaleOrderId() {
		return saleOrderId;
	}
	/**
	 * 设置 销售单ID
	 * @param saleOrderId 销售单ID
	 */
	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	@Override
	public String toString() {
		return "OutstockItem [outstockItemId=" + outstockItemId
				+ ", outstockMainId=" + outstockMainId + ", saleOrderId="
				+ saleOrderId + ", goodsId=" + goodsId + ", goodsDetailId="
				+ goodsDetailId + ", goodsName=" + goodsName + ", goodsBatch="
				+ goodsBatch + ", product=" + product + ", productId="
				+ productId + ", productName=" + productName
				+ ", productStandardDetailId=" + productStandardDetailId
				+ ", productTypeName=" + productTypeName + ", productType="
				+ productType + ", fullStandardName=" + fullStandardName
				+ ", standardDetail=" + standardDetail + ", outstockNum="
				+ outstockNum + ", traceCode=" + traceCode + ", deliverType="
				+ deliverType + ", deliveryTime=" + deliveryTime
				+ ", deliveryBy=" + deliveryBy + ", stockNum=" + stockNum + "]";
	}

}
package cn.rfidcer.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
  * @Title: 商品明细
  * @author xzm
  * @date 2016年6月27日 上午10:14:51 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class GoodsDetail extends BaseEntity {
	/**商品明细ID*/
	private String goodsDetailId;
	/**商品ID*/
	private String goodsId;
	/**#商品批次*/
	private String goodsBatch;
	/**#规格数量*/
	private String productStandardNum;
	/**#进货价格*/
	private String purchasePrice;
	/**#销售价格*/
	private String salePrice;
	/**#零售价格*/
	private String retailPrice;
	/**产品名称 非数据库字段*/
	private String productName;
	/**企业ID*/
	private String companyId;
	/**标签类型*/
	private String type;
	/**商品标签二维码*/
	private String code;
	/**标签密钥*/
	private String secretKey;
	/**扫描时间 非数据库字段-手持机显示使用*/
	private String scanTime;
	/**条形码*/
	private String barcode;
	/**产品ID*/
	private String productId;
	/**规格明细ID*/
	private String productStandardDetailId;

	/**
	 * 获取 商品明细ID
	 * @return goodsDetailId
	 */
	public String getGoodsDetailId() {
		return goodsDetailId;
	}
	/**
	 * 设置 商品明细ID
	 * @param goodsDetailId 商品明细ID
	 */
	public void setGoodsDetailId(String goodsDetailId) {
		this.goodsDetailId = goodsDetailId;
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
	 * 获取 产品名称 非数据库字段
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 产品名称 非数据库字段
	 * @param productName 产品名称 非数据库字段
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * 获取 标签类型
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置 标签类型
	 * @param type 标签类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取 商品标签二维码
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置 商品标签二维码
	 * @param code 商品标签二维码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取 标签密钥
	 * @return secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * 设置 标签密钥
	 * @param secretKey 标签密钥
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	/**
	 * 获取 扫描时间 非数据库字段-手持机显示使用
	 * @return scanTime
	 */
	public String getScanTime() {
		return scanTime;
	}
	/**
	 * 设置 扫描时间 非数据库字段-手持机显示使用
	 * @param scanTime 扫描时间 非数据库字段-手持机显示使用
	 */
	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}
	/**
	 * 获取 条形码
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}
	/**
	 * 设置 条形码
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	 * 获取 #规格数量
	 * @return productStandardNum
	 */
	public String getProductStandardNum() {
		return productStandardNum;
	}
	/**
	 * 设置 #规格数量
	 * @param productStandardNum #规格数量
	 */
	public void setProductStandardNum(String productStandardNum) {
		this.productStandardNum = productStandardNum;
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
	 * 获取 规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 规格明细ID
	 * @param productStandardDetailId 规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	
	/**
	 * 获取 #进货价格
	 * @return purchasePrice
	 */
	public String getPurchasePrice() {
		return purchasePrice;
	}
	/**
	 * 设置 #进货价格
	 * @param purchasePrice #进货价格
	 */
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	
	/**
	 * 获取 #销售价格
	 * @return salePrice
	 */
	public String getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置 #销售价格
	 * @param salePrice #销售价格
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取 #零售价格
	 * @return retailPrice
	 */
	public String getRetailPrice() {
		return retailPrice;
	}
	/**
	 * 设置 #零售价格
	 * @param retailPrice #零售价格
	 */
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	@Override
	public String toString() {
		return "GoodsDetail [goodsDetailId=" + goodsDetailId + ", goodsId=" + goodsId + ", productName=" + productName
				+ ", companyId=" + companyId + ", type=" + type + ", code=" + code + ", secretKey=" + secretKey
				+ ", scanTime=" + scanTime + ", barcode=" + barcode + "]";
	} 

}

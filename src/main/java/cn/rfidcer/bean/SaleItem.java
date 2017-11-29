package cn.rfidcer.bean;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import cn.rfidcer.util.BigDecimalUtil;

/**   
  * @Title: 销售单明细
  * @author jie.jia
  * @date 2016年6月27日 下午3:09:19 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class SaleItem extends BaseEntity{
	/**销售单明细ID*/
	private String saleItemId;
	/**销售单ID*/
	private String saleOrderId;
	/**产品ID*/
	private String productId;
	/**数量*/
	private BigDecimal quantity;
	/**库存数量*/
	private BigDecimal stockNum;
	/**价格*/
	private BigDecimal salePrice;
	/**产品规格明细ID*/
	private String productStandardDetailId; 
	/**#产品信息*/
	private Product product;
	/**#产品规格明细*/
	private ProductStandardDetail standardDetail;
	/**#规格明细 + 规格*/
	private String fullStandardName; 
	
	/**#规格名称*/
	private String productStandardName; 
	
	/**状态 0：未出库，-1位已出库*/
	private String status;
	
	/**产品表的产地*/
	private String catgName;
	/**商品表，用来显示商品批次*/
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
	 * 获取 销售单明细ID
	 * @return saleItemId
	 */
	public String getSaleItemId() {
		return saleItemId;
	}
	/**
	 * 设置 销售单明细ID
	 * @param saleItemId 销售单明细ID
	 */
	public void setSaleItemId(String saleItemId) {
		this.saleItemId = saleItemId;
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
	 * 获取 数量
	 * @return quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	/**
	 * 获取 数量
	 * @return quantity
	 */
	public BigDecimal getOutstockNum() {
		return quantity;
	}

	
	
	/**
	 * 设置 数量
	 * @param quantity 数量
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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
	 * 获取 #产品信息
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * 设置 #产品信息
	 * @param product #产品信息
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	 * 获取 #规格明细 + 规格
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		if(standardDetail!=null){
			fullStandardName=standardDetail.getFullStandardName();
		}
		return fullStandardName;
	}
	/**
	 * 设置 #规格明细 + 规格
	 * @param fullStandardName #规格明细 + 规格
	 */
	public void setFullStandardName(String fullStandardName) {
		this.fullStandardName = fullStandardName;
	}
	/**
	 * 获取 产品名称
	 * @return productName
	 */
	public String getProductName() {
		String strproductName="";
		if(product!=null){
			strproductName = product.getProductName();
		}
		return strproductName;
	}
	/**
	 * 获取 状态 0：未出库，-1位已出库
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 状态 0：未出库，-1位已出库
	 * @param status 状态 0：未出库，-1位已出库
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取 价格
	 * @return salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置 价格
	 * @param salePrice 价格
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
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
	 * 获取 #规格名称
	 * @return productStandardName
	 */
	public String getProductStandardName() {
		return productStandardName;
	}
	/**
	 * 设置 #规格名称
	 * @param productStandardName #规格名称
	 */
	public void setProductStandardName(String productStandardName) {
		this.productStandardName = productStandardName;
	}
	/**
	 * 获取 #产品表的产地
	 * @return catgName #产品表的产地
	 */
	public String getCatgName() {
		return catgName;
	}
	/**
	 * 设置 #产品表的产地
	 * @param catgName #产品表的产地
	 */
	public void setCatgName(String catgName) {
		this.catgName = catgName;
	}
	@Override
	public String toString() {
		return "SaleItem [saleItemId=" + saleItemId + ", saleOrderId="
				+ saleOrderId + ", productId=" + productId + ", quantity="
				+ quantity + ", productStandardDetailId="
				+ productStandardDetailId + ", product=" + product
				+ ", standardDetail=" + standardDetail + ", fullStandardName="
				+ fullStandardName 
				+ ", status=" + status + "]";
	}
}
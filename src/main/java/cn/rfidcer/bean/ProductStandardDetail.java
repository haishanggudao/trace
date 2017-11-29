package cn.rfidcer.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.rfidcer.util.BigDecimalUtil;

/**   
  * @Title: 产品规格明细
  * @author xzm
  * @date 2016年6月27日 下午7:53:37 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class ProductStandardDetail extends BaseEntity {
	/**产品规格明细ID*/
	private String productStandardDetailId;
	/**产品信息*/
	private Product product;
	/**产品规格数量*/
	private String productStandardNum;
	/**#产品规格*/
	private ProductStandard productStandard;
	/**#产品规格名称*/
	private String fullStandardName;
	/**产品ID*/
	private String productId;
	/**#产品名称*/
	private String productName;  
	/**规格ID*/
	private String productStandardId;
	/**#规格名称*/
	private String productStandardName;
	/**进货价格*/
	private BigDecimal purchasePrice;
	/**进货货币*/
	private String purchaseCurrency;
	/**销售货币*/
	private String saleCurrency;
	/**销售价格*/
	private BigDecimal salePrice;
	/**
	 * 零售价格
	 */
	private BigDecimal retailPrice;
	/**
	 * 零售货币
	 */
	private String retailCurrency;
	/**#商品名称*/
	private String productStandardDetailName;
	/**#企业ID*/
	private String companyId; 
	/**#产品分类*/
	private String productCategoryId;
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
	 * 获取 产品信息
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * 设置 产品信息
	 * @param product 产品信息
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * 获取 产品规格数量
	 * @return productStandardNum
	 */
	public String getProductStandardNum() {
		return productStandardNum;
	}
	/**
	 * 设置 产品规格数量
	 * @param productStandardNum 产品规格数量
	 */
	public void setProductStandardNum(String productStandardNum) {
		this.productStandardNum = productStandardNum;
	}
	/**
	 * 获取 #产品规格
	 * @return productStandard
	 */
	public ProductStandard getProductStandard() {
		return productStandard;
	}
	/**
	 * 设置 #产品规格
	 * @param productStandard #产品规格
	 */
	public void setProductStandard(ProductStandard productStandard) {
		this.productStandard = productStandard;
	}
	/**
	 * 获取 #产品规格名称
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		if (productStandard != null) {
			// 过滤掉规格为1的显示
			if("1".equalsIgnoreCase(productStandardNum.toString() )){
				return productStandard.getProductStandardName();
			}else{
				return BigDecimalUtil.delLastZero(productStandardNum.toString()) + productStandard.getProductStandardName();
			}
			
		} else {
			return fullStandardName;
		}
	}
	/**
	 * 设置 #产品规格名称
	 * @param fullStandardName #产品规格名称
	 */
	public void setFullStandardName(String fullStandardName) {
		this.fullStandardName = fullStandardName;
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
	 * 获取 规格ID
	 * @return productStandardId
	 */
	public String getProductStandardId() {
		return productStandardId;
	}
	/**
	 * 设置 规格ID
	 * @param productStandardId 规格ID
	 */
	public void setProductStandardId(String productStandardId) {
		this.productStandardId = productStandardId;
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
	 * 获取 进货价格
	 * @return purchasePrice
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	/**
	 * 设置 进货价格
	 * @param purchasePrice 进货价格
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * 获取 进货货币
	 * @return purchaseCurrency
	 */
	public String getPurchaseCurrency() {
		return purchaseCurrency;
	}
	/**
	 * 设置 进货货币
	 * @param purchaseCurrency 进货货币
	 */
	public void setPurchaseCurrency(String purchaseCurrency) {
		this.purchaseCurrency = purchaseCurrency;
	}
	/**
	 * 获取 销售货币
	 * @return saleCurrency
	 */
	public String getSaleCurrency() {
		return saleCurrency;
	}
	/**
	 * 设置 销售货币
	 * @param saleCurrency 销售货币
	 */
	public void setSaleCurrency(String saleCurrency) {
		this.saleCurrency = saleCurrency;
	}
	/**
	 * 获取 销售价格
	 * @return salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置 销售价格
	 * @param salePrice 销售价格
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取 #商品名称
	 * @return productStandardDetailName
	 */
	public String getProductStandardDetailName() {
		return productStandardDetailName;
	}
	/**
	 * 设置 #商品名称
	 * @param productStandardDetailName #商品名称
	 */
	public void setProductStandardDetailName(String productStandardDetailName) {
		this.productStandardDetailName = productStandardDetailName;
	}
	/**
	 * 获取 #企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 #企业ID
	 * @param companyId #企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 #产品分类
	 * @return productCategoryId
	 */
	public String getProductCategoryId() {
		return productCategoryId;
	}
	/**
	 * 设置 #产品分类
	 * @param productCategoryId #产品分类
	 */
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	/**
	 * 获取 零售价格
	 * @return the retailPrice
	 */
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	/**
	 * 设置 零售价格
	 * @param retailPrice the retailPrice to set
	 */
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	/**
	 * 获取 零售货币
	 * @return the retailCurrency
	 */
	public String getRetailCurrency() {
		return retailCurrency;
	}
	/**
	 * 设置 零售货币
	 * @param retailCurrency the retailCurrency to set
	 */
	public void setRetailCurrency(String retailCurrency) {
		this.retailCurrency = retailCurrency;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductStandardDetail [productStandardDetailId=" + productStandardDetailId + ", product=" + product
				+ ", productStandardNum=" + productStandardNum + ", productStandard=" + productStandard
				+ ", fullStandardName=" + fullStandardName + ", productId=" + productId + ", productName=" + productName
				+ ", productStandardId=" + productStandardId + ", productStandardName=" + productStandardName
				+ ", purchasePrice=" + purchasePrice + ", purchaseCurrency=" + purchaseCurrency + ", saleCurrency="
				+ saleCurrency + ", salePrice=" + salePrice + ", retailPrice=" + retailPrice + ", retailCurrency="
				+ retailCurrency + ", productStandardDetailName=" + productStandardDetailName + ", companyId="
				+ companyId + ", productCategoryId=" + productCategoryId + "]";
	}
	
	
}

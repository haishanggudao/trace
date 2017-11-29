package cn.rfidcer.bean;

import java.math.BigDecimal;

/**   
  * @Title: 产品规格转化
  * @author xzm
  * @date 2016年6月27日 下午7:58:36 
  * @Copyright Copyright
  * @version V1.0   
*/
public class ProductStandardRate extends BaseEntity {
	/**规格转化ID*/
	private String productStandardRateId;
	/**规格ID*/
	private String productStandardId;
	/**目标规格转化ID*/
	private String rateProductStandardId;
	/**#产品规格*/
	private ProductStandard productStandard;
	/**#目标产品规格*/
	private ProductStandard rateProductStandard;
	/**产品规格数量*/
	private BigDecimal productStandardNum;
	/**目标产品规格数量*/
	private BigDecimal rateProductStandardNum;
	/**企业ID*/
	private String companyId;
	/**#产品分类*/
	private ProductCategory productCategory;
	/**产品分类ID*/
	private String productCategoryId;
	/**
	 * 获取 规格转化ID
	 * @return productStandardRateId
	 */
	public String getProductStandardRateId() {
		return productStandardRateId;
	}
	/**
	 * 设置 规格转化ID
	 * @param productStandardRateId 规格转化ID
	 */
	public void setProductStandardRateId(String productStandardRateId) {
		this.productStandardRateId = productStandardRateId;
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
	 * 获取 目标规格转化ID
	 * @return rateProductStandardId
	 */
	public String getRateProductStandardId() {
		return rateProductStandardId;
	}
	/**
	 * 设置 目标规格转化ID
	 * @param rateProductStandardId 目标规格转化ID
	 */
	public void setRateProductStandardId(String rateProductStandardId) {
		this.rateProductStandardId = rateProductStandardId;
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
	 * 获取 #目标产品规格
	 * @return rateProductStandard
	 */
	public ProductStandard getRateProductStandard() {
		return rateProductStandard;
	}
	/**
	 * 设置 #目标产品规格
	 * @param rateProductStandard #目标产品规格
	 */
	public void setRateProductStandard(ProductStandard rateProductStandard) {
		this.rateProductStandard = rateProductStandard;
	}
	/**
	 * 获取 产品规格数量
	 * @return productStandardNum
	 */
	public BigDecimal getProductStandardNum() {
		return productStandardNum;
	}
	/**
	 * 设置 产品规格数量
	 * @param productStandardNum 产品规格数量
	 */
	public void setProductStandardNum(BigDecimal productStandardNum) {
		this.productStandardNum = productStandardNum;
	}
	/**
	 * 获取 目标产品规格数量
	 * @return rateProductStandardNum
	 */
	public BigDecimal getRateProductStandardNum() {
		return rateProductStandardNum;
	}
	/**
	 * 设置 目标产品规格数量
	 * @param rateProductStandardNum 目标产品规格数量
	 */
	public void setRateProductStandardNum(BigDecimal rateProductStandardNum) {
		this.rateProductStandardNum = rateProductStandardNum;
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
	 * 获取 #产品分类
	 * @return productCategory
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	/**
	 * 设置 #产品分类
	 * @param productCategory #产品分类
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	/**
	 * 获取 产品分类ID
	 * @return productCategoryId
	 */
	public String getProductCategoryId() {
		return productCategoryId;
	}
	/**
	 * 设置 产品分类ID
	 * @param productCategoryId 产品分类ID
	 */
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	@Override
	public String toString() {
		return "ProductStandardRate [productStandardRateId="
				+ productStandardRateId + ", productStandardId="
				+ productStandardId + ", rateProductStandardId="
				+ rateProductStandardId + ", productStandard=" + productStandard
				+ ", rateProductStandard=" + rateProductStandard
				+ ", productStandardNum=" + productStandardNum
				+ ", rateProductStandardNum=" + rateProductStandardNum
				+ ", companyId=" + companyId + ", productCategory="
				+ productCategory + ", productCategoryId=" + productCategoryId
				+ "]";
	}
	
}

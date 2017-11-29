package cn.rfidcer.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
  * @Title: 产品规格
  * @author xzm
  * @date 2016年6月27日 下午7:52:01 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value=Include.NON_NULL)
public class ProductStandard extends BaseEntity{
	/**规格ID*/
	private String productStandardId;
	/**规格名称*/
	private String productStandardName;
	/**产品分类ID*/
	private String productCategoryId;
	/**#产品分类名称*/
	private String productCategoryName;
	/**#企业ID*/
	private String companyId;
	
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
	 * 获取 规格名称
	 * @return productStandardName
	 */
	public String getProductStandardName() {
		return productStandardName;
	}

	/**
	 * 设置 规格名称
	 * @param productStandardName 规格名称
	 */
	public void setProductStandardName(String productStandardName) {
		this.productStandardName = productStandardName;
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

	/**
	 * 获取 #产品分类名称
	 * @return productCategoryName
	 */
	public String getProductCategoryName() {
		return productCategoryName;
	}

	/**
	 * 设置 #产品分类名称
	 * @param productCategoryName #产品分类名称
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
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

	@Override
	public String toString() {
		return "ProductStandard [productStandardId=" + productStandardId + ", productStandardName="
				+ productStandardName + ", productCategoryId=" + productCategoryId + ", productCategoryName="
				+ productCategoryName + ", companyId=" + companyId + "]";
	}
	
	
}

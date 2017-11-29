package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

@NameStyle(Style.normal)
@Table(name="t_product_company")
public class ProductCompany {
	
	/**产品ID*/
	@Id
	private String productId;
	/**企业ID*/
	@Id
    private String companyId;
	/**企业名称*/
	@Transient
    private String companyName;
	/**客户企业别名*/
	@Transient
    private String customerAlias;
	
	
	/**
	 * 获取 产品ID
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置 产品ID
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取 企业ID
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置 企业ID
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取 企业名称
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置 企业名称
	 * @param companyName 企业名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 获取 客户企业别名
	 * @return customerAlias
	 */
	public String getCustomerAlias() {
		return customerAlias;
	}

	/**
	 * 设置 客户企业别名
	 * @param customerAlias 客户企业别名
	 */
	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}


	@Override
	public String toString() {
		return "ProductCompany [productId=" + productId + ", companyId="
				+ companyId + ", companyName=" + companyName
				+ ", customerAlias=" + customerAlias + "]";
	}

	 

}

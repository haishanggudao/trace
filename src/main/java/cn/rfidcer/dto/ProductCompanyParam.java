package cn.rfidcer.dto;

public class ProductCompanyParam {

	
	/**
	 *企业ID 
	 */
	private String companyId;
	/**
	 * 供应商企业ID
	 */
	private String supplierCompanyId;
	/**
	 * 产品类型
	 */
	private String productType;
	/**获取企业ID
	 * @return the 企业ID
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**设置企业ID
	 * @param companyId the 企业ID to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**获取供应商企业ID
	 * @return the 供应商企业ID
	 */
	public String getSupplierCompanyId() {
		return supplierCompanyId;
	}
	/**设置供应商企业ID
	 * @param supplierCompanyId the 供应商企业ID to set
	 */
	public void setSupplierCompanyId(String supplierCompanyId) {
		this.supplierCompanyId = supplierCompanyId;
	}
	/**获取产品类型
	 * @return the 产品类型
	 */
	public String getProductType() {
		return productType;
	}
	/**设置产品类型
	 * @param productType the 产品类型 to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
}

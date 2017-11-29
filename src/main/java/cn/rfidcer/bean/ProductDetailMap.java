package cn.rfidcer.bean;

/**   
  * @Title: 产品属性映射信息
  * @author jie.jia
  * @date 2016年6月27日 下午7:49:58 
  * @Copyright Copyright
  * @version V1.0   
*/
public class ProductDetailMap extends BaseEntity {
	/**产品明细ID*/
	private String productDetailMapId;
	/**产品分类ID*/
	private String productCategoryId;
	/**企业ID*/
	private String companyId;
	/**产品附加属性表名*/
	private String productDetailTable;
	/**状态*/
	private String status;
	/**#产品分类*/
	private ProductCategory category;
	/**#企业*/
	private Company company;
	/**
	 * 获取 产品明细ID
	 * @return productDetailMapId
	 */
	public String getProductDetailMapId() {
		return productDetailMapId;
	}
	/**
	 * 设置 产品明细ID
	 * @param productDetailMapId 产品明细ID
	 */
	public void setProductDetailMapId(String productDetailMapId) {
		this.productDetailMapId = productDetailMapId;
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
	 * 获取 产品附加属性表名
	 * @return productDetailTable
	 */
	public String getProductDetailTable() {
		return productDetailTable;
	}
	/**
	 * 设置 产品附加属性表名
	 * @param productDetailTable 产品附加属性表名
	 */
	public void setProductDetailTable(String productDetailTable) {
		this.productDetailTable = productDetailTable;
	}
	/**
	 * 获取 状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 状态
	 * @param status 状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取 #产品分类
	 * @return category
	 */
	public ProductCategory getCategory() {
		return category;
	}
	/**
	 * 设置 #产品分类
	 * @param category #产品分类
	 */
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	/**
	 * 获取 #企业
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * 设置 #企业
	 * @param company #企业
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "ProductDetailMap [productDetailMapId=" + productDetailMapId
				+ ", productCategoryId=" + productCategoryId + ", companyId="
				+ companyId + ", productDetailTable=" + productDetailTable
				+ ", status=" + status + ", category=" + category + ", company="
				+ company + "]";
	}


}
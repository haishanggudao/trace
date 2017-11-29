package cn.rfidcer.bean;

import java.math.BigDecimal;

/**   
  * @Title: POJO 产品库存
  * @author xzm & jie.jia
  * @date 2016年6月27日 下午8:02:01 
  * @Copyright Copyright
  * @version V1.0   
*/
public class ProductStock extends BaseEntity{
	/**产品库存ID*/
	private String productStockId;
	/**产品ID*/
	private String productId;
	/**产品规格明细ID*/
	private String productStandardDetailId;
	/**库位号ID*/
	private String storageLocationId;
	/**库存数量*/
	private BigDecimal stockNum;
	/**所属公司ID*/
	private String companyId;
	/**预警数量*/
	private String warningNum;
	/**是否删除：0-正常 1-已删除*/
	private String isDeleted;
	/**#所属企业*/
	private Company company;
	/**产品名称-用于查询*/
	private String productName;
	/**#产品信息*/
	private Product product;
	/**#产品规格明细*/
	private ProductStandardDetail productStandardDetail;
	//无参构造器
	public ProductStock(){}
	//有参构造器
	public ProductStock(String productId, String productStandardDetailId, String companyId) {
		this.productId = productId;
		this.productStandardDetailId = productStandardDetailId;
		this.companyId = companyId;
	}
	/**
	 * 获取 产品库存ID
	 * @return productStockId
	 */
	public String getProductStockId() {
		return productStockId;
	}
	/**
	 * 设置 产品库存ID
	 * @param productStockId 产品库存ID
	 */
	public void setProductStockId(String productStockId) {
		this.productStockId = productStockId;
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
	 * 获取 库位号ID
	 * @return storageLocationId
	 */
	public String getStorageLocationId() {
		return storageLocationId;
	}
	/**
	 * 设置 库位号ID
	 * @param storageLocationId 库位号ID
	 */
	public void setStorageLocationId(String storageLocationId) {
		this.storageLocationId = storageLocationId;
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
	 * 获取 所属公司ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 所属公司ID
	 * @param companyId 所属公司ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 预警数量
	 * @return warningNum
	 */
	public String getWarningNum() {
		return warningNum;
	}
	/**
	 * 设置 预警数量
	 * @param warningNum 预警数量
	 */
	public void setWarningNum(String warningNum) {
		this.warningNum = warningNum;
	}
	/**
	 * 获取 是否删除：0-正常 1-已删除
	 * @return isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置 是否删除：0-正常 1-已删除
	 * @param isDeleted 是否删除：0-正常 1-已删除
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取 #所属企业
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * 设置 #所属企业
	 * @param company #所属企业
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * 获取 产品名称-用于查询
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 产品名称-用于查询
	 * @param productName 产品名称-用于查询
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * @return productStandardDetail
	 */
	public ProductStandardDetail getProductStandardDetail() {
		return productStandardDetail;
	}
	/**
	 * 设置 #产品规格明细
	 * @param productStandardDetail #产品规格明细
	 */
	public void setProductStandardDetail(
			ProductStandardDetail productStandardDetail) {
		this.productStandardDetail = productStandardDetail;
	}
	
	@Override
	public String toString() {
		return "ProductStock [productStockId=" + productStockId + ", productId="
				+ productId + ", productStandardDetailId="
				+ productStandardDetailId + ", storageLocationId="
				+ storageLocationId + ", stockNum=" + stockNum + ", companyId="
				+ companyId + ", warningNum=" + warningNum + ", isDeleted="
				+ isDeleted + ", company=" + company + ", productName="
				+ productName + ", product=" + product
				+ ", productStandardDetail=" + productStandardDetail + "]";
	}
	
}

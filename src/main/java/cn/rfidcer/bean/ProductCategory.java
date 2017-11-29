package cn.rfidcer.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**   
  * @Title: POJO 产品分类 
  * @author xzm & jie.jia
  * @date 2016年6月27日 下午7:47:40 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value=Include.NON_NULL)
public class ProductCategory extends BaseEntity {

	/**分类ID*/
	private String productCategoryId;
	/**名称 */
	private String productCategoryName; 
	/**简介*/
	private String productCategoryDesc; 
	/**等级 */
	private String level; 
	/**父分类ID*/
	private String parentCategoryId; 
	/**状态 */
	private String status; 
	/**用户企业ID*/
	private String companyId; 
	
	/**
	 * 分类编码
	 */
	private String productCategoryCode="";
	/**#子类别 */
	private List<ProductCategory> children;
	/**
	 * 获取 分类ID
	 * @return productCategoryId
	 */
	public String getProductCategoryId() {
		return productCategoryId;
	}
	/**
	 * 设置 分类ID
	 * @param productCategoryId 分类ID
	 */
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	
	/**获取分类编码
	 * @return the 分类编码
	 */
	public String getProductCategoryCode() {
		return productCategoryCode;
	}
	/**设置分类编码
	 * @param productCategoryCode the 分类编码 to set
	 */
	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}
	/**
	 * 获取 名称
	 * @return productCategoryName
	 */
	public String getProductCategoryName() {
		return productCategoryName;
	}
	/**
	 * 设置 名称
	 * @param productCategoryName 名称
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	/**
	 * 获取 简介
	 * @return productCategoryDesc
	 */
	public String getProductCategoryDesc() {
		return productCategoryDesc;
	}
	/**
	 * 设置 简介
	 * @param productCategoryDesc 简介
	 */
	public void setProductCategoryDesc(String productCategoryDesc) {
		this.productCategoryDesc = productCategoryDesc;
	}
	/**
	 * 获取 等级
	 * @return level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置 等级
	 * @param level 等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取 父分类ID
	 * @return parentCategoryId
	 */
	public String getParentCategoryId() {
		return parentCategoryId;
	}
	/**
	 * 设置 父分类ID
	 * @param parentCategoryId 父分类ID
	 */
	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
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
	 * 获取 用户企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 用户企业ID
	 * @param companyId 用户企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 #子类别
	 * @return children
	 */
	public List<ProductCategory> getChildren() {
		return children;
	}
	/**
	 * 设置 #子类别
	 * @param children #子类别
	 */
	public void setChildren(List<ProductCategory> children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		return "ProductCategory [productCategoryId=" + productCategoryId
				+ ", productCategoryName=" + productCategoryName
				+ ", productCategoryDesc=" + productCategoryDesc + ", level="
				+ level + ", parentCategoryId=" + parentCategoryId + ", status="
				+ status + ", companyId=" + companyId + ", children=" + children
				+ "]";
	}
	
}

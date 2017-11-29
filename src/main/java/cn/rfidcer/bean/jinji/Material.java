package cn.rfidcer.bean.jinji;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.BaseEntity;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
* @Title: Material.java 
* @Package cn.rfidcer.bean.jinji 
* @Description: POJO 产品表
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月26日 下午6:34:49 
* @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_product")
public class Material extends BaseEntity{
	/**产品ID*/
	@Id
	private String productId;
	/**产品名称*/
	private String productName;
	/**产品编码*/
	private String productCode;
	/**产品简称*/
	private String productShortName;
	/**产品简介*/
	private String productDesc;
	/**产品类型*/
	private String productType;
	/**产品分类ID*/
	private String productCategoryId;
	/**产品图片*/
	private String productImageUrl;
	/**产品状态*/
	private String status;
	/**企业ID*/
	private String companyId;
	/**产品附加属性映射ID*/
	private String productDetailMapId;
	/**超市商品编码*/
	private String marketCode;
	/**删除标志*/
	private int isDeleted;
	/**宣传图*/
	private String publicityImageUrl;
	/**宣传图链接*/
	private String publicityImageLink;
	/**#产地*/
	private String madein;  
	/**#产品图片文件*/
	@Transient
	private MultipartFile productImgfile;
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
	 * 获取 产品名称
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 产品名称
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取 产品编码
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置 产品编码
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取 产品简称
	 * @return the productShortName
	 */
	public String getProductShortName() {
		return productShortName;
	}
	/**
	 * 设置 产品简称
	 * @param productShortName the productShortName to set
	 */
	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}
	/**
	 * 获取 产品简介
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}
	/**
	 * 设置 产品简介
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	/**
	 * 获取 产品类型
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 设置 产品类型
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取 产品分类ID
	 * @return the productCategoryId
	 */
	public String getProductCategoryId() {
		return productCategoryId;
	}
	/**
	 * 设置 产品分类ID
	 * @param productCategoryId the productCategoryId to set
	 */
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	/**
	 * 获取 产品图片
	 * @return the productImageUrl
	 */
	public String getProductImageUrl() {
		return productImageUrl;
	}
	/**
	 * 设置 产品图片
	 * @param productImageUrl the productImageUrl to set
	 */
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	/**
	 * 获取 产品状态
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 产品状态
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 获取 产品附加属性映射ID
	 * @return the productDetailMapId
	 */
	public String getProductDetailMapId() {
		return productDetailMapId;
	}
	/**
	 * 设置 产品附加属性映射ID
	 * @param productDetailMapId the productDetailMapId to set
	 */
	public void setProductDetailMapId(String productDetailMapId) {
		this.productDetailMapId = productDetailMapId;
	}
	/**
	 * 获取 超市商品编码
	 * @return the marketCode
	 */
	public String getMarketCode() {
		return marketCode;
	}
	/**
	 * 设置 超市商品编码
	 * @param marketCode the marketCode to set
	 */
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	/**
	 * 获取 删除标志
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置 删除标志
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取 宣传图
	 * @return the publicityImageUrl
	 */
	public String getPublicityImageUrl() {
		return publicityImageUrl;
	}
	/**
	 * 设置 宣传图
	 * @param publicityImageUrl the publicityImageUrl to set
	 */
	public void setPublicityImageUrl(String publicityImageUrl) {
		this.publicityImageUrl = publicityImageUrl;
	}
	/**
	 * 获取 宣传图链接
	 * @return the publicityImageLink
	 */
	public String getPublicityImageLink() {
		return publicityImageLink;
	}
	/**
	 * 设置 宣传图链接
	 * @param publicityImageLink the publicityImageLink to set
	 */
	public void setPublicityImageLink(String publicityImageLink) {
		this.publicityImageLink = publicityImageLink;
	}
	/**
	 * 获取 #产地
	 * @return the madein
	 */
	public String getMadein() {
		return madein;
	}
	/**
	 * 设置 #产地
	 * @param madein the madein to set
	 */
	public void setMadein(String madein) {
		this.madein = madein;
	} 
	
	/**
	 * 获取 #产品图片文件
	 * @return the productImgfile
	 */
	public MultipartFile getProductImgfile() {
		return productImgfile;
	}
	/**
	 * 设置 #产品图片文件
	 * @param productImgfile the productImgfile to set
	 */
	public void setProductImgfile(MultipartFile productImgfile) {
		this.productImgfile = productImgfile;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Material [productId=" + productId + ", productName=" + productName + ", productCode=" + productCode
				+ ", productShortName=" + productShortName + ", productDesc=" + productDesc + ", productType="
				+ productType + ", productCategoryId=" + productCategoryId + ", productImageUrl=" + productImageUrl
				+ ", status=" + status + ", companyId=" + companyId + ", productDetailMapId=" + productDetailMapId
				+ ", marketCode=" + marketCode + ", isDeleted=" + isDeleted + ", publicityImageUrl=" + publicityImageUrl
				+ ", publicityImageLink=" + publicityImageLink + ", madein=" + madein + "]";
	}
	
	
	
	 
}

package cn.rfidcer.bean;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
  * @Title: 产品表
  * @author jgx
  * @date 2016年6月27日 下午7:40:13 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class Product extends BaseEntity {
	/**产品ID*/
	private String productId;
	/**产品名称*/
	private String productName;
	/**产品编码*/
	private String productCode;
	/**产品简称*/
	private String productShortName;
	/**产品类型*/
	private String productType;
	/**产品分类ID*/
	private String productCategoryId;
	/**#产品规格ID*/
	private String productStandardId;
	/**宣传图链接*/
	private String publicityImageLink;
	/**宣传图*/
	private String publicityImageUrl;
	/**#产品规格名称*/
	private String productStandardName;
	/**产品图片*/
	private String productImageUrl;
	/**#产品图片文件 可能无用*/
	private MultipartFile file;
	/**#产品图片文件*/
	private MultipartFile productImgfile;
	/**#产品宣传图片文件*/
	private MultipartFile publicityImagefile;
	/**产品状态*/
	private String status;
	/**企业ID*/
	private String companyId;
	/**产品附加属性映射ID*/
	private String productDetailMapId;
	/**产品简介*/
	private String productDesc;
	/**超市商品编码*/
	private String marketCode;
	/**#资源分组*/
	private String varGroup;
	/**#资源键*/
	private String varValue;
	/**#资源值*/
	private String varName;
	/**#产地*/
	private String madein;
	/**#产地显示*/
	private String madeinStr;	
    /**生产日期*/
	private String productDate;
	/**#商品价格*/
	private BigDecimal salePrice;
	/**#保质期*/
	private int shelfLife;
	/**#是否可选规格*/
	private String isStandarddetail;
	/**#产品分类*/
	private ProductCategory category;
	/**#产品附加属性*/
	private ProductDetailMap detailMap;
	/**#产品规格明细ID*/
	private String productStandardDetailId;
	 /**辅助显示列，用来显示产品名称跟市场商品码*/
	private String productNameMarketCode;
	
	
	/**
	 * 设置辅助显示列，用来显示产品名称跟市场商品码
	 * @return
	 */
	public String getProductNameMarketCode() {
		if(marketCode!=null){
			return productName+"-"+marketCode;
		}else{
			return productName;
		}
		
	}
	/**
	 * 获取辅助显示列，用来显示产品名称跟市场商品码
	 * @param productNameMarketCode
	 */
	public void setProductNameMarketCode(String productNameMarketCode) {
		this.productNameMarketCode =productNameMarketCode;
	}
	/**
	 * 获取 产品规格明细ID
	 * @return productId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 产品规格明细ID
	 * @param productId 产品规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
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
	 * 获取 产地显示
	 * @return madeinStr
	 */
	
	public String getMadeinStr() {
		return madeinStr;
	}
	/**
	 * 设置 产地显示
	 * @param 产地显示
	 */	
	public void setMadeinStr(String madeinStr) {
		this.madeinStr = madeinStr;
	}
	/**
	 * 获取 商品价格
	 * @return salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置 商品价格
	 * @param salePrice 商品价格
	 */	
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取 产品名称
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 产品名称
	 * @param productName 产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取 产品编码
	 * @return productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置 产品编码
	 * @param productCode 产品编码
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取 产品简称
	 * @return productShortName
	 */
	public String getProductShortName() {
		return productShortName;
	}
	/**
	 * 设置 产品简称
	 * @param productShortName 产品简称
	 */
	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}
	/**
	 * 获取 产品类型
	 * @return productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 设置 产品类型
	 * @param productType 产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
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
	 * 获取 #产品规格ID
	 * @return productStandardId
	 */
	public String getProductStandardId() {
		return productStandardId;
	}
	/**
	 * 设置 #产品规格ID
	 * @param productStandardId #产品规格ID
	 */
	public void setProductStandardId(String productStandardId) {
		this.productStandardId = productStandardId;
	}
	/**
	 * 获取 宣传图链接
	 * @return publicityImageLink
	 */
	public String getPublicityImageLink() {
		return publicityImageLink;
	}
	/**
	 * 设置 宣传图链接
	 * @param publicityImageLink 宣传图链接
	 */
	public void setPublicityImageLink(String publicityImageLink) {
		this.publicityImageLink = publicityImageLink;
	}
	/**
	 * 获取 宣传图
	 * @return publicityImageUrl
	 */
	public String getPublicityImageUrl() {
		return publicityImageUrl;
	}
	/**
	 * 设置 宣传图
	 * @param publicityImageUrl 宣传图
	 */
	public void setPublicityImageUrl(String publicityImageUrl) {
		this.publicityImageUrl = publicityImageUrl;
	}
	/**
	 * 获取 #产品规格名称
	 * @return productStandardName
	 */
	public String getProductStandardName() {
		return productStandardName;
	}
	/**
	 * 设置 #产品规格名称
	 * @param productStandardName #产品规格名称
	 */
	public void setProductStandardName(String productStandardName) {
		this.productStandardName = productStandardName;
	}
	/**
	 * 获取 产品图片
	 * @return productImageUrl
	 */
	public String getProductImageUrl() {
		return productImageUrl;
	}
	/**
	 * 设置 产品图片
	 * @param productImageUrl 产品图片
	 */
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	/**
	 * 获取 #产品图片文件 可能无用
	 * @return file
	 */
	public MultipartFile getFile() {
		return file;
	}
	/**
	 * 设置 #产品图片文件 可能无用
	 * @param file #产品图片文件 可能无用
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	/**
	 * 获取 #产品图片文件
	 * @return productImgfile
	 */
	public MultipartFile getProductImgfile() {
		return productImgfile;
	}
	/**
	 * 设置 #产品图片文件
	 * @param productImgfile #产品图片文件
	 */
	public void setProductImgfile(MultipartFile productImgfile) {
		this.productImgfile = productImgfile;
	}
	/**
	 * 获取 #产品宣传图片文件
	 * @return publicityImagefile
	 */
	public MultipartFile getPublicityImagefile() {
		return publicityImagefile;
	}
	/**
	 * 设置 #产品宣传图片文件
	 * @param publicityImagefile #产品宣传图片文件
	 */
	public void setPublicityImagefile(MultipartFile publicityImagefile) {
		this.publicityImagefile = publicityImagefile;
	}
	/**
	 * 获取 产品状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 产品状态
	 * @param status 产品状态
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 获取 产品附加属性映射ID
	 * @return productDetailMapId
	 */
	public String getProductDetailMapId() {
		return productDetailMapId;
	}
	/**
	 * 设置 产品附加属性映射ID
	 * @param productDetailMapId 产品附加属性映射ID
	 */
	public void setProductDetailMapId(String productDetailMapId) {
		this.productDetailMapId = productDetailMapId;
	}
	/**
	 * 获取 产品简介
	 * @return productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}
	/**
	 * 设置 产品简介
	 * @param productDesc 产品简介
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	/**
	 * 获取 超市商品编码
	 * @return marketCode
	 */
	public String getMarketCode() {
		return marketCode;
	}
	/**
	 * 设置 超市商品编码
	 * @param marketCode 超市商品编码
	 */
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	/**
	 * 获取 #资源分组
	 * @return varGroup
	 */
	public String getVarGroup() {
		return varGroup;
	}
	/**
	 * 设置 #资源分组
	 * @param varGroup #资源分组
	 */
	public void setVarGroup(String varGroup) {
		this.varGroup = varGroup;
	}
	/**
	 * 获取 #资源键
	 * @return varValue
	 */
	public String getVarValue() {
		return varValue;
	}
	/**
	 * 设置 #资源键
	 * @param varValue #资源键
	 */
	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}
	/**
	 * 获取 #资源值
	 * @return varName
	 */
	public String getVarName() {
		return varName;
	}
	/**
	 * 设置 #资源值
	 * @param varName #资源值
	 */
	public void setVarName(String varName) {
		this.varName = varName;
	}
	/**
	 * 获取 #是否可选规格
	 * @return isStandarddetail
	 */
	public String getIsStandarddetail() {
		return isStandarddetail;
	}
	/**
	 * 设置 #是否可选规格
	 * @param isStandarddetail #是否可选规格
	 */
	public void setIsStandarddetail(String isStandarddetail) {
		this.isStandarddetail = isStandarddetail;
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
	 * 获取 #产品附加属性
	 * @return detailMap
	 */
	public ProductDetailMap getDetailMap() {
		return detailMap;
	}
	/**
	 * 设置 #产品附加属性
	 * @param detailMap #产品附加属性
	 */
	public void setDetailMap(ProductDetailMap detailMap) {
		this.detailMap = detailMap;
	}
	
	public String getMadein() {
		return madein;
	}
	
	public void setMadein(String madein) {
		this.madein = madein;
	}
	
	
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	/**
	 * 获取 #产品保质期
	 * @return productDate #保质期
	 */
	public int getShelfLife() {
		return shelfLife;
	}
	/**
	 * 设置 #产品保质期
	 * @param productDate #保质期
	 */
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", productCode=" + productCode
				+ ", productShortName=" + productShortName + ", productType="
				+ productType + ", productCategoryId=" + productCategoryId
				+ ", productStandardId=" + productStandardId
				+ ", publicityImageLink=" + publicityImageLink
				+ ", publicityImageUrl=" + publicityImageUrl
				+ ", productStandardName=" + productStandardName
				+ ", productImageUrl=" + productImageUrl + ", file=" + file
				+ ", productImgfile=" + productImgfile + ", publicityImagefile="
				+ publicityImagefile + ", status=" + status + ", companyId="
				+ companyId + ", productDetailMapId=" + productDetailMapId
				+ ", productDesc=" + productDesc + ", marketCode=" + marketCode
				+ ", varGroup=" + varGroup + ", varValue=" + varValue
				+ ", varName=" + varName + ", isStandarddetail="
				+ isStandarddetail + ", category=" + category + ", detailMap="
				+ detailMap + ",productDate="+productDate+",shelfLife="+shelfLife+"]";
	}
	

}
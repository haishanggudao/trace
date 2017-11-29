package cn.rfidcer.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.rfidcer.enums.ProcessItemType;
import cn.rfidcer.enums.ProductType;
import cn.rfidcer.util.StringUtil;

/**   
  * @Title: 加工模板明细
  * @author xzm
  * @date 2016年6月27日 下午7:13:33 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class ProcessTemplateItem extends BaseEntity {
	/**加工模版明细ID*/
	private String templateItemId;
	/**加工模版ID*/
	private String processTemplateId;
	/**产品ID*/
	private String productId;
	/**#产品名称*/
	private String productName;
	/**产品类型*/
	private String productType;
	/**#商品ID*/
	private String goodsId;
	/**#商品批次*/
	private String goodsBatch;
	/**#产品信息*/
	private Product product;
	/**#产品规格明细ID*/
	private String productStandardDetailId;
	/**#产品规格明细*/
	private ProductStandardDetail standardDetail;
	/**产品数量*/
	private BigDecimal num;
	/**产品类型：0-主料，1-辅料*/
	private String type;
	/**#产品规格名称*/
	private String fullStandardName;
	/**产品类型名称*/
	private String productTypeName;
	/**
	 * 获取 加工模版明细ID
	 * @return templateItemId
	 */
	public String getTemplateItemId() {
		return templateItemId;
	}
	/**
	 * 设置 加工模版明细ID
	 * @param templateItemId 加工模版明细ID
	 */
	public void setTemplateItemId(String templateItemId) {
		this.templateItemId = templateItemId;
	}
	/**
	 * 获取 加工模版ID
	 * @return processTemplateId
	 */
	public String getProcessTemplateId() {
		return processTemplateId;
	}
	/**
	 * 设置 加工模版ID
	 * @param processTemplateId 加工模版ID
	 */
	public void setProcessTemplateId(String processTemplateId) {
		this.processTemplateId = processTemplateId;
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
	 * 获取 #商品ID
	 * @return goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置 #商品ID
	 * @param goodsId #商品ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取 #商品批次
	 * @return goodsBatch
	 */
	public String getGoodsBatch() {
		return goodsBatch;
	}
	/**
	 * 设置 #商品批次
	 * @param goodsBatch #商品批次
	 */
	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
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
	 * 获取 #产品规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 #产品规格明细ID
	 * @param productStandardDetailId #产品规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	/**
	 * 获取 #产品规格明细
	 * @return standardDetail
	 */
	public ProductStandardDetail getStandardDetail() {
		return standardDetail;
	}
	/**
	 * 设置 #产品规格明细
	 * @param standardDetail #产品规格明细
	 */
	public void setStandardDetail(ProductStandardDetail standardDetail) {
		this.standardDetail = standardDetail;
	}
	/**
	 * 获取 产品数量
	 * @return num
	 */
	public BigDecimal getNum() {
		return num;
	}
	/**
	 * 设置 产品数量
	 * @param num 产品数量
	 */
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	/**
	 * 获取 产品类型：0-主料，1-辅料
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置 产品类型：0-主料，1-辅料
	 * @param type 产品类型：0-主料，1-辅料
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取 #产品规格名称
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		if (standardDetail != null) {
			return standardDetail.getFullStandardName();
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
	 * 获取 产品类型名称
	 * @return productTypeName
	 */
	public String getProductTypeName() {
		String typeName = "";
		if(!StringUtil.isBlank(this.getProductType()))
		{
			typeName = ProductType.Enums.getName(Integer.parseInt(this.getProductType()));
		}
		this.setProductTypeName(typeName);
		return productTypeName;
	}
	/**
	 * 设置 产品类型名称
	 * @param productTypeName 产品类型名称
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	/**产品类型名称*/
	public String getTypeName() {
		String strName = "";
		if(!StringUtil.isBlank(this.getType()))
		{
			strName = ProcessItemType.Enums.getName(Integer.parseInt(this.getType()));
		}
		return strName;
	}
	
	@Override
	public String toString() {
		return "ProcessTemplateItem [templateItemId=" + templateItemId
				+ ", processTemplateId=" + processTemplateId + ", productId="
				+ productId + ", productName=" + productName + ", productType="
				+ productType + ", goodsId=" + goodsId + ", goodsBatch="
				+ goodsBatch + ", product=" + product
				+ ", productStandardDetailId=" + productStandardDetailId
				+ ", standardDetail=" + standardDetail + ", num=" + num
				+ ", type=" + type + ", fullStandardName=" + fullStandardName
				+ ", productTypeName=" + productTypeName + "]";
	}
	
}

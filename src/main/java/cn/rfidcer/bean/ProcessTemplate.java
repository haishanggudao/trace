package cn.rfidcer.bean;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;
import cn.rfidcer.enums.ProductType;
import cn.rfidcer.util.StringUtil;

/**   
  * @Title: 加工模板
  * @author xzm
  * @date 2016年6月27日 下午7:05:55 
  * @Copyright Copyright
  * @version V1.0   
*/
@Table(name="t_process_template")
@NameStyle(Style.normal)
public class ProcessTemplate extends BaseEntity {
	/**加工模版ID*/
	@Id
	private String processTemplateId;
	/**产品ID*/
	private String productId;
	/**产品ID*/
	private String standardDetailId;
	/**加工者Id*/
	private String processorId;
	/**#产品信息*/
	@Transient
	private Product product;
	/**#产品规格明细*/
	@Transient
	private ProductStandardDetail standardDetail;
	/**#加工模版明细*/
	@Transient
	private List<ProcessTemplateItem> templateItems;
	
	/**
	 * 加工者
	 */
	@Transient
	private Processor processor;
	/**#规格名称*/
	@Transient
	private String fullStandardName;
	/**模版类型0:加工模板；type 1:拆分模板*/
	private String type;
	/**模版名称*/
	private String templateName;
	/**企业ID*/
	private String companyId;
	/**#加工ID*/
	@Transient
	private String processMainId;
	/**加工产品类型*/
	private String productType;
	/**#加工类型名称*/
	@Transient
	private String productTypeName;
	
	/**获取加工者
	 * @return the 加工者
	 */
	public Processor getProcessor() {
		return processor;
	}
	
	
	public String getProcessorId() {
		return processorId;
	}


	public void setProcessorId(String processorId) {
		this.processorId = processorId;
	}


	/**设置加工者
	 * @param processor the 加工者 to set
	 */
	public void setProcessor(Processor processor) {
		this.processor = processor;
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
	 * 获取 产品ID
	 * @return standardDetailId
	 */
	public String getStandardDetailId() {
		return standardDetailId;
	}
	/**
	 * 设置 产品ID
	 * @param standardDetailId 产品ID
	 */
	public void setStandardDetailId(String standardDetailId) {
		this.standardDetailId = standardDetailId;
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
	 * 获取 #加工模版明细
	 * @return templateItems
	 */
	public List<ProcessTemplateItem> getTemplateItems() {
		return templateItems;
	}
	/**
	 * 设置 #加工模版明细
	 * @param templateItems #加工模版明细
	 */
	public void setTemplateItems(List<ProcessTemplateItem> templateItems) {
		this.templateItems = templateItems;
	}
	/**
	 * 获取 #规格名称
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		if (!StringUtil.isBlank(standardDetail)) {
			return standardDetail.getFullStandardName();
		} else {
			return fullStandardName;
		}
	}
	/**
	 * 设置 #规格名称
	 * @param fullStandardName #规格名称
	 */
	public void setFullStandardName(String fullStandardName) {
		this.fullStandardName = fullStandardName;
	}
	/**
	 * 获取 模版类型0:加工模板；type 1:拆分模板
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置 模版类型0:加工模板；type 1:拆分模板
	 * @param type 模版类型0:加工模板；type 1:拆分模板
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取 模版名称
	 * @return templateName
	 */
	public String getTemplateName() {
		return templateName;
	}
	/**
	 * 设置 模版名称
	 * @param templateName 模版名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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
	 * 获取 #加工ID
	 * @return processMainId
	 */
	public String getProcessMainId() {
		return processMainId;
	}
	/**
	 * 设置 #加工ID
	 * @param processMainId #加工ID
	 */
	public void setProcessMainId(String processMainId) {
		this.processMainId = processMainId;
	}
	/**
	 * 获取 加工产品类型
	 * @return productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 设置 加工产品类型
	 * @param productType 加工产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取 #加工类型名称
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
	 * 设置 #加工类型名称
	 * @param productTypeName #加工类型名称
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
}

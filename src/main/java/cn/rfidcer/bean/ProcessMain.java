package cn.rfidcer.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.rfidcer.util.DateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**   
  * @Title: 加工主表-成品
  * @author jie.jia
  * @date 2016年6月27日 下午7:01:31 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class ProcessMain extends BaseEntity {
	/**加工主表*/
	private String processMainId;
	/**商品ID*/
	private String goodsId;
	/**#产品类型*/
	private String productType;
	/**加工时间*/
	private Date processTime;
	/**#加工时间字符串*/
	private String processTimeStr;
	/**加工批次*/
	private String processBatch;
	/**加工真实批次*/
	private String processRealBatch;
	/**加工数量*/
	private BigDecimal processQuantity;
	/**商品信息*/
	private Goods goods;
	/**#加工明细*/
	private List<ProcessItem> processItems;
	/**企业ID*/
	private String companyId;
	/**加工类型*/
	private String type;
	
	/**
	 * 加工者
	 */
	private Processor processor;
	
	
	/**获取加工者
	 * @return the 加工者
	 */
	public Processor getProcessor() {
		return processor;
	}
	/**设置加工者
	 * @param processor the 加工者 to set
	 */
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}
	/**
	 * 获取 加工主表
	 * @return processMainId
	 */
	public String getProcessMainId() {
		return processMainId;
	}
	/**
	 * 设置 加工主表
	 * @param processMainId 加工主表
	 */
	public void setProcessMainId(String processMainId) {
		this.processMainId = processMainId;
	}
	/**
	 * 获取 商品ID
	 * @return goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置 商品ID
	 * @param goodsId 商品ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取 #产品类型
	 * @return productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 设置 #产品类型
	 * @param productType #产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取 加工时间
	 * @return processTime
	 */
	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getProcessTime() {
		return processTime;
	}
	/**
	 * 设置 加工时间
	 * @param processTime 加工时间
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	/**
	 * 获取 #加工时间字符串
	 * @return processTimeStr
	 */
	public String getProcessTimeStr() {
		return processTimeStr;
	}
	/**
	 * 设置 #加工时间字符串
	 * @param processTimeStr #加工时间字符串
	 */
	public void setProcessTimeStr(String processTimeStr) {
		this.processTimeStr = processTimeStr;
	}
	/**
	 * 获取 加工批次
	 * @return processBatch
	 */
	public String getProcessBatch() {
		return processBatch;
	}
	/**
	 * 设置 加工批次
	 * @param processBatch 加工批次
	 */
	public void setProcessBatch(String processBatch) {
		this.processBatch = processBatch;
	}
	/**
	 * 获取 加工真实批次
	 * @return processRealBatch
	 */
	public String getProcessRealBatch() {
		return processRealBatch;
	}
	/**
	 * 设置 加工真实批次
	 * @param processRealBatch 加工真实批次
	 */
	public void setProcessRealBatch(String processRealBatch) {
		this.processRealBatch = processRealBatch;
	}
	/**
	 * 获取 加工数量
	 * @return processQuantity
	 */
	public BigDecimal getProcessQuantity() {
		return processQuantity;
	}
	/**
	 * 设置 加工数量
	 * @param processQuantity 加工数量
	 */
	public void setProcessQuantity(BigDecimal processQuantity) {
		this.processQuantity = processQuantity;
	}
	/**
	 * 获取 商品信息
	 * @return goods
	 */
	public Goods getGoods() {
		return goods;
	}
	/**
	 * 设置 商品信息
	 * @param goods 商品信息
	 */
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	/**
	 * 获取 #加工明细
	 * @return processItems
	 */
	public List<ProcessItem> getProcessItems() {
		return processItems;
	}
	/**
	 * 设置 #加工明细
	 * @param processItems #加工明细
	 */
	public void setProcessItems(List<ProcessItem> processItems) {
		this.processItems = processItems;
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
	 * 获取 加工类型
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置 加工类型
	 * @param type 加工类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ProcessMain [processMainId=" + processMainId + ", goodsId="
				+ goodsId + ", productType=" + productType + ", processTime="
				+ processTime + ", processTimeStr=" + processTimeStr
				+ ", processBatch=" + processBatch + ", processRealBatch="
				+ processRealBatch + ", processQuantity=" + processQuantity
				+ ", goods=" + goods + ", processItems=" + processItems
				+ ", companyId=" + companyId + ", type=" + type + "]";
	}
	



}
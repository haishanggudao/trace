package cn.rfidcer.bean;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
  * @Title: 出库单
  * @author jgx
  * @date 2016年6月27日 下午6:48:06 
  * @Copyright Copyright
  * @version V1.0   
*/
public class OutstockMain extends BaseEntity {
	/**出库单ID*/
	private String outstockMainId;
	/**销售单ID*/
	private String saleOrderId;
	/**销售单*/
	private SaleOrder saleOrder;
	/**企业ID*/
	private String companyId;
	/**物流企业ID*/
	private String logisticsId;
	/**出库单号*/
	private String outstockNum;
	/**出库批次*/
	private String outstockBatchNum;
	/**收货人*/
	private String consignor;
	/**登记人*/
	private String registrant;
	/**出库日期*/
	private String outstockDate;
	/**登记日期*/
	private String registDate;
	/**出库类型*/
	private String outstockType;
	/**出库状态*/
	private String status;
	/**#产品名称*/
	private String productName;
	/**#物流企业名称*/
	private String logisticsAlias;
	/**追溯码*/
	private String traceCode;
	/**#订单日期*/
	private String orderTime;
	/**出库明细*/
	private List<OutstockItem> outstockItem;
	/**#登记日期开始*/
	private String registDateStart;
	/**#登记日期结束*/
	private String registDateEnd;
	/**#出库日期开始*/
	private String outstockDateStart;
	/**#出库日期结束*/
	private String outstockDateEnd;
	/**
	 * 获取 出库单ID
	 * @return outstockMainId
	 */
	public String getOutstockMainId() {
		return outstockMainId;
	}
	/**
	 * 设置 出库单ID
	 * @param outstockMainId 出库单ID
	 */
	public void setOutstockMainId(String outstockMainId) {
		this.outstockMainId = outstockMainId;
	}
	/**
	 * 获取 销售单ID
	 * @return saleOrderId
	 */
	public String getSaleOrderId() {
		return saleOrderId;
	}
	/**
	 * 设置 销售单ID
	 * @param saleOrderId 销售单ID
	 */
	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}
	/**
	 * 获取 销售单
	 * @return saleOrder
	 */
	public SaleOrder getSaleOrder() {
		return saleOrder;
	}
	/**
	 * 设置 销售单
	 * @param saleOrder 销售单
	 */
	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
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
	 * 获取 物流企业ID
	 * @return logisticsId
	 */
	public String getLogisticsId() {
		return logisticsId;
	}
	/**
	 * 设置 物流企业ID
	 * @param logisticsId 物流企业ID
	 */
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	/**
	 * 获取 出库单号
	 * @return outstockNum
	 */
	public String getOutstockNum() {
		return outstockNum;
	}
	/**
	 * 设置 出库单号
	 * @param outstockNum 出库单号
	 */
	public void setOutstockNum(String outstockNum) {
		this.outstockNum = outstockNum;
	}
	/**
	 * 获取 出库批次
	 * @return outstockBatchNum
	 */
	public String getOutstockBatchNum() {
		return outstockBatchNum;
	}
	/**
	 * 设置 出库批次
	 * @param outstockBatchNum 出库批次
	 */
	public void setOutstockBatchNum(String outstockBatchNum) {
		this.outstockBatchNum = outstockBatchNum;
	}
	/**
	 * 获取 收货人
	 * @return consignor
	 */
	public String getConsignor() {
		return consignor;
	}
	/**
	 * 设置 收货人
	 * @param consignor 收货人
	 */
	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}
	/**
	 * 获取 登记人
	 * @return registrant
	 */
	public String getRegistrant() {
		return registrant;
	}
	/**
	 * 设置 登记人
	 * @param registrant 登记人
	 */
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	/**
	 * 获取 出库日期
	 * @return outstockDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public String getOutstockDate() {
		return outstockDate;
	}
	/**
	 * 设置 出库日期
	 * @param outstockDate 出库日期
	 */
	public void setOutstockDate(String outstockDate) {
		this.outstockDate = outstockDate;
	}
	/**
	 * 获取 登记日期
	 * @return registDate
	 */
	public String getRegistDate() {
		return registDate;
	}
	/**
	 * 设置 登记日期
	 * @param registDate 登记日期
	 */
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	/**
	 * 获取 出库类型
	 * @return outstockType
	 */
	public String getOutstockType() {
		return outstockType;
	}
	/**
	 * 设置 出库类型
	 * @param outstockType 出库类型
	 */
	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}
	/**
	 * 获取 出库状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 出库状态
	 * @param status 出库状态
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 获取 #物流企业名称
	 * @return logisticsAlias
	 */
	public String getLogisticsAlias() {
		return logisticsAlias;
	}
	/**
	 * 设置 #物流企业名称
	 * @param logisticsAlias #物流企业名称
	 */
	public void setLogisticsAlias(String logisticsAlias) {
		this.logisticsAlias = logisticsAlias;
	}
	/**
	 * 获取 追溯码
	 * @return traceCode
	 */
	public String getTraceCode() {
		return traceCode;
	}
	/**
	 * 设置 追溯码
	 * @param traceCode 追溯码
	 */
	public void setTraceCode(String traceCode) {
		this.traceCode = traceCode;
	}
	/**
	 * 获取 #订单日期
	 * @return orderTime
	 */
	public String getOrderTime() {
		return orderTime;
	}
	/**
	 * 设置 #订单日期
	 * @param orderTime #订单日期
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	/**
	 * 获取 出库明细
	 * @return outstockItem
	 */
	public List<OutstockItem> getOutstockItem() {
		return outstockItem;
	}
	/**
	 * 设置 出库明细
	 * @param outstockItem 出库明细
	 */
	public void setOutstockItem(List<OutstockItem> outstockItem) {
		this.outstockItem = outstockItem;
	}
	/**
	 * 获取 #登记日期开始
	 * @return registDateStart
	 */
	public String getRegistDateStart() {
		return registDateStart;
	}
	/**
	 * 设置 #登记日期开始
	 * @param registDateStart #登记日期开始
	 */
	public void setRegistDateStart(String registDateStart) {
		this.registDateStart = registDateStart;
	}
	/**
	 * 获取 #登记日期结束
	 * @return registDateEnd
	 */
	public String getRegistDateEnd() {
		return registDateEnd;
	}
	/**
	 * 设置 #登记日期结束
	 * @param registDateEnd #登记日期结束
	 */
	public void setRegistDateEnd(String registDateEnd) {
		this.registDateEnd = registDateEnd;
	}
	/**
	 * 获取 #出库日期开始
	 * @return outstockDateStart
	 */
	public String getOutstockDateStart() {
		return outstockDateStart;
	}
	/**
	 * 设置 #出库日期开始
	 * @param outstockDateStart #出库日期开始
	 */
	public void setOutstockDateStart(String outstockDateStart) {
		this.outstockDateStart = outstockDateStart;
	}
	/**
	 * 获取 #出库日期结束
	 * @return outstockDateEnd
	 */
	public String getOutstockDateEnd() {
		return outstockDateEnd;
	}
	/**
	 * 设置 #出库日期结束
	 * @param outstockDateEnd #出库日期结束
	 */
	public void setOutstockDateEnd(String outstockDateEnd) {
		this.outstockDateEnd = outstockDateEnd;
	}




}
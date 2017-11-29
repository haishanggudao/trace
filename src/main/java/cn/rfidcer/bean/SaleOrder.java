package cn.rfidcer.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/***
 * 
* @Title: SaleOrder.java 
* @Package cn.rfidcer.bean 
* @Description:销售单信息
* @author jie.jia
* @Copyright Copyright
* @date 2016-01-06 11:39
* @version V1.0
*/
@JsonInclude(value = Include.NON_NULL)
public class SaleOrder extends BaseEntity {
	/**销售单ID*/
	private String saleOrderId;
	/**销售单编号*/
	private String saleOrderNo;
	/**#出库单ID*/
	private String outstockMainId;
	/**客户ID*/
	private String customerId; 
	/**客户名称*/
	private String customerName; 
	/**物流企业ID*/
	private String logisticsId;
	/**#物流企业名称*/
	private String logisticsCompanyName;
	/**用户企业ID*/
	private String companyId;
	/**下单时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderTime; 
	/**#搜索下单时间开始*/
	private String orderTimeStart;
	/**#搜索下单时间结束*/
	private String orderTimeEnd;
	/**追溯码*/
	private String traceCode;
	/**销售单条形码*/
	private String barcode;
	/**#销售明细*/
	private List<SaleItem> saleItems;
	/**#客户所属企业*/
	private Company company;
	/**订单状态*/
	private String status;
	/**#产品名称*/
	private String productName;
	/**#订单状态名称*/
	private String statusName;
	/**#出库日期*/
	private Timestamp outstockDate;
	
	/**
	 * 客户
	 */
	private Customers customers;
	
	/**
	 * 酒类经营许可证号
	 */
	private String cliquorbusinesslicense;
	/**
	 * 企业电话
	 */
	private String companyTel;
	
	
	/**获取酒类经营许可证号
	 * @return the 酒类经营许可证号
	 */
	public String getCliquorbusinesslicense() {
		return cliquorbusinesslicense;
	}
	/**设置酒类经营许可证号
	 * @param cliquorbusinesslicense the 酒类经营许可证号 to set
	 */
	public void setCliquorbusinesslicense(String cliquorbusinesslicense) {
		this.cliquorbusinesslicense = cliquorbusinesslicense;
	}
	/**获取企业电话
	 * @return the 企业电话
	 */
	public String getCompanyTel() {
		return companyTel;
	}
	/**设置企业电话
	 * @param companyTel the 企业电话 to set
	 */
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	/**获取客户
	 * @return the 客户
	 */
	public Customers getCustomers() {
		return customers;
	}
	/**设置客户
	 * @param customers the 客户 to set
	 */
	public void setCustomers(Customers customers) {
		this.customers = customers;
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
	 * 获取 销售单编号
	 * @return saleOrderNo
	 */
	public String getSaleOrderNo() {
		return saleOrderNo;
	}
	/**
	 * 设置 销售单编号
	 * @param saleOrderNo 销售单编号
	 */
	public void setSaleOrderNo(String saleOrderNo) {
		this.saleOrderNo = saleOrderNo;
	}
	/**
	 * 获取 #出库单ID
	 * @return outstockMainId
	 */
	public String getOutstockMainId() {
		return outstockMainId;
	}
	/**
	 * 设置 #出库单ID
	 * @param outstockMainId #出库单ID
	 */
	public void setOutstockMainId(String outstockMainId) {
		this.outstockMainId = outstockMainId;
	}
	/**
	 * 获取 客户ID
	 * @return customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 设置 客户ID
	 * @param customerId 客户ID
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取 客户名称
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置 客户名称
	 * @param customerName 客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	 * 获取 #物流企业名称
	 * @return logisticsCompanyName
	 */
	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}
	/**
	 * 设置 #物流企业名称
	 * @param logisticsCompanyName #物流企业名称
	 */
	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
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
	 * 获取 下单时间
	 * @return orderTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getOrderTime() {
		return orderTime;
	}
	/**
	 * 设置 下单时间
	 * @param orderTime 下单时间
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	/**
	 * 获取 #搜索下单时间开始
	 * @return orderTimeStart
	 */
	public String getOrderTimeStart() {
		return orderTimeStart;
	}
	/**
	 * 设置 #搜索下单时间开始
	 * @param orderTimeStart #搜索下单时间开始
	 */
	public void setOrderTimeStart(String orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}
	/**
	 * 获取 #搜索下单时间结束
	 * @return orderTimeEnd
	 */
	public String getOrderTimeEnd() {
		return orderTimeEnd;
	}
	/**
	 * 设置 #搜索下单时间结束
	 * @param orderTimeEnd #搜索下单时间结束
	 */
	public void setOrderTimeEnd(String orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
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
	 * 获取 #销售明细
	 * @return saleItems
	 */
	public List<SaleItem> getSaleItems() {
		return saleItems;
	}
	/**
	 * 设置 #销售明细
	 * @param saleItems #销售明细
	 */
	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	/**
	 * 获取 #客户所属企业
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * 设置 #客户所属企业
	 * @param company #客户所属企业
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * 获取 订单状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 订单状态
	 * @param status 订单状态
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
	 * 获取 #订单状态名称
	 * @return statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * 设置 #订单状态名称
	 * @param statusName #订单状态名称
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * 获取 #出库日期
	 * @return outstockDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Timestamp getOutstockDate() {
		return outstockDate;
	}
	/**
	 * 设置 #出库日期
	 * @param outstockDate #出库日期
	 */
	public void setOutstockDate(Timestamp outstockDate) {
		this.outstockDate = outstockDate;
	}

	/**
	 * 获取 销售单条形码
	 * @return barcode
	 */
	public String getBarcode() {
		return barcode;
	}
	/**
	 * 设置 销售单条形码
	 * @param barcode 销售单条形码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	@Override
	public String toString() {
		return "SaleOrder [saleOrderId=" + saleOrderId + ", saleOrderNo="
				+ saleOrderNo + ", outstockMainId=" + outstockMainId
				+ ", customerId=" + customerId + ", customerName="
				+ customerName + ", logisticsId=" + logisticsId
				+ ", logisticsCompanyName=" + logisticsCompanyName
				+ ", companyId=" + companyId + ", orderTime=" + orderTime
				+ ", orderTimeStart=" + orderTimeStart + ", orderTimeEnd="
				+ orderTimeEnd + ", traceCode=" + traceCode + ", saleItems="
				+ saleItems + ", company=" + company + ", status=" + status
				+ ", productName=" + productName + ", statusName=" + statusName
				+ ", outstockDate=" + outstockDate + "]";
	}


}
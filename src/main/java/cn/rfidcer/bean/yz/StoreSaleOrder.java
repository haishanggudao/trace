package cn.rfidcer.bean.yz;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.rfidcer.bean.BaseEntity;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;


/**   
 * @Title: StoreSaleOrder.java 
 * @Package cn.rfidcer.bean.yz 
 * @Description: 门店销售单
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月8日 下午2:14:14 
 * @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_storeSale_order")
public class StoreSaleOrder extends BaseEntity {
	/**销售单ID*/
	@Id
	private String storeSaleOrderId;
	/**销售单编号*/
	private String storeSaleOrdeNo;
	/**用户企业ID*/
	private String companyId;
	/**下单时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	private Date orderTime;
	/**折扣价格*/
	private BigDecimal discountPrice;
	/**销售总价格*/
	private BigDecimal totalPrice;
	/**销售原价*/
	private BigDecimal salePrice;
	/**付款方式*/
	private int payType;	
	/**会员卡*/
	private String clubCard;
	
	/**
	 * 退货标志(0表示未退货，1表示全部退货，2表示部分退货)
	 */
	private int isRefund;
	/**用户ID*/
	@Transient
	private String userId;
	/**销售单明细*/
	@Transient
	private List<StoreSaleItem> items;
	/**门店名称*/
	@Transient
	private String customerAlias;
	/**企业编号*/
	@Transient
	private String companyCode;
	/**
	 * 销售门店ID
	 */
	@Transient
	private String customerId;
	/**
	 * 配送中心ID
	 */
	@Transient
	private String entId;
	
	
	/**获取退货标志(0表示未退货，1表示全部退货，2表示部分退货)
	 * @return the 退货标志(0表示未退货，1表示全部退货，2表示部分退货)
	 */
	public int getIsRefund() {
		return isRefund;
	}
	/**设置退货标志(0表示未退货，1表示全部退货，2表示部分退货)
	 * @param isRefund the 退货标志(0表示未退货，1表示全部退货，2表示部分退货) to set
	 */
	public void setIsRefund(int isRefund) {
		this.isRefund = isRefund;
	}
	/**
	 * 获取 销售单ID
	 * @return storeSaleOrderId
	 */
	public String getStoreSaleOrderId() {
		return storeSaleOrderId;
	}
	/**
	 * 设置 销售单ID
	 * @param storeSaleOrderId 销售单ID
	 */
	public void setStoreSaleOrderId(String storeSaleOrderId) {
		this.storeSaleOrderId = storeSaleOrderId;
	}
	/**
	 * 获取 销售单编号
	 * @return storeSaleOrdeNo
	 */
	public String getStoreSaleOrdeNo() {
		return storeSaleOrdeNo;
	}
	/**
	 * 设置 销售单编号
	 * @param storeSaleOrdeNo 销售单编号
	 */
	public void setStoreSaleOrdeNo(String storeSaleOrdeNo) {
		this.storeSaleOrdeNo = storeSaleOrdeNo;
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
	 * 获取 用户ID
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置 用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取 企业编号
	 * @return companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * 设置 企业编号
	 * @param companyCode 企业编号
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * 获取 销售单明细
	 * @return items
	 */
	public List<StoreSaleItem> getItems() {
		return items;
	}
	/**
	 * 设置 销售单明细
	 * @param items 销售单明细
	 */
	public void setItems(List<StoreSaleItem> items) {
		this.items = items;
	}
	/**
	 * 获取 折扣价格
	 * @return discountPrice
	 */
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * 设置 折扣价格
	 * @param discountPrice 折扣价格
	 */
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * 获取 销售总价格
	 * @return totalPrice
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 设置 销售总价格
	 * @param totalPrice 销售总价格
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 获取 会员卡
	 * @return clubCard
	 */
	public String getClubCard() {
		return clubCard;
	}
	/**
	 * 设置 会员卡
	 * @param clubCard 会员卡
	 */
	public void setClubCard(String clubCard) {
		this.clubCard = clubCard;
	}
	
	/**
	 * 获取 付款方式
	 * @return payType
	 */
	public int getPayType() {
		return payType;
	}
	/**
	 * 设置 付款方式
	 * @param payType 付款方式
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	/**
	 * 获取 门店名称
	 * @return customerAlias
	 */
	public String getCustomerAlias() {
		return customerAlias;
	}
	/**
	 * 设置 门店名称
	 * @param customerAlias 门店名称
	 */
	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}
	
	/**
	 * 获取 销售门店ID
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 设置 销售门店ID
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * 获取 配送中心ID
	 * @return the entId
	 */
	public String getEntId() {
		return entId;
	}
	/**
	 * 设置 配送中心ID
	 * @param entId the entId to set
	 */
	public void setEntId(String entId) {
		this.entId = entId;
	}
	
	
	/**获取销售原价
	 * @return the 销售原价
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**设置销售原价
	 * @param salePrice the 销售原价 to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	@Override
	public String toString() {
		return "StoreSaleOrder [storeSaleOrderId=" + storeSaleOrderId
				+ ", storeSaleOrdeNo=" + storeSaleOrdeNo + ", companyId="
				+ companyId + ", orderTime=" + orderTime + ", discountPrice="
				+ discountPrice + ", totalPrice=" + totalPrice + ", payType="
				+ payType + ", clubCard=" + clubCard + ", userId=" + userId
				+ ", items=" + items + ", companyCode=" + companyCode
				+ ", getStoreSaleOrderId()=" + getStoreSaleOrderId()
				+ ", getStoreSaleOrdeNo()=" + getStoreSaleOrdeNo()
				+ ", getCompanyId()=" + getCompanyId() + ", getOrderTime()="
				+ getOrderTime() + ", getUserId()=" + getUserId()
				+ ", getCompanyCode()=" + getCompanyCode() + ", getItems()="
				+ getItems() + ", getDiscountPrice()=" + getDiscountPrice()
				+ ", getTotalPrice()=" + getTotalPrice() + ", getPayType()="
				+ getPayType() + ", getClubCard()=" + getClubCard()
				+ ", getCreateTime()=" + getCreateTime() + ", getCreateBy()="
				+ getCreateBy() + ", getUpdateTime()=" + getUpdateTime()
				+ ", getUpdateBy()=" + getUpdateBy() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}





}

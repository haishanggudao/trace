package cn.rfidcer.bean.yz;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.rfidcer.bean.BaseEntity;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;


/**   
 * @Title: StoreSaleItem.java 
 * @Package cn.rfidcer.bean.yz 
 * @Description: 门店销售明细
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月8日 下午2:26:08 
 * @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_storeSale_item")
public class StoreSaleItem extends BaseEntity {
	/**销售单明细ID*/
	@Id
	private String storeSaleItemId;
	/**销售单ID*/
	private String storeSaleOrderId;
	/**商品条形码*/
	private String goodsBarCode;
	/**商品追溯码*/
	private String goodsTraceCode;	
	/**销售单价*/
	private BigDecimal salePrice;	
	/**销售总价*/
	private BigDecimal totalPrice;	
	/**折扣总价*/
	private BigDecimal discountPrice;	
	
	/**销售单位*/
	private String unitName;		
	/**销售数量*/
	private BigDecimal quantity;
	/**
	 * 产品名称
	 */
	@Transient
	private String productName;
	
	@Transient
	private String goodsId;
	/**
	 * 销售门店Id
	 */
	@Transient
	private String storeID;
	/**
	 * 配送中心Id
	 */
	@Transient
	private String entId;
	
	
	/**
	 * 退货标志(0表示未退货，1表示全部退货，2表示部分退货)
	 */
	private int isRefund;
	
	/**
	 * 退到损耗的总量
	 */
	private BigDecimal refundAttritionQuantity;
	/**
	 * 退到库存的总量
	 */
	private BigDecimal refundStockQuantity;
	
	
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
	/**获取退到损耗的总量
	 * @return the 退到损耗的总量
	 */
	public BigDecimal getRefundAttritionQuantity() {
		return refundAttritionQuantity;
	}
	/**设置退到损耗的总量
	 * @param refundAttritionQuantity the 退到损耗的总量 to set
	 */
	public void setRefundAttritionQuantity(BigDecimal refundAttritionQuantity) {
		this.refundAttritionQuantity = refundAttritionQuantity;
	}
	/**获取退到库存的总量
	 * @return the 退到库存的总量
	 */
	public BigDecimal getRefundStockQuantity() {
		return refundStockQuantity;
	}
	/**设置退到库存的总量
	 * @param refundStockQuantity the 退到库存的总量 to set
	 */
	public void setRefundStockQuantity(BigDecimal refundStockQuantity) {
		this.refundStockQuantity = refundStockQuantity;
	}
	/**获取折扣总价
	 * @return the 折扣总价
	 */
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	/**设置折扣总价
	 * @param discountPrice the 折扣总价 to set
	 */
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * 获取 销售单明细ID
	 * @return storeSaleItemId
	 */
	public String getStoreSaleItemId() {
		return storeSaleItemId;
	}
	/**
	 * 设置 销售单明细ID
	 * @param storeSaleItemId 销售单明细ID
	 */
	public void setStoreSaleItemId(String storeSaleItemId) {
		this.storeSaleItemId = storeSaleItemId;
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
	 * 获取 商品条形码
	 * @return goodsBarCode
	 */
	public String getGoodsBarCode() {
		return goodsBarCode;
	}
	/**
	 * 设置 商品条形码
	 * @param goodsBarCode 商品条形码
	 */
	public void setGoodsBarCode(String goodsBarCode) {
		this.goodsBarCode = goodsBarCode;
	}
	/**
	 * 获取 商品追溯码
	 * @return goodsTraceCode
	 */
	public String getGoodsTraceCode() {
		return goodsTraceCode;
	}
	/**
	 * 设置 商品追溯码
	 * @param goodsTraceCode 商品追溯码
	 */
	public void setGoodsTraceCode(String goodsTraceCode) {
		this.goodsTraceCode = goodsTraceCode;
	}
	
	/**
	 * 获取 销售单价
	 * @return salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置 销售单价
	 * @param salePrice 销售单价
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取 销售总价
	 * @return totalPrice
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 设置 销售总价
	 * @param totalPrice 销售总价
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 获取 销售单位
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * 设置 销售单位
	 * @param unitName 销售单位
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * 获取 销售数量
	 * @return quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	/**
	 * 设置 销售数量
	 * @param quantity 销售数量
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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
	 * 获取 #{bare_field_comment}
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置 #{bare_field_comment}
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	/**
	 * 获取 销售门店Id
	 * @return the storeID
	 */
	public String getStoreID() {
		return storeID;
	}
	/**
	 * 设置 销售门店Id
	 * @param storeID the storeID to set
	 */
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	/**
	 * 获取 配送中心Id
	 * @return the entId
	 */
	public String getEntId() {
		return entId;
	}
	/**
	 * 设置 配送中心Id
	 * @param entId the entId to set
	 */
	public void setEntId(String entId) {
		this.entId = entId;
	}
	@Override
	public String toString() {
		return "StoreSaleItem [storeSaleItemId=" + storeSaleItemId
				+ ", storeSaleOrderId=" + storeSaleOrderId + ", goodsBarCode="
				+ goodsBarCode + ", goodsTraceCode=" + goodsTraceCode
				+ ", salePrice=" + salePrice + ", totalPrice=" + totalPrice
				+ ", unitName=" + unitName + ", quantity=" + quantity + "]";
	}



	

}

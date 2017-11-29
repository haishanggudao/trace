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
import cn.rfidcer.bean.SaleItem;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
 * @Title: StorePurchaseOrder.java 
 * @Package cn.rfidcer.bean.yz 
 * @Description: 安卓门店采购单
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月19日 下午3:55:32 
 * @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_storeSale_order")
public class StorePurchaseOrder extends BaseEntity {
	/**供应商Id*/
	private String supplierCompanyId;
	/**系统采购单Id*/
	private String purchaseOrderId;
	/**门店企业Id*/
	private String storeCompanyId;
	/**门店用户Id*/
	private String userId;
	/**采购单详细*/
	private List<StorePurchaseItem> item;

	/**
	 * 获取 供应商Id
	 * @return supplierCompanyId
	 */
	public String getSupplierCompanyId() {
		return supplierCompanyId;
	}
	/**
	 * 设置 供应商Id
	 * @param supplierCompanyId 供应商Id
	 */
	public void setSupplierCompanyId(String supplierCompanyId) {
		this.supplierCompanyId = supplierCompanyId;
	}
	/**
	 * 获取 系统采购单Id
	 * @return purchaseOrderId
	 */
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	/**
	 * 设置 系统采购单Id
	 * @param purchaseOrderId 系统采购单Id
	 */
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	/**
	 * 获取 门店企业Id
	 * @return storeCompanyId
	 */
	public String getStoreCompanyId() {
		return storeCompanyId;
	}
	/**
	 * 设置 门店企业Id
	 * @param storeCompanyId 门店企业Id
	 */
	public void setStoreCompanyId(String storeCompanyId) {
		this.storeCompanyId = storeCompanyId;
	}
	/**
	 * 获取 采购单详细
	 * @return item
	 */
	public List<StorePurchaseItem> getItem() {
		return item;
	}
	/**
	 * 设置 采购单详细
	 * @param item 采购单详细
	 */
	public void setItem(List<StorePurchaseItem> item) {
		this.item = item;
	}
	/**
	 * 获取 门店用户Id
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置 门店用户Id
	 * @param userId 门店用户Id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "StorePurchaseOrder [supplierCompanyId=" + supplierCompanyId
				+ ", purchaseOrderId=" + purchaseOrderId + ", storeCompanyId="
				+ storeCompanyId + ", userId=" + userId + ", item=" + item
				+ "]";
	}
	
}

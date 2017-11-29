package cn.rfidcer.bean.yz;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.rfidcer.bean.BaseEntity;

/**   
 * @Title: StorePurchaseItem.java 
 * @Package cn.rfidcer.bean.yz 
 * @Description: 门店采购明细单
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月13日 上午11:58:57 
 * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class StorePurchaseItem extends BaseEntity{
	

	/**数量*/
	private BigDecimal quantity;
	/**规格明细ID*/
	private String productStandardDetailId;	
	
	/**
	 * 获取 数量
	 * @return quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	/**
	 * 设置 数量
	 * @param quantity 数量
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 获取 规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 规格明细ID
	 * @param productStandardDetailId 规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	@Override
	public String toString() {
		return "StorePurchaseItem [quantity=" + quantity
				+ ", productStandardDetailId=" + productStandardDetailId + "]";
	}
	

	
}
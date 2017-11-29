package cn.rfidcer.bean.yz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**   
* @Description:IOS客户端用户
* @author 席志明 
* @date 2016年8月25日 下午7:37:50 
* @version V1.0   
*/
@JsonInclude(value=Include.NON_NULL)
public class SaleTotal {
	/**订单总金额*/
	private String saleAmount="0.00";
	/**订单数*/
	private String saleCount;
	/**
	 * 获取 订单总金额
	 * @return saleAmount
	 */
	public String getSaleAmount() {
		return saleAmount;
	}
	/**
	 * 设置 订单总金额
	 * @param saleAmount 订单总金额
	 */
	public void setSaleAmount(String saleAmount) {
		this.saleAmount = saleAmount;
	}
	/**
	 * 获取 订单数
	 * @return saleCount
	 */
	public String getSaleCount() {
		return saleCount;
	}
	/**
	 * 设置 订单数
	 * @param saleCount 订单数
	 */
	public void setSaleCount(String saleCount) {
		this.saleCount = saleCount;
	}
	
	

}

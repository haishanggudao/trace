package cn.rfidcer.bean.yz;

/**   
* @Title: SaleOrderTotalMoney.java 
* @Package cn.rfidcer.bean.yz 
* @Description: iOS-POJO 销售单的总销售额和总折扣
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月30日 下午7:19:50 
* @version V1.0   
*/
public class SaleOrderTotalMoney {
	
	/**
	 * 门店销售单ID
	 */
	private String storeSaleOrderId;
	/**已销售总金额*/
	private String totalMoney="0.00";
	/**总折扣金额*/
	private String totalCutOff="0.00";
	
	/**
	 * 获取 门店销售单ID
	 * @return the storeSaleOrderId
	 */
	public String getStoreSaleOrderId() {
		return storeSaleOrderId;
	}
	/**
	 * 设置 门店销售单ID
	 * @param storeSaleOrderId the storeSaleOrderId to set
	 */
	public void setStoreSaleOrderId(String storeSaleOrderId) {
		this.storeSaleOrderId = storeSaleOrderId;
	}
	/**
	 * 获取 已销售总金额
	 * @return totalMoney
	 */
	public String getTotalMoney() {
		return totalMoney;
	}
	/**
	 * 设置 已销售总金额
	 * @param totalMoney 已销售总金额
	 */
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取 总折扣金额
	 * @return totalCutOff
	 */
	public String getTotalCutOff() {
		return totalCutOff;
	}
	/**
	 * 设置 总折扣金额
	 * @param totalCutOff 总折扣金额
	 */
	public void setTotalCutOff(String totalCutOff) {
		this.totalCutOff = totalCutOff;
	}
	
}

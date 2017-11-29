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
public class SaleTotalMoney {

	/**已销售总金额*/
	private String totalMoney="0.00";
	/**总折扣金额*/
	private String totalCutOff="0.00";
	
	/**
	 * 销售单总笔数
	 */
	private int totalNum=0;
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
	/**获取销售单总笔数
	 * @return the 销售单总笔数
	 */
	public int getTotalNum() {
		return totalNum;
	}
	/**设置销售单总笔数
	 * @param totalNum the 销售单总笔数 to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	

}

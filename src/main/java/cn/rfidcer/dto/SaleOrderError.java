package cn.rfidcer.dto;

import cn.rfidcer.bean.ResultEntity;
import cn.rfidcer.bean.ResultMsg;

/**   
* @Title: SaleOrderError.java 
* @Package cn.rfidcer.dto 
* @Description:销售单错误信息
* @author 席志明
* @Copyright Copyright
* @date 2017年3月23日 下午4:27:47 
* @version V1.0   
*/
public class SaleOrderError implements ResultEntity{

	/**
	 * 销售单ID
	 */
	private String storeSaleOrderId;

	/**获取销售单ID
	 * @return the 销售单ID
	 */
	public String getStoreSaleOrderId() {
		return storeSaleOrderId;
	}

	/**设置销售单ID
	 * @param storeSaleOrderId the 销售单ID to set
	 */
	public void setStoreSaleOrderId(String storeSaleOrderId) {
		this.storeSaleOrderId = storeSaleOrderId;
	}
	
	
	
	
}

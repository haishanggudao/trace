package cn.rfidcer.dto;

import java.util.List;

import cn.rfidcer.bean.ResultEntity;

/**   
* @Title: SaleOrderErrorList.java 
* @Package cn.rfidcer.dto 
* @Description:同步异常的销售单ID
* @author 席志明
* @Copyright Copyright
* @date 2017年3月24日 上午10:09:46 
* @version V1.0   
*/
public class SaleOrderErrorList implements ResultEntity{

	/**
	 * 同步异常的销售单ID
	 */
	private List<SaleOrderError> errorSaleOrderIds;

	/**获取同步异常的销售单ID
	 * @return the 同步异常的销售单ID
	 */
	public List<SaleOrderError> getErrorSaleOrderIds() {
		return errorSaleOrderIds;
	}

	/**设置同步异常的销售单ID
	 * @param errorSaleOrderIds the 同步异常的销售单ID to set
	 */
	public void setErrorSaleOrderIds(List<SaleOrderError> errorSaleOrderIds) {
		this.errorSaleOrderIds = errorSaleOrderIds;
	}


	
	


}

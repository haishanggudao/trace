package cn.rfidcer.bean;

import java.util.Date;

/**   
  * @Title: 采购入库关系表
  * @author lxy
  * @date 2016年6月28日 下午1:17:15 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class PurchaseInstockAssoc extends BaseEntity {
	/**入库单ID*/
	private String instockMainId;
	/**采购单ID*/
	private String purchaseOrderId;
	/**
	 * 获取 入库单ID
	 * @return instockMainId
	 */
	public String getInstockMainId() {
		return instockMainId;
	}
	/**
	 * 设置 入库单ID
	 * @param instockMainId 入库单ID
	 */
	public void setInstockMainId(String instockMainId) {
		this.instockMainId = instockMainId;
	}
	/**
	 * 获取 采购单ID
	 * @return purchaseOrderId
	 */
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	/**
	 * 设置 采购单ID
	 * @param purchaseOrderId 采购单ID
	 */
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	
}
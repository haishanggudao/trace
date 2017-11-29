package cn.rfidcer.bean;

import java.util.Date;

/**   
  * @Title: #入库信息
  * @author xzm
  * @date 2016年6月27日 下午3:29:17 
  * @Copyright Copyright
  * @version V1.0   
*/
public class InstockInfo extends BaseEntity{
	/**入库明细单ID*/
	private String instockItemId;
	/**入库单ID*/
	private String instockMainId;
	/**入库数量*/
	private String instockNum;
	/**入库日期*/
	private Date instockDate;
	/**入库批次号*/
	private String instockBatchNum;
	/**收货人*/
	private String consignee;
	/**供应商*/
	private Supplier supplier;
	
	/**
	 * 源头信息
	 */
	private Origin origin;
	
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	/**
	 * 获取 入库明细单ID
	 * @return instockItemId
	 */
	public String getInstockItemId() {
		return instockItemId;
	}
	/**
	 * 设置 入库明细单ID
	 * @param instockItemId 入库明细单ID
	 */
	public void setInstockItemId(String instockItemId) {
		this.instockItemId = instockItemId;
	}
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
	 * 获取 入库数量
	 * @return instockNum
	 */
	public String getInstockNum() {
		return instockNum;
	}
	/**
	 * 设置 入库数量
	 * @param instockNum 入库数量
	 */
	public void setInstockNum(String instockNum) {
		this.instockNum = instockNum;
	}
	
	
	
	/**获取入库日期
	 * @return the 入库日期
	 */
	public Date getInstockDate() {
		return instockDate;
	}
	/**设置入库日期
	 * @param instockDate the 入库日期 to set
	 */
	public void setInstockDate(Date instockDate) {
		this.instockDate = instockDate;
	}
	/**
	 * 获取 入库批次号
	 * @return instockBatchNum
	 */
	public String getInstockBatchNum() {
		return instockBatchNum;
	}
	/**
	 * 设置 入库批次号
	 * @param instockBatchNum 入库批次号
	 */
	public void setInstockBatchNum(String instockBatchNum) {
		this.instockBatchNum = instockBatchNum;
	}
	/**
	 * 获取 收货人
	 * @return consignee
	 */
	public String getConsignee() {
		return consignee;
	}
	/**
	 * 设置 收货人
	 * @param consignee 收货人
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/**
	 * 获取 供应商
	 * @return supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}
	/**
	 * 设置 供应商
	 * @param supplier 供应商
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@Override
	public String toString() {
		return "InstockInfo [instockItemId=" + instockItemId
				+ ", instockMainId=" + instockMainId + ", instockNum="
				+ instockNum + ", instockDate=" + instockDate
				+ ", instockBatchNum=" + instockBatchNum + ", consignee="
				+ consignee + ", supplier=" + supplier + "]";
	}

}

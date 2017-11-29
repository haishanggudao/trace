package cn.rfidcer.bean;


import java.util.List;

/**   
  * @Title: 入库单
  * @author jgx
  * @date 2016年6月27日 下午3:40:12 
  * @Copyright Copyright
  * @version V1.0   
*/
public class InstockMain extends BaseEntity {
	/**入库单ID*/
	private String instockMainId;
	/**采购单ID*/
	private String purchaseOrderId;
	/**企业ID*/
	private String companyId;
	/**出库单号*/
	private String instockNum;
	/**出库批次*/
	private String instockBatchNum;
	/**收货人*/
	private String consignee;
	/**登记人*/
	private String registrant;
	/**入库时间*/
	private String instockDate;
	/**登记时间*/
	private String registDate;
	/**入库类型*/
	private String instockType;
	/**当前状态*/
	private String status;
	/**备注信息*/
	private String remark;
	/**#商品入库明细*/
	private List<InstockItem> instockitems;
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
	/**
	 * 获取 企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 企业ID
	 * @param companyId 企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 出库单号
	 * @return instockNum
	 */
	public String getInstockNum() {
		return instockNum;
	}
	/**
	 * 设置 出库单号
	 * @param instockNum 出库单号
	 */
	public void setInstockNum(String instockNum) {
		this.instockNum = instockNum;
	}
	/**
	 * 获取 出库批次
	 * @return instockBatchNum
	 */
	public String getInstockBatchNum() {
		return instockBatchNum;
	}
	/**
	 * 设置 出库批次
	 * @param instockBatchNum 出库批次
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
	 * 获取 登记人
	 * @return registrant
	 */
	public String getRegistrant() {
		return registrant;
	}
	/**
	 * 设置 登记人
	 * @param registrant 登记人
	 */
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	/**
	 * 获取 入库时间
	 * @return instockDate
	 */
	public String getInstockDate() {
		return instockDate;
	}
	/**
	 * 设置 入库时间
	 * @param instockDate 入库时间
	 */
	public void setInstockDate(String instockDate) {
		this.instockDate = instockDate;
	}
	/**
	 * 获取 登记时间
	 * @return registDate
	 */
	public String getRegistDate() {
		return registDate;
	}
	/**
	 * 设置 登记时间
	 * @param registDate 登记时间
	 */
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	/**
	 * 获取 入库类型
	 * @return instockType
	 */
	public String getInstockType() {
		return instockType;
	}
	/**
	 * 设置 入库类型
	 * @param instockType 入库类型
	 */
	public void setInstockType(String instockType) {
		this.instockType = instockType;
	}
	/**
	 * 获取 当前状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 当前状态
	 * @param status 当前状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取 备注信息
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置 备注信息
	 * @param remark 备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取 #商品入库明细
	 * @return instockitems
	 */
	public List<InstockItem> getInstockitems() {
		return instockitems;
	}
	/**
	 * 设置 #商品入库明细
	 * @param instockitems #商品入库明细
	 */
	public void setInstockitems(List<InstockItem> instockitems) {
		this.instockitems = instockitems;
	}
	
	@Override
	public String toString() {
		return "InstockMain [instockMainId=" + instockMainId
				+ ", purchaseOrderId=" + purchaseOrderId + ", companyId="
				+ companyId + ", instockNum=" + instockNum
				+ ", instockBatchNum=" + instockBatchNum + ", consignee="
				+ consignee + ", registrant=" + registrant + ", instockDate="
				+ instockDate + ", registDate=" + registDate + ", instockType="
				+ instockType + ", status=" + status + ", remark=" + remark
				+ ", instockitems=" + instockitems + "]";
	}
	
}
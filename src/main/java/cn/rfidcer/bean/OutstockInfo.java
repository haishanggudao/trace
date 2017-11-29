package cn.rfidcer.bean;

import java.sql.Timestamp;
import java.util.Date;

import cn.rfidcer.util.DateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**   
  * @Title: #手持机出库信息
  * @author xzm
  * @date 2016年6月27日 下午4:13:21 
  * @Copyright Copyright
  * @version V1.0   
*/
public class OutstockInfo {
	/**出库明细单ID*/
	private String outstockItemId;
	/**出库日期*/
	private Date outstockDate;
	/**更新时间*/
	private Timestamp updateTime;
	/**交货时间*/
	private Timestamp deliveryTime;
	/**物流企业名称*/
	private String name;
	/**联系人*/
	private String contact;
	/**电话*/
	private String tel;
	/**出库单号*/
	private String outstockNum;
	/**签收人*/
	private String consignor;
	/**出库单id*/
	private String outstockMainId;
	/**出库批次号*/
	private String outstockBatchNum;
	/**
	 * 获取出库单id
	 * @return
	 */
	public String getOutstockMainId() {
		return outstockMainId;
	}
	/**
	 * 设置出库单ID
	 * @param outstockMainId
	 */
	public void setOutstockMainId(String outstockMainId) {
		this.outstockMainId = outstockMainId;
	}
	/**
	 * 获取出库单号
	 * @return
	 */
	public String getOutstockNum() {
		return outstockNum;
	}
	/**
	 * 设置出库单号
	 * @param outstockNum
	 */
	public void setOutstockNum(String outstockNum) {
		this.outstockNum = outstockNum;
	}
	/**
	 * 获取签收人
	 * @return
	 */
	public String getConsignor() {
		return consignor;
	}
	/**
	 * 设置签收人
	 * @param consignor
	 */
	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}
	/**
	 * 获取 出库明细单ID
	 * @return outstockItemId
	 */
	public String getOutstockItemId() {
		return outstockItemId;
	}
	/**
	 * 设置 出库明细单ID
	 * @param outstockItemId 出库明细单ID
	 */
	public void setOutstockItemId(String outstockItemId) {
		this.outstockItemId = outstockItemId;
	}
	/**
	 * 获取 出库日期
	 * @return outstockDate
	 */
	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getOutstockDate() {
		return outstockDate;
	}
	/**
	 * 设置 出库日期
	 * @param outstockDate 出库日期
	 */
	public void setOutstockDate(Date outstockDate) {
		this.outstockDate = outstockDate;
	}
	/**
	 * 获取 更新时间
	 * @return updateTime
	 */
	@JsonSerialize(using = DateTimeSerializer.class)
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取 交货时间
	 * @return deliveryTime
	 */
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * 设置 交货时间
	 * @param deliveryTime 交货时间
	 */
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * 获取 物流企业名称
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置 物流企业名称
	 * @param name 物流企业名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取 联系人
	 * @return contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 设置 联系人
	 * @param contact 联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取 电话
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置 电话
	 * @param tel 电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOutstockBatchNum() {
		return outstockBatchNum;
	}
	public void setOutstockBatchNum(String outstockBatchNum) {
		this.outstockBatchNum = outstockBatchNum;
	}


}

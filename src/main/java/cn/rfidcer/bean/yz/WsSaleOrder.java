package cn.rfidcer.bean.yz;

import com.fasterxml.jackson.annotation.JsonRootName;

/**   
* @Title: WsSaleOrder.java 
* @Package cn.rfidcer.bean.yz 
* @Description: iOS-WS-POJO 销售单
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月29日 下午2:35:35 
* @version V1.0   
*/
@JsonRootName("SaleInfo") 
public class WsSaleOrder {
	
	/**
	 * GUID
	 */
	private String guid;
	/**
	 * 销售单号
	 */
	private String saleNo;
	/**
	 * 合计销售金额
	 */
	private double totalMoney;
	/**
	 * 销售日期;
	 */
	private String saleDate;
	/**
	 * 销售店铺ID
	 */
	private String storeID;
	/**
	 * 销售店铺名称
	 */
	private String storeName;
	
	/**
	 * 最后更新的操作者
	 */
	private String updateUser;
	/**
	 * 最后更新时间
	 */
	private String updateTime;
	/**
	 * 创建的操作者
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 会员卡ID
	 */
	private String memberID;
	/**
	 * 总折扣
	 */
	private double totalCutOff;
	/**
	 * 获取 GUID
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * 设置 GUID
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * 获取 销售单号
	 * @return the saleNo
	 */
	public String getSaleNo() {
		return saleNo;
	}
	/**
	 * 设置 销售单号
	 * @param saleNo the saleNo to set
	 */
	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}
	/**
	 * 获取 合计销售金额
	 * @return the totalMoney
	 */
	public double getTotalMoney() {
		return totalMoney;
	}
	/**
	 * 设置 合计销售金额
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取 销售日期;
	 * @return the saleDate
	 */
	public String getSaleDate() {
		return saleDate;
	}
	/**
	 * 设置 销售日期;
	 * @param saleDate the saleDate to set
	 */
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	/**
	 * 获取 销售店铺ID
	 * @return the storeID
	 */
	public String getStoreID() {
		return storeID;
	}
	/**
	 * 设置 销售店铺ID
	 * @param storeID the storeID to set
	 */
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	/**
	 * 获取 销售店铺名称
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}
	/**
	 * 设置 销售店铺名称
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * 获取 最后更新的操作者
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置 最后更新的操作者
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取 最后更新时间
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置 最后更新时间
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取 创建的操作者
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置 创建的操作者
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取 创建时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 创建时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 会员卡ID
	 * @return the memberID
	 */
	public String getMemberID() {
		return memberID;
	}
	/**
	 * 设置 会员卡ID
	 * @param memberID the memberID to set
	 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	/**
	 * 获取 总折扣
	 * @return the totalCutOff
	 */
	public double getTotalCutOff() {
		return totalCutOff;
	}
	/**
	 * 设置 总折扣
	 * @param totalCutOff the totalCutOff to set
	 */
	public void setTotalCutOff(double totalCutOff) {
		this.totalCutOff = totalCutOff;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WsSaleOrder [guid=" + guid + ", saleNo=" + saleNo + ", totalMoney=" + totalMoney + ", saleDate="
				+ saleDate + ", storeID=" + storeID + ", storeName=" + storeName + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + ", createUser=" + createUser + ", createTime=" + createTime + "]";
	} 

}

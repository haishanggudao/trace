package cn.rfidcer.bean.yz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
* @Title: WsSellStore.java 
* @Package cn.rfidcer.bean.yz 
* @Description: POJO 羽众酒业-销售门店 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月26日 下午1:15:09 
* @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class WsSellStore {
	
	private String guid;
	private String enterpriseID; 
	private String storeNo; 
	private String storeName; 
	private String address;
	private String telephone;
	private String fax;
	private String email; 
	private String updateUser;
	private String updateTime;
	private String createUser;
	private String createTime; 
	
	public WsSellStore() { 
	}
 

	/**
	 * 获取 #{bare_field_comment}
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the enterpriseID
	 */
	public String getEnterpriseID() {
		return enterpriseID;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param enterpriseID the enterpriseID to set
	 */
	public void setEnterpriseID(String enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the storeNo
	 */
	public String getStoreNo() {
		return storeNo;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param storeNo the storeNo to set
	 */
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
 
	/**
	 * 获取 #{bare_field_comment}
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	} 

	/**
	 * 获取 #{bare_field_comment}
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 #{bare_field_comment}
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WsSellStore [guid=" + guid + ", enterpriseID=" + enterpriseID + ", storeNo=" + storeNo + ", storeName="
				+ storeName + ", address=" + address + ", telephone=" + telephone + ", fax=" + fax + ", email=" + email
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", createUser=" + createUser
				+ ", createTime=" + createTime + "]";
	} 

}

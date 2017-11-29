package cn.rfidcer.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**   
* @Title: 手持设备的用户信息
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月21日 下午1:33:31 
* @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class ClientUser extends BaseEntity {
	
	/**主键ID*/
	private String clientUserId;
	/**设备用户名*/
	private String clientUserName;
	/**用户密码*/
	private String clientPassword;
	/**密码确认*/
	private String clientPasswordAgain; 
	/**设备token*/
	private String token;
	/**设备mac地址*/
	private String mac;
	/**资源号*/
	private String resourceNums;
	/**企业ID*/
	private String companyId;
	/**设备类型*/
	private String clientType;
	/**最后一次登录*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLogin;
	/**状态*/
	private String state;
	
	/**
	 * 获取 主键ID
	 * @return clientUserId
	 */
	public String getClientUserId() {
		return clientUserId;
	}

	/**
	 * 设置 主键ID
	 * @param clientUserId 主键ID
	 */
	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	/**
	 * 获取 设备用户名
	 * @return clientUserName
	 */
	public String getClientUserName() {
		return clientUserName;
	}

	/**
	 * 设置 设备用户名
	 * @param clientUserName 设备用户名
	 */
	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}

	/**
	 * 获取 用户密码
	 * @return clientPassword
	 */
	public String getClientPassword() {
		return clientPassword;
	}

	/**
	 * 设置 用户密码
	 * @param clientPassword 用户密码
	 */
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	/**
	 * 获取 密码确认
	 * @return clientPasswordAgain
	 */
	public String getClientPasswordAgain() {
		return clientPasswordAgain;
	}

	/**
	 * 设置 密码确认
	 * @param clientPasswordAgain 密码确认
	 */
	public void setClientPasswordAgain(String clientPasswordAgain) {
		this.clientPasswordAgain = clientPasswordAgain;
	}

	/**
	 * 获取 设备token
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置 设备token
	 * @param token 设备token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取 设备mac地址
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * 设置 设备mac地址
	 * @param mac 设备mac地址
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * 获取 资源号
	 * @return resourceNums
	 */
	public String getResourceNums() {
		return resourceNums;
	}

	/**
	 * 设置 资源号
	 * @param resourceNums 资源号
	 */
	public void setResourceNums(String resourceNums) {
		this.resourceNums = resourceNums;
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
	 * 获取 设备类型
	 * @return clientType
	 */
	public String getClientType() {
		return clientType;
	}

	/**
	 * 设置 设备类型
	 * @param clientType 设备类型
	 */
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	/**
	 * 获取 状态
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置 状态
	 * @param state 状态
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 设置 最后一次登录
	 * @param lastLogin 最后一次登录
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * 获取 最后一次登录
	 * @return lastLogin
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getLastLogin() {
		return lastLogin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClientUser [clientUserId=" + clientUserId + ", clientUserName="
				+ clientUserName + ", clientPassword=" + clientPassword
				+ ", clientPasswordAgain=" + clientPasswordAgain + ", token="
				+ token + ", mac=" + mac + ", resourceNums=" + resourceNums
				+ ", companyId=" + companyId + ", clientType=" + clientType
				+ ", lastLogin=" + lastLogin + ", state=" + state + "]";
	}

}
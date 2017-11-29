package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;


/**   
 * @Title: PosMac.java 
 * @Package cn.rfidcer.bean 
 * @Description: 企业Mac地址管理
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年8月19日 上午11:24:00 
 * @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_store_mac")
public class StoreMac extends BaseEntity{
	
	/**Mac地址*/
	@Id
	private String mac;
	/**Mac编号*/
	private String macNum;
	/**企业Id*/
	private String companyId;
	/**是否删除*/
	private int isDeleted;
	/**状态*/
	private String status;
	/**
	 * 获取 Mac地址
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * 设置 Mac地址
	 * @param mac Mac地址
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * 获取 Mac编号
	 * @return macNum
	 */
	public String getMacNum() {
		return macNum;
	}
	/**
	 * 设置 Mac编号
	 * @param macNum Mac编号
	 */
	public void setMacNum(String macNum) {
		this.macNum = macNum;
	}
	/**
	 * 获取 企业Id
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 企业Id
	 * @param companyId 企业Id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 是否删除
	 * @return isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置 是否删除
	 * @param isDeleted 是否删除
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取 状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 状态
	 * @param status 状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	


}
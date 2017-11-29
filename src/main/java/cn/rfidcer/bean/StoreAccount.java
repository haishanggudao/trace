/**
 * 
 */
package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**
 * @Description:门店账户
 * @author 黄苇
 * @Copyright Copyright
 * @date 2016年11月16日 下午2:39:28
 * @version V1.0
 */
@Table(name="t_store_account")
@NameStyle(Style.normal)
@JsonInclude(value = Include.NON_NULL)
public class StoreAccount extends BaseEntity{
    /**
     * ID
     */
	@Id
	private String id;
	/**
     * 门店名
     */
	private String username;
	/**
     * 密码
     */
	private String password;
	/**
     * 企业ID
     */
	private String companyId;
	
	/**
     * 名称
     */
	private String nickName;
	/**
	 * 关联门店
	 */
	@Transient
	private Company company;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**获取密码
	 * @return the 密码
	 */
	public String getPassword() {
		return password;
	}
	/**设置密码
	 * @param password the 密码 to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "StoreAccount [id=" + id + ", username=" + username + ", passwd=" + password + ", companyId=" + companyId
				+ ", nickName=" + nickName + "]";
	}
	
	
}

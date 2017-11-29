package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
* @Description:门店注册
* @author 席志明
* @Copyright Copyright
* @date 2016年11月14日 下午4:05:32 
* @version V1.0   
*/
@Table(name="t_store_register")
@NameStyle(Style.normal)
public class StoreRegister extends BaseEntity{

	/**
	 * ID
	 */
	@Id
	private String id;
	
	/**
	 * 注册码
	 */
	private String license;
	
	/**
	 * mac地址
	 */
	private String mac;
	
	/**
	 * 门店编号
	 */
	private String location;
	
	
	/**
	 * 门店机器编号
	 */
	private String subLocation;
	/**
	 * 门店ID
	 */
	private String companyId;
	
	/**
	 * 关联门店
	 */
	@Transient
	private Company company;

	/**获取ID
	 * @return the ID
	 */
	public String getId() {
		return id;
	}

	/**设置ID
	 * @param id the ID to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**获取注册码
	 * @return the 注册码
	 */
	public String getLicense() {
		return license;
	}

	/**设置注册码
	 * @param license the 注册码 to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**获取mac地址
	 * @return the mac地址
	 */
	public String getMac() {
		return mac;
	}

	/**设置mac地址
	 * @param mac the mac地址 to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}


	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	
	/**获取关联门店
	 * @return the 关联门店
	 */
	public Company getCompany() {
		return company;
	}

	/**设置关联门店
	 * @param company the 关联门店 to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**获取门店编号
	 * @return the 门店编号
	 */
	public String getLocation() {
		return location;
	}

	/**设置门店编号
	 * @param location the 门店编号 to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**获取门店机器编号
	 * @return the 门店机器编号
	 */
	public String getSubLocation() {
		return subLocation;
	}

	/**设置门店机器编号
	 * @param subLocation the 门店机器编号 to set
	 */
	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

	@Override
	public String toString() {
		return "StoreRegister [id=" + id + ", license=" + license + ", mac="
				+ mac + ", location=" + location + ", subLocation="
				+ subLocation + ", companyId=" + companyId + "]";
	}
	
}

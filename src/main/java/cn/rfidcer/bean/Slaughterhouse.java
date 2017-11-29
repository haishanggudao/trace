package cn.rfidcer.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**   
  * @Title: 屠宰场
  * @author jie.jia
  * @date 2016年6月28日 下午2:23:11 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
@JsonInclude(value = Include.NON_NULL)
public class Slaughterhouse extends BaseEntity {
	/**#企业*/
	private Company company;
	/**#企业名称*/
	private String companyName;
	/**屠宰场ID*/
	private String slaughterhouseId;
	/**企业ID*/
	private String companyId;
	/**屠宰场所属公司ID*/
	private String slaughterhouseCompanyId;
	/**屠宰场名称*/
	private String slaughterhouseName;
	/**屠宰场地址*/
	private String slaughterhouseAddress;
	/**联系人*/
	private String contact;
	/**联系人别名*/
	private String altContact;
	/**联系电话*/
	private String tel;
	/**状态*/
	private String status;
	/**备注*/
	private String remark;
	/**屠宰方式*/
	private String mode;
	/**是否删除*/
	private String isDeleted;
	/**
	 * 获取 #企业
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * 设置 #企业
	 * @param company #企业
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * 获取 #企业名称
	 * @return companyName
	 */
	public String getCompanyName() {
		if (company != null) {
			companyName = company.getName();
		}
		return companyName;
	}
	/**
	 * 设置 #企业名称
	 * @param companyName #企业名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取 屠宰场ID
	 * @return slaughterhouseId
	 */
	public String getSlaughterhouseId() {
		return slaughterhouseId;
	}
	/**
	 * 设置 屠宰场ID
	 * @param slaughterhouseId 屠宰场ID
	 */
	public void setSlaughterhouseId(String slaughterhouseId) {
		this.slaughterhouseId = slaughterhouseId;
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
	 * 获取 屠宰场所属公司ID
	 * @return slaughterhouseCompanyId
	 */
	public String getSlaughterhouseCompanyId() {
		return slaughterhouseCompanyId;
	}
	/**
	 * 设置 屠宰场所属公司ID
	 * @param slaughterhouseCompanyId 屠宰场所属公司ID
	 */
	public void setSlaughterhouseCompanyId(String slaughterhouseCompanyId) {
		this.slaughterhouseCompanyId = slaughterhouseCompanyId;
	}
	/**
	 * 获取 屠宰场名称
	 * @return slaughterhouseName
	 */
	public String getSlaughterhouseName() {
		if (slaughterhouseName == null) {
			slaughterhouseName = companyName;
		}
		if (slaughterhouseName == null && company != null) {
			slaughterhouseName = company.getName();
		}
		return slaughterhouseName;
	}
	/**
	 * 设置 屠宰场名称
	 * @param slaughterhouseName 屠宰场名称
	 */
	public void setSlaughterhouseName(String slaughterhouseName) {
		this.slaughterhouseName = slaughterhouseName;
	}
	/**
	 * 获取 屠宰场地址
	 * @return slaughterhouseAddress
	 */
	public String getSlaughterhouseAddress() {
		return slaughterhouseAddress;
	}
	/**
	 * 设置 屠宰场地址
	 * @param slaughterhouseAddress 屠宰场地址
	 */
	public void setSlaughterhouseAddress(String slaughterhouseAddress) {
		this.slaughterhouseAddress = slaughterhouseAddress;
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
	 * 获取 联系人别名
	 * @return altContact
	 */
	public String getAltContact() {
		return altContact;
	}
	/**
	 * 设置 联系人别名
	 * @param altContact 联系人别名
	 */
	public void setAltContact(String altContact) {
		this.altContact = altContact;
	}
	/**
	 * 获取 联系电话
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置 联系电话
	 * @param tel 联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
	/**
	 * 获取 备注
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取 屠宰方式
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * 设置 屠宰方式
	 * @param mode 屠宰方式
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * 获取 是否删除
	 * @return isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置 是否删除
	 * @param isDeleted 是否删除
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public String toString() {
		return "Slaughterhouse [company=" + company + ", companyName="
				+ companyName + ", slaughterhouseId=" + slaughterhouseId
				+ ", companyId=" + companyId + ", slaughterhouseCompanyId="
				+ slaughterhouseCompanyId + ", slaughterhouseName="
				+ slaughterhouseName + ", slaughterhouseAddress="
				+ slaughterhouseAddress + ", contact=" + contact
				+ ", altContact=" + altContact + ", tel=" + tel + ", status="
				+ status + ", remark=" + remark + ", mode=" + mode
				+ ", isDeleted=" + isDeleted + "]";
	}
	



}
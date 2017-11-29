package cn.rfidcer.bean;

/**   
  * @Title: 物流表
  * @author jgx
  * @date 2016年6月27日 下午3:51:05 
  * @Copyright Copyright
  * @version V1.0   
*/
public class Logistics extends BaseEntity {
	/**物流ID*/
	private String logisticsId; // PK
	/**企业ID*/
	private String companyId; // 用户企业ID
	/**#企业名称*/
	private String companyName;
	/**物流所属企业ID*/
	private String logisticsCompanyId;
	/**#物流所属企业名称*/
	private String logisticsCompanyName;
	/**联系人*/
	private String contact;
	/**联系人别名*/
	private String altContact;
	/**电话*/
	private String tel;
	/**状态*/
	private String status;
	/**#所属企业*/
	private Company company;
	/**物流企业别名*/
	private String logisticsAlias;
	/**#物流企业地址*/
	private String logisticsAddress;
	/**
	 * 获取 物流ID
	 * @return logisticsId
	 */
	public String getLogisticsId() {
		return logisticsId;
	}
	/**
	 * 设置 物流ID
	 * @param logisticsId 物流ID
	 */
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
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
	 * 获取 #企业名称
	 * @return companyName
	 */
	public String getCompanyName() {
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
	 * 获取 物流所属企业ID
	 * @return logisticsCompanyId
	 */
	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}
	/**
	 * 设置 物流所属企业ID
	 * @param logisticsCompanyId 物流所属企业ID
	 */
	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}
	/**
	 * 获取 #物流所属企业名称
	 * @return logisticsCompanyName
	 */
	public String getLogisticsCompanyName() {
		if (company != null) {
			logisticsCompanyName = company.getName();
		}
		return logisticsCompanyName;
	}
	/**
	 * 设置 #物流所属企业名称
	 * @param logisticsCompanyName #物流所属企业名称
	 */
	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
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
	 * 获取 #所属企业
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * 设置 #所属企业
	 * @param company #所属企业
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * 获取 物流企业别名
	 * @return logisticsAlias
	 */
	public String getLogisticsAlias() {
		if (logisticsAlias == null) {
			logisticsAlias = logisticsCompanyName;
		}
		if (logisticsAlias == null && company != null) {
			logisticsAlias = company.getName();
		}
		return logisticsAlias;
	}
	/**
	 * 设置 物流企业别名
	 * @param logisticsAlias 物流企业别名
	 */
	public void setLogisticsAlias(String logisticsAlias) {
		this.logisticsAlias = logisticsAlias;
	}
	/**
	 * 获取 #物流企业地址
	 * @return logisticsAddress
	 */
	public String getLogisticsAddress() {
		return logisticsAddress;
	}
	/**
	 * 设置 #物流企业地址
	 * @param logisticsAddress #物流企业地址
	 */
	public void setLogisticsAddress(String logisticsAddress) {
		this.logisticsAddress = logisticsAddress;
	}
	
	@Override
	public String toString() {
		return "Logistics [logisticsId=" + logisticsId + ", companyId="
				+ companyId + ", companyName=" + companyName
				+ ", logisticsCompanyId=" + logisticsCompanyId
				+ ", logisticsCompanyName=" + logisticsCompanyName
				+ ", contact=" + contact + ", altContact=" + altContact
				+ ", tel=" + tel + ", status=" + status + ", company=" + company
				+ ", logisticsAlias=" + logisticsAlias + ", logisticsAddress="
				+ logisticsAddress + "]";
	}
	
	
}
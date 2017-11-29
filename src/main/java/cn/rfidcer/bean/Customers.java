package cn.rfidcer.bean;

import org.apache.commons.lang3.StringUtils;

/**   
  * @Title: 客户信息表
  * @author jgx
  * @date 2016年6月27日 上午10:34:35 
  * @Copyright Copyright
  * @version V1.0   
*/
public class Customers extends BaseEntity {
	/**客户ID*/
	private String customerId;
	/**企业ID*/
	private String companyId;
	/**客户企业名称*/
	private String companyName;
	/**客户企业ID*/
	private String custCompanyId;
	/**联系人名称（法人姓名）*/
	private String contact;
	/**联系人别名（法人姓名）*/
	private String altContact;
	/**客户企业别名*/
	private String customerAlias;
	/**联系人电话(联系方式)*/
	private String tel;
	/**状态：0-启用，1-禁用*/
	private String status;
	/**企业对象 非数据库字段*/
	private Company company; // 企业信息; added by jie.jia at 2016-01-07 10:00
	/**企业对象 非数据库字段*/
	private String custCompanyName; // 客户企业名称; added by jie.jia at 2016-01-07

	private Integer isDeleted;
	
    private String cadministrativedivision;

    private String custCode;
    
    private String companyCidnumb;
    
    private String companyCnatureName;
    
    private String companyCregistrationnumber;
    
    private String code;
    
	public String getCompanyCidnumb() {
		if(StringUtils.isEmpty(companyCidnumb) && company != null) {
			companyCidnumb = company.getCidnumb();
		}
		return companyCidnumb;
	}

	public void setCompanyCidnumb(String companyCidnumb) {
		this.companyCidnumb = companyCidnumb;
	}
	
	public String getCode() {
		if(StringUtils.isEmpty(code) && company != null) {
			code = company.getCode();
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyCnatureName() {
		if(StringUtils.isEmpty(companyCnatureName) && company != null) {
			companyCnatureName = company.getCnatureName();
		}
		return companyCnatureName;
	}

	public void setCompanyCnatureName(String companyCnatureName) {
		this.companyCnatureName = companyCnatureName;
	}

	public String getCompanyCregistrationnumber() {
		if(StringUtils.isEmpty(companyCregistrationnumber) && company != null) {
			companyCregistrationnumber = company.getCregistrationnumber();
		}
		return companyCregistrationnumber;
	}

	public void setCompanyCregistrationnumber(String companyCregistrationnumber) {
		this.companyCregistrationnumber = companyCregistrationnumber;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCustCompanyId() {
		return custCompanyId;
	}

	public void setCustCompanyId(String custCompanyId) {
		this.custCompanyId = custCompanyId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAltContact() {
		return altContact;
	}

	public void setAltContact(String altContact) {
		this.altContact = altContact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCustCompanyName() {
		if (company != null) {
			custCompanyName = company.getName();
		}
		return custCompanyName;
	}

	public void setCustCompanyName(String custCompanyName) {
		this.custCompanyName = custCompanyName;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCustomerAlias() {
		if (customerAlias == null) {
			customerAlias = custCompanyName;
		}
		if (customerAlias == null && company != null) {
			customerAlias = company.getName();
		}
		return customerAlias;
	}

	public void setCustomerAlias(String customerAlias) {
		this.customerAlias = customerAlias;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

    public String getCadministrativedivision() {
		return cadministrativedivision;
	}

	public void setCadministrativedivision(String cadministrativedivision) {
		this.cadministrativedivision = cadministrativedivision;
	}
}
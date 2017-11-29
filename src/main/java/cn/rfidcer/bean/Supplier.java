package cn.rfidcer.bean;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
  * @Title: 供应商 
  * @author jie.jia
  * @date 2016年6月28日 下午2:23:37 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
@JsonInclude(value=Include.NON_NULL)
public class Supplier extends BaseEntity {
	/**#企业*/
	private Company company;
	/**#企业名称*/
	private String companyName;
	/**供应商ID*/
	private String supplierId;
	/**企业ID*/
	private String companyId;
	/**供应商别名*/
	private String supplierAlias;
	/**供应商所属公司ID*/
	private String supplierCompanyId;
	/**联系人*/
	private String contact;
	/**联系人别名*/
	private String altContact;
	/**联系电话*/
	private String tel;
	/**状态*/
	private String status;
	/**是否删除*/
	private Integer isDeleted;
	/**供应商地址*/
	private String supplierAddress;
	/**备注*/
	private String remark;
	/**登记人*/
    private String sregisteredperson;
	/**登记时间*/
    private Date sregiesteredtime;
	/**登记时间*/    
    private Integer isinuse;
    /**营业证号*/
    private String companyCidnumb;
    /**营业证号*/    
    private String companyCnatureName;
    /**工商注册号*/  
    private String companyCregistrationnumber;
    /**供应商号*/
    private String supplierCode;
    
    /**
     * 供应商简介
     */
    private String supplierDesc;
    
	public String getCompanyCidnumb() {
		if(StringUtils.isEmpty(companyCidnumb) && company != null) {
			companyCidnumb = company.getCidnumb();
		}
		return companyCidnumb;
	}

	public void setCompanyCidnumb(String companyCidnumb) {
		this.companyCidnumb = companyCidnumb;
	}
	
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public String getSupplierAlias() {
		if (supplierAlias == null) {
			supplierAlias = companyName;
		}
		if (supplierAlias == null && company != null) {
			supplierAlias = company.getName();
		}
		return supplierAlias;
	}

	public void setSupplierAlias(String supplierAlias) {
		this.supplierAlias = supplierAlias;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getSupplierCompanyId() {
		return supplierCompanyId;
	}

	public void setSupplierCompanyId(String supplierCompanyId) {
		this.supplierCompanyId = supplierCompanyId;
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

	public String getCompanyName() {
		if (company != null) {
			companyName = company.getName();
		}
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSregisteredperson() {
        return sregisteredperson;
    }

    public void setSregisteredperson(String sregisteredperson) {
        this.sregisteredperson = sregisteredperson;
    }

    public Date getSregiesteredtime() {
        return sregiesteredtime;
    }

    public void setSregiesteredtime(Date sregiesteredtime) {
        this.sregiesteredtime = sregiesteredtime;
    }
    
    public Integer getIsinuse() {
        return isinuse;
    }

    public void setIsinuse(Integer isinuse) {
        this.isinuse = isinuse;
    }
    
    
	/**获取供应商简介
	 * @return the 供应商简介
	 */
	public String getSupplierDesc() {
		return supplierDesc;
	}

	/**设置供应商简介
	 * @param supplierDesc the 供应商简介 to set
	 */
	public void setSupplierDesc(String supplierDesc) {
		this.supplierDesc = supplierDesc;
	}

	@Override
	public String toString() {
		return "Supplier [company=" + company + ", companyName=" + companyName + ", supplierId=" + supplierId
				+ ", companyId=" + companyId + ", supplierAlias=" + supplierAlias + ", supplierCompanyId="
				+ supplierCompanyId + ", contact=" + contact + ", altContact=" + altContact + ", tel=" + tel
				+ ", status=" + status + ", isDeleted=" + isDeleted + ", supplierAddress=" + supplierAddress
				+ ", remark=" + remark + ", sregisteredperson=" + sregisteredperson + ", sregiesteredtime="
				+ sregiesteredtime + ", isinuse=" + isinuse + ", companyCidnumb=" + companyCidnumb
				+ ", companyCnatureName=" + companyCnatureName + ", companyCregistrationnumber="
				+ companyCregistrationnumber + "]";
	}
    
    
}
package cn.rfidcer.bean;

/**   
  * @Title: 企业领域表
  * @author jgx
  * @date 2016年6月27日 上午10:34:18 
  * @Copyright Copyright
  * @version V1.0   
*/
public class CompanyField extends BaseEntity{
	/**企业领域ID*/
	private String companyFieldId;
	/**企业领域名称*/
	private String companyFieldName;
	/**领域等级*/
	private String level;
	/**父级领域*/
	private String parentFieldId;
	/**状态：0-启用，1-禁用*/
	private String status;
	/**企业领域简介*/
	private String companyFieldDesc;
	
	/**
	 * 获取 企业领域ID
	 * @return companyFieldId
	 */
	public String getCompanyFieldId() {
		return companyFieldId;
	}

	/**
	 * 设置 企业领域ID
	 * @param companyFieldId 企业领域ID
	 */
	public void setCompanyFieldId(String companyFieldId) {
		this.companyFieldId = companyFieldId;
	}

	/**
	 * 获取 企业领域名称
	 * @return companyFieldName
	 */
	public String getCompanyFieldName() {
		return companyFieldName;
	}

	/**
	 * 设置 企业领域名称
	 * @param companyFieldName 企业领域名称
	 */
	public void setCompanyFieldName(String companyFieldName) {
		this.companyFieldName = companyFieldName;
	}

	/**
	 * 获取 领域等级
	 * @return level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 设置 领域等级
	 * @param level 领域等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 获取 父级领域
	 * @return parentFieldId
	 */
	public String getParentFieldId() {
		return parentFieldId;
	}

	/**
	 * 设置 父级领域
	 * @param parentFieldId 父级领域
	 */
	public void setParentFieldId(String parentFieldId) {
		this.parentFieldId = parentFieldId;
	}

	/**
	 * 获取 状态：0-启用，1-禁用
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置 状态：0-启用，1-禁用
	 * @param status 状态：0-启用，1-禁用
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取 企业领域简介
	 * @return companyFieldDesc
	 */
	public String getCompanyFieldDesc() {
		return companyFieldDesc;
	}

	/**
	 * 设置 企业领域简介
	 * @param companyFieldDesc 企业领域简介
	 */
	public void setCompanyFieldDesc(String companyFieldDesc) {
		this.companyFieldDesc = companyFieldDesc;
	}

	@Override
	public String toString() {
		return "CompanyField [companyFieldId=" + companyFieldId
				+ ", companyFieldName=" + companyFieldName + ", level=" + level
				+ ", parentFieldId=" + parentFieldId + ", status=" + status
				+ ", companyFieldDesc=" + companyFieldDesc + "]";
	}

	
	
	
}
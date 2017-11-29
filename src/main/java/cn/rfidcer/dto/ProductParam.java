package cn.rfidcer.dto;

public class ProductParam {

	/**
	 * 企业ID
	 */
	private String companyId;
	
	/**
	 * 最后更新时间
	 */
	private String lastUpdateTime;

	/**获取企业ID
	 * @return the 企业ID
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**设置企业ID
	 * @param companyId the 企业ID to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**获取最后更新时间
	 * @return the 最后更新时间
	 */
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**设置最后更新时间
	 * @param lastUpdateTime the 最后更新时间 to set
	 */
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "ProductParam [companyId=" + companyId + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}
}

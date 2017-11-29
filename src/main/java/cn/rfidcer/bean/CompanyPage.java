package cn.rfidcer.bean;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
* @Description:企业跳转页面
* @author 席志明
* @date 2016年6月22日 下午7:58:44 
* @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_company_page")
public class CompanyPage {

	/**
	 * 企业ID
	 */
	@Id
	private String companyId;
	/**
	 * 页面跳转的URL
	 */
	@Id
	private String url;
	/**
	 * 跳转的页面，如jsp
	 */
	private String page;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private Timestamp createTime;


	public CompanyPage() {
	}

	public CompanyPage(String companyId, String url) {
		this.companyId = companyId;
		this.url = url;
	}

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

	/**获取页面跳转的URL
	 * @return the 页面跳转的URL
	 */
	public String getUrl() {
		return url;
	}

	/**设置页面跳转的URL
	 * @param url the 页面跳转的URL to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**获取跳转的页面，如jsp
	 * @return the 跳转的页面，如jsp
	 */
	public String getPage() {
		return page;
	}

	/**设置跳转的页面，如jsp
	 * @param page the 跳转的页面，如jsp to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**获取备注
	 * @return the 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**设置备注
	 * @param remark the 备注 to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**获取创建时间
	 * @return the 创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**设置创建时间
	 * @param createTime the 创建时间 to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}

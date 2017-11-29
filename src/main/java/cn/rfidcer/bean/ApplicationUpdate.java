package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
* @Description:应用升级
* @author 席志明
* @Copyright Copyright
* @date 2016年11月3日 下午2:28:02 
* @version V1.0   
*/
@Table(name="t_appliation_update")
@NameStyle(Style.normal)
@JsonInclude(value=Include.NON_NULL)
public class ApplicationUpdate extends BaseEntity{

	@Id
	private String id;
	/**
	 * 应用名
	 */
	private String name;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 版本升级简介
	 */
	private String versionIntroduction;
	/**
	 * 是否强制升级
	 */
	private String status;
	
	/**
	 * 企业ID
	 */
	private String companyId;
	
	
	/**
	 * 应用文件
	 */
	@Transient
	private MultipartFile applicationfile;
	
	/**
	 * 应用路径
	 */
	private String applicationPath;
	
	
	/**获取应用文件
	 * @return the 应用文件
	 */
	public MultipartFile getApplicationfile() {
		return applicationfile;
	}
	/**设置应用文件
	 * @param applicationfile the 应用文件 to set
	 */
	public void setApplicationfile(MultipartFile applicationfile) {
		this.applicationfile = applicationfile;
	}
	/**获取应用路径
	 * @return the 应用路径
	 */
	public String getApplicationPath() {
		return applicationPath;
	}
	/**设置应用路径
	 * @param applicationPath the 应用路径 to set
	 */
	public void setApplicationPath(String applicationPath) {
		this.applicationPath = applicationPath;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**获取应用名
	 * @return the 应用名
	 */
	public String getName() {
		return name;
	}
	/**设置应用名
	 * @param name the 应用名 to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**获取版本号
	 * @return the 版本号
	 */
	public String getVersion() {
		return version;
	}
	/**设置版本号
	 * @param version the 版本号 to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**获取版本升级简介
	 * @return the 版本升级简介
	 */
	public String getVersionIntroduction() {
		return versionIntroduction;
	}
	/**设置版本升级简介
	 * @param versionIntroduction the 版本升级简介 to set
	 */
	public void setVersionIntroduction(String versionIntroduction) {
		this.versionIntroduction = versionIntroduction;
	}
	/**获取是否强制升级
	 * @return the 是否强制升级
	 */
	public String getStatus() {
		return status;
	}
	/**设置是否强制升级
	 * @param status the 是否强制升级 to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplicationUpdate [id=" + id + ", name=" + name + ", version="
				+ version + ", versionIntroduction=" + versionIntroduction
				+ ", status=" + status + ", companyId=" + companyId
				+ ", applicationPath=" + applicationPath + "]";
	}
	
}

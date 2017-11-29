package cn.rfidcer.bean;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
* @Title: 企业表
* @author 具光星  
* @Copyright Copyright
* @date 2016年6月24日 下午2:28:58 
* @version V1.0
 */
@JsonInclude(value=Include.NON_NULL)
public class Company extends BaseEntity{
	/**企业ID*/
    private String companyid;
    /**企业名称*/
    private String name;
    /**企业简称*/
    private String shortname;
    /**企业编码*/
    private String code;
    /**企业地址*/
    private String address;
    /**联系人*/
    private String contact;
    /**电子邮件*/
    private String email;
    /**电话*/
    private String tel;
    /**营业执照注册号*/
    private String license;
    /**食品安全许可证号*/
    private String foodSafetyCode;
    /**组织机构代码*/
    private String orgcode;
    /**所属领域*/
    private String companyfieldid;
    /**省*/
    private String province;
    /**市*/
    private String city;
    /**区*/
    private String area;
    /**状态：0-启用，1-禁用*/
    private String status;
    /**名称*/
    private String cname;
    /**性质*/
    private String cnature;
    /**性质名称：非数据库字段*/
    private String cnatureName;
    /**身份证号*/
	private String cidnumb;
	/**客户类别*/
    private String ccustomercategories;
    /**经营范围*/
    private String cbusinessscope;
    /**酒类经营许可证*/
    private String cliquorbusinesslicense;
    /**经营地址*/
    private String cbusinessaddress;
    /**卫生许可证*/
    private String chygienelicense;
    /**法人地址*/
    private String clegalpersonaddress;
    /**营业执照*/
    private String cbusinesslicense;
    /**工商注册号*/
    private String cregistrationnumber;
    /**有效日期1*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ceffectivedate1;
    /**有效日期2*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ceffectivedate2;
    
    /**
     * 营业执照图片
     */
    private String licenseUrl; 
    
    /**
     * 卫生许可证图片
     */
    private String chygienelicenseUrl;
    
    /**
     * 追溯码流水号
     */
    private int traceSerialNum;
    /**mac编号数量*/
    private int macNum;
    
    /**
     * 企业简介
     */
    private String presentation;
    
	/**获取追溯码流水号
	 * @return the 追溯码流水号
	 */
	public int getTraceSerialNum() {
		return traceSerialNum;
	}
	/**设置追溯码流水号
	 * @param traceSerialNum the 追溯码流水号 to set
	 */
	public void setTraceSerialNum(int traceSerialNum) {
		this.traceSerialNum = traceSerialNum;
	}
	/**
	 * 获取 企业ID
	 * @return companyid
	 */
	public String getCompanyid() {
		return companyid;
	}
	/**
	 * 设置 企业ID
	 * @param companyid 企业ID
	 */
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	/**
	 * 获取 企业名称
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置 企业名称
	 * @param name 企业名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取 企业简称
	 * @return shortname
	 */
	public String getShortname() {
		return shortname;
	}
	/**
	 * 设置 企业简称
	 * @param shortname 企业简称
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	/**
	 * 获取 企业编码
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置 企业编码
	 * @param code 企业编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取 企业地址
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置 企业地址
	 * @param address 企业地址
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * 获取 电子邮件
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置 电子邮件
	 * @param email 电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * 获取 营业执照注册号
	 * @return license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * 设置 营业执照注册号
	 * @param license 营业执照注册号
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * 获取 食品安全许可证号
	 * @return foodSafetyCode
	 */
	public String getFoodSafetyCode() {
		return foodSafetyCode;
	}
	/**
	 * 设置 食品安全许可证号
	 * @param foodSafetyCode 食品安全许可证号
	 */
	public void setFoodSafetyCode(String foodSafetyCode) {
		this.foodSafetyCode = foodSafetyCode;
	}
	/**
	 * 获取 组织机构代码
	 * @return orgcode
	 */
	public String getOrgcode() {
		return orgcode;
	}
	/**
	 * 设置 组织机构代码
	 * @param orgcode 组织机构代码
	 */
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	/**
	 * 获取 所属领域
	 * @return companyfieldid
	 */
	public String getCompanyfieldid() {
		return companyfieldid;
	}
	/**
	 * 设置 所属领域
	 * @param companyfieldid 所属领域
	 */
	public void setCompanyfieldid(String companyfieldid) {
		this.companyfieldid = companyfieldid;
	}
	/**
	 * 获取 省
	 * @return province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置 省
	 * @param province 省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取 市
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置 市
	 * @param city 市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取 区
	 * @return area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置 区
	 * @param area 区
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * 获取 名称
	 * @return cname
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置 名称
	 * @param cname 名称
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取 性质
	 * @return cnature
	 */
	public String getCnature() {
		return cnature;
	}
	/**
	 * 设置 性质
	 * @param cnature 性质
	 */
	public void setCnature(String cnature) {
		this.cnature = cnature;
	}
	/**
	 * 获取 性质名称：非数据库字段
	 * @return cnatureName
	 */
	public String getCnatureName() {
		return cnatureName;
	}
	/**
	 * 设置 性质名称：非数据库字段
	 * @param cnatureName 性质名称：非数据库字段
	 */
	public void setCnatureName(String cnatureName) {
		this.cnatureName = cnatureName;
	}
	/**
	 * 获取 身份证号
	 * @return cidnumb
	 */
	public String getCidnumb() {
		return cidnumb;
	}
	/**
	 * 设置 身份证号
	 * @param cidnumb 身份证号
	 */
	public void setCidnumb(String cidnumb) {
		this.cidnumb = cidnumb;
	}
	/**
	 * 获取 客户类别
	 * @return ccustomercategories
	 */
	public String getCcustomercategories() {
		return ccustomercategories;
	}
	/**
	 * 设置 客户类别
	 * @param ccustomercategories 客户类别
	 */
	public void setCcustomercategories(String ccustomercategories) {
		this.ccustomercategories = ccustomercategories;
	}
	/**
	 * 获取 经营范围
	 * @return cbusinessscope
	 */
	public String getCbusinessscope() {
		return cbusinessscope;
	}
	/**
	 * 设置 经营范围
	 * @param cbusinessscope 经营范围
	 */
	public void setCbusinessscope(String cbusinessscope) {
		this.cbusinessscope = cbusinessscope;
	}
	/**
	 * 获取 酒类经营许可证
	 * @return cliquorbusinesslicense
	 */
	public String getCliquorbusinesslicense() {
		return cliquorbusinesslicense;
	}
	/**
	 * 设置 酒类经营许可证
	 * @param cliquorbusinesslicense 酒类经营许可证
	 */
	public void setCliquorbusinesslicense(String cliquorbusinesslicense) {
		this.cliquorbusinesslicense = cliquorbusinesslicense;
	}
	/**
	 * 获取 经营地址
	 * @return cbusinessaddress
	 */
	public String getCbusinessaddress() {
		return cbusinessaddress;
	}
	/**
	 * 设置 经营地址
	 * @param cbusinessaddress 经营地址
	 */
	public void setCbusinessaddress(String cbusinessaddress) {
		this.cbusinessaddress = cbusinessaddress;
	}
	/**
	 * 获取 卫生许可证
	 * @return chygienelicense
	 */
	public String getChygienelicense() {
		return chygienelicense;
	}
	/**
	 * 设置 卫生许可证
	 * @param chygienelicense 卫生许可证
	 */
	public void setChygienelicense(String chygienelicense) {
		this.chygienelicense = chygienelicense;
	}
	/**
	 * 获取 法人地址
	 * @return clegalpersonaddress
	 */
	public String getClegalpersonaddress() {
		return clegalpersonaddress;
	}
	/**
	 * 设置 法人地址
	 * @param clegalpersonaddress 法人地址
	 */
	public void setClegalpersonaddress(String clegalpersonaddress) {
		this.clegalpersonaddress = clegalpersonaddress;
	}
	/**
	 * 获取 营业执照
	 * @return cbusinesslicense
	 */
	public String getCbusinesslicense() {
		return cbusinesslicense;
	}
	/**
	 * 设置 营业执照
	 * @param cbusinesslicense 营业执照
	 */
	public void setCbusinesslicense(String cbusinesslicense) {
		this.cbusinesslicense = cbusinesslicense;
	}
	/**
	 * 获取 工商注册号
	 * @return cregistrationnumber
	 */
	public String getCregistrationnumber() {
		return cregistrationnumber;
	}
	/**
	 * 设置 工商注册号
	 * @param cregistrationnumber 工商注册号
	 */
	public void setCregistrationnumber(String cregistrationnumber) {
		this.cregistrationnumber = cregistrationnumber;
	}
	/**
	 * 获取 有效日期1
	 * @return ceffectivedate1
	 */
	public Date getCeffectivedate1() {
		return ceffectivedate1;
	}
	/**
	 * 设置 有效日期1
	 * @param ceffectivedate1 有效日期1
	 */
	public void setCeffectivedate1(Date ceffectivedate1) {
		this.ceffectivedate1 = ceffectivedate1;
	}
	/**
	 * 获取 有效日期2
	 * @return ceffectivedate2
	 */
	public Date getCeffectivedate2() {
		return ceffectivedate2;
	}
	/**
	 * 设置 有效日期2
	 * @param ceffectivedate2 有效日期2
	 */
	public void setCeffectivedate2(Date ceffectivedate2) {
		this.ceffectivedate2 = ceffectivedate2;
	}
	
	/**
	 * 获取 mac编号数量
	 * @return macNum
	 */
	public int getMacNum() {
		return macNum;
	}
	/**
	 * 设置 mac编号数量
	 * @param macNum mac编号数量
	 */
	public void setMacNum(int macNum) {
		this.macNum = macNum;
	}
	
	/**
	 * 获取 营业执照图片
	 * @return the licenseUrl
	 */
	public String getLicenseUrl() {
		return licenseUrl;
	}
	/**
	 * 设置 营业执照图片
	 * @param licenseUrl the licenseUrl to set
	 */
	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}
	/**
	 * 获取 卫生许可证图片
	 * @return the chygienelicenseUrl
	 */
	public String getChygienelicenseUrl() {
		return chygienelicenseUrl;
	}
	/**
	 * 设置 卫生许可证图片
	 * @param chygienelicenseUrl the chygienelicenseUrl to set
	 */
	public void setChygienelicenseUrl(String chygienelicenseUrl) {
		this.chygienelicenseUrl = chygienelicenseUrl;
	}
	
	/**
	 * 获取 企业简介
	 * @return the presentation
	 */
	public String getPresentation() {
		return presentation;
	}
	/**
	 * 设置 企业简介
	 * @param presentation the presentation to set
	 */
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	@Override
	public String toString() {
		return "Company [companyid=" + companyid + ", name=" + name
				+ ", shortname=" + shortname + ", code=" + code + ", address="
				+ address + ", contact=" + contact + ", email=" + email
				+ ", tel=" + tel + ", license=" + license + ", foodSafetyCode="
				+ foodSafetyCode + ", orgcode=" + orgcode + ", companyfieldid="
				+ companyfieldid + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", status=" + status + ", cname=" + cname
				+ ", cnature=" + cnature + ", cnatureName=" + cnatureName
				+ ", cidnumb=" + cidnumb + ", ccustomercategories="
				+ ccustomercategories + ", cbusinessscope=" + cbusinessscope
				+ ", cliquorbusinesslicense=" + cliquorbusinesslicense
				+ ", cbusinessaddress=" + cbusinessaddress
				+ ", chygienelicense=" + chygienelicense
				+ ", clegalpersonaddress=" + clegalpersonaddress
				+ ", cbusinesslicense=" + cbusinesslicense
				+ ", cregistrationnumber=" + cregistrationnumber
				+ ", ceffectivedate1=" + ceffectivedate1 + ", ceffectivedate2="
				+ ceffectivedate2 + "]";
	}

    


}
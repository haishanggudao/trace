package cn.rfidcer.bean.fruit;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.BaseEntity;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
* @Title: FruitCompany.java 
* @Package cn.rfidcer.bean.fruit 
* @Description: POJO 水果协会的企业信息
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月23日 下午3:13:29 
* @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_company")
public class FruitCompany extends BaseEntity {
	/**企业ID*/
	@Id
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
    @Transient
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
     * 企业宣传
     */
    private String presentation;
    /**
     * 企业图片
     */
    private String imageUrl;
    
    /**#产品图片文件 可能无用*/
    @Transient
	private MultipartFile companyImgfile;
	
	/**
	 * 获取 企业ID
	 * @return the companyid
	 */
	public String getCompanyid() {
		return companyid;
	}
	/**
	 * 设置 企业ID
	 * @param companyid the companyid to set
	 */
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	/**
	 * 获取 企业名称
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置 企业名称
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取 企业简称
	 * @return the shortname
	 */
	public String getShortname() {
		return shortname;
	}
	/**
	 * 设置 企业简称
	 * @param shortname the shortname to set
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	/**
	 * 获取 企业编码
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置 企业编码
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取 企业地址
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置 企业地址
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取 联系人
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 设置 联系人
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取 电子邮件
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置 电子邮件
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取 电话
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置 电话
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取 营业执照注册号
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * 设置 营业执照注册号
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * 获取 食品安全许可证号
	 * @return the foodSafetyCode
	 */
	public String getFoodSafetyCode() {
		return foodSafetyCode;
	}
	/**
	 * 设置 食品安全许可证号
	 * @param foodSafetyCode the foodSafetyCode to set
	 */
	public void setFoodSafetyCode(String foodSafetyCode) {
		this.foodSafetyCode = foodSafetyCode;
	}
	/**
	 * 获取 组织机构代码
	 * @return the orgcode
	 */
	public String getOrgcode() {
		return orgcode;
	}
	/**
	 * 设置 组织机构代码
	 * @param orgcode the orgcode to set
	 */
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	/**
	 * 获取 所属领域
	 * @return the companyfieldid
	 */
	public String getCompanyfieldid() {
		return companyfieldid;
	}
	/**
	 * 设置 所属领域
	 * @param companyfieldid the companyfieldid to set
	 */
	public void setCompanyfieldid(String companyfieldid) {
		this.companyfieldid = companyfieldid;
	}
	/**
	 * 获取 省
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置 省
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取 市
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置 市
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取 区
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置 区
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取 状态：0-启用，1-禁用
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 状态：0-启用，1-禁用
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取 名称
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置 名称
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取 性质
	 * @return the cnature
	 */
	public String getCnature() {
		return cnature;
	}
	/**
	 * 设置 性质
	 * @param cnature the cnature to set
	 */
	public void setCnature(String cnature) {
		this.cnature = cnature;
	}
	/**
	 * 获取 性质名称：非数据库字段
	 * @return the cnatureName
	 */
	public String getCnatureName() {
		return cnatureName;
	}
	/**
	 * 设置 性质名称：非数据库字段
	 * @param cnatureName the cnatureName to set
	 */
	public void setCnatureName(String cnatureName) {
		this.cnatureName = cnatureName;
	}
	/**
	 * 获取 身份证号
	 * @return the cidnumb
	 */
	public String getCidnumb() {
		return cidnumb;
	}
	/**
	 * 设置 身份证号
	 * @param cidnumb the cidnumb to set
	 */
	public void setCidnumb(String cidnumb) {
		this.cidnumb = cidnumb;
	}
	/**
	 * 获取 客户类别
	 * @return the ccustomercategories
	 */
	public String getCcustomercategories() {
		return ccustomercategories;
	}
	/**
	 * 设置 客户类别
	 * @param ccustomercategories the ccustomercategories to set
	 */
	public void setCcustomercategories(String ccustomercategories) {
		this.ccustomercategories = ccustomercategories;
	}
	/**
	 * 获取 经营范围
	 * @return the cbusinessscope
	 */
	public String getCbusinessscope() {
		return cbusinessscope;
	}
	/**
	 * 设置 经营范围
	 * @param cbusinessscope the cbusinessscope to set
	 */
	public void setCbusinessscope(String cbusinessscope) {
		this.cbusinessscope = cbusinessscope;
	}
	/**
	 * 获取 酒类经营许可证
	 * @return the cliquorbusinesslicense
	 */
	public String getCliquorbusinesslicense() {
		return cliquorbusinesslicense;
	}
	/**
	 * 设置 酒类经营许可证
	 * @param cliquorbusinesslicense the cliquorbusinesslicense to set
	 */
	public void setCliquorbusinesslicense(String cliquorbusinesslicense) {
		this.cliquorbusinesslicense = cliquorbusinesslicense;
	}
	/**
	 * 获取 经营地址
	 * @return the cbusinessaddress
	 */
	public String getCbusinessaddress() {
		return cbusinessaddress;
	}
	/**
	 * 设置 经营地址
	 * @param cbusinessaddress the cbusinessaddress to set
	 */
	public void setCbusinessaddress(String cbusinessaddress) {
		this.cbusinessaddress = cbusinessaddress;
	}
	/**
	 * 获取 卫生许可证
	 * @return the chygienelicense
	 */
	public String getChygienelicense() {
		return chygienelicense;
	}
	/**
	 * 设置 卫生许可证
	 * @param chygienelicense the chygienelicense to set
	 */
	public void setChygienelicense(String chygienelicense) {
		this.chygienelicense = chygienelicense;
	}
	/**
	 * 获取 法人地址
	 * @return the clegalpersonaddress
	 */
	public String getClegalpersonaddress() {
		return clegalpersonaddress;
	}
	/**
	 * 设置 法人地址
	 * @param clegalpersonaddress the clegalpersonaddress to set
	 */
	public void setClegalpersonaddress(String clegalpersonaddress) {
		this.clegalpersonaddress = clegalpersonaddress;
	}
	/**
	 * 获取 营业执照
	 * @return the cbusinesslicense
	 */
	public String getCbusinesslicense() {
		return cbusinesslicense;
	}
	/**
	 * 设置 营业执照
	 * @param cbusinesslicense the cbusinesslicense to set
	 */
	public void setCbusinesslicense(String cbusinesslicense) {
		this.cbusinesslicense = cbusinesslicense;
	}
	/**
	 * 获取 工商注册号
	 * @return the cregistrationnumber
	 */
	public String getCregistrationnumber() {
		return cregistrationnumber;
	}
	/**
	 * 设置 工商注册号
	 * @param cregistrationnumber the cregistrationnumber to set
	 */
	public void setCregistrationnumber(String cregistrationnumber) {
		this.cregistrationnumber = cregistrationnumber;
	}
	/**
	 * 获取 有效日期1
	 * @return the ceffectivedate1
	 */
	public Date getCeffectivedate1() {
		return ceffectivedate1;
	}
	/**
	 * 设置 有效日期1
	 * @param ceffectivedate1 the ceffectivedate1 to set
	 */
	public void setCeffectivedate1(Date ceffectivedate1) {
		this.ceffectivedate1 = ceffectivedate1;
	}
	/**
	 * 获取 有效日期2
	 * @return the ceffectivedate2
	 */
	public Date getCeffectivedate2() {
		return ceffectivedate2;
	}
	/**
	 * 设置 有效日期2
	 * @param ceffectivedate2 the ceffectivedate2 to set
	 */
	public void setCeffectivedate2(Date ceffectivedate2) {
		this.ceffectivedate2 = ceffectivedate2;
	}
	/**
	 * 获取 企业宣传
	 * @return the presentation
	 */
	public String getPresentation() {
		return presentation;
	}
	/**
	 * 设置 企业宣传
	 * @param presentation the presentation to set
	 */
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	/**
	 * 获取 企业图片
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * 设置 企业图片
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	 
	/**
	 * 获取 #产品图片文件 可能无用
	 * @return the companyImgfile
	 */
	public MultipartFile getCompanyImgfile() {
		return companyImgfile;
	}
	/**
	 * 设置 #产品图片文件 可能无用
	 * @param companyImgfile the companyImgfile to set
	 */
	public void setCompanyImgfile(MultipartFile companyImgfile) {
		this.companyImgfile = companyImgfile;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FruitCompany [companyid=" + companyid + ", name=" + name + ", shortname=" + shortname + ", code=" + code
				+ ", address=" + address + ", contact=" + contact + ", email=" + email + ", tel=" + tel + ", license="
				+ license + ", foodSafetyCode=" + foodSafetyCode + ", orgcode=" + orgcode + ", companyfieldid="
				+ companyfieldid + ", province=" + province + ", city=" + city + ", area=" + area + ", status=" + status
				+ ", cname=" + cname + ", cnature=" + cnature + ", cnatureName=" + cnatureName + ", cidnumb=" + cidnumb
				+ ", ccustomercategories=" + ccustomercategories + ", cbusinessscope=" + cbusinessscope
				+ ", cliquorbusinesslicense=" + cliquorbusinesslicense + ", cbusinessaddress=" + cbusinessaddress
				+ ", chygienelicense=" + chygienelicense + ", clegalpersonaddress=" + clegalpersonaddress
				+ ", cbusinesslicense=" + cbusinesslicense + ", cregistrationnumber=" + cregistrationnumber
				+ ", ceffectivedate1=" + ceffectivedate1 + ", ceffectivedate2=" + ceffectivedate2 + ", presentation="
				+ presentation + ", imageUrl=" + imageUrl + "]";
	}
    
}

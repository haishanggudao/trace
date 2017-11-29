package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
* @Description:加工者
* @author 席志明
* @Copyright Copyright
* @date 2016年10月26日 下午2:18:23 
* @version V1.0   
*/
@Table(name="t_processor")
@NameStyle(Style.normal)
public class Processor {

	/**
	 * ID
	 */
	@Id
	private String id;
	/**
	 * 加工者姓名
	 */
	private String name;
	
	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 健康证图片地址
	 */
	private String healthCard;
	
	/**#健康证图片文件*/
	@Transient
	private MultipartFile healthCardImgfile;
	
	
	/**获取#健康证图片文件
	 * @return the #健康证图片文件
	 */
	public MultipartFile getHealthCardImgfile() {
		return healthCardImgfile;
	}
	/**设置#健康证图片文件
	 * @param healthCardImgfile the #健康证图片文件 to set
	 */
	public void setHealthCardImgfile(MultipartFile healthCardImgfile) {
		this.healthCardImgfile = healthCardImgfile;
	}
	/**获取ID
	 * @return the ID
	 */
	public String getId() {
		return id;
	}
	/**设置ID
	 * @param id the ID to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**获取加工者姓名
	 * @return the 加工者姓名
	 */
	public String getName() {
		return name;
	}
	/**设置加工者姓名
	 * @param name the 加工者姓名 to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**获取健康证图片地址
	 * @return the 健康证图片地址
	 */
	public String getHealthCard() {
		return healthCard;
	}
	/**设置健康证图片地址
	 * @param healthCard the 健康证图片地址 to set
	 */
	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
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
	
	@Override
	public String toString() {
		return "Processor [id=" + id + ", name=" + name + ", companyId="
				+ companyId + ", healthCard=" + healthCard + "]";
	}
	
}

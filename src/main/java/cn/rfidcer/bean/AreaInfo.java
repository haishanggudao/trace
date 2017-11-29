package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
  * @Title: 地区表
  * @author jgx
  * @date 2016年6月24日 下午3:11:37 
  * @Copyright Copyright
  * @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="sys_area_info")
public class AreaInfo extends BaseEntity{
	/**地区ID*/
	@Id
	private String id;
	/**地区名称*/
	private String cityName;
	/**地区全名*/
	private String catgName;
	/**地区全名（英文）*/
	private String catgNameEn;
	/**省*/
	private String province;
	/**市*/
	private String city;
	/**区*/
	private String area;
	/**
	 * 获取 地区ID
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置 地区ID
	 * @param id 地区ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取 地区名称
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置 地区名称
	 * @param cityName 地区名称
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取 地区全名
	 * @return catgName
	 */
	public String getCatgName() {
		return catgName;
	}
	/**
	 * 设置 地区全名
	 * @param catgName 地区全名
	 */
	public void setCatgName(String catgName) {
		this.catgName = catgName;
	}
	/**
	 * 获取 地区全名（英文）
	 * @return catgNameEn
	 */
	public String getCatgNameEn() {
		return catgNameEn;
	}
	/**
	 * 设置 地区全名（英文）
	 * @param catgNameEn 地区全名（英文）
	 */
	public void setCatgNameEn(String catgNameEn) {
		this.catgNameEn = catgNameEn;
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

	@Override
	public String toString() {
		return "AreaInfo [id=" + id + ", cityName=" + cityName + ", catgName="
				+ catgName + ", catgNameEn=" + catgNameEn + ", province="
				+ province + ", city=" + city + ", area=" + area + "]";
	}


}
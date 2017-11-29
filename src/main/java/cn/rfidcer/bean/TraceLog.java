package cn.rfidcer.bean;

import java.util.Date;

/**   
  * @Title: 追溯日志表
  * @author jgx
  * @date 2016年6月29日 上午9:22:14 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/

public class TraceLog {
	/**IP地址*/
    private String traceLogId;
    /**IP地址*/
    private String IPAddress;
    /**浏览器内核*/
    private String kernel;
    /**国家*/
    private String country;
    /**省*/
    private String province;
    /**市*/
    private String city;
    /**区*/
    private String area;
    /**运营商*/
    private String isp;
    /**创建时间*/
    private Date createTime;
    
    /**
     * 二维码
     */
    private String qrcode;
	/**
	 * 获取 IP地址
	 * @return traceLogId
	 */
	public String getTraceLogId() {
		return traceLogId;
	}
	/**
	 * 设置 IP地址
	 * @param traceLogId IP地址
	 */
	public void setTraceLogId(String traceLogId) {
		this.traceLogId = traceLogId;
	}
	/**
	 * 获取 IP地址
	 * @return iPAddress
	 */
	public String getIPAddress() {
		return IPAddress;
	}
	/**
	 * 设置 IP地址
	 * @param iPAddress IP地址
	 */
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	/**
	 * 获取 浏览器内核
	 * @return kernel
	 */
	public String getKernel() {
		return kernel;
	}
	/**
	 * 设置 浏览器内核
	 * @param kernel 浏览器内核
	 */
	public void setKernel(String kernel) {
		this.kernel = kernel;
	}
	/**
	 * 获取 国家
	 * @return country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置 国家
	 * @param country 国家
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * 获取 运营商
	 * @return isp
	 */
	public String getIsp() {
		return isp;
	}
	/**
	 * 设置 运营商
	 * @param isp 运营商
	 */
	public void setIsp(String isp) {
		this.isp = isp;
	}
	/**
	 * 获取 创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**获取二维码
	 * @return the 二维码
	 */
	public String getQrcode() {
		return qrcode;
	}
	/**设置二维码
	 * @param qrcode the 二维码 to set
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	@Override
	public String toString() {
		return "TraceLog [traceLogId=" + traceLogId + ", IPAddress=" + IPAddress
				+ ", kernel=" + kernel + ", country=" + country + ", province="
				+ province + ", city=" + city + ", area=" + area + ", isp="
				+ isp + ", createTime=" + createTime + "]";
	}

}
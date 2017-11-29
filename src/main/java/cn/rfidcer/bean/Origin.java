package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
 * @Title: 源头信息表
 * @author zt
 * @date 2017年9月29日 上午10:31:17 
 * @Copyright Copyright
 * @version V1.0   
*/
@Table(name="t_origin")
@NameStyle(Style.normal)
@JsonInclude(value=Include.NON_NULL)
public class Origin extends BaseEntity {
	
	/**源头ID*/
	@Id
	private String originId;
	/**企业ID*/
	private String companyId;
	/**渔船名称*/
	private String originName;
	/**舷号*/
	private String originNo;
	/**坐标*/
	private String coordinate;
	/**联系人*/
	private String contact;
	/**电话*/
	private String tel;
	/**地址*/
	private String address;
	/**备注*/
	private String remark;
	/**状态*/
	private String status;
	/**是否删除*/
	private String isDeleted;
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getOriginNo() {
		return originNo;
	}
	public void setOriginNo(String originNo) {
		this.originNo = originNo;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "Origin [originId=" + originId + ", originName=" + originName + ", originNo=" + originNo
				+ ", coordinate=" + coordinate + ", contact=" + contact + ", tel=" + tel + ", address=" + address
				+ ", remark=" + remark + ", status=" + status + "]";
	}
	
	


}

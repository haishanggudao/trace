package cn.rfidcer.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**   
  * @Title: 包装绑定信息
  * @author jie.jia
  * @date 2016年6月27日 下午6:51:14 
  * @Copyright Copyright
  * @version V1.0   
*/
public class PackageMain extends BaseEntity {
	/**包装单ID*/
	private String packageMainId;
	/**包装编码*/
	private String packageCode;
	/**包装时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date packageTime;
	/**内部流通节点ID*/
	private String transNodeInteriorId;
	/**包装类型（0表示与商品关联，1表示包装关联）*/
	private String packageType;
	/**父包装ID*/
	private String parentPackageMainId; // 父包装ID
	/**操作者*/
	private String operator;
	/**包装的商品明细*/
	private List<PackageGoodsItem> packageGoodsItems;
	/**操作者信息*/
	private User user;
	/**父包装编码*/
	private String parentPackageCode;
	/**
	 * 获取 包装单ID
	 * @return packageMainId
	 */
	public String getPackageMainId() {
		return packageMainId;
	}
	/**
	 * 设置 包装单ID
	 * @param packageMainId 包装单ID
	 */
	public void setPackageMainId(String packageMainId) {
		this.packageMainId = packageMainId;
	}
	/**
	 * 获取 包装编码
	 * @return packageCode
	 */
	public String getPackageCode() {
		return packageCode;
	}
	/**
	 * 设置 包装编码
	 * @param packageCode 包装编码
	 */
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	/**
	 * 获取 包装时间
	 * @return packageTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getPackageTime() {
		return packageTime;
	}
	/**
	 * 设置 包装时间
	 * @param packageTime 包装时间
	 */
	public void setPackageTime(Date packageTime) {
		this.packageTime = packageTime;
	}
	/**
	 * 获取 内部流通节点ID
	 * @return transNodeInteriorId
	 */
	public String getTransNodeInteriorId() {
		return transNodeInteriorId;
	}
	/**
	 * 设置 内部流通节点ID
	 * @param transNodeInteriorId 内部流通节点ID
	 */
	public void setTransNodeInteriorId(String transNodeInteriorId) {
		this.transNodeInteriorId = transNodeInteriorId;
	}
	/**
	 * 获取 包装类型（0表示与商品关联，1表示包装关联）
	 * @return packageType
	 */
	public String getPackageType() {
		return packageType;
	}
	/**
	 * 设置 包装类型（0表示与商品关联，1表示包装关联）
	 * @param packageType 包装类型（0表示与商品关联，1表示包装关联）
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	/**
	 * 获取 父包装ID
	 * @return parentPackageMainId
	 */
	public String getParentPackageMainId() {
		return parentPackageMainId;
	}
	/**
	 * 设置 父包装ID
	 * @param parentPackageMainId 父包装ID
	 */
	public void setParentPackageMainId(String parentPackageMainId) {
		this.parentPackageMainId = parentPackageMainId;
	}
	/**
	 * 获取 操作者
	 * @return operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置 操作者
	 * @param operator 操作者
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取 包装的商品明细
	 * @return packageGoodsItems
	 */
	public List<PackageGoodsItem> getPackageGoodsItems() {
		return packageGoodsItems;
	}
	/**
	 * 设置 包装的商品明细
	 * @param packageGoodsItems 包装的商品明细
	 */
	public void setPackageGoodsItems(List<PackageGoodsItem> packageGoodsItems) {
		this.packageGoodsItems = packageGoodsItems;
	}
	/**
	 * 获取 操作者信息
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * 设置 操作者信息
	 * @param user 操作者信息
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 获取 父包装编码
	 * @return parentPackageCode
	 */
	public String getParentPackageCode() {
		return parentPackageCode;
	}
	/**
	 * 设置 父包装编码
	 * @param parentPackageCode 父包装编码
	 */
	public void setParentPackageCode(String parentPackageCode) {
		this.parentPackageCode = parentPackageCode;
	}
	
	@Override
	public String toString() {
		return "PackageMain [packageMainId=" + packageMainId + ", packageCode="
				+ packageCode + ", packageTime=" + packageTime
				+ ", transNodeInteriorId=" + transNodeInteriorId
				+ ", packageType=" + packageType + ", parentPackageMainId="
				+ parentPackageMainId + ", operator=" + operator
				+ ", packageGoodsItems=" + packageGoodsItems + ", user=" + user
				+ ", parentPackageCode=" + parentPackageCode + "]";
	}


}
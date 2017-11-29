package cn.rfidcer.bean.yz;

import com.fasterxml.jackson.annotation.JsonRootName;

/**   
* @Title: WsSaleDetail.java 
* @Package cn.rfidcer.bean.yz 
* @Description: iOS-WS-POJO 销售单明细
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月29日 下午2:07:37 
* @version V1.0   
*/
@JsonRootName("SaleDetailList") 
public class WsSaleDetail {
	
	/**
	 * GUID
	 */
	private String guid;
	/**
	 * 销售单号
	 */
	private String saleNo;
	/**
	 * 商品ID
	 */
	private String goodsID;
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 销售价格
	 */
	private double salePrice;
	/**
	 * 销售数量
	 */
	private double saleNumber;
	/**
	 * 合计销售金额
	 */
	private double amount;
	/**
	 * 销售序号
	 */
	private int displayNO;
	/**
	 * 单位名称
	 */
	private String unitName;
	
	/**
	 * 最后更新的操作者
	 */
	private String updateUser;
	/**
	 * 最后更新时间
	 */
	private String updateTime;
	/**
	 * 创建的操作者
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 获取 GUID
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * 设置 GUID
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * 获取 销售单号
	 * @return the saleNo
	 */
	public String getSaleNo() {
		return saleNo;
	}
	/**
	 * 设置 销售单号
	 * @param saleNo the saleNo to set
	 */
	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}
	/**
	 * 获取 商品ID
	 * @return the goodsID
	 */
	public String getGoodsID() {
		return goodsID;
	}
	/**
	 * 设置 商品ID
	 * @param goodsID the goodsID to set
	 */
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	/**
	 * 获取 商品名称
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置 商品名称
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取 销售价格
	 * @return the salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置 销售价格
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取 销售数量
	 * @return the saleNumber
	 */
	public double getSaleNumber() {
		return saleNumber;
	}
	/**
	 * 设置 销售数量
	 * @param saleNumber the saleNumber to set
	 */
	public void setSaleNumber(double saleNumber) {
		this.saleNumber = saleNumber;
	}
	/**
	 * 获取 合计销售金额
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * 设置 合计销售金额
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * 获取 销售序号
	 * @return the displayNO
	 */
	public int getDisplayNO() {
		return displayNO;
	}
	/**
	 * 设置 销售序号
	 * @param displayNO the displayNO to set
	 */
	public void setDisplayNO(int displayNO) {
		this.displayNO = displayNO;
	}
	/**
	 * 获取 单位名称
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * 设置 单位名称
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * 获取 最后更新的操作者
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置 最后更新的操作者
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取 最后更新时间
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置 最后更新时间
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取 创建的操作者
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置 创建的操作者
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取 创建时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 创建时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WsSaleDetail [guid=" + guid + ", saleNo=" + saleNo + ", goodsID=" + goodsID + ", goodsName=" + goodsName
				+ ", salePrice=" + salePrice + ", saleNumber=" + saleNumber + ", amount=" + amount + ", displayNO="
				+ displayNO + ", unitName=" + unitName + ", updateUser=" + updateUser + ", updateTime=" + updateTime
				+ ", createUser=" + createUser + ", createTime=" + createTime + "]";
	}
	
	

}

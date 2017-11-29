package cn.rfidcer.bean.yz;

public class YzStoreSale {

	/**
	 * 店铺ID
	 */
	private String storeID;
	/**
	 * 店铺名称
	 */
	private String storeName;
	/**
	 * 总销售额
	 */
	private String totalSaleMoney="0.00";
	/**获取店铺ID
	 * @return the 店铺ID
	 */
	public String getStoreID() {
		return storeID;
	}
	/**设置店铺ID
	 * @param storeID the 店铺ID to set
	 */
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	/**获取店铺名称
	 * @return the 店铺名称
	 */
	public String getStoreName() {
		return storeName;
	}
	/**设置店铺名称
	 * @param storeName the 店铺名称 to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**获取总销售额
	 * @return the 总销售额
	 */
	public String getTotalSaleMoney() {
		return totalSaleMoney;
	}
	/**设置总销售额
	 * @param totalSaleMoney the 总销售额 to set
	 */
	public void setTotalSaleMoney(String totalSaleMoney) {
		this.totalSaleMoney = totalSaleMoney;
	}
	
	
}

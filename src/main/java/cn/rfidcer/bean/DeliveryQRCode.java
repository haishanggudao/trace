package cn.rfidcer.bean;

import java.util.List;

/**   
  * @Title: 配送单
  * @author xzm
  * @date 2016年6月27日 上午10:31:46 
  * @Copyright Copyright
  * @version V1.0   
*/
public class DeliveryQRCode {
	/**配送单二维码*/
	private String deliveryQRCode;
	/**门店企业ID*/
	private String storeCompanyId;
	/**门店名称*/
	private String storeName;
	
	/**配送商品*/
	private List<GoodQRCode> goodList;

	/**
	 * 获取 配送单二维码
	 * @return deliveryQRCode
	 */
	public String getDeliveryQRCode() {
		return deliveryQRCode;
	}

	/**
	 * 设置 配送单二维码
	 * @param deliveryQRCode 配送单二维码
	 */
	public void setDeliveryQRCode(String deliveryQRCode) {
		this.deliveryQRCode = deliveryQRCode;
	}

	/**
	 * 获取 配送商品
	 * @return goodList
	 */
	public List<GoodQRCode> getGoodList() {
		return goodList;
	}

	/**
	 * 设置 配送商品
	 * @param goodList 配送商品
	 */
	public void setGoodList(List<GoodQRCode> goodList) {
		this.goodList = goodList;
	}

	/**
	 * 获取 门店企业ID
	 * @return storeCompanyId
	 */
	public String getStoreCompanyId() {
		return storeCompanyId;
	}

	/**
	 * 设置 门店企业ID
	 * @param storeCompanyId 门店企业ID
	 */
	public void setStoreCompanyId(String storeCompanyId) {
		this.storeCompanyId = storeCompanyId;
	}

	/**
	 * 获取 门店名称
	 * @return storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 设置 门店名称
	 * @param storeName 门店名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	@Override
	public String toString() {
		return "DeliveryQRCode [deliveryQRCode=" + deliveryQRCode
				+ ", storeCompanyId=" + storeCompanyId + ", storeName="
				+ storeName + ", goodList=" + goodList + "]";
	}



}

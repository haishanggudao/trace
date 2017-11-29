package cn.rfidcer.bean.yz;

import java.util.List;

import cn.rfidcer.bean.GoodQRCode;

/**   
  * @Title: 配送单
  * @author xzm
  * @date 2016年6月27日 上午10:31:46 
  * @Copyright Copyright
  * @version V1.0   
*/
public class StoreInstockInfo {
	/**配送单扫码*/
	private String deliveryBarCode;
	/**门店企业ID*/
	private String storeCompanyId;
	/**门店名称*/
	private String storeName;
	/**配送商品*/
	private List<GoodsDetailInfo> goodList;
	/**
	 * 获取 配送单扫码
	 * @return deliveryBarCode
	 */
	public String getDeliveryBarCode() {
		return deliveryBarCode;
	}
	/**
	 * 设置 配送单扫码
	 * @param deliveryBarCode 配送单扫码
	 */
	public void setDeliveryBarCode(String deliveryBarCode) {
		this.deliveryBarCode = deliveryBarCode;
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
	/**
	 * 获取 配送商品
	 * @return goodList
	 */
	public List<GoodsDetailInfo> getGoodList() {
		return goodList;
	}
	/**
	 * 设置 配送商品
	 * @param goodList 配送商品
	 */
	public void setGoodList(List<GoodsDetailInfo> goodList) {
		this.goodList = goodList;
	}
	
	@Override
	public String toString() {
		return "StoreInstockInfo [deliveryBarCode=" + deliveryBarCode
				+ ", storeCompanyId=" + storeCompanyId + ", storeName="
				+ storeName + ", goodList=" + goodList + "]";
	}
	
	
	
	
}

package cn.rfidcer.bean.yz;

/**   
* @Description:产品
* @author 席志明
* @date 2016年8月30日 上午11:23:05 
* @version V1.0   
*/
public class YzProductStock {

	/**
	 * 商品ID
	 */
	private String guid;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品编码
	 */
	private String goodsCode;
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 库存
	 */
	private String currentInventory;
	
	/**
	 * 单位
	 */
	private String unitName="L";
	
	/**
	 * 零售价格
	 */
	private String retailPrice;

	/**获取商品ID
	 * @return the 商品ID
	 */
	public String getGuid() {
		return guid;
	}

	/**设置商品ID
	 * @param guid the 商品ID to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**获取商品名称
	 * @return the 商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**设置商品名称
	 * @param goodsName the 商品名称 to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**获取商品编码
	 * @return the 商品编码
	 */
	public String getGoodsCode() {
		return goodsCode;
	}

	/**设置商品编码
	 * @param goodsCode the 商品编码 to set
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
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

	/**获取库存
	 * @return the 库存
	 */
	public String getCurrentInventory() {
		return currentInventory;
	}

	/**设置库存
	 * @param currentInventory the 库存 to set
	 */
	public void setCurrentInventory(String currentInventory) {
		this.currentInventory = currentInventory;
	}

	/**获取单位
	 * @return the 单位
	 */
	public String getUnitName() {
		return unitName;
	}

	/**设置单位
	 * @param unitName the 单位 to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**获取零售价格
	 * @return the 零售价格
	 */
	public String getRetailPrice() {
		return retailPrice;
	}

	/**设置零售价格
	 * @param retailPrice the 零售价格 to set
	 */
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	
}

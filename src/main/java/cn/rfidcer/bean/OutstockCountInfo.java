package cn.rfidcer.bean;

/**   
  * @Title: 手持机出库统计信息
  * @author xzm
  * @date 2016年6月27日 下午4:10:03 
  * @Copyright Copyright
  * @version V1.0   
*/
public class OutstockCountInfo {
	/**产品名称*/
	private String productName;
	/**规格明细数量*/
	private String productStandardNum;
	/**规格名称*/
	private String productStandardName;
	/**商品批次*/
	private String goodsBatch;
	/**手持机确认用户*/
	private String deliveryBy;
	/**确认数量*/
	private String totalNum;
	/**产品规格名称*/
	private String fullStandName;
	/**
	 * 获取 产品名称
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 产品名称
	 * @param productName 产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取 规格明细数量
	 * @return productStandardNum
	 */
	public String getProductStandardNum() {
		return productStandardNum;
	}
	/**
	 * 设置 规格明细数量
	 * @param productStandardNum 规格明细数量
	 */
	public void setProductStandardNum(String productStandardNum) {
		this.productStandardNum = productStandardNum;
	}
	/**
	 * 获取 规格名称
	 * @return productStandardName
	 */
	public String getProductStandardName() {
		return productStandardName;
	}
	/**
	 * 设置 规格名称
	 * @param productStandardName 规格名称
	 */
	public void setProductStandardName(String productStandardName) {
		this.productStandardName = productStandardName;
	}
	/**
	 * 获取 商品批次
	 * @return goodsBatch
	 */
	public String getGoodsBatch() {
		return goodsBatch;
	}
	/**
	 * 设置 商品批次
	 * @param goodsBatch 商品批次
	 */
	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
	}
	/**
	 * 获取 手持机确认用户
	 * @return deliveryBy
	 */
	public String getDeliveryBy() {
		return deliveryBy;
	}
	/**
	 * 设置 手持机确认用户
	 * @param deliveryBy 手持机确认用户
	 */
	public void setDeliveryBy(String deliveryBy) {
		this.deliveryBy = deliveryBy;
	}
	/**
	 * 获取 确认数量
	 * @return totalNum
	 */
	public String getTotalNum() {
		return totalNum;
	}
	/**
	 * 设置 确认数量
	 * @param totalNum 确认数量
	 */
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 获取 产品规格名称
	 * @return fullStandName
	 */
	public String getFullStandName() {
		fullStandName = productStandardNum + productStandardName;
		return fullStandName;
	}
	/**
	 * 设置 产品规格名称
	 * @param fullStandName 产品规格名称
	 */
	public void setFullStandName(String fullStandName) {
		this.fullStandName = fullStandName;
	}
	
	@Override
	public String toString() {
		return "OutstockCountInfo [productName=" + productName
				+ ", productStandardNum=" + productStandardNum
				+ ", productStandardName=" + productStandardName
				+ ", goodsBatch=" + goodsBatch + ", deliveryBy=" + deliveryBy
				+ ", totalNum=" + totalNum + ", fullStandName=" + fullStandName
				+ "]";
	}
	
	
}

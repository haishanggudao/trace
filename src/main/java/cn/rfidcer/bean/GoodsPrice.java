package cn.rfidcer.bean;
/***
* @Title: GoodsPrice.java 
* @Package cn.rfidcer.bean 
* @Description:  商品价格
* @author 具光星  
* @Copyright Copyright
* @date 2016年6月13日 上午10:22:50 
* @version V1.0
 */
public class GoodsPrice {
	/**产品ID*/
	private String productId;
	/**产品名称*/
	private String productName;
	/**产品规格ID*/
	private String productStandardId;
	/**产品规格名称*/
	private String productStandardName;
	/**产品规格明细数量*/
	private String productStandardNum;
	/**产品规格明细ID*/
	private String productStandardDetailId;

	/**
	 * 获取 产品ID
	 * @return productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置 产品ID
	 * @param productId 产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
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
	 * 获取 产品规格ID
	 * @return productStandardId
	 */
	public String getProductStandardId() {
		return productStandardId;
	}
	/**
	 * 设置 产品规格ID
	 * @param productStandardId 产品规格ID
	 */
	public void setProductStandardId(String productStandardId) {
		this.productStandardId = productStandardId;
	}
	/**
	 * 获取 产品规格名称
	 * @return productStandardName
	 */
	public String getProductStandardName() {
		return productStandardName;
	}
	/**
	 * 设置 产品规格名称
	 * @param productStandardName 产品规格名称
	 */
	public void setProductStandardName(String productStandardName) {
		this.productStandardName = productStandardName;
	}
	/**
	 * 获取 产品规格明细数量
	 * @return productStandardNum
	 */
	public String getProductStandardNum() {
		return productStandardNum;
	}
	/**
	 * 设置 产品规格明细数量
	 * @param productStandardNum 产品规格明细数量
	 */
	public void setProductStandardNum(String productStandardNum) {
		this.productStandardNum = productStandardNum;
	}
	/**
	 * 获取 产品规格明细名称 界面调用
	 * @return productStandardFullName
	 */
	public String getproductStandardFullName() {
	
		return 	this.productStandardNum  + this.productStandardName; 
	}
	/**
	 * 获取 产品规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 产品规格明细ID
	 * @param productStandardDetailId 产品规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}	
	
	

}

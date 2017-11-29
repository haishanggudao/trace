package cn.rfidcer.bean.yz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**   
* @Description:IOS客户端用户
* @author 席志明 
* @date 2016年8月25日 下午7:37:50 
* @version V1.0   
*/
@JsonInclude(value=Include.NON_NULL)
public class GoodsInfoList {
	/**商品guid*/
	private String guid;
	/**商品编码*/
	private String goodsCode;
	/**商品名称*/
	private String goodsName;
	/**销售价格*/
	private String retailPrice;
	/**销售状态*/
	private String saleState;
	/**规格名称*/
	private String unitName;
	/**是否上架*/
	private Boolean isForbidden;	
	/**
	 * 获取 商品guid
	 * @return guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * 设置 商品guid
	 * @param guid 商品guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * 获取 商品编码
	 * @return goodsCode
	 */
	public String getGoodsCode() {
		return goodsCode;
	}
	/**
	 * 设置 商品编码
	 * @param goodsCode 商品编码
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	/**
	 * 获取 商品名称
	 * @return goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置 商品名称
	 * @param goodsName 商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取 销售价格
	 * @return retailPrice
	 */
	public String getRetailPrice() {
		return retailPrice;
	}
	/**
	 * 设置 销售价格
	 * @param retailPrice 销售价格
	 */
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	/**
	 * 获取 销售状态
	 * @return saleState
	 */
	public String getSaleState() {
		return saleState;
	}
	/**
	 * 设置 销售状态
	 * @param saleState 销售状态
	 */
	public void setSaleState(String saleState) {
		this.saleState = saleState;
	}
	/**
	 * 获取 规格名称
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * 设置 规格名称
	 * @param unitName 规格名称
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * 获取 是否上架
	 * @return isForbidden
	 */
	public Boolean getIsForbidden() {
		return isForbidden;
	}
	/**
	 * 设置 是否上架
	 * @param isForbidden 是否上架
	 */
	public void setIsForbidden(Boolean isForbidden) {
		this.isForbidden = isForbidden;
	}

	
	
	
	
}

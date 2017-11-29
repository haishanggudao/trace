package cn.rfidcer.bean.yz;

import cn.rfidcer.bean.ResultEntity;

/**   
 * @Title: YzGoodsInfo.java 
 * @Package cn.rfidcer.bean.yz 
 * @Description:POS机读取商品信息 
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月5日 下午6:49:50 
 * @version V1.0   
*/
public class GoodsDetailInfo implements ResultEntity {
	/**商品ID*/
	private String goodsID;
	/**商品批次*/
	private String goodsBatch;
	/**条形码*/
	private String barCode;
	/**追溯码*/
	private String traceCode;	
	/**应收数量*/
	private String shouldIncome;
	/**进货价格*/
	private String costAmount;
	
	//无参构造器
	public GoodsDetailInfo() {}
	//有参构造器
	public GoodsDetailInfo(String goodsID, String goodsBatch, String traceCode,String barCode, String shouldIncome,String costAmount) {
		this.goodsID = goodsID;
		this.goodsBatch = goodsBatch;
		this.barCode = barCode;
		this.traceCode = traceCode;
		this.shouldIncome = shouldIncome;
		this.costAmount = costAmount;
	}	
	/**
	 * 获取 商品ID
	 * @return goodsID
	 */
	public String getGoodsID() {
		return goodsID;
	}
	/**
	 * 设置 商品ID
	 * @param goodsID 商品ID
	 */
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	
	/**
	 * 获取 追溯码
	 * @return barCode
	 */
	public String getBarCode() {
		return barCode;
	}
	/**
	 * 设置 追溯码
	 * @param barCode 追溯码
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	/**
	 * 获取 应收数量
	 * @return shouldIncome
	 */
	public String getShouldIncome() {
		return shouldIncome;
	}
	/**
	 * 设置 应收数量
	 * @param shouldIncome 应收数量
	 */
	public void setShouldIncome(String shouldIncome) {
		this.shouldIncome = shouldIncome;
	}
	/**
	 * 获取 进货价格
	 * @return costAmount
	 */
	public String getCostAmount() {
		return costAmount;
	}
	/**
	 * 设置 进货价格
	 * @param costAmount 进货价格
	 */
	public void setCostAmount(String costAmount) {
		this.costAmount = costAmount;
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
	 * 获取 追溯码
	 * @return traceCode
	 */
	public String getTraceCode() {
		return traceCode;
	}
	/**
	 * 设置 追溯码
	 * @param traceCode 追溯码
	 */
	public void setTraceCode(String traceCode) {
		this.traceCode = traceCode;
	}
	
	@Override
	public String toString() {
		return "GoodsDetailInfo [goodsID=" + goodsID + ", goodsBatch="
				+ goodsBatch + ", barCode=" + barCode + ", traceCode="
				+ traceCode + ", shouldIncome=" + shouldIncome + ", costAmount="
				+ costAmount + "]";
	}
	

	
}

package cn.rfidcer.bean.yz;

import cn.rfidcer.bean.BaseEntity;

/**   
 * @Title: YzGoodsInfo.java 
 * @Package cn.rfidcer.bean.yz 
 * @Description:POS机读取商品信息 
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月5日 下午6:49:50 
 * @version V1.0   
*/
public class YzGoodsInfo extends BaseEntity {
	/**商品ID*/
	private String GoodsID;
	/**商品批次*/
	private String ProductBatch;
	/**追溯码*/
	private String TraceCode;
	/**应收数量*/
	private String ShouldIncome;
	/**进货价格*/
	private String costAmount;
	
	
	//无参构造器
	public YzGoodsInfo() {}
	//有参构造器
	public YzGoodsInfo(String GoodsID, String ProductBatch, String TraceCode, String ShouldIncome,String costAmount) {
		this.GoodsID = GoodsID;
		this.ProductBatch = ProductBatch;
		this.TraceCode = TraceCode;
		this.ShouldIncome = ShouldIncome;
		this.costAmount = costAmount;
	}
	/**
	 * 获取 商品ID
	 * @return goodsID
	 */
	public String getGoodsID() {
		return GoodsID;
	}
	/**
	 * 设置 商品ID
	 * @param goodsID 商品ID
	 */
	public void setGoodsID(String goodsID) {
		GoodsID = goodsID;
	}
	/**
	 * 获取 商品批次
	 * @return productBatch
	 */
	public String getProductBatch() {
		return ProductBatch;
	}
	/**
	 * 设置 商品批次
	 * @param productBatch 商品批次
	 */
	public void setProductBatch(String productBatch) {
		ProductBatch = productBatch;
	}
	/**
	 * 获取 追溯码
	 * @return traceCode
	 */
	public String getTraceCode() {
		return TraceCode;
	}
	/**
	 * 设置 追溯码
	 * @param traceCode 追溯码
	 */
	public void setTraceCode(String traceCode) {
		TraceCode = traceCode;
	}
	/**
	 * 获取 应收数量
	 * @return shouldIncome
	 */
	public String getShouldIncome() {
		return ShouldIncome;
	}
	/**
	 * 设置 应收数量
	 * @param shouldIncome 应收数量
	 */
	public void setShouldIncome(String shouldIncome) {
		ShouldIncome = shouldIncome;
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

	@Override
	public String toString() {
		return "YzGoodsInfo [GoodsID=" + GoodsID + ", ProductBatch="
				+ ProductBatch + ", TraceCode=" + TraceCode + ", ShouldIncome="
				+ ShouldIncome + "]";
	}
}

package cn.rfidcer.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**   
  * @Title: 商品库存操作历史
  * @author xzm
  * @date 2016年6月27日 下午3:25:39 
  * @Copyright Copyright
  * @version V1.0   
*/
public class GoodsStockHistory {
	/**商品库存历史ID*/
	private String goodsStockHistoryId;
	/**商品ID*/
	private String goodsId;
	/**当前库存数量*/
	private BigDecimal currentNum;
	/**操作数量*/
	private BigDecimal operationNum;
	/**操作后的库存量*/
	private BigDecimal doneNum;
	/**操作类型*/
	private String operationType;
	/**变换类型*/
	private String changeType;
	/**企业ID*/
	private String companyId;
	/**创建者*/
	private String createBy;
	/**创建时间*/
	private Timestamp createTime;
	/**是否删除*/
	private String isDeleted;
	
	//无参构造器
	public GoodsStockHistory() {}
	//有参构造器
	public GoodsStockHistory(String goodsId, String operationType, String changeType, String companyId) {
		this.goodsId = goodsId;
		this.operationType = operationType;
		this.changeType = changeType;
		this.companyId = companyId;
	}
	/**
	 * 获取 商品库存历史ID
	 * @return goodsStockHistoryId
	 */
	public String getGoodsStockHistoryId() {
		return goodsStockHistoryId;
	}
	/**
	 * 设置 商品库存历史ID
	 * @param goodsStockHistoryId 商品库存历史ID
	 */
	public void setGoodsStockHistoryId(String goodsStockHistoryId) {
		this.goodsStockHistoryId = goodsStockHistoryId;
	}
	/**
	 * 获取 商品ID
	 * @return goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置 商品ID
	 * @param goodsId 商品ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取 当前库存数量
	 * @return currentNum
	 */
	public BigDecimal getCurrentNum() {
		return currentNum;
	}
	/**
	 * 设置 当前库存数量
	 * @param currentNum 当前库存数量
	 */
	public void setCurrentNum(BigDecimal currentNum) {
		this.currentNum = currentNum;
	}
	/**
	 * 获取 操作数量
	 * @return operationNum
	 */
	public BigDecimal getOperationNum() {
		return operationNum;
	}
	/**
	 * 设置 操作数量
	 * @param operationNum 操作数量
	 */
	public void setOperationNum(BigDecimal operationNum) {
		this.operationNum = operationNum;
	}
	/**
	 * 获取 操作后的库存量
	 * @return doneNum
	 */
	public BigDecimal getDoneNum() {
		return doneNum;
	}
	/**
	 * 设置 操作后的库存量
	 * @param doneNum 操作后的库存量
	 */
	public void setDoneNum(BigDecimal doneNum) {
		this.doneNum = doneNum;
	}
	/**
	 * 获取 操作类型
	 * @return operationType
	 */
	public String getOperationType() {
		return operationType;
	}
	/**
	 * 设置 操作类型
	 * @param operationType 操作类型
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	/**
	 * 获取 变换类型
	 * @return changeType
	 */
	public String getChangeType() {
		return changeType;
	}
	/**
	 * 设置 变换类型
	 * @param changeType 变换类型
	 */
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	/**
	 * 获取 企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 企业ID
	 * @param companyId 企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 创建者
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置 创建者
	 * @param createBy 创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取 创建时间
	 * @return createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取 是否删除
	 * @return isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置 是否删除
	 * @param isDeleted 是否删除
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "GoodsStockHistory [goodsStockHistoryId=" + goodsStockHistoryId
				+ ", goodsId=" + goodsId + ", currentNum=" + currentNum
				+ ", operationNum=" + operationNum + ", doneNum=" + doneNum
				+ ", operationType=" + operationType + ", changeType="
				+ changeType + ", companyId=" + companyId + ", createBy="
				+ createBy + ", createTime=" + createTime + ", isDeleted="
				+ isDeleted + "]";
	}
	
}

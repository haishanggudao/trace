package cn.rfidcer.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**   
  * @Title: 产品库存操作历史
  * @author jgx
  * @date 2016年6月27日 下午8:06:10 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class ProductStockHistory {
	/**产品库存历史ID*/
	private String productStockHistoryId;
	/**产品ID*/
	private String productId;
	/**产品规格明细ID*/
	private String productStandardDetailId;
	/**当前库存数量*/
	private BigDecimal currentNum;
	/**操作库存数量*/
	private BigDecimal operationNum;
	/**操作后的库存量*/
	private BigDecimal doneNum;
	/**操作类型*/
	private String operationType;
	/**变化类型*/
	private String changeType;
	/**企业ID*/
	private String companyId;
	/**是否删除*/
	private String isDeleted;
	/**创建者*/
	private String createBy;
	/**创建日期*/
	private Timestamp createTime;
	
	//无参构造器
	public ProductStockHistory() {}
	//有参构造器
	public ProductStockHistory(String productId, String productStandardDetailId, String operationType, String changeType, String companyId) {
		this.productId = productId;
		this.productStandardDetailId = productStandardDetailId;
		this.operationType = operationType;
		this.changeType = changeType;
		this.companyId = companyId;
	}
	/**
	 * 获取 产品库存历史ID
	 * @return productStockHistoryId
	 */
	public String getProductStockHistoryId() {
		return productStockHistoryId;
	}
	/**
	 * 设置 产品库存历史ID
	 * @param productStockHistoryId 产品库存历史ID
	 */
	public void setProductStockHistoryId(String productStockHistoryId) {
		this.productStockHistoryId = productStockHistoryId;
	}
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
	 * 获取 操作库存数量
	 * @return operationNum
	 */
	public BigDecimal getOperationNum() {
		return operationNum;
	}
	/**
	 * 设置 操作库存数量
	 * @param operationNum 操作库存数量
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
	 * 获取 变化类型
	 * @return changeType
	 */
	public String getChangeType() {
		return changeType;
	}
	/**
	 * 设置 变化类型
	 * @param changeType 变化类型
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
	 * 获取 创建日期
	 * @return createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 创建日期
	 * @param createTime 创建日期
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "ProductStockHistory [productStockHistoryId="
				+ productStockHistoryId + ", productId=" + productId
				+ ", productStandardDetailId=" + productStandardDetailId
				+ ", currentNum=" + currentNum + ", operationNum="
				+ operationNum + ", doneNum=" + doneNum + ", operationType="
				+ operationType + ", changeType=" + changeType + ", companyId="
				+ companyId + ", isDeleted=" + isDeleted + ", createBy="
				+ createBy + ", createTime=" + createTime + "]";
	}
	
	
}

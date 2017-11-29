package cn.rfidcer.bean;

import java.sql.Timestamp;
/**   
  * @Title: 基础实体类
  * @author xzm
  * @date 2016年6月27日 上午10:33:32 
  * @Copyright Copyright
  * @version V1.0   
*/
public class BaseEntity {
	/**创建时间*/
	private Timestamp createTime;
	/**创建用户ID*/
	private String createBy;
	/**最后更新时间*/
	private Timestamp updateTime;
	/**更新用户ID*/
	private String updateBy;
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
	 * 获取 创建用户ID
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置 创建用户ID
	 * @param createBy 创建用户ID
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取 最后更新时间
	 * @return updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置 最后更新时间
	 * @param updateTime 最后更新时间
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取 更新用户ID
	 * @return updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置 更新用户ID
	 * @param updateBy 更新用户ID
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
}

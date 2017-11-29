package cn.rfidcer.bean;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   追溯码类型
* @Title: Trace.java 
* @author 席志明
* @date 2016年8月3日 下午3:16:27 
* @version V1.0   
*/
@NameStyle(Style.normal)
@Table(name="t_trace")
public class Trace {

	public Trace() {}
	public Trace(String traceCode,String createBy) {
		this.traceCode=traceCode;
		this.createBy=createBy;
		this.createTime=new Timestamp(System.currentTimeMillis());
	}
	/**
	 * 追溯码
	 */
	@Id
	private String traceCode;
	/**
	 * 追溯码类型
	 * <p>0：商品追溯码</p>
	 * <p>1：商品明细追溯码</p>
	 * <p>2：团餐追溯码</p>
	 * <p>3：销售单商品追溯码</p>
	 */
	private int type;
	/**
	 * 创建人 
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**获取追溯码
	 * @return the 追溯码
	 */
	public String getTraceCode() {
		return traceCode;
	}
	/**设置追溯码
	 * @param traceCode the 追溯码 to set
	 */
	public void setTraceCode(String traceCode) {
		this.traceCode = traceCode;
	}
	/**获取追溯码类型  
	 * @return the 追溯码类型  <p>0：商品追溯码<p>  <p>1：商品明细追溯码<p>  <p>2：团餐追溯码<p>  <p>3：销售单商品追溯码<p>
	 */
	public int getType() {
		return type;
	}
	/**设置追溯码类型  <p>0：商品追溯码<p>  <p>1：商品明细追溯码<p>  <p>2：团餐追溯码<p>  <p>3：销售单商品追溯码<p>
	 * @param type the 追溯码类型  <p>0：商品追溯码<p>  <p>1：商品明细追溯码<p>  <p>2：团餐追溯码<p>  <p>3：销售单商品追溯码<p> to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**获取创建人
	 * @return the 创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**设置创建人
	 * @param createBy the 创建人 to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**获取创建时间
	 * @return the 创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**设置创建时间
	 * @param createTime the 创建时间 to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}

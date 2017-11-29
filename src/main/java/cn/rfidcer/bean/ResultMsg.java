package cn.rfidcer.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
  * @Title: #消息结果集
  * @author xzm
  * @date 2016年6月28日 下午2:08:05 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
@JsonInclude(value = Include.NON_NULL)
public class ResultMsg {
	/**成功*/
	public static final String SUCCESS = "1";
	/**失败*/
	public static final String FAILURE = "0";
	/**编码*/
	private String code;
	/**消息内容*/
	private String msg;
	/**消息时间*/
	private String time;
	/**消息时间*/
	private BaseEntity baseEntity;
	/**返回类*/
	private ResultEntity resultEntity;
	/**
	 * 获取 编码
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 编码
	 * @param code 编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取 消息内容
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置 消息内容
	 * @param msg 消息内容
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 获取 消息时间
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 设置 消息时间
	 * @param time 消息时间
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 获取 消息时间
	 * @return baseEntity
	 */
	public BaseEntity getBaseEntity() {
		return baseEntity;
	}

	/**
	 * 设置 消息时间
	 * @param baseEntity 消息时间
	 */
	public void setBaseEntity(BaseEntity baseEntity) {
		this.baseEntity = baseEntity;
	}

	/**
	 * 获取 成功
	 * @return success
	 */
	public static String getSuccess() {
		return SUCCESS;
	}

	/**
	 * 获取 失败
	 * @return failure
	 */
	public static String getFailure() {
		return FAILURE;
	}

	/**
	 * 获取 返回类
	 * @return resultEntity
	 */
	public ResultEntity getResultEntity() {
		return resultEntity;
	}

	/**
	 * 设置 返回类
	 * @param resultEntity 返回类
	 */
	public void setResultEntity(ResultEntity resultEntity) {
		this.resultEntity = resultEntity;
	}

	@Override
	public String toString() {
		return "ResultMsg [code=" + code + ", msg=" + msg + ", time=" + time
				+ ", baseEntity=" + baseEntity + ", resultEntity="
				+ resultEntity + "]";
	}


}

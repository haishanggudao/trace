package cn.rfidcer.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
* @Title: ResponseData.java 
* @Package cn.rfidcer.dto 
* @Description:IOS返回数据
* @author 席志明
* @Copyright Copyright
* @date 2016年8月25日 下午7:12:29 
* @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class ResponseData<T> {

	/**
	 * 结果
	 */
	@ApiModelProperty(value = "结果",notes="1表示成功",required=true) 
	private int result;
	/**
	 * 请求时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "请求时间",required=true)
	private Date synTime;
	/**
	 * 数据
	 */
	@ApiModelProperty(value = "返回的真正的数据",notes="登录成功时才有值")
	private Data<T> value;
	/**
	 * 错误
	 */
	@ApiModelProperty(value = "错误信息")
	private String error;
	/**获取结果
	 * @return the 结果
	 */
	public int getResult() {
		return result;
	}
	/**设置结果
	 * @param result the 结果 to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**获取请求时间
	 * @return the 请求时间
	 */
	public Date getSynTime() {
		return synTime;
	}
	/**设置请求时间
	 * @param synTime the 请求时间 to set
	 */
	public void setSynTime(Date synTime) {
		this.synTime = synTime;
	}
	
	
	/**获取数据
	 * @return the 数据
	 */
	public Data<T> getValue() {
		return value;
	}
	/**设置数据
	 * @param value the 数据 to set
	 */
	public void setValue(Data<T> value) {
		this.value = value;
	}
	/**获取错误
	 * @return the 错误
	 */
	public String getError() {
		return error;
	}
	/**设置错误
	 * @param error the 错误 to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	
}

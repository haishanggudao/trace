package cn.rfidcer.bean.yz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**   
* @Description:IOS客户端用户
* @author 席志明 
* @date 2016年8月25日 下午7:37:50 
* @version V1.0   
*/
@JsonInclude(value=Include.NON_NULL)
@ApiModel(description="用户",value="sysUser")
public class SysUser {

	/**
	 * 企业ID
	 */
	private String guid;
	
	/**
	 * 用户编号
	 */
	@ApiModelProperty(name="enterpriseNodeNo",value="用户名",dataType="string",notes="用户名")
	private String enterpriseNodeNo;
	
	/**
	 * 用户密码
	 */
	private String psw;
	/**
	 * 企业名字
	 */
	private String enterpriseName;
	
	public SysUser(){};
	
	public SysUser(String enterpriseNodeNo, String psw) {
		this.enterpriseNodeNo=enterpriseNodeNo;
		this.psw=psw;
	}
	/**获取企业ID
	 * @return the 企业ID
	 */
	public String getGuid() {
		return guid;
	}
	/**设置企业ID
	 * @param guid the 企业ID to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**获取用户编号
	 * @return the 用户编号
	 */
	public String getEnterpriseNodeNo() {
		return enterpriseNodeNo;
	}
	/**设置用户编号
	 * @param enterpriseNodeNo the 用户编号 to set
	 */
	public void setEnterpriseNodeNo(String enterpriseNodeNo) {
		this.enterpriseNodeNo = enterpriseNodeNo;
	}
	/**获取用户密码
	 * @return the 用户密码
	 */
	public String getPsw() {
		return psw;
	}
	/**设置用户密码
	 * @param psw the 用户密码 to set
	 */
	public void setPsw(String psw) {
		this.psw = psw;
	}
	/**获取企业名字
	 * @return the 企业名字
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}
	/**设置企业名字
	 * @param enterpriseName the 企业名字 to set
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	



}

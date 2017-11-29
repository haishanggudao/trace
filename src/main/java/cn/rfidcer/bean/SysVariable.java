package cn.rfidcer.bean;

/**   
  * @Title: 系统参数
  * @author xzm
  * @date 2016年6月29日 上午9:15:37 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class SysVariable {
	/**参数ID*/
	private String variableId;
	/**参数键*/
	private String varkey;
	/**参数值*/
	private String varValue;
	/**
	 * 获取 参数ID
	 * @return variableId
	 */
	public String getVariableId() {
		return variableId;
	}
	/**
	 * 设置 参数ID
	 * @param variableId 参数ID
	 */
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	/**
	 * 获取 参数键
	 * @return varkey
	 */
	public String getVarkey() {
		return varkey;
	}
	/**
	 * 设置 参数键
	 * @param varkey 参数键
	 */
	public void setVarkey(String varkey) {
		this.varkey = varkey;
	}
	/**
	 * 获取 参数值
	 * @return varValue
	 */
	public String getVarValue() {
		return varValue;
	}
	/**
	 * 设置 参数值
	 * @param varValue 参数值
	 */
	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}
	
	@Override
	public String toString() {
		return "SysVariable [variableId=" + variableId + ", varkey=" + varkey
				+ ", varValue=" + varValue + "]";
	}


}

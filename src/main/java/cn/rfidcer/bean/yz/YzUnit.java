package cn.rfidcer.bean.yz;

/**   
* @Description: 羽众单位
* @author 席志明
* @date 2016年8月30日 下午3:30:27 
* @version V1.0   
*/
public class YzUnit {

	/**
	 * 规格ID
	 */
	private String guid;
	
	/**
	 * 规格编码
	 */
	private String unitCode;
	
	/**
	 * 规格名称
	 */
	private String unitName;

	/**获取规格ID
	 * @return the 规格ID
	 */
	public String getGuid() {
		return guid;
	}

	/**设置规格ID
	 * @param guid the 规格ID to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**获取规格编码
	 * @return the 规格编码
	 */
	public String getUnitCode() {
		return unitCode;
	}

	/**设置规格编码
	 * @param unitCode the 规格编码 to set
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	/**获取规格名称
	 * @return the 规格名称
	 */
	public String getUnitName() {
		return unitName;
	}

	/**设置规格名称
	 * @param unitName the 规格名称 to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
}

package cn.rfidcer.bean.yz;

/**   
* @Title: Province.java 
* @Package cn.rfidcer.bean.yz 
* @Description: POJO 羽众酒业-省会
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月25日 下午7:17:41 
* @version V1.0   
*/
public class Province {
	
	/**
	 * 省会代码
	 */
	private String region_code;
	/**
	 * 省会名称
	 */
	private String region_name; 
	
	public Province() { 
	}

	public Province(String region_code, String region_name) { 
		this.region_code = region_code;
		this.region_name = region_name;
	}

	/**
	 * 获取 省会代码
	 * @return the region_code
	 */
	public String getRegion_code() {
		return region_code;
	}

	/**
	 * 设置 省会代码
	 * @param region_code the region_code to set
	 */
	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}

	/**
	 * 获取 省会名称
	 * @return the region_name
	 */
	public String getRegion_name() {
		return region_name;
	}

	/**
	 * 设置 省会名称
	 * @param region_name the region_name to set
	 */
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Province [region_code=" + region_code + ", region_name=" + region_name + "]";
	}
	
	 

	 

}

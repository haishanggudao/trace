package cn.rfidcer.dto;

/**   
* @Description:时间范围
* @author 席志明
* @date 2016年8月29日 下午5:33:40 
* @version V1.0   
*/
public class TimeRange {

	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**获取开始时间
	 * @return the 开始时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**设置开始时间
	 * @param startTime the 开始时间 to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**获取结束时间
	 * @return the 结束时间
	 */
	public String getEndTime() {
		return endTime;
	}
	/**设置结束时间
	 * @param endTime the 结束时间 to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}

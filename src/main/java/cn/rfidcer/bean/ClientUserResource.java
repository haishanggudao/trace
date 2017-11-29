package cn.rfidcer.bean;

/**   
  * @Title: 手持设备资源表
  * @author jie.jia 
  * @date 2016年6月27日 下午3:24:47 
  * @Copyright Copyright
  * @version V1.0   
*/
public class ClientUserResource extends BaseEntity{
	/**设备权限ID*/
	private String resourceId; 
	/**设备权限标识符*/
	private Integer resourceNum;
	/**设备权限描述*/
	private String description;
	
	/**
	 * 获取 设备权限ID
	 * @return resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * 设置 设备权限ID
	 * @param resourceId 设备权限ID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 获取 设备权限标识符
	 * @return resourceNum
	 */
	public Integer getResourceNum() {
		return resourceNum;
	}

	/**
	 * 设置 设备权限标识符
	 * @param resourceNum 设备权限标识符
	 */
	public void setResourceNum(Integer resourceNum) {
		this.resourceNum = resourceNum;
	}

	/**
	 * 获取 设备权限描述
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 设备权限描述
	 * @param description 设备权限描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ClientUserResource [resourceId=" + resourceId + ", resourceNum=" + resourceNum + ", description="
				+ description + "]";
	}

}
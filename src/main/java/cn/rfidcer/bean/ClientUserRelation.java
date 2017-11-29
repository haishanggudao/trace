package cn.rfidcer.bean;

import javax.persistence.Table;

/**   
  * @Title: 手持设备权限
  * @author jie.jia 
  * @date 2016年6月27日 下午3:24:47 
  * @Copyright Copyright
  * @version V1.0   
*/
@Table(name="client_user_relation")
public class ClientUserRelation{
	/**设备资源ID*/
	private String resource_id; 
	/**设备ID*/
	private Integer client_user_id;
	/**
	 * 获取 设备资源ID
	 * @return resource_id
	 */
	public String getResource_id() {
		return resource_id;
	}
	/**
	 * 设置 设备资源ID
	 * @param resource_id 设备资源ID
	 */
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	/**
	 * 获取 设备ID
	 * @return client_user_id
	 */
	public Integer getClient_user_id() {
		return client_user_id;
	}
	/**
	 * 设置 设备ID
	 * @param client_user_id 设备ID
	 */
	public void setClient_user_id(Integer client_user_id) {
		this.client_user_id = client_user_id;
	}
	
	@Override
	public String toString() {
		return "ClientUserRelation [resource_id=" + resource_id
				+ ", client_user_id=" + client_user_id + "]";
	}
	

}
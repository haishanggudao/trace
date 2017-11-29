package cn.rfidcer.bean;


/**   
  * @Title: 权限管理表
  * @author xzm
  * @date 2016年6月28日 下午2:22:52 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class Role {

	/**权限ID*/
	private String id;
	/**权限名称*/
	private String role_name;
	/**权限描述*/
	private String description;
	/**权限是否可用*/
	private Boolean available=Boolean.FALSE;
	

	/**
	 * 获取 权限ID
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 权限ID
	 * @param id 权限ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 权限名称
	 * @return role_name
	 */
	public String getRole_name() {
		return role_name;
	}

	/**
	 * 设置 权限名称
	 * @param role_name 权限名称
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	/**
	 * 获取 权限描述
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置 权限描述
	 * @param description 权限描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取 权限是否可用
	 * @return available
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * 设置 权限是否可用
	 * @param available 权限是否可用
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public boolean isNewRole(){
		if(this.id==null){
			return true;
		}else if(this.id.trim().length()==0){
			return true;
		}
		return false;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Role role = (Role) o;
	        
	        if(id!=role.id){
	        	return false;
	        }

	        return true;
	    }
	@Override
	public String toString() {
		return "Role [id=" + id + ", role_name=" + role_name + ", description=" + description + ", available="
				+ available + "]";
	}
	 
	 
}

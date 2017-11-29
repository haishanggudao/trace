package cn.rfidcer.bean;

import java.io.Serializable;
import java.util.List;

import cn.rfidcer.enums.ResourceType;

/**   
  * @Title: 资源表
  * @author xzm
  * @date 2016年6月28日 下午2:10:12 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class Resource implements Serializable,Cloneable  {

	private static final long serialVersionUID = -3557821311912020043L;
	/**资源ID*/
	private Long id; // 
	/**资源名称*/
	private String name;
	/**资源类型*/
	private ResourceType type = ResourceType.menu;
	/**资源路径*/
	private String url;
	/**权限字符串*/
	private String permission;
	/**父编号*/
	private Long parentId;
	/**是否可用*/
	private Boolean available = Boolean.FALSE;
	/**图标*/
	private String icon;
	/**#子资源*/
	private List<Resource> children;
	
	
	
	/**
	 * 获取 资源ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 资源ID
	 * @param id 资源ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取 资源名称
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 资源名称
	 * @param name 资源名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 资源类型
	 * @return type
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * 设置 资源类型
	 * @param type 资源类型
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}

	/**
	 * 获取 资源路径
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置 资源路径
	 * @param url 资源路径
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取 权限字符串
	 * @return permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 设置 权限字符串
	 * @param permission 权限字符串
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * 获取 父编号
	 * @return parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 设置 父编号
	 * @param parentId 父编号
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取 是否可用
	 * @return available
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * 设置 是否可用
	 * @param available 是否可用
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	/**
	 * 获取 图标
	 * @return icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置 图标
	 * @param icon 图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取 #子资源
	 * @return children
	 */
	public List<Resource> getChildren() {
		return children;
	}

	/**
	 * 设置 #子资源
	 * @param children #子资源
	 */
	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Resource resource = (Resource) o;

		if (id != null ? !id.equals(resource.id) : resource.id != null)
			return false;

		return true;
	}
	
	@Override
	public Resource clone() {   
		Resource o = null;   
        try {   
            o = (Resource) super.clone();   
        } catch (CloneNotSupportedException e) {   
            e.printStackTrace();   
        }   
        return o;   
    }   

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", permission="
				+ permission + ", parentId=" + parentId + ", available=" + available + ", children=" + children + "]";
	}

}

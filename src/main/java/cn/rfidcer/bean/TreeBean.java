package cn.rfidcer.bean;

import java.io.Serializable;
import java.util.List;

/**   
  * @Title: 树实体类
  * @author jgx
  * @date 2016年6月29日 上午9:23:39 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class TreeBean implements Serializable {

	private static final long serialVersionUID = 7606565492657366328L;
	/**树ID*/
	private String id;
	/**树标题*/
	private String text;
	/**树图标*/
	private String iconCls;
	/**树子类*/
	private List<TreeBean> children;
	/**允许*/
	private String permission;
	/**是否选中*/
	private boolean checked;
	
	/**
	 * 获取 树ID
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 树ID
	 * @param id 树ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 树标题
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置 树标题
	 * @param text 树标题
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 获取 树图标
	 * @return iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * 设置 树图标
	 * @param iconCls 树图标
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * 获取 树子类
	 * @return children
	 */
	public List<TreeBean> getChildren() {
		return children;
	}

	/**
	 * 设置 树子类
	 * @param children 树子类
	 */
	public void setChildren(List<TreeBean> children) {
		this.children = children;
	}

	/**
	 * 获取 允许
	 * @return permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 设置 允许
	 * @param permission 允许
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * 获取 是否选中
	 * @return checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * 设置 是否选中
	 * @param checked 是否选中
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * 获取 #{bare_field_comment}
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TreeBean [id=" + id + ", text=" + text + ", iconCls=" + iconCls + ", children=" + children
				+ ", permission=" + permission + ", checked=" + checked + "]";
	}

}

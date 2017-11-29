package cn.rfidcer.service;

import java.util.List;
import java.util.Set;

import cn.rfidcer.bean.Resource;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.TreeBean;

public interface ResourceService {

	int createResource(Resource resource);

	int addResourceForRole(long roleId, long resourceId);

	ResultMsg addResourcesForRole(long roleId, String[] resourceIds);

	/**
	 * 根据角色所有的资源权限
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	List<Resource> findResourcesByRoleId(String roleId);

	/**
	 * 根据用户权限得到菜单
	 * 
	 * @param permissions
	 * @return
	 */
	List<Resource> findMenus(Set<String> permissions);

	/**
	 * 获得所有资源
	 * 
	 * @return
	 */
	List<Resource> findAll();

	List<TreeBean> findTree(Set<String> permissions);
}

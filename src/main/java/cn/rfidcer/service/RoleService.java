package cn.rfidcer.service;

import java.util.List;
import java.util.Set;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Role;
import cn.rfidcer.interceptor.Page;

public interface RoleService {

	/**
	 * 根据角色ID查找角色
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findRoles(Long... roleIds);

	/**
	 * 根据角色ID查找角色
	 * 
	 * @param roleId
	 * @return
	 */
	Role findOne(Long roleId);

	/**
	 * 查找用户的角色列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	List<Role> findRolesByUserId(long userId);

	Set<String> findRolenamesByUserId(long userId);

	Set<String> findPermissions(long userId);

	List<Role> findAll(Page page);

	Set<String> findPermissionsByRoleId(long roleId);

	ResultMsg createRole(Role role);

	ResultMsg updateRole(Role role);

	ResultMsg deleteRole(Long roleId);

	int addRoleForUser(long userId, long roleId);
}

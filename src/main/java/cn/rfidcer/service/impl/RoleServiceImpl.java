package cn.rfidcer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.Resource;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Role;
import cn.rfidcer.dao.ResourceDao;
import cn.rfidcer.dao.RoleDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public Set<String> findRoles(Long... roleIds) {
		Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole_name());
            }
        }
        return roles;
	}

	@Override
	public Role findOne(Long roleId) {
		return roleDao.findOne(roleId);
	}

	@Override
	public List<Role> findRolesByUserId(long userId) {
		return roleDao.findRolesByUserId(userId);
	}

	@Override
	public Set<String> findRolenamesByUserId(long userId) {
		Set<String> set=new HashSet<String>();
		List<Role> roles = findRolesByUserId(userId);
		for (Role role : roles) {
			set.add(role.getRole_name());
		}
		return set;
	}

	@Override
	public Set<String> findPermissions(long userId) {
		Set<String> permissions=new HashSet<String>();
		List<Role> roles = findRolesByUserId(userId);
		for (Role role : roles) {
			List<Resource> resources = resourceDao.findResourcesByRoleId(role.getId());
			for (Resource resource : resources) {
				permissions.add(resource.getPermission());
			}
		}
		return permissions;
	}

	@Override
	public List<Role> findAll(Page page) {
		return roleDao.findAll(page);
	}

	@Override
	public ResultMsg createRole(Role role) {
		ResultMsg resultMsg = new ResultMsg();
		int res = roleDao.createRole(role);
		resultMsg.setCode(res+"");
		if( res==1){
			resultMsg.setMsg("新增角色成功");
		}else{
			resultMsg.setMsg("新增角色失败");
		}
		return resultMsg;
	}

	@Override
	public ResultMsg updateRole(Role role) {
		ResultMsg resultMsg = new ResultMsg();
		int res = roleDao.updateRole(role);
		resultMsg.setCode(res+"");
		if( res==1){
			resultMsg.setMsg("更新角色成功");
		}else{
			resultMsg.setMsg("更新角色失败");
		}
		return resultMsg;
	}

	@Override
	public ResultMsg deleteRole(Long roleId) {
		ResultMsg resultMsg = new ResultMsg();
		roleDao.delResourceRelation(roleId);
		roleDao.delUserRelation(roleId);
		int res = roleDao.deleteRole(roleId);
		resultMsg.setCode(res+"");
		if( res==1){
			resultMsg.setMsg("删除角色成功");
		}else{
			resultMsg.setMsg("删除角色失败");
		}
		return resultMsg;
	}

	@Override
	public Set<String> findPermissionsByRoleId(long roleId) {
		Set<String> permissions=new HashSet<String>();
		List<Resource> resources = resourceDao.findResourcesByRoleId(roleId+"");
		for (Resource resource : resources) {
			permissions.add(resource.getPermission());
		}
		return permissions;
	}

	@Override
	public int addRoleForUser(long userId, long roleId) {
		return roleDao.addRoleForUser(userId, roleId);
	}

}

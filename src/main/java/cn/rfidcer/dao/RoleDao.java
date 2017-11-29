package cn.rfidcer.dao;

import java.util.List;

import cn.rfidcer.bean.Role;
import cn.rfidcer.interceptor.Page;

public interface RoleDao {

	int createRole(Role role);
    int updateRole(Role role);
    int deleteRole(Long roleId);

    Role findOne(Long roleId);
    List<Role> findAll(Page page);
    
    List<Role> findRolesByUserId(long userId);
    
    int addRoleForUser(long userId,long roleId);
    
    int delResourceRelation(long roleId);
    
    int delUserRelation(long roleId);
}

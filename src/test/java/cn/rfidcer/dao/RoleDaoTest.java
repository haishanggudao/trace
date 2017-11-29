package cn.rfidcer.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.DefaultContext;
import cn.rfidcer.bean.Role;
import cn.rfidcer.dao.RoleDao;

public class RoleDaoTest extends DefaultContext{

	@Autowired
	private RoleDao roleDao;
	
	public void createRole(){
		Role role = new Role();
		role.setRole_name("admin");
		role.setDescription("超级管理员");
		roleDao.createRole(role);
	}
	
	public void addRoleForUserTest(){
		int res = roleDao.addRoleForUser(2, 4);
		System.out.println(res);
	}
	
	@Test
	public void findRolesByUserIdTest(){
		List<Role> roles = roleDao.findRolesByUserId(2);
		System.out.println(roles);
	}
	
	public void delRoleTest(){
		roleDao.deleteRole((long) 5);
	}
}

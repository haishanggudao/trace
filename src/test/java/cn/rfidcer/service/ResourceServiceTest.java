package cn.rfidcer.service;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.DefaultContext;
import cn.rfidcer.service.RoleService;

public class ResourceServiceTest extends DefaultContext{

	@Autowired
	private RoleService roleService;
	
	@Test
	public void findPermissionsByUserId(){
		Set<String> permissions = roleService.findPermissions(2);
		System.out.println(permissions);
	}
}

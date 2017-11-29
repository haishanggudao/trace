package cn.rfidcer.service;

import cn.rfidcer.service.RoleService;

public class RoleServiceTest{

//	@Autowired
	private RoleService roleService;
	
//	@Test
	public void delRoleTest(){
		roleService.deleteRole((long) 5);
	}
}

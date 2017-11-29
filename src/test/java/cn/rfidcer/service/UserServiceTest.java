package cn.rfidcer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.DefaultContext;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.service.UserService;


public class UserServiceTest extends DefaultContext{

	@Autowired
	private UserService userService;
	
	public void createUser(){
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
//		int res = userService.createUser(user,"");
//		System.out.println(res);
	}
	
//	@Test
	public void findOne(){
		User user = userService.findOne(2);
		System.out.println(user);
	}
	
	@Test
	public void findUserByname(){
		User user = userService.findUserByname("admin");
		System.out.println(user);
	}
	
//	@Test
	public void updatePasswordTest(){
		User user=new User();
		user.setId("7");
		user.setUsername("a");
		user.setPassword("5");
		ResultMsg res = userService.updatePassword(user);
		System.out.println(res.getCode());
	}
}

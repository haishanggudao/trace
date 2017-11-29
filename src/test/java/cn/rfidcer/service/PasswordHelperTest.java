package cn.rfidcer.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.bean.User;
import cn.rfidcer.service.impl.PasswordHelper;

public class PasswordHelperTest {

	@Autowired
	private PasswordHelper helper;
	
	public void createAdminUser(){
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		helper.encryptPassword(user);
		System.out.println(user);
	}
}

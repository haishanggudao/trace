package cn.rfidcer.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.DefaultContext;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.UserDao;

public class UserDaoTest extends DefaultContext{

	@Autowired
	private UserDao userDao;
	
	@Test
	public void findOne(){
		User user = userDao.findOne(1);
		System.out.println(user);
	}
}

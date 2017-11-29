package cn.rfidcer.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.UserDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.RoleService;
import cn.rfidcer.service.UserService;

@Service
public class UserServcieImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Autowired
	private RoleService roleService;
	
	private Logger logger=LoggerFactory.getLogger(UserServcieImpl.class);
	@Override
	public ResultMsg createUser(User user,String roleIds,String user_company_Ids) {
		ResultMsg msg=new ResultMsg();
		passwordHelper.encryptPassword(user);
		long res = userDao.createUser(user);
		logger.info("新增用户ID:"+user.getId());
		if(res==1){
			String[] ids = roleIds.split(",");
			for (String role_id : ids) {
				res=roleService.addRoleForUser(Long.parseLong(user.getId()), Long.parseLong(role_id));
			}
			if(user_company_Ids!=null && !StringUtils.isEmpty(user_company_Ids)){
				String[] company_Ids=user_company_Ids.split(",");
				for (String company_Id : company_Ids) {
					userDao.addCompanyRelation(Long.parseLong(user.getId()), company_Id);
				}
			}
			msg.setCode("1");
			msg.setMsg("新增用户成功！");
		}else{
			msg.setCode("0");
			msg.setMsg("新增用户失败！");
		}
		return msg;
	}

	@Override
	public User findOne(int userId) {
		return userDao.findOne(userId);
	}

	@Override
	public User findUserByname(String username) {
		return userDao.findUserByname(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		User user = findUserByname(username);
		if(user==null){
			return Collections.emptySet();
		}
		return roleService.findRolenamesByUserId(Long.parseLong(user.getId()));
	}

	@Override
	public Set<String> findPermissions(String username) {
		User user = findUserByname(username);
		if(user==null){
			return Collections.emptySet();
		}
		return roleService.findPermissions(Long.parseLong(user.getId()));
	}

	@Override
	public List<User> findAllUsers(Page page) {
		return userDao.findAll(page);
	}

	@Override
	public ResultMsg updateUser(User user, String roleIds,String user_company_Ids) {
		ResultMsg msg=new ResultMsg();
		int res = userDao.updateUser(user);
		if(res==1){
			long userId = Long.parseLong(user.getId());
			userDao.delRoleRelation(userId);
			String[] ids = roleIds.split(",");
			for (String role_id : ids) {
				res=roleService.addRoleForUser(userId, Long.parseLong(role_id));
			}
			if(user_company_Ids!=null && !StringUtils.isEmpty(user_company_Ids)){
				userDao.delCompanyRelation(userId);
				String[] company_ids=user_company_Ids.split(",");
				for (String company_id : company_ids) {
					userDao.addCompanyRelation(Long.parseLong(user.getId()), company_id);
				}
			}
			msg.setCode("1");
			msg.setMsg("修改用户成功！");
		}else{
			msg.setCode("0");
			msg.setMsg("修改用户失败！");
		}
		return msg;
	}

	@Override
	public ResultMsg changeUserLock(User user) {
		ResultMsg msg=new ResultMsg();
		user.setLocked(!user.getLocked());
		int res=userDao.changeUserLock(user);
		if(res==1){
			msg.setCode("1");
			msg.setMsg("锁定或解锁用户成功！");
		}else{
			msg.setCode("0");
			msg.setMsg("锁定或解锁用户失败！");
		}
		return msg;
	}

	@Override
	public ResultMsg updatePassword(User user) {
		ResultMsg msg=new ResultMsg();
		passwordHelper.encryptPassword(user);
		int res = userDao.updatePassword(user);
		if(res==1){
			msg.setCode("1");
			msg.setMsg("修改密码成功！");
		}else{
			msg.setCode("0");
			msg.setMsg("修改密码失败！");
		}
		return msg;
	}

	@Override
	public List<String> getCompanyRelation(String userId) {
		return userDao.getCompanyRelation(userId);
	}


	



}

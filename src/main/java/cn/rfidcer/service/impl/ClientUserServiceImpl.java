package cn.rfidcer.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ClientUser;
import cn.rfidcer.bean.ClientUserResource;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ClientUserMapper;
import cn.rfidcer.dao.ClientUserResourceMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ClientUserService;
import cn.rfidcer.util.MD5Util;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.TokenCacheUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ClientUserServiceImpl implements ClientUserService {
	
	private Logger logger = LoggerFactory.getLogger(ClientUserServiceImpl.class);
	
	@Autowired
	private ClientUserMapper clientUserDao;
	
	@Autowired
	private ClientUserResourceMapper clientUserResourceDao;
	
	@Autowired
	private TokenCacheUtil tokenCacheUtil;

	@Override
	public ClientUser selectByUserName(String clientUserName) { 
		logger.info("查询用户:"+clientUserName);
		return clientUserDao.selectByUserName(clientUserName);
	}

	@Override
	public ResultMsg createLoginClientUser(ClientUser clientUser) { 
		logger.info("用户登录:{}",clientUser.getClientUserName());
		
		ResultMsg resultMsg=new ResultMsg();
		String code="0";
		String msg=null;
		
		String clientUserName = clientUser.getClientUserName();
		
		ClientUser myFoundClientUser = clientUserDao.selectByUserName(clientUserName);
		
		if (myFoundClientUser != null) {
			
			String myLoginPassword = clientUser.getClientPassword();
			String myFoundPassword = myFoundClientUser.getClientPassword();
			
			if (myLoginPassword.equalsIgnoreCase(myFoundPassword)) {
				
				String exectedToken = UUIDGenerator.generatorUUID();
				tokenCacheUtil.putTokenToCache(clientUserName, exectedToken);
//				System.out.println(tokenCacheUtil.getCacheManager().getCache("handtokenCache").get(clientUserName));
				
				myFoundClientUser.setToken(exectedToken);
				
				 List<ClientUserResource> myresources = clientUserResourceDao.findByClientUserId(myFoundClientUser.getClientUserId());
				 
				 String strResources = "";
				 
				 for (ClientUserResource clientUserResource : myresources) {
					 strResources += clientUserResource.getResourceNum()+",";
				}
				 if(strResources!=""){
					 myFoundClientUser.setResourceNums(strResources.substring(0, strResources.length()-1));
				 }
				
				// action: update token by user_id
				clientUserDao.updateTokenByUserid(exectedToken, myFoundClientUser.getClientUserId());
				
				code="1";
				resultMsg.setBaseEntity(myFoundClientUser);
			} else {
				code="3";
				msg="密码错误";
			}
			
		} else {
			code="2";
			msg="没有该用户名";
		}
		
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public List<ClientUser> findAll(Page page,ClientUser clientUser) { 
		return clientUserDao.findAll(page,clientUser);
	}

	@Override
	public ResultMsg addClientUser(ClientUser clientUser, String clientRoleIds, User currentUser) { 
		int res=0;
		String msg=null;
		
		String myGlobalClientUserId = "";
		
		if (StringUtils.isEmpty(clientUser.getClientUserId())) {
			// case: add the new client user
			logger.info("新增设备用户 ");
			msg = "新增设备用户";
			String myClientUserID = UUIDGenerator.generatorUUID();
			myGlobalClientUserId = myClientUserID;
			clientUser.setClientUserId(myClientUserID);
			
			// convert password to md5
			String myPassword = clientUser.getClientPassword();
			String myMD5Password = MD5Util.string2MD5(myPassword);
			clientUser.setClientPassword(myMD5Password);
			
			String myPasswordAgain = clientUser.getClientPasswordAgain();
			Boolean isEqualPassword = false;
			isEqualPassword = myPassword.equalsIgnoreCase(myPasswordAgain);
			if ( !isEqualPassword ) {
				res = 2;
				msg = "密码和密码确认不一致";
				return ResultMsgUtil.getReturnMsg(res, msg);
			}
			
			// check the user name is existed?
			String myUserName = clientUser.getClientUserName();
			int checkIsExistedUserName = checkIsExistedUserName(myUserName);
			if ( checkIsExistedUserName < 0) {
				res = 3;
				msg = "该用户名[" + myUserName + "]已经存在";
				return ResultMsgUtil.getReturnMsg(res, msg);
			}
			
			// assign UUID to token
			clientUser.setToken( UUIDGenerator.generatorUUID());
			
			clientUser.setCreateBy(currentUser.getId());
			clientUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
			clientUser.setUpdateBy(currentUser.getId());
			clientUser.setUpdateTime( new Timestamp(System.currentTimeMillis())); 
			
			// action: insert a new client
			res = clientUserDao.insertSelective(clientUser);
		} else {
			// case: update the new client user
			logger.info("修改设备用户信息："+clientUser);
			msg = "修改设备用户信息";
			
			myGlobalClientUserId = clientUser.getClientUserId();
			
			// convert password to md5
			String myPassword = clientUser.getClientPassword();
			
			if ( !StringUtils.isEmpty(myPassword) ) {
				String myMD5Password = MD5Util.string2MD5(myPassword);
				clientUser.setClientPassword(myMD5Password);
				
				String myPasswordAgain = clientUser.getClientPasswordAgain();
				Boolean isEqualPassword = false;
				isEqualPassword = myPassword.equalsIgnoreCase(myPasswordAgain);
				if ( !isEqualPassword ) {
					res = 2;
					msg = "密码和密码确认不一致";
					return ResultMsgUtil.getReturnMsg(res, msg);
				}
			}else{
				clientUser.setClientPassword(null);
			} 
			
			clientUser.setUpdateBy(currentUser.getId());
			clientUser.setUpdateTime( new Timestamp(System.currentTimeMillis())); 
			
			// action: update the client
			res = clientUserDao.updateByPrimaryKeySelective(clientUser);
		}
		
		clientUserDao.delPermissionRelation(myGlobalClientUserId);
		 
		String[] ids = clientRoleIds.split(",");
		for (String role_id : ids) {
			res=clientUserDao.addPermissionForUser(role_id, myGlobalClientUserId);
		}  
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	/**
	 * 校验设备用户名是否已经存在? created by jie.jia at 2016-03-07 11:49
	 * @param myUserName
	 * @return
	 */
	private int checkIsExistedUserName(String myUserName) {
		int result = 0;
		ClientUser mySelectedClientUser = clientUserDao.selectByUserName(myUserName);
		if ( mySelectedClientUser != null) {
			// exception: the user name has existed
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public ResultMsg deleteByClientUserId(ClientUser clientUser) { 
		logger.info("删除设备用户记录ID：" + clientUser.getClientUserId() );
		int res=0;
		String msg=null;
		msg = "删除设备用户记录";
		
		// action: delete the record of client user by userId
		res = clientUserDao.deleteByPrimaryKey(clientUser.getClientUserId());
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public int addPermissionForUser(String resourceId, String clientUserId) { 
		return clientUserDao.addPermissionForUser(resourceId, clientUserId);
	}

	@Override
	public ResultMsg updateUserPermission(String clientUserId, String clientRoleIds) { 
		int res=0;
		String msg=null;
		msg = "更改设备用户权限";
		
		clientUserDao.delPermissionRelation(clientUserId);
		 
		String[] ids = clientRoleIds.split(",");
		for (String role_id : ids) {
			res = clientUserDao.addPermissionForUser(role_id, clientUserId);
		}  
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}
	
	/**
	 * 比较密码和密码确认是否一致? created by jie.jia at 2016-03-11 10:50
	 * @param password
	 * @param passwordAgain
	 * @return
	 */
	private boolean isSamePassword(String password, String passwordAgain){
		boolean result = false;
		
		if (password.equalsIgnoreCase(passwordAgain)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public ResultMsg updateClientPassword(ClientUser clientUser) { 
		int res=0;
		String msg=null;
		msg = "更改设备用户密码";
		
		boolean isSamePassword = isSamePassword(clientUser.getClientPassword(), clientUser.getClientPasswordAgain());
		
		if (isSamePassword) {
			String myPassword = clientUser.getClientPassword();
			String myMD5Password = MD5Util.string2MD5(myPassword);
			clientUser.setClientPassword(myMD5Password);
			
			res = clientUserDao.updatePasswordByPrimarKey(clientUser);
		} else {
			res = 2;
			msg = "密码和密码确认不一致";
		}
		
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

}

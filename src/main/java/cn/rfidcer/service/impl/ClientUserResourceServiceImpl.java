package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ClientUserResource;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ClientUserResourceMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ClientUserResourceService;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ClientUserResourceServiceImpl implements ClientUserResourceService {
	
	private Logger logger = LoggerFactory.getLogger(ClientUserResourceServiceImpl.class);
	
	@Autowired
	private ClientUserResourceMapper clientUserResourceDao;

	@Override
	public List<ClientUserResource> findAll(Page page) { 
		return clientUserResourceDao.findAll(page);
	}

	@Override
	public ResultMsg addOrUpdateClientUserResource(ClientUserResource clientUserResource, User currentUser) { 
		logger.info("设备权限:"+clientUserResource.getResourceNum());
		int res=0;
		String msg=null;
		
		if (StringUtils.isEmpty( clientUserResource.getResourceId() )) {
			msg = "新增设备权限";
			
			clientUserResource.setResourceId(UUIDGenerator.generatorUUID());
			
			// action DAO: insert a new resource of client user
			res = clientUserResourceDao.insertSelective(clientUserResource);
		} else {
			msg = "编辑设备权限";
			
			// action DAO: update the resource of client user
			res = clientUserResourceDao.updateByPrimaryKeySelective(clientUserResource);
		}
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public ResultMsg deleteByClientUserResourceId(ClientUserResource clientUserResource) { 
		logger.info("删除设备资源记录ID：" + clientUserResource.getResourceId());
		int res=0;
		String msg=null;
		msg = "删除设备资源记录";
		
		// action: delete the resource of client user by resourceId
		res = clientUserResourceDao.deleteByPrimaryKey(clientUserResource.getResourceId());
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public List<ClientUserResource> findByClientUserId(String clientUserId) { 
		return clientUserResourceDao.findByClientUserId(clientUserId);
	}

}

package cn.rfidcer.service;

import java.util.List;
 
import cn.rfidcer.bean.ClientUserResource;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * Service for the resource of client user
 * @author jie.jia
 * created by jie.jia at 2016-03-10 15:12
 */
public interface ClientUserResourceService {
	
	/**
	 * 新增或修改设备权限信息; created by jie.jia at 2016-03-10 16:08
	 * @param clientUserResource
	 * @param currentUser
	 * @return
	 */
	ResultMsg addOrUpdateClientUserResource(ClientUserResource clientUserResource, User currentUser);
	
	/**
	 * 删除设备资源信息; created by jie.jia at 2016-03-10 16:35
	 * @param clientUserResource
	 * @return
	 */
	ResultMsg deleteByClientUserResourceId(ClientUserResource clientUserResource);
	
	/**
	 * 查询所有的设备权限信息; created by jie.jia at 2016-03-10 15:19
	 * @param page
	 * @return
	 */
	List<ClientUserResource> findAll(Page page);
	
	/**
	 * 依据设备用户ID来查询权限信息; created by jie.jia at 2016-03-10 18:38
	 * @param clientUserId
	 * @return
	 */
	List<ClientUserResource> findByClientUserId(String clientUserId);

}

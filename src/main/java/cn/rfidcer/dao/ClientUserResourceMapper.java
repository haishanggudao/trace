package cn.rfidcer.dao;

import java.util.List;
 
import cn.rfidcer.bean.ClientUserResource;
import cn.rfidcer.interceptor.Page;

/**
 * DAO for the resource of client user
 * @author jie.jia
 * created at 2016-03-10 15:16
 */
public interface ClientUserResourceMapper {
	
	/**
	 * 依据设备权限ID来删除该记录; created by jie.jia at 2016-03-10 16:33
	 * @param resourceId
	 * @return
	 */
    int deleteByPrimaryKey(String resourceId);
    
    /**
     * 查询所有的设备权限信息; created by jie.jia at 2016-03-10 15:21
     * @param page
     * @return
     */
    List<ClientUserResource> findAll(Page page);
    
    /**
     * 依据设备用户ID来查询设备权限; created by jie.jia at 2016-03-10 18:30
     * @param clientUserId
     * @return
     */
    List<ClientUserResource> findByClientUserId(String clientUserId);

    int insert(ClientUserResource record);

    /**
     * 新增设备权限信息; created by jie.jia at 2016-03-10 16:05
     * @param record
     * @return
     */
    int insertSelective(ClientUserResource record);

    ClientUserResource selectByPrimaryKey(String resourceId);

    /**
     * 依据设备权限ID来修改该记录; created by jie.jia at 2016-03-10 16:48
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ClientUserResource record);

    int updateByPrimaryKey(ClientUserResource record);
}
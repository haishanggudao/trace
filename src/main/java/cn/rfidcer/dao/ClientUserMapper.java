package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ClientUser;
import cn.rfidcer.bean.TreeBean;
import cn.rfidcer.interceptor.Page;

/**
 * DAO Client User
 * @author jie.jia
 *
 */
public interface ClientUserMapper {
	
	/**
	 * 为设备用户添加权限; created by jie.jia at 2016-03-10 19:08
	 * @param resourceId
	 * @param clientUserId
	 * @return
	 */
	int addPermissionForUser(String resourceId, String clientUserId);
     
	/**
	 * 删除设备用户信息; created by jie.jia at 2016-03-07 11:54
	 * @param clientUserId
	 * @return
	 */
    int deleteByPrimaryKey(String clientUserId);
    
    /**
     * 依据设备用户名ID删除权限; created by jie.jia at 2016-03-10 19:44
     * @param clientUserId
     * @return
     */
    int delPermissionRelation(String clientUserId);
    
    /**
     * 查询所有的设备用户信息; created by jie.jia at 2016-03-04 13:53
     * @param page
     * @return
     */
    List<ClientUser> findAll(Page page,@Param("clientUser") ClientUser clientUser);
    
    /**
     * 依据用户名来查询该用户的信息; created by jie.jia at 2016-03-07 10:52
     * @param clientUserName
     * @return
     */
    ClientUser findByUserName(String clientUserName);
    
    /**
     * 查找设备权限的树形节点; created by jie.jia at 2016-03-10 17:25
     * @return
     */
    List<TreeBean> findTree();

    int insert(ClientUser record);

    /**
     * 新增设备用户信息; created by jie.jia at 2016-03-07 10:48
     * @param record
     * @return
     */
    int insertSelective(ClientUser record);

	ClientUser selectByPrimaryKey(String clientUserId);
    
    /**
     * 依据用户名来查询用户信息; created by jie.jia at 2016-03-02 11:08
     * @param clientUserName 
     * @return
     */
    ClientUser selectByUserName(String clientUserName);

    /**
     * 更新设备用户信息; created by jie.jia at 2016-03-07 14:43
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ClientUser record);

    int updateByPrimaryKey(ClientUser record);
    
    /**
     * 依据设备用户ID来修改密码; created by jie.jia at 2016-03-11 10:42
     * @param clientUser
     * @return
     */
    int updatePasswordByPrimarKey(ClientUser clientUser);
    
    /**
     * 依据clientUserId来修改token; created by jie.jia at 2016-03-02 13:54
     * @param token
     * @param clientUserId
     * @return
     */
    int updateTokenByUserid(String token, String clientUserId);
}
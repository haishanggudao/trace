package cn.rfidcer.service;
 
import java.util.List; 
import cn.rfidcer.bean.ClientUser;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * Service for Client User
 * @author jie.jia
 * created by jie.jia at 2016-03-02 11:16
 */
public interface ClientUserService {
	
	/**
	 * 新增设备用户信息; created by jie.jia at 2016-03-04 16:43
	 * @param clientUser
	 * @param currentUser
	 * @return
	 */
	ResultMsg addClientUser(ClientUser clientUser,String clientRoleIds, User currentUser);
	
	/**
	 * 为设备用户添加权限; created by jie.jia at 2016-03-10 19:08
	 * @param resourceId
	 * @param clientUserId
	 * @return
	 */
	int addPermissionForUser(String resourceId, String clientUserId);
	
	/**
	 * 删除设备用户信息; created by jie.jia at 2016-03-07 13:27
	 * @param clientUserId
	 * @return
	 */
	ResultMsg deleteByClientUserId(ClientUser clientUser);
	
	/**
	 * 查询所有的设备用户信息; created by jie.jia at 2016-03-04 15:53
	 * @param page
	 * @return
	 */
	List<ClientUser> findAll(Page page,ClientUser clientUser);
	
	/**
	 * 根据用户名来查询用户信息; created by jie.jia at 2016-03-02 11:19
	 * @param clientUserName
	 * @return
	 */
	ClientUser selectByUserName(String clientUserName);
	
	/**
	 * 验证手持设备的用户登录; created by jie.jia at 2016-03-02 11:42
	 * @param clientUser
	 * @return
	 */
	ResultMsg createLoginClientUser(ClientUser clientUser);
	
	/**
	 * 修改设备用户的权限; created by jie.jia at 2016-03-10 19:26
	 * @param clientUserId
	 * @param clientRoleIds
	 * @return
	 */
	ResultMsg updateUserPermission(String clientUserId,String clientRoleIds);
	
	/**
	 * 更新设备用户的密码; created by jie.jia at 2016-03-11 10:32
	 * @param clientUser
	 * @return
	 */
	ResultMsg updateClientPassword(ClientUser clientUser);

}

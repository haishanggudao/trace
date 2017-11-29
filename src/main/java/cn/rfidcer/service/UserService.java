package cn.rfidcer.service;

import java.util.List;
import java.util.Set;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface UserService {


	/**增加用户及相关权限
	 * @param user
	 * @param roleIds 角色ID，以<span>,<span>分割
	 * @param projectIds 项目点ID，以<span>,<span>分割
	 * @param supplierIds 供应商ID，以<span>,<span>分割
	 * @return
	 */
	ResultMsg createUser(User user,String roleIds,String user_company_Ids);
	
	/**修改用户
	 * @param user
	 * @param roleIds
	 * @return
	 */
	ResultMsg updateUser(User user,String roleIds,String user_company_Ids);
	
	/**锁定用户
	 * @param userId
	 * @return
	 */
	ResultMsg changeUserLock(User user);
	
	/**修改密码
	 * @param user
	 * @return
	 */
	ResultMsg updatePassword(User user);
	/** 根据ID查找用户
	 * @param userId 用户ID
	 * @return
	 */
	User findOne(int userId);
	
	/**根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findUserByname(String username);

	/**根据用户名查找角色
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);

	/**根据用户名查找资源权限
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);
	
	/**查找所有用户
	 * @return
	 */
	List<User> findAllUsers(Page page);

	/**查找用户企业关系
	 * @param userId
	 * @return
	 */
	List<String> getCompanyRelation(String userId);
	
}

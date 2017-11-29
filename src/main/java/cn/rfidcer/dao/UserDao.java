package cn.rfidcer.dao;

import java.util.List;

import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface UserDao {

	/**根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findUserByname(String username);
	
	/**查找所有用户
	 * @param page
	 * @return
	 */
	List<User> findAll(Page page);
	
	/**根据用户ID查找用户
	 * @param userId
	 * @return
	 */
	User findOne(int userId);
	
	/**删除用户
	 * @param userId
	 * @return
	 */
	int deleteUser(long userId);
	
	/**锁定或解锁用户
	 * @param user
	 * @return
	 */
	int changeUserLock(User user);
	
	/**创建用户
	 * @param user
	 * @return
	 */
	long createUser(User user);
	
	/**修改用户
	 * @param user
	 * @return
	 */
	int updateUser(User user);
	
	/**删除用户的角色关系
	 * @param userId
	 * @return
	 */
	int delRoleRelation(long userId);
	
	/**修改密码
	 * @param user
	 * @return
	 */
	int updatePassword(User user);
	
	/**添加用户的企业权限
	 * @param userId
	 * @param projectId
	 * @return
	 */
	int addCompanyRelation(long userId,String companyId);
	
	
	/**删除用户的企业关系
	 * @param userId
	 * @return
	 */
	int delCompanyRelation(long userId);
	
	/**获取用户的企业关系
	 * @param userId
	 * @return
	 */
	List<String> getCompanyRelation(String userId);

}

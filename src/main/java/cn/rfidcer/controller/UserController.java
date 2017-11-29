package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Role;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.RoleService;
import cn.rfidcer.service.UserService;

/**   
* @Title: UserController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 用户信息
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午11:32:48 
* @version V1.0   
*/
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "user";
	}
	
	/**
	 * 获取所有的用户信息;
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	@RequiresPermissions(value={"system:user:list"})
	public List<User> list(Page page){
		return userService.findAllUsers(page);
	}
	
	/**
	 * 获取所有的用户信息;
	 * @param page
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String,Object> findAllList(Page page){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", userService.findAllUsers(page));
		return map;
	}
	
	/**
	 * 新增或修改用户信息;
	 * @param user
	 * @param roleIds
	 * @param user_company_Ids
	 * @return
	 */
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:user:add","system:user:update"})
	public ResultMsg addUser(User user,String roleIds,String user_company_Ids){
		if(StringUtils.isEmpty(user.getId())){
			return userService.createUser(user,roleIds,user_company_Ids);
		}else{
			return userService.updateUser(user, roleIds,user_company_Ids);
		}
	}
	
	/**
	 * 依据userId获取角色;
	 * @param userId
	 * @return
	 */
	@RequestMapping("/roleRelation")
	@ResponseBody
	@RequiresPermissions(value={"system:user:update"})
	public List<Role> getRolesByUserId(long userId){
		return roleService.findRolesByUserId(userId);
	}
	
	/**
	 * 锁定用户;
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/lockUser",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("system:user:lockUser")
	public ResultMsg lockUser(User user){
		return userService.changeUserLock(user);
	}
	
	/**
	 * 修改密码;
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("system:user:updatePassword")
	public ResultMsg updatePassword(User user){
		return userService.updatePassword(user);
	}
	
	/**
	 * 查找用户企业关系;
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getCompanyRelation",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:user:update"})
	public List<String> getProjectRelation(String userId){
		return userService.getCompanyRelation(userId);
	}
}

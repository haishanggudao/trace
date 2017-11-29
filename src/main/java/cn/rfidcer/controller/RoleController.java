package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Role;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.RoleService;

/**
* @Title: RoleController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 角色管理 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午10:28:47 
* @version V1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	private Logger logger=LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "role";
	}
	
	/**
	 * 获取角色记录列表;
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("system:role:list")
	@ResponseBody
	public List<Role> list(Page page){
		logger.info("page:"+page);
		return roleService.findAll(page);
	}
	
	/**
	 * 获取角色记录列表;
	 * @param page
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String,Object> findAllList(Page page){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", roleService.findAll(page));
		return map;
	}
	
	/**
	 * 获取角色记录列表
	 * @return
	 */
	@RequestMapping("/combobox")
	@ResponseBody
	public List<Role> getRoleList(){
		return roleService.findAll(null);
	}
	
	/**
	 * 添加角色;
	 * @param role
	 * @return
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	@RequiresPermissions(value={"system:role:create","system:role:update"})
	public ResultMsg addRole(Role role){
		if(!role.isNewRole()){
			return roleService.updateRole(role);
		}
		return roleService.createRole(role);
	}
	
	/**
	 * 删除角色;
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/delRole",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("system:role:del")
	public ResultMsg delRole(long roleId){
		return roleService.deleteRole(roleId);
	}
}

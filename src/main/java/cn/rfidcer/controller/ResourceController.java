package cn.rfidcer.controller;

import java.util.List;
import java.util.Set;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.TreeBean;
import cn.rfidcer.service.ResourceService;
import cn.rfidcer.service.RoleService;

/**
* @Title: ResourceController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 系统菜单功能 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午10:25:56 
* @version V1.0
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleService roleService;
	
	private Logger logger=LoggerFactory.getLogger(ResourceController.class);
	
	/**
	 * 依据角色ID来获取所属权限列表;
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	@RequiresPermissions("system:role:permissions")
	public List<TreeBean> getAllResource(long roleId){
		Set<String> permissions =roleService.findPermissionsByRoleId(roleId);
		return  resourceService.findTree(permissions);
	}
	
	/**
	 * 为角色添加权限;
	 * @param roleId
	 * @param resourceIds
	 * @return
	 */
	@RequestMapping(value="/saveRoleRelation",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("system:role:permissions")
	public ResultMsg saveRoleRelation(long roleId,String resourceIds){
		logger.info("roleId:{},resourcesId:{}",roleId,resourceIds);
		String[] ids=resourceIds.split(",");
		return resourceService.addResourcesForRole(roleId, ids);
	}
}

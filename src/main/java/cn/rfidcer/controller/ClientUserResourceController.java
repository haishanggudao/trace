package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser; 
import cn.rfidcer.bean.ClientUserResource;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ClientUserResourceService;

/**
* @Title: ClientUserResourceController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 设备权限 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:43:50 
* @version V1.0
 */
@Controller
@RequestMapping("/client_user_resource")
public class ClientUserResourceController {
	
	@Autowired
	private ClientUserResourceService clientUserResourceService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "clientUserResource";
	} 
	
	@RequestMapping("/list")
	@ResponseBody
	public List<ClientUserResource> list(Page page){
		return clientUserResourceService.findAll(page);
	}
	
	/**
	 * 查询全部的设备权限；
	 * @param page
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"system:client_user_resource:list"},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", clientUserResourceService.findAll(page));
		return map;
	}

	/**
	 * 新增设备权限；
	 * @param clientUserResource
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addClientUserResource",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:client_user_resource:add","system:client_user_resource:edit"},logical=Logical.OR)
	public ResultMsg addClientUserResource(ClientUserResource clientUserResource, @CurrentUser User currentUser){ 
		return clientUserResourceService.addOrUpdateClientUserResource(clientUserResource, currentUser);
	}
	
	/**
	 * 删除设备权限；
	 * @param clientUserResource
	 * @return
	 */
	@RequestMapping(value="/delClientUserResource",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:client_user_resource:delete"},logical=Logical.OR)
	public ResultMsg delClientUserResource(ClientUserResource clientUserResource){  
		return clientUserResourceService.deleteByClientUserResourceId(clientUserResource);
	}
	
	/**
	 * 依据用户ID来查询设备权限；
	 * @param clientUserId
	 * @return
	 */
	@RequestMapping("/findByClientUserId")
	@ResponseBody
	public List<ClientUserResource> findByClientUserId(String clientUserId){
		return clientUserResourceService.findByClientUserId(clientUserId);
	}
	
}

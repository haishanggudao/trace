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
import cn.rfidcer.bean.ClientUser;
import cn.rfidcer.bean.ClientUserResource;
import cn.rfidcer.bean.ResultMsg; 
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ClientUserResourceService;
import cn.rfidcer.service.ClientUserService;

/**
* @Title: ClientUserController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 设备账号 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:41:19 
* @version V1.0
 */
@Controller
@RequestMapping("/client_user")
public class ClientUserController {
	
	@Autowired
	private ClientUserService clientUserService;
	
	@Autowired
	private ClientUserResourceService clientUserResourceService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "clientUser";
	} 
	
	@RequestMapping("/list")
	@ResponseBody
	public List<ClientUser> list(Page page){
		return clientUserService.findAll(page,null);
	}
	
	/**
	 * 查询所有的设备账号；
	 * @param page
	 * @param clientUser
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value="system:client_user:list")
	public Map<String, Object> findAllList(Page page,ClientUser clientUser){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", clientUserService.findAll(page,clientUser));		
		return map;
	}
	
	/**
	 * 新增设备账号；
	 * @param clientUser
	 * @param clientRoleIds
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addClientUser",method=RequestMethod.POST)
	@ResponseBody 
	@RequiresPermissions(value={"system:client_user:add","system:client_user:edit"},logical=Logical.OR)
	public ResultMsg addClientUser(ClientUser clientUser, String clientRoleIds, @CurrentUser User currentUser){ 
		return clientUserService.addClientUser(clientUser, clientRoleIds, currentUser);
	}
	
	/**
	 * 删除设备账号；
	 * @param clientUser
	 * @return
	 */
	@RequestMapping(value="/delClientUser",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:client_user:delete"},logical=Logical.OR)
	public ResultMsg delClientUser(ClientUser clientUser){  
		return clientUserService.deleteByClientUserId(clientUser);
	}
	
	/**
	 * 查询权限；
	 * @return
	 */
	@RequestMapping("/findPermissions")
	@ResponseBody
	public List<ClientUserResource> findPermissions(){
		return clientUserResourceService.findAll(null);
	}
	
	/**
	 * 保存新的密码；
	 * @param clientUser
	 * @return
	 */
	@RequestMapping(value="/saveNewPassword",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:client_user:setPassword"},logical=Logical.OR)
	public ResultMsg saveNewPassword(ClientUser clientUser){
		//System.out.println(clientUser);
		return clientUserService.updateClientPassword(clientUser);
	}
	 
	 

}

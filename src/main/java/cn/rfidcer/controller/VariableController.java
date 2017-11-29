package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SysVariable;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.VariableService;

/**   
* @Title: VariableController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 系统参数管理;
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午11:37:11 
* @version V1.0   
*/
@Controller
@RequestMapping("/variable")
public class VariableController {

	@Autowired
	private VariableService variableService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "variable";
	} 
	
	/**
	 * 获取所有参数记录;
	 * @param page
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"system:variable:list"})
	public Map<String,Object> findAllList(Page page){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", variableService.findAllVariables(page));
		return map;
	} 
	
	/**
	 * 修改系统参数;
	 * @param sysVariable
	 * @return
	 */
	@RequestMapping(value="/updateVariable",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:variable:edit"})
	public ResultMsg updateVariable(SysVariable sysVariable){
		return variableService.updateVariableById(sysVariable);
	}
	
	/**
	 * 获取下载url
	 * @return
	 */
	@RequestMapping("/getdownloadurl")
	@ResponseBody
	public Map<String,Object> getdownloadurl(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", variableService.getValByKey("upload"));
		return map;
	}
	
	
}

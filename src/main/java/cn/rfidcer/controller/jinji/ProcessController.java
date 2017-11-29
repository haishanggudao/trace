package cn.rfidcer.controller.jinji;

import java.util.ArrayList;
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
import cn.rfidcer.bean.ProcessItem;
import cn.rfidcer.bean.ProcessMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.jinji.ProcessService;
import cn.rfidcer.util.StringUtil;

/**   金机餐饮加工管理控制器
* @Title: ProcessController.java 
* @Package cn.rfidcer.controller.jinji 
* @Description:
* @author 席志明
* @Copyright Copyright
* @date 2016年7月26日 下午7:37:55 
* @version V1.0   
*/
@Controller
@RequestMapping("/process")
public class ProcessController {
	
	@Autowired
	private ProcessService processService; 
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "jinji/process";
	}
	
	
	/**
	 * 获取所有的加工记录;
	 * @param page
	 * @param processMain
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"operation:process:list","operation:splitProduct:list"},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page, ProcessMain processMain){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<ProcessMain>  lst = processService.getProcessList(page, processMain);
		map.put("rows", lst);		
		return map;
	}
	
	/**
	 * 获取加工明细记录;
	 * @param page
	 * @param processMain
	 * @return
	 */
	@RequestMapping("/findAllItemList")
	@ResponseBody
	@RequiresPermissions(value={"operation:process:list","operation:splitProduct:list"},logical=Logical.OR)
	public Map<String, Object> findAllItemList(Page page, ProcessMain processMain){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<ProcessItem>  lst = new ArrayList<ProcessItem>();
		if(!StringUtil.isBlank(processMain.getProcessMainId())){
			lst=processService.getProcessItemList(page, processMain);
		}
		map.put("rows",lst );		
		return map;
	}
	
	/**
	 * 新增或修改加工记录;
	 * @param processMain
	 * @param processDetail
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addProcess",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"operation:process:add","operation:process:edit","operation:process:record"},logical=Logical.OR)
	public ResultMsg addProcess(ProcessMain processMain,String processDetail, @CurrentUser User currentUser){
		return processService.createOrUpdateProcess(processMain,processDetail, currentUser);
	}
	
	
	/**
	 * 删除加工记录;
	 * @param processMain
	 * @return
	 */
	@RequestMapping(value="/delProcess",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"operation:process:delete"},logical=Logical.OR)
	public ResultMsg delProcess(ProcessMain processMain){
		return processService.delProcess(processMain);
	}
	
	
}

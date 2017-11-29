package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.ApplicationUpdate;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ApplicationUpdateService;

/**   
* @Description:应用管理
* @author 席志明
* @Copyright Copyright
* @date 2016年11月2日 下午3:45:14 
* @version V1.0   
*/
@Controller
@RequestMapping("/application")
public class ApplicationUpdateController {

	@Autowired
	private ApplicationUpdateService applicationUpdateService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "application";
	}
	
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String,Object> findAllList(Page page,ApplicationUpdate applicationUpdate){
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationUpdate> applicationUpdates = applicationUpdateService.findAllList(page,applicationUpdate);
		map.put("rows", applicationUpdates);
		return map;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg addorUpdateApp(ApplicationUpdate applicationUpdate,@CurrentUser User user){
		return applicationUpdateService.addorUpdateApp(applicationUpdate,user);
	}
	
	
}

package cn.rfidcer.controller.codetrace;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.codetrace.CodeQueryLogs;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.codetrace.CodeQueryLogsService;


@Controller
@RequestMapping("/codequery")
public class CodeQueryLogsController {
	
	@Autowired
	private CodeQueryLogsService codeQueryLogsService;
	
	@RequestMapping("/huntCodeTrace")
	@RequiresPermissions(value={"stats:huntcodetrace:*"})
	public String huntCodeTrace(Page page){
		return "codetrace/codeQueryHunt";
	}
	
	@RequestMapping("/findallquerylogs")
	@ResponseBody
	public Map<String, Object> findAllQueryLogs(Page page,CodeQueryLogs codequerylogs){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", codeQueryLogsService.findAllTotalTimes(page, codequerylogs));		
		return map;
	}
	
	@RequestMapping("/findallquerydetails")
	@ResponseBody
	public Map<String, Object> findAllQueryDetails(Page page,CodeQueryLogs codequerylogs){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", codeQueryLogsService.findAllDetails(page, codequerylogs));		
		return map;
	}
}

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

import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.Processor;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProcessorService;

/**   
* @Description:加工者信息
* @author 席志明
* @Copyright Copyright
* @date 2016年10月26日 下午2:13:52 
* @version V1.0   
*/
@Controller
@RequestMapping("/processor")
public class ProcessorControlller {

	@Autowired
	private ProcessorService processorService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(CompanyPage companyPage){
		return "processor";
	}
	
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String,Object> findAllList(Page page,Processor processor){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Processor> processors = processorService.findAllList(page,processor);
		map.put("rows", processors);
		return map;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Processor> list(Page page,Processor processor){
		return processorService.findAllList(page,processor);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	@RequiresPermissions(value={"base:processor:add","base:processor:edit"},logical=Logical.OR)
	public ResultMsg addProcessor(Processor processor){
		return processorService.addorUpdateProcessor(processor);
	}
	
}

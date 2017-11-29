package cn.rfidcer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.ProcessTemplate;
import cn.rfidcer.bean.ProcessTemplateItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SplistTemplateService;

/**   
* @Title: splitTemplateController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 拆分模板
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午11:14:53 
* @version V1.0   
*/
@Controller
@RequestMapping("/splitTemplate")
public class SplitTemplateController {

	@Autowired
	private SplistTemplateService splistTemplateService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "splitTemplate";
	}
	
	/**
	 * 获取所有的拆分模板记录;
	 * @param page
	 * @param processTemplate
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<ProcessTemplate> list(Page page, ProcessTemplate processTemplate){
		return splistTemplateService.getSplitTemplateList(page, processTemplate);
	}
	
	/**
	 * 获取所有的拆分模板记录;
	 * @param page
	 * @param processTemplate
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"operation:splitTemplate:list"},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page, ProcessTemplate processTemplate){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<ProcessTemplate> lst = splistTemplateService.getSplitTemplateList(page, processTemplate);
		map.put("rows", lst);		
		return map;
	}
	
	/**
	 * 获取该加工模板的所有明细记录信息;
	 * @param page
	 * @param templateId
	 * @return
	 */
	@RequestMapping("/findAllItemlist")
	@ResponseBody
	public Map<String, Object> findAllItemlist(Page page, String templateId){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<ProcessTemplateItem> lst = new ArrayList<ProcessTemplateItem>();
		if(templateId!="" && templateId!=null){
			lst = splistTemplateService.findProcessTemplateItemsByTemplateid(page,templateId);
		}
		map.put("rows", lst);		
		return map;
	}
	
	/**
	 * 新增或修改拆分模板;
	 * @param processTemplate
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addSplitTemplate",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequiresPermissions(value={"operation:splitTemplate:add","operation:splitTemplate:edit"},logical=Logical.OR)
	public ResultMsg addSplitTemplate(@RequestBody  ProcessTemplate processTemplate, @CurrentUser User currentUser){
		return splistTemplateService.createOrUpdateSplitTemplate(processTemplate, currentUser);
	}
	
	/**
	 * 删除拆分模板;
	 * @param processTemplate
	 * @return
	 */
	@RequestMapping(value="/delSplitTemplate",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@RequiresPermissions(value={"operation:splitTemplate:delete"},logical=Logical.OR)
	public ResultMsg delSplitTemplate(ProcessTemplate processTemplate){
		return splistTemplateService.delSplitTemplate(processTemplate);
	}
	
	/**
	 * 删除拆分明细;
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value="/delTemplateItem",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResultMsg delTemplateItem(String  templateId){
		return splistTemplateService.delTemplateItem(templateId);
	}
}

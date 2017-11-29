package cn.rfidcer.controller.jinji;

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

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.ProcessTemplate;
import cn.rfidcer.bean.ProcessTemplateItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.jinji.ProcessTemplateService;

/**
* @Title: ProcessTemplateController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 加工模板
* @author xzm
* @Copyright Copyright
* @date 2016年6月28日 下午2:03:32 
* @version V1.0
 */
@Controller
@RequestMapping("/processTemplate")
public class ProcessTemplateController {

	@Autowired
	private ProcessTemplateService processTemplateService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "jinji/processTemplate";
	}
	
	/**
	 * 获取所有的加工模板记录;
	 * @param page
	 * @param processTemplate
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<ProcessTemplate> list(Page page, ProcessTemplate processTemplate){
		return processTemplateService.getProcessTemplateList(page, processTemplate);
	}
	
	/**
	 * 获取所有的加工模板记录;
	 * @param page
	 * @param processTemplate
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"operation:processTemplate:list"},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page, ProcessTemplate processTemplate){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<ProcessTemplate> lst = processTemplateService.getProcessTemplateList(page, processTemplate);
		map.put("rows", lst);		
		return map;
	}
	
	/**
	 * 新增或修改加工模板;
	 * @param processTemplate
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addProcessTemplate",method=RequestMethod.POST)
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequiresPermissions(value={"operation:processTemplate:add","operation:processTemplate:edit"},logical=Logical.OR)
	public ResultMsg addProcessTemplate(ProcessTemplate processTemplate, @CurrentUser User currentUser){
		return processTemplateService.createOrUpdateProcessTemplate(processTemplate, currentUser);
	}
	
	
	/**
	 * 删除加工模板;
	 * @param processTemplate
	 * @return
	 */
	@RequestMapping(value="/delProcessTemplate",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@RequiresPermissions(value={"operation:processTemplate:delete"},logical=Logical.OR)
	public ResultMsg delProcessTemplate(ProcessTemplate processTemplate){
		return processTemplateService.delProcessTemplate(processTemplate);
	}
	
	/**
	 * 删除加工明细;
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value="/delTemplateItem",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResultMsg delTemplateItem(String  templateId){
		return processTemplateService.delTemplateItem(templateId);
	}
	
	
	/**
	 * 依据产品信息来获取加工模板的明细信息;
	 * @param processTemplate
	 * @return
	 */
	@RequestMapping("/findProcessTemplateItemsByProduct")
	@ResponseBody
	public List<ProcessTemplateItem> findProcessTemplateItemsByProduct(ProcessTemplate processTemplate){
		return processTemplateService.findProcessTemplateItemsByProduct(processTemplate);
	}
	
	
	/**根据模板ID查询模板明细
	 * @param templateId
	 * @return
	 */
	@RequestMapping("/findProcessTemplateItems")
	@ResponseBody
	@RequiresPermissions(value={"operation:processTemplate:edit"},logical=Logical.OR)
	public List<ProcessTemplateItem> findProcessItemsByMainid(String templateId){
		return processTemplateService.findProcessTemplateItemsBTemplateid(templateId);
	}
	
	@RequestMapping(value="/findTemplateByDetailId",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ProcessTemplate findTemplateByDetailId(ProcessTemplate processTemplate){
		return processTemplateService.findTemplateByDetailId(processTemplate);
	}
}

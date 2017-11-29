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
import cn.rfidcer.bean.CompanyField;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CompanyFieldService;

/**
* @Title: CompanyFieldController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 企业领域 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:49:23 
* @version V1.0
 */
@Controller
@RequestMapping("/companyfield")
public class CompanyFieldController {
	
	@Autowired
	private CompanyFieldService cfService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "companyfield";
	}
	
	/**
	 * 新增或编辑企业领域；
	 * @param companyfield
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:companyfield:add","system:companyfield:edit"},logical=Logical.OR)
	public ResultMsg save(CompanyField companyfield,@CurrentUser User user){
		companyfield.setStatus("0");
		return cfService.addOrUpdate(companyfield,user);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public List<CompanyField> list(Page page) {
		return cfService.list(page);
	}
	
	/**
	 * 查询全部的企业领域信息；
	 * @param page
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"system:companyfield:list"},logical=Logical.OR)
	public Map<String,Object> findAllList(Page page){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", cfService.list(page));
		return map;
	}
	
	/**
	 * 删除企业领域；
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	@RequiresPermissions(value={"system:companyfield:delete"},logical=Logical.OR)
	public ResultMsg delete(String id){
		return cfService.deleteByKey(id);
	}
	
	/**
	 * 获取企业领域信息; 
	 * @param exceptid
	 * @return
	 */
	@RequestMapping(value="/getcompanyfields")
	@ResponseBody
	public List<CompanyField> getCompanyFields(String exceptid) {
		return cfService.listExcept(exceptid);
	}
}
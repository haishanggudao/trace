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
import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.fruit.FruitCompany;
import cn.rfidcer.bean.jinji.JinJiCompany;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.fruit.FruitCompanyService;
import cn.rfidcer.service.jinji.JinJiCompanyService;


@Controller
@RequestMapping("/jinji_company")
public class JinJiCompanyController {
	
	@Autowired
	private JinJiCompanyService jinjiCompanyService;
	
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"system:company:list"},logical=Logical.OR)
	public Map<String,Object> findAllList(Page page, JinJiCompany jinjiCompany, String findType){
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		map.put("rows", jinjiCompanyService.listQuery( page,jinjiCompany));
		return map;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody 
	public ResultMsg save(@CurrentUser User user, JinJiCompany jinjiCompany){
		return jinjiCompanyService.addOrUpdateCompany(jinjiCompany,user);
	}
	
	@RequestMapping(value = "/getCompanyNatures")
	@ResponseBody
	public List<CommonVariable> getCompanyNatures() {
		List<CommonVariable> list = null;
		try {
			list = jinjiCompanyService.getCompanyNatures();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (list == null) {
				list = new ArrayList<CommonVariable>();
			}
		}
		return list;
	}

}

package cn.rfidcer.controller.fruit;

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
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.fruit.FruitCompanyService;

/**   
* @Title: FruitCompanyController.java 
* @Package cn.rfidcer.controller.fruit 
* @Description: Controller 水果协会-企业信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月23日 下午3:28:45 
* @version V1.0   
*/
@Controller
@RequestMapping("/fruit_company")
public class FruitCompanyController {
	
	@Autowired
	private FruitCompanyService fruitCompanyService;
	
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"system:company:list"},logical=Logical.OR)
	public Map<String,Object> findAllList(Page page, FruitCompany fruitCompany, String findType){
		Map<String,Object> map = new HashMap<String,Object>(); 
		
		map.put("rows", fruitCompanyService.listQuery( page,fruitCompany));
		
		return map;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody 
	public ResultMsg save(@CurrentUser User user, FruitCompany fruitCompany){
		return fruitCompanyService.addOrUpdateCompany(fruitCompany,user);
	}
	
	@RequestMapping(value = "/getCompanyNatures")
	@ResponseBody
	public List<CommonVariable> getCompanyNatures() {
		List<CommonVariable> list = null;
		try {
			list = fruitCompanyService.getCompanyNatures();
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

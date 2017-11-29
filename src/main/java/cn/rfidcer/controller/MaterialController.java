package cn.rfidcer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.rfidcer.bean.CompanyPage;

/**
* @Title: MaterialController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 原料管理 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午11:32:13 
* @version V1.0
 */
@Controller
@RequestMapping("/material")
public class MaterialController {
	
	/**
	 * 菜单跳转页面
	 * @param companyPage 根据数据库动态改变跳转页面
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String index(CompanyPage companyPage){
		return "material";
	}
}
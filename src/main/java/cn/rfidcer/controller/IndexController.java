package cn.rfidcer.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.Resource;
import cn.rfidcer.bean.User;
import cn.rfidcer.service.CompanyService;
import cn.rfidcer.service.ResourceService;
import cn.rfidcer.service.UserService;

import com.alibaba.fastjson.JSONObject;

/**
* @Title: IndexController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller Index 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午10:21:15 
* @version V1.0
 */
@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 默认跳转;
	 * @param currentUser
	 * @param map
	 * @return
	 */
	@RequestMapping("/")
	@AvoidDuplicateSubmission(needSaveToken=true)
	public String index(@CurrentUser User currentUser,ModelMap map,@CookieValue(required=false) String currentCompany,CompanyPage companyPage) {
		Set<String> permissions = userService.findPermissions(currentUser.getUsername());
		List<Resource> menus = resourceService.findMenus(permissions);
		map.put("menus", menus);
		map.put("user", JSONObject.toJSONString(currentUser));
		map.put("userInfo", currentUser);
		List<Company> companys = companyService.getCompanys(currentUser.getId());
		if(companys!=null&&!companys.isEmpty()){
			if(companys.size()==1||currentCompany==null){
				companyPage.setCompanyId(companys.get(0).getCompanyid());
				companyPage.setUrl("/");
			}else{
				Company company = companyService.getCompanyById(currentCompany);
				if(company!=null){
					companyPage.setCompanyId(company.getCompanyid());
					companyPage.setUrl("/");
				}
			}
			
		}
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/header")
	public String header() {
		return "header";
	}

}

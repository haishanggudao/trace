package cn.rfidcer.controller;
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
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.CompanyField;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CompanyFieldService;
import cn.rfidcer.service.CompanyService;

/**
* @Title: CompanyController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 企业信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:46:43 
* @version V1.0
 */
@Controller
@RequestMapping("/mycompany")
public class MyCompanyController {
	
	@Autowired
	private CompanyService cService;
	@Autowired
	private CompanyFieldService cfService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "mycompany";
	}
	
	/**
	 * 新增或编辑企业信息；
	 * @param company
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:mycompany:edit"},logical=Logical.OR)
	public ResultMsg save(Company company,@CurrentUser User user){
		return cService.addOrUpdateCompany(company,user);
	}
	
	/**
	 * 查询所有的企业信息；
	 * @param page
	 * @param company
	 * @param findType
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String,Object> findAllList(Page page,Company company){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<Company> list = new ArrayList<Company>();
		Company item = null;
		try {
			item = cService.getCompanyById(company.getCompanyid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(item != null) {
			list.add(item);
		}
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 获取企业领域信息；
	 * @return
	 */
	@RequestMapping(value="/getcompanyfields")
	@ResponseBody
	public List<CompanyField> getCompanyFields() {
		return cfService.list(null);
	}

	/**
	 * 依据用户ID来获取企业列表；
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/getCompanys",method=RequestMethod.POST)
	@ResponseBody
	public List<Company> getCompanys(@CurrentUser User user){
		return cService.getCompanys(user.getId());
	}

}

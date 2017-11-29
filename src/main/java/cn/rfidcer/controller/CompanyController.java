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
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.CompanyField;
import cn.rfidcer.bean.CompanyPage;
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
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService cService;
	@Autowired
	private CompanyFieldService cfService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(CompanyPage companyPage){
		return "company";
	}
	
	/**
	 * 新增或编辑企业信息；
	 * @param company
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:company:add","system:company:edit"},logical=Logical.OR)
	public ResultMsg save(Company company,@CurrentUser User user){
		return cService.addOrUpdateCompany(company,user);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public List<Company> list(Page page,Company company) {
		return cService.list(page,company);
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
	@RequiresPermissions(value={"system:company:list","system:storeAccount:add","system:storeAccount:edit","system:storeRegister:add","system:storeRegister:edit"},logical=Logical.OR)
	public Map<String,Object> findAllList(Page page,Company company,String findType){
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(findType!=null && findType.equals("2")){
			map.put("rows", cService.listQueryByBlurAnd(page,company));
		}else{
			map.put("rows", cService.listQueryByBlurOr(page,company));
		}
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
	 * 删除企业信息；
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	@RequiresPermissions(value={"system:company:delete"},logical=Logical.OR)
	public ResultMsg delete(String id){
		return cService.deleteByKey(id);
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
	
	/**
	 * 导入企业信息; created by jie.jia at 2016-04-25 10:54
	 * @param uploadImportFile
	 * @param currentUser
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/importcompany", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"system:company:import"},logical=Logical.OR)
	public ResultMsg importSupplier(MultipartFile uploadImportFile, @CurrentUser User currentUser,
			String companyId) { 
		return cService.addImportCompanies(uploadImportFile, currentUser, companyId);
	}

}

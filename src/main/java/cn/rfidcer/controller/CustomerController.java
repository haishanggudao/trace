package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CustomerService;

/**
* @Title: CustomerController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 客户信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:56:43 
* @version V1.0
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService custService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(CompanyPage companyPage) {
		return "customer";
	}

	/**
	 * 新增或编辑客户信息;
	 * @param customers
	 * @param user
	 * @return
	 */
	@RequiresPermissions(value = { "base:customers:add", "base:customers:edit" }, logical = Logical.OR)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg save(Customers customers, @CurrentUser User user) {
		return custService.addOrUpdate(customers, user);
	}

	/**
	 * 查询全部的客户信息;
	 * @param page
	 * @param customers
	 * @param company
	 * @return
	 */
	@RequiresPermissions(value = { "base:customers:list" })
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String, Object> findAllList(Page page, Customers customers, Company company) {
		if(customers != null && customers.getStatus() != null && StringUtils.equalsIgnoreCase("-1",customers.getStatus())) {
			customers.setStatus(null);
		}
		List<Customers> list = custService.list(page, customers, company);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}

	/**
	 * 依据companyID查询客户信息;
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/getCustomersCompanys")
	@ResponseBody
	public List<Customers> getCustomersCompanys(String companyId) {
		return custService.getCustomersCompanys(companyId);
	}
	
	/***
	 * 查询企业的客户列表;
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/findCustomerList")
	@ResponseBody
	public List<Customers> findCustomerList(String companyId) {
		List<Customers> lst = custService.findCustomerList(companyId);
		return lst;
	} 
	
	/***
	 * 查询企业的客户列表;
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/findFilterCustomers")
	@ResponseBody
	public List<Customers> findFilterCustomers(ProductCompany productCompany) {
		List<Customers> lst = custService.findFilterCustomers(productCompany);
		return lst;
	} 
	
	
	/**
	 * 查询企业的客户列表, 包括自身所属公司; added by jie.jia at 2016-07-07 19:29 
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/findCustomerListIncludeCompany")
	@ResponseBody
	public List<Customers> findCustomerListIncludeCompany(String companyId) {
		return custService.findCustomerListIncludeCompany(companyId);
	} 
	
	/**
	 * 删除客户信息;
	 * @param id
	 * @return
	 */
	@RequiresPermissions(value = { "base:customers:delete" })
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResultMsg delete(String id) {
		return custService.deleteByKey(id);
	}

	/**
	 * 导入客户信息;
	 * @param uploadImportFile
	 * @param currentUser
	 * @param companyId
	 * @return
	 */
	@RequiresPermissions(value={"base:customers:import"})
	@RequestMapping(value = "/importCustomers", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addImportCustomers(MultipartFile uploadImportFile, @CurrentUser User currentUser,
			String companyId) {
		return custService.addImportCustomers(uploadImportFile, currentUser, companyId);
	}
	
	@RequestMapping(value = "/findCustomers", method = RequestMethod.POST)
	@ResponseBody
	public List<Customers> findCustomers(Page page,Customers customers){
		return custService.findCustomers(page,customers);
	}
}

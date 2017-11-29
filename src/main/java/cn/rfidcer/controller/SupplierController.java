package cn.rfidcer.controller;

import java.util.ArrayList;
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
import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SupplierService;

/**   
* @Title: SupplierController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 供应商
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午11:15:08 
* @version V1.0   
*/
@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(CompanyPage companyPage) {
		return "supplier";
	}
	
	/**
	 * 新增或修改供应商;
	 * @param supplier
	 * @param user
	 * @return
	 */
	@RequiresPermissions(value={"base:supplier:add","base:supplier:edit"},logical=Logical.OR)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg save(Supplier supplier, @CurrentUser User user) {
		return supplierService.addOrUpdate(supplier,user);
	}

	/**
	 * 获取所有的供应商记录列表;
	 * @param supplier
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Supplier> list(Supplier supplier) {
		return supplierService.list(null,supplier,null);
	}

	/**
	 * 获取所有的供应商记录列表;
	 * @param page
	 * @param supplier
	 * @param company
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:supplier:list"})
	public Map<String, Object> findAllList(Page page, Supplier supplier, Company company) {
		if(supplier != null && supplier.getStatus() != null && StringUtils.equalsIgnoreCase("-1",supplier.getStatus())) {
			supplier.setStatus(null);
		}
		List<Supplier> list = supplierService.list(page,supplier,company);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 删除供应商;
	 * @param id
	 * @return
	 */
	@RequiresPermissions(value={"base:supplier:delete"})
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResultMsg delete(Supplier supplier, @CurrentUser User user) {
		return supplierService.deleteByKey(supplier,user);
	}
	
	@RequiresPermissions(value={
			"base:supplier:add",
			"base:supplier:edit",
			"base:supplier:delete",
			"base:supplier:import"
			},logical=Logical.OR)
	@RequestMapping(value = "/getSupplierCompanys")
	@ResponseBody
	public List<Supplier> getCompanys(String companyId) {
		return supplierService.getSupplierCompanys(companyId);
	}
	
	/**
	 * 导入供应商;
	 * @param uploadImportFile
	 * @param currentUser
	 * @param companyId
	 * @return
	 */
	@RequiresPermissions(value={"base:supplier:import"})
	@RequestMapping(value = "/importsupplier", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg importSupplier(MultipartFile uploadImportFile, @CurrentUser User currentUser,
			String companyId) {
		return supplierService.addImportSupplier(uploadImportFile, currentUser, companyId);
	}

	/**
	 * 获取所有的企业性质
	 * @param nature
	 * @return
	 */
	@RequestMapping(value = "/getcnature")
	@ResponseBody
	public List<CommonVariable> getCNature() {
		List<CommonVariable> list = null;
		try {
			list = supplierService.getCNatures();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (list == null) {
				list = new ArrayList<CommonVariable>();
			}
		}
		return list;
	}
	
	/**
	 * 获取客户分类;
	 * @param cc
	 * @return
	 */
	@RequestMapping(value = "/getccustomercategories")
	@ResponseBody
	public List<CommonVariable> getCCustomerCategories() {
		List<CommonVariable> list = null;
		try {
			list = supplierService.getCCustomerCategories();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (list == null) {
				list = new ArrayList<CommonVariable>();
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/getcadministrativedivision")
	@ResponseBody
	public List<CommonVariable> getCAdministrativeDivision() {
		List<CommonVariable> list = null;
		try {
			list = supplierService.getCAdministrativeDivision();
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
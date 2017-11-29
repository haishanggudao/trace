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
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Slaughterhouse;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SlaughterhouseService;
 
/**   
* @Title: SlaughterhouseController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 屠宰场 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午10:57:53 
* @version V1.0   
*/
@Controller
@RequestMapping("/slaughterhouse")
public class SlaughterhouseController {

	@Autowired
	private SlaughterhouseService sService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "slaughterhouse";
	}

	/**
	 * 新增或修改屠宰场;
	 * @param slaughterhouse
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:slaughterhouse:add","base:slaughterhouse:edit"},logical=Logical.OR)
	public ResultMsg save(Slaughterhouse slaughterhouse, @CurrentUser User user) {
		return sService.addOrUpdate(slaughterhouse,user);
	}

	/**
	 * 获取所有的屠宰场记录;
	 * @param slaughterhouse
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Slaughterhouse> list(Slaughterhouse slaughterhouse) {
			return sService.list(null,slaughterhouse);
	}

	/**
	 * 获取所有的屠宰场记录;
	 * @param page
	 * @param slaughterhouse
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:slaughterhouse:list"})
	public Map<String, Object> findAllList(Page page, Slaughterhouse slaughterhouse) {
		List<Slaughterhouse> list = sService.list(page,slaughterhouse);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}

	/**
	 * 删除屠宰场;
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	@RequiresPermissions(value={"base:slaughterhouse:delete"},logical=Logical.OR)
	public ResultMsg delete(String id) {
		return sService.deleteByKey(id);
	}


	/**
	 * 依据companyID来获取屠宰场记录列表;
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/getslaughterhouseCompanys")
	@ResponseBody
	@RequiresPermissions(value={"base:slaughterhouse:add","base:slaughterhouse:edit"},logical=Logical.OR)
	public List<Slaughterhouse> getslaughterhouseCompanys(String companyId){
		return sService.getslaughterhouseCompanys(companyId);
	}
	
	/**
	 * 导入屠宰场
	 * @param uploadImportFile
	 * @param currentUser
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/importslaughterhouse", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:slaughterhouse:import"})
	public ResultMsg importSlaughterHouse(MultipartFile uploadImportFile, @CurrentUser User currentUser,
			String companyId) {
		return sService.addImportSlaughterhouse(uploadImportFile, currentUser, companyId);
	}

}
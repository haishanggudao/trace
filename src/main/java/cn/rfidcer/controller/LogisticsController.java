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
import cn.rfidcer.bean.Logistics;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.LogisticsService;
import cn.rfidcer.service.VariableService;

/**
* @Title: LogisticsController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 物流企业 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午10:34:24 
* @version V1.0
 */
@Controller
@RequestMapping("/logistics")
public class LogisticsController {
	
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private VariableService vService;

	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "logistics";
	} 
	
	@RequestMapping("/getdownloadurl")
	@ResponseBody
	public Map<String,Object> getdownloadurl(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", vService.getValByKey("upload"));
		return map;
	}
	
	/**
	 * 获取所有的物流企业信息;
	 * @param logistics
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Logistics> list(Logistics logistics){
		return logisticsService.findAll(null,logistics);
	}
	
	/**
	 * 获取所有的物流企业信息;
	 * @param page
	 * @param logistics
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value="base:logistics:list")
	public Map<String, Object> findAllList(Page page,Logistics logistics){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", logisticsService.findAll(page,logistics));		
		return map;
	}
	
	/**
	 * 依据companyID来获取物流企业信息;
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/findByUserCompanyId")
	@ResponseBody
	public List<Logistics> findByUserCompanyId(String companyId){
		return logisticsService.findByUserCompanyId(companyId);
	}
	
	/**
	 * 新增或修改物流企业;
	 * @param logistics
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addLogistics",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:logistics:add","base:logistics:edit"},logical=Logical.OR)
	public ResultMsg addLogistics(Logistics logistics, @CurrentUser User currentUser){ 
		return logisticsService.createOrUpdateLogistics(logistics, currentUser);
	}
	
	/**
	 * 删除物流企业;
	 * @param logistics
	 * @return
	 */
	@RequestMapping(value="/delLogistics",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value="base:logistics:delete")
	public ResultMsg delLogistics(Logistics logistics){   
		return logisticsService.deleteByLogisticsId(logistics);
	}
	
	/**
	 * 获取物流企业列表;
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/getLogisticsCompanys",method=RequestMethod.POST)
	@ResponseBody
	public List<Logistics> getLogisticsCompanys(String companyId){
		return logisticsService.getLogisticsCompanys(companyId);
	}
	
	/**
	 * 导入物流企业;
	 * @param uploadImportFile
	 * @param currentUser
	 * @param companyId
	 * @return
	 */
	@RequiresPermissions(value={"base:logistics:import"})
	@RequestMapping(value = "/importlogistics", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addImportLogistics(MultipartFile uploadImportFile, @CurrentUser User currentUser,
			String companyId) {
		return logisticsService.addImportLogistics(uploadImportFile, currentUser, companyId);
	}
}

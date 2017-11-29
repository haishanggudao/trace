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

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CompanyService;
import cn.rfidcer.service.OutstockService;
import cn.rfidcer.service.SaleOrderService;

/**
* @Title: OutstockMainProductController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品出库 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午11:44:56 
* @version V1.0
 */
@Controller
@RequestMapping("/outstockmainProduct")
public class OutstockMainProductController {
	
	@Autowired
	private OutstockService oService;
	@Autowired
	private CompanyService cService;
	@Autowired
	private SaleOrderService soService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "outstockmainProduct";
	}
	
	/**
	 * 新增或修改产品出库记录;
	 * @param outstockmain
	 * @param user
	 * @param outstockItems
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequiresPermissions(value={"trans:OutstockMainProduct:add", "trans:OutstockMainProduct:edit" }, logical=Logical.OR)
	public ResultMsg save(OutstockMain outstockmain,@CurrentUser User user,String outstockItems){
		ResultMsg msg  = new ResultMsg();
		if(outstockmain.getConsignor()==null || outstockmain.getCompanyId()==null || outstockmain.getLogisticsId()==null){
			msg.setCode("0");
			msg.setMsg("亲,发现您的数据不完整！");
			return msg;
		}
		msg = oService.saveProductOutStock(outstockmain, user, outstockItems);
		return msg;
	}
	
	/**
	 * 获取所有的产品出库记录;
	 * @param page
	 * @param user
	 * @param outstockMain
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"trans:outstockmainProduct:list" }, logical=Logical.OR)
	public Map<String,Object> findAllList(Page page,@CurrentUser User user,OutstockMain outstockMain){
		List<OutstockMain> listOutstockMain = null;
		listOutstockMain = oService.findAllProductOutstock(page,outstockMain);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", listOutstockMain);
		return map;
	}
	
	/**
	 * 依据出库单ID来获取销售单ID;
	 * @param outstockMainId
	 * @return
	 */
	@RequestMapping("/findSaleOrderIdsBySaleOutstock")
	@ResponseBody
	public String findSaleOrderIdsBySaleOutstock(String outstockMainId){
		return oService.findSaleOrderIdsBySaleOutstock(outstockMainId);
	}
	
	/**
	 * 删除产品出库记录;
	 * @param outstockMainId
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	@RequiresPermissions(value={"trans:outstockmainProduct:delete" }, logical=Logical.OR)
	public ResultMsg delete(String outstockMainId){
		return oService.deleteOutstockMainByKey(outstockMainId);
	}
	
	/**
	 * 获取销售单记录列表;
	 * @return
	 */
	@RequestMapping(value="/getsaleorders")
	@ResponseBody
	public List<SaleOrder> getSaleOrders() {
		return soService.list(null);
	}
	
	/**
	 * 获取企业信息列表;
	 * @return
	 */
	@RequestMapping(value="/getcompanys")
	@ResponseBody
	public List<Company> getcompanys() {
		return cService.list(null);
	}
	
}
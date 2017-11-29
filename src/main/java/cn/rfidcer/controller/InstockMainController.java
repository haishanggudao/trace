package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.PurchaseInstockAssoc;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CompanyService;
import cn.rfidcer.service.InstockService;
import cn.rfidcer.service.PurchaseOrderService;
import cn.rfidcer.service.SupplierService;

/**
* @Title: InstockMainController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 商品入库; 
* @author JUGUANGXING & jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午10:29:52 
* @version V1.0
 */
@Controller
@RequestMapping("/instockmain")
public class InstockMainController {
	
	@Autowired
	private InstockService iService;
	@Autowired
	private CompanyService cService;
	@Autowired
	private SupplierService sService;
	@Autowired
	private PurchaseOrderService poService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "instockmain";
	}
	
	/**
	 * 新增或修改商品入库记录;
	 * @param instockmain
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"trans:instockmain:add", "trans:instockmain:edit" }, logical=Logical.OR)
	public ResultMsg save(@RequestBody InstockMain instockmain,@CurrentUser User user){
		String purchaseids = instockmain.getPurchaseOrderId();
		ResultMsg msg  = new ResultMsg();
		if(instockmain.getConsignee()==null || instockmain.getCompanyId()==null ){
			msg.setCode("0");
			msg.setMsg("亲,发现您的数据不完整！");
			return msg;
		}
		if(StringUtils.isEmpty(purchaseids)) {
			instockmain.setPurchaseOrderId("poid");
		}
		msg = iService.addOrUpdate(instockmain,user);
		return msg;
	}
	
	/**
	 * 获取所有的商品入库列表;
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody 
	public List<InstockMain> list(Page page,@CurrentUser User user) {
		List<InstockMain> listInstockMain = null;
		String companyId = null;
		List<Company> companys = cService.getCompanys(user.getId());
		if(companys != null && companys.size() > 0) {
			Company company = companys.get(0);
			companyId = company.getCompanyid();
		}
		if(StringUtils.isEmpty(companyId)) {
			listInstockMain = iService.listInstockMain(page);
		} else {
			listInstockMain = iService.listQCompanyId(page,companyId);
		}
		for (InstockMain instockMain : listInstockMain) {
			List<PurchaseInstockAssoc> pias = iService.listPurchaseOrderAssocByInstockMainId(instockMain.getInstockMainId());
			String[] purchaseOrderIds = null;
			if(pias != null) {
				purchaseOrderIds = new String[pias.size()];
				int i = 0;
				for (PurchaseInstockAssoc pia : pias) {
					purchaseOrderIds[i] = pia.getPurchaseOrderId();
					i++;
				}
			}
			String purchaseOrderId = org.springframework.util.StringUtils.arrayToDelimitedString(purchaseOrderIds, ",");
			instockMain.setPurchaseOrderId(purchaseOrderId);
		}
		return listInstockMain;
	}
	/**
	 * 获取所有的商品入库列表;
	 * @author JUGUANGXING
	 * 2016年4月19日 14:16:15
	 * @param page
	 * @param user
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"trans:instockmain:list","trans:purchase_manager:list" }, logical=Logical.OR)
	public Map<String,Object> findAllList(Page page,@CurrentUser User user,InstockMain instockMain){
		List<InstockMain> listInstockMain = iService.findAllList(page,instockMain);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", listInstockMain);
		return map;
	}
	
	/**
	 * 删除商品入库记录;
	 * @param instockMainId
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	@RequiresPermissions(value={"trans:instockmain:delete" }, logical=Logical.OR)
	public ResultMsg delete(String instockMainId){
		return iService.deleteInstockMainByKey(instockMainId);
	}
	
	/**
	 * 获取供应商列表;
	 * @return
	 */
	@RequestMapping(value="/getsuppliers")
	@ResponseBody
	public List<Supplier> getsuppliers() {
		return sService.list(null,null);
	}
	
	/**
	 * 获取采购单列表;
	 * @return
	 */
	@RequestMapping(value="/getpurchaseorders")
	@ResponseBody
	public List<PurchaseOrder> getPurchaseOrders() {
		return poService.listAll();
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
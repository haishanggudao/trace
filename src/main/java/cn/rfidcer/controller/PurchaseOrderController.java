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
import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.PurchaseOrderService;

/**
* @Title: PurchaseOrderController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 采购单 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午10:19:46 
* @version V1.0
 */
@Controller
@RequestMapping("/purchase_order")
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "purchaseOrder";
	}
	
	/**
	 * 新增或修改采购单;
	 * @param user
	 * @param purchaseOrder
	 * @param items
	 * @return
	 */
	@RequestMapping(value="/addOrUpdatePurchaseOrder",method=RequestMethod.POST)
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true) 
	@RequiresPermissions(value={"trans:purchase_order:add",	"trans:purchase_order:edit"	},logical=Logical.OR)
	public ResultMsg addOrUpdatePurchaseOrder(@CurrentUser User user, PurchaseOrder purchaseOrder,String items){
		return purchaseOrderService.addOrUpdate(purchaseOrder, user, items);
	}
	
	/**
	 * 获取所有的采购单记录列表;
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public List<PurchaseOrder> list(Page page,PurchaseOrder purchaseOrder){
		return purchaseOrderService.list(page, purchaseOrder);
	}
	
	/**
	 * 获取所有的采购单记录列表;
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"trans:purchase_order:list"	})
	public Map<String, Object> findAllList(Page page,PurchaseOrder purchaseOrder){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", purchaseOrderService.list(page, purchaseOrder));		
		return map;
	}
	
	/**
	 * 获取所有的采购单记录列表;
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	@RequestMapping("/findAllQueryList")
	@ResponseBody
	public Map<String, Object> findAllQueryList(Page page,PurchaseOrder purchaseOrder) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("rows", purchaseOrderService.listByAdvancedQuery(page, purchaseOrder));

		return map;
	}
	
	/**
	 * 获取采购单明细记录;
	 * @param purchaseOrderId
	 * @return
	 */
	@RequestMapping(value="/itemlist",method=RequestMethod.POST)
	@ResponseBody
	public List<PurchaseItem> list(String purchaseOrderId){
		return purchaseOrderService.findPurchaseItemsByOrderId(purchaseOrderId);
	}
	
	/**
	 * 获取采购单明细记录;
	 * @param page
	 * @param user
	 * @param purchaseOrderId
	 * @return
	 */
	@RequestMapping(value="/findAllItemlist")
	@ResponseBody
	public Map<String, Object>  findAllItemlist(Page page,@CurrentUser User user,String purchaseOrderId){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<PurchaseItem>  lstpo = purchaseOrderService.findPurchaseItemsByOrderIdOderPage(page,purchaseOrderId);
		map.put("rows", lstpo);		
		return map;
	}
	
	/**
	 * 依据companyID获取采购单记录列表;
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/findAllPurchaseOrderId")
	@ResponseBody
	public List<PurchaseOrder> findAllPurchaseOrderId(String companyId) {
		List<PurchaseOrder> lst  = purchaseOrderService.findAllPurchaseOrderId(companyId);
		return lst;
	}
	
	/**
	 * 删除采购单;
	 * @param purchaseOrder
	 * @return
	 */
	@RequestMapping(value="/delPurchaseOrder",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={
			"trans:purchase_order:delete"
			},logical=Logical.OR)
	public ResultMsg delPurchaseOrder(PurchaseOrder purchaseOrder){
		return purchaseOrderService.deletePurchaseOrder(purchaseOrder);
	}
	
	
}

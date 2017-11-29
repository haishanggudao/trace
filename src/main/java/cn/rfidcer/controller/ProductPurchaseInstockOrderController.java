package cn.rfidcer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.InstockPurchaseItem;
import cn.rfidcer.bean.PurchaseInstockOrder;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.AreaInfoMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CompanyService;
import cn.rfidcer.service.SupplierService;
import cn.rfidcer.service.PurchaseOrderInstockService;

/**   
*    
* 项目名称：trace   
* 类名称：ProductPurchaseInstockOrderController   
* 类描述：   产品采购入库管理
* 创建人：JUGUANGXING  
* 创建时间：2016年3月9日 下午3:21:19   
* 修改人：honest   
* 修改时间：2016年3月9日 下午3:21:19   
* 修改备注：   
* @version    
*    
*/
@Controller
@RequestMapping("/productPurchaseInstockOrder")
public class ProductPurchaseInstockOrderController {
	

	@Autowired
	private CompanyService cService;
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	@Autowired
	private SupplierService sService;
	@Autowired
	private PurchaseOrderInstockService poService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "productPurchaseInstockOrder";
	}
	
	/**
	 * 新增或修改产品采购入库;
	 * @param purchaseInstockOrder
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequiresPermissions(value={"trans:productPurchaseInstockOrder:add",	"trans:productPurchaseInstockOrder:edit"	},logical=Logical.OR)
	public ResultMsg save(@RequestBody PurchaseInstockOrder purchaseInstockOrder,@CurrentUser User user){
		ResultMsg addOrUpdate = poService.addOrUpdate(purchaseInstockOrder, user);
		return addOrUpdate;
	}
	
	/**
	 * 获取区信息列表;
	 * @param page
	 * @param user
	 * @param ai
	 * @return
	 */
	@RequestMapping(value="/areaList")
	@ResponseBody
	public List<AreaInfo> list(Page page,@CurrentUser User user,AreaInfo ai) {
		List<AreaInfo> lst = areaInfoMapper.findAllCatgNameAndId(page, ai);
		return lst;
	}
	
	/**
	 * 获取采购入库单信息;
	 * @param page
	 * @param user
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public List<PurchaseInstockOrder> list(Page page,@CurrentUser User user,String companyId) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCompanyId(companyId);
		List<PurchaseInstockOrder> lstPo = poService.listPurchaseInstockOrder(page, purchaseOrder);
		return lstPo;
	}
	
	/**
	 * 获取采购入库单信息;
	 * @param page
	 * @param user
	 * @param companyId
	 * @param instockType
	 * @return
	 */
	@RequestMapping(value="/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"trans:productPurchaseInstockOrder:list"})
	public Map<String,Object> findAllList(Page page,@CurrentUser User user,String companyId,String instockType) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<PurchaseInstockOrder> lstPo = poService.listPurchaseInstockOrder(page, companyId, instockType);
        map.put("rows",lstPo);
        return map;
	}
	
	/**
	 * 获取采购明细记录信息;
	 * @param page
	 * @param user
	 * @param purchaseOrderId
	 * @return
	 */
	@RequestMapping(value="/findAllItemlist")
	@ResponseBody
	@RequiresPermissions(value={"trans:productPurchaseInstockOrder:list"})
	public Map<String,Object> findAllItemlist(Page page,@CurrentUser User user,String purchaseOrderId) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<InstockPurchaseItem> lstpo = new ArrayList<InstockPurchaseItem>();
		if(purchaseOrderId!="" && purchaseOrderId!=null){
			lstpo = poService.findInstockItemsByOrderId(page,purchaseOrderId);
		}
        map.put("rows",lstpo);
        return map;
	}
	
	/**
	 * 删除采购入库单;
	 * @param purchaseOrderId
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	@RequiresPermissions(value={"trans:productPurchaseInstockOrder:delete"})
	public ResultMsg delete(String purchaseOrderId){
		return poService.delPurchaseInstockOrder(purchaseOrderId);
	}
	
	/**
	 * 删除采购明细;
	 * @param purchaseItemId
	 * @param instockItemId
	 * @param goodsId
	 * @param num
	 * @return
	 */
	@RequestMapping(value="/deleteitem",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteitem(String purchaseItemId,String instockItemId,String goodsId,String num){
		return poService.deletePurchaseItem(purchaseItemId,instockItemId,goodsId,num);
	}
	
	/**
	 * 获取供应商信息列表;
	 * @return
	 */
	@RequestMapping(value="/getsuppliers")
	@ResponseBody
	public List<Supplier> getsuppliers() {
		return sService.list(null,null);
	}
	
	/**
	 * 获取采购单记录列表;
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
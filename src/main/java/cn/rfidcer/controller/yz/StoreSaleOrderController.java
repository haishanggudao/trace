package cn.rfidcer.controller.yz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.yz.StoreSaleItemService;
import cn.rfidcer.service.yz.StoreSaleOrderService;


/**   
 * @Title: StoreSaleOrderController.java 
 * @Package cn.rfidcer.controller.yz 
 * @Description: 门店销售单Controller
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月11日 下午2:13:27 
 * @version V1.0   
*/
@Controller
@RequestMapping("/storeSaleOrder")
public class StoreSaleOrderController {

	@Autowired
	private StoreSaleOrderService storeSaleOrderService;
	
	@Autowired
	private StoreSaleItemService storeSaleItemService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "storeSaleOrder";
	}
	
	

	/**
	 * 查询门店销售记录
	 * @param page
	 * @param storeSaleOrder
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"trans:storeSaleOrder:list"})
	public Map<String, Object> findAllList(Page page,StoreSaleOrder storeSaleOrder){
		List<StoreSaleOrder> lst =storeSaleOrderService.findAll(page, storeSaleOrder);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", lst);		
		return map;
	}
	
	@RequestMapping("/findAllListItem")
	@ResponseBody
	@RequiresPermissions(value={"trans:storeSaleOrder:list"})
	public Map<String, Object> findAllListItem(Page page,StoreSaleItem storeSaleItem){
		List<StoreSaleItem> lst =storeSaleItemService.findAll(page, storeSaleItem);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", lst);		
		return map;
	}
	
}

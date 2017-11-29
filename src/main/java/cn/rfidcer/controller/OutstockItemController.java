package cn.rfidcer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsService;
import cn.rfidcer.service.OutstockService;

/**
* @Title: OutstockItemController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 商品出库 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午11:33:58 
* @version V1.0
 */
@Controller
@RequestMapping("/outstockitem")
public class OutstockItemController {
	
	@Autowired
	private OutstockService oService;
	@Autowired
	private GoodsService gService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "outstockitem";
	}
	
	/**
	 * 获取所有的商品出库记录;
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public List<OutstockItem> list(Page page) {
		return oService.listOutstockItem(page);
	}

	/**
	 * 获取所有的商品出库记录;
	 * @param page
	 * @param user
	 * @param outstockMainId
	 * @return
	 */
	@RequestMapping(value="/findAllList")
	@ResponseBody
	public Map<String,Object> findAllList(Page page,@CurrentUser User user,String outstockMainId){
		List<OutstockItem> ois = oService.findAllItemsList(page,outstockMainId);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", ois);
		return map;
	}
	
	/**
	 * 获取所有的产品列表;
	 * @param page
	 * @param user
	 * @param outstockMainId
	 * @return
	 */
	@RequestMapping(value="/findAllProductItems")
	@ResponseBody
	public Map<String,Object> findAllProductItems(Page page,@CurrentUser User user,String outstockMainId){
		List<OutstockItem> ois = oService.findAllProductItems(page,outstockMainId);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", ois);
		return map;
	}	
	
	
	
	@RequestMapping(value="/list3")
	@ResponseBody
	public List<OutstockItem> list3(String saleorderid) {
		List<OutstockItem> ois = new ArrayList<OutstockItem>();
		List<OutstockMain> oms = oService.listOutstockMainBySaleOrderId(saleorderid);
		if(oms != null && !oms.isEmpty()) {
			OutstockMain om = oms.get(0);
			ois = oService.listOutstockItemWithOutstockMainId(null,om.getOutstockMainId());
			for (OutstockItem outstockItem : ois) {
				Goods goods = gService.getByKey(outstockItem.getGoodsId());
				if(goods != null) {
					outstockItem.setGoodsName(goods.getGoodsName());
				}
			}
		}
		return ois;
	}
	
	/**
	 * 删除商品出库记录;
	 * @param outstockItemId
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultMsg delete(String outstockItemId){
		return oService.deleteOutstockItemByKey(outstockItemId);
	}
	
	/**
	 * 获取商品列表;
	 * @return
	 */
	@RequestMapping(value="/getgoods")
	@ResponseBody
	public List<Goods> getGoods() {
		return gService.list(null);
	}
	
	/**
	 * 获取出库主单记录;
	 * @return
	 */
	@RequestMapping(value="/getoutstockmains")
	@ResponseBody
	public List<OutstockMain> getInstockMains() {
		return oService.listOutstockMain(null);
	}
}
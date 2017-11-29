package cn.rfidcer.controller;

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
import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.InstockPurchaseItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsService;
import cn.rfidcer.service.InstockItemService;
import cn.rfidcer.service.InstockService;

/**
* @Title: InstockItemController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 商品入库; 
* @author JUGUANGXING
* @Copyright Copyright
* @date 2016年6月28日 上午10:22:56 
* @version V1.0
 */
@Controller
@RequestMapping("/instockitem")
public class InstockItemController {
	
	@Autowired
	private InstockService iService;
	@Autowired
	
	private InstockItemService itService;
	@Autowired
	private GoodsService gService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "instockitem";
	}
	
	/**
	 * 获取所有的商品入库信息;
	 * @author JUGUANGXING
	 * 2016年4月19日 14:16:15
	 * @param page
	 * @param user
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String,Object> findAllList(Page page,@CurrentUser User user,String instockMainId){
		InstockItem  instockItem = new InstockItem();
		instockItem.setInstockMainId(instockMainId);
		List<InstockPurchaseItem> lstinstockItem = itService.findAllListByParamAnd(page,instockItem);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", lstinstockItem);
		return map;
	}
	
	/**
	 * 获取所有的商品入库信息;
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public List<InstockItem> list(Page page) {
		return iService.listInstockItem(page);
	}
	
	/**
	 * 删除商品入库记录;
	 * @param instockItemId
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultMsg delete(String instockItemId){
		return itService.deleteByKey(instockItemId);
	}
	
	/**
	 * 获取商品信息;
	 * @return
	 */
	@RequestMapping(value="/getgoods")
	@ResponseBody
	public List<Goods> getGoods() {
		return gService.list(null);
	}
	
	/**
	 * 获取商品入库主单;
	 * @return
	 */
	@RequestMapping(value="/getinstockmains")
	@ResponseBody
	public List<InstockMain> getInstockMains() {
		return iService.listInstockMain(null);
	}
}
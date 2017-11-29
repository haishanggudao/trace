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

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsStockService;

/**
* @Title: GoodsStockController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 商品库存; 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午10:13:52 
* @version V1.0
 */
@Controller
@RequestMapping("/goodsStock")
public class GoodsStockController {
	
	@Autowired
	private GoodsStockService goodsStockService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "goodsStock";
	}
	
	/**
	 * 获取商品库存列表;
	 * @param page
	 * @param user
	 * @param goodsStock
	 * @return
	 */
	@RequestMapping(value="/findAllItemlist")
	@ResponseBody
	@RequiresPermissions(value = { "trans:goodsStock:list", "trans:productStock:list"}, logical = Logical.OR)
	public Map<String, Object>  findAllItemlist(Page page,@CurrentUser User user, GoodsStock goodsStock){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<GoodsStock>  lstpo = goodsStockService.findByProductIdStandDetailId(page,goodsStock);
		map.put("rows", lstpo);		
		return map;
	}

	/**
	 * 获取商品库存列表;
	 * @param page
	 * @param user
	 * @param goodsStock
	 * @return
	 */
	@RequestMapping(value="/findAllItem")
	@ResponseBody
	@RequiresPermissions(value = { "trans:goodsStock:list", "trans:productStock:list"}, logical = Logical.OR)
	public List<GoodsStock>  findAllItem(Page page,@CurrentUser User user, GoodsStock goodsStock){
		List<GoodsStock>  lstpo = goodsStockService.findByProductIdStandDetailId(page,goodsStock);
		return lstpo;
	}
	
	/**
	 * 更新商品库存;
	 * @param user
	 * @param goodsStock
	 * @return
	 */
	@RequestMapping(value="/updateGoodsStock")
	@ResponseBody
	@RequiresPermissions(value="trans:goodsStock:edit")
	public ResultMsg updateGoodsStock(@CurrentUser User user,GoodsStock goodsStock){
		return goodsStockService.updateGoodsStock(user, goodsStock);
	}

}

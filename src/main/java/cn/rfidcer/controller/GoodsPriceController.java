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
import cn.rfidcer.bean.GoodsPrice;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsPriceService;

/**
* @Title: GoodsPriceController.java 
* @Package cn.rfidcer.controller 商品价格
* @Description: Controllers  
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午9:42:01 
* @version V1.0
 */
@Controller
@RequestMapping("/goodsprice")
public class GoodsPriceController {

	@Autowired
	private GoodsPriceService goodsPriceService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "goodsprice";
	}
	
	/**
	 * 获取所有的商品价格信息;
	 * @param page
	 * @param productStandardDetail
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:goodsprice:list"})
	public Map<String, Object> findAllList(Page page,ProductStandardDetail productStandardDetail){
		List<ProductStandardDetail> lst =goodsPriceService.findAll(page, productStandardDetail);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", lst);		
		return map;
	}
	
	/**
	 * 查询没有商品价格规格明细;
	 * @param page
	 * @param productStandardDetail
	 * @return
	 */
	@RequestMapping("/findNoPriceList")
	@ResponseBody
	public List<ProductStandardDetail> findNoPriceList(Page page,ProductStandardDetail productStandardDetail){
		List<ProductStandardDetail> lst = goodsPriceService.findNoPriceList(productStandardDetail);	
		return lst;
	}
	
	/**
	 * 获取产品ID列表;
	 * @param page
	 * @param productStandardDetail
	 * @return
	 */
	@RequestMapping("/findProductIdList")
	@ResponseBody
	public Map<String, Object> findAllProductIdList(Page page,ProductStandardDetail productStandardDetail){
		List<GoodsPrice> lst = goodsPriceService.findProductIdList(page,productStandardDetail);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", lst);		
		return map;
	}
	
	/**
	 * 获取产品规格ID列表;
	 * @param page
	 * @param productStandardDetail
	 * @return
	 */
	@RequestMapping("/findProductStandardIdList")
	@ResponseBody
	public List<GoodsPrice> findProductStandardIdList(ProductStandardDetail productStandardDetail){
		List<GoodsPrice> lst = goodsPriceService.findProductStandardIdList(productStandardDetail);	
		return lst;
	} 
	
	/**新增或修改产品规格明细价格
	 * @param productStandardDetail
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addgoodsprice",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:goodsprice:add","base:goodsprice:edit"},logical=Logical.OR)
	public ResultMsg addgoodsprice(ProductStandardDetail productStandardDetail,@CurrentUser User currentUser){
		return goodsPriceService.createOrUpdateGoodsPrice(productStandardDetail,currentUser);
	}
	
	/**删除产品规格明细价格
	 * @param productCategory
	 * @return
	 */
	@RequestMapping(value="/delGoodsPrice",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard_detail:del"})
	public ResultMsg delGoodsPrice(ProductStandardDetail productStandardDetail,@CurrentUser User currentUser){
		return goodsPriceService.delGoodsPrice(productStandardDetail, currentUser);
	}
}

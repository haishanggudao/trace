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
import cn.rfidcer.bean.ProductStandardRate;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStandardRateService;
 
/**
* @Title: ProductStandardRateController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品规格转化控制器
* @author xzm
* @Copyright Copyright
* @date 2016年6月29日 上午9:50:19 
* @version V1.0
 */
@Controller
@RequestMapping("/product_standard_rate")
public class ProductStandardRateController {

	@Autowired
	private ProductStandardRateService rateService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "productStandardRate";
	}
	
	/**
	 * 产品规格转化列表
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<ProductStandardRate> list(Page page,ProductStandardRate rate,String productCategoryId){
		return rateService.findAll(page, rate,productCategoryId);
	}
	
	/**
	 * 获取所有的产品规格转化列表;
	 * @param page
	 * @param rate
	 * @param productCategoryId
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard_rate:list"})
	public Map<String, Object> findAllList(Page page,ProductStandardRate rate,String productCategoryId){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", rateService.findAll(page, rate, productCategoryId));		
		return map;
	}
	
	/**新增或修改产品规格转化
	 * @param productStandardDetail
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addProductStandardRate",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard_rate:create","base:product_standard_rate:update"},logical=Logical.OR)
	public ResultMsg addProductStandardRate(ProductStandardRate rate,@CurrentUser User currentUser){
		return rateService.createOrUpdateProductStandardRate(rate,currentUser);
	}
	

	/**删除产品规格转化
	 * @param productCategory
	 * @return
	 */
	@RequestMapping(value="/delProductStandardRate",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard_rate:del"})
	public ResultMsg delProductStandardRate(ProductStandardRate rate){
		return rateService.delProductStandardRate(rate);
	}
}

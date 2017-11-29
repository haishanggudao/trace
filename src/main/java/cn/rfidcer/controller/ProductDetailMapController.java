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
import cn.rfidcer.bean.ProductDetailMap;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductDetailMapService;

/**
* @Title: ProductDetailMapController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品附加属性 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 下午2:34:09 
* @version V1.0
 */
@Controller
@RequestMapping("/product_detail_map")
public class ProductDetailMapController {
	
	@Autowired
	private ProductDetailMapService productDetailMapService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "productDetailMap";
	}
	
	/**
	 * 获取所有的产品附加属性信息列表;
	 * @param page
	 * @param productDetailMap
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody 
	public List<ProductDetailMap> list(Page page,ProductDetailMap productDetailMap){ 
		return productDetailMapService.getProductDetailMapList(page, productDetailMap);
	}
	
	/**
	 * 获取所有的产品附加属性信息列表;
	 * @param page
	 * @param productDetailMap
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={
			"base:product_detail_map:list"
			},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page,ProductDetailMap productDetailMap){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", productDetailMapService.getProductDetailMapList(page, productDetailMap));		
		return map;
	}
	
	/**
	 * 新增或修改产品附加属性信息;
	 * @param user
	 * @param productDetailMap
	 * @return
	 */
	@RequestMapping(value="/addProductDetailMap",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={
			"base:product_detail_map:add",
			"base:product_detail_map:edit"
			},logical=Logical.OR)
	public ResultMsg addProduct(@CurrentUser User user, ProductDetailMap productDetailMap){ 
		return productDetailMapService.addOrUpdateProductDetailMap(productDetailMap, user);
	}
	
	/**
	 * 删除产品附加属性信息;
	 * @param productDetailMap
	 * @return
	 */
	@RequestMapping(value="/delProductDetailMap",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={ "base:product_detail_map:delete" },logical=Logical.OR)
	public ResultMsg delProductDetailMap(ProductDetailMap productDetailMap){
	    return productDetailMapService.delProductDetailMap(productDetailMap);
	}

}

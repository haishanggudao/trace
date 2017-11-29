package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStockService;

/**
* @Title: ProductStockController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品库存; 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午9:51:11 
* @version V1.0
 */
@Controller
@RequestMapping("/productStock")
public class ProductStockController {
	
	@Autowired
	private ProductStockService productStockService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "productStock";
	}
	
	/**
	 * 获取所有的产品库存信息;
	 * @param page
	 * @param productStock
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody 
	public Map<String, Object> findAllList(Page page,ProductStock productStock){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", productStockService.list(page, productStock));		
		return map;
	}
	
	/**
	 * 获取所有的产品库存信息;
	 * @param page
	 * @param productStock
	 * @return
	 */
	@RequestMapping("/findAllQueryList")
	@ResponseBody
	public Map<String, Object> findAllQueryList(Page page,ProductStock productStock) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("rows", productStockService.listByAdvancedQuery(page, productStock));

		return map;
	}

}

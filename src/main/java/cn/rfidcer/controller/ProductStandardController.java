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
import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStandardService;
 
/**
* @Title: ProductStandardController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品规格控制器
* @author xzm
* @Copyright Copyright
* @date 2016年6月29日 上午9:47:18 
* @version V1.0
 */
@Controller
@RequestMapping("/product_standard")
public class ProductStandardController {

	@Autowired
	private ProductStandardService standardService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "productStandard";
	}
	
	/**
	 * 产品规格列表
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<ProductStandard> list(Page page,ProductStandard productStandard){
		return standardService.findAll(page, productStandard);
	}
	
	/**
	 * 获取所有的产品规格列表;
	 * @param page
	 * @param productStandard
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard:list"})
	public Map<String, Object> findAllList(Page page,ProductStandard productStandard){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", standardService.findAll(page, productStandard));		
		return map;
	}
	
	/**
	 * 新增或修改产品规格信息;
	 * @param productStandard
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addProductStandard",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard:create","base:product_standard:update"},logical=Logical.OR)
	public ResultMsg addProductStandard(ProductStandard productStandard,@CurrentUser User currentUser){
		return standardService.createOrUpdateProductStandard(productStandard,currentUser);
	}
	

	/**删除产品规格
	 * @param productCategory
	 * @return
	 */
	@RequestMapping(value="/delProductStandard",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard:del"})
	public ResultMsg ProductStandard(ProductStandard productStandard){
		return standardService.delProductStandard(productStandard);
	}
}

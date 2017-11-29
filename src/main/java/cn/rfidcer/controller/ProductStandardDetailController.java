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
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStandardDetailService;
 
/**
* @Title: ProductStandardDetailController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品规格明细控制器
* @author xzm
* @Copyright Copyright
* @date 2016年6月29日 上午9:49:30 
* @version V1.0
 */
@Controller
@RequestMapping("/product_standard_detail")
public class ProductStandardDetailController {

	@Autowired
	private ProductStandardDetailService standardDetailService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(CompanyPage companyPage){
		return "productStandardDetail";
	}
	
	/**
	 * 产品规格明细列表
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<ProductStandardDetail> list(Page page,ProductStandardDetail productStandardDetail){
		return standardDetailService.findAll(page, productStandardDetail);
	}
	
	/**
	 * 获取所有的产品规格明细列表;
	 * @param page
	 * @param productStandardDetail
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard_detail:list"})
	public Map<String, Object> findAllList(Page page,ProductStandardDetail productStandardDetail){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", standardDetailService.findAll(page, productStandardDetail));		
		return map;
	}
	
	/**新增或修改产品规格明细
	 * @param productStandardDetail
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addProductStandardDetail",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard_detail:create","base:product_standard_detail:update"},logical=Logical.OR)
	public ResultMsg addProductStandard(ProductStandardDetail productStandardDetail,@CurrentUser User currentUser){
		return standardDetailService.createOrUpdateProductStandardDetail(productStandardDetail,currentUser);
	}
	
	/**删除产品规格明细
	 * @param productCategory
	 * @return
	 */
	@RequestMapping(value="/delProductStandardDetail",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_standard_detail:del"})
	public ResultMsg ProductStandard(ProductStandardDetail productStandardDetail){
		return standardDetailService.delProductStandardDetail(productStandardDetail);
	}
}

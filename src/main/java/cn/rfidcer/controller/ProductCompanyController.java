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
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dto.ProductCompanyParam;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductCompanyService;

/**
* @Title: ProductController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 下午2:20:04 
* @version V1.0
 */
@Controller
@RequestMapping("/productCompany")
public class ProductCompanyController {

	@Autowired
	private ProductCompanyService productCompanyService;
	/**
	 * 根据产品ID和企业ID查询产品关联信息
	 * @param user
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/findAllProductCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductCompany> findAllProductCompanyByProductId(@CurrentUser User user, ProductCompany productCompany) {
		return productCompanyService.findAllProductCompanyByProductId(productCompany);
	}
	
	/**
	 * 删除产品关联企业
	 * @param user
	 * @param productCompany
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResultMsg delete(@CurrentUser User user, ProductCompany productCompany) {
		return productCompanyService.deleteByParam(productCompany);
	}
	
	/**
	 * 添加产品关联企业
	 * @param user
	 * @param productCompany
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg add(@CurrentUser User user, ProductCompany productCompany) {
		return productCompanyService.add(productCompany);
	}
	
	@RequestMapping(value="/findAllNotCheckedProducts", method = RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, Object> findAllNotCheckedProducts(Page page,ProductCompanyParam productCompanyParam){
		List<Product> list=productCompanyService.findAllNotCheckedProducts(page,productCompanyParam);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",list);
		return map;
	}
	
	@RequestMapping(value="/addProducts", method = RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResultMsg addProducts(String products,String companyId) {
		return productCompanyService.addProducts(products,companyId);
	}
	
}
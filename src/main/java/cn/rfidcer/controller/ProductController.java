package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductBaseService; 

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
@RequestMapping("/product")
public class ProductController {

	@Autowired
	@Qualifier("productBaseServiceImpl")
	private ProductBaseService productService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(CompanyPage companyPage) {
		return "product";
	}

	/**
	 * 新增或修改产品;
	 * @param user
	 * @param product
	 * @return
	 */
	@RequiresPermissions(value = { "base:material:add", "base:material:edit", "base:product:edit", "base:product:add" }, logical = Logical.OR)
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addProduct(@CurrentUser User user, Product product) {
		return productService.addOrUpdateProduct(product, user);
	}

	/**
	 * 删除产品;
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/delProduct", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "base:material:delete", "base:product:delete" }, logical = Logical.OR)
	public ResultMsg delProduct(Product product) {
		return productService.delProduct(product);
	}

	/**
	 * 依据商品属性来获取产品信息列表;
	 * @param page
	 * @param product
	 * @return
	 */
	@RequestMapping("/findProductListByGoodsVariable")
	@ResponseBody
	public List<Product> findProductListByGoodsVariable(Page page, Product product) {
		List<Product> lst  = productService.findProductListByGoodsVariable(page, product);
		return lst;
	}
	
	/**
	 * 获取所有的产品信息列表;
	 * @param page
	 * @param product
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Product> list(Page page, Product product) {
		return productService.getProductList(page, product);
	}
	
	/**
	 * 获取有规格明细的产品信息列表;
	 * @param product
	 * @return
	 */
	@RequestMapping("/findProductInfoAreDetailed")
	@ResponseBody
	public List<Product> findProductInfoAreDetailed(Product product) {
		return productService.findProductInfoAreDetailed(product);
	}
	
	/**
	 * 获取所有的产品信息列表;
	 * @param page
	 * @param product
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value = { "base:material:list", "base:product:list" }, logical = Logical.OR)
	public Map<String, Object> findAllList(Page page, Product product) {
		List<Product> lst = productService.getProductList(page, product);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",lst);
		return map;
	}

	/**
	 * 产品查询; created by jie.jia at 2016-04-01 16:42
	 * 
	 * @param page
	 * @param product
	 * @return
	 */
	@RequestMapping("/findAllQueryList")
	@ResponseBody
	public Map<String, Object> findAllQueryList(Page page, Product product) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("rows", productService.findProductListByQuery(page, product));

		return map;
	}

	/**
	 * 导入产品;
	 * @param uploadImportFile
	 * @param currentUser
	 * @param companyId
	 * @param productType
	 * @return
	 */
	@RequiresPermissions(value = { "base:material:import", "base:product:import" }, logical = Logical.OR)
	@RequestMapping(value = "/importproduct", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg importProductCategory(MultipartFile uploadImportFile, @CurrentUser User currentUser,
			String companyId, String productType) {
		return productService.addImportProduct(uploadImportFile, currentUser, companyId, productType);
	}

	/**
	 * 产品和原料的类型转换;
	 * @param product
	 * @param currentUser
	 * @return
	 */
	@RequiresPermissions(value = { "base:product:convert", "base:material:convert" }, logical = Logical.OR)
	@RequestMapping(value = "/transform", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg transform(Product product, @CurrentUser User currentUser) {
		return productService.changeTransform(product);
	}
	@RequestMapping("/listByNameOrMarketcode")
	@ResponseBody
	public List<Product> findProductListByNameOrMarketcode(Product product){
		return productService.findProductListByNameOrMarketcode(product);
	}
}
package cn.rfidcer.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductCategoryService;

/**
* @Title: ProductCategoryController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品分类 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 下午2:16:34 
* @version V1.0
 */
@Controller
@RequestMapping("/product_category")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService categoryService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "productCategory";
	}
	
	/**产品分类列表
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<ProductCategory> list(Page page,ProductCategory productCategory){
		return categoryService.findAll(page, productCategory);
	}
	
	/**
	 * Goods产品分类列表,在原来的基础上添加了一个list对象原来查询全部
	 * @param page
	 * @param productCategory
	 * @return
	 */
	@RequestMapping("/listGoods")
	@ResponseBody
	public List<ProductCategory> listGoods(Page page,ProductCategory productCategory){
		List<ProductCategory> findAll = categoryService.findAll(page, productCategory);
		ProductCategory goods=new ProductCategory();
		goods.setProductCategoryName("全部");
		goods.setProductCategoryDesc("全部");
		goods.setLevel("1");
		goods.setCreateBy("1");
		//ProductCategoryId为空时查询全部
		goods.setProductCategoryId("");
		findAll.add(0, goods);
		   
		
		
		return findAll;
	}
	
	
	
	
	
	/**
	 * 获取所有的产品分类信息列表;
	 * @param page
	 * @param productCategory
	 * @return
	 */
	@RequestMapping("/listForSelecting")
	@ResponseBody
	public List<ProductCategory> listForSelecting(Page page,ProductCategory productCategory){
		List<ProductCategory> myProductCatgories =  categoryService.findAll(page, productCategory);
		
		// 增加选项-全部分类
		ProductCategory aProductCategory = new ProductCategory();
		aProductCategory.setProductCategoryId(null);
		aProductCategory.setProductCategoryName("全部");
		myProductCatgories.add(0, aProductCategory);
		
		return myProductCatgories;
	}
	
	/**
	 * 获取所有的产品分类信息列表;
	 * @param page
	 * @param productCategory
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"base:product_category:list"},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page,ProductCategory productCategory){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", categoryService.findAll(page, productCategory));		
		return map;
	}

	/**
	 * 导入产品分类;
	 * @param uploadImportFile
	 * @param currentUser
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/importproductcategory", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg importProductCategory(MultipartFile uploadImportFile, @CurrentUser User currentUser,String companyId) {
		ResultMsg rm = new ResultMsg();
		rm.setCode(ResultMsg.FAILURE);
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(uploadImportFile.getInputStream());
			HSSFSheet sheetAt = null;
			sheetAt = workbook.getSheetAt(0);
			Timestamp createTime = new Timestamp(System.currentTimeMillis()); ;
			List<ProductCategory> pcs = loadData(workbook, sheetAt,companyId, createTime, currentUser.getId());
			categoryService.createOrUpdate(pcs);
			rm.setCode(ResultMsg.SUCCESS);
			rm.setMsg("导入成功！");
		} catch (Exception e) {
			rm.setCode(ResultMsg.FAILURE);
			rm.setMsg(e.getMessage());
		}
		return rm;
	}

	private List<ProductCategory> loadData(HSSFWorkbook workbook, HSSFSheet sheetAt,String companyId, Timestamp createTime, String createBy) throws Exception {
		List<ProductCategory> pcs = new ArrayList<ProductCategory>();
		for (int index = sheetAt.getFirstRowNum() + 1; index <= sheetAt.getLastRowNum(); index++) {
			HSSFRow row = sheetAt.getRow(index);
			ProductCategory pc = new ProductCategory();
			pc.setProductCategoryName(row.getCell(0).toString());
			pc.setProductCategoryDesc(row.getCell(1).toString());
			pc.setCompanyId(companyId);
			pc.setLevel("1");
			pc.setStatus("0");
			pc.setCreateTime(createTime);
			pc.setCreateBy(createBy);
			pc.setUpdateTime(createTime);
			pc.setUpdateBy(createBy);
			pcs.add(pc);
		}
		return pcs;
	}
	
	/**
	 * 新增或修改产品分类;
	 * @param productCategory
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addProductCategory",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg addProductCategory(ProductCategory productCategory,@CurrentUser User currentUser){
		return categoryService.createOrUpdateProductCategory(productCategory,currentUser);
	}
	

	/**删除产品分类
	 * @param productCategory
	 * @return
	 */
	@RequestMapping(value="/delProductCategory",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"base:product_category:del"})
	public ResultMsg delProductCategory(ProductCategory productCategory){
		return categoryService.delProductCategory(productCategory);
	}
}

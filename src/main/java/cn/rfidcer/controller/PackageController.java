package cn.rfidcer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.svenson.JSONParser;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.PackageGoodsItem;
import cn.rfidcer.bean.PackageMain; 
import cn.rfidcer.bean.ResultMsg; 
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.PackageService; 

/**
* @Title: PackageController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 包装绑定信息  
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 下午1:51:32 
* @version V1.0
 */
@Controller
@RequestMapping("/package")
public class PackageController {
	
	@Autowired
	private PackageService packageService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "package";
	}
	
	/**
	 * 获取所有的包装记录信息;
	 * @param page
	 * @param packageMain
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<PackageMain> list(Page page, PackageMain packageMain){ 
		return packageService.findAll(page, packageMain);
	}
	
	/**
	 * 获取所有的包装记录信息;
	 * @param page
	 * @param packageMain
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String, Object> findAllList(Page page, PackageMain packageMain){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", packageService.findAll(page, packageMain));		
		return map;
	}
	
	/**
	 * 获取父级的包装记录信息;
	 * @param page
	 * @param packageMain
	 * @return
	 */
	@RequestMapping("/findParentalPackageMains")
	@ResponseBody
	public List<PackageMain> findParentalPackageMains(Page page, PackageMain packageMain){
		return packageService.findParentalPackageMains(page, packageMain);
	}
	
	/**
	 * 获取包装明细信息;
	 * @param page
	 * @param packageGoodsItem
	 * @return
	 */
	@RequestMapping("/findAllItems")
	@ResponseBody
	public List<PackageGoodsItem> findAllItems(Page page, PackageGoodsItem packageGoodsItem){ 
		return packageService.findAllItems(page, packageGoodsItem);
	}
	
	/**
	 * 新增或修改包装信息；created by jie.jia at 2016-01-11 17:21
	 * @param packageMain
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addPackage",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg addPackage(PackageMain packageMain, @CurrentUser User currentUser, HttpServletRequest request){
		
		String json = request.getParameter("packageItems");
//		System.out.println(json); 
		
		JSONParser parser = new JSONParser();
		
		@SuppressWarnings("rawtypes")
		ArrayList list = parser.parse(ArrayList.class, json);
		
		List<PackageGoodsItem> result = new ArrayList<PackageGoodsItem>();
		
		for(int i = 0 ; i < list.size() ; i++){ 
			@SuppressWarnings({ "unchecked", "rawtypes" })
			HashMap<String, String> map = (HashMap) list.get(i);
			
			PackageGoodsItem myPackageGoodsItem = new PackageGoodsItem();
			myPackageGoodsItem.setGoodsDetailId( map.get("goodsDetailId"));
			result.add(myPackageGoodsItem);
		}
		
		packageMain.setPackageGoodsItems(result);
		
		return packageService.createOrUpdatePackage(packageMain, currentUser);
	}
	
	/**
	 * 删除包装信息; 
	 * @param packageMain
	 * @return
	 */
	@RequestMapping(value="/delPackage",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg deletePackage(PackageMain packageMain){
		return packageService.deletePackage(packageMain);
	}

}

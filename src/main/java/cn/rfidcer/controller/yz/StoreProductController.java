package cn.rfidcer.controller.yz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.Product;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.yz.StoreProductService;

@Controller
@RequestMapping("/storeProduct")
public class StoreProductController {

	@Autowired
	private StoreProductService storeProductService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "yz/storeProduct";
	} 
	
	@RequestMapping("/findAllList")
	@ResponseBody
	public Map<String, Object> findAllList(Page page,Product product){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", storeProductService.findAllList(page, product));
		return map;
	}
}

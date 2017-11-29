package cn.rfidcer.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.enums.ProcessItemType;
import cn.rfidcer.enums.ProductType;

/**
* @Title: ProductTypeController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 产品类型
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午9:52:08 
* @version V1.0
 */
@Controller
public class ProductTypeController {

	/**
	 * 获取产品类型;
	 * @return
	 */
	@RequestMapping("/getProductype")
	@ResponseBody
	public List<ProductType> getProductype() {
		List<ProductType> lst = new ArrayList<ProductType>();
        for(ProductType.Enums ptype : ProductType.Enums.values()){
        	ProductType type = new ProductType();
        	type.setProductType(Integer.toString(ptype.getIndex()));
        	type.setProductTypeName(ptype.getName());
        	lst.add(type);
        }
		return lst;
	}
	
	/**
	 * 获取加工类型;
	 * @return
	 */
	@RequestMapping("/getProcessItemType")
	@ResponseBody
	public List<ProcessItemType> getProcessItemType() {
		List<ProcessItemType> lst = new ArrayList<ProcessItemType>();
        for(ProcessItemType.Enums ptype : ProcessItemType.Enums.values()){
        	ProcessItemType type = new ProcessItemType();
        	type.setType(Integer.toString(ptype.getIndex()));
        	type.setTypeName(ptype.getName());
        	lst.add(type);
        }
		return lst;
	}
	



}

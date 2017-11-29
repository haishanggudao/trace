package cn.rfidcer.controller.jinji;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.jinji.Material;
import cn.rfidcer.service.jinji.MaterialService;

/**
 * 
* @Title: MaterialController.java 
* @Package cn.rfidcer.controller.jinji 
* @Description: Controller 金机餐饮-原料管理 
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月26日 下午4:37:01 
* @version V1.0
 */
@Controller
@RequestMapping("/material_jinji")
public class MaterialJinjiController {
	
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "jinji/material";
	}
	
	/**
	 * 新增或修改原料;
	 * @param user
	 * @param product
	 * @return
	 */
	@RequiresPermissions(value = { "base:material:add", "base:material:edit" }, logical = Logical.OR)
	@RequestMapping(value = "/addMaterial", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addMaterial(@CurrentUser User user, Material material) {
		return materialService.addOrUpdateMaterial(material, user);
	}
	
	@RequestMapping(value = "/delMaterial", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "base:material:delete", "base:product:delete" }, logical = Logical.OR)
	public ResultMsg delMaterial(Material material) {
		return materialService.delMaterial(material);
	}

}

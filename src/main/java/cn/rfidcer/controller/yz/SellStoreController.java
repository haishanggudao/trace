package cn.rfidcer.controller.yz;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.service.yz.SellStoreService;

/**   
* @Title: SellStoreController.java 
* @Package cn.rfidcer.controller.yz 
* @Description: controller 羽众酒业-销售门店 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月2日 上午10:46:59 
* @version V1.0   
*/
@Controller
@RequestMapping("/sellStore")
public class SellStoreController {
	
	@Autowired 
	private SellStoreService sellStoreService;
	
	/**
	 * 新增或编辑零售门店信息; created by jie.jia at 2016-08-02 13:59
	 * @param customers
	 * @param user
	 * @return
	 */
	@RequiresPermissions(value = { "base:customers:add", "base:customers:edit" }, logical = Logical.OR)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg save(Customers customers, @CurrentUser User user) {
		return sellStoreService.addOrUpdate(customers, user);
	}

}

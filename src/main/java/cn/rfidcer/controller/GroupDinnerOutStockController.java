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
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GroupDinnerOutStockService;

/**
* @Title: GroupDinnerOutStockController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 团餐出库 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午10:16:37 
* @version V1.0
 */
@Controller
@RequestMapping("/groupDinnerOutStock")
public class GroupDinnerOutStockController {
	
	@Autowired
	private GroupDinnerOutStockService groupDinnerOutStockService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "groupDinnerOutStock";
	}
	
	/**
	 * 新增或编辑团餐出库;
	 * @param user
	 * @param outstockMain
	 * @param outstockDetail
	 * @return
	 */
	@RequiresPermissions(value = { "trans:groupDinnerOutStock:add", "trans:groupDinnerOutStock:edit"}, logical = Logical.OR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg add(@CurrentUser User user,OutstockMain outstockMain,String outstockDetail) {
		return groupDinnerOutStockService.addOrUpdate(outstockMain, outstockDetail, user);
	}
		
	/**
	 * 获取所有的团餐出库记录;
	 * @param page
	 * @param user
	 * @param outstockMain
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"trans:groupDinnerOutStock:list" }, logical=Logical.OR)
	public Map<String,Object> findAllList(Page page,@CurrentUser User user,OutstockMain outstockMain){
		List<OutstockMain> listOutstockMain =  groupDinnerOutStockService.findAllGroupDinnerOutstock(page,outstockMain);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", listOutstockMain);
		return map;
	}
	
	/**
	 * 删除团餐出库记录;
	 * @param outstockMain
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	@RequiresPermissions(value={"trans:groupDinnerOutStock:delete" }, logical=Logical.OR)
	public ResultMsg delete(OutstockMain outstockMain){
		return groupDinnerOutStockService.delete(outstockMain);
	}
	
}

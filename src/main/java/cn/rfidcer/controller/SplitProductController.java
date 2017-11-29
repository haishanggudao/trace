package cn.rfidcer.controller;

import java.util.ArrayList;
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

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.ProcessItem;
import cn.rfidcer.bean.ProcessMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SplitProductService;
import cn.rfidcer.util.StringUtil;

/**   
* @Title: splitProductController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 拆分产品
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午11:14:36 
* @version V1.0   
*/
@Controller
@RequestMapping("/splitProduct")
public class SplitProductController {

	@Autowired
	private SplitProductService splitProductService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "splitProduct";
	}

	
	/**
	 * 获取所有的拆分记录;
	 * @param page
	 * @param processMain
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"operation:splitProduct:list"},logical=Logical.OR)
	public Map<String, Object> findAllList(Page page, ProcessMain processMain){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<ProcessMain>  lst = splitProductService.getSplitProductList(page, processMain);
		map.put("rows", lst);		
		return map;
	}
	
	/**
	 * 新增或修改拆分记录;
	 * @param processMain
	 * @param processDetail
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addSplit",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@RequiresPermissions(value={"operation:splitProduct:add","operation:splitProduct:edit"},logical=Logical.OR)
	@AvoidDuplicateSubmission(needRemoveToken=true)
	public ResultMsg addSplit(ProcessMain processMain,String processDetail, @CurrentUser User currentUser){
		return splitProductService.createOrUpdateSplitProduct(processMain,processDetail, currentUser);
	}
	
	/**
	 * 获取加工明细记录;
	 * @param page
	 * @param processMain
	 * @return
	 */
	@RequestMapping("/findAllItemList")
	@ResponseBody
	@RequiresPermissions(value={"operation:splitProduct:list"},logical=Logical.OR)
	public Map<String, Object> findAllItemList(Page page, ProcessMain processMain){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<ProcessItem>  lst = new ArrayList<ProcessItem>();
		if(!StringUtil.isBlank(processMain.getProcessMainId())){
			lst=splitProductService.getSplitItemList(page, processMain);
		}
		map.put("rows",lst );		
		return map;
	}
	
	/**
	 * 删除拆分明细;
	 * @param processItem
	 * @return
	 */
	@RequestMapping(value="/delSplitItem",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResultMsg delSplitItem(ProcessItem processItem){
		return splitProductService.delSplitItem(processItem);
	}
	
	/**
	 * 删除拆分记录;
	 * @param processMain
	 * @return
	 */
	@RequestMapping(value="/delSplitProduct",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@RequiresPermissions(value={"operation:splitProduct:delete"},logical=Logical.OR)
	public ResultMsg delProcess(ProcessMain processMain){
		return splitProductService.delSplit(processMain);
	}
}

package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.InstockManagerItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.InstockService;
 
/**
* @Title: PurchaseManagerController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 进货管理
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午10:16:56 
* @version V1.0
 */
@Controller
@RequestMapping("/purchase_manager")
public class PurchaseManagerController {

	@Autowired
	private InstockService instockService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "purchaseManager";
	}
	
	/**
	 * 新增或修改入库记录;
	 * @param instockmain
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequiresPermissions(value="trans:purchase_manager:add")
	public ResultMsg save(@RequestBody InstockMain instockmain,@CurrentUser User user){
		String purchaseids = instockmain.getPurchaseOrderId();
		ResultMsg msg  = new ResultMsg();
		if(instockmain.getConsignee()==null || instockmain.getCompanyId()==null ){
			msg.setCode("0");
			msg.setMsg("亲,发现您的数据不完整！");
			return msg;
		}
		if(StringUtils.isEmpty(purchaseids)) {
			instockmain.setPurchaseOrderId("poid");
		}
		return instockService.addOrUpdateAndUpdateStock(instockmain, user);
	}
	
	/**
	 * 获取所有的入库明细记录列表;
	 * @param page
	 * @param instockManagerItem
	 * @return
	 */
	@RequestMapping(value="/findAllList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findAllList(Page page,InstockManagerItem instockManagerItem){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", instockService.findAllList(page, instockManagerItem));
		return map;
	}
}

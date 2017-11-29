package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.StoreAccount;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.StoreAccountService;

@Controller
@RequestMapping("/storeAccount")
public class StoreAccountController {

	@Autowired
	private StoreAccountService storeAccountService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(CompanyPage companyPage) {
		return "storeAccount";
	}
	
	@RequestMapping(value="/addstoreAccount",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@RequiresPermissions(value={"system:storeAccount:add","system:storeAccount:edit"},logical=Logical.OR)
	public ResultMsg addOrUpdateStoreAccount(@CurrentUser User user,StoreAccount storeAccount){
		return storeAccountService.addOrUpdateStoreAccount(user,storeAccount);
	}
	
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"system:storeAccount:list"},logical=Logical.OR)
	public Map<String,Object> findAllList(Page page,StoreAccount StoreAccount){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", storeAccountService.findAllList(page,StoreAccount));		
		return map;
	}
	
	@RequestMapping(value="/deleteStoreAccount",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@RequiresPermissions(value={"system:storeAccount:del"},logical=Logical.OR)
	public ResultMsg deleteStoreAccount(StoreAccount storeAccount){
		return storeAccountService.deleteStoreAccount(storeAccount);
	}
}

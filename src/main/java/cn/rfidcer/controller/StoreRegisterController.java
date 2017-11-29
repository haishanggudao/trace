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
import cn.rfidcer.bean.StoreRegister;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.StoreRegisterService;

@Controller
@RequestMapping("/storeRegister")
public class StoreRegisterController {

	@Autowired
	private StoreRegisterService storeRegisterService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(CompanyPage companyPage) {
		return "storeRegister";
	}
	
	@RequestMapping(value="/addstoreRegister",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@RequiresPermissions(value={"system:storeRegister:add","system:storeRegister:edit"},logical=Logical.OR)
	public ResultMsg addOrUpdateStoreRegister(@CurrentUser User user,StoreRegister storeRegister){
		return storeRegisterService.addOrUpdateStoreRegister(user,storeRegister);
	}
	
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"system:storeRegister:list"},logical=Logical.OR)
	public Map<String,Object> findAllList(Page page,StoreRegister StoreRegister){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", storeRegisterService.findAllList(page,StoreRegister));		
		return map;
	}
	
	@RequestMapping("/findLocationByCompanyId")
	@ResponseBody
	public String findLocationByCompanyId(StoreRegister storeRegister){
	   return storeRegisterService.findLocationByCompanyId(storeRegister);
	}
	
	@RequestMapping(value="/getLicense",method=RequestMethod.POST)
    @ResponseBody
    public String getLicense(String companyId){
		return storeRegisterService.getLicense(companyId);
		}
	
	@RequestMapping(value="/deleteStoreRegister",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ResultMsg deleteStoreRegister(StoreRegister storeRegister){
		return storeRegisterService.deleteStoreRegister(storeRegister);
	}
	}
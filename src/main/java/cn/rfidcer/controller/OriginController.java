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
import cn.rfidcer.bean.Origin;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.OriginService;

/**
* @Title: OriginController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 源头信息 
* @author tao.zhang
* @Copyright Copyright
* @date 2017年9月29日 下午12:20:04 
* @version V1.0
 */

@Controller
@RequestMapping("/origin")
public class OriginController {
	
	@Autowired
	private OriginService originService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "origin";
	}

	/**
	 * 新增或修改源头信息;
	 * @param user
	 * @param Origin
	 * @return
	 */
	@RequiresPermissions(value = { "base:material:add", "base:material:edit", "base:Origin:edit", "base:Origin:add" }, logical = Logical.OR)
	@RequestMapping(value = "/addOrigin", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg addOrigin(@CurrentUser User user, Origin origin) {
		return originService.addOrUpdateOrigin(origin, user);
	}

	/**
	 * 删除源头信息;
	 * @param origin
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delOrigin", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "base:origin:delete" })
	public ResultMsg delOrigin(@CurrentUser User user,Origin origin) {
		return originService.delOrigin(origin,user);
	}

	
	/**
	 * 获取所有的源头信息列表;
	 * @param page
	 * @param origin
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Origin> list(Page page, Origin origin) {
		return originService.getOriginList(page, origin);
	}
	
	/**
	 * 获取所有的源头信息列表;
	 * @param page
	 * @param origin
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value = { "base:origin:list" })
	public Map<String, Object> findAllList(Page page, Origin origin) {
		List<Origin> lst = originService.getOriginList(page, origin);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",lst);
		return map;
	}
}

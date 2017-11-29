package cn.rfidcer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.GoodsQC;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.Instockqc;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsQCService;
import cn.rfidcer.service.InstockService;
import cn.rfidcer.service.yz.InstockQCService;

/**
* @Title: GoodsQCController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 商品质检 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午9:49:03 
* @version V1.0
 */
@Controller
@RequestMapping("/qc")
public class GoodsQCController {

	@Autowired
	private GoodsQCService goodsQCService;
	
	@Autowired
	private InstockQCService instockQCService;
	
	@Autowired
	private InstockService instockService;
	
	
	/**菜单跳转页面
	 * @param companyPage 根据数据库动态改变跳转页面
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String index(CompanyPage companyPage){
		return "instockQC";
	}
	
	/**商品质检信息列表
	 * @param page
	 * @param goodsQC
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<GoodsQC> list(Page page,GoodsQC goodsQC){
		return goodsQCService.findAll(page, goodsQC);
	}
	
	/**
	 * 获取所有的质检信息;
	 * @param page
	 * @param goodsQC
	 * @return
	 */
	@RequestMapping("/findAllList")
	@ResponseBody
	@RequiresPermissions(value={"operation:qc:list"})
	public Map<String, Object> findAllList(Page page,GoodsQC goodsQC){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", goodsQCService.findAll(page, goodsQC));		
		return map;
	}
	
	@RequestMapping("/findallinstocklist")
	@ResponseBody
	@RequiresPermissions(value={"operation:qc:list"})
	public Map<String, Object> findAllInstockList(Page page,Instockqc instockqc){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", instockQCService.findAll(page, instockqc));		
		return map;
	}
	
	@RequestMapping("/getbatchnum")
	@ResponseBody
	public List<Map<String, String>> getBatchNum(String companyId){
		List<Map<String, String>> list = null;
		list = instockService.getBactchNum(companyId);
		if(list == null) {
			list = new ArrayList<Map<String, String>>();
		}
		return list;
	}
	
	/**新增或修改商品质检信息
	 * @param goodsQC
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/addGoodsQC",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"operation:qc:add","operation:qc:edit"},logical=Logical.OR)
	public ResultMsg addGoodsQC(GoodsQC goodsQC,@CurrentUser User currentUser){
		return goodsQCService.createOrUpdateGoodsQC(goodsQC,currentUser);
	}
	
	/**
	 * 系统基础的进货质检
	 * @param yzinstockqc
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value="/saveInstockQC",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"operation:qc:add","operation:qc:edit"},logical=Logical.OR)
	public ResultMsg saveInstockQC(Instockqc instockqc,@CurrentUser User currentUser){
		if (!StringUtils.isEmpty(instockqc.getId())) {
			return instockQCService.updateInstockQC(instockqc, currentUser);
		} else {
			return instockQCService.saveInstockQC(instockqc, currentUser);
		}
	}

	@RequestMapping(value="/deleteimage",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteImage(String id,String url) {
		if(!StringUtils.isEmpty(id) && !StringUtils.isEmpty(url)) {
			return instockQCService.delteImage(id,url);
		}
		ResultMsg msg = new ResultMsg();
		msg.setCode("-1");
		return msg;
	}
	
	@RequestMapping(value="/uploadfiles",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg uploadFiles(GoodsQC goodsQC,@CurrentUser User currentUser,MultipartFile[] qcmaterialURL){
		return goodsQCService.createOrUpdateGoodsQC(goodsQC,currentUser);
	}
	

	/**删除商品质检信息
	 * @param goodsQC
	 * @return
	 */
	@RequestMapping(value="/delGoodsQC",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value={"operation:qc:delete"})
	public ResultMsg delGoodsQC(GoodsQC goodsQC){
		return goodsQCService.delGoodsQC(goodsQC);
	}
}

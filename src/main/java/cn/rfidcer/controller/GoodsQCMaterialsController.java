package cn.rfidcer.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.GoodsQCMaterials;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsQCMaterialsService;

@Controller
@RequestMapping("/goodsqc")
public class GoodsQCMaterialsController {

	@Autowired
	private GoodsQCMaterialsService goodsQCMaterialsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "goodsQCMaterials";
	}
	
	@RequestMapping(value="/saveGoodsQC",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg saveGoodsQC(GoodsQCMaterials goodsQCMaterials,@CurrentUser User currentUser){
		if (!StringUtils.isEmpty(goodsQCMaterials.getId())) {
			return goodsQCMaterialsService.updateGoodsQC(goodsQCMaterials, currentUser);
		} else {
			return goodsQCMaterialsService.saveGoodsQC(goodsQCMaterials, currentUser);
		}
	}
	
	@RequestMapping(value="findAllList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findAllList(Page page,GoodsQCMaterials goodsQCMaterials){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", goodsQCMaterialsService.findAllList(page,goodsQCMaterials));		
		return map;
	}
	
	@RequestMapping(value="/deleteImage",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteImage(String id,String url) {
		if(!StringUtils.isEmpty(id) && !StringUtils.isEmpty(url)) {
			return goodsQCMaterialsService.delteImage(id,url);
		}
		ResultMsg msg = new ResultMsg();
		msg.setCode("-1");
		return msg;
	}
}

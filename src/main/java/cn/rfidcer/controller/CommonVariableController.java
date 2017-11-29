package cn.rfidcer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.service.CommonVariableService;

/**
* @Title: CommonVariableController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 通用参数 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:45:56 
* @version V1.0
 */
@Controller
@RequestMapping("/commonVariable")
public class CommonVariableController {

	@Autowired
	private CommonVariableService commonVariableService;
	
	/**
	 * 获取value
	 * @param commonVariable
	 * @return
	 */
	@RequestMapping(value="getVariablesByGroup",method=RequestMethod.POST)
	@ResponseBody
	public List<CommonVariable> getCommonVariables(CommonVariable commonVariable){
		List<CommonVariable> list = commonVariableService.getCommonVariables(commonVariable);
		return list;
	}
	
}

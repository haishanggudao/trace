package cn.rfidcer.controller.codetrace;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.rfidcer.bean.TraceInfo;
import cn.rfidcer.service.HandShakeService;
import cn.rfidcer.service.codetrace.CodeTraceInfoService;


@Controller
@RequestMapping("/codeTrace")
public class CodeTraceController {
    
	@Autowired
	private HandShakeService handShakeService;
	
	@Autowired
	private CodeTraceInfoService codeTraceInfoService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap map) {
		TraceInfo traceInfo = null;
		map.put("traceInfo", traceInfo);
		return "codetrace/codeTrace";
	}
	
	@RequestMapping("/queryResults")
	public String queryResults(String search, ModelMap map, HttpServletRequest request){
		TraceInfo traceInfo = null;
		
		try {
			codeTraceInfoService.saveCodeTraceInfo(search, request);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
		    traceInfo = handShakeService.getTraceInfoByCode(search);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		map.put("traceInfo", traceInfo);
		map.put("search", search);
		
		return "codetrace/codeTrace";
	}
}

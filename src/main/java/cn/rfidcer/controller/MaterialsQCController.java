package cn.rfidcer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/materialsQC")
public class MaterialsQCController {

	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "materialsQC";
	}
}

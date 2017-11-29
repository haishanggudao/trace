package cn.rfidcer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.dto.UploaderParam;
import cn.rfidcer.service.ImageService;

@RequestMapping("/image")
@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public UploaderParam uploadImage(MultipartFile upfile){
		return imageService.uploadImage(upfile);
	}
}

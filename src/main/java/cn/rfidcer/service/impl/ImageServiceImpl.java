package cn.rfidcer.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.dto.UploaderParam;
import cn.rfidcer.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysVariableDao variableDao;
	
	@Override
	public UploaderParam uploadImage(MultipartFile file) {
		UploaderParam param = new UploaderParam();
		param.setOriginalName(file.getOriginalFilename());
		logger.info(file.getOriginalFilename());
		String strSaveFileUrl = "";
		if (file != null && !file.isEmpty()) {
			String webPath = variableDao.getValByKey("WebPath");
			String strogePath = variableDao.getValByKey("strogePath");
			try {
				String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				String fileName = System.currentTimeMillis()+"."+ type;
				String path = strogePath + fileName;
				File imgFile = new File(path);
				FileUtils.copyInputStreamToFile(file.getInputStream(), imgFile);
				strSaveFileUrl=webPath + imgFile.getName();
				param.setName(fileName);
				param.setUrl(strSaveFileUrl);
				param.setState("SUCCESS");
				param.setSize(file.getSize());
				param.setType(type);
			} catch (IOException e) {
				logger.error("上传图片异常",e);
			}
		}
		return param;
	}

}

package cn.rfidcer.service;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.dto.UploaderParam;

public interface ImageService {

	UploaderParam uploadImage(MultipartFile file);
}

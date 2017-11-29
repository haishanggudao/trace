package cn.rfidcer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.GoodsQCMaterials;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsQCMaterialsMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsQCMaterialsService;
import cn.rfidcer.service.ProductBaseService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class GoodsQCMaterialsServiceImpl implements GoodsQCMaterialsService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GoodsQCMaterialsMapper goodsQCMaterialsMapper;
	
	@Autowired
	@Qualifier("productBaseServiceImpl")
	private ProductBaseService productService;
	
	@Override
	public ResultMsg saveGoodsQC(GoodsQCMaterials goodsQCMaterials,
			User currentUser) {
		ResultMsg rm = new ResultMsg();
		try {
			goodsQCMaterials.setId(UUIDGenerator.generatorUUID());
			CommonImportUtils.setCreateAndUpdateTime(goodsQCMaterials,currentUser);
			updateImages(goodsQCMaterials);
			goodsQCMaterialsMapper.insertSelective(goodsQCMaterials);
			rm.setCode("1");
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			rm.setCode("-1");
			rm.setMsg(e.getMessage());
		}
		return rm;
	}

	@Override
	public ResultMsg updateGoodsQC(GoodsQCMaterials goodsQCMaterials,
			User currentUser) {
		ResultMsg rm = new ResultMsg();
		try {
			CommonImportUtils.setUpdateTime(goodsQCMaterials, currentUser);
			updateImages(goodsQCMaterials);
			goodsQCMaterialsMapper.updateByPrimaryKeySelective(goodsQCMaterials);
			rm.setCode("1");
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			rm.setCode("-1");
			rm.setMsg(e.getMessage());
		}
		return rm;
	}
	
	private String updateImages(GoodsQCMaterials goodsQCMaterials) {
		MultipartFile[] qcmaterialURLFiles = goodsQCMaterials.getQcmaterialURLFiles();
		List<String> list = new ArrayList<String>();
		if(qcmaterialURLFiles != null && qcmaterialURLFiles.length > 0) {
			for (MultipartFile multipartFile : qcmaterialURLFiles) {
				String saveFile = productService.saveFile(multipartFile, "");
				if(!StringUtils.isEmpty(saveFile)) {
					list.add(saveFile);
				}
			}
		}
		if(list.size() > 0) {
			String[] listSize = list.toArray(new String[list.size()]);
			goodsQCMaterials.setQcmaterialURL(listSize);
		}
		return goodsQCMaterials.getQcmaterialsURL();
	}

	@Override
	public List<GoodsQCMaterials> findAllList(Page page,
			GoodsQCMaterials goodsQCMaterials) {
		return goodsQCMaterialsMapper.findAllList(page,goodsQCMaterials);
	}

	@Override
	public ResultMsg delteImage(String id, String url) {
		ResultMsg msg = new ResultMsg();
		GoodsQCMaterials bean = goodsQCMaterialsMapper.selectByPrimaryKey(id);
		if(bean != null) {
			String[] qcmaterialURL = bean.getQcmaterialURL();
			bean.setQcmaterialsURL("");
			if(qcmaterialURL != null && qcmaterialURL.length > 0) {
				List<String> list = new ArrayList<String>();
				for (String imgUrl : qcmaterialURL) {
					if(!org.apache.commons.lang3.StringUtils.equalsIgnoreCase(imgUrl, url)) {
						list.add(imgUrl);
					}
				}
				if(list.size() > 0) {
					String[] listSize = list.toArray(new String[list.size()]);
					bean.setQcmaterialURL(listSize);
				}
			}
		}
		goodsQCMaterialsMapper.updateByPrimaryKeySelective(bean);
		msg.setMsg("1");
		return msg;
	}

}

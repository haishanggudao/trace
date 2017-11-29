package cn.rfidcer.service.impl.yz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.Instockqc;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.InstockqcMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductBaseService; 
import cn.rfidcer.service.yz.InstockQCService;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class InstockQCServiceImpl implements InstockQCService {
	
	private Logger logger = LoggerFactory.getLogger(InstockQCServiceImpl.class);

	@Autowired
	private InstockqcMapper instockqcMapper;
	
	@Autowired
	@Qualifier("productBaseServiceImpl")
	private ProductBaseService productService;
	
	@Override
	public List<Instockqc> findAll(Page page, Instockqc instockqc) {
		List<Instockqc> items = instockqcMapper.selectByBean(page,instockqc);
		return items;
	}

	@Override
	public ResultMsg updateInstockQC(Instockqc instockqc, User currentUser) {
		ResultMsg rm = new ResultMsg();
		try {
			instockqc.setUpdateBy(currentUser.getId());
			instockqc.setUpdateTime(new Date());
			updateImages(instockqc);
			instockqcMapper.updateByPrimaryKeySelective(instockqc);
			rm.setCode("1");
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			rm.setCode("-1");
			rm.setMsg(e.getMessage());
		}
		return rm;
	}

	@Override
	public ResultMsg saveInstockQC(Instockqc instockqc, User currentUser) {
		ResultMsg rm = new ResultMsg();
		try {
			instockqc.setUpdateBy(currentUser.getId());
			instockqc.setUpdateTime(new Date());
			instockqc.setId(UUIDGenerator.generatorUUID());
			updateImages(instockqc);
			instockqcMapper.insertSelective(instockqc);
			rm.setCode("1");
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			rm.setCode("-1");
			rm.setMsg(e.getMessage());
		}
		return rm;
	}
	
	private String updateImages(Instockqc instockqc) {
		MultipartFile[] qcmaterialURLFiles = instockqc.getQcmaterialURLFiles();
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
			instockqc.setQcmaterialURL(listSize);
		}
		return instockqc.getQcmaterialsURL();
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.yz.InstockQCService#delteImage(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultMsg delteImage(String _id, String _url) {
		ResultMsg msg = new ResultMsg();
		Instockqc bean = instockqcMapper.selectByPrimaryKey(_id);
		if(bean != null) {
			String[] qcmaterialURL = bean.getQcmaterialURL();
			bean.setQcmaterialsURL("");
			if(qcmaterialURL != null && qcmaterialURL.length > 0) {
				List<String> list = new ArrayList<String>();
				for (String url : qcmaterialURL) {
					if(!org.apache.commons.lang3.StringUtils.equalsIgnoreCase(url, _url)) {
						list.add(url);
					}
				}
				if(list.size() > 0) {
					String[] listSize = list.toArray(new String[list.size()]);
					bean.setQcmaterialURL(listSize);
				}
			}
		}
		instockqcMapper.updateByPrimaryKeySelective(bean);
		msg.setMsg("1");
		return msg;
	}
}
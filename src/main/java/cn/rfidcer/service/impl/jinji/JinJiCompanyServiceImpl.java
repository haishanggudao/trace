package cn.rfidcer.service.impl.jinji;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.jinji.JinJiCompany;
import cn.rfidcer.dao.AreaInfoMapper;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.dao.jinji.JinJiCompanyMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.jinji.JinJiCompanyService;
import cn.rfidcer.util.CodeGenerator;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class JinJiCompanyServiceImpl implements JinJiCompanyService {
	
	private Logger logger = LoggerFactory.getLogger(JinJiCompanyServiceImpl.class);
	
	@Autowired
	private JinJiCompanyMapper jinjiCompanyMapper;
	
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	
	@Autowired
	private CompanyMapper commonCompanyDao;
	
	@Autowired
	protected SysVariableDao variableDao;
	
	@Autowired
	private CommonVariableMapper comvarMapper;
	
	public String genCompanyCode(AreaInfo areaInfo) {
		String companyCode = CodeGenerator.getCompanyCode(areaInfo.getId());
		while(commonCompanyDao.findByCode(companyCode)!=null){
			companyCode = CodeGenerator.getCompanyCode(areaInfo.getId());
		}
		return companyCode; 
	}
	
	private  String  saveFile(MultipartFile file,String fileUrl) {
		String strSaveFileUrl = "";
		if (file != null && !file.isEmpty()) {
			String webPath = variableDao.getValByKey("WebPath");
			String strogePath = variableDao.getValByKey("strogePath");
			if (fileUrl != null && fileUrl.trim().length() != 0) {
				String oldFileName = fileUrl.substring(webPath.length());
				File oldFile = new File(strogePath + oldFileName);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			try {
				String path = strogePath + System.currentTimeMillis()
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				File imgFile = new File(path);
				FileUtils.copyInputStreamToFile(file.getInputStream(), imgFile);
				strSaveFileUrl=webPath + imgFile.getName();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strSaveFileUrl;
	}
	
	private void saveJinJiCompanyFile(JinJiCompany jinjiCompany) {
		String licenseUrl = null;
		String chygienelicenseUrl=null;
		if(jinjiCompany.getCompanyImgfile() != null && jinjiCompany.getCompanyImgfile().getSize()>0){
			//上传企业图片
			licenseUrl = saveFile(jinjiCompany.getCompanyImgfile(), jinjiCompany.getLicenseUrl());
			jinjiCompany.setLicenseUrl(licenseUrl);
			if (jinjiCompany.getCompanyImgchyienelicensefile()!=null&&jinjiCompany.getCompanyImgchyienelicensefile().getSize()>0) {
				chygienelicenseUrl=saveFile(jinjiCompany.getCompanyImgchyienelicensefile(), jinjiCompany.getChygienelicenseUrl());
				jinjiCompany.setChygienelicenseUrl(chygienelicenseUrl);
			}
		} 
	}

	@Override
	public ResultMsg addOrUpdateCompany(JinJiCompany jinjiCompany, User user) {
		// TODO Auto-generated method stub
		logger.info("invoke addOrUpdateCompany ,id is {} ", jinjiCompany.getCompanyid());
		ResultMsg resultMsg = new ResultMsg();
		try {
			// call: save the image file
			saveJinJiCompanyFile(jinjiCompany);
			
			if ( StringUtils.isEmpty(jinjiCompany.getCompanyid())) {
				jinjiCompany.setCompanyid(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(jinjiCompany, user);//设置修改时间和创建时间
				if ( StringUtils.isEmpty(jinjiCompany.getCode()) ) {
					AreaInfo areaInfo = new AreaInfo();
					areaInfo.setProvince(jinjiCompany.getProvince());
					areaInfo.setCity(jinjiCompany.getCity());
					areaInfo.setArea(jinjiCompany.getArea());
					areaInfo=areaInfoMapper.selectOne(areaInfo);
					if(areaInfo==null){
						resultMsg.setCode("-1");
						resultMsg.setMsg("找不见地区信息");
						return resultMsg;
					}
					// 生成企业代码
					String companyCode = genCompanyCode(areaInfo);
					jinjiCompany.setCode(companyCode);
				}
				
				// call: save the image file
				// saveJinJiCompanyFile(jinjiCompany);
				
				// DAO action: insert a new company
				jinjiCompanyMapper.insertSelective(jinjiCompany);
			} else {
				if(StringUtils.isEmpty(jinjiCompany.getCode())){
					AreaInfo areaInfo = new AreaInfo();
					areaInfo.setProvince(jinjiCompany.getProvince());
					areaInfo.setCity(jinjiCompany.getCity());
					areaInfo.setArea(jinjiCompany.getArea());
					areaInfo=areaInfoMapper.selectOne(areaInfo);
					if(areaInfo==null){
						resultMsg.setCode("-1");
						resultMsg.setMsg("找不见地区信息");
						return resultMsg;
					}
					// 生成企业代码
					String companyCode = genCompanyCode(areaInfo);
					jinjiCompany.setCode(companyCode);
				}
				
				// call: save the image file
				// saveJinJiCompanyFile(jinjiCompany);
				
				CommonImportUtils.setUpdateTime(jinjiCompany, user);
				
				// DAO action: update the company
				jinjiCompanyMapper.updateByPrimaryKeySelective(jinjiCompany);
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public List<JinJiCompany> listQuery(Page page, JinJiCompany jinjiCompany) {
		return jinjiCompanyMapper.listQuery(page, jinjiCompany);
	}

	@Override
	public List<CommonVariable> getCompanyNatures() {
		CommonVariable cv = new CommonVariable();
		cv.setVarGroup("jinjiCompanyNature");
		List<CommonVariable> list = comvarMapper.selectByBean(cv);
		return list;
	}

}

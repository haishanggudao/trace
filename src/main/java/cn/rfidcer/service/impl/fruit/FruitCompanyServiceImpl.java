package cn.rfidcer.service.impl.fruit;

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
import cn.rfidcer.bean.fruit.FruitCompany;
import cn.rfidcer.dao.AreaInfoMapper;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.dao.fruit.FruitCompanyMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.fruit.FruitCompanyService;
import cn.rfidcer.util.CodeGenerator;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class FruitCompanyServiceImpl implements FruitCompanyService {
	
	private Logger logger = LoggerFactory.getLogger(FruitCompanyServiceImpl.class);
	
	@Autowired
	private FruitCompanyMapper fruitCompanyMapper;
	
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
	
	private void saveFruitCompanyFile(FruitCompany fruitCompany) {
		String strfileUrl = null;
		if(fruitCompany.getCompanyImgfile() != null && fruitCompany.getCompanyImgfile().getSize()>0){
			//上传企业图片
			strfileUrl = saveFile(fruitCompany.getCompanyImgfile(), fruitCompany.getImageUrl() );
			fruitCompany.setImageUrl( strfileUrl );
		} 
	}

	@Override
	public ResultMsg addOrUpdateCompany(FruitCompany fruitCompany, User user) {
		// TODO Auto-generated method stub
		logger.info("invoke addOrUpdateCompany ,id is {} ", fruitCompany.getCompanyid());
		ResultMsg resultMsg = new ResultMsg();
		try {
			// call: save the image file
			saveFruitCompanyFile(fruitCompany);
			
			if ( StringUtils.isEmpty(fruitCompany.getCompanyid())) {
				fruitCompany.setCompanyid(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(fruitCompany, user);//设置修改时间和创建时间
				if ( StringUtils.isEmpty(fruitCompany.getCode()) ) {
					AreaInfo areaInfo = new AreaInfo();
					areaInfo.setProvince(fruitCompany.getProvince());
					areaInfo.setCity(fruitCompany.getCity());
					areaInfo.setArea(fruitCompany.getArea());
					areaInfo=areaInfoMapper.selectOne(areaInfo);
					if(areaInfo==null){
						resultMsg.setCode("-1");
						resultMsg.setMsg("找不见地区信息");
						return resultMsg;
					}
					// 生成企业代码
					String companyCode = genCompanyCode(areaInfo);
					fruitCompany.setCode(companyCode);
				}
				
				// call: save the image file
				// saveFruitCompanyFile(fruitCompany);
				
				// DAO action: insert a new company
				fruitCompanyMapper.insertSelective(fruitCompany);
			} else {
				if(StringUtils.isEmpty(fruitCompany.getCode())){
					AreaInfo areaInfo = new AreaInfo();
					areaInfo.setProvince(fruitCompany.getProvince());
					areaInfo.setCity(fruitCompany.getCity());
					areaInfo.setArea(fruitCompany.getArea());
					areaInfo=areaInfoMapper.selectOne(areaInfo);
					if(areaInfo==null){
						resultMsg.setCode("-1");
						resultMsg.setMsg("找不见地区信息");
						return resultMsg;
					}
					// 生成企业代码
					String companyCode = genCompanyCode(areaInfo);
					fruitCompany.setCode(companyCode);
				}
				
				// call: save the image file
				// saveFruitCompanyFile(fruitCompany);
				
				CommonImportUtils.setUpdateTime(fruitCompany, user);
				
				// DAO action: update the company
				fruitCompanyMapper.updateByPrimaryKeySelective(fruitCompany);
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
	public List<FruitCompany> listQuery(Page page, FruitCompany fruitCompany) {
		return fruitCompanyMapper.listQuery(page, fruitCompany);
	}

	@Override
	public List<CommonVariable> getCompanyNatures() {
		CommonVariable cv = new CommonVariable();
		cv.setVarGroup("fruitCompanyNature");
		List<CommonVariable> list = comvarMapper.selectByBean(cv);
		return list;
	}

}

package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.CompanyField;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyFieldMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.CompanyFieldService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class CompanyFieldServiceImpl implements CompanyFieldService {

private Logger logger = LoggerFactory.getLogger(CompanyFieldServiceImpl.class);
	
	@Autowired
	private CompanyFieldMapper companyFieldDao;
	
	@Override
	public ResultMsg addOrUpdate(CompanyField companyField,User user) {
		logger.info("invoke addOrUpdateCompany ,id is {0} .", companyField.getCompanyFieldId());
		ResultMsg resultMsg = new ResultMsg();
		CommonImportUtils.setCreateAndUpdateTime(companyField, user);
		try {
			if (StringUtils.isEmpty(companyField.getCompanyFieldId())) {
				companyField.setCompanyFieldId(UUIDGenerator.generatorUUID());
				companyFieldDao.insert(companyField);
			} else if (!StringUtils.isEmpty(companyField.getCompanyFieldDesc())) {
				companyFieldDao.updateByPrimaryKeyWithBLOBs(companyField);
			} else {
				companyFieldDao.updateByPrimaryKeySelective(companyField);
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
	public List<CompanyField> list(Page page) {
		if(page != null) {
			return companyFieldDao.list(page);
		} else {
			return companyFieldDao.list();
		}
	}

	@Override
	public ResultMsg deleteByKey(String id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int deleteByPrimaryKey = companyFieldDao.deleteByPrimaryKey(id);
			if (deleteByPrimaryKey == 1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("删除成功");
			} else {
				resultMsg.setCode("0");
				resultMsg.setMsg("删除失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public List<CompanyField> listExcept(String exceptid) {
		return companyFieldDao.listExcept(exceptid);
	}

	@Override
	public List<CompanyField> findAll(Page page, CompanyField cf) {
		return companyFieldDao.findAll(page,cf);
	}
	//
	//	@Override
	//	public void createOrUpdate(CompanyField cf) {
	//		addOrUpdate(cf);
	//	}

}

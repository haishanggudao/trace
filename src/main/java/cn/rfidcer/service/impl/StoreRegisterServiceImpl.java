package cn.rfidcer.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.StoreRegister;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.StoreRegisterMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.StoreRegisterService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class StoreRegisterServiceImpl extends BaseServiceImpl<StoreRegister> implements StoreRegisterService{

	@Autowired
	private StoreRegisterMapper storeRegisterMapper;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ResultMsg addOrUpdateStoreRegister(User user,
			StoreRegister storeRegister) {
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		if(StringUtils.isEmpty(storeRegister.getId())){
			info="新增";
			logger.info("新增门店注册："+storeRegister);
			storeRegister.setId(UUIDGenerator.generatorUUID());
			String locationNum=storeRegisterMapper.findLocationByCompanyId(storeRegister);
			if (locationNum!=null) {
				storeRegister.setLocation(locationNum);
				String subLocation=storeRegisterMapper.insertBySubLocation(storeRegister);
				if (Integer.parseInt(subLocation)<10) {
					storeRegister.setSubLocation('0'+subLocation);
				}else{
					storeRegister.setSubLocation(subLocation);
				}
				storeRegister.setLicense(getLicense(storeRegister.getCompanyId())+storeRegister.getLocation()+storeRegister.getSubLocation());
				CommonImportUtils.setUpdateTime(storeRegister, user);
				storeRegister.setId(UUIDGenerator.generatorUUID());
				res=storeRegisterMapper.insert(storeRegister);
			}else{
				String location=storeRegisterMapper.addLocation();
				if(location==null){
					storeRegister.setLocation("01");
				}else{
					if (Integer.parseInt(location)<10) {
						storeRegister.setLocation("0"+storeRegisterMapper.addLocation());
					}else{
						storeRegister.setLocation(location);
					}
				}
			   storeRegister.setSubLocation("01");
			   storeRegister.setLicense(getLicense(storeRegister.getCompanyId())+storeRegister.getLocation()+storeRegister.getSubLocation());
			   CommonImportUtils.setUpdateTime(storeRegister, user);
			   storeRegister.setId(UUIDGenerator.generatorUUID());
			   res=storeRegisterMapper.insert(storeRegister);
			}
		}
		if (res == 1) {
			resultMsg.setCode("1");
			resultMsg.setMsg(info + "成功" + " ");
		} else{
			resultMsg.setCode("0");
			resultMsg.setMsg(info + "失败");
		}
		return resultMsg;
	}

	@Override
	public List<StoreRegister> findAllList(Page page,
			StoreRegister storeRegister) {
		return storeRegisterMapper.findAllList(page,storeRegister);
	}
	
	@Override
	public String findLocationByCompanyId(StoreRegister storeRegister){
		return storeRegisterMapper.findLocationByCompanyId(storeRegister);
	}

	
	@Override
	public String getLicense(String companyId) {
		//return DateUtil.format(new Date(),"yyMMddHHmmssSSS");
		String companyCode=storeRegisterMapper.findCompanyCode(companyId);
		if (companyCode!=null) {
			return companyCode;
		}else {
           return DateUtil.format(new Date(),"yyMMddSSS");			
		}
	}
	
	@Override
	public ResultMsg deleteStoreRegister(StoreRegister storeRegister){
		ResultMsg resultMsg=new ResultMsg();
		String info="删除";
		logger.info("删除账户:"+storeRegister);
		int res=0;
		res=storeRegisterMapper.deleteByPrimaryKey(storeRegister);
		if(res==1){
			resultMsg.setCode("1");
			resultMsg.setMsg(info + "成功" + " ");
		} else{
			resultMsg.setCode("0");
			resultMsg.setMsg(info + "失败");
		}
		return resultMsg;
	}
}

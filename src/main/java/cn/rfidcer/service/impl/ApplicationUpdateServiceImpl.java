package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ApplicationUpdate;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ApplicationUpdateMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.dto.Data;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ApplicationUpdateService;
import cn.rfidcer.service.ProductBaseService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ApplicationUpdateServiceImpl extends BaseServiceImpl<ApplicationUpdate> implements ApplicationUpdateService{

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationUpdateMapper applicationUpdateMapper;
	
	@Autowired
	@Qualifier("productBaseServiceImpl")
	private ProductBaseService productBaseService;
	
	@Autowired
	protected SysVariableDao variableDao;
	
	@Override
	public ResultMsg addorUpdateApp(ApplicationUpdate applicationUpdate,User user) {
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		String webPath = variableDao.getValByKey("appPath");
		String strogePath = variableDao.getValByKey("appStrogePath");
		if(applicationUpdate.getStatus()==null){
			applicationUpdate.setStatus("0");
		}else{
			applicationUpdate.setStatus("1");
		}
		if(StringUtils.isEmpty(applicationUpdate.getId())){
			info="新增";
			logger.info("新增应用："+applicationUpdate);
			String savePath = productBaseService.saveFile(applicationUpdate.getApplicationfile(), applicationUpdate.getApplicationPath(),webPath,strogePath);
			applicationUpdate.setApplicationPath(savePath);
			applicationUpdate.setId(UUIDGenerator.generatorUUID());
			CommonImportUtils.setCreateAndUpdateTime(applicationUpdate, user);
			res=applicationUpdateMapper.insert(applicationUpdate);
		}else{
			info="修改";
			logger.info("修改应用："+applicationUpdate);
			if(applicationUpdate.getApplicationfile()!=null&&applicationUpdate.getApplicationfile().getSize()>0){
				String savePath = productBaseService.saveFile(applicationUpdate.getApplicationfile(), applicationUpdate.getApplicationPath(),webPath,strogePath);
				applicationUpdate.setApplicationPath(savePath);
			}
			CommonImportUtils.setUpdateTime(applicationUpdate, user);
			res=applicationUpdateMapper.updateByPrimaryKey(applicationUpdate);
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
	public List<ApplicationUpdate> findAllList(Page page,
			ApplicationUpdate applicationUpdate) {
		return applicationUpdateMapper.findAllList(page,applicationUpdate);
	}

	@Override
	public ResponseData<ApplicationUpdate> getLastVersion() {
		ResponseData<ApplicationUpdate> res=new ResponseData<ApplicationUpdate>();
		ApplicationUpdate lastVersion = applicationUpdateMapper.getLastVersion();
		res.setValue(new Data<ApplicationUpdate>(lastVersion));
		res.setResult(1);
		return res;
	}

}

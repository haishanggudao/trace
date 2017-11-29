package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.StoreAccount;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.StoreAccountMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.StoreAccountService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class StoreAccountServiceImpl extends BaseServiceImpl<StoreAccount> implements StoreAccountService{

	@Autowired
	private StoreAccountMapper storeAccountMapper;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ResultMsg addOrUpdateStoreAccount(User user,
			StoreAccount storeAccount) {
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		if(StringUtils.isEmpty(storeAccount.getId())){
			info="新增";
			logger.info("新增账户："+storeAccount);
			storeAccount.setId(UUIDGenerator.generatorUUID());
			CommonImportUtils.setCreateAndUpdateTime(storeAccount, user);
			res=storeAccountMapper.insert(storeAccount);
		}else{
			info="修改";
			logger.info("修改账户："+storeAccount);
			CommonImportUtils.setUpdateTime(storeAccount, user);
			res=storeAccountMapper.updateByPrimaryKeySelective(storeAccount);
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
	public List<StoreAccount> findAllList(Page page,
			StoreAccount storeAccount) {
		return storeAccountMapper.findAllList(page,storeAccount);
	}
	
	@Override
	public ResultMsg deleteStoreAccount(StoreAccount storeAccount){
		ResultMsg resultMsg=new ResultMsg();
		String info="删除";
		logger.info("删除账户:"+storeAccount);
		int res=0;
		res=storeAccountMapper.deleteByPrimaryKey(storeAccount);
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

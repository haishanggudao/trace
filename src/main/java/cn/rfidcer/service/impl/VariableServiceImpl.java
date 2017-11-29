package cn.rfidcer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SysVariable;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.VariableService;
import cn.rfidcer.util.ResultMsgUtil;

@Service
public class VariableServiceImpl implements VariableService{

	@Autowired
	private SysVariableDao variableDao;
	@Override
	public List<SysVariable> findAllVariables(Page page) {
		return variableDao.findAllVariables(page);
	}
	@Override
	public ResultMsg updateVariableById(SysVariable sysVariable) {
		int res = variableDao.updateVariableById(sysVariable);
		return ResultMsgUtil.getReturnMsg(res, "修改系统参数");
	}
	
	@Override
	public String getValByKey(String key) {
		return variableDao.getValByKey(key);
	}

}

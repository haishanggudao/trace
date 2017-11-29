package cn.rfidcer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.service.CommonVariableService;

@Service
public class CommonVariableServiceImpl implements CommonVariableService {

	@Autowired
	private CommonVariableMapper commonVariableMapper;
	
	@Override
	public List<CommonVariable> getDeliverType(String companyId, String varGroup, String varName) {
		return commonVariableMapper.selectByCVV(companyId,varGroup,varName);
	}

	@Override
	public List<CommonVariable> getCommonVariables(CommonVariable commonVariable) {
		return commonVariableMapper.getCommonVariables(commonVariable);
	}

	@Override
	public CommonVariable findOne(CommonVariable commonVariable) {
		return commonVariableMapper.selectOne(commonVariable);
	}

}
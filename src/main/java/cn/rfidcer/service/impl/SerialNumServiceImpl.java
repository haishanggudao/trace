package cn.rfidcer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.service.SerialNumService;

@Service
public class SerialNumServiceImpl implements SerialNumService{

	@Autowired
	private CommonVariableMapper commonVariableMapper;
	

	@Override
	public String newSerialNum(CommonVariable commonVariable){
			String serialNum = null;
			commonVariableMapper.updateVarvalueIncreament(commonVariable);
			commonVariable.setVarValue(null);
			List<CommonVariable> commonVariables = commonVariableMapper.getCommonVariables(commonVariable);
			if(commonVariables!=null&&!commonVariables.isEmpty()){
				serialNum=commonVariables.get(0).getVarValue();
			}
			return serialNum;
	}
}

package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.CommonVariable;

public interface CommonVariableService {

	List<CommonVariable> getDeliverType(String companyId, String varGroup, String varName);

	/**根据companyId和varGroup获取所有变量值
	 * @param commonVariable
	 * @return
	 */
	List<CommonVariable> getCommonVariables(CommonVariable commonVariable);
	
	CommonVariable findOne(CommonVariable commonVariable);
	
}
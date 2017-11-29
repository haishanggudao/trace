package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SysVariable;
import cn.rfidcer.interceptor.Page;

public interface VariableService {

	List<SysVariable> findAllVariables(Page page);

	/**修改系统参数
	 * @param sysVariable
	 * @return
	 */
	ResultMsg updateVariableById(SysVariable sysVariable);

	String getValByKey(String key);
}

package cn.rfidcer.dao;

import java.util.List;

import cn.rfidcer.bean.SysVariable;
import cn.rfidcer.interceptor.Page;


/**系统变量
 * @author xzm
 *
 */
public interface SysVariableDao {


	/**查询系统参数
	 * @return
	 */
	List<SysVariable> findAllVariables(Page page);

	int updateVariableById(SysVariable sysVariable);
	
	String getValByKey(String varKey);
	
}

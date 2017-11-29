package cn.rfidcer.service.jinji;

import java.util.List;

import cn.rfidcer.bean.ProcessItem;
import cn.rfidcer.bean.ProcessMain; 
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**   金机餐饮加工管理
* @Title: ProcessService.java 
* @Package cn.rfidcer.service.jinji 
* @Description:
* @author 席志明
* @Copyright Copyright
* @date 2016年7月26日 下午7:31:47 
* @version V1.0   
*/
public interface ProcessService { 
	
	/**新增或修改加工记录
	 * @param processMain
	 * @param processDetail
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateProcess(ProcessMain processMain,String processDetail, User user);
	
	/**删除加工记录
	 * @param processMain
	 * @return
	 */
	ResultMsg delProcess(ProcessMain processMain);
	
	/**查询加工记录
	 * @param page
	 * @param processMain
	 * @return
	 */
	List<ProcessMain> getProcessList(Page page, ProcessMain processMain);


	List<ProcessItem> getProcessItemList(Page page, ProcessMain processMain);

	
}

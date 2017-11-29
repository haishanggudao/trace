package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ProcessItem;
import cn.rfidcer.bean.ProcessMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**   拆分管理
* @Title: SplitProductService.java 
* @Package cn.rfidcer.service 
* @Description:
* @author 席志明
* @Copyright Copyright
* @date 2016年7月27日 上午10:20:12 
* @version V1.0   
*/
public interface SplitProductService { 
	
	
	/**删除拆分管理
	 * @param processMain
	 * @return
	 */
	ResultMsg delSplit(ProcessMain processMain);

	/**新增或修改拆分管理
	 * @param processMain
	 * @param processDetail
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateSplitProduct(ProcessMain processMain,String processDetail, User user);

	/**删除拆分管理明细
	 * @param processItem
	 * @return
	 */
	ResultMsg delSplitItem(ProcessItem processItem);

	/**查询拆分明细
	 * @param page
	 * @param processMain
	 * @return
	 */
	List<ProcessItem> getSplitItemList(Page page, ProcessMain processMain);

	/**查询拆分记录
	 * @param page
	 * @param processMain
	 * @return
	 */
	List<ProcessMain> getSplitProductList(Page page, ProcessMain processMain);
	
}

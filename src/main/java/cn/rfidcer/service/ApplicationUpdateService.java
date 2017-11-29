package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ApplicationUpdate;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.interceptor.Page;

/**   
* @Description:应用升级service
* @author 席志明
* @Copyright Copyright
* @date 2016年11月3日 下午2:38:51 
* @version V1.0   
*/
public interface ApplicationUpdateService extends BaseService<ApplicationUpdate>{

	/**新增或修改应用
	 * @param applicationUpdate
	 * @return
	 */
	ResultMsg addorUpdateApp(ApplicationUpdate applicationUpdate,User user);

	List<ApplicationUpdate> findAllList(Page page,ApplicationUpdate applicationUpdate);

	ResponseData<ApplicationUpdate> getLastVersion();

}

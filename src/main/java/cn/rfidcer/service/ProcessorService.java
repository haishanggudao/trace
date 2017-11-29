package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.Processor;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.interceptor.Page;

/**   
* @Description:加工者service
* @author 席志明
* @Copyright Copyright
* @date 2016年10月26日 下午2:21:14 
* @version V1.0   
*/
public interface ProcessorService extends BaseService<Processor>{

	/**分页查询加工者
	 * @param page
	 * @param processor
	 * @return
	 */
	List<Processor> findAllList(Page page, Processor processor);

	ResultMsg addorUpdateProcessor(Processor processor);

}

package cn.rfidcer.service.yz;

import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;

/**   
* @Title: SellStoreService.java 
* @Package cn.rfidcer.service.yz 
* @Description: Service 羽众酒业-零售门店 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月2日 下午1:22:40 
* @version V1.0   
*/
public interface SellStoreService {
	
	/**
	 * 新增或编辑零售门店信息; created by jie.jia at 2016-08-02 13:25
	 * @param customers
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdate(Customers customers,User user);

}

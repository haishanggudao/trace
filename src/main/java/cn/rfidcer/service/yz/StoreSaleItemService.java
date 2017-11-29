package cn.rfidcer.service.yz;

import java.util.List;

import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.interceptor.Page;

/**   
* @Title: StoreSaleItemService.java 
* @Package cn.rfidcer.service.yz 
* @Description: Service 门店销售明细
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月12日 上午11:44:55 
* @version V1.0   
*/
public interface StoreSaleItemService {
	
	/**
	 * 获取所有的门店销售明细; created by jie.jia at 2016-07-12 11:47 
	 * @param page
	 * @param storeSaleItem
	 * @return
	 */
	List<StoreSaleItem> findAll(Page page, StoreSaleItem storeSaleItem);

}

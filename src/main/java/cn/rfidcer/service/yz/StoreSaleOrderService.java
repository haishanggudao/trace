package cn.rfidcer.service.yz;
 
import java.util.List;

import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.interceptor.Page;


/**   
 * @Title: StoreSaleOrderService.java 
 * @Package cn.rfidcer.service.yz 
 * @Description: 门店销售主单 service
 * @author jgx
 * @Copyright Copyright
 * @date 2016年7月11日 下午2:12:28 
 * @version V1.0   
*/
public interface StoreSaleOrderService {


	List<StoreSaleOrder> findAll(Page page, StoreSaleOrder storeSaleOrder);
	
	void addOrUpdateSaleOrder(StoreSaleOrder storeSaleOrder);
	
}

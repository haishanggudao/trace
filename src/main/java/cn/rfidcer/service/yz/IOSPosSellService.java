package cn.rfidcer.service.yz;

import java.util.List;

import cn.rfidcer.bean.yz.WsSaleOrder;
import cn.rfidcer.dto.ResponseData;

/**   
* @Title: IOSPosSellService.java 
* @Package cn.rfidcer.service.yz 
* @Description: service 羽众酒业-iOS 销售相关
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月29日 下午6:50:54 
* @version V1.0   
*/
public interface IOSPosSellService {
	
	/**
	 * 已销售分页查询; created by jie.jia at 2016-08-29 19:05
	 * @param storeId
	 * @param dateType
	 * @param currPageIndex
	 * @param pageSize
	 * @return
	 */
	ResponseData<List<WsSaleOrder>> GetSaleListByPage(String storeId, String entId, int dateType, int currPageIndex, int pageSize);
	
	/**
	 * 已销售记录数; created by jie.jia at 2016-08-30 15:26
	 * @param storeId 门店ID
	 * @param dateType 1-本日;3-本月;其他-本周
	 * @return
	 */
	ResponseData<Integer> GetSaleRecordCount(String storeId, String entId, int dateType);

}

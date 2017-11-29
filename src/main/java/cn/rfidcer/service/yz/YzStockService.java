package cn.rfidcer.service.yz;

import java.util.List;

import cn.rfidcer.bean.yz.YzProductStock;
import cn.rfidcer.dto.ResponseData;

/**   
* @Description:
* @author 席志明
* @date 2016年8月30日 上午11:42:54 
* @version V1.0   
*/
public interface YzStockService {

	/**获取店铺商品库存
	 * @param entId 企业ID
	 * @param currPageIndex 第几页
	 * @param pageSize 条数
	 * @return
	 */
	ResponseData<List<YzProductStock>> getGoodsInventoryListByPage(String entId,int currPageIndex,int pageSize);
}

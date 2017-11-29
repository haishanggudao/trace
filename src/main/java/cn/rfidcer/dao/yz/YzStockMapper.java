package cn.rfidcer.dao.yz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.yz.YzProductStock;
import cn.rfidcer.interceptor.Page;

/**   
* @Description:羽众库存
* @author 席志明
* @date 2016年8月30日 上午11:49:13 
* @version V1.0   
*/
public interface YzStockMapper {

	List<YzProductStock> getGoodsInventoryListByPage(@Param("entId") String entId,Page page);
}

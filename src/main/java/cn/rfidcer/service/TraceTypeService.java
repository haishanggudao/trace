package cn.rfidcer.service;

import cn.rfidcer.bean.User;
import cn.rfidcer.enums.TraceType;

/**   
* @Title: TraceTypeService.java 
* @Package cn.rfidcer.service 
* @Description:追溯类型
* @author 席志明
* @Copyright Copyright
* @date 2016年8月9日 上午10:10:00 
* @version V1.0   
*/
public interface TraceTypeService {

	/**新增普通商品和销售单商品的追溯类型
	 * @param traceCode
	 * @param traceType
	 * @param createBy
	 * @return
	 */
	int insertTraceType(String traceCode,TraceType traceType,String createBy);
	
	/**根据商品的配送类型绝对追溯类型
	 * @param goodsId
	 * @param currentUser
	 * @param goodsCode
	 * @return
	 */
	int insertTraceTypeByDeliverType(String goodsId, User currentUser,String goodsCode);
}

package cn.rfidcer.service;
 
import cn.rfidcer.bean.DeliveryQRCode;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.TraceInfo;
import cn.rfidcer.bean.UserToken;

/**手持机和手机逻辑
 * @author xzm
 *
 */
public interface HandShakeService {

	/**确认配送单
	 * @param orderId 配送单的二维码ID
	 * @return
	 */
	ResultMsg updateDeliveryOrder(String orderId,UserToken userToken);

	/**关联配送单与商品关系，确认商品到达时间
	 * @param deliveryQRCode
	 * @return
	 */
	ResultMsg addDeliveryOrderAndGoodOrderRelation(DeliveryQRCode deliveryQRCode,String username,String token);
	
	/**
	 * 查询销售单信息
	 * @param outstockMainId 出库单ID
	 * @return
	 */
	ResultMsg querySaleOrder(String outstockMainId,UserToken userToken);
	
	/**根据二维码网址获取追溯信息
	 * @param qrAddress
	 * @return
	 */
	ResultMsg getTraceInfo(String qrAddress);
	
	TraceInfo getTraceInfoByCode(String qrAddress);
	
	/**
	 * 依据productStandardDetailId 来获取追溯信息; created by jie.jia at 2016-10-26 17:12
	 * @param productStandardDetailId
	 * @return
	 */
	TraceInfo getTraceInfoByProductStandardDetailId(String productStandardDetailId);
	
	/**根据企业ID进行页面跳转
	 * @param companyId
	 * @return
	 */
	String getTracePageByCompany(String companyId);

}

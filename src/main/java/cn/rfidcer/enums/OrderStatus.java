package cn.rfidcer.enums;

/**订单状态
 * @author xzm
 *
 */
public class OrderStatus {

	public static final String order_create="1";//待生成
	public static final String order_send="2";//已生成订单，发送到供应商
	public static final String order_deliver="3";//供应商已发货
	public static final String order_receive="4";//确认收货
	public static final String order_storage="5";//已入库
}

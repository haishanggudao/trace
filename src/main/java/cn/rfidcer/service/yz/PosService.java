package cn.rfidcer.service.yz;
 
import cn.rfidcer.bean.DeliveryQRCode;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.UserToken;
import cn.rfidcer.bean.yz.StockOrderInfo;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.yz.YzGoodsInfo;

/**手持机和手机逻辑
 * @author xzm
 *
 */
public interface PosService {

	/**门店入库单
	 * @param deliveryQRCode
	 * @return
	 */
	ResultMsg addStoreInstock(DeliveryQRCode deliveryQRCode);
	
	/**根据商品二维码查找商品信息
	 * @param qrcode
	 * @return
	 */
	ResultMsg getGoodsDetailByBarCode(YzGoodsInfo yzGoodsInfo,UserToken userToken);
	/**
	 * 添加门店采购单
	 * @param saleOrder
	 * @param user
	 * @param saleItemList
	 * @return
	 */
	ResultMsg addStorePurchase(StockOrderInfo stockOrderInfo);
	
	/**
	 * 添加门店销售记录
	 * @param stockOrderInfo
	 * @return
	 */
	ResultMsg addStoreSaleOrder(StoreSaleOrder storeSaleOrder);
	
}

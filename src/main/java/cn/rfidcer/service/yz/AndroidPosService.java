package cn.rfidcer.service.yz;
 
import java.util.List;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.StoreAccount;
import cn.rfidcer.bean.StoreMac;
import cn.rfidcer.bean.StoreRegister;
import cn.rfidcer.bean.UserToken;
import cn.rfidcer.bean.yz.GoodsDetailInfo;
import cn.rfidcer.bean.yz.StoreInstockInfo;
import cn.rfidcer.bean.yz.StorePurchaseOrder;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.dto.ProductParam;


/**   
 * @Title: AndroidPosService.java 
 * @Package cn.rfidcer.service.yz 
 * @Description:羽众安卓接口
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月19日 下午3:10:32 
 * @version V1.0   
*/
public interface AndroidPosService {

	
	/**
	 * 门店Mac地址编号获取
	 * @param  posMac
	 * @return
	 */
	ResultMsg getMacNum(StoreMac posMac);

	
	/**门店入库单
	 * @param deliveryQRCode
	 * @return
	 */
	ResultMsg addStoreInstock(StoreInstockInfo storeInstockInfo);
	
	/**根据商品二维码查找商品信息
	 * @param qrcode
	 * @return
	 */
	ResultMsg getGoodsDetailByBarCode(GoodsDetailInfo goodsDetailInfo,UserToken userToken);
	/**
	 * 添加门店采购单
	 * @param saleOrder
	 * @param user
	 * @param saleItemList
	 * @return
	 */
	ResultMsg addStorePurchase(StorePurchaseOrder storePurchaseOrder);
	
	/**
	 * 添加门店销售记录
	 * @param stockOrderInfo
	 * @return
	 */
	ResultMsg addStoreSaleOrder(StoreSaleOrder storeSaleOrder);


	/**注册门店pos机
	 * @param license 注册码
	 * @param mac mac地址
	 * @return
	 */
	ResultMsg getInitInfo(StoreRegister storeRegister);


	/**获取门店账号密码
	 * @param storeAccount
	 * @return
	 */
	ResultMsg getLoginInfo(StoreAccount storeAccount);


	/**同步基础信息
	 * @param productParam
	 * @return
	 */
	ResultMsg getBaseInfo(ProductParam productParam);
	
	/**
	 * 门店销售记录同步接口
	 * @param stockOrderInfo
	 * @return
	 */
	ResultMsg syncStoreSaleOrder(List<StoreSaleOrder> storeSaleOrderList);
	
	/**
	 * 门店销售记录同步接口
	 * @param stockOrderInfo
	 * @return
	 */
	ResultMsg syncStoreInstock(List<StoreInstockInfo> storeInstockInfoList);
	
	/**
	 * 门店销售记录同步接口
	 * @param stockOrderInfo
	 * @return
	 */
	ResultMsg syncStorePurchase(List<StorePurchaseOrder> storePurchaseOrderList);
	
}

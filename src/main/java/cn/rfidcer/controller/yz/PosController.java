package cn.rfidcer.controller.yz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.rfidcer.bean.ClientUser;
import cn.rfidcer.bean.DeliveryQRCode;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.yz.StockOrderInfo;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.yz.YzGoodsInfo;
import cn.rfidcer.service.ClientUserService; 
import cn.rfidcer.service.yz.PosService;
import cn.rfidcer.util.UUIDGenerator;

/**   
 * @Title: PosController.java 
 * @Package cn.rfidcer.controller.yz 
 * @Description: 于众手持机接口
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月25日 下午2:28:11 
 * @version V1.0   
*/
@RequestMapping("/yzws")
@Controller
public class PosController {

	@Autowired
	private PosService posService;
	@Autowired
	private ClientUserService clientUserService;
	
	/**
	 * 手持设备的用户登录; created by jie.jia at 2016-03-02 11:40
	 * @param clientUser
	 * @return
	 */
	@RequestMapping("/loginClientUser")
	@ResponseBody
	public ResultMsg loginClientUser(@RequestBody ClientUser clientUser){
		return clientUserService.createLoginClientUser(clientUser);
	}
	
	/**
	 * 门店入库单接口
	 * @param deliveryQRCode
	 * @param username
	 * @param token
	 * @return
	 */
	@RequestMapping("/addDeliveryOrder")
	@ResponseBody
	public ResultMsg addStoreInstock(@RequestBody DeliveryQRCode deliveryQRCode){
		return posService.addStoreInstock(deliveryQRCode);
	}
	
	/**
	 * 门店采购单接口
	 * @param saleOrder
	 * @param currentUser
	 * @param saleItemList
	 * @return
	 */
	@RequestMapping(value="/getStockOrder")
	@ResponseBody
	public ResultMsg addStorePurchase(@RequestBody  StockOrderInfo stockOrderInfo){
		return posService.addStorePurchase(stockOrderInfo);
	}
	/**
	 * 扫商品条形码获取信息接口
	 * @param qrcode
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/getGoodsDetailByQRCode")
	@ResponseBody
	public ResultMsg getGoodsDetailByBarCode(@RequestBody YzGoodsInfo yzGoodsInfo){
		return posService.getGoodsDetailByBarCode(yzGoodsInfo,null);
	}
	/**
	 * 添加门店销售记录
	 * @param qrcode
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/addStoreSaleOrder")
	@ResponseBody
	public ResultMsg addStoreSaleOrder(@RequestBody StoreSaleOrder storeSaleOrder){
		return posService.addStoreSaleOrder(storeSaleOrder);
	}
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		/*
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmSSS").format(new Date()));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		*/
		String storeSaleOrderId = UUIDGenerator.generatorUUID();
		StoreSaleOrder storeSaleOrder = new StoreSaleOrder();
		storeSaleOrder.setOrderTime(new Date());
		storeSaleOrder.setCompanyId("4f215af6f59b42e9b2e3a1df4af26b7f");
		storeSaleOrder.setStoreSaleOrdeNo(new SimpleDateFormat("yyyyMMddHHmmSSS").format(new Date()));
		storeSaleOrder.setStoreSaleOrderId(storeSaleOrderId);
		storeSaleOrder.setCompanyCode("310118689");
		storeSaleOrder.setUserId("小张");
		storeSaleOrder.setClubCard("010010156");
		storeSaleOrder.setDiscountPrice(new BigDecimal("5.00"));
		storeSaleOrder.setPayType(1);//付款方式现金支付
		storeSaleOrder.setTotalPrice(new BigDecimal("415.02"));
		List<StoreSaleItem> items =new ArrayList<StoreSaleItem>();
		
		StoreSaleItem item = new StoreSaleItem();
		item.setGoodsBarCode("310310118278201607071021026");
		item.setStoreSaleItemId(UUIDGenerator.generatorUUID());
		item.setUnitName("L");
		item.setQuantity(new BigDecimal("3"));
		item.setSalePrice(new BigDecimal("30.50"));
		item.setStoreSaleOrderId(storeSaleOrderId);
		item.setTotalPrice(new BigDecimal("91.5"));
		items.add(item);
		
		StoreSaleItem item2 = new StoreSaleItem();
		item2.setGoodsBarCode("310310118278201607071021027");
		item2.setStoreSaleItemId(UUIDGenerator.generatorUUID());
		item2.setUnitName("L");
		item2.setQuantity(new BigDecimal("4"));
		item2.setSalePrice(new BigDecimal("80.88"));
		item2.setTotalPrice(new BigDecimal("323.52"));
		item2.setStoreSaleOrderId(storeSaleOrderId);
		items.add(item2);
		
		storeSaleOrder.setItems(items);
		System.out.println(JSONObject.toJSONString(storeSaleOrder));
		
		/*
		DeliveryQRCode deliveryQRCode = new DeliveryQRCode();
		List<GoodQRCode> goodList=new ArrayList<GoodQRCode>();
		GoodQRCode e = new GoodQRCode();
		e.setGoodQRCodeId("test12316070447176");
		GoodQRCode e1 = new GoodQRCode();
		e1.setGoodQRCodeId("test12316070447176");
		goodList.add(e);
		goodList.add(e1);
		deliveryQRCode.setDeliveryQRCode("20160704144509");
		deliveryQRCode.setGoodList(goodList);
		System.out.println(JSONObject.toJSONString(deliveryQRCode));
		*/
		//handShakeService.addDeliveryOrderAndGoodOrderRelation(deliveryQRCode,"0","0");
		
	}
}

package cn.rfidcer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.ClientUser;
import cn.rfidcer.bean.DeliveryQRCode;
import cn.rfidcer.bean.GoodQRCode;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.TraceInfo;
import cn.rfidcer.bean.UserToken;
import cn.rfidcer.service.ClientUserService;
import cn.rfidcer.service.GoodsDetailService;
import cn.rfidcer.service.HandShakeService;

import com.alibaba.fastjson.JSONObject;

/**
* @Title: HandShakeController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 手持机服务;
* @author xzm & jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午10:19:56 
* @version V1.0
 */
@RequestMapping("/ws")
@Controller
public class HandShakeController {

	@Autowired
	private HandShakeService handShakeService;
	
	@Autowired
	private GoodsDetailService goodsDetailService;
	 
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
	
	/**确认配送单的配送时间
	 * @return
	 */
	@RequestMapping("/confirmDeliveryOrder/{orderId}")
	@ResponseBody
	public ResultMsg confirmDeliveryOrder(@PathVariable String orderId,@RequestBody UserToken userToken){
		return handShakeService.updateDeliveryOrder(orderId,userToken);
	}
	
	/**
	 * 扫描出库单二维码获取信息接口
	 * @param outstockMainId
	 * @param userToken
	 * @return
	 */
	@RequestMapping("/querySaleOrder/{outstockMainId}")
	@ResponseBody
	public ResultMsg querySaleOrder(@PathVariable String outstockMainId,@RequestBody UserToken userToken){
		return handShakeService.querySaleOrder(outstockMainId,userToken);
	}
	
	/**
	 * 确认收货接口
	 * @param deliveryQRCode
	 * @param username
	 * @param token
	 * @return
	 */
	@RequestMapping("/addDeliveryOrder/{username}/{token}")
	@ResponseBody
	public ResultMsg addDeliveryOrderAndGoodOrderRelation(@RequestBody DeliveryQRCode deliveryQRCode,@PathVariable String username,@PathVariable String token){
		return handShakeService.addDeliveryOrderAndGoodOrderRelation(deliveryQRCode,username,token);
	}
	
	/**
	 * 扫描商品二维码获取信息接口
	 * @param qrcode
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/getGoodsDetailByQRCode/{qrcode}")
	@ResponseBody
	public ResultMsg getGoodsDetailByQRCode(@PathVariable String qrcode,@RequestBody UserToken userToken){
		return goodsDetailService.getGoodsByQRCode(qrcode,userToken);
	}
	
	/**
	 * 根据商品明细二维码或商品二维码查询追溯信息
	 * @param qrAddress 商品明细二维码或商品二维码
	 * @return
	 */
	@RequestMapping(value="/getTraceInfo/{qrAddress}",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg getTraceInfo(@PathVariable String qrAddress){
		return handShakeService.getTraceInfo(qrAddress);
	}
	
	/**
	 * 根据商品明细二维码或商品二维码查询追溯信息
	 * @param qrAddress
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getTrace/{qrAddress}")
	public String getTraceInfoPage(@PathVariable String qrAddress, ModelMap map, HttpServletRequest request) {
		TraceInfo traceInfo = handShakeService.getTraceInfoByCode(qrAddress);
		map.put("traceInfo", traceInfo);
		String companyId=traceInfo==null?null:traceInfo.getGoodsInfo()==null?null:traceInfo.getGoodsInfo().getCompanyId();
		return handShakeService.getTracePageByCompany(companyId);
	}
	
	/**
	 * 根据productStandardDetailId来追溯商品的信息; created by jie.jia at 2016-10-26 17:10
	 * @param qrAddress
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getTraceJinji/{productStandardDetailId}")
	public String getTraceJinjiInfoPage(@PathVariable String productStandardDetailId, ModelMap map, HttpServletRequest request) {
		TraceInfo traceInfo = handShakeService.getTraceInfoByProductStandardDetailId(productStandardDetailId);
		map.put("traceInfo", traceInfo); 
		return "jinji/trace";
	}
	
	public static void main(String[] args) {
		DeliveryQRCode deliveryQRCode = new DeliveryQRCode();
		List<GoodQRCode> goodList=new ArrayList<GoodQRCode>();
		GoodQRCode e = new GoodQRCode();
		e.setGoodQRCodeId("111");
		GoodQRCode e1 = new GoodQRCode();
		e1.setGoodQRCodeId("112");
		goodList.add(e);
		goodList.add(e1);
		deliveryQRCode.setDeliveryQRCode("1");
		deliveryQRCode.setGoodList(goodList);
		System.out.println(JSONObject.toJSONString(deliveryQRCode));
	}
}

package cn.rfidcer.controller.yz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.ApplicationUpdate;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.StoreAccount;
import cn.rfidcer.bean.StoreMac;
import cn.rfidcer.bean.StoreRegister;
import cn.rfidcer.bean.yz.GoodsDetailInfo;
import cn.rfidcer.bean.yz.StoreInstockInfo;
import cn.rfidcer.bean.yz.StorePurchaseOrder;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.dto.ProductParam;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.service.ApplicationUpdateService;
import cn.rfidcer.service.yz.AndroidPosService;

/**   
 * @Title: AndroidPosController.java 
 * @Package cn.rfidcer.controller.yz 
 * @Description: 羽众安卓接口
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月25日 下午2:27:56 
 * @version V1.0   
*/
@RequestMapping("/yzAndroid")
@Controller
@Api(tags="Android",description="羽众安卓调用接口")
public class AndroidPosController {

	@Autowired
	private AndroidPosService androidPosService;
	
	@Autowired
	private ApplicationUpdateService applicationUpdateService;
	/**
	 * 门店Mac地址获取
	 * @param posMac
	 * @return
	 */
	@RequestMapping(value="/getMacNum",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="门店Mac地址获取", httpMethod ="POST", response=ResultMsg.class, notes ="门店Mac地址获取")
	public ResultMsg getMacNum(@RequestBody  StoreMac posMac){
		return androidPosService.getMacNum(posMac);
	}
	/**
	 * 门店采购单接口
	 * @param saleOrder
	 * @param currentUser
	 * @param saleItemList
	 * @return
	 */
	@RequestMapping(value="/addStorePurchase",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="门店采购单", httpMethod ="POST", response=ResultMsg.class, notes ="门店采购单")	
	public ResultMsg addStorePurchase(@RequestBody  StorePurchaseOrder storePurchaseOrder){
		return androidPosService.addStorePurchase(storePurchaseOrder);
	}
	/**
	 * 扫商品条形码获取信息接口
	 * @param qrcode
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/getGoodsDetailByBarCode",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="扫商品条形码获取信息", httpMethod ="POST", response=ResultMsg.class, notes ="扫商品条形码获取信息")	
	public ResultMsg getGoodsDetailByBarCode(@RequestBody GoodsDetailInfo goodsDetailInfo){
		return androidPosService.getGoodsDetailByBarCode(goodsDetailInfo,null);
	}
	/**
	 * 门店入库单接口
	 * @param deliveryQRCode
	 * @param username
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/addStoreInstock",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="门店入库单", httpMethod ="POST", response=ResultMsg.class, notes ="门店入库单")	
	public ResultMsg addStoreInstock(@RequestBody StoreInstockInfo storeInstockInfo){
		return androidPosService.addStoreInstock(storeInstockInfo);
	}	
	/**
	 * 门店销售记录
	 * @param qrcode
	 * @param userToken
	 * @return
	 */
	@RequestMapping(value="/addStoreSaleOrder",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="门店销售记录", httpMethod ="POST", response=ResultMsg.class, notes ="门店销售记录")	
	public ResultMsg addStoreSaleOrder(@RequestBody StoreSaleOrder storeSaleOrder){
		return androidPosService.addStoreSaleOrder(storeSaleOrder);
	}
	
	/**
	 * 系统初始化接口
	 * @param posMac
	 * @return
	 */
	@RequestMapping(value="/getInitInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="系统初始化接口", httpMethod ="POST", response=ResultMsg.class, notes ="系统初始化接口")
	public ResultMsg getInitInfo(@RequestBody StoreRegister storeRegister){
		return androidPosService.getInitInfo(storeRegister);
	}
	
	/**
	 * 系统登录账号同步接口
	 * @param posMac
	 * @return
	 */
	@RequestMapping(value="/getLoginInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
//	@ApiOperation(value="系统登录账号同步接口", httpMethod ="POST", response=ResultMsg.class, notes ="系统初始化接口")	
	public ResultMsg getLoginInfo(@RequestBody StoreAccount storeAccount){
		return androidPosService.getLoginInfo(storeAccount);
	}
	
	/**
	 * 同步基础信息接口
	 * @param posMac
	 * @return
	 */
	@RequestMapping(value="/getBaseInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="同步基础信息接口", httpMethod ="POST", response=ResultMsg.class, notes ="同步基础信息接口")	
	public ResultMsg getBaseInfo(@RequestBody ProductParam productParam){
		return androidPosService.getBaseInfo(productParam);
	}
	
	@RequestMapping(value="/version",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="获取最新版本", httpMethod ="POST", response=ResponseData.class, notes ="获取最新版本")
	public ResponseData<ApplicationUpdate> getLastVersion(){
		return applicationUpdateService.getLastVersion();
	}
	
	/**
	 * 门店采购单同步接口
	 * @param saleOrder
	 * @param currentUser
	 * @param saleItemList
	 * @return
	 */
	@RequestMapping(value="/syncStorePurchase",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="门店采购单同步接口", httpMethod ="POST", response=ResultMsg.class, notes ="门店采购单同步接口")	
	public ResultMsg syncStorePurchase(@RequestBody  List<StorePurchaseOrder> storePurchaseOrderList){
		return androidPosService.syncStorePurchase(storePurchaseOrderList);
	}
	/**
	 * 门店入库单同步接口
	 * @param storeInstockInfo
	 * @return
	 */
	@RequestMapping(value="/syncStoreInstock",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="门店入库单同步接口", httpMethod ="POST", response=ResultMsg.class, notes ="门店入库单同步接口")	
	public ResultMsg syncStoreInstock(@RequestBody List<StoreInstockInfo> storeInstockInfoList){
		return androidPosService.syncStoreInstock(storeInstockInfoList);
	}	
	/**
	 * 门店销售记录同步接口
	 * @param storeSaleOrderList
	 * @return
	 */
	@RequestMapping(value="/syncStoreSaleOrder",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="门店销售记录同步接口", httpMethod ="POST", response=ResultMsg.class, notes ="门店销售记录同步接口")	
	public ResultMsg syncStoreSaleOrder(@RequestBody List<StoreSaleOrder> storeSaleOrderList){
		return androidPosService.syncStoreSaleOrder(storeSaleOrderList);
	}
}

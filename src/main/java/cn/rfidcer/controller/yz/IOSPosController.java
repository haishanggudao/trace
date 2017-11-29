package cn.rfidcer.controller.yz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.yz.GoodsInfoList;
import cn.rfidcer.bean.yz.Province;
import cn.rfidcer.bean.yz.SaleTotal;
import cn.rfidcer.bean.yz.SaleTotalMoney;
import cn.rfidcer.bean.yz.SysUser;
import cn.rfidcer.bean.yz.WsSale;
import cn.rfidcer.bean.yz.WsSaleOrder;
import cn.rfidcer.bean.yz.WsSellStore;
import cn.rfidcer.bean.yz.YzProductType;
import cn.rfidcer.bean.yz.YzStoreSale;
import cn.rfidcer.bean.yz.YzUnit;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.service.yz.IOSPosSellService;
import cn.rfidcer.service.yz.IOSPosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**   
* @Title: IOSPosController.java 
* @Package cn.rfidcer.controller.yz 
* @Description: Controller 羽众酒业-IOS 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月25日 下午6:52:58 
* @version V1.0   
*/
@RequestMapping("/yzIOS")
@Controller
@Api(tags="IOS inteface")
public class IOSPosController {
	
	@Autowired
	private IOSPosService iOSPosService;
	
	@Autowired
	private IOSPosSellService iOSPosSellService;
	
	@RequestMapping(value="/GetProvinceList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="获取省份列表", httpMethod ="POST", response=ResponseData.class, notes ="获取省份列表")
	@ApiImplicitParams({@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")})
	public ResponseData<ArrayList<Province>> GetProvinceList(@RequestParam String code){
		return iOSPosService.getAllProvince();
	}
	
	/**IOS用戶登录
	 * @param sysUser
	 * @param code 授权码（死码，用处不大）
	 * @return
	 */
	@RequestMapping(value="/SysLogin",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="用戶登录", httpMethod ="POST", response=ResponseData.class, notes ="用戶登录")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="enterpriseNodeNo",value="用户名",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="psw",value="密码",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<SysUser> SysLogin(@RequestParam String enterpriseNodeNo,@RequestParam String psw,@RequestParam String code){
		return iOSPosService.SysLogin(new SysUser(enterpriseNodeNo,psw));
	}
	/**
	 * 全部店铺
	 * @param entId 输入参数entId（企业ID）
	 * @param code code（授权码）
	 * @return
	 */
	@RequestMapping(value="/GetAllStoreList",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="全部店铺", httpMethod ="POST", response=ResponseData.class, notes ="获取企业的下属门店信息")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<ArrayList<WsSellStore>> GetAllStoreList(@RequestParam String entId,@RequestParam String code){
		return iOSPosService.GetAllStoreList(entId); 
	}
	
	
	/**
	 * 获取今日销售汇总(果园项目)
	 * @param entId 输入参数entId（企业ID）
	 * @param code code（授权码）
	 * @return
	 */
	@RequestMapping(value="/AppHome",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="获取今日销售汇总", httpMethod ="POST", response=ResponseData.class, notes ="获取今日销售汇总")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<SaleTotal> AppHome(@RequestParam String entId,@RequestParam String code){
		return iOSPosService.AppHome(entId); 
	}
	

	/**
	 * 获取门店已销售总金额与总折扣金额
	 * @param entId
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/GetSaleTotalMoney",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="获取门店已销售总金额与总折扣金额", httpMethod ="POST", response=ResponseData.class, notes ="获取门店已销售总金额与总折扣金额")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="storeID",value="门店ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="dateType",value="1-本日;3-本月;其他-本周",required=true,dataType="int",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<SaleTotalMoney> GetSaleTotalMoney(@RequestParam String storeID,@RequestParam String entId,@RequestParam int dateType,@RequestParam String code){
		return iOSPosService.GetSaleTotalMoney(storeID,entId,dateType); 
	}
	/**
	 * 商品根据条件分页查询(果园项目)
	 * @param entId
	 * @param code
	 * @return 
	 */
	@RequestMapping(value="/GetGoodsInfoListByPage",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="商品根据条件分页查询(果园项目)", httpMethod ="POST", response=ResponseData.class, notes ="商品根据条件分页查询(果园项目)")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="goodCodeOrName",value="商品code或名称关键字",dataType="String",paramType="form"),
				@ApiImplicitParam(name="currPageIndex",value="当前页(1开始)",dataType="int",paramType="form"),
				@ApiImplicitParam(name="pageSize",value="每页行数",dataType="int",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form"),
				})
	public ResponseData<ArrayList<GoodsInfoList>> GetGoodsInfoListByPage(@RequestParam String entId,
			String goodCodeOrName,
			@RequestParam(required=false,defaultValue="1") int currPageIndex,
			@RequestParam(required=false,defaultValue="10") int pageSize,
			@RequestParam String code){
		return iOSPosService.GetGoodsInfoListByPage(entId,goodCodeOrName,currPageIndex,pageSize); 
	}
	
	/**
	 * 已销售分页查询; created by jie.jia at 2016-08-29 18:47
	 * @param storeId
	 * @param dateType
	 * @param currPageIndex
	 * @param pageSize
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/GetSaleListByPage",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="已销售分页查询", httpMethod ="POST", response=ResponseData.class, notes ="已销售分页查询")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="storeId",value="门店ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="dateType",value="1-本日;3-本月;其他-本周",dataType="String",paramType="form"),
				@ApiImplicitParam(name="currPageIndex",value="当前页(1开始)",dataType="int",paramType="form"),
				@ApiImplicitParam(name="pageSize",value="每页行数",dataType="int",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<List<WsSaleOrder>> GetSaleListByPage(@RequestParam String storeId, 
			@RequestParam String entId,
			@RequestParam(required=false,defaultValue="2") int dateType, 
			@RequestParam(required=false,defaultValue="1") int currPageIndex,
			@RequestParam(required=false,defaultValue="10") int pageSize,
			@RequestParam String code){
		
		return iOSPosSellService.GetSaleListByPage(storeId, entId, dateType, currPageIndex, pageSize);
	}
	
	/**
	 * 已销售订单详细
	 * @param saleId
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/GetSaleDetailInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="已销售订单详细", httpMethod ="POST", response=ResponseData.class, notes ="已销售订单详细")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="saleId",value="销售单ID",required=true,dataType="String",paramType="form"), 
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<WsSale> GetSaleDetailInfo(@RequestParam String saleId,@RequestParam String code){
		return iOSPosService.GetSaleDetailInfo(saleId);
	}
	
	@RequestMapping(value="/GetGoodsTypeList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="获取商品类型", httpMethod ="POST", response=ResponseData.class, 
		notes ="获取商品类型，typeId为空时，返回当前企业的所有一级分类，反之返回typeId对应的子类型")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="entId",value="企业ID",dataType="String",paramType="form"),
				@ApiImplicitParam(name="typeId",value="父分类ID",dataType="String",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<List<YzProductType>> GetGoodsTypeList(String entId,String typeId,@RequestParam String code){
		return iOSPosService.GetGoodsTypeList(entId,typeId);
	}
	
	@RequestMapping(value="/GetStoreSaleTop10",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="获取店铺销售额排行", httpMethod ="POST", response=ResponseData.class, 
		notes ="获取店铺销售额排行")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="dateType",value="（1-本日;2-本周;3-本月;）",dataType="int",paramType="form"),
				@ApiImplicitParam(name="currPageIndex",value="当前页(1开始)",dataType="int",paramType="form"),
				@ApiImplicitParam(name="pageSize",value="每页行数",dataType="int",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<List<YzStoreSale>> GetStoreSaleTop10(@RequestParam String entId,
			@RequestParam(required=false,defaultValue="2") int dateType,
			@RequestParam(required=false,defaultValue="1") int currPageIndex,
			@RequestParam(required=false,defaultValue="10") int pageSize,
			@RequestParam String code){
		return iOSPosService.GetStoreSaleTop10(entId,dateType,currPageIndex,pageSize);
	}
	
	
	@RequestMapping(value="/GetUnitList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="获取商品单位", httpMethod ="POST", response=ResponseData.class, 
		notes ="获取商品单位")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<List<YzUnit>> GetUnitList(@RequestParam String entId,@RequestParam String code){
		return iOSPosService.getUnitList(entId);
	}
}

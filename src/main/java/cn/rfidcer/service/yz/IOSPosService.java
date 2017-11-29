package cn.rfidcer.service.yz;

import java.util.ArrayList;
import java.util.List;

import cn.rfidcer.bean.yz.GoodsInfoList;
import cn.rfidcer.bean.yz.Province;
import cn.rfidcer.bean.yz.SaleTotal;
import cn.rfidcer.bean.yz.SaleTotalMoney;
import cn.rfidcer.bean.yz.SysUser;
import cn.rfidcer.bean.yz.WsSale;
import cn.rfidcer.bean.yz.WsSellStore;
import cn.rfidcer.bean.yz.YzProductType;
import cn.rfidcer.bean.yz.YzStoreSale;
import cn.rfidcer.bean.yz.YzUnit;
import cn.rfidcer.dto.ResponseData;

/**   
* @Title: IOSPosService.java 
* @Package cn.rfidcer.service.yz 
* @Description: service 羽众酒业-iOS 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月25日 下午7:36:30 
* @version V1.0   
*/
public interface IOSPosService {
	
	/**
	 * 获取所有省会; 
	 * @return
	 */
	ResponseData<ArrayList<Province>> getAllProvince(); 
	
	/**
	 * 依据省会ID来获取市的信息;
	 * @param provinceId
	 * @return
	 */
	ResponseData<ArrayList<Province>> getAllCity(String provinceId); 
	
	/**IOS登录
	 * @param sysUser
	 * @return
	 */
	ResponseData<SysUser> SysLogin(SysUser sysUser);
	
	/**
	 * 获取该企业的店铺;
	 * @param entId
	 * @return
	 */
	ResponseData<ArrayList<WsSellStore>> GetAllStoreList(String entId);
	
	/**
	 * 获取今日销售汇总(果园项目)
	 * @param entId
	 * @return
	 */
	ResponseData<SaleTotal> AppHome(String entId);
	
	/**
	 * 获取门店已销售总金额与总折扣金额
	 * @param storeID
	 * @param dateType
	 * @param code
	 * @return
	 */
	ResponseData<SaleTotalMoney> GetSaleTotalMoney(String storeID,String entId,int dateType);
	

	/**
	 * 商品根据条件分页查询(果园项目)
	 * @param enId
	 * @param goodCodeOrName
	 * @param currPageIndex
	 * @param pageSize
	 * @return
	 */
	ResponseData<ArrayList<GoodsInfoList>> GetGoodsInfoListByPage(String enId,String goodCodeOrName,int currPageIndex,int pageSize);

	/**
	 * 已销售订单详细; created by jie.jia at 2016-08-29 14:17
	 * @param saleId 销售单code
	 * @return
	 */
	ResponseData<WsSale> GetSaleDetailInfo(String saleId);
	
	/**获取商品类型
	 * @param entId 企业ID
	 * @param typeId 父类型ID
	 * @return
	 */
	ResponseData<List<YzProductType>> GetGoodsTypeList(String entId,String typeId);

	/**获取店铺排行
	 * @param entId 企业ID
	 * @param dateType 1-本日;2-本周;3-本月
	 * @param currPageIndex 当前页数
	 * @param pageSize 查询数目
	 * @return
	 */
	ResponseData<List<YzStoreSale>> GetStoreSaleTop10(String entId,
			int dateType, int currPageIndex, int pageSize);

	/**获取规格
	 * @param entId 企业ID
	 * @return
	 */
	ResponseData<List<YzUnit>> getUnitList(String entId);
}

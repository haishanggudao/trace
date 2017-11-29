package cn.rfidcer.dao.yz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.yz.SaleTotal;
import cn.rfidcer.bean.yz.SaleTotalMoney;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.yz.YzStoreSale;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;


/**   
 * @Title: StoreSaleItemMapper.java 
 * @Package cn.rfidcer.dao.yz 
 * @Description: 门店销售单Dao
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月8日 下午2:29:16 
 * @version V1.0   
*/
public interface StoreSaleOrderMapper extends Mapper<StoreSaleOrder>{
	
	/**
	 * 门店销售单查询
	 * @param page
	 * @param storeSaleOrder
	 * @return
	 */
	List<StoreSaleOrder> findAll(Page page,@Param("storeSaleOrder") StoreSaleOrder storeSaleOrder);
	
	/**
	 * 门店销售单查询
	 * @param page
	 * @param storeSaleOrder
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<StoreSaleOrder> findAllWithDataType(Page page,@Param("storeSaleOrder") StoreSaleOrder storeSaleOrder,@Param("startTime") String startTime,@Param("endTime") String endTime);
	

	/**
	 * 获取所有门店今日销售总金额
	 * @param storeID
	 * @param entId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	SaleTotal todaySaleTotalPrice(@Param("entId") String entId,@Param("startTime") String startTime,@Param("endTime") String endTime);
	
	
	/**
	 * 获取所有门店今日销售总单数
	 * @param storeID
	 * @param entId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	SaleTotal todaySaleOrderCount(@Param("entId") String entId,@Param("startTime") String startTime,@Param("endTime") String endTime);
	 
	
	
	/**
	 * 获取门店已销售总金额与总折扣金额
	 * @param storeID
	 * @param entId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	SaleTotalMoney getSaleTotalMoney(@Param("storeID") String storeID,@Param("entId") String entId,@Param("startTime") String startTime,@Param("endTime") String endTime);
	
	
	int getSaleTotalCount(@Param("storeID") String storeID,@Param("entId") String entId,@Param("startTime") String startTime,@Param("endTime") String endTime);
	
	/** 获取店铺排行
	 * @param entId 企业ID
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param page
	 * @return
	 */
	List<YzStoreSale> getStoreSaleTop10(@Param("entId") String entId,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("page") Page page);
	
	
	
	
}

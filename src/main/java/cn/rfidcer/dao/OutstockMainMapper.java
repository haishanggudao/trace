package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.interceptor.Page;

/**
 * DAO: the main out stock
 * @author jie.jia
 * updated at 2016-03-09 17:20
 */
public interface OutstockMainMapper {
    
	int deleteByPrimaryKey(String outstockMainId);
	int findCountByOutstockMainId(String outstockMainId);
	
	/**
	 * 新增出库主单信息; created by jie.jia at 2016-03-10 10:52
	 * @param record
	 * @return
	 */
	int insert(OutstockMain record);

	/**
	 * 新增出库主单信息; created by jie.jia at 2016-03-09 17:15
	 * @param record
	 * @return
	 */
	int insertSelective(OutstockMain record);

	/**
	 * 查询出库主单信息; updated by jie.jia at 2016-03-01 15:52
	 * @param outstockMainId 出库单ID
	 * @return
	 */
	OutstockMain selectByPrimaryKey(String outstockMainId);

	/**
	 * 修改出库主单信息; updated by jie.jia at 2016-03-10 10:19
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(OutstockMain record);

	int updateByPrimaryKey(OutstockMain record);

	List<OutstockMain> list(Page page);
	
	List<OutstockMain> findAllGroupDinnerOutstock(Page page,@Param("outstockMain") OutstockMain outstockMain);
	
	
	
	List<OutstockMain> list();
	
	/***
	 * 查询产品出库信息
	 * @param page
	 * @param companyId
	 * @return
	 */
	List<OutstockMain> findAllProductOutstock(Page page,@Param("outstockMain") OutstockMain  outstockMain);
	
	
	String findSaleOrderIdsBySaleOutstock(String outstockMainId);
	
	
	
	List<OutstockMain> findAllList(Page page,@Param("outstockMain") OutstockMain outstockMain);

	List<OutstockMain> listOutstockMainBySaleOrderId(@Param("saleorderid") String saleorderid)
	;
}
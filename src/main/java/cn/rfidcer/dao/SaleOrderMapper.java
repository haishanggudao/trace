package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
 
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.interceptor.Page;

/**
 * 销售单Data Access Object
 * @author jie.jia at 2016-01-06 11:46
 *
 */
public interface SaleOrderMapper {
	
	/**
	 * 删除销售单；created by jie.jia at 2016-01-11 09:52
	 * @param saleOrderId
	 * @return
	 */
	int deleteByPrimaryKey(String saleOrderId);



	/**
	 * 根据销售单条形码查询销售单信息
	 * @param barCode
	 * @return
	 */
	SaleOrder selectBybarCode(String barCode);
	
	
	int insert(SaleOrder record);

	/**
	 * 新增销售单；created by jie.jia at 2016-01-06 16:22
	 * @param record
	 * @return
	 */
	int insertSelective(SaleOrder record);
	
	/**
	 * 查询销售单记录；created by jie.jia at 2016-01-06 14:35
	 * @param page		分页条件
	 * @param saleOrder	查询条件
	 * @return
	 */
	List<SaleOrder> findAll(Page page,@Param("saleOrder") SaleOrder saleOrder);

	/**
	 * 查询销售主单信息; created by jie.jia at 2016-03-01 15:46
	 * @param saleOrderId
	 * @return
	 */
	SaleOrder selectByPrimaryKey(String saleOrderId);

	/**
	 * 依据销售单ID修改销售单信息; created by jie.jia at 2016-03-01 15:46 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(SaleOrder record);

	int updateByPrimaryKey(SaleOrder record);

	List<SaleOrder> list(Page page);

	List<SaleOrder> list();
	/***
	 * 根据订单状态查询销售单列表
	 * @param saleOrder
	 * @return
	 */
	List<SaleOrder> findByStatus(SaleOrder saleOrder);
	
}
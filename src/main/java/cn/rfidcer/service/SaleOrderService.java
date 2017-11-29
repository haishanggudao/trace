package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * 销售单信息Business Logic Layer
 * @author jie.jia at 2016-01-06 11:59
 *
 */
public interface SaleOrderService {

	List<SaleOrder> list(Page page);
	
	/**
	 * query all records of sale order; created by jie.jia at 2016-01-06 14:33
	 * @param page
	 * @param saleOrder
	 * @return
	 */
	List<SaleOrder> findAll(Page page,SaleOrder saleOrder);
	
	/**
	 * 通过采购单ID查询出库单的明细
	 * @param page
	 * @param saleItem
	 * @return
	 */
	List<SaleItem> findAllOutstockItems(Page page, SaleItem saleItem);
	
	
	/**
	 * 查询采购单明细
	 * @param page
	 * @param saleItem
	 * @return
	 */
	List<SaleItem> findAllItems(Page page, SaleItem saleItem);
	
	List<SaleItem> getSaleItems(String saleOrderId);
	
	/***
	 * 根据订单状态查询销售单列表
	 * @param saleOrder
	 * @return
	 */
	List<SaleOrder> findByStatus(SaleOrder saleOrder);
	

	
	/**
	 * 新增或编辑销售单；created by jie.jia at 2016-01-06 16:19
	 * @param saleOrder
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateSaleOrder(SaleOrder saleOrder, User user);
	
	
	
	/**
	 * 新增或编辑销售单；
	 * @param saleOrder
	 * @param user
	 * @param saleItemList
	 * @return
	 */
	ResultMsg addOrUpdate(SaleOrder saleOrder, User user,String saleItemList);
	
	
	
	/**
	 * 删除销售单；created by jie.jia at 2016-01-11 09:56
	 * @param saleOrder
	 * @return
	 */
	ResultMsg delSaleOrder(SaleOrder saleOrder);

	
	/**
	 * 删除销售单明细
	 * @param saleOrder
	 * @return
	 */
	ResultMsg delSaleOrderItems(SaleItem items);

	
	SaleOrder getSaleOrder(String saleOrderId);

	/**生成销售单号
	 * @return
	 */
	String getSaleOrderNo();
	
	
	
	
}
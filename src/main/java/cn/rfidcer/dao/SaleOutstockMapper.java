package cn.rfidcer.dao;

import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.SaleOutstock;

public interface SaleOutstockMapper {

	/**
	 * 新增销售出库关联信息
	 * @param outstockMain
	 * @return
	 */
	int insertSelective(OutstockMain outstockMain);
	
	
	/**
	 * 根据销售单号查询关联出库单号
	 * @param saleorder
	 * @return
	 */
	SaleOutstock selectById(SaleOrder saleorder);
	
	
}
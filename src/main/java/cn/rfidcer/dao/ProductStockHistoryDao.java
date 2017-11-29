package cn.rfidcer.dao;

import cn.rfidcer.bean.ProductStockHistory;

public interface ProductStockHistoryDao {

	/**新增产品库存操作
	 * @param productStockHistory
	 * @return
	 */
	int addProductStockHistory(ProductStockHistory productStockHistory);
}

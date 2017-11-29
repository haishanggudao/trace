package cn.rfidcer.dao;

import cn.rfidcer.bean.GoodsStockHistory;

public interface GoodsStockHistoryDao {

	/**新增商品库存操作
	 * @param goodsStockHistory
	 * @return
	 */
	int addGoodsStockHistory(GoodsStockHistory goodsStockHistory);
}

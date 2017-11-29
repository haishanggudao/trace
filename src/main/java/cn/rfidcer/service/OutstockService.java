package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.OutstockCountInfo;
import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface OutstockService {

	List<OutstockItem> listOutstockItem(Page page);

	ResultMsg deleteOutstockItemByKey(String id);

	List<OutstockMain> listOutstockMain(Page page);

	ResultMsg deleteOutstockMainByKey(String id);

	List<OutstockItem> listOutstockItemWithOutstockMainId(Page page, String outstockMainId);

	List<OutstockMain> findAllList(Page page, OutstockMain outstockMain);
	/**
	 * 查询产品出库信息
	 * @param page
	 * @param companyId
	 * @return
	 */
	List<OutstockMain> findAllProductOutstock(Page page, OutstockMain outstockMain);
	
	/**
	 * 查询产品出库对应的销售单
	 * @param page
	 * @param companyId
	 * @return
	 */
	 String findSaleOrderIdsBySaleOutstock(String outstockMainId);
	 
	
	
	
	List<OutstockMain> listOutstockMainBySaleOrderId(String id);

	List<OutstockCountInfo> getClintCountInfo(String outstockMainId);

	/**
	 * @param page
	 * @param outstockMainId
	 * @return
	 */
	List<OutstockItem> findAllItemsList(Page page, String outstockMainId);

	/**
	 * 新增或编辑出库单信息
	 * @param outstockmain
	 * @return
	 */
	ResultMsg saveGoodsOutStock(OutstockMain outstockmain, User user,String outstockItems);
	/**
	 * 新增或编辑出库单信息
	 * @param outstockmain
	 * @return
	 */
	ResultMsg saveProductOutStock(OutstockMain outstockmain, User user,String outstockItems);

	/**
	 * 产品出库明细
	 * @param page
	 * @param outstockMainId
	 * @return
	 */
	List<OutstockItem> findAllProductItems(Page page,String outstockMainId);
}

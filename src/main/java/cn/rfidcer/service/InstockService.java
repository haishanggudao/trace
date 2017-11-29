package cn.rfidcer.service;

import java.util.List;
import java.util.Map;

import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.InstockManagerItem;
import cn.rfidcer.bean.PurchaseInstockAssoc;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface InstockService {

	List<InstockItem> listInstockItem(Page page);


	List<InstockMain> listInstockMain(Page page);

	ResultMsg deleteInstockMainByKey(String id);

	List<InstockItem> listInstockItem(String instockMainId);

	void adddOrUpdate(InstockMain instockmain, String purchaseids);

	List<PurchaseInstockAssoc> listPurchaseOrderAssocByInstockMainId(String instockMainId);

	List<InstockMain> listQCompanyId(Page page, String companyId);
	
	List<InstockMain> findAllList(Page page, InstockMain instockMain);
	/**
	 * @param instockmain
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdate(InstockMain instockmain, User user);
	
	/**新增或修改入库单并更新库存
	 * @param instockmain
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdateAndUpdateStock(InstockMain instockmain, User user);
	
	List<InstockManagerItem> findAllList(Page page,InstockManagerItem instockManagerItem);


	/**
	 * @param companyId
	 * @return
	 */
	List<Map<String, String>> getBactchNum(String companyId);
}
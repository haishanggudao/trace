package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.InstockPurchaseItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.interceptor.Page;

public interface InstockItemService {
	
	List<InstockPurchaseItem> findAllListByParamAnd(Page page, InstockItem instockItem);

	/**
	 * @param id
	 * @return
	 */
	ResultMsg deleteByKey(String instockItemId);
	
}
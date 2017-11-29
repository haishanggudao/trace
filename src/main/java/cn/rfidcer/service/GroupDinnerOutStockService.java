package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface GroupDinnerOutStockService {

	/**
	 * 新增或编辑出库单信息
	 * @param outstockmain
	 * @return
	 */
	ResultMsg addOrUpdate(OutstockMain outstockMain,String outstockDetail, User user);
	
	
	/**
	 * 根据公司ID查询团餐
	 * @param outstockMain
	 * @param outstockDetail
	 * @param user
	 * @return
	 */
	List<OutstockMain> findAllGroupDinnerOutstock(Page page,OutstockMain outstockMain);
	
	/**
	 * 删除销售单和出库单
	 * @param outstockMain
	 * @return
	 */
	ResultMsg delete(OutstockMain outstockMain);
	
}

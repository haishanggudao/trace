package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.StoreRegister;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface StoreRegisterService extends BaseService<StoreRegister>{

	ResultMsg addOrUpdateStoreRegister(User user,StoreRegister storeRegister);

	List<StoreRegister> findAllList(Page page,StoreRegister storeRegister);

	String findLocationByCompanyId(StoreRegister storeRegister);

	/**获取company code
	 * @return
	 */
	String getLicense(String companyId);

	/**
	 * @return
	 */
	ResultMsg deleteStoreRegister(StoreRegister storeRegister);

	
	
}

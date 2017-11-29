package cn.rfidcer.service;

import java.util.List;


import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.StoreAccount;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface StoreAccountService extends BaseService<StoreAccount>{

	ResultMsg addOrUpdateStoreAccount(User user,StoreAccount storeAccount);

    ResultMsg deleteStoreAccount(StoreAccount storeAccount);
	List<StoreAccount> findAllList(Page page,StoreAccount storeAccount);
}

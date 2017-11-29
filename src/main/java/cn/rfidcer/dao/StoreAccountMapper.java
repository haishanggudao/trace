package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.StoreAccount;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

public interface StoreAccountMapper extends Mapper<StoreAccount>{

	List<StoreAccount> findAllList(Page page, @Param("storeAccount")StoreAccount storeAccount);
	
	List<StoreAccount> findAccountByCompanyId(StoreAccount storeAccount);
    
}

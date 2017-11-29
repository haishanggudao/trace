package cn.rfidcer.dao;

import java.util.List;


import cn.rfidcer.bean.StoreRegister;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

public interface StoreRegisterMapper extends Mapper<StoreRegister>{

	List<StoreRegister> findAllList(Page page, StoreRegister storeRegister);

	String findLocationByCompanyId(StoreRegister storeRegister);
	
	String insertBySubLocation(StoreRegister storeRegister);

	/**
	 * @param companyId
	 */
	String findCompanyCode(String companyId);

	/**
	 * @return
	 */
	String addLocation();
	
}

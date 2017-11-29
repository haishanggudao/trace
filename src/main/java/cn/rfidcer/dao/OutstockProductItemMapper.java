package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.interceptor.Page;

public interface OutstockProductItemMapper {

	int deleteByPrimaryKey(String outstockItemId);

	int deleteByOutstockMainId(String outstockMainId);

	int findCountByOutstockMainId(String outstockMainId);
	
	int insertSelective(OutstockItem record);

	List<OutstockItem> findAllList(Page page, @Param("outstockmainid") String outstockmainid);
	
}
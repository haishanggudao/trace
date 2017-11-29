package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.OutstockLog;

public interface OutstockLogMapper {

	int deleteByPrimaryKey(String id);
	int insert(OutstockLog record);
	int insertSelective(OutstockLog record);
	OutstockLog selectByPrimaryKey(String id);
	int updateByPrimaryKeySelective(OutstockLog record);
	int updateByPrimaryKey(OutstockLog record);
	List<OutstockLog> selectByBean(@Param("outstocklog") OutstockLog outstocklog);
}
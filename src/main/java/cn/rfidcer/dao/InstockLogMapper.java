package cn.rfidcer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.InstockLog;

public interface InstockLogMapper {
	int deleteByPrimaryKey(String id);
	int insert(InstockLog record);
	int insertSelective(InstockLog record);
	InstockLog selectByPrimaryKey(String id);
	int updateByPrimaryKeySelective(InstockLog record);
	int updateByPrimaryKey(InstockLog record);
	List<InstockLog> selectByBean(@Param("instocklog") InstockLog instockLog);
	List<Map<String, String>> getGoodsByYearMonth(@Param("yearmonth")String yearmonth,@Param("companyid")String companyid);
	List<Map<String, String>> getYearMonth(@Param("companyid")String companyid);
	List<Map<String, Object>> getInstockPriceReport(@Param("month")String month,@Param("goods")String goods,@Param("companyid")String companyid);
	List<Map<String, Object>> getOutstockPriceReport(@Param("month")String month,@Param("goods")String goods,@Param("companyid")String companyid);
	List<Map<String, Object>> getPriceReport(@Param("yearmonth")String yearmonth,@Param("companyid")String companyid);
}
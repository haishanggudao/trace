package cn.rfidcer.dao;

import cn.rfidcer.bean.TraceLog;

public interface TraceLogMapper {
    int deleteByPrimaryKey(String traceLogId);
    int insert(TraceLog record);
    int insertSelective(TraceLog record);
    TraceLog selectByPrimaryKey(String traceLogId);
    int updateByPrimaryKeySelective(TraceLog record);
    int updateByPrimaryKey(TraceLog record);
}
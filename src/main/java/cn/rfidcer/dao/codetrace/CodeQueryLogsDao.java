package cn.rfidcer.dao.codetrace;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.codetrace.CodeQueryLogs;
import cn.rfidcer.interceptor.Page;

public interface CodeQueryLogsDao {
    List<CodeQueryLogs> countTotalTimes(Page page, @Param("codequerylogs")CodeQueryLogs codequerylogs);
    
    List<CodeQueryLogs> selectDetails(Page page, @Param("codequerylogs")CodeQueryLogs codequerylogs);
}

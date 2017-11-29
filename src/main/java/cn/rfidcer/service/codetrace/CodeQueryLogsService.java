package cn.rfidcer.service.codetrace;

import java.util.List;

import cn.rfidcer.bean.codetrace.CodeQueryLogs;
import cn.rfidcer.interceptor.Page;

public interface CodeQueryLogsService {
    List<CodeQueryLogs> findAllTotalTimes(Page page, CodeQueryLogs codequerylogs);
    
    List<CodeQueryLogs> findAllDetails(Page page, CodeQueryLogs codequerylogs);
}

package cn.rfidcer.service.impl.codetrace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.codetrace.CodeQueryLogs;
import cn.rfidcer.dao.codetrace.CodeQueryLogsDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.codetrace.CodeQueryLogsService;

@Service
public class CodeQueryLogsServiceImpl implements CodeQueryLogsService {

	@Autowired
    private CodeQueryLogsDao codequerylogsDao;
	
	@Override
	public List<CodeQueryLogs> findAllTotalTimes(Page page, CodeQueryLogs codequerylogs) {
		// TODO Auto-generated method stub
		List<CodeQueryLogs> items = null;
		items = codequerylogsDao.countTotalTimes(page, codequerylogs);
		return items;
	}

	@Override
	public List<CodeQueryLogs> findAllDetails(Page page, CodeQueryLogs codequerylogs) {
		// TODO Auto-generated method stub
		List<CodeQueryLogs> items = null;
		items = codequerylogsDao.selectDetails(page, codequerylogs);
		return items;
	}
}

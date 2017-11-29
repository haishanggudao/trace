package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ProcessMain;
import cn.rfidcer.interceptor.Page;

public interface ProcessMainMapper {
	
	/**
	 * 删除加工主单记录；created by jie.jia at 2015-12-31 09:33
	 * @param processMainId 加工主单ID
	 * @return
	 */
    int deleteByPrimaryKey(String processMainId);

    int insert(ProcessMain record);

    /**
     * 新增加工记录；created by jie.jia at 2016-01-05 10:49
     * @param record
     * @return
     */
    int insertSelective(ProcessMain record);
    
    /**
     * 查询加工记录；created by jie.jia at 2015-12-29 17:10
     * @param page	分页
     * @param processMain	查询条件
     * @return
     */
    List<ProcessMain> getProcessList(Page page, @Param("processMain") ProcessMain processMain);

    ProcessMain selectByPrimaryKey(String processMainId);

    int updateByPrimaryKeySelective(ProcessMain record);

    int updateByPrimaryKey(ProcessMain record);
}
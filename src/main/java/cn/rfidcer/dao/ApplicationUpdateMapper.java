package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ApplicationUpdate;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

public interface ApplicationUpdateMapper extends Mapper<ApplicationUpdate>{

	List<ApplicationUpdate> findAllList(Page page,@Param("applicationUpdate") ApplicationUpdate applicationUpdate);

	ApplicationUpdate getLastVersion();

}

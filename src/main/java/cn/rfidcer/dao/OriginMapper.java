package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Origin;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

public interface OriginMapper extends Mapper<Origin>{

	List<Origin> findAllList(Page page, @Param("origin") Origin origin);

}

package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ClientUserResource;
import cn.rfidcer.bean.TestBean;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TestMapper extends Mapper<TestBean>,  MySqlMapper<TestBean> {
	List<ClientUserResource> findAll(Page page,@Param("goodsQC") ClientUserResource goodsQC);
}
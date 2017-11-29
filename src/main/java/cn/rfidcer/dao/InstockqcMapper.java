package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Instockqc;
import cn.rfidcer.interceptor.Page;

public interface InstockqcMapper {
    int deleteByPrimaryKey(String id);
    int insert(Instockqc record);
    int insertSelective(Instockqc record);
    Instockqc selectByPrimaryKey(String id);
    int updateByPrimaryKeySelective(Instockqc record);
    int updateByPrimaryKey(Instockqc record);
	List<Instockqc> selectByBean(Page page,@Param("instockqc")Instockqc instockqc);
	Instockqc getQCReportByGoodsIdAndInstockMainId(Instockqc instockqc);
}
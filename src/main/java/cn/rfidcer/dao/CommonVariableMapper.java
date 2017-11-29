package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import cn.rfidcer.bean.CommonVariable;

public interface CommonVariableMapper extends Mapper<CommonVariable>{
	
	CommonVariable selectByVarGroup(String deliverType);

	List<CommonVariable> selectByCVV(@Param("companyid")String companyid,@Param("vargroup")String vargroup,@Param("varname")String varname);

	List<CommonVariable> getCommonVariables(CommonVariable commonVariable);

	/**
	 * @param cv
	 * @return
	 */
	List<CommonVariable> selectByBean(@Param("cv")CommonVariable cv);
	
	/**更新自增ID，最大值大于varValue后归1,即最大值为varValue+1
	 * @param commonVariable
	 * @return
	 */
	int updateVarvalueIncreament(CommonVariable commonVariable);
}
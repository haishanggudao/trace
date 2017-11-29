package cn.rfidcer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.interceptor.Page;

public interface InstockMainMapper {

	int deleteByPrimaryKey(String instockMainId);

	int deleteInstockMainByPurchaseOrderId(String purchaseOrderId);

	int insert(InstockMain record);
	
	//查询重复数据
	int findRepeat(InstockMain record);
	
	int insertSelective(InstockMain record);

	InstockMain selectByPrimaryKey(String instockMainId);

	InstockMain selectByPurchaseOrderId(String purchaseOrderId);

	int updateByPrimaryKeySelective(InstockMain record);

	int updateByPrimaryKey(InstockMain record);

	List<InstockMain> list(Page page);

	List<InstockMain> list();

	List<InstockMain> listQCompanyId(Page page, @Param("companyId") String companyId);

	List<InstockMain> findAllList(Page page, @Param("instockMain") InstockMain instockMain);

	/**
	 * @param companyId
	 * @return
	 */
	List<Map<String, String>> getBactchNum(@Param("companyid")String companyId);

}
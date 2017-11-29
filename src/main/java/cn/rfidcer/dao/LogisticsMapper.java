package cn.rfidcer.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.rfidcer.bean.Logistics;
import cn.rfidcer.interceptor.Page;

/**
 * DAO for logistics
 * @author jie.jia
 * created by 2016-03-08 11:37
 */
public interface LogisticsMapper {
	
	/**
	 * 删除物流企业信息; created by jie.jia at 2016-03-09 10:54
	 * @param logisticsId
	 * @return
	 */
	int deleteByPrimaryKey(String logisticsId);
	/**
	 * 删除物流企业信息修改状态; created by jie.jia at 2016-03-09 10:54
	 * @param logisticsId
	 * @return
	 */
	int deleteWithStatusByPrimaryKey(String logisticsId);
    
	
    int insert(Logistics record);

    /**
     * 新增物流企业信息; created by jie.jia at 2016-03-08 16:21
     * @param record
     * @return
     */
    int insertSelective(Logistics record);
    
    /**
     * 查询所有的物流企业信息; created by jie.jia at 2016-03-08 15:00
     * @param page
     * @return
     */
    List<Logistics> findAll(Page page,@Param("logistics") Logistics logistics);
    
    /**
     * 依据用户所属企业ID来查询物流企业信息; created by jie.jia at 2016-03-09 15:06
     * @param companyId
     * @return
     */
    List<Logistics> findByUserCompanyId(String companyId);
    
    /**
     * 修改物流企业信息; created by jie.jia at 2016-03-09 09:20
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Logistics record);
    
	List<Logistics> getLogisticsCompanys(String companyId);
	
	/**根据物流企业ID和CompanyId检查物流企业是否存在
	 * @param logistics
	 * @return
	 */
	int checkLogisticsExistsByLogisticsId(Logistics logistics);
	
	/**检查物流企业是否存在
	 * @param logistics
	 * @return
	 */
	int checkCountByLogisticsId(@Param("logisticsId") String logisticsId);

	/**根据别名查询物流企业
	 * @param logistics
	 * @return
	 */
	int checkLogisticsExistsByLogisticsName(Logistics logistics);
}
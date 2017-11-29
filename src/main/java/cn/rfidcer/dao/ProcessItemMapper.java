package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ProcessItem;
import cn.rfidcer.interceptor.Page;

/**
 * 加工明细原料的Data Access Object
 * @author jie.jia；created by jie.jia at 2015-12-30 16:29
 *
 */
public interface ProcessItemMapper {

    int deleteByPrimaryKey(String processItemId);
    
    /**
     * 删除加工明细-原料；created by jie.jia at 2015-12-30 17:33
     * @param processMainId 加工记录主单ID
     * @return
     */
    int deleteByMainid(String processMainId);
    
    /**
     * 查询加工明细原料；created by jie.jia at 2015-12-30 16:27
     * @param processMainId 加工记录主单ID
     * @return
     */
    List<ProcessItem> findProcessItemsByMainid(Page page, @Param("processMainId") String processMainId);

    int insert(ProcessItem record);

    int insertSelective(ProcessItem record);

    ProcessItem selectByPrimaryKey(String processItemId);

    int updateByPrimaryKeySelective(ProcessItem record);

    int updateByPrimaryKey(ProcessItem record);

	List<ProcessItem> findSimilarProcessItems(String productId);
	
	/**检查该商品是否已被拆分过
	 * @param goodsId
	 * @return
	 */
	int checkCountByGoodsId(ProcessItem processItem);
}
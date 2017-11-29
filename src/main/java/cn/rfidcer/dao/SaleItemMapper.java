package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.interceptor.Page;

/**
 * 销售单明细Data Access Object
 * @author jie.jia at 2016-01-06 11:45 
 *
 */
public interface SaleItemMapper {
	
	/**
	 * 删除销售单明细；created by jie.jia at 2016-01-08 17:19
	 * @param saleMainId
	 * @return
	 */
	int deleteByMainid(String saleMainId);
	
    int deleteByPrimaryKey(String saleItemId);
    
    /**
     * 查询销售单明细；created by jie.jia at 2016-01-07 17:30
     * @param page
     * @param saleItem
     * @return
     */
    List<SaleItem> findAll(Page page,@Param("saleItem") SaleItem saleItem);

	  /**
     * 根据ids查询销售单明细
     * @param page
     * @param ids
     * @return
     */
    List<SaleItem> findAllGroupByProductId(Page page,@Param("ids") String[] ids);
  
    

    int insert(SaleItem record);

    /**
     * 新增销售单明细；created by jie.jia 2016-01-07 16:22
     * @param record
     * @return
     */
    int insertSelective(SaleItem record);

    SaleItem selectByPrimaryKey(String saleItemId);

    int updateByPrimaryKeySelective(SaleItem record);
    /**
     * 根据销售单号修改明细状态
     * @param saleItem
     * @return
     */
    int updateStatusBySaleOrderId(SaleItem saleItem);
    
    int updateByPrimaryKey(SaleItem record);

	List<SaleItem> listSaleItems(@Param("saleorderid")String saleorderid);
}
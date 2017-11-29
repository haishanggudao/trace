package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.PackageGoodsItem;
import cn.rfidcer.interceptor.Page;

/**
 * 包装商品明细Data Access Object
 * @author jie.jia
 *
 */
public interface PackageGoodsItemMapper {
	
    int deleteByPrimaryKey(String packageGoodsItemId);
    
    /**
     * delete the items by main ID; created by jie.jia at 2016-01-12 16:28
     * @param packageMainId
     * @return
     */
    int deletePackageGoodsItemsByPackageMainId(String packageMainId);
    
    /**
     * find all items of main package; created by jie.jia at 2016-01-12 14:50
     * @param page
     * @param packageGoodsItem
     * @return
     */
    List<PackageGoodsItem> findAll(Page page,@Param("packageGoodsItem") PackageGoodsItem packageGoodsItem);

    int insert(PackageGoodsItem record);

    /**
     * insert a new goodsItems of main package; created by jie.jia at 2016-01-12 14:10
     * @param record
     * @return
     */
    int insertSelective(PackageGoodsItem record);

    PackageGoodsItem selectByPrimaryKey(String packageGoodsItemId);

    int updateByPrimaryKeySelective(PackageGoodsItem record);

    int updateByPrimaryKey(PackageGoodsItem record);
}
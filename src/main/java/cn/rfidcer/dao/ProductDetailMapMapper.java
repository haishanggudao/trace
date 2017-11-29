package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
 
import cn.rfidcer.bean.ProductDetailMap;
import cn.rfidcer.interceptor.Page;

/**
 * 产品属性映射DAO；
 * @author jie.jia
 *
 */
public interface ProductDetailMapMapper {
	
    int deleteByPrimaryKey(String productDetailMapId);
    
    /**
     * 获取产品属性映射列表；created by jie.jia at 2015-12-24 11:15
     * @param page
     * @param productDetailMap
     * @return
     */
    List<ProductDetailMap> getProductDetailMapList(Page page,@Param("productDetailMap") ProductDetailMap productDetailMap);

    /**
     * 新增产品附加属性；created by jie.jia at 2015-12-24 15:11
     * @param record
     * @return
     */
    int insert(ProductDetailMap record);

    int insertSelective(ProductDetailMap record);

    ProductDetailMap selectByPrimaryKey(String productDetailMapId);

    int updateByPrimaryKeySelective(ProductDetailMap record);

    /**
     * 修改产品附加属性；created by jie.jia at 2015-12-25 09:49
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductDetailMap record);
}
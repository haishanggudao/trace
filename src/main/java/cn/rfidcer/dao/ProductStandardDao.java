package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.interceptor.Page;

/**产品规格数据库层
 * @author xzm
 *
 */
public interface ProductStandardDao {

	/** 查询产品规格
	 * @param page 分页对象
	 * @param productStandard 查询条件
	 * @return
	 */
	List<ProductStandard> findAll(Page page,@Param("standard") ProductStandard productStandard);
	
	/**新增产品规格
	 * @param productStandard
	 * @return
	 */
	int createProductStandard(ProductStandard productStandard);
	
	/**删除产品规格
	 * @param productStandard
	 * @return
	 */
	int delProductStandard(ProductStandard productStandard);
	
	/**修改产品规格
	 * @param productStandard
	 * @return
	 */
	int updateProductStandard(ProductStandard productStandard);
	
	/**根据产品规格名称和分类ID查找规格是否已存在
	 * @param productStandard
	 * @return
	 */
	ProductStandard checkProductStandardExists(ProductStandard productStandard);
	
	/**
	 * 查询规格数量
	 * @param productStandard
	 * @return
	 */
	int getProductStandardCount(ProductStandard productStandard);
	
	
	int insertSelective(ProductStandard ps);
	
}

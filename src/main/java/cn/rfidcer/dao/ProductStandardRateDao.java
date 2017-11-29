package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.ProductStandardRate;
import cn.rfidcer.interceptor.Page;

/**产品规格转化数据库层
 * @author xzm
 *
 */
public interface ProductStandardRateDao {

	/** 查询产品规格转化
	 * @param page 分页对象
	 * @param rate 查询条件
	 * @return
	 */
	List<ProductStandardRate> findAll(Page page,@Param("rate") ProductStandardRate rate);
	
	/**新增产品规格转化
	 * @param rate
	 * @return
	 */
	int createProductStandardRate(ProductStandardRate rate);
	
	/**删除产品规格转化
	 * @param rate
	 * @return
	 */
	int delProductStandardRate(ProductStandardRate rate);
	
	/**修改产品规格转化
	 * @param rate
	 * @return
	 */
	int updateProductStandardRate(ProductStandardRate rate);
	
}

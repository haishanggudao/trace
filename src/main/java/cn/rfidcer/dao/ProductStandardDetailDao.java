package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.GoodsPrice;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.interceptor.Page;
/**   
 * @Title: ProductStandardDetailDao.java 
 * @Package cn.rfidcer.dao 
 * @Description: 产品规格明细数据库层
 * @author xzm 
 * @Copyright Copyright
 * @date 2016年7月7日 下午3:48:06 
 * @version V1.0   
*/
public interface ProductStandardDetailDao {

	/** 查询产品规格明细
	 * @param page 分页对象
	 * @param standardDetail 查询条件
	 * @return
	 */
	List<ProductStandardDetail> findAll(Page page,@Param("standardDetail") ProductStandardDetail standardDetail);
	/** 查询没有商品价格规格明细
	 * @param standardDetail 查询条件
	 * @return
	 */
	List<ProductStandardDetail> findNoPriceList(ProductStandardDetail standardDetail);
	ProductStandardDetail findPriceById(ProductStandardDetail standardDetail);
	
	List<GoodsPrice> findProductIdList(Page page,@Param("standardDetail") ProductStandardDetail standardDetail);
	List<GoodsPrice> findProductStandardIdList(ProductStandardDetail standardDetail);
	
	/**新增产品规格明细
	 * @param standardDetail
	 * @return
	 */
	int createProductStandardDetail(ProductStandardDetail standardDetail);
	
	/**
	 * 新增产品规格明细
	 * @param std
	 */
	int insertSelective(ProductStandardDetail standardDetail);
	
	/**删除产品规格明细
	 * @param productStandard
	 * @return
	 */
	int delProductStandardDetail(ProductStandardDetail standardDetail);
	
	/**删除产品规格明细
	 * @param productStandard
	 * @return
	 */
	int delProductStandardDetailByProductStandardId(String standardId);
	
	
	/**修改产品规格明细
	 * @param standardDetail
	 * @return
	 */
	int updateProductStandardDetail(ProductStandardDetail standardDetail);
	
	
	/**
	 * 根据产品ID和规格名称查询规格明细
	 * @param productId
	 * @param productStandardName
	 * @return
	 */
	ProductStandardDetail findIdByStandardNameAndProductId(@Param("productId") String productId,@Param("productStandardName") String productStandardName);
	
	
	/**
	 * 根据产品ID和规格名称查询规格明细
	 * @param productId
	 * @param productStandardName
	 * @return
	 */
	ProductStandardDetail findByStandardDetailId(@Param("productStandardDetailId") String productStandardDetailId);
	
	
	
	
	
	
	/**根据产品ID、规格ID、规格数量查找规格明细
	 * @param standardDetail
	 * @return
	 */
	int findProductStandardDetailByUnique(ProductStandardDetail standardDetail);

	int findProductStandardDetailById(ProductStandardDetail standardDetail);
	
	
	
	
	
}

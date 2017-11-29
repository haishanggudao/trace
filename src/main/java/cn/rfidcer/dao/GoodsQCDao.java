package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.GoodsQC;
import cn.rfidcer.interceptor.Page;

/**商品质检信息数据库层
 * @author xzm
 *
 */
public interface GoodsQCDao {

	/** 查询商品质检信息
	 * @param page 分页对象
	 * @param goodsQC 查询条件
	 * @return
	 */
	List<GoodsQC> findAll(Page page,@Param("goodsQC") GoodsQC goodsQC);
	
	/**新增商品质检信息
	 * @param goodsQC
	 * @return
	 */
	int createGoodsQC(GoodsQC goodsQC);
	
	/**删除商品质检信息
	 * @param goodsQC
	 * @return
	 */
	int delGoodsQC(GoodsQC goodsQC);
	
	/**修改商品质检信息
	 * @param goodsQC
	 * @return
	 */
	int updateGoodsQC(GoodsQC goodsQC);
	
	/**根据商品ID检查对应质检信息是否存在
	 * @param goodsQC
	 * @return
	 */
	int checkGoodsQCExists(GoodsQC goodsQC);
	
}

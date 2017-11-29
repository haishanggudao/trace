package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Goods; 
import cn.rfidcer.interceptor.Page;

/**
 * 商品及商品分类的数据库接口
 * @author jie.jia at 2015-12-28 11:10 
 */
public interface GoodsDao { 
	
	/**查找商品名称是否存在
	 * @param goods
	 * @return
	 */
	int checkGoodsNameExists(Goods goods);
	
	/**新增商品
	 * @param goods
	 * @return
	 * updated by jie.jia at 2015-12-28 17:19
	 */
	int createGoods(Goods goods);
	
	/**
	 * 新增商品信息；created by jie.jia at 2016-01-05 11:01
	 * @param goods
	 * @return
	 */
	int insertSelective(Goods goods);
	
	/**删除商品
	 * @param goods
	 * @return
	 */
	int delGoods(Goods goods);
	
	/**
	 * 统计商品的记录数；created by jie.jia at 2016-01-04 17:21
	 * @param goods	查询条件=》商品信息
	 * @return
	 */
	int findCountGoods(@Param("goods") Goods goods);
	
	/**
	 * 查找商品信息；created by jie.jia at 2016-01-05 11:22
	 * @param productId	产品ID
	 * @param productStandardDetailId	规格明细ID
	 * @param goodsBatch	商品批次
	 * @return
	 */
	Goods findByProductidStandarddetailidBatch(String productId, String productStandardDetailId, String goodsBatch);
	
	
	/**
	 * 获取商品列表; created by jie.jia at 2015-12-28 11:11
	 * @param page
	 * @param goods
	 * @return
	 */
	List<Goods> getGoodsList(Page page,@Param("goods") Goods goods,@Param("producttype")String producttype);
	List<Goods> getGoodsList(@Param("goods") Goods goods);
	 	
	/**修改商品
	 * @param goods
	 * @return
	 * updated by jie.jia at 2015-12-28 17:16
	 */
	int updateGoods(Goods goods);  
	
	List<Goods> list(Page page);

	List<Goods> list();

	/**查找一个企业下是否已有同一商品
	 * @param goods 产品ID、规格明细ID、商品批次相同为同一商品
	 * @return
	 */
	int findByProductidStandarddetailidBatchCompany(Goods goods);

	Goods getByGoodsId(@Param("goodsId")String goodsId);

	int updateGoodsUsable(@Param("goodsid")String goodsid);
	
	/**
	 * 依据productStandardDetailId 来查询商品信息列表; updated by jie.jia at 2016-10-27 10:28
	 * @param standardDetailId
	 * @return
	 */
	List<Goods> findGoodsByStandardDetailId(String standardDetailId);
	
	/**
	 * 获取商品名列表
	 * @param companyId
	 * @return
	 */
	List<Goods> findAllGoodsName(@Param("companyId") String companyId);
	/**根据追溯码查找商品信息
	 * @param code
	 * @return
	 */
	Goods findGoodsByCode(String code);
	
	/**查询创建时间最早的商品原料
	 * @param goods
	 * @return
	 */
	Goods findMinCreateTimeGoodsByDetailId(String standardDetailId);

	List<Goods> findGoodsByStandardDetailIdLimit(@Param("standardDetailId")String standardDetailId,@Param("limit") int limit);
}

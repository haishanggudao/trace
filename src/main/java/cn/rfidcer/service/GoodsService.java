package cn.rfidcer.service;

import java.util.List; 

import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.ResultMsg; 
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * 商品 Business Logic Layer
 * @author jie.jia 
 *
 */
public interface GoodsService { 
	
	/**
	 * 新增或修改商品
	 * @param goods
	 * @param user
	 * @return
	 * updated by jie.jia at 2015-12-28 17:23
	 */
	ResultMsg createOrUpdateGoods(Goods goods, User user);
	
	/**获取商品列表
	 * @param page
	 * @return
	 */
	List<Goods> getGoodsList(Page page, Goods goods, String producttype);

	/**删除商品
	 * @param goods
	 * @return
	 * updated by jie.jia at 2015-12-28 17:32
	 */
	ResultMsg delGoods(Goods goods);

	List<Goods> list(Page page);

	Goods getByKey(String goodsId);

	List<Goods> getByProductId(String productId);

	ResultMsg updateGoodsUsable(Goods goods);
	
	List<Goods> findGoodsByStandardDetailId(Goods goods);


	/**
	 * @param companyId
	 * @return
	 */
	List<Goods> findAllGoodsName(String companyId);
	
	/**获取商品批次号
	 * @return
	 */
	String getGoodsBatchNo();

	List<Goods> findGoodsByStandardDetailIdLimit(Goods goods, int limit);
}

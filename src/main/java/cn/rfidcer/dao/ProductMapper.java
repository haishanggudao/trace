package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Product;
import cn.rfidcer.interceptor.Page;

public interface ProductMapper {
	
	/**
	 * 检查产品名称是否存在？ created by jie.jia at 2015-12-23 13:23
	 * @param product
	 * @return
	 */
	int checkProductNameExists(Product product);
    
	/**
	 * 检查产品ID是否存在？ 
	 * @param product
	 * @return
	 */
	
	int checkProductExists(Product product);
	/**
	 * 删除产品；
	 * @param productId
	 * @return
	 */
    int deleteByPrimaryKey(String productId);
    
    /**
     * 通过更改状态来移除产品; created by jie.jia at 2016-04-07 13:58
     * @param productId
     * @return
     */
    int deleteWithStatusByPrimaryKey(String productId);
    
	/**
	 * 根据商品ID查询产品信息；created by JUGUANGXING at 2016年3月10日 19:02:24
	 * @param goodsId
	 * @return
	 */
	Product findProductBygoodsId(String goodsId);
    
    /**
	 * 获取产品列表信息；created by jie.jia at 2015-12-23 13:39
	 * @param page
	 * @param product
	 * @return
	 */
	List<Product> getProductList(Page page,@Param("product") Product product);
	
	
	List<Product> findProductListByGoodsVariable(Page page,@Param("product") Product product);
	
	
	List<Product> findProductFruitList(Page page,@Param("product") Product product);
	

	/**
	 * 用left join的方式来获取产品列表信息; created by jie.jia at 2016-04-07 10:27
	 * @param page
	 * @param product
	 * @return
	 */
	List<Product> findProductList(Page page,@Param("product") Product product);
	
	/**
	 * 查询有规格明细的产品
	 * @param product
	 * @return
	 */
	List<Product> findProductInfoAreDetailed(Product product);
	
	
	/**
	 * 查询产品列表信息; created by jie.jia at 2016-04-01 16:53
	 * @param page
	 * @param product
	 * @return
	 */
	List<Product> findProductListByQuery(Page page,@Param("product") Product product);
	
	/**
	 * 查询产品列表信息; created by 黄苇 at 2016年8月25日18:20:08
	 * @param page
	 * @param product
	 * @return
	 */
	List<Product> findProductFruitListByQuery(Page page,@Param("product") Product product);
	
 
    int insert(Product record);
 
    int insertSelective(Product record);
 
    /**
     * 获取产品信息；created by jie.jia at 2016-01-05 10:08
     * @param productId
     * @return
     */
    Product selectByPrimaryKey(String productId);
 
    int updateByPrimaryKeySelective(Product record);
    
   
    /**
     * 更新产品;
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(Product record);
 
    int updateByPrimaryKey(Product record);

	List<Product> getProductList2(Page page,@Param("product") Product product,@Param("producttype") String producttype);
	
	/**根据产品名称和companyId查询产品
	 * @param product
	 * @return
	 */
	Product findProductByName(Product product);
	
	
	/**根据产品名称和companyId产品有多少个
	 * @param product
	 * @return
	 */
	int findProductCountByName(Product product);
	
	List<Product> findProductListByName(Product product);
	
	List<Product> findProductListByNameOrMarketcode(Product product);
	
}
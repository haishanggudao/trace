package cn.rfidcer.service;

import java.util.List;
 
import cn.rfidcer.bean.ProductDetailMap;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * 产品附加属性，service；
 * @author jie.jia
 *
 */
public interface ProductDetailMapService {
	
	/**
	 * 新增或者修改产品附加属性；created by jie.jia at 2015-12-24 15:03
	 * @param productDetailMap
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdateProductDetailMap(ProductDetailMap productDetailMap, User user);
	
	/**
	 * 删除产品附加属性； created by jie.jia at 2015-12-25 09:58
	 * @param productDetailMap
	 * @return
	 */
	ResultMsg delProductDetailMap(ProductDetailMap productDetailMap);
	
	/**
	 * 获取产品附加属性列表；created by jie.jia at 2015-12-24 13:39
	 * @param page
	 * @param productDetailMap
	 * @return
	 */
	List<ProductDetailMap> getProductDetailMapList(Page page,ProductDetailMap productDetailMap);

}

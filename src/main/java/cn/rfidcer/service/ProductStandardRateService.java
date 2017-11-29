package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ProductStandardRate;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;


/**产品规格转化业务层
 * @author xzm
 *
 */
public interface ProductStandardRateService {

	/**查找产品规格转化，可分页
	 * @param page 分页对象
	 * @return
	 */
	List<ProductStandardRate> findAll(Page page,ProductStandardRate rate,String productCategoryId);
	
	/**新增或修改产品规格转化
	 * @param rate
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateProductStandardRate(ProductStandardRate rate,User user);
	
	/**删除产品规格转化
	 * @param rate
	 * @return
	 */
	ResultMsg delProductStandardRate(ProductStandardRate rate);
	
}

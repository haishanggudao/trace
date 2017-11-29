package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;


/**产品规格业务层
 * @author xzm
 *
 */
public interface ProductStandardService {

	/**查找产品规格，可分页
	 * @param page 分页对象
	 * @return
	 */
	List<ProductStandard> findAll(Page page,ProductStandard productStandard);
	
	/**新增或修改产品规格
	 * @param productStandard
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateProductStandard(ProductStandard productStandard,User user);
	
	/**删除产品规格
	 * @param productCategory
	 * @return
	 */
	ResultMsg delProductStandard(ProductStandard productCategory);

	void createAPS(ProductStandard ps);
}

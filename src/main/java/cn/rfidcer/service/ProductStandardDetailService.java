package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;


/**产品规格明细业务层
 * @author xzm
 *
 */
public interface ProductStandardDetailService {

	/**查找产品规格明细，可分页
	 * @param page 分页对象
	 * @return
	 */
	List<ProductStandardDetail> findAll(Page page,ProductStandardDetail productStandardDetail);
	
	/**新增或修改产品规格明细
	 * @param productStandardDetail
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateProductStandardDetail(ProductStandardDetail productStandardDetail,User user);
	
	/**删除产品规格明细
	 * @param productStandardDetail
	 * @return
	 */
	ResultMsg delProductStandardDetail(ProductStandardDetail productStandardDetail);

	void createAPSD(ProductStandardDetail std);
}

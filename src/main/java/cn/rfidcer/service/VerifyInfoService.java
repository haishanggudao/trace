package cn.rfidcer.service;

import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.bean.ResultMsg;


/**校验接口
 * @author JUGUANGXING
 */
public interface VerifyInfoService {
	/**
	 * 校验产品
	 * @param product
	 * @return
	 */
	ResultMsg verifyProduct(Product product);
	/**
	 * 校验产品规格
	 * @param product
	 * @return
	 */
	ResultMsg verifyStandard(ProductStandard productStandard);
	
}

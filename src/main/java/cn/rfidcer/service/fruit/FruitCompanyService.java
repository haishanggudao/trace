package cn.rfidcer.service.fruit;

import java.util.List;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.fruit.FruitCompany;
import cn.rfidcer.interceptor.Page;

/**   
* @Title: FruitCompanyService.java 
* @Package cn.rfidcer.service.fruit 
* @Description: service 水果协会-企业信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月23日 下午3:31:29 
* @version V1.0   
*/
public interface FruitCompanyService {
	
	/**
	 * 新增或编辑水果企业协会-企业信息; created by jie.jia at 2016-08-23 15:34 
	 * @param fruitCompany
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdateCompany(FruitCompany fruitCompany,User user);

	/**
	 * query the list of fruit company; created by jie.jia at 2016-08-24 13:48
	 * @param page
	 * @param fruitCompany
	 * @return
	 */
	List<FruitCompany> listQuery(Page page, FruitCompany fruitCompany);
	
	/**
	 * 获取水果协会-企业性质; created by jie.jia at 2016-08-25 11:21
	 * @return
	 */
	public List<CommonVariable> getCompanyNatures();
}

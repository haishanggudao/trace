package cn.rfidcer.dao.fruit;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.fruit.FruitCompany;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

/**   
* @Title: FruitCompanyMapper.java 
* @Package cn.rfidcer.dao.fruit 
* @Description: DAO 水果协会-企业信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月23日 下午3:18:25 
* @version V1.0   
*/
public interface FruitCompanyMapper extends Mapper<FruitCompany> {
	
	
	/**
	 * query the list of fruit company; created by jie.jia at 2016-08-24 11:25
	 * @param page
	 * @param fruitCompany
	 * @return
	 */
	List<FruitCompany> listQuery(Page page,@Param("fruitCompany")FruitCompany fruitCompany);

}

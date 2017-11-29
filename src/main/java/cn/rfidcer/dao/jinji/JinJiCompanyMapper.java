package cn.rfidcer.dao.jinji;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.jinji.JinJiCompany;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

/**   
* @Title: JinJiCompanyMapper.java 
* @Package cn.rfidcer.dao.jinji 
* @Description: DAO 金机快餐-企业信息 
* @author 黄苇
* @Copyright Copyright
* @date 2016年10月27日09:10:03
* @version V1.0   
*/
public interface JinJiCompanyMapper extends Mapper<JinJiCompany> {
	
	
	/**
	 * query the list of jinji company; created by huangwei at 2016年10月27日09:10:52
	 * @param page
	 * @param fruitCompany
	 * @return
	 */
	List<JinJiCompany> listQuery(Page page,@Param("jinjiCompany")JinJiCompany jinjiCompany);
    
	// int insertSelective(@Param("jinjiCompany")JinJiCompany jinJiCompany);
}

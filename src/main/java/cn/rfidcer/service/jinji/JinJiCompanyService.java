package cn.rfidcer.service.jinji;

import java.util.List;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.jinji.JinJiCompany;
import cn.rfidcer.interceptor.Page;

/**   
* @Title: JinJiCompanyService.java 
* @Package cn.rfidcer.service.jinji 
* @Description: service 金机快餐-企业信息 
* @author 黄苇
* @Copyright Copyright
* @date 2016年10月27日09:34:47 
* @version V1.0   
*/
public interface JinJiCompanyService {
	
	/**
	 * 新增或编辑金机企业协会-企业信息; created by huangwei at 2016年10月27日09:35:11
	 * @param jinjiCompany
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdateCompany(JinJiCompany jinjiCompany,User user);

	/**
	 * query the list of jinji company; created by huangwei at 2016年10月27日09:35:11
	 * @param page
	 * @param jinjiCompany
	 * @return
	 */
	List<JinJiCompany> listQuery(Page page, JinJiCompany jinjiCompany);
	
	/**
	 * 获取水果协会-企业性质; created by huangwei at 2016年10月27日09:35:11
	 * @return
	 */
	public List<CommonVariable> getCompanyNatures();
}

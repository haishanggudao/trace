package cn.rfidcer.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.Logistics;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * Service for logistics
 * @author jie.jia
 * created by jie.jia at 2016-03-08 14:13
 */
public interface LogisticsService {
	
	/**
	 * 新增或编辑物流企业信息; created by jie.jia at 2016-03-08 16:31
	 * @param logistics
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateLogistics(Logistics logistics,User user);
	
	/**
	 * 删除物流企业信息; created by jie.jia at 2016-03-09 11:34
	 * @param logistics
	 * @return
	 */
	ResultMsg deleteByLogisticsId(Logistics logistics);
	
	/**
	 * 查询所有的物流企业信息; created by jie.jia at 2016-03-08 15:40
	 * @param page
	 * @return
	 */
	List<Logistics> findAll(Page page,Logistics logistics);
	
	/**
	 * 依据用户所属企业ID来查询物流企业信息; created by jie.jia at 2016-03-09 15:37
	 * @param companyId
	 * @return
	 */
	List<Logistics> findByUserCompanyId(String companyId);

	void createOrUpdate(List<Logistics> ps, User currentUser);

	List<Logistics> getLogisticsCompanys(String companyId);

	/**
	 * @param importFile
	 * @param user
	 * @param companyId
	 * @return
	 */
	ResultMsg addImportLogistics(MultipartFile importFile, User user, String companyId);

}

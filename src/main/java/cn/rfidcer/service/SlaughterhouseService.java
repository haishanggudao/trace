package cn.rfidcer.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.Slaughterhouse;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
* @Title: SlaughterhouseService.java 
* @Package cn.rfidcer.service 
* @Description: Service 屠宰场信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月22日 下午2:02:04 
* @version V1.0
 */
public interface SlaughterhouseService {

	/**
	 * 新增或编辑屠宰场信息；updated by jie.jia at 2016-06-22 14:02
	 * @param slaughterhouse
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdate(Slaughterhouse slaughterhouse, User user);

	/**
	 * 获取所有的屠宰场信息列表；updated by jie.jia at 2016-06-22 14:42
	 * @param page
	 * @param slaughterhouse
	 * @return
	 */
	List<Slaughterhouse> list(Page page, Slaughterhouse slaughterhouse);

	ResultMsg deleteByKey(String id);

	void createOrUpdate(List<Slaughterhouse> ps, User currentUser);

	List<Slaughterhouse> getslaughterhouseCompanys(String companyId);

	ResultMsg addImportSlaughterhouse(MultipartFile uploadImportFile, User currentUser, String companyId);

}
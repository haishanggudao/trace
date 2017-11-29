package cn.rfidcer.service;

import java.util.List;


import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.interceptor.Page;
/**   
 * @Title: AreaInfoService.java 
 * @Package cn.rfidcer.service 
 * @Description: 地区管理业务层
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年6月29日 上午11:00:29 
 * @version V1.0   
*/
public interface AreaInfoService {
	/**
	 * 查找产品分类，可分页
	 * @param page 分页对象
	 * @return
	 */
	List<AreaInfo> findAll(Page page,AreaInfo AreaInfo);
	
	/**
	 * 查询地区信息; created by jie.jia at 2016-10-17 14:22
	 * @param page
	 * @param areaInfo
	 * @return
	 */
	List<AreaInfo> findAllWithQuery(Page page, AreaInfo areaInfo);
	
	/**
	 * 获取省份列表
	 * @return
	 */
	List<AreaInfo> getProvinces();
	/**
	 * 根据省份获取市
	 * @return
	 */
	List<AreaInfo> getCitys(String proviceId);
	/**
	 * 根据省份和城市获取地区地址
	 * @param proviceId
	 * @param cityId
	 * @return
	 */
	List<AreaInfo> getAreas(String proviceId, String cityId);

	/**
	 * @param page
	 * @param ai
	 * @return
	 */
	List<AreaInfo> findAllCatgNameAndId(Page page, AreaInfo ai);
}

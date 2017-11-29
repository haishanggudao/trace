package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;

/**   
  * @Title: 地区表mapper
  * @author jgx
  * @date 2016年6月29日 上午10:26:32 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public interface AreaInfoMapper extends Mapper<AreaInfo>{

	List<AreaInfo> list(Page page,@Param("areainfo")AreaInfo ai);
	
	List<AreaInfo> findAllCatgNameAndId(Page page,@Param("areainfo")AreaInfo ai);
	
	/**
	 * @param page
	 * @param areaInfo
	 * @return
	 */
	List<AreaInfo> findAll(Page page, AreaInfo areaInfo);
	
	/**
	 * 查询地区信息; created by jie.jia at 2016-10-17 14:22
	 * @param page
	 * @param areaInfo
	 * @return
	 */
	List<AreaInfo> findAllWithQuery(Page page, @Param("areainfo")AreaInfo areaInfo);
	
	/**
	 * 依据省市区来查询地区信息
	 * @param areaInfo
	 * @return
	 */
	AreaInfo findByProviceCityArea(AreaInfo areaInfo);
	
	

	/**
	 * 查询ID是否存在
	 * @param id
	 * @return
	 */
	int  findExitId(String id);
	
}
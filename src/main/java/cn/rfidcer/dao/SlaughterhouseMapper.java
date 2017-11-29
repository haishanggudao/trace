package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Slaughterhouse;
import cn.rfidcer.interceptor.Page;

/**
* @Title: SlaughterhouseMapper.java 
* @Package cn.rfidcer.dao 
* @Description: DAO 屠宰场信息管理 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月22日 下午1:53:51 
* @version V1.0
 */
public interface SlaughterhouseMapper {
	
    int deleteByPrimaryKey(String slaughterhouseId);

    
    int deleteWithStatusByPrimaryKey(String slaughterhouseId);
    
    /**
     * 新增屠宰场；updated by jie.jia at 2016-06-22 14:29
     * @param record
     * @return
     */
    int insert(Slaughterhouse record);

    int insertSelective(Slaughterhouse record);

    Slaughterhouse selectByPrimaryKey(String slaughterhouseId);
    
    /**
     * 修改屠宰信息；updated by jie.jia at 2016-06-22 14:38
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Slaughterhouse record);

	/**查询屠宰场列表信息,Page对象为null时，不分页
	 * @param page
	 * @param slaughterhouse
	 * @return
	 */
	List<Slaughterhouse> list(Page page,@Param("slaughterhouse") Slaughterhouse slaughterhouse);
	
	
	/**获取所有企业，有屠宰场的显示别名
	 * @return
	 */
	List<Slaughterhouse> getslaughterhouseCompanys(String companyId);
	
	/**查找该企业的对应的slaughterhouseCompanyId屠宰场是否已存在
	 * @param slaughterhouse
	 * @return 0为不存在
	 */
	int checkSlaughterhouseExistsBySlaughterhouseId(Slaughterhouse slaughterhouse);
	
	/**查找该企业的对应的slaughterhouseName 屠宰场是否已存在
	 * @param slaughterhouse
	 * @return
	 */
	int checkSlaughterhouseExistsBySlaughterhouseName(Slaughterhouse slaughterhouse);
	
	/**根据屠宰场别名和companyId查找
	 * @param slaughterhouse
	 * @return
	 */
	Slaughterhouse findBySlaughterhouseName(Slaughterhouse slaughterhouse);
	
	
}
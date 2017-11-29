package cn.rfidcer.dao.jinji;

import cn.rfidcer.bean.jinji.Material;
import tk.mybatis.mapper.common.Mapper;

/**   
* @Title: MaterialMapper.java 
* @Package cn.rfidcer.dao.jinji 
* @Description: DAO 金机餐饮-原料
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月26日 下午7:01:54 
* @version V1.0   
*/
public interface MaterialMapper extends Mapper<Material>{
	
	/**
	 * 检查产品名称是否存在？ created by jie.jia at 2015-12-23 13:23
	 * @param product
	 * @return
	 */
	int checkMaterialNameExists(Material material);
	
	/**
     * 通过更改状态来移除产品; created by jie.jia at 2016-04-07 13:58
     * @param productId
     * @return
     */
    int deleteWithStatusByPrimaryKey(String productId);

}

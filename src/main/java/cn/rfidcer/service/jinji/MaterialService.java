package cn.rfidcer.service.jinji;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.jinji.Material;

/**
* @Title: MaterialService.java 
* @Package cn.rfidcer.service.jinji 
* @Description: Service 金机餐饮-原料
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月26日 下午5:28:54 
* @version V1.0
 */
public interface MaterialService {
	
	/**
	 * 新增或编辑原料;
	 * @param material
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdateMaterial(Material material, User user);
	 
	/**
	 * 删除原料信息；created by jie.jia at 2015-12-23 16:24
	 * @param material
	 * @return
	 */
	ResultMsg delMaterial(Material material);

}

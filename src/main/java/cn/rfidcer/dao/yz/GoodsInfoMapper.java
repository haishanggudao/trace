package cn.rfidcer.dao.yz;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.yz.GoodsInfoList;
import cn.rfidcer.interceptor.Page;



/**
 * 商品根据条件分页查询(果园项目)
 * @Title: GoodsInfoMapper.java 
 * @Package cn.rfidcer.dao.yz 
 * @Description: 
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年8月29日 下午5:41:24 
 * @version V1.0   
*/
public interface GoodsInfoMapper {
	
	/**
	 * @param page
	 * @param companyId
	 * @param goodCodeOrName
	 * @return
	 */
	ArrayList<GoodsInfoList> getGoodsInfoListByPage(Page page,@Param("companyId") String companyId,@Param("goodCodeOrName") String goodCodeOrName );
	
}

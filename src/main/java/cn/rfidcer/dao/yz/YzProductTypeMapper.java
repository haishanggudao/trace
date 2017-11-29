package cn.rfidcer.dao.yz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.yz.YzProductType;

public interface YzProductTypeMapper {

	
	/**获取羽众的商品分类
	 * @param entId 企业ID
	 * @param typeId 父分类ID
	 */
	List<YzProductType> queryYzProductTypes(@Param("entId") String entId,@Param("typeId") String typeId);
}

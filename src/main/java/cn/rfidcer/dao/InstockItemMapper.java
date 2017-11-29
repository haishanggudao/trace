package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.bean.InstockManagerItem;
import cn.rfidcer.bean.InstockPurchaseItem;
import cn.rfidcer.interceptor.Page;

public interface InstockItemMapper {

	int deleteByPrimaryKey(String instockItemId);

	int deleteInstockItemsByPurchaseOrderId(String purchaseOrderId);

	int deleteInstockitemByInstockMainId(String instockMainId);

	int findCountByInstockMainId(String instockMainId);

	int deleteByInstockMainId(String instockMainId);

	int insert(InstockItem record);

	int insertSelective(InstockItem record);

	InstockItem selectByPrimaryKey(String instockItemId);

	int updateByPrimaryKeySelective(InstockItem record);

	int updateByPrimaryKey(InstockItem record);

	List<InstockItem> list(Page page);

	List<InstockItem> listWidthIMID(String instockMainId);

	List<InstockItem> listInstockItemsByPurchaseOrderId(String purchaseOrderId);

	List<InstockPurchaseItem> findInstockItemsByOrderId(Page page, @Param("purchaseOrderId") String purchaseOrderId);

	List<InstockPurchaseItem> findAllListByParamAnd(Page page, @Param("instockItem") InstockItem instockItem);

	/**查询所有进货登记
	 * @param page
	 * @param instockItem
	 * @return
	 */
	List<InstockManagerItem> findAllList(Page page,@Param("instockManagerItem") InstockManagerItem instockManagerItem);
}
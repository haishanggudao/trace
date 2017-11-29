package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.OutstockCountInfo;
import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.interceptor.Page;

public interface OutstockItemMapper {

	int deleteByPrimaryKey(String outstockItemId);

	int deleteByOutstockMainId(String outstockMainId);

	int findCountByOutstockMainId(String outstockMainId);

	int insert(OutstockItem record);
	
	int insertSelective(OutstockItem record);
	
	OutstockItem selectByPrimaryKey(String outstockItemId);
	
	/**
	 * 根据商品明细ID查询出库明细
	 * @param goodsId
	 * @return
	 */
	OutstockItem selectByGoodsDetailId(String goodsId);
	
	int updateByPrimaryKeySelective(OutstockItem record);

	int updateByPrimaryKey(OutstockItem record);

	List<OutstockItem> list(Page page);

	List<OutstockItem> list();

	List<OutstockItem> listOIWOMI(Page page, @Param("outstockmainid") String outstockmainid);

	List<OutstockItem> findAllList(Page page, @Param("outstockmainid") String outstockmainid);

	/**
	 * 根据商品明细追溯码获取商品明细ID和出库明细ID
	 * @param QRCode
	 * @return
	 */
	OutstockItem getOutstockItemByQRCode(String QRCode);
	
	/**
	 * 根据商品明细ID查询出库明细
	 * @param goodsId
	 * @return
	 */
	OutstockItem getOutstockItemByGoodsQRCode(String QRCode);

	List<OutstockCountInfo> getClintCountInfo(String outstockMainId);

	/**
	 * @param outstockitem
	 */
	List<OutstockItem> selectByBean(@Param("outstockitem")OutstockItem outstockitem);
}
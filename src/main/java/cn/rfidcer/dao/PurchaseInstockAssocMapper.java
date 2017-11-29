package cn.rfidcer.dao;

import java.util.List;

import cn.rfidcer.bean.PurchaseInstockAssoc;

public interface PurchaseInstockAssocMapper {

	int deleteByPrimaryKey(PurchaseInstockAssoc key);

	int deleteByInstockMainId(String instockMainId);

	int insert(PurchaseInstockAssoc record);

	int insertSelective(PurchaseInstockAssoc record);

	PurchaseInstockAssoc selectByPrimaryKey(PurchaseInstockAssoc key);

	List<PurchaseInstockAssoc> selectByInstockMainId(String instockMainId);

	int updateByPrimaryKeySelective(PurchaseInstockAssoc record);

	int updateByPrimaryKey(PurchaseInstockAssoc record);
}
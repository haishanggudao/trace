package cn.rfidcer.dao;

import cn.rfidcer.bean.PurchaseItem;

/**
 * 
* @Title: PurchaseItemMapper.java 
* @Package cn.rfidcer.dao 
* @Description: 采购单明细DAO 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月14日 下午2:04:57 
* @version V1.0
 */
public interface PurchaseItemMapper {
    
    int deleteByPrimaryKey(String purchaseitemid);
    
    /**
     * delete items by the purchase order id
     * @param purchaseOrderId
     * @return
     * created by jie.jia at 2016-06-14 14:08
     */
    int deleteByPurchaseOrderId(String purchaseOrderId);

    
    int insert(PurchaseItem record);

    
    int insertSelective(PurchaseItem record);

    
    PurchaseItem selectByPrimaryKey(String purchaseitemid);

    
    int updateByPrimaryKeySelective(PurchaseItem record);

    
    int updateByPrimaryKey(PurchaseItem record);
}
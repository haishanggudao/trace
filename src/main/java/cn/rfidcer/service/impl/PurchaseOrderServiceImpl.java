package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.PurchaseItemMapper;
import cn.rfidcer.dao.PurchaseOrderDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.PurchaseOrderService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	private Logger logger=LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private PurchaseItemMapper purchaseItemMapper;

	@Override
	public ResultMsg addOrUpdate(PurchaseOrder purchaseOrder,User user,String items) {
		int res=0;
		String msg=null;
		if(StringUtils.isEmpty(purchaseOrder.getPurchaseOrderId())){
			// purchaseOrder.setOrderTime(new Timestamp(System.currentTimeMillis()));//设置下单时间
			String UUID = UUIDGenerator.generatorUUID();
			purchaseOrder.setPurchaseOrderId(UUID);
			logger.info("新增采购单："+purchaseOrder);
			msg="新增采购单";
			CommonImportUtils.setCreateAndUpdateTime(purchaseOrder, user);//设置修改日期
			
			// DAO action: insert a new purchase order
			res = purchaseOrderDao.addPurchaseOrder(purchaseOrder);
			
			if(res==1){
				List<PurchaseItem> purchaseItems = JSONObject.parseArray(items, PurchaseItem.class);
				addPurchaseItems(purchaseItems,purchaseOrder.getPurchaseOrderId(), user);
			}
		}else{
			// purchaseOrder.setOrderTime(new Timestamp(System.currentTimeMillis()));//设置下单时间
			logger.info("修改采购单："+purchaseOrder);
			msg="修改采购单";
			CommonImportUtils.setUpdateTime(purchaseOrder, user);//设置修改日期
			
			// DAO action: update the purchase order
			res = purchaseOrderDao.updatePurchaseOrder(purchaseOrder);
			
			if(res==1){
				// DAO action: delete the items by purchase order id
				purchaseItemMapper.deleteByPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
				
				List<PurchaseItem> purchaseItems = JSONObject.parseArray(items, PurchaseItem.class);
				
				// call method: add new items after updated purchase order
				addPurchaseItemsForUpdated(purchaseItems,purchaseOrder.getPurchaseOrderId(), user);
			}
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}
	
	/**
	 * add new items after updated purchase order
	 * @param purchaseItems
	 * @param purchaseOrderId
	 * @param user
	 * created by jie.jia at 2016-06-14 14:13
	 */
	private void addPurchaseItemsForUpdated(List<PurchaseItem> purchaseItems,String purchaseOrderId, User user) {
		for (PurchaseItem  purchaseItem: purchaseItems) {
			CommonImportUtils.setCreateAndUpdateTime(purchaseItem, user);//设置创建日期和修改日期
			CommonImportUtils.setUpdateTime(purchaseItem, user);//设置修改日期
			purchaseItem.setPurchaseItemId(UUIDGenerator.generatorUUID());
			purchaseItem.setPurchaseOrderId(purchaseOrderId);
			
			// DAO action: insert a new item
			purchaseItemMapper.insertSelective(purchaseItem);
		}
	}
	
	private void addPurchaseItems(List<PurchaseItem> purchaseItems,String purchaseOrderId, User user) {
		for (PurchaseItem  purchaseItem: purchaseItems) {
			if(StringUtils.isEmpty(purchaseItem.getPurchaseItemId())){
				CommonImportUtils.setCreateAndUpdateTime(purchaseItem, user);//设置创建日期和修改日期
				purchaseItem.setPurchaseItemId(UUIDGenerator.generatorUUID());
				purchaseItem.setPurchaseOrderId(purchaseOrderId);
				purchaseItemMapper.insertSelective(purchaseItem);
			}else{
				CommonImportUtils.setUpdateTime(purchaseItem, user);//设置修改日期
				purchaseItemMapper.updateByPrimaryKeySelective(purchaseItem);
			}
		}
	}
	
	@Override
	public List<PurchaseOrder> list(Page page, PurchaseOrder purchaseOrder) {
		return purchaseOrderDao.list(page, purchaseOrder);
	}
	
	@Override
	public List<PurchaseOrder> listByAdvancedQuery(Page page, PurchaseOrder purchaseOrder) {
		// assign new value to the start date time of order
		if ( !StringUtil.isBlank(purchaseOrder.getOrderTimeStart()) ) {
			String myOrderTimeStart = purchaseOrder.getOrderTimeStart() + " 00:00:00";
			purchaseOrder.setOrderTimeStart(myOrderTimeStart);
		}
		
		// assign new value to the end date time of order
		if ( !StringUtil.isBlank(purchaseOrder.getOrderTimeEnd()) ) {
			String myOrderTimeEnd = purchaseOrder.getOrderTimeEnd() + " 23:59:59";
			purchaseOrder.setOrderTimeEnd(myOrderTimeEnd);
		}
		
		return purchaseOrderDao.listByAdvancedQuery(page, purchaseOrder);
	}
	
	@Override
	public ResultMsg deletePurchaseOrder(PurchaseOrder purchaseOrder) {
		logger.info("删除采购单,ID:"+purchaseOrder.getPurchaseOrderId());
		purchaseOrderDao.deletePurchaseItemsByPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
		int res = purchaseOrderDao.deletePurchaseOrder(purchaseOrder.getPurchaseOrderId());
		return ResultMsgUtil.getReturnMsg(res, "删除采购单");
	}
	
	@Override
	public List<PurchaseItem> findPurchaseItemsByOrderId(String purchaseOrderId) {
		return purchaseOrderDao.findPurchaseItemsByOrderId(purchaseOrderId);
	}
	
	public List<PurchaseItem> findPurchaseItemsByOrderIdOderPage(Page page, String purchaseOrderId) {
		List<PurchaseItem> list = purchaseOrderDao.findPurchaseItemsByOrderIdOderPage(page,purchaseOrderId);
		return list;
	}
	
	@Override
	public List<PurchaseOrder> listAll() {
		return purchaseOrderDao.listAll();
	}
	@Override
	public List<PurchaseOrder> findAllPurchaseOrderId(String companyId) {
		return purchaseOrderDao.findAllPurchaseOrderId(companyId);
	}

	

}

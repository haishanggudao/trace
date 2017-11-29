package cn.rfidcer.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.OutstockMainMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.ProductStandardDetailDao;
import cn.rfidcer.dao.SaleItemMapper;
import cn.rfidcer.dao.SaleOrderMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SaleOrderService;
import cn.rfidcer.service.SerialNumService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {
	
	private Logger logger=LoggerFactory.getLogger(SaleOrderServiceImpl.class);
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private SaleOrderMapper saleOrderDao;
	@Autowired
	private SaleItemMapper saleItemMapper;
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private ProductStandardDetailDao productStandardDetailDao;
	
	@Autowired
	private OutstockMainMapper outstockDao;
	
	@Autowired
	private SerialNumService serialNumService;
	
	@Override
	public List<SaleOrder> list(Page page) {
		if(page != null) {
			return saleOrderDao.list(page);
		} else {
			return saleOrderDao.list();
		}
	}

	@Override
	public List<SaleOrder> findByStatus(SaleOrder saleOrder) {
			return saleOrderDao.findByStatus(saleOrder);
	}
	
	@Override
	public List<SaleOrder> findAll(Page page, SaleOrder saleOrder) { 
		return saleOrderDao.findAll(page, saleOrder);
	}
	
	@Override
	public List<SaleItem> findAllOutstockItems(Page page, SaleItem saleItem) { 
		String[] saleItemIdArray =  null;
		if(!StringUtil.isBlank(saleItem.getSaleOrderId()) && saleItem.getSaleOrderId().contains(",")){
			saleItemIdArray = saleItem.getSaleOrderId().split(",");
		}else if(!StringUtil.isBlank(saleItem.getSaleOrderId())){
			saleItemIdArray = new String[]{saleItem.getSaleOrderId()};
		}
		List<SaleItem>  lst = saleItemMapper.findAllGroupByProductId(page, saleItemIdArray);
		for (SaleItem saleItem2 : lst) {
			List<Goods> findGoodsByStandardDetailId = goodsDao.findGoodsByStandardDetailId(saleItem2.getProductStandardDetailId());
			saleItem2.setGoods(findGoodsByStandardDetailId.get(0));
		}
		return lst;
	}
	@Override
	public ResultMsg createOrUpdateSaleOrder(SaleOrder saleOrder, User user) {
		int res=0;
		String msg=null; 
		String mySaleOrderID = "";
		if(StringUtils.isEmpty(saleOrder.getSaleOrderId())){
			// case: insert
			logger.info("新增销售单："+saleOrder);
			msg="新增销售单信息";
			CommonImportUtils.setCreateAndUpdateTime(saleOrder, user);//设置修改日期
			mySaleOrderID = UUIDGenerator.generatorUUID();
			saleOrder.setSaleOrderId(mySaleOrderID ); 
			res = saleOrderDao.insertSelective(saleOrder); 
			if ( res == 1) {
				attachEmptyOutstockMain(saleOrder,user);
			}
			
		} else {
			// case: update
			logger.info("编辑销售单："+saleOrder);
			msg="编辑销售单信息";
			mySaleOrderID = saleOrder.getSaleOrderId();
			CommonImportUtils.setUpdateTime(saleOrder, user);//设置修改日期
			saleItemMapper.deleteByMainid(mySaleOrderID);
			res = saleOrderDao.updateByPrimaryKeySelective(saleOrder);
		}
		
		if ( res == 1) {
			List<SaleItem> mySaleItems = saleOrder.getSaleItems();
			if (mySaleItems != null && !mySaleItems.isEmpty()) {
				for (SaleItem myItem : mySaleItems) {
					if (myItem.getSaleItemId() == null || myItem.getSaleItemId().equals("")) {
						continue;
					}
					myItem.setSaleOrderId(mySaleOrderID);
					CommonImportUtils.setCreateAndUpdateTime(myItem, user);//设置修改日期
					// action: insert new saleItem
					saleItemMapper.insertSelective(myItem);
				}
			}
		}
		
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	
	@Override
	public ResultMsg addOrUpdate(SaleOrder saleOrder, User user,String items) {
		int res=0;
		ResultMsg resultMsg = new ResultMsg();
		String code = "1";
		String msg = null;
		List<SaleItem> saleItems = JSONObject.parseArray(items, SaleItem.class);
		try{
			if(StringUtils.isEmpty(saleOrder.getSaleOrderId())){
				String saleOrderId = UUIDGenerator.generatorUUID();
				saleOrder.setSaleOrderId(saleOrderId);
				saleOrder.setBarcode(saleOrder.getTraceCode());
				logger.info("新增销售单："+saleOrderId);
				msg="新增销售单信息";
				CommonImportUtils.setCreateAndUpdateTime(saleOrder, user);//设置修改日期
				res = saleOrderDao.insertSelective(saleOrder); 
			} else {
				logger.info("修改销售单："+saleOrder);
				msg="修改采购单";
				CommonImportUtils.setUpdateTime(saleOrder, user);//设置修改日期
				res = saleOrderDao.updateByPrimaryKeySelective(saleOrder);
				//修改销售单明细的出库状态
				SaleItem saleItem = new SaleItem();
				// saleItem.setStatus(saleOrder.getStatus());
				saleItem.setStatus("0");
				saleItem.setSaleOrderId(saleOrder.getSaleOrderId());
				saleItemMapper.updateStatusBySaleOrderId(saleItem);
			}
			/*添加主单完成后操作详细单*/
			if(res==1){
				resultMsg =addSaleItems(saleItems,saleOrder.getSaleOrderId(),user);
				if(resultMsg.getCode().equals("0")){
					return resultMsg;
				}
			}
			
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			code = "-2";
			msg = "数据异常";
			
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		
		return resultMsg;
	}

	
	

	private ResultMsg addSaleItems(List<SaleItem> saleItems,String saleOrderId, User user) {
		ResultMsg msg = new ResultMsg();
		msg.setCode("1");
		int i = 0 ;
		for (SaleItem  saleItem: saleItems) {
			i++;
			if(saleItem.getProductId()== null || saleItem.getProductId().equals("") || productMapper.selectByPrimaryKey(saleItem.getProductId())==null){
				msg.setCode("0");
				msg.setMsg("第"+i+"行,请选择对应的产品！");
				return  msg;
			}
			if(StringUtils.isEmpty(saleItem.getSaleItemId())){
				CommonImportUtils.setCreateAndUpdateTime(saleItem, user);//设置创建日期和修改日期
				saleItem.setSaleOrderId(saleOrderId);
				saleItem.setSaleItemId(UUIDGenerator.generatorUUID());
				//只有添加的时候判断销售价格为空来设置产品销售价格
				if(StringUtils.isEmpty(saleItem.getSalePrice())){
					ProductStandardDetail  productStandardDetail = new ProductStandardDetail();
					productStandardDetail.setProductStandardDetailId(saleItem.getProductStandardDetailId());
					productStandardDetail = productStandardDetailDao.findPriceById(productStandardDetail);
					if(productStandardDetail!=null){
						saleItem.setSalePrice(productStandardDetail.getSalePrice());
					}
				}
				saleItemMapper.insertSelective(saleItem);
			}else{
				CommonImportUtils.setUpdateTime(saleItem, user);//设置修改日期
				saleItemMapper.updateByPrimaryKeySelective(saleItem);
			}
		}
		return msg;
	}
	
	
	
	@Override
	public ResultMsg delSaleOrder(SaleOrder saleOrder) { 
		String toDeleteSaleMainId = saleOrder.getSaleOrderId();
		logger.info("删除销售单记录ID：" + toDeleteSaleMainId );
		
		ResultMsg resultMsg=new ResultMsg();
		int res = 0; 
		
		try {
			// action: delete the items of sale order
			saleItemMapper.deleteByMainid(toDeleteSaleMainId);
			
			// action: delete the main of sale order
			res = saleOrderDao.deleteByPrimaryKey(toDeleteSaleMainId);
			resultMsg.setCode(res+"");
			
			if(res==1){
				resultMsg.setMsg("删除销售单记录成功");
			}else{
				resultMsg.setMsg("删除销售单记录失败");
			}
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前销售记录无法删除");
		}
		
		return resultMsg;
	}

	/**
	 * attach a empty record of outstockMain; created by jie.jia at 2016-02-24 16:39
	 * @param saleOrder
	 */
	private void attachEmptyOutstockMain(SaleOrder saleOrder,User user){
		// attach a empty outstockMain
		OutstockMain outstockmain = new OutstockMain();
		
		outstockmain.setOutstockMainId(UUIDGenerator.generatorUUID());
		outstockmain.setSaleOrderId(saleOrder.getSaleOrderId());
		outstockmain.setCompanyId(saleOrder.getCompanyId());
		// 物流企业ID
		outstockmain.setLogisticsId(saleOrder.getLogisticsId());
		// 出库单号, outstockNum
		outstockmain.setOutstockNum(saleOrder.getSaleOrderNo());
		// 登记日期, registDate
//		outstockmain.setRegistDate(saleOrder.getCreateTime().toString());
		// 出库状态, outstockType
		outstockmain.setOutstockType("0");
		// 当前状态, status
		outstockmain.setStatus("0");
		CommonImportUtils.setCreateAndUpdateTime(outstockmain, user);//设置修改日期
		// action: insert a new outstockmain
		outstockDao.insertSelective(outstockmain);
	}

	@Override
	public SaleOrder getSaleOrder(String saleOrderId) {
		return saleOrderDao.selectByPrimaryKey(saleOrderId);
	}

	@Override
	public List<SaleItem> getSaleItems(String saleOrderId) {
		return saleItemMapper.listSaleItems(saleOrderId);
	}

	@Override
	public ResultMsg delSaleOrderItems(SaleItem saleItem) {
		logger.info("删除销售单明细记录ID：" + saleItem.getSaleItemId() );
		
		ResultMsg resultMsg=new ResultMsg();
		int res = 0; 
		
		try {
			res = saleItemMapper.deleteByPrimaryKey(saleItem.getSaleItemId());
			resultMsg.setCode(res+"");
			if(res==1){
				resultMsg.setMsg("删除销售单明细成功");
			}else{
				resultMsg.setMsg("删除销售单明细失败");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前销售明细无法删除");
		}
		return resultMsg;
	}
	
	@Override
	public List<SaleItem> findAllItems(Page page, SaleItem saleItem) {
		List<SaleItem>  lst = saleItemMapper.findAll(page, saleItem);
		return lst;
	}

	@Override
	public String getSaleOrderNo() {
		String serialNum = serialNumService.newSerialNum(new CommonVariable("-","saleOrder","serialNum","998"));
		String fullSerialNum = StringUtil.formatFillZeroLeft(3, serialNum);
		return DateUtil.timestamp()+fullSerialNum;
	}
	

}

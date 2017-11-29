package cn.rfidcer.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.bean.OutstockCountInfo;
import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.OutstockLog;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.LogisticsMapper;
import cn.rfidcer.dao.OutstockItemMapper;
import cn.rfidcer.dao.OutstockLogMapper;
import cn.rfidcer.dao.OutstockMainMapper;
import cn.rfidcer.dao.OutstockProductItemMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.SaleItemMapper;
import cn.rfidcer.dao.SaleOrderMapper;
import cn.rfidcer.dao.SaleOutstockMapper;
import cn.rfidcer.enums.ChangeType;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsStockService;
import cn.rfidcer.service.OutstockService;
import cn.rfidcer.service.ProductStockService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class OutstockServiceImpl implements OutstockService {

	private Logger logger = LoggerFactory.getLogger(OutstockServiceImpl.class);
	@Autowired
	private OutstockItemMapper oiMapper;
	@Autowired
	private OutstockMainMapper outstockMainMapper;
	@Autowired
	private OutstockLogMapper outstockLogMapper;
	@Autowired
	private OutstockProductItemMapper outstockProductItemMapper;	
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private SaleOutstockMapper saleOutstockMapper;	
	@Autowired
	private SaleItemMapper saleItemMapper;		
	@Autowired
	private SaleOrderMapper saleOrderMapper;		
	@Autowired
	private LogisticsMapper logisticsMapper;
	@Autowired
	private GoodsStockService goodsStockService;
	@Autowired
	private ProductStockService productStockService;
	
	public ResultMsg addOrUpdateOutstockItem(OutstockItem outstockitem,User user,int rowCount) {
		logger.info("invoke addOrUpdate ,id is {0} .", outstockitem.getOutstockItemId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			//int icompare = outstockitem.getStockNum().compareTo(BigDecimal.valueOf(0));
			//if(icompare!=0){
				if(StringUtils.isEmpty(outstockitem.getGoodsId()) || goodsDao.getByGoodsId(outstockitem.getGoodsId())==null){
					resultMsg.setCode("0");
					resultMsg.setMsg("第："+rowCount+"行,商品需要选择不可以随便输入！");
					return resultMsg;
				}
				if (StringUtils.isEmpty(outstockitem.getOutstockItemId())) {
					outstockitem.setOutstockItemId(UUIDGenerator.generatorUUID());
					CommonImportUtils.setCreateAndUpdateTime(outstockitem, user);//设置创建日期和修改日期	
					oiMapper.insert(outstockitem);
				} 
			//}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}
	
	public ResultMsg addOrUpdateOutstockProductItem(OutstockItem outstockitem,User user,int rowCount) {
		logger.info("invoke addOrUpdate ,id is {0} .", outstockitem.getOutstockItemId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			int icompare = outstockitem.getStockNum().compareTo(BigDecimal.valueOf(0));
			if(icompare!=0){
				if(productMapper.selectByPrimaryKey(outstockitem.getProductId())==null){
					resultMsg.setCode("0");
					resultMsg.setMsg("第："+rowCount+"行,产品需要选择不可以随便输入！");
					return resultMsg;
				}
				if (StringUtils.isEmpty(outstockitem.getOutstockItemId())) {
					outstockitem.setOutstockItemId(UUIDGenerator.generatorUUID());
					CommonImportUtils.setCreateAndUpdateTime(outstockitem, user);//设置创建日期和修改日期	
					outstockProductItemMapper.insertSelective(outstockitem);
				}
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public ResultMsg saveGoodsOutStock(OutstockMain outstockmain,User user,String outstockItems) {
		logger.info("新增修改产品出库单 {0} .", outstockmain.getOutstockMainId());
		ResultMsg resultMsg = new ResultMsg();
		/*if(StringUtil.isBlank(outstockmain.getLogisticsId()) || logisticsMapper.checkCountByLogisticsId(outstockmain.getLogisticsId())<1 ){
			resultMsg.setCode("0");
			resultMsg.setMsg("亲,物流企业必须要选择,不可以随便输入的！");
			return resultMsg;
		}*/
		try {
			if (StringUtils.isEmpty(outstockmain.getOutstockMainId())) {

				
				outstockmain.setOutstockMainId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(outstockmain, user);//设置创建日期和修改日期
				//循环插入关系表中 一对多
				String[] saleOrderIdArray =  null;
				if(!StringUtil.isBlank(outstockmain.getSaleOrderId()) && outstockmain.getSaleOrderId().contains(",")){
					saleOrderIdArray = outstockmain.getSaleOrderId().split(",");
				}else if(!StringUtil.isBlank(outstockmain.getSaleOrderId())){
					saleOrderIdArray = new String[]{outstockmain.getSaleOrderId()};
				}
				for(int i = 0 ; i<saleOrderIdArray.length ;i++){
					outstockmain.setSaleOrderId(saleOrderIdArray[i]);
					try {
						//修改销售单明细的出库状态
						SaleItem saleItem = new SaleItem();
						saleItem.setStatus("1");
						saleItem.setSaleOrderId(saleOrderIdArray[i]);
						saleItemMapper.updateStatusBySaleOrderId(saleItem);
						
						//修改销售单的出库状态
						SaleOrder  saleOrder = new SaleOrder();
						saleOrder.setStatus("1");
						saleOrder.setSaleOrderId(saleOrderIdArray[i]);
						saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
						saleOutstockMapper.insertSelective(outstockmain);
					}catch (Exception e) {
						resultMsg.setCode("0");
						resultMsg.setMsg(e.getMessage());
						e.printStackTrace();
						return resultMsg;
					}
				}
				outstockmain.setSaleOrderId("-");
				outstockMainMapper.insertSelective(outstockmain);
				//完善商品批次bug
				List<OutstockItem> lstoutstockitem = JSONObject.parseArray(outstockItems, OutstockItem.class);
				for (OutstockItem outstockItem : lstoutstockitem) {
					if(StringUtils.isEmpty(outstockItem.getGoodsId())){
						outstockItem.setGoodsId(outstockItem.getGoods().getGoodsId());
					}
				}
				int  rowCount = 0;//详细列表的行数
				//保存详细
				for(OutstockItem outstockitem : lstoutstockitem){
					rowCount++;
					outstockitem.setOutstockMainId(outstockmain.getOutstockMainId());
					/*---添加商品出库明细*/
					resultMsg = addOrUpdateOutstockItem(outstockitem,user,rowCount);
					//添加详细的时候如果遇到错误弹回错误信息
					if(resultMsg.getCode().equals("0"))
					{
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return resultMsg;
					}
					/*
					 * 更新库存，首先更新商品库存
					 */
					GoodsStock goodsStock = new GoodsStock(outstockitem.getGoodsId(),outstockmain.getCompanyId());
					goodsStock.setStockNum(outstockitem.getOutstockNum());
					goodsStock.setProductId(outstockitem.getProductId());
					goodsStock.setProductStandardDetailId(outstockitem.getProductStandardDetailId());
					goodsStockService.addOrupdateStock( user, OperationType.OUTSTOCK, goodsStock,true);
					// 插入一段日志记录
					insertOutstockMainLog(outstockmain, user, outstockitem);
				}
			} else {
				CommonImportUtils.setUpdateTime(outstockmain, user);//设置修改日期
				outstockMainMapper.updateByPrimaryKeySelective(outstockmain);
				OutstockItem outstockitem = new OutstockItem();
				outstockitem.setOutstockMainId(outstockmain.getOutstockMainId());
				List<OutstockItem> list = oiMapper.selectByBean(outstockitem);
				for (OutstockItem oi : list) {
					// 插入一段日志记录
					insertOutstockMainLog(outstockmain, user, oi);
				}
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultMsg;
	}

	
	
	/**
	 * @param outstockmain
	 * @param user
	 * @param outstockitem
	 */
	private void insertOutstockMainLog(OutstockMain outstockmain, User user, OutstockItem outstockitem) {
		// 插入一段日志记录
		OutstockLog outstocklog = new OutstockLog();
		outstocklog.setOutstockBatchNum(outstockmain.getOutstockBatchNum());
		outstocklog.setOutstockNum(outstockmain.getOutstockNum());
		outstocklog.setGoodsId(outstockitem.getGoodsId());
		outstocklog.setCompanyId(outstockmain.getCompanyId());
		List<OutstockLog> ols = outstockLogMapper.selectByBean(outstocklog);
		OutstockLog olinstance = null;
		boolean isupdate = false;
		if (ols != null && ols.size() > 0) {
			olinstance = ols.get(0);
			isupdate = true;
		} else {
			olinstance = new OutstockLog();
			olinstance.setId(UUIDGenerator.generatorUUID());
			olinstance.setCreateBy(user.getId());
			olinstance.setCreateTime(new Date());
		}
		olinstance.setCompanyId(outstockmain.getCompanyId());
		olinstance.setOutstockBatchNum(outstockmain.getOutstockBatchNum());
		olinstance.setOutstockNum(outstockmain.getOutstockNum());
		olinstance.setGoodsId(outstockitem.getGoodsId());
		olinstance.setUpdateBy(user.getId());
		olinstance.setUpdateTime(new Date());
		if (StringUtils.isEmpty(outstockitem.getOutstockNum())) {
			olinstance.setNum(new BigDecimal(0));
		} else {
			olinstance.setNum(outstockitem.getOutstockNum());
		}
		if (isupdate) {
			outstockLogMapper.updateByPrimaryKeySelective(olinstance);
		} else {
			outstockLogMapper.insertSelective(olinstance);
		}
	}

	@Override
	public ResultMsg saveProductOutStock(OutstockMain outstockmain,User user,String outstockItems) {
		logger.info("新增修改产品出库单 {0} .", outstockmain.getOutstockMainId());
		ResultMsg resultMsg = new ResultMsg();
		if(StringUtil.isBlank(outstockmain.getLogisticsId()) || logisticsMapper.checkCountByLogisticsId(outstockmain.getLogisticsId())<1 ){
			resultMsg.setCode("0");
			resultMsg.setMsg("亲,物流企业必须要选择,不可以随便输入的！");
			return resultMsg;
		}
		try {
			if (StringUtils.isEmpty(outstockmain.getOutstockMainId())) {
				outstockmain.setOutstockMainId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(outstockmain, user);//设置创建日期和修改日期
				//循环插入关系表中 一对多
				String[] saleOrderIdArray =  null;
				if(!StringUtil.isBlank(outstockmain.getSaleOrderId()) && outstockmain.getSaleOrderId().contains(",")){
					saleOrderIdArray = outstockmain.getSaleOrderId().split(",");
				}else if(!StringUtil.isBlank(outstockmain.getSaleOrderId())){
					saleOrderIdArray = new String[]{outstockmain.getSaleOrderId()};
				}
				for(int i = 0 ; i<saleOrderIdArray.length ;i++){
					outstockmain.setSaleOrderId(saleOrderIdArray[i]);
					try {
						//修改销售单明细的出库状态
						SaleItem saleItem = new SaleItem();
						saleItem.setStatus("1");
						saleItem.setSaleOrderId(saleOrderIdArray[i]);
						saleItemMapper.updateStatusBySaleOrderId(saleItem);
						
						//修改销售单的出库状态
						SaleOrder  saleOrder = new SaleOrder();
						saleOrder.setStatus("1");
						saleOrder.setSaleOrderId(saleOrderIdArray[i]);
						saleOrderMapper.updateByPrimaryKeySelective(saleOrder);
						saleOutstockMapper.insertSelective(outstockmain);
					}catch (Exception e) {
						resultMsg.setCode("0");
						resultMsg.setMsg(e.getMessage());
						e.printStackTrace();
						return resultMsg;
					}
				}
				outstockmain.setSaleOrderId("-");
				outstockMainMapper.insertSelective(outstockmain);
				List<OutstockItem> lstoutstockitem = JSONObject.parseArray(outstockItems, OutstockItem.class);
				int  rowCount = 0;//详细列表的行数
				//保存详细
				for(OutstockItem outstockitem : lstoutstockitem){
					rowCount++;
					outstockitem.setOutstockMainId(outstockmain.getOutstockMainId());
					resultMsg = addOrUpdateOutstockProductItem(outstockitem,user,rowCount);
					//添加详细的时候如果遇到错误弹回错误信息
					if(resultMsg.getCode().equals("0"))
					{
						return resultMsg;
					}
					/*
					 * 更新库存，首先更新产品库存
					 */
					ProductStock productStock = new ProductStock(outstockitem.getProductId(),outstockitem.getProductStandardDetailId(),outstockmain.getCompanyId());
					productStock.setStockNum(outstockitem.getOutstockNum().multiply(new BigDecimal(-1)));
					productStockService.addOrupdateStock( user, OperationType.OUTSTOCK,ChangeType.SUBTRACT, productStock);
				}
			} else {
				CommonImportUtils.setUpdateTime(outstockmain, user);//设置修改日期
				outstockMainMapper.updateByPrimaryKeySelective(outstockmain);
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultMsg;
	}

	@Override
	public List<OutstockItem> listOutstockItem(Page page) {
		return oiMapper.list(page);
	}

	@Override
	public ResultMsg deleteOutstockItemByKey(String id) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int deleteByPrimaryKey = oiMapper.deleteByPrimaryKey(id);
			if(deleteByPrimaryKey == 1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("删除成功");
			} else {
				resultMsg.setCode("0");
				resultMsg.setMsg("删除失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public List<OutstockMain> listOutstockMain(Page page) {
		if(page != null) {
			return outstockMainMapper.list(page);
		} else {
			return outstockMainMapper.list();
		}
	}

	@Override
	public ResultMsg deleteOutstockMainByKey(String outstockMainId) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int deleteByPrimaryKey = outstockMainMapper.deleteByPrimaryKey(outstockMainId);
			if(deleteByPrimaryKey == 1) {
				//如果他下面有详细信息
				if(oiMapper.findCountByOutstockMainId(outstockMainId)>0){
					deleteByPrimaryKey = oiMapper.deleteByOutstockMainId(outstockMainId);
				}
			}
			if(deleteByPrimaryKey >= 1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("删除成功");
			}else{
				resultMsg.setCode("0");
				resultMsg.setMsg("删除失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public List<OutstockItem> listOutstockItemWithOutstockMainId(Page page,String outstockMainId) {
		return oiMapper.listOIWOMI(page,outstockMainId);
	}
	

	@Override
	public List<OutstockItem> findAllItemsList(Page page,String outstockMainId) {
		return oiMapper.findAllList(page,outstockMainId);
	}
	
	@Override
	public List<OutstockItem> findAllProductItems(Page page,String outstockMainId) {
		return outstockProductItemMapper.findAllList(page,outstockMainId);
	}
	@Override
	public List<OutstockMain> findAllList(Page page, OutstockMain outstockMain) {
			return outstockMainMapper.findAllList(page,outstockMain);
	}
	
	
	
	@Override
	public List<OutstockMain> listOutstockMainBySaleOrderId(String id) {
		return outstockMainMapper.listOutstockMainBySaleOrderId(id);
	}

	@Override
	public List<OutstockCountInfo> getClintCountInfo(String outstockMainId) {
		return oiMapper.getClintCountInfo(outstockMainId);
	}
	
	@Override
	public List<OutstockMain> findAllProductOutstock(Page page,OutstockMain outstockMain) {
		return outstockMainMapper.findAllProductOutstock(page, outstockMain);
	}
	
	@Override
	public String findSaleOrderIdsBySaleOutstock(String outstockMainId) {
		return outstockMainMapper.findSaleOrderIdsBySaleOutstock(outstockMainId);
	}

}

package cn.rfidcer.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.bean.InstockLog;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.InstockManagerItem;
import cn.rfidcer.bean.PurchaseInstockAssoc;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.InstockItemMapper;
import cn.rfidcer.dao.InstockLogMapper;
import cn.rfidcer.dao.InstockMainMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.PurchaseInstockAssocMapper;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsStockService;
import cn.rfidcer.service.InstockService;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class InstockServiceImpl implements InstockService {

	private Logger logger = LoggerFactory.getLogger(InstockServiceImpl.class);

	@Autowired
	private InstockItemMapper iiMapper;
	@Autowired
	private InstockMainMapper immapper;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private PurchaseInstockAssocMapper piamapper;
	@Autowired
	private InstockLogMapper instockLogMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private GoodsStockService goodsStockService;
	@Autowired
	private ProductMapper pmapper;
	
	@Autowired
	private TraceCodeService traceCodeService;
	
	@Autowired
	private TraceTypeService traceTypeService;
	
	private ResultMsg addOrUpdateItems(InstockItem instockitem,User user,int rowCount,InstockMain instockMain) {
		logger.info("invoke addOrUpdate ,id is {} ", instockitem.getInstockItemId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			/*
			 * goodsId为空时，首先新建商品
			 */
			if(StringUtils.isEmpty(instockitem.getGoodsId())){
				if(StringUtils.isEmpty(instockitem.getProductId())||StringUtils.isEmpty(instockitem.getProductStandardDetailId())||StringUtils.isEmpty(instockitem.getGoodsBatch())){
					resultMsg.setCode("0");
					resultMsg.setMsg("进货数据异常");
				}else{
					//判断产品ID乱选
					if(instockitem.getProductId()== null || instockitem.getProductId().equals("") || pmapper.selectByPrimaryKey(instockitem.getProductId())==null){
						resultMsg.setCode("0");
						resultMsg.setMsg("第"+rowCount+"行,请选择对应的产品！");
						return  resultMsg;
					}
					
					Goods goods=goodsDao.findByProductidStandarddetailidBatch(instockitem.getProductId(), instockitem.getProductStandardDetailId(), instockitem.getGoodsBatch());
					if(goods==null){
						goods = new Goods();
						goods.setGoodsId(UUIDGenerator.generatorUUID());
						goods.setProductId(instockitem.getProductId());
						goods.setProductStandardDetailId(instockitem.getProductStandardDetailId());
						goods.setGoodsBatch(instockitem.getGoodsBatch());
						goods.setCompanyId(instockMain.getCompanyId());
						goods.setNum(new BigDecimal(instockitem.getInstockNum())); // 商品数量
						CommonImportUtils.setCreateAndUpdateTime(goods, user);
						String strCompanyCode = companyMapper.selectByPrimaryKey(instockMain.getCompanyId()).getCode();
						//设置商品Code
						String goodsCode  = traceCodeService.newTraceCode(instockMain.getCompanyId(), strCompanyCode);
						goods.setCode(goodsCode);
						int res = goodsDao.insertSelective(goods);
						if(res==1){
							traceTypeService.insertTraceType(goodsCode, TraceType.GOODS, user.getId());
						}
					}
					instockitem.setGoodsId(goods.getGoodsId());
				}
			}
			if(goodsDao.getByGoodsId(instockitem.getGoodsId())==null){
				resultMsg.setCode("0");
				resultMsg.setMsg("第："+rowCount+"行,商品需要选择不可以随便输入！");
				return resultMsg;
			}
			if (StringUtils.isEmpty(instockitem.getInstockItemId())) {
				instockitem.setInstockItemId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(instockitem, user);//设置修改日期
				iiMapper.insert(instockitem);
			} else {
				CommonImportUtils.setUpdateTime(instockitem, user);//设置修改日期
				iiMapper.updateByPrimaryKeySelective(instockitem);
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
	public ResultMsg addOrUpdate(InstockMain instockmain,User user) {
		logger.info("invoke addOrUpdate ,id is {} .", instockmain.getInstockMainId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			//保存商品入库单
			if (StringUtils.isEmpty(instockmain.getInstockMainId())) {
				instockmain.setInstockMainId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(instockmain, user);//设置修改日期
				immapper.insert(instockmain);
			} else {
				CommonImportUtils.setUpdateTime(instockmain, user);//设置修改日期
				immapper.updateByPrimaryKeySelective(instockmain);
			}
			
			int  rowCount = 0;//详细列表的行数
			//保存商品入库详细
			for(InstockItem instockitem : instockmain.getInstockitems()){
				rowCount++;
				instockitem.setInstockMainId(instockmain.getInstockMainId());
				resultMsg = addOrUpdateItems(instockitem,user,rowCount,instockmain);
				//添加详细的时候如果遇到错误弹回错误信息
				if(resultMsg.getCode().equals("0"))
				{
					return resultMsg;
				}
				// 插入一段日志记录
				insertInstockMainLog(instockmain, user, instockitem);
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	/**
	 * 插入一段日志记录
	 * @param instockmain
	 * @param user
	 * @param instockitem
	 */
	private void insertInstockMainLog(InstockMain instockmain, User user, InstockItem instockitem) {
		// 插入一段日志记录
		InstockLog instockLog = new InstockLog();
		instockLog.setInstockBatchNum(instockmain.getInstockBatchNum());
		instockLog.setInstockNum(instockmain.getInstockNum());
		instockLog.setGoodsId(instockitem.getGoodsId());
		instockLog.setCompanyId(instockmain.getCompanyId());
		List<InstockLog> ils = instockLogMapper.selectByBean(instockLog);
		InstockLog ilinstance = null;
		boolean isupdate = false;
		if(ils != null && ils.size() > 0) {
			ilinstance = ils.get(0);
			isupdate = true;
		} else {
			ilinstance = new InstockLog();
			ilinstance.setId(UUIDGenerator.generatorUUID());
			ilinstance.setCreateBy(user.getId());
			ilinstance.setCreateTime(new Date());
		}
		ilinstance.setCompanyId(instockmain.getCompanyId());
		ilinstance.setInstockBatchNum(instockmain.getInstockBatchNum());
		ilinstance.setInstockNum(instockmain.getInstockNum());
		ilinstance.setGoodsId(instockitem.getGoodsId());
		ilinstance.setUpdateBy(user.getId());
		ilinstance.setUpdateTime(new Date());
		if(StringUtils.isEmpty(instockitem.getInstockNum())) {
			ilinstance.setNum(new BigDecimal(0));
		} else {
			ilinstance.setNum(new BigDecimal(instockitem.getInstockNum()));
		}
		if(isupdate) {
			instockLogMapper.updateByPrimaryKeySelective(ilinstance);
		} else {
			instockLogMapper.insertSelective(ilinstance);
		}
	}

	@Override
	public List<InstockItem> listInstockItem(Page page) {
		return iiMapper.list(page);
	}



	@Override
	public List<InstockMain> listInstockMain(Page page) {
		if(page != null) {
			return immapper.list(page);
		} else {
			return immapper.list();
		}
	}

	@Override
	public ResultMsg deleteInstockMainByKey(String instockMainId) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int deleteByPrimaryKey = immapper.deleteByPrimaryKey(instockMainId);
			if(deleteByPrimaryKey == 1) {
				//如果他下面有详细信息
				if(iiMapper.findCountByInstockMainId(instockMainId)>0){
					deleteByPrimaryKey = iiMapper.deleteByInstockMainId(instockMainId);
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
	public List<InstockItem> listInstockItem(String instockMainId) {
		return iiMapper.listWidthIMID(instockMainId);
	}
	@Override
	public void adddOrUpdate(InstockMain instockmain, String purchaseids) {
		if(!org.apache.commons.lang3.StringUtils.isEmpty(instockmain.getInstockMainId())) {
			piamapper.deleteByInstockMainId(instockmain.getInstockMainId());
		}
		if (!org.apache.commons.lang3.StringUtils.isEmpty(purchaseids)) {
			String[] split = purchaseids.split(",");
			for (String string : split) {
				PurchaseInstockAssoc record = new PurchaseInstockAssoc();
				record.setInstockMainId(instockmain.getInstockMainId());
				record.setPurchaseOrderId(string);
				piamapper.insertSelective(record);
			}
		}
	}

	@Override
	public List<PurchaseInstockAssoc> listPurchaseOrderAssocByInstockMainId(String instockMainId) {
		return piamapper.selectByInstockMainId(instockMainId);
	}

	@Override
	public List<InstockMain> listQCompanyId(Page page, String companyId) {
		if(org.apache.commons.lang3.StringUtils.isEmpty(companyId)) {
			return listInstockMain(page);
		} else {
			return immapper.listQCompanyId(page,companyId);
		}
	}
	@Override
	public List<InstockMain> findAllList(Page page, InstockMain instockMain) {
			return immapper.findAllList(page,instockMain);

	}

	@Override
	public  ResultMsg addOrUpdateAndUpdateStock(InstockMain instockmain, User user) {
		logger.info("invoke addOrUpdate ,id is {} .", instockmain.getInstockMainId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			//保存商品入库单
			if (StringUtils.isEmpty(instockmain.getInstockMainId())) {
				instockmain.setInstockMainId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(instockmain, user);//设置修改日期
				immapper.insert(instockmain);
			} else {
				CommonImportUtils.setUpdateTime(instockmain, user);//设置修改日期
				immapper.updateByPrimaryKeySelective(instockmain);
			}
			
			int  rowCount = 0;//详细列表的行数
			//保存商品入库详细
			for(InstockItem instockitem : instockmain.getInstockitems()){
				rowCount++;
				instockitem.setInstockMainId(instockmain.getInstockMainId());
				resultMsg = addOrUpdateItems(instockitem,user,rowCount,instockmain);
				//添加详细的时候如果遇到错误弹回错误信息
				if(resultMsg.getCode().equals("0"))
				{
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return resultMsg;
				}
				/*
				 * 更新库存，首先更新商品库存
				 */
				GoodsStock goodsStock = new GoodsStock(instockitem.getGoodsId(),instockmain.getCompanyId());
				goodsStock.setStockNum(new BigDecimal(instockitem.getInstockNum()));
				goodsStock.setProductId(instockitem.getProductId());
				goodsStock.setProductStandardDetailId(instockitem.getProductStandardDetailId());
				goodsStockService.addOrupdateStock( user, OperationType.INSTOCK, goodsStock,true);
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return resultMsg;
	}


	@Override
	public List<InstockManagerItem> findAllList(Page page, InstockManagerItem instockManagerItem) { 		
		return iiMapper.findAllList(page, instockManagerItem);
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.InstockService#getBactchNum(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> getBactchNum(String companyId) {
		List<Map<String, String>> list = immapper.getBactchNum(companyId);
		return list;
	}


}

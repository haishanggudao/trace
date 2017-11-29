package cn.rfidcer.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.ProcessItem;
import cn.rfidcer.bean.ProcessMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.ProcessItemMapper;
import cn.rfidcer.dao.ProcessMainMapper;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.SplitProductService;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

import com.alibaba.fastjson.JSONObject;

@Service
public class SplitProductServiceImpl implements SplitProductService {

	private Logger logger = LoggerFactory.getLogger(SplitProductServiceImpl.class);

	@Autowired
	private ProcessMainMapper processMainMapper;

	@Autowired
	private ProcessItemMapper processItemMapper;

	@Autowired
	private GoodsDao goodsDao;

	
	@Autowired
	private CompanyMapper companyMapper;


	@Autowired
	private TraceCodeService traceCodeService;
	
	@Autowired
	private TraceTypeService traceTypeService;


	@Override
	public ResultMsg delSplit(ProcessMain processMain) {
		String toDeleteProcessMainId = processMain.getProcessMainId();
		logger.info("删除加工记录ID：" + toDeleteProcessMainId);

		ResultMsg resultMsg = new ResultMsg();
		int res = 0;

		try {
			// action: delete the items of process
			processItemMapper.deleteByMainid(toDeleteProcessMainId);

			// action: delete the main of process
			res = processMainMapper.deleteByPrimaryKey(toDeleteProcessMainId);
			resultMsg.setCode(res + "");

			if (res == 1) {
				resultMsg.setMsg("删除成功");
			} else {
				resultMsg.setMsg("删除失败");
			}

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前记录无法删除");
		}
		return resultMsg;
	}
	@Override
	public ResultMsg delSplitItem(ProcessItem processItem) {
		logger.info("删除加工记录明细ID：" + processItem.getProcessItemId());

		ResultMsg resultMsg = new ResultMsg();
		int res = 0;

		try {
			res =processItemMapper.deleteByPrimaryKey(processItem.getProcessItemId());
			resultMsg.setCode(res + "");
			if (res == 1) {
				resultMsg.setMsg("删除记录明细成功");
			} else {
				resultMsg.setMsg("删除记录明细失败");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前记录明细无法删除");
		}

		return resultMsg;
	}
	
	
	


	@Override
	public ResultMsg createOrUpdateSplitProduct(ProcessMain processMain, String processDetail, User user) {
		ResultMsg msg = new ResultMsg();
		int res = 0;
		String info = null;
		String myMainId = processMain.getProcessMainId();
		Goods myInsertedGoods = processMain.getGoods();
		Goods myGoods = goodsDao.findByProductidStandarddetailidBatch(myInsertedGoods.getProductId(),
				myInsertedGoods.getProductStandardDetailId(), myInsertedGoods.getGoodsBatch());
		processMain.setGoodsId(myGoods.getGoodsId());
		if (StringUtils.isEmpty(processMain.getProcessMainId())) {
			info="新增拆分管理";
			myMainId= UUIDGenerator.generatorUUID();
			processMain.setProcessMainId(myMainId);
			CommonImportUtils.setCreateAndUpdateTime(processMain, user);
			if (StringUtil.isBlank(myGoods)) {
				msg.setCode("0");
				msg.setMsg("商品批次对应的商品找不到！");
				return msg;
			} 
			
			processMain.setType("1");
			try {
				if (processMain.getProcessTimeStr() == null || processMain.getProcessTimeStr().isEmpty()) {
					processMain.setProcessTime(new Timestamp(System.currentTimeMillis()));
				} else {
					processMain.setProcessTime(DateUtil.parse(processMain.getProcessTimeStr()));
				}
			} catch (ParseException e) {
				logger.error("DateUtil parse ",e);
			}

			res = processMainMapper.insertSelective(processMain);
		}else{
			info="修改拆分管理";
			CommonImportUtils.setUpdateTime(processMain, user);
			//添加拆分日期
			try {
				processMain.setProcessTime(DateUtil.parse(processMain.getProcessTimeStr()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			res=processMainMapper.updateByPrimaryKeySelective(processMain);
		}
		if (res == 1) {
			//processItemMapper.deleteByMainid(myMainId);
			List<ProcessItem> parseArray = JSONObject.parseArray(processDetail, ProcessItem.class);
			int itemCount = 0 ;
			for (ProcessItem processItem : parseArray) {
				itemCount ++;
				String productId = processItem.getProductId();
				String productStandardDetailId = processItem.getProductStandardDetailId();
				String goodsBatch = processItem.getGoodsBatch();
				if(StringUtil.isBlank(goodsBatch)){
					msg.setCode("0");
					msg.setMsg("第"+itemCount+"行详细里缺少批次号码,请输入批次号码");
					return msg;
				}
				
				/*
				 * 同一个商品只能有一个拆分明细
				 */
				
				Goods goods=goodsDao.findByProductidStandarddetailidBatch(productId, productStandardDetailId, goodsBatch);
				if(goods==null){
					/*
					 * 新建商品
					 */
					goods = new Goods();
					goods.setGoodsId(UUIDGenerator.generatorUUID());
					String companyId = processMain.getCompanyId();
					goods.setCompanyId(companyId);
					goods.setProductId(productId);
					goods.setProductStandardDetailId(productStandardDetailId);
					goods.setGoodsBatch(goodsBatch);
					goods.setNum(processItem.getNum());
					goods.setAreaInfoId(myGoods.getAreaInfoId());
					goods.setSlaughterhouseId(myGoods.getSlaughterhouseId());
					CommonImportUtils.setCreateAndUpdateTime(goods, user);
					String strCompanyCode = companyMapper.selectByPrimaryKey(companyId).getCode();
					//设置商品Code
					String goodsCode  = traceCodeService.newTraceCode(companyId, strCompanyCode);
					goods.setCode(goodsCode);
					int goodsRes = goodsDao.insertSelective(goods);
					if(goodsRes==1){
						traceTypeService.insertTraceType(goodsCode, TraceType.GOODS, user.getId());
					}
				}
				processItem.setGoodsId(goods.getGoodsId());
				if(StringUtil.isBlank(processItem.getProcessItemId())){
					CommonImportUtils.setCreateAndUpdateTime(processItem, user);//设置修改时间和创建时间
					processItem.setProcessItemId(UUIDGenerator.generatorUUID());
					processItem.setProcessMainId(myMainId);
					int count=processItemMapper.checkCountByGoodsId(processItem);
					if(count!=0){
						msg.setCode("-3");
						msg.setMsg("该商品已被拆分过");
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return msg;
					}else{
						processItemMapper.insertSelective(processItem);
					}
				}else{
					int count=processItemMapper.checkCountByGoodsId(processItem);
					if(count!=0){
						msg.setCode("-3");
						msg.setMsg("该商品已被拆分过");
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return msg;
					}else{
						CommonImportUtils.setUpdateTime(processItem, user);
						processItemMapper.updateByPrimaryKeySelective(processItem);
					}
					
				}
				
			}
			msg.setCode("1");
			msg.setMsg(info + "成功");
		}
		return msg;
	}
	@Override
	public List<ProcessItem> getSplitItemList(Page page, ProcessMain processMain) {
		return processItemMapper.findProcessItemsByMainid(page, processMain.getProcessMainId());
	}
	@Override
	public List<ProcessMain> getSplitProductList(Page page, ProcessMain processMain) {
		return processMainMapper.getProcessList(page, processMain);
	}

}

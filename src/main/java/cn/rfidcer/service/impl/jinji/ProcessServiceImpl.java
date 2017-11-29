package cn.rfidcer.service.impl.jinji;

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
import cn.rfidcer.bean.ProcessTemplateItem;
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.ProcessItemMapper;
import cn.rfidcer.dao.ProcessMainMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsService;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.service.jinji.ProcessService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.UUIDGenerator;

import com.alibaba.fastjson.JSONObject;

@Service
public class ProcessServiceImpl implements ProcessService {

	private Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

	@Autowired
	private ProcessMainMapper processMainMapper;

	@Autowired
	private ProcessItemMapper processItemMapper;

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private ProductMapper productDao;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private TraceCodeService traceCodeService;
	
	@Autowired
	private TraceTypeService traceTypeService;
	

	@Override
	public List<ProcessMain> getProcessList(Page page, ProcessMain processMain) {
		return processMainMapper.getProcessList(page, processMain);
	}

	@Override
	public List<ProcessItem> getProcessItemList(Page page, ProcessMain processMain) {
		return processItemMapper.findProcessItemsByMainid(page, processMain.getProcessMainId());
	}
	
	@Override
	public ResultMsg createOrUpdateProcess(ProcessMain processMain, String processDetail, User user) {
		ResultMsg msg = new ResultMsg();
		int res = 0;
		String info = null;
		CommonImportUtils.setUpdateTime(processMain, user);
		String myMainId = "";

		if (StringUtils.isEmpty(processMain.getProcessMainId())) {
			info = "新增加工记录";
			String myProcessMainUUID = UUIDGenerator.generatorUUID();
			myMainId = myProcessMainUUID;
			processMain.setProcessMainId(myProcessMainUUID);
			CommonImportUtils.setCreateAndUpdateTime(processMain, user);
			Goods myInsertedGoods = processMain.getGoods();
			if (processMain.getGoods().getGoodsBatch() == null || processMain.getGoods().getGoodsBatch().isEmpty()) {
				/*
				 * 默认可以不填写商品批次号
				 */
				myInsertedGoods.setGoodsBatch(goodsService.getGoodsBatchNo());
			}
			// 检查该商品是否存在
			int countGoods = goodsDao.findCountGoods(myInsertedGoods);
			// 若不存在则新增
			if (countGoods == 0) {
				String myInsertedGoodsUUID = UUIDGenerator.generatorUUID();
				myInsertedGoods.setGoodsId(myInsertedGoodsUUID); // 新增商品ID
				myInsertedGoods.setProcessMainId(myProcessMainUUID); // 加工ID
				myInsertedGoods.setNum(processMain.getProcessQuantity()); // 商品数量
				Product myProduct = productDao.selectByPrimaryKey(myInsertedGoods.getProductId());
				String companyId = myProduct.getCompanyId();
				myInsertedGoods.setCompanyId(companyId); // 企业ID
				CommonImportUtils.setCreateAndUpdateTime(myInsertedGoods, user);//设置修改时间和创建时间
				//设置商品Code
				String goodsCode  = traceCodeService.newTraceCode(companyId);
				myInsertedGoods.setCode(goodsCode);
				res=goodsDao.insertSelective(myInsertedGoods);
				if(res==1){
					traceTypeService.insertTraceType(goodsCode, TraceType.GOODS, user.getId());
				}
				processMain.setGoodsId(myInsertedGoodsUUID);
			} else {
				Goods myGoods = goodsDao.findByProductidStandarddetailidBatch(myInsertedGoods.getProductId(),
						myInsertedGoods.getProductStandardDetailId(), myInsertedGoods.getGoodsBatch());
				processMain.setGoodsId(myGoods.getGoodsId());
			}

			try {
				if (processMain.getProcessTimeStr() == null || processMain.getProcessTimeStr().isEmpty()) {
					processMain.setProcessTime(new Timestamp(System.currentTimeMillis()));
				} else {
					processMain.setProcessTime(DateUtil.parse(processMain.getProcessTimeStr()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("DateUtil parse ");
			}

			// action: insert a new processMain
			res = processMainMapper.insertSelective(processMain);
		} else {
			info = "修改加工记录";
			// case: update
			String toBeUpdatedProcessMainId = processMain.getProcessMainId();
			myMainId = toBeUpdatedProcessMainId;
			processItemMapper.deleteByMainid(toBeUpdatedProcessMainId);
			res = processMainMapper.updateByPrimaryKeySelective(processMain);
		}

		if (res == 1) {
			processItemMapper.deleteByMainid(myMainId);
			List<ProcessTemplateItem> parseArray = JSONObject.parseArray(processDetail, ProcessTemplateItem.class);
			for (ProcessTemplateItem processTemplateItem : parseArray) {
				ProcessItem processItem = new ProcessItem();
				processItem.setProcessItemId(UUIDGenerator.generatorUUID());
				processItem.setProcessMainId(myMainId);
				CommonImportUtils.setCreateAndUpdateTime(processItem, user);//设置修改时间和创建时间
				processItem.setGoodsId(processTemplateItem.getGoodsId());
				processItem.setNum(processTemplateItem.getNum());
				processItem.setType(processTemplateItem.getType());
				processItemMapper.insertSelective(processItem);
			}
			msg.setCode("1");
			msg.setMsg(info + "成功");
		}
		return msg;
	}


	@Override
	public ResultMsg delProcess(ProcessMain processMain) {
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
	
}

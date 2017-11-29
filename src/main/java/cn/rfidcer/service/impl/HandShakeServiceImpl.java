package cn.rfidcer.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.DeliveryQRCode;
import cn.rfidcer.bean.GoodQRCode;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.GoodsInfo;
import cn.rfidcer.bean.GoodsQC;
import cn.rfidcer.bean.InstockInfo;
import cn.rfidcer.bean.Instockqc;
import cn.rfidcer.bean.OutstockInfo;
import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ProcessItem;
import cn.rfidcer.bean.ProcessMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.Slaughterhouse;
import cn.rfidcer.bean.Trace;
import cn.rfidcer.bean.TraceInfo;
import cn.rfidcer.bean.UserToken;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.GoodsQCMaterials;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.CustomersMapper;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.GoodsDetailDao;
import cn.rfidcer.dao.InstockqcMapper;
import cn.rfidcer.dao.OutstockItemMapper;
import cn.rfidcer.dao.OutstockMainMapper;
import cn.rfidcer.dao.SaleItemMapper;
import cn.rfidcer.dao.SaleOrderMapper;
import cn.rfidcer.dao.TraceColumnsDao;
import cn.rfidcer.dao.TraceDao;
import cn.rfidcer.dao.TraceLogMapper;
import cn.rfidcer.dao.TraceMapper;
import cn.rfidcer.dao.yz.StoreSaleItemMapper;
import cn.rfidcer.dao.yz.StoreSaleOrderMapper;
import cn.rfidcer.enums.TraceInfoType;
import cn.rfidcer.service.HandShakeService;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.MyStringUtls;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.TokenCacheUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class HandShakeServiceImpl implements HandShakeService {

	private Logger logger = LoggerFactory.getLogger(HandShakeServiceImpl.class);

	@Autowired
	private OutstockMainMapper ommapper;

	@Autowired
	private OutstockItemMapper itemMapper;

	@Autowired
	private SaleOrderMapper saleOrderMapper;

	@Autowired
	private SaleItemMapper saleItemMapper;

	@Autowired
	private TokenCacheUtil tokenCacheUtil;
	
	@Autowired
	private InstockqcMapper instockqcMapper;

	@Autowired
	private TraceDao traceDao;
	
	@Autowired
	private TraceMapper traceMapper;

	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private TraceColumnsDao columnsDao;
	@Autowired
	private TraceLogMapper traceLogMapper;
	
	@Autowired
	private CommonVariableMapper commonVariableMapper;
	
	@Autowired
	private StoreSaleItemMapper storeSaleItemMapper;
	
	@Autowired
	private StoreSaleOrderMapper storeSaleOrderMapper;
	
	@Autowired
	private GoodsDetailDao goodsDetailDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private CustomersMapper customersMapper;

	@Override
	public ResultMsg updateDeliveryOrder(String orderId, UserToken userToken) {
		logger.info("确认出库单ID:" + orderId);
		ResultMsg returnMsg = null;
		if (tokenCacheUtil.compareUserTokenFromCache(userToken)) {
			OutstockMain record = new OutstockMain();
			record.setOutstockMainId(orderId);
			String today = DateUtil.getToday();
			record.setOutstockDate(today);
			record.setStatus("1");
			int res = ommapper.updateByPrimaryKeySelective(record);
			returnMsg = ResultMsgUtil.getReturnMsg(res, "确认配送单");
			returnMsg.setTime(today);
		} else {
			returnMsg = new ResultMsg();
			returnMsg.setCode("-1");
			returnMsg.setMsg("Token过期,请重新登录");
		}
		return returnMsg;
	}

	@Override
	public ResultMsg addDeliveryOrderAndGoodOrderRelation(DeliveryQRCode deliveryQRCode, String username,
			String token) {
		logger.info("关联出库明细：" + deliveryQRCode);
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		if (tokenCacheUtil.compareUserTokenFromCache(new UserToken(username, token))) {
			List<GoodQRCode> goodList = deliveryQRCode.getGoodList();
			if (deliveryQRCode.getClass() == null || deliveryQRCode.getDeliveryQRCode().trim().length() == 0) {
				code = "2";
				msg = "无出库单信息";
			} else if (goodList == null || goodList.isEmpty()) {
				code = "2";
				msg = "无出库单信息";
			} else {
				try {
					for (GoodQRCode goodQRCode : goodList) {
						/*
						 * 未查到出库明细，新增出库明细 查询到已有出库明细，更新配送时间，更改关联的出库单
						 */
						String QRCode = MyStringUtls.subQRCode(goodQRCode.getGoodQRCodeId());
						/*
						 * 先查团餐
						 */
						OutstockItem item = itemMapper.getOutstockItemByGoodsQRCode(QRCode);
						Timestamp createTime = new Timestamp(System.currentTimeMillis());
						if(item==null){
							item=itemMapper.getOutstockItemByQRCode(QRCode);
							if(item==null){
								code = "-3";
								msg = "无数据";
							}else{
								/*
								 * 非团餐
								 */
								if(item.getDeliverType().equals("0")){
									if (StringUtils.isEmpty(item.getOutstockItemId())) {
										OutstockItem outstockItem = new OutstockItem();
										outstockItem.setOutstockItemId(UUIDGenerator.generatorUUID());
										outstockItem.setOutstockMainId(deliveryQRCode.getDeliveryQRCode());
										outstockItem.setGoodsId(item.getGoodsDetailId());
										outstockItem.setCreateBy(username);
										outstockItem.setCreateTime(createTime);
										outstockItem.setUpdateBy(username);
										outstockItem.setUpdateTime(createTime);
										outstockItem.setDeliveryBy(username);
										outstockItem.setDeliveryTime(createTime);
										itemMapper.insertSelective(outstockItem);
										code = "1";
										msg = "确认成功";
									} else {
										item.setDeliveryBy(username);
										item.setDeliveryTime(createTime);
										item.setOutstockMainId(deliveryQRCode.getDeliveryQRCode());
										item.setGoodsId(null);
										itemMapper.updateByPrimaryKeySelective(item);
										code = "1";
										msg = "确认成功";
									}
								}else{
									code = "-3";
									msg = "非团餐配送单";
								}
							}
						}else{
							if(item.getDeliverType().equals("0")){
								code = "-3";
								msg = "非团餐类型";
							}else{
								if(item.getOutstockMainId()!=null&&item.getOutstockMainId().equals(deliveryQRCode.getDeliveryQRCode())){
									CommonVariable variable = commonVariableMapper.selectByPrimaryKey(item.getDeliverType());
									if(variable!=null&&variable.getVarValue().equals("团餐")){
										item.setDeliveryBy(username);
										item.setDeliveryTime(createTime);
										itemMapper.updateByPrimaryKeySelective(item);
										code = "1";
										msg = "确认成功";
									}else{
										code="-5";
										msg="非团餐类型";
									}
								}else{
									code="-4";
									msg="商品与出库单不匹配";
								}
							}

							}
					}
				} catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					code = "-2";
					msg = "确认异常";
				}
			}
		} else {
			code = "-1";
			msg = "Token过期,请重新登录";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public ResultMsg querySaleOrder(String outstockMainId, UserToken userToken) {
		logger.info("查询销售单信息: " + outstockMainId);
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		if (tokenCacheUtil.compareUserTokenFromCache(userToken)) {
			OutstockMain myOutstockMain = ommapper.selectByPrimaryKey(outstockMainId);
			if (myOutstockMain != null) {
				SaleOrder mySaleOrder = saleOrderMapper.selectByPrimaryKey(myOutstockMain.getSaleOrderId());

				SaleItem mySaleItem = new SaleItem();
				mySaleItem.setSaleOrderId(mySaleOrder.getSaleOrderId());

				List<SaleItem> saleItems = saleItemMapper.findAll(null, mySaleItem);
				mySaleOrder.setSaleItems(saleItems);
				resultMsg.setBaseEntity(mySaleOrder);
				code = "1";
			} else {
				code = "2";
				msg = "无数据";
			}
		} else {
			code = "-1";
			msg = "Token过期,请重新登录";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public ResultMsg getTraceInfo(String qrAddress) {
		logger.info("查询追溯信息，追溯码：{}", qrAddress);
		/*
		 * 先根据明细二维码获取商品信息
		 */
		GoodsInfo goodsInfo = traceDao.getGoodsByDetailCode(qrAddress, null);
		if (goodsInfo == null) {
			/*
			 * 根据明细查不到信息时，再根据商品二维码查询商品信息
			 */
			goodsInfo = traceDao.getGoodsByGoodsCode(qrAddress, null);
		}
		TraceInfo traceInfo = new TraceInfo();
		ResultMsg resultMsg = new ResultMsg();
		String code = null;
		String msg = null;
		if (goodsInfo == null) {
			code = "0";
			msg = "无数据";
		} else {
			code = "1";
			traceInfo.setGoodsInfo(goodsInfo);
			GoodsQC QCInfo = traceDao.getGoodsQCByQRCode(goodsInfo.getGoodsId(), null);
			traceInfo.setGoodsQC(QCInfo);
			ProcessMain processMain = traceDao.getProcessInfoByGoodsId(goodsInfo.getGoodsId(), null);
			traceInfo.setProcessMain(processMain);
			OutstockInfo outstockInfo = traceDao.getOutStockMainInfoByGoodsId(goodsInfo.getGoodsDetailId(), null);
			traceInfo.setOutstockInfo(outstockInfo);
			InstockInfo instockInfo = traceDao.getInstockInfoByGoodsId(goodsInfo.getGoodsId(), null);
			traceInfo.setInstockInfo(instockInfo);
			/*
			 * 屠宰场信息
			 */
			if (goodsInfo.getSlaughterhouseId() != null) {
				Slaughterhouse slaughterhouseInfo = traceDao.getSlaughterhouseInfoById(goodsInfo.getSlaughterhouseId(),
						null);
				traceInfo.setSlaughterhouse(slaughterhouseInfo);
			}
			/*
			 * 零售企业信息
			 */
			Company company = companyMapper.selectByPrimaryKey(goodsInfo.getCompanyId());
			company.setCode(qrAddress);
			traceInfo.setCompany(company);
			resultMsg.setBaseEntity(traceInfo);
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public TraceInfo getTraceInfoByCode(String qrAddress) {
		logger.info("查询追溯信息，追溯码：{}", qrAddress);
		if (StringUtils.isEmpty(qrAddress) || qrAddress.trim().length() < 9) {
			return null;
		} else {
			TraceInfo traceInfo = new TraceInfo();
			GoodsInfo goodsInfo=null;
			String companyCode=null;
			String goodsInfoColumns=null;
			String traceCode=qrAddress;
			/*
			 * 查询追溯码类型
			 */
			Trace traceType = traceMapper.selectByPrimaryKey(qrAddress);
			if(traceType==null){
				return null;
			}
			switch (traceType.getType()) {
			case 0://商品追溯码
				/*
				 * 新增根据前九位企业编码区分显示追溯字段的逻辑
				 */
				companyCode = qrAddress.substring(0, 9);
				/*
				 * 查询企业配置的商品信息字段
				 */
				goodsInfoColumns = columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.goodsInfo);
				/*
				 * 查询商品信息
				 */
				goodsInfo = traceDao.getGoodsByGoodsCode(qrAddress, goodsInfoColumns);
				break;
				
			case 1://非团餐商品明细追溯码
				/*
				 * 新增根据前九位企业编码区分显示追溯字段的逻辑
				 */
				companyCode = qrAddress.substring(0, 9);
				goodsInfo = getGoodsDetailTraceInfo(qrAddress, traceInfo,companyCode,true);
				break;
				
			case 2://团餐商品明细追溯码
				
				companyCode = qrAddress.substring(0, 9);
				goodsInfo = getGoodsDetailTraceInfo(qrAddress, traceInfo,companyCode,false);
				break;
				
			case 3://销售单商品追溯码
				/*
				 *查询销售明细信息
				 */
				StoreSaleItem storeSaleItem = new StoreSaleItem();
				storeSaleItem.setGoodsTraceCode(qrAddress);
				storeSaleItem = storeSaleItemMapper.selectOne(storeSaleItem);
				if(storeSaleItem!=null){
					/*
					 * 销售单信息
					 */
					StoreSaleOrder storeSaleOrder = new StoreSaleOrder();
					storeSaleOrder.setStoreSaleOrderId(storeSaleItem.getStoreSaleOrderId());
					storeSaleOrder=storeSaleOrderMapper.selectByPrimaryKey(storeSaleOrder);
					if(storeSaleOrder!=null){
						GoodsDetail goodsDetail = goodsDetailDao.getGoodsByBarCode(storeSaleItem.getGoodsBarCode());
						if(goodsDetail!=null){
							traceCode=goodsDetail.getCode();
							traceInfo.setStoreSaleItem(storeSaleItem);
							traceInfo.setStoreSaleOrder(storeSaleOrder);
						}
						if(storeSaleOrder.getCompanyId()!=null){
							/*
							 * 客户信息
							 */
							Customers customers = customersMapper.getCustomersByCustomerCompanyId(storeSaleOrder.getCompanyId());
							traceInfo.setCustomers(customers);
						}
					}
				}
				goodsInfo = getGoodsDetailTraceInfo(traceCode, traceInfo,companyCode,true);
				break;
			default:
				break;
			}
			
			if (goodsInfo != null) {
				/*
				 * 更新追溯查询次数
				 */
				getQCInstockSlaughterInfo(traceInfo, goodsInfo, companyCode,qrAddress);
				traceInfo.setGoodsInfo(goodsInfo);
				traceDao.updateGoodsTraceCount(goodsInfo);
				goodsInfo.setTraceCount(goodsInfo.getTraceCount() + 1);
			}
			return traceInfo;
		}

	}

	private GoodsInfo getGoodsDetailTraceInfo(String qrAddress,
			TraceInfo traceInfo, String companyCode,boolean flag) {
		GoodsInfo goodsInfo=null;
		/*
		 * 查询企业配置的商品信息字段
		 */
		String goodsInfoColumns = columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.goodsInfo);
		/*
		 * 先根据明细二维码获取商品信息
		 */
		 goodsInfo = traceDao.getGoodsByDetailCode(qrAddress, goodsInfoColumns);
		if(goodsInfo!=null){
			/*
			 * 出库信息
			 */
			 String outstockId=null;
			 if(flag){
				 outstockId=goodsInfo.getGoodsDetailId();
			 }else{
				 outstockId=goodsInfo.getGoodsId();
			 }
			 OutstockInfo outstockInfo = traceDao.getOutStockMainInfoByGoodsId(outstockId,
						columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.outStockInfo));
				traceInfo.setOutstockInfo(outstockInfo);
		}
		return goodsInfo;
	}

	private void getQCInstockSlaughterInfo(TraceInfo traceInfo,	GoodsInfo goodsInfo, String companyCode,String qrAddress) {
		/*
		 * 先查找拆分信息
		 */
		ProcessMain processMain =traceDao.getProcessSplitByGoodsId(goodsInfo.getGoodsId(), 
				columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.processSplitInfo));
		String processGoodsId=goodsInfo.getGoodsId();
		String processSlaughterhouseId=goodsInfo.getSlaughterhouseId();
		if(processMain==null){
			/*
			 * 拆分信息为空时，再查找加工信息
			 */
			processMain = traceDao.getProcessInfoByGoodsId(goodsInfo.getGoodsId(), 
					columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.processMainInfo));
			if(processMain!=null){
				List<ProcessItem> myProcessItems =processMain.getProcessItems();
				for (int i = 0; i < myProcessItems.size(); i++) {
					ProcessItem myProcessItem = myProcessItems.get(i);
					InstockInfo myInstockInfo = traceDao.getInstockInfoByGoodsId(myProcessItem.getGoodsId(),
							columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.inStockInfo));
					// System.out.println(myInstockInfo);
					GoodsInfo myGoodsInfo = traceDao.getGoodsByGoodsId(myProcessItem.getGoodsId());
					// System.out.println(myGoodsInfo);
					myProcessItem.setSupplier(myInstockInfo.getSupplier());
					myProcessItem.setAreaName(myGoodsInfo.getAreaName());
					myProcessItem.setOrigin(myInstockInfo.getOrigin());
					/*
					 * 原料质检信息
					 */
					GoodsQCMaterials materialsQCInfo = traceDao.getGoodsQCMaterialsByGoodsId(myProcessItem.getGoodsId());
					myProcessItem.setMaterialsQCInfo(materialsQCInfo);
				}
			}
		}else{
			/*
			 * 有拆分信息时，入库信息使用拆分前的商品的入库信息,屠宰场信息使用拆分前的商品的屠宰场信息
			 */
			Goods goods = processMain.getGoods();
			if(goods!=null){
				processGoodsId=goods.getGoodsId();
				processSlaughterhouseId=goods.getSlaughterhouseId();
			}
		}
		/*
		 * 入库信息
		 */
		InstockInfo instockInfo = traceDao.getInstockInfoByGoodsId(processGoodsId,
				columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.inStockInfo));
		traceInfo.setInstockInfo(instockInfo);
		traceInfo.setProcessMain(processMain);
		/*
		 * 出库信息
		 */
		if(goodsInfo!=null){
			/*
			 * 出库信息
			 */
			 String outstockId=null;
				 outstockId=goodsInfo.getGoodsId();
			 OutstockInfo outstockInfo = traceDao.getOutStockMainInfoByGoodsId(outstockId,
						columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.outStockInfo));
				traceInfo.setOutstockInfo(outstockInfo);
				/*
				 *查询门店信息 - - 
				 */
				if(!StringUtils.isEmpty(outstockInfo)){
				if(!StringUtils.isEmpty(outstockInfo.getOutstockMainId())){
					Customers customers = customersMapper.getCustomersByOutstockMainId(outstockInfo.getOutstockMainId());
					traceInfo.setCustomers(customers);
				}
				}
			
		}
		/*
		 * 质检信息
		 */
		GoodsQC QCInfo = traceDao.getGoodsQCByQRCode(goodsInfo.getGoodsId(),
				columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.goodsQC));
		traceInfo.setGoodsQC(QCInfo);
		/*
		 * 商品质检信息
		 */
		GoodsQCMaterials goodsQCInfo = traceDao.getGoodsQCMaterialsByGoodsId(goodsInfo.getGoodsId());
		traceInfo.setGoodsQCInfo(goodsQCInfo);
		
		/*
		 * 质检报告图片
		 */
		if(instockInfo!=null&&instockInfo.getInstockMainId()!=null){
			Instockqc instockqc = new Instockqc();
			instockqc.setInstockMainId(instockInfo.getInstockMainId());
			instockqc.setGoodsId(goodsInfo.getGoodsId());
			Instockqc qcReport = instockqcMapper.getQCReportByGoodsIdAndInstockMainId(instockqc);
			traceInfo.setYzInstockqc(qcReport);
		}
		/*
		 * 屠宰场信息
		 */
		if (processSlaughterhouseId != null) {
			Slaughterhouse slaughterhouseInfo = traceDao.getSlaughterhouseInfoById(processSlaughterhouseId,
					columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.slaughterhouseInfo));
			traceInfo.setSlaughterhouse(slaughterhouseInfo);
		}
		/*
		 * 企业信息
		 */
		Company company = traceDao.getTraceCompanyInfoById(goodsInfo.getCompanyId(),
				columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.companyInfo));
		if (company != null) {
			company.setCode(qrAddress);
		}
		traceInfo.setCompany(company);
	}

	public String getTracePageByCompany(String companyId) {
		String page ="traceWeChar";
		if(companyId!=null){
			Company company = companyMapper.selectByPrimaryKey(companyId);
			
			switch (company.getCode()) {
			case "310118954":
				page="yz/trace";
				break;
			case "310117743":
				page="tracePage/trace_mfsy";
				break;
			case "310106807":
				page="byz/trace";
				break;
			case "310115443":
				page="fruit/trace";
				break;
			case "310115604":
				page="fruit/trace";
				break;
			case "310230817":
				page="traceWeChar";
				break; 
			default:
				page = "trace";
				break;
			}
		}
		return page;
	}
	
	/**
	 * 根据 ProductStandardDetailId 来得到 GoodsDetail 的追溯码; created by jie.jia 
	 * @param productStandardDetailId
	 * @return
	 */
	private String getGoodsDetailCodeByProductStandardDetailId(String productStandardDetailId){
		String goodsDetailCode = "";
		
		// DAO action: select Goods by StandardDetailId
		List<Goods> myGoodsList = goodsDao.findGoodsByStandardDetailId(productStandardDetailId); 
		
		if (myGoodsList.size() > 0) {
			Goods myFirstGoods = myGoodsList.get(0);
			// DAO action: select goodsDetail by goodsId
			List<GoodsDetail> myGoodsDetailLists = goodsDetailDao.getGoodsDetailsByGoodsId(myFirstGoods.getGoodsId());
			
			if (myGoodsDetailLists.size() > 0) {
				GoodsDetail myFirstGoodsDetail = myGoodsDetailLists.get(0);
				goodsDetailCode = myFirstGoodsDetail.getCode();
			} 
		}
		return goodsDetailCode;
	}

	@Override
	public TraceInfo getTraceInfoByProductStandardDetailId(String productStandardDetailId) {
		logger.info("查询追溯信息，productStandardDetailId：{}", productStandardDetailId);
		
		// call method: 根据 ProductStandardDetailId 来得到 GoodsDetail 的追溯码
		String qrAddress = getGoodsDetailCodeByProductStandardDetailId(productStandardDetailId); 
		
		logger.info("查询追溯信息，追溯码：{}", qrAddress);
		
		if (StringUtils.isEmpty(qrAddress) || qrAddress.trim().length() < 9) {
			return null;
		} else {
			TraceInfo traceInfo = new TraceInfo();
			GoodsInfo goodsInfo=null;
			String companyCode=null;
			String goodsInfoColumns=null;
			String traceCode=qrAddress;
			/*
			 * 查询追溯码类型
			 */
			Trace traceType = traceMapper.selectByPrimaryKey(qrAddress);
			if(traceType==null){
				return null;
			}
			switch (traceType.getType()) {
			case 0://商品追溯码
				/*
				 * 新增根据前九位企业编码区分显示追溯字段的逻辑
				 */
				companyCode = qrAddress.substring(0, 9);
				/*
				 * 查询企业配置的商品信息字段
				 */
				goodsInfoColumns = columnsDao.getTraceColumnsByCompanyCodeAndType(companyCode, TraceInfoType.goodsInfo);
				/*
				 * 查询商品信息
				 */
				goodsInfo = traceDao.getGoodsByGoodsCode(qrAddress, goodsInfoColumns);
				break;
				
			case 1://非团餐商品明细追溯码
				/*
				 * 新增根据前九位企业编码区分显示追溯字段的逻辑
				 */
				companyCode = qrAddress.substring(0, 9);
				goodsInfo = getGoodsDetailTraceInfo(qrAddress, traceInfo,companyCode,true);
				break;
				
			case 2://团餐商品明细追溯码
				
				companyCode = qrAddress.substring(0, 9);
				goodsInfo = getGoodsDetailTraceInfo(qrAddress, traceInfo,companyCode,false);
				break;
				
			case 3://销售单商品追溯码
				/*
				 *查询销售明细信息
				 */
				StoreSaleItem storeSaleItem = new StoreSaleItem();
				storeSaleItem.setGoodsTraceCode(qrAddress);
				storeSaleItem = storeSaleItemMapper.selectOne(storeSaleItem);
				if(storeSaleItem!=null){
					/*
					 * 销售单信息
					 */
					StoreSaleOrder storeSaleOrder = new StoreSaleOrder();
					storeSaleOrder.setStoreSaleOrderId(storeSaleItem.getStoreSaleOrderId());
					storeSaleOrder=storeSaleOrderMapper.selectByPrimaryKey(storeSaleOrder);
					if(storeSaleOrder!=null){
						GoodsDetail goodsDetail = goodsDetailDao.getGoodsByBarCode(storeSaleItem.getGoodsBarCode());
						if(goodsDetail!=null){
							traceCode=goodsDetail.getCode();
							traceInfo.setStoreSaleItem(storeSaleItem);
							traceInfo.setStoreSaleOrder(storeSaleOrder);
						}
						if(storeSaleOrder.getCompanyId()!=null){
							/*
							 * 客户信息
							 */
							Customers customers = customersMapper.getCustomersByCustomerCompanyId(storeSaleOrder.getCompanyId());
							traceInfo.setCustomers(customers);
						}
					}
				}
				goodsInfo = getGoodsDetailTraceInfo(traceCode, traceInfo,companyCode,true);
				break;
			default:
				break;
			}
			
			if (goodsInfo != null) {
				/*
				 * 更新追溯查询次数
				 */
				getQCInstockSlaughterInfo(traceInfo, goodsInfo, companyCode,qrAddress);
				traceInfo.setGoodsInfo(goodsInfo);
				traceDao.updateGoodsTraceCount(goodsInfo);
				goodsInfo.setTraceCount(goodsInfo.getTraceCount() + 1);
			}
			return traceInfo;
		}
	}


}

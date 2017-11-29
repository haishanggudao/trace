package cn.rfidcer.service.impl.yz;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.DeliveryQRCode;
import cn.rfidcer.bean.GoodQRCode;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.SaleOutstock;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.UserToken;
import cn.rfidcer.bean.yz.StockOrderInfo;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.yz.YzGoodsInfo;
import cn.rfidcer.dao.CustomersMapper;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.GoodsDetailDao;
import cn.rfidcer.dao.InstockItemMapper;
import cn.rfidcer.dao.InstockMainMapper;
import cn.rfidcer.dao.OutstockItemMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.ProductStandardDetailDao;
import cn.rfidcer.dao.PurchaseItemMapper;
import cn.rfidcer.dao.PurchaseOrderDao;
import cn.rfidcer.dao.SaleItemMapper;
import cn.rfidcer.dao.SaleOrderMapper;
import cn.rfidcer.dao.SaleOutstockMapper;
import cn.rfidcer.dao.yz.StoreSaleItemMapper;
import cn.rfidcer.dao.yz.StoreSaleOrderMapper;
import cn.rfidcer.enums.ChangeType;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.service.GoodsStockService;
import cn.rfidcer.service.ProductStockService;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.service.yz.PosService;
import cn.rfidcer.util.BatchNumberUtil;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.MyStringUtls;
import cn.rfidcer.util.TokenCacheUtil;
import cn.rfidcer.util.UUIDGenerator;

import com.alibaba.fastjson.JSONObject;

@Service
public class PosServiceImpl implements PosService {

	private Logger logger = LoggerFactory.getLogger(PosServiceImpl.class);

	@Autowired
	private OutstockItemMapper outstockItemMapper;
	@Autowired
	private ProductStandardDetailDao productStandardDetailDao;
	@Autowired
	private SaleOrderMapper saleOrderMapper;
	@Autowired
	private TokenCacheUtil tokenCacheUtil;
	@Autowired
	private SaleOutstockMapper saleOutstockMapper;
	@Autowired
	private GoodsDetailDao  goodsDetailDao;
	@Autowired
	private GoodsStockService goodsStockService;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private SaleOrderMapper saleOrderDao;
	@Autowired
	private SaleItemMapper saleItemMapper;
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private PurchaseItemMapper purchaseItemMapper;
	@Autowired
	private StoreSaleItemMapper storeSaleItemMapper;	
	@Autowired
	private StoreSaleOrderMapper storeSaleOrderMapper;		
	@Autowired
	private CustomersMapper customersMapper;		
	@Autowired
	private InstockMainMapper instockMainMapper;	
	@Autowired
	private InstockItemMapper instockItemMapper;	
	@Autowired
	private ProductStockService productStockService;	
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private TraceCodeService traceCodeService;
	@Autowired
	private TraceTypeService traceTypeService;
	
	@Override
	public ResultMsg addStoreInstock(DeliveryQRCode deliveryQRCode) {
		logger.info("门店入库单邀请JSON："+JSONObject.toJSONString(deliveryQRCode));
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		List<GoodQRCode> goodList = deliveryQRCode.getGoodList();
		if (deliveryQRCode.getClass() == null || deliveryQRCode.getDeliveryQRCode().trim().length() == 0 || goodList == null || goodList.isEmpty()) {
			resultMsg.setCode("2");
			resultMsg.setMsg("无出库单信息");
			return resultMsg;
		}
		User user = new User();
		user.setId(deliveryQRCode.getStoreName());
		String storeCompanyId = deliveryQRCode.getStoreCompanyId();
		
		
		InstockMain instockMain = new InstockMain();
		String  instockMainId=UUIDGenerator.generatorUUID();
		instockMain.setInstockMainId(instockMainId);
		//instockMain.setConsignee("");
		instockMain.setInstockDate(DateUtil.getToday());
		instockMain.setStatus("1");
		instockMain.setInstockBatchNum(BatchNumberUtil.getStockBatch());
		instockMain.setInstockType("2");
		//instockMain.setRegistrant("");
		instockMain.setPurchaseOrderId("-");
		instockMain.setRegistDate(DateUtil.getToday());
		instockMain.setInstockNum(BatchNumberUtil.getStockBatch());
		instockMain.setCompanyId(storeCompanyId);
		CommonImportUtils.setCreateAndUpdateTime(instockMain, user);//设置创建日期和修改日期
		instockMainMapper.insertSelective(instockMain);
		try {
			for (GoodQRCode goodQRCode : goodList) {
				/*
				 * 未查到出库明细，新增出库明细 查询到已有出库明细，更新配送时间，更改关联的出库单
				 */
				String goodsBarCode = MyStringUtls.subQRCode(goodQRCode.getGoodQRCodeId());
				GoodsDetail  goodsDetail = goodsDetailDao.getGoodsByBarCode(goodsBarCode);
				SaleOrder saleorder  = saleOrderMapper.selectBybarCode(deliveryQRCode.getDeliveryQRCode());
				if(!StringUtils.isEmpty(goodsDetail) || !StringUtils.isEmpty(saleorder) ){
					SaleOutstock saleOutstock  = saleOutstockMapper.selectById(saleorder);
					//配送中心出库记录
					OutstockItem outstockItem = new OutstockItem();
					outstockItem.setOutstockItemId(UUIDGenerator.generatorUUID());
					outstockItem.setGoodsId(goodsDetail.getGoodsDetailId());
					outstockItem.setOutstockMainId(saleOutstock.getOutstockMainId());
					outstockItem.setSaleOrderId(saleOutstock.getSaleOrderId());
					outstockItem.setOutstockNum(new BigDecimal(1));
					outstockItem.setDeliveryTime(new Timestamp(System.currentTimeMillis()));
					CommonImportUtils.setCreateAndUpdateTime(outstockItem);
					outstockItemMapper.insertSelective(outstockItem);
					///配送中心库存操作
					String productId = goodsDetail.getProductId();
					String productStandardDetailId=goodsDetail.getProductStandardDetailId();
					GoodsStock goodsStock = new GoodsStock(goodsDetail.getGoodsId(),goodsDetail.getCompanyId());
					goodsStock.setStockNum(new BigDecimal(1));
					goodsStock.setProductId(productId);
					goodsStock.setProductStandardDetailId(productStandardDetailId);
					goodsStockService.addOrupdateStock( user, OperationType.OUTSTOCK, goodsStock,false);
					
					InstockItem instockItem = new InstockItem();
					instockItem.setInstockItemId(UUIDGenerator.generatorUUID());
					instockItem.setInstockMainId(instockMainId);
					instockItem.setGoodsId(goodsDetail.getGoodsId());
					instockItem.setInstockNum("1");
					CommonImportUtils.setCreateAndUpdateTime(instockItem, user);//设置创建日期和修改日期
					instockItemMapper.insertSelective(instockItem);
					//设置门店产品库存
					ProductStock productStock = new ProductStock();
					productStock.setCompanyId(storeCompanyId);
					productStock.setProductId(productId);
					productStock.setStockNum(new BigDecimal(goodsDetail.getProductStandardNum()));//设置门店里的规格数量110
					productStock.setProductStandardDetailId(productStandardDetailId);
					productStockService.addOrupdateStock(user, OperationType.INSTOCK, ChangeType.ADD, productStock);
					
				}else{
					code = "-1";
					msg = "扫码数据未没找到";
					break;
				}
			}
			code = "1";
			msg = "入库完毕";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			code = "-2";
			msg = "确认异常";
		}
		
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

		

	@Override
	public ResultMsg getGoodsDetailByBarCode(YzGoodsInfo yzGoodsInfo, UserToken userToken) {
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		try {
			if(StringUtils.isEmpty(yzGoodsInfo) || StringUtils.isEmpty(yzGoodsInfo.getTraceCode())){
				code = "2";
				msg = "无数据";
			}else{
				
				String decode = URLDecoder.decode(yzGoodsInfo.getTraceCode(), "UTF-8");
				System.out.println(decode);
				logger.info("扫描二维码获取商品信息：{}", decode);
				if (userToken == null || tokenCacheUtil.compareUserTokenFromCache(userToken)) {
					GoodsDetail goodsDetail = goodsDetailDao.getGoodsByBarCode(MyStringUtls.subQRCode(decode));
					if (goodsDetail != null) {
						//手持机查看扫描时间
						yzGoodsInfo = new YzGoodsInfo(
								goodsDetail.getProductId(),
								goodsDetail.getGoodsBatch(),
								goodsDetail.getCode(),
								goodsDetail.getProductStandardNum(),
								goodsDetail.getSalePrice()
						);
						code = "1";
						msg = "查询成功";
						resultMsg.setBaseEntity(yzGoodsInfo);
					}else { 
						code = "2";
						msg = "无数据";
					}
				} else {
					code = "-1";
					msg = "Token过期,请重新登录";
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("转码异常",e);
			code = "-2";
			msg = "转码异常";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}
	/**
	 * @param stockOrderInfo
	 * @return
	 */
	@Override
	public ResultMsg addStorePurchase(StockOrderInfo stockOrderInfo) {
		logger.info("门店采购单邀请JSON："+JSONObject.toJSONString(stockOrderInfo));
		User user = new User();
		user.setId(stockOrderInfo.getStoreNo());
		int res=0;
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		List<SaleItem> saleItems = stockOrderInfo.getItemList();
		String tracecode = new SimpleDateFormat("yyyyMMddHHmmSSS").format(new Date());
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setCompanyId(stockOrderInfo.getCompanyId()); //后期可能要改动
		saleOrder.setStatus("0");
		saleOrder.setOrderTime(new Date());
		saleOrder.setTraceCode(tracecode);
		saleOrder.setSaleOrderNo(tracecode);
		saleOrder.setBarcode(tracecode);
		//通过企业ID和客户用户企业ID查询出 客户表中的ID
		Customers customers = new Customers();
		customers.setCompanyId(stockOrderInfo.getCompanyId());
		customers.setCustCompanyId(stockOrderInfo.getCustomerId());
		customers = customersMapper.selectByCustCompanyId(customers);
		saleOrder.setCustomerId(customers.getCustomerId());
		logger.info("新增配送中心销售单："+saleOrder);
		try{
			//新增配送中心销售单
			String saleOrderId = UUIDGenerator.generatorUUID();
			saleOrder.setSaleOrderId(saleOrderId);
			logger.info("新增配送中心销售单ID："+saleOrderId);
			msg="新增销售单信息";
			CommonImportUtils.setCreateAndUpdateTime(saleOrder, user);//设置修改日期
			res = saleOrderDao.insertSelective(saleOrder); 
			
			//添加门店采购单信息
			String purchaseOrderId = UUIDGenerator.generatorUUID();
			logger.info("新增门店采购单："+purchaseOrderId);
			System.out.println("purchaseOrderId:"+purchaseOrderId);
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setCompanyId(stockOrderInfo.getCustomerId());
			purchaseOrder.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			CommonImportUtils.setCreateAndUpdateTime(purchaseOrder, user);//设置修改日期
			purchaseOrder.setSupplierId(stockOrderInfo.getCompanyId());
			purchaseOrder.setPurchaseOrderNo(new SimpleDateFormat("yyyyMMddHHmmSSS").format(new Date()));
			purchaseOrder.setPurchaseOrderId(purchaseOrderId);
			purchaseOrder.setRegistrant(stockOrderInfo.getStoreNo());//这里可能要动态改动  
			res = purchaseOrderDao.addPurchaseOrder(purchaseOrder);
			//添加主单完成后操作详细单
			if(res==1){
				//resultMsg =addSaleItems(saleItems,saleOrder.getSaleOrderId(),user);
				for (SaleItem  saleItem: saleItems) {
					PurchaseItem purchaseItem = new PurchaseItem();
					CommonImportUtils.setCreateAndUpdateTime(saleItem, user);//设置创建日期和修改日期
					saleItem.setSaleOrderId(saleOrderId);
					saleItem.setSaleItemId(UUIDGenerator.generatorUUID());
					//只有添加的时候判断销售价格为空来设置产品销售价格
					if(StringUtils.isEmpty(saleItem.getSalePrice())){
						ProductStandardDetail  productStandardDetail = productStandardDetailDao.
								findIdByStandardNameAndProductId(saleItem.getProductId(),saleItem.getProductStandardName());
						if(productStandardDetail!=null){
							String productStandardDetailId = productStandardDetail.getProductStandardDetailId();
							saleItem.setSalePrice(productStandardDetail.getSalePrice());
							saleItem.setProductStandardDetailId(productStandardDetailId);
							
					        BigDecimal b2 = new BigDecimal(productStandardDetail.getProductStandardNum());//采购单
					        BigDecimal quantity =  saleItem.getQuantity().divide(b2,0,BigDecimal.ROUND_HALF_EVEN);
							saleItem.setQuantity(quantity);
							
							//门店采购单详细
							purchaseItem.setProductId(saleItem.getProductId());
							purchaseItem.setPurchaseOrderId(purchaseOrderId);
							purchaseItem.setQuantity(quantity.toString());
							purchaseItem.setProductStandardDetailId(productStandardDetailId);
							purchaseItem.setPurchaseItemId(UUIDGenerator.generatorUUID());
							CommonImportUtils.setCreateAndUpdateTime(purchaseItem, user);//设置创建日期和修改日期
							purchaseItemMapper.insertSelective(purchaseItem);
							
							saleItemMapper.insertSelective(saleItem);
							
						}else{
							resultMsg.setCode("-4");
							resultMsg.setMsg("没有找到对应的商品ID："+saleItem.getProductId());
							return resultMsg;
						}
					}
				}
				code = "1";
				msg = "下单成功";
			}else{
				code = "-3";
				msg = "下单失败";
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
	
	@Override
	public ResultMsg addStoreSaleOrder(StoreSaleOrder storeSaleOrder) {
		logger.info("新增门店销售单ID："+storeSaleOrder.getStoreSaleOrderId());
		logger.info("新增门店销售单JSON："+JSONObject.toJSONString(storeSaleOrder));
		User user = new User();
		user.setId(storeSaleOrder.getUserId());
		int res=0;
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		try{
			CommonImportUtils.setCreateAndUpdateTime(storeSaleOrder, user);//设置创建日期和修改日期
			res=storeSaleOrderMapper.insertSelective(storeSaleOrder);
		if(res==1){
				logger.info("新增门店销售单成功！");
				for (StoreSaleItem  storeSaleItem: storeSaleOrder.getItems()) {
					
					GoodsDetail  goodsDetail = goodsDetailDao.getGoodsByBarCode(storeSaleItem.getGoodsBarCode());
					if(goodsDetail!=null){
						//门店库存销售库存操作
						ProductStock productStock = new ProductStock();
						productStock.setCompanyId(storeSaleOrder.getCompanyId());
						productStock.setProductId(goodsDetail.getProductId());
						productStock.setStockNum(storeSaleItem.getQuantity());//设置门店里的规格数量110
						productStock.setProductStandardDetailId(goodsDetail.getProductStandardDetailId());
						productStockService.addOrupdateStock(user, OperationType.OUTSTOCK, ChangeType.SUBTRACT, productStock);
					}
					
					int icount =  0 ;
					String traceCode=null;
					while(true){ //循环条件中直接为TRUE 
						icount++;
						traceCode = traceCodeService.newTraceCode(storeSaleOrder.getCompanyId(), storeSaleOrder.getCompanyCode());
						storeSaleItem.setGoodsTraceCode(traceCode);
				        Example example = new Example(StoreSaleItem.class);
				        Criteria criteria = example.createCriteria();
				        criteria = criteria.andEqualTo("goodsTraceCode", storeSaleItem.getGoodsTraceCode());
				        example.or(criteria);
				        List<StoreSaleItem> lst =  storeSaleItemMapper.selectByExample(example);
						//循环内容
						if(lst.size()<=0){ //直到符合条件后跳出本循环 否则一直循环下去
							break;
						}
						if(icount>15){
							resultMsg.setCode("-6");
							resultMsg.setMsg("商品条形码："+storeSaleItem.getGoodsBarCode()+" 尝试多次生成追溯码失败！");
							return resultMsg;
						}
					}
					logger.info("新增门店销售单明细:"+storeSaleItem);
					CommonImportUtils.setCreateAndUpdateTime(storeSaleItem, user);//设置创建日期和修改日期
					res=storeSaleItemMapper.insertSelective(storeSaleItem);
					if(res==1){
						traceTypeService.insertTraceType(traceCode, TraceType.SALEGOODS, user.getId());
						logger.info("新增门店销售单明细成功");
					}else{
						logger.info("新增门店销售单明细失败");
					}
				}
			}
			resultMsg.setBaseEntity(storeSaleOrder);
			code = "1";
			msg = "添加成功";
		}catch(DuplicateKeyException e){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				code = "-3";
				msg = "数据主键ID重复";
		}catch(Exception e){
			logger.info("新增门店销售单数据异常："+e.toString());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			code = "-2";
			msg = "数据异常";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;

	}

}

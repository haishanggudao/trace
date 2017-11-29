package cn.rfidcer.service.impl.yz;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.bean.InstockMain;
import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleItem;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.SaleOutstock;
import cn.rfidcer.bean.StoreAccount;
import cn.rfidcer.bean.StoreMac;
import cn.rfidcer.bean.StoreRegister;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.UserToken;
import cn.rfidcer.bean.yz.GoodsDetailInfo;
import cn.rfidcer.bean.yz.StoreInstockInfo;
import cn.rfidcer.bean.yz.StorePurchaseItem;
import cn.rfidcer.bean.yz.StorePurchaseOrder;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.yz.YzProduct;
import cn.rfidcer.dao.CompanyMapper;
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
import cn.rfidcer.dao.StoreAccountMapper;
import cn.rfidcer.dao.StoreMacMapper;
import cn.rfidcer.dao.StoreRegisterMapper;
import cn.rfidcer.dao.SupplierMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.dao.yz.StoreSaleItemMapper;
import cn.rfidcer.dao.yz.StoreSaleOrderMapper;
import cn.rfidcer.dao.yz.YzProductMapper;
import cn.rfidcer.dto.BaseInfo;
import cn.rfidcer.dto.DeviceSerial;
import cn.rfidcer.dto.InitInfo;
import cn.rfidcer.dto.LoginInfo;
import cn.rfidcer.dto.ProductParam;
import cn.rfidcer.dto.SaleOrderError;
import cn.rfidcer.dto.SaleOrderErrorList;
import cn.rfidcer.dto.StoreConfig;
import cn.rfidcer.enums.ChangeType;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.service.GoodsStockService;
import cn.rfidcer.service.ProductStockService;
import cn.rfidcer.service.SaleOrderService;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.service.yz.AndroidPosService;
import cn.rfidcer.service.yz.StoreSaleOrderService;
import cn.rfidcer.util.BatchNumberUtil;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.MyStringUtls;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.TokenCacheUtil;
import cn.rfidcer.util.UUIDGenerator;

import com.alibaba.fastjson.JSONObject;

@Service
public class AndroidPosServiceImpl implements AndroidPosService {

	private Logger logger = LoggerFactory
			.getLogger(AndroidPosServiceImpl.class);
	@Autowired
	private SaleOrderService saleOrderService;
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
	private GoodsDetailDao goodsDetailDao;
	@Autowired
	private GoodsStockService goodsStockService;
	@Autowired
	private ProductMapper productMapper;
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
	@Autowired
	private StoreMacMapper storeMacMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private StoreRegisterMapper storeRegisterMapper;
	@Autowired
	protected SysVariableDao variableDao;
	@Autowired
	private StoreAccountMapper storeAccountMapper;
	@Autowired
	private YzProductMapper yzProductMapper;
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private StoreSaleOrderService storeSaleOrderService;

	@Override
	public ResultMsg addStoreInstock(StoreInstockInfo storeInstockInfo) {
		logger.info("门店入库单邀请JSON：" + JSONObject.toJSONString(storeInstockInfo));
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		try {
			List<GoodsDetailInfo> goodList = storeInstockInfo.getGoodList();
			if (storeInstockInfo.getClass() == null || goodList == null
					|| goodList.isEmpty()) {
				resultMsg.setCode("-4");
				resultMsg.setMsg("无入库信息");
				return resultMsg;
			}
			User user = new User();
			user.setId(storeInstockInfo.getStoreName());
			String storeCompanyId = storeInstockInfo.getStoreCompanyId();

			InstockMain instockMain = new InstockMain();
			String instockMainId = UUIDGenerator.generatorUUID();
			instockMain.setInstockMainId(instockMainId);
			// instockMain.setConsignee("");
			instockMain.setInstockDate(DateUtil.getToday());
			instockMain.setStatus("1");
			instockMain.setInstockBatchNum(BatchNumberUtil.getStockBatch());
			instockMain.setInstockType("2");
			// instockMain.setRegistrant("");
			instockMain.setPurchaseOrderId("-");
			instockMain.setRegistDate(DateUtil.getToday());
			instockMain.setInstockNum(BatchNumberUtil.getStockBatch());
			instockMain.setCompanyId(storeCompanyId);
			CommonImportUtils.setCreateAndUpdateTime(instockMain, user);// 设置创建日期和修改日期
			instockMainMapper.insertSelective(instockMain);

			// 如果有配送单的情况下查询出销售单的信息
			SaleOrder saleorder = new SaleOrder();
			if (!StringUtils.isEmpty(storeInstockInfo.getDeliveryBarCode())) {
				saleorder = saleOrderMapper.selectBybarCode(storeInstockInfo
						.getDeliveryBarCode());
			}
			for (GoodsDetailInfo goodsDetailInfo : goodList) {
				/*
				 * 未查到出库明细，新增出库明细 查询到已有出库明细，更新配送时间，更改关联的出库单
				 */
				String goodsBarCode = MyStringUtls.subQRCode(goodsDetailInfo
						.getBarCode());
				GoodsDetail goodsDetail = goodsDetailDao
						.getGoodsByBarCode(goodsBarCode);
				if (!StringUtils.isEmpty(goodsDetail)) {
					// 如果有配送单的情况下给配送中心增加商品出库明细
					OutstockItem outstockItem = null;
					try {
						outstockItem = outstockItemMapper
								.selectByGoodsDetailId(goodsDetail
										.getGoodsDetailId());
					} catch (Exception e) {

					}
					if (outstockItem == null) {
						outstockItem = new OutstockItem();
						outstockItem.setOutstockItemId(UUIDGenerator
								.generatorUUID());
						outstockItem.setGoodsId(goodsDetail.getGoodsDetailId());
						outstockItem.setOutstockNum(new BigDecimal(1));
						outstockItem.setDeliveryTime(new Timestamp(System
								.currentTimeMillis()));
						CommonImportUtils.setCreateAndUpdateTime(outstockItem);
						if (!StringUtils.isEmpty(saleorder)
								&& !StringUtils.isEmpty(saleorder
										.getSaleOrderId())) {
							SaleOutstock saleOutstock = saleOutstockMapper
									.selectById(saleorder);
							// 配送中心出库记录
							outstockItem.setOutstockMainId(saleOutstock
									.getOutstockMainId());
							outstockItem.setSaleOrderId(saleOutstock
									.getSaleOrderId());
						} else {
							outstockItem.setOutstockMainId("-");
							outstockItem.setSaleOrderId("-");
						}
						outstockItemMapper.insertSelective(outstockItem);
					} else {
						CommonImportUtils.setCreateAndUpdateTime(outstockItem);
						outstockItemMapper
								.updateByPrimaryKeySelective(outstockItem);
					}

					// /配送中心库存操作
					String productId = goodsDetail.getProductId();
					String productStandardDetailId = goodsDetail
							.getProductStandardDetailId();
					GoodsStock goodsStock = new GoodsStock(
							goodsDetail.getGoodsId(),
							goodsDetail.getCompanyId());
					goodsStock.setStockNum(new BigDecimal(1));
					goodsStock.setProductId(productId);
					goodsStock
							.setProductStandardDetailId(productStandardDetailId);
					goodsStockService.addOrupdateStock(user,
							OperationType.OUTSTOCK, goodsStock, false);

					InstockItem instockItem = new InstockItem();
					instockItem.setInstockItemId(UUIDGenerator.generatorUUID());
					instockItem.setInstockMainId(instockMainId);
					instockItem.setGoodsId(goodsDetail.getGoodsId());
					instockItem.setInstockNum("1");
					CommonImportUtils.setCreateAndUpdateTime(instockItem, user);// 设置创建日期和修改日期
					instockItemMapper.insertSelective(instockItem);
					// 设置门店产品库存
					ProductStock productStock = new ProductStock();
					productStock.setCompanyId(storeCompanyId);
					productStock.setProductId(productId);
					productStock.setStockNum(new BigDecimal(goodsDetail
							.getProductStandardNum()));// 设置门店里的规格数量110 减少库存
					productStock
							.setProductStandardDetailId(productStandardDetailId);
					productStockService
							.addOrupdateStock(user, OperationType.INSTOCK,
									ChangeType.ADD, productStock);

				} else {
					code = "-2";
					msg = "扫码数据未没找到条形码:" + goodsBarCode;
					resultMsg.setCode(code);
					resultMsg.setMsg(msg);
					TransactionAspectSupport.currentTransactionStatus()
							.setRollbackOnly();
					return resultMsg;
				}
			}
			code = "1";
			msg = "入库完毕";
		} catch (Exception e) {
			logger.error("门店入库单数据异常", e);
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			code = "-3";
			msg = "数据异常";
		}

		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public ResultMsg getGoodsDetailByBarCode(GoodsDetailInfo goodsDetailInfo,
			UserToken userToken) {
		ResultMsg resultMsg = new ResultMsg();
		String code = "1";
		String msg = "查询成功";
		try {
			if (StringUtils.isEmpty(goodsDetailInfo)
					|| StringUtils.isEmpty(goodsDetailInfo.getBarCode())) {
				resultMsg.setCode("-2");
				resultMsg.setMsg("无数据");
				return resultMsg;
			} else {
				String decode = URLDecoder.decode(goodsDetailInfo.getBarCode(),
						"UTF-8");
				System.out.println(decode);
				logger.info("扫描二维码获取商品信息：{}", decode);
				GoodsDetail goodsDetail = goodsDetailDao
						.getGoodsByBarCode(MyStringUtls.subQRCode(decode));
				if (goodsDetail == null) {
					resultMsg.setCode("-2");
					resultMsg.setMsg("无数据");
					return resultMsg;
				}
				// 手持机查看扫描时间
				goodsDetailInfo = new GoodsDetailInfo(
						goodsDetail.getProductId(),
						goodsDetail.getGoodsBatch(), goodsDetail.getCode(),
						goodsDetail.getBarcode(),
						goodsDetail.getProductStandardNum(),
						goodsDetail.getSalePrice());
				resultMsg.setResultEntity(goodsDetailInfo);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("获取数据异常", e);
			code = "-3";
			msg = "数据异常";
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
	public ResultMsg addStorePurchase(StorePurchaseOrder storePurchaseOrder) {
		logger.info("安卓门店采购单JSON："
				+ JSONObject.toJSONString(storePurchaseOrder));
		int res = 0;
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		try {
			User user = new User();
			user.setId(storePurchaseOrder.getUserId());
			String tracecode = saleOrderService.getSaleOrderNo();
			SaleOrder saleOrder = new SaleOrder();
			saleOrder.setCompanyId(storePurchaseOrder.getSupplierCompanyId()); // 后期可能要改动
			saleOrder.setStatus("0");
			saleOrder.setOrderTime(new Date());
			saleOrder.setTraceCode(tracecode);
			saleOrder.setSaleOrderNo(tracecode);
			saleOrder.setBarcode(tracecode);
			// 通过企业ID和客户用户企业ID查询出 客户表中的ID
			Customers customers = new Customers();
			customers.setCompanyId(storePurchaseOrder.getSupplierCompanyId());
			customers.setCustCompanyId(storePurchaseOrder.getStoreCompanyId());
			customers = customersMapper.selectByCustCompanyId(customers);
			saleOrder.setCustomerId(customers.getCustomerId());
			// 新增配送中心销售单
			String saleOrderId = UUIDGenerator.generatorUUID();
			saleOrder.setSaleOrderId(saleOrderId);
			logger.info("新增配送中心销售单ID：" + saleOrderId);
			CommonImportUtils.setCreateAndUpdateTime(saleOrder, user);// 设置修改日期
			res = saleOrderMapper.insertSelective(saleOrder);
			// 添加门店采购单信息
			String purchaseOrderId = storePurchaseOrder.getPurchaseOrderId();
			logger.info("新增门店采购单：" + purchaseOrderId);
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setCompanyId(storePurchaseOrder.getStoreCompanyId());// 门店ID
			purchaseOrder.setOrderTime(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));
			CommonImportUtils.setCreateAndUpdateTime(purchaseOrder, user);// 设置修改日期
			purchaseOrder.setSupplierId(storePurchaseOrder
					.getSupplierCompanyId());
			purchaseOrder.setPurchaseOrderNo(saleOrderService.getSaleOrderNo());
			purchaseOrder.setPurchaseOrderId(purchaseOrderId);
			purchaseOrder.setRegistrant("");// 登记人
			res = purchaseOrderDao.addPurchaseOrder(purchaseOrder);
			// 添加主单完成后操作详细单
			if (res == 1) {
				// resultMsg
				// =addSaleItems(saleItems,saleOrder.getSaleOrderId(),user);
				for (StorePurchaseItem storePurchaseItem : storePurchaseOrder
						.getItem()) {
					ProductStandardDetail productStandardDetail = productStandardDetailDao
							.findByStandardDetailId(storePurchaseItem
									.getProductStandardDetailId());
					if (productStandardDetail != null) {
						PurchaseItem purchaseItem = new PurchaseItem();
						SaleItem saleItem = new SaleItem();
						saleItem.setProductId(productStandardDetail
								.getProductId());
						saleItem.setQuantity(storePurchaseItem.getQuantity());
						CommonImportUtils
								.setCreateAndUpdateTime(saleItem, user);// 设置创建日期和修改日期
						saleItem.setSaleOrderId(saleOrderId);
						saleItem.setSaleItemId(UUIDGenerator.generatorUUID());

						String productStandardDetailId = productStandardDetail
								.getProductStandardDetailId();
						saleItem.setSalePrice(productStandardDetail
								.getSalePrice());
						saleItem.setProductStandardDetailId(productStandardDetailId);
						// BigDecimal b2 = new
						// BigDecimal(productStandardDetail.getProductStandardNum());//采购单
						// BigDecimal quantity =
						// saleItem.getQuantity().divide(b2,0,BigDecimal.ROUND_HALF_EVEN);
						// saleItem.setQuantity(quantity);
						// 门店采购单详细
						purchaseItem.setProductId(saleItem.getProductId());
						purchaseItem.setPurchaseOrderId(purchaseOrderId);
						purchaseItem.setQuantity(saleItem.getQuantity()
								.toString());
						purchaseItem
								.setProductStandardDetailId(productStandardDetailId);
						purchaseItem.setPurchaseItemId(UUIDGenerator
								.generatorUUID());
						CommonImportUtils.setCreateAndUpdateTime(purchaseItem,
								user);// 设置创建日期和修改日期
						purchaseItemMapper.insertSelective(purchaseItem);
						saleItemMapper.insertSelective(saleItem);
					} else {
						resultMsg.setCode("-4");
						resultMsg.setMsg("没有找到对应的规格明细ID："
								+ storePurchaseItem
										.getProductStandardDetailId());
						TransactionAspectSupport.currentTransactionStatus()
								.setRollbackOnly();
						return resultMsg;
					}

				}
				code = "1";
				msg = "下单成功";
			} else {
				code = "-3";
				msg = "下单失败";
			}
		} catch (DuplicateKeyException e) {
			logger.error("门店要货单数据异常", e);
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			code = "-4";
			msg = "采购单ID已存在";
		} catch (Exception e) {
			logger.error("门店要货单数据异常", e);
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			code = "-2";
			msg = "数据异常";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public ResultMsg addStoreSaleOrder(StoreSaleOrder storeSaleOrder) {
		String strJson = JSONObject.toJSONString(storeSaleOrder);
		logger.info("新增门店销售单JSON：" + strJson);
		User user = new User();
		user.setId(storeSaleOrder.getUserId());
		int res = 0;
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		try {
			CommonImportUtils.setCreateAndUpdateTime(storeSaleOrder, user);// 设置创建日期和修改日期
			res = storeSaleOrderMapper.insertSelective(storeSaleOrder);
			if (res == 1) {
				logger.info("新增门店销售单成功！");
				for (StoreSaleItem storeSaleItem : storeSaleOrder.getItems()) {

					GoodsDetail goodsDetail = goodsDetailDao
							.getGoodsByBarCode(storeSaleItem.getGoodsBarCode());
					if (goodsDetail != null) {
						// 门店库存销售库存操作
						ProductStock productStock = new ProductStock();
						productStock
								.setCompanyId(storeSaleOrder.getCompanyId());
						productStock.setProductId(goodsDetail.getProductId());
						productStock.setStockNum(storeSaleItem.getQuantity()
								.multiply(new BigDecimal(-1)));// 设置门店里的规格数量110
						productStock.setProductStandardDetailId(goodsDetail
								.getProductStandardDetailId());
						productStockService.addOrupdateStock(user,
								OperationType.OUTSTOCK, ChangeType.SUBTRACT,
								productStock);
					}
					// int icount = 0 ;
					String traceCode = null;
					while (true) { // 循环条件中直接为TRUE
					// icount++;
						// 安卓端生成追溯码提交
						// traceCode =
						// traceCodeService.newTraceCode(storeSaleOrder.getCompanyId(),
						// storeSaleOrder.getCompanyCode());
						// storeSaleItem.setGoodsTraceCode(traceCode);
						traceCode = storeSaleItem.getGoodsTraceCode();
						Example example = new Example(StoreSaleItem.class);
						Criteria criteria = example.createCriteria();
						criteria = criteria.andEqualTo("goodsTraceCode",
								traceCode);
						example.or(criteria);
						List<StoreSaleItem> lst = storeSaleItemMapper
								.selectByExample(example);
						// 循环内容
						if (lst.size() <= 0) { // 直到符合条件后跳出本循环 否则一直循环下去
							break;
						} else {
							resultMsg.setCode("-4");
							resultMsg.setMsg("追溯码重复生成！");
							TransactionAspectSupport.currentTransactionStatus()
									.setRollbackOnly();
							return resultMsg;
						}
						/*
						 * if(icount>15){ resultMsg.setCode("-4");
						 * resultMsg.setMsg("追溯码尝试多次生成失败！"); return resultMsg; }
						 */
					}
					logger.info("新增门店销售单明细:" + storeSaleItem);
					CommonImportUtils.setCreateAndUpdateTime(storeSaleItem,
							user);// 设置创建日期和修改日期
					res = storeSaleItemMapper.insertSelective(storeSaleItem);
					if (res == 1) {
						traceTypeService.insertTraceType(traceCode,
								TraceType.SALEGOODS, user.getId());
						logger.info("新增门店销售单明细成功");
					} else {
						logger.info("新增门店销售单明细失败!JSON:" + strJson);
						resultMsg.setCode("-5");
						resultMsg.setMsg("数据出错请检查数据格式！");
						TransactionAspectSupport.currentTransactionStatus()
								.setRollbackOnly();
						return resultMsg;

					}
				}
			}
			resultMsg.setBaseEntity(storeSaleOrder);
			code = "1";
			msg = "添加成功";
		} catch (DuplicateKeyException e) {
			logger.error("门店销售单数据异常", e);
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			code = "-3";
			msg = "数据主键ID重复";
		} catch (Exception e) {
			logger.error("门店销售单数据异常", e);
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			code = "-2";
			msg = "数据异常";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;

	}

	@Override
	public ResultMsg getMacNum(StoreMac posMac) {

		logger.info("获取门店Mac编号JSON：" + JSONObject.toJSONString(posMac));
		int res = 0;
		ResultMsg resultMsg = new ResultMsg();
		String code = "0";
		String msg = null;
		try {
			StoreMac storeMac = storeMacMapper.selectByPrimaryKey(posMac
					.getMac());
			if (!StringUtil.isBlank(storeMac)) {
				code = "1";
				msg = "获取成功";
				resultMsg.setBaseEntity(storeMac);
			} else {
				res = companyMapper.updateMacNum(posMac.getCompanyId());
				if (res > 0) {
					Company company = companyMapper.selectByPrimaryKey(posMac
							.getCompanyId());
					if (company != null) {
						String macNum = (company.getMacNum() < 10) ? "0"
								+ company.getMacNum() : ""
								+ company.getMacNum();
						posMac.setMacNum(macNum);
						posMac.setCreateBy("1");
						posMac.setUpdateBy("1");
						posMac.setStatus("0");
						posMac.setCreateTime(new Timestamp(System
								.currentTimeMillis()));
						posMac.setUpdateTime(new Timestamp(System
								.currentTimeMillis()));
						res = storeMacMapper.insert(posMac);
						if (res > 0) {
							logger.info("门店Mac编号添加成功");
							code = "1";
							msg = "获取成功";
							resultMsg.setBaseEntity(posMac);
						} else {
							logger.info("门店Mac编号添加失败");
							code = "-2";
							msg = "添加时数据异常";
						}
					}
				} else {
					code = "-3";
					msg = "企业未找到";
				}
			}
		} catch (Exception e) {
			logger.error("获取门店Mac编号异常", e);
			code = "-4";
			msg = "数据异常";
		}
		resultMsg.setCode(code);
		resultMsg.setMsg(msg);
		return resultMsg;
	}

	@Override
	public ResultMsg getInitInfo(StoreRegister storeRegister) {
		ResultMsg resultMsg = new ResultMsg();
		StoreRegister selectRegister = new StoreRegister();
		selectRegister.setLicense(storeRegister.getLicense());
		selectRegister = storeRegisterMapper.selectOne(selectRegister);
		if (selectRegister == null) {
			resultMsg.setCode("-2");
			resultMsg.setMsg("无数据");
		} else {
			if (selectRegister.getMac() == null
					|| selectRegister.getMac().equals(storeRegister.getMac())) {
				InitInfo initInfo = new InitInfo();
				Company company = companyMapper
						.selectByPrimaryKey(selectRegister.getCompanyId());
				if (company == null) {
					resultMsg.setCode("-3");
					resultMsg.setMsg("数据异常");
				} else {
					selectRegister.setMac(storeRegister.getMac());
					storeRegisterMapper.updateByPrimaryKey(selectRegister);
					StoreConfig config = new StoreConfig();
					config.setCompanyAddress(company.getAddress());
					config.setCompanyCode(company.getCode());
					config.setCompanyId(selectRegister.getCompanyId());
					config.setCompanyName(company.getName());
					config.setCompanyTel(company.getTel());
					String qrCodePrev = variableDao.getValByKey("QRCodePrev");
					config.setTraceCodePath(qrCodePrev);
					DeviceSerial deviceSerial = new DeviceSerial();
					deviceSerial.setWifiMacAddress(storeRegister.getMac());
					deviceSerial.setLocation(selectRegister.getLocation());
					deviceSerial
							.setSubLocation(selectRegister.getSubLocation());
					initInfo.setConfig(config);
					initInfo.setDeviceSerial(deviceSerial);
					resultMsg.setCode(ResultMsg.SUCCESS);
					resultMsg.setMsg("注册成功");
					resultMsg.setResultEntity(initInfo);
				}
			} else {
				resultMsg.setCode("-4");
				resultMsg.setMsg("license已使用过");
			}
		}
		return resultMsg;
	}

	@Override
	public ResultMsg getLoginInfo(StoreAccount storeAccount) {
		ResultMsg resultMsg = new ResultMsg();
		List<StoreAccount> storeAccounts = storeAccountMapper
				.findAccountByCompanyId(storeAccount);
		if (storeAccounts == null || storeAccounts.isEmpty()) {
			resultMsg.setCode("-2");
			resultMsg.setMsg("无数据");
		} else {
			resultMsg.setCode(ResultMsg.SUCCESS);
			resultMsg.setMsg("查询成功");
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setLoginInfo(storeAccounts);
			resultMsg.setResultEntity(loginInfo);
		}
		return resultMsg;
	}

	@Override
	public ResultMsg getBaseInfo(ProductParam productParam) {
		ResultMsg resultMsg = new ResultMsg();
		List<YzProduct> products = yzProductMapper
				.getUpdateProductByIdAndTime(productParam);
		List<ProductCategory> categories = yzProductMapper
				.getCategoryByIdAndTime(productParam);
		List<Supplier> suppliers = supplierMapper
				.getSupplierByCompanyId(productParam);
		if ((products == null || products.isEmpty())
				&& (categories == null || categories.isEmpty())
				&& (suppliers == null || suppliers.isEmpty())) {
			resultMsg.setCode("-2");
			resultMsg.setMsg("无数据");
		} else {
			BaseInfo baseInfo = new BaseInfo();
			baseInfo.setProductCategory(categories);
			baseInfo.setProduct(products);
			baseInfo.setSupplier(suppliers);
			resultMsg.setResultEntity(baseInfo);
			baseInfo.setLastUpdateTime(new Date());
			resultMsg.setCode("1");
			resultMsg.setMsg("查询成功");
		}
		return resultMsg;
	}

	@Override
	public ResultMsg syncStoreSaleOrder(List<StoreSaleOrder> storeSaleOrderList) {
		String strJson = JSONObject.toJSONString(storeSaleOrderList);
		logger.info("同步门店销售单JSON：" + strJson);
		ResultMsg resultMsg = new ResultMsg();
		List<SaleOrderError> errorList=new ArrayList<SaleOrderError>();
		for (StoreSaleOrder storeSaleOrder : storeSaleOrderList) {
			
			try {
				storeSaleOrderService.addOrUpdateSaleOrder(storeSaleOrder);
			} catch (Exception e) {
				resultMsg.setCode("-2");
				resultMsg.setMsg("数据异常");
				SaleOrderError saleOrderError = new SaleOrderError();
				saleOrderError.setStoreSaleOrderId(storeSaleOrder.getStoreSaleOrderId());
				errorList.add(saleOrderError);
				logger.error("同步门店销售单异常:"+e.getMessage());
			}
		}
		/*
		 * 判断是否同步成功
		 */
		if(resultMsg.getCode()!=null){
			SaleOrderErrorList saleOrderErrorList = new SaleOrderErrorList();
			saleOrderErrorList.setErrorSaleOrderIds(errorList);
			resultMsg.setResultEntity(saleOrderErrorList);
		}else{
			resultMsg.setCode("1");
			resultMsg.setMsg("同步成功");
			resultMsg.setTime(DateUtil.getToday());
		}
		return resultMsg;
	}





	

	@Override
	public ResultMsg syncStoreInstock(
			List<StoreInstockInfo> storeInstockInfoList) {
		return null;
	}

	@Override
	public ResultMsg syncStorePurchase(
			List<StorePurchaseOrder> storePurchaseOrderList) {
		return null;
	}

}

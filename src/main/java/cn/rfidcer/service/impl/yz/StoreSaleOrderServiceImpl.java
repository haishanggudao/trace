package cn.rfidcer.service.impl.yz;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.dao.GoodsDetailDao;
import cn.rfidcer.dao.yz.StoreSaleItemMapper;
import cn.rfidcer.dao.yz.StoreSaleOrderMapper;
import cn.rfidcer.enums.ChangeType;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.exception.DataErrorException;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStockService;
import cn.rfidcer.service.TraceTypeService;
import cn.rfidcer.service.yz.StoreSaleOrderService;
import cn.rfidcer.util.CommonImportUtils;

@Service
public class StoreSaleOrderServiceImpl implements StoreSaleOrderService {

	private Logger logger = LoggerFactory.getLogger(StoreSaleOrderServiceImpl.class);
	
	@Autowired
	private StoreSaleOrderMapper storeSaleOrderMapper;
	
	@Autowired
	private StoreSaleItemMapper storeSaleItemMapper;
	
	@Autowired
	private GoodsDetailDao goodsDetailDao;
	
	@Autowired
	private ProductStockService productStockService;
	
	@Autowired
	private TraceTypeService traceTypeService;
	
	@Override
	public List<StoreSaleOrder> findAll(Page page,StoreSaleOrder storeSaleOrder) {
		
		return storeSaleOrderMapper.findAll(page, storeSaleOrder);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void addOrUpdateSaleOrder(StoreSaleOrder storeSaleOrder) {
		int res;
		User user = new User();
		user.setId(storeSaleOrder.getUserId());
		/*
		 * 查询销售单是否存在，不存在新增，存在则更新销售单
		 */
		String storeSaleOrderId = storeSaleOrder.getStoreSaleOrderId();
		StoreSaleOrder oldSaleOrder = storeSaleOrderMapper.selectByPrimaryKey(storeSaleOrder);
		if (oldSaleOrder == null) {
			/*
			 * 新增销售单
			 */
			CommonImportUtils.setCreateAndUpdateTime(storeSaleOrder, user);// 设置创建日期和修改日期
			res = storeSaleOrderMapper.insertSelective(storeSaleOrder);
			if (res == 1) {
				logger.info("新增门店销售单成功！");
				for (StoreSaleItem storeSaleItem : storeSaleOrder.getItems()) {
					String traceCode = storeSaleItem.getGoodsTraceCode();
					selectTraceCodeRepeat(storeSaleOrderId, traceCode);
					GoodsDetail goodsDetail = goodsDetailDao.getGoodsByBarCode(storeSaleItem.getGoodsBarCode());
					if (goodsDetail != null) {
						addSaleOrderItem(storeSaleOrder,user, storeSaleItem,goodsDetail);
						
					}else{
						throw new DataErrorException("条形码"+storeSaleItem.getGoodsBarCode()+"无对应商品");
					}
				}
			}else{
				throw new DataErrorException("新增销售单异常,ID:"+storeSaleOrderId);
			}
		} else {
			/*
			 * 更新销售单，门店退货时销售单会改变
			 */
			CommonImportUtils.setUpdateTime(storeSaleOrder,user);// 设置创建日期和修改日期
			res = storeSaleOrderMapper.updateByPrimaryKey(storeSaleOrder);
			if (res == 1) {
				for (StoreSaleItem storeSaleItem : storeSaleOrder.getItems()) {
					/*
					 * 根据条形码查询商品明细，明细不存在则回滚当前销售单的数据库操作
					 */
					String goodsBarCode = storeSaleItem.getGoodsBarCode();
					GoodsDetail goodsDetail = goodsDetailDao.getGoodsByBarCode(goodsBarCode);
					if (goodsDetail != null) {
						/*
						 * 查询销售明细是否存在
						 */
						StoreSaleItem oldSaleItem = storeSaleItemMapper.selectByPrimaryKey(storeSaleItem);
						if (oldSaleItem == null) {
							/*
							 * 判断追溯码是否重复，然后新增销售明细
							 */
							String traceCode = storeSaleItem.getGoodsTraceCode();
							selectTraceCodeRepeat(storeSaleOrderId, traceCode);
							addSaleOrderItem(storeSaleOrder,user, storeSaleItem,goodsDetail);
						} else {
							/*
							 * 更新销售明细
							 */
							CommonImportUtils.setUpdateTime(storeSaleItem,user);// 设置创建日期和修改日期
							res = storeSaleItemMapper.updateByPrimaryKey(storeSaleItem);
							if(res==1){
								// 门店库存销售库存操作
								/*
								 * 计算销售量变化
								 * 2017-3-29 库存变化改为refundStockQuantity字段计算
								 */
								BigDecimal oldQuantity = oldSaleItem.getRefundStockQuantity();
								oldQuantity=oldQuantity==null?new BigDecimal(0):oldQuantity;
								BigDecimal newQuantity = storeSaleItem.getRefundStockQuantity();
								newQuantity=newQuantity==null?new BigDecimal(0):newQuantity;
								BigDecimal change = oldQuantity.subtract(newQuantity);//
								int compareTo = newQuantity.compareTo(oldQuantity);
								ProductStock productStock = new ProductStock();
								productStock.setCompanyId(storeSaleOrder.getCompanyId());
								productStock.setProductId(goodsDetail.getProductId());
								productStock.setProductStandardDetailId(goodsDetail.getProductStandardDetailId());
								productStock.setStockNum(change);
								if(compareTo==1){
									/*
									 * 库存增加
									 */
									productStockService.addOrupdateStock(user,OperationType.OUTSTOCK, ChangeType.ADD,productStock);
								}else if(compareTo==0){
									/*
									 * 库存无变化
									 */
								}else{
									/*
									 * 库存减少
									 */
									productStockService.addOrupdateStock(user,OperationType.OUTSTOCK, ChangeType.SUBTRACT,productStock);
								}
								
								
								
								
							}else{
								logger.info("更新门店销售单明细失败:"+storeSaleItem );
								throw new DataErrorException("更新销售单明细异常,ID:"+storeSaleItem.getStoreSaleItemId());
							}
						}

					} else {
						throw new DataErrorException("条形码"+storeSaleItem.getGoodsBarCode()+"无对应商品");
					}

				}
			} else {
				/*
				 * 更新销售单异常
				 */
				throw new DataErrorException("更新销售单异常,ID:"+storeSaleOrderId);
			}
		}
	}
	
	/**查询追溯码是否重复
	 * @param storeSaleOrderId
	 * @param traceCode
	 */
	private void selectTraceCodeRepeat(String storeSaleOrderId, String traceCode) {
		if (storeSaleItemMapper.getSaleItemsByTraceCode(traceCode) != null) {
			throw new DataErrorException("追溯码"+traceCode+"重复生成");
		}
	}
	
	/**新增销售单明细
	 * @param storeSaleOrder 销售单
	 * @param user 用户
	 * @param storeSaleItem
	 * @param goodsDetail
	 */
	private void addSaleOrderItem(StoreSaleOrder storeSaleOrder,User user,
			StoreSaleItem storeSaleItem,GoodsDetail goodsDetail) {
		int res;
		/*
		 * 先新增明细，再修改库存
		 */
		CommonImportUtils.setCreateAndUpdateTime(storeSaleItem,user);// 设置创建日期和修改日期
		res = storeSaleItemMapper.insertSelective(storeSaleItem);
		if (res == 1) {
			traceTypeService.insertTraceType(storeSaleItem.getGoodsTraceCode(),TraceType.SALEGOODS, user.getId());
			// 门店库存销售库存操作
			ProductStock productStock = new ProductStock();
			productStock.setCompanyId(storeSaleOrder.getCompanyId());
			productStock.setProductId(goodsDetail.getProductId());
			productStock.setStockNum(storeSaleItem.getQuantity().multiply(new BigDecimal(-1)));// 设置门店里的规格数量110
			productStock.setProductStandardDetailId(goodsDetail.getProductStandardDetailId());
			productStockService.addOrupdateStock(user,OperationType.OUTSTOCK, ChangeType.SUBTRACT,productStock);
		} else {
			logger.info("新增门店销售单明细失败:"+storeSaleItem );
			throw new DataErrorException("新增销售单明细异常,ID:"+storeSaleItem.getStoreSaleItemId());
		}
	}

}

package cn.rfidcer.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.rfidcer.bean.GoodsStock;
import cn.rfidcer.bean.GoodsStockHistory;
import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsStockDao;
import cn.rfidcer.dao.GoodsStockHistoryDao;
import cn.rfidcer.dao.ProductStockDao;
import cn.rfidcer.dao.ProductStockHistoryDao;
import cn.rfidcer.enums.ChangeType;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsStockService;
import cn.rfidcer.service.ProductStockService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

/**
 * 
* @Title: GoodsStockServiceImpl.java 
* @Package cn.rfidcer.service.impl 
* @Description: Service implements 商品库存 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月16日 上午11:38:52 
* @version V1.0
 */
@Service
public class GoodsStockServiceImpl implements GoodsStockService {
	
	private Logger logger=LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Autowired
	private GoodsStockDao goodsStockDao;
	
	@Autowired
	private ProductStockDao productStockDao;
	
	@Autowired
	private GoodsStockHistoryDao goodsStockHistoryDao;
	
	@Autowired
	private ProductStockHistoryDao productStockHistoryDao;
	
	@Autowired
	private ProductStockService productStockService;
	
	

	@Override
	public List<GoodsStock> findByProductIdStandDetailId(Page page, GoodsStock goodsStock) {
		return goodsStockDao.findByProductIdStandDetailId(page, goodsStock);
	}

	@Override
	public ResultMsg addOrupdateStock(User user, OperationType operationType,GoodsStock goodsStock,boolean updateProduct) {
		ResultMsg msg=new ResultMsg();
		BigDecimal currentNum=new BigDecimal(0);
		BigDecimal doneNum=new BigDecimal(0);
		BigDecimal operationNum=new BigDecimal(0);
		String productId=null;
		String productStandardDetailId=null;
		String goodsId=null;
		String companyId=null;
		ChangeType changeType =ChangeType.ADD;
		switch (operationType) {
			case SALERETURN://退货
				changeType=ChangeType.SUBTRACT;
				break;
			case INSTOCK://进货
				changeType=ChangeType.ADD;
				break;
			case OUTSTOCK://出货
				changeType=ChangeType.SUBTRACT;
				break;
			case COMPLEMENT://补货
				changeType=ChangeType.ADD;
				break;
			case LOSS://损耗
				changeType=ChangeType.SUBTRACT;
				break;
			case UPDATE://修改库存
				changeType=ChangeType.UNKNOWN;
				break;				
			default:
				break;
		}
		try{
			/*
			 * 查找商品库存是否存在
			 */
			GoodsStock oldGoodsStock = goodsStockDao.findByGoodsStockByCase(goodsStock);
			if(oldGoodsStock==null){
				switch (changeType) {
					case SUBTRACT://减少
						msg.setCode("0");
						msg.setMsg("没有库存不能出库");
						logger.info("商品{}库存无变化",goodsStock.getGoodsId());
						return msg;	
					default:
						break;
				}
				/*
				 *新增商品库存
				 */
				productId=goodsStock.getProductId();
				productStandardDetailId=goodsStock.getProductStandardDetailId();
				goodsId=goodsStock.getGoodsId();
				companyId=goodsStock.getCompanyId();
				operationNum=goodsStock.getStockNum();
				doneNum=goodsStock.getStockNum();
				goodsStock.setGoodsStockId(UUIDGenerator.generatorUUID());
				goodsStock.setStockNum(doneNum);
				CommonImportUtils.setCreateAndUpdateTime(goodsStock, user);
				goodsStockDao.addGoodsStock(goodsStock);
				
				
			}else{
				/*
				 * 更新商品库存
				 */
				productId=oldGoodsStock.getProduct().getProductId();
				productStandardDetailId=oldGoodsStock.getProductStandardDetail().getProductStandardDetailId();
				goodsId=oldGoodsStock.getGoodsId();
				companyId=oldGoodsStock.getCompanyId();
				currentNum=oldGoodsStock.getStockNum();//当前库存数量
				switch (changeType) {
					case SUBTRACT://减少
						operationNum=goodsStock.getStockNum();
						int compare=operationNum.compareTo(new BigDecimal(0));
						if( compare==1){
							operationNum=operationNum.multiply(new BigDecimal(-1));
						}
						doneNum=currentNum.add(operationNum);
						break;
					case ADD://增加
						operationNum=goodsStock.getStockNum();
						doneNum=currentNum.add(operationNum);
						break;
					case UNKNOWN:
						doneNum=goodsStock.getStockNum();
						operationNum=doneNum.subtract(currentNum);
						int compareTo = doneNum.compareTo(currentNum);
						if(compareTo==1){
							changeType=ChangeType.ADD;
						}else if(compareTo==0){
							msg.setCode("0");
							msg.setMsg("库存无变化");
							logger.info("商品{}库存无变化",goodsId);
							return msg;
						}else{
							changeType=ChangeType.SUBTRACT;
						} 
					default:
						break;
				}
				
				oldGoodsStock.setStockNum(operationNum);
				CommonImportUtils.setUpdateTime(oldGoodsStock, user);
				goodsStockDao.updateStockById(oldGoodsStock);
			}
			/*
			 * 新增商品库存操作历史
			 */
			GoodsStockHistory goodsStockHistory = new GoodsStockHistory(goodsId,operationType.getValue(),changeType.getValue(),companyId);
			goodsStockHistory.setGoodsStockHistoryId(UUIDGenerator.generatorUUID());
			goodsStockHistory.setCurrentNum(currentNum);
			goodsStockHistory.setOperationNum(operationNum );
			goodsStockHistory.setDoneNum(doneNum);
			goodsStockHistory.setCreateBy(user.getId());
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			goodsStockHistory.setCreateTime(createTime);
			goodsStockHistoryDao.addGoodsStockHistory(goodsStockHistory);
			
			msg.setCode("1");
			msg.setMsg("更新库存成功");
			logger.info("商品{}更新库存成功",goodsId);
			if(updateProduct){
				ProductStock productStock = new ProductStock(productId,productStandardDetailId,companyId);
				productStock.setStockNum(operationNum);
				msg = productStockService.addOrupdateStock(user, operationType,changeType,productStock);
			}
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新库存异常",e);
			msg.setCode("-1");
			msg.setMsg("更新库存异常");
		}
		return msg;
	}
	
	@Override
	public ResultMsg updateGoodsStock(User user, GoodsStock goodsStock) {
		logger.info("更新库存");
		return addOrupdateStock(user,  OperationType.UPDATE, goodsStock,true);
	}

}

package cn.rfidcer.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.rfidcer.bean.ProductStock;
import cn.rfidcer.bean.ProductStockHistory;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductStockDao;
import cn.rfidcer.dao.ProductStockHistoryDao;
import cn.rfidcer.enums.ChangeType;
import cn.rfidcer.enums.OperationType;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStockService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

/**
 * 
* @Title: ProductStockServiceImpl.java 
* @Package cn.rfidcer.service.impl 
* @Description: 产品库存service的实现
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月15日 下午6:36:32 
* @version V1.0
 */
@Service
public class ProductStockServiceImpl implements ProductStockService {
	private Logger logger=LoggerFactory.getLogger(ProductStockServiceImpl.class);
	@Autowired
	private ProductStockDao productStockDao;
	@Autowired
	private ProductStockHistoryDao productStockHistoryDao;
	@Override
	public List<ProductStock> list(Page page, ProductStock productStock) { 
		return productStockDao.list(page, productStock);
	}

	@Override
	public List<ProductStock> listByAdvancedQuery(Page page, ProductStock productStock) {
		return productStockDao.list(page, productStock);
	}

	@Override
	public ResultMsg addOrupdateStock(User user, OperationType operationType,ChangeType changeType, ProductStock productStock) {
		ResultMsg msg=new ResultMsg();
		BigDecimal currentNum=new BigDecimal(0);//当前数量
		BigDecimal doneNum=new BigDecimal(0);//已完成数量
		BigDecimal operationNum=productStock.getStockNum();
		try{
			/*
			 * 查找产品库存是否存在
			 */
			ProductStock oldProductStock=productStockDao.findByProductIdAndDetailId(productStock);
			if(oldProductStock==null){
				switch (changeType) {
					case SUBTRACT://减少
						msg.setCode("0");
						msg.setMsg("没有库存不能出库");
						logger.info("产品{}没有库存不能减少",productStock.getProductId());
						return msg;	
					default:
						break;
				}
				/*
				 * 新增产品库存
				 */
				productStock.setProductStockId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(productStock, user);
				productStockDao.addProductStock(productStock);
			}else{
				/*
				 * 更新产品库存
				 */
				currentNum=oldProductStock.getStockNum();//当前库存数量
				operationNum=productStock.getStockNum();
				switch (changeType) {
					case SUBTRACT://减少
						/*
						 * 修改商品库存时，如果库存为负数，取绝对值，即减正数
						 */
						doneNum=currentNum.subtract(operationNum.abs());
						break;
					case ADD://增加
						doneNum=currentNum.add(operationNum);
						break;					
					default:
						break;
				}
				oldProductStock.setStockNum(operationNum);
				CommonImportUtils.setUpdateTime(oldProductStock, user);
				productStockDao.updateStockNumById(oldProductStock);
			}
			String productId=productStock.getProductId();
			String productStandardDetailId=productStock.getProductStandardDetailId();
			String companyId=productStock.getCompanyId();
			
			logger.info("产品{}库存由{}变为{}",productStandardDetailId,currentNum,doneNum);
			/*
			 * 新增产品库存操作历史
			 */
			ProductStockHistory productStockHistory = new ProductStockHistory(productId,productStandardDetailId,operationType.getValue(),changeType.getValue(),companyId);
			productStockHistory.setProductStockHistoryId(UUIDGenerator.generatorUUID());
			productStockHistory.setCurrentNum(currentNum);
			productStockHistory.setOperationNum(operationNum);
			productStockHistory.setDoneNum(doneNum);
			productStockHistory.setCreateBy(user.getId());
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			productStockHistory.setCreateTime(createTime);
			productStockHistoryDao.addProductStockHistory(productStockHistory);
			msg.setCode("1");
			msg.setMsg("更新库存成功");
			logger.info("产品{}更新库存成功",productStandardDetailId);
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新库存异常",e);
			msg.setCode("-1");
			msg.setMsg("更新库存异常");
		}
		return msg;
	}

}

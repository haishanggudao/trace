package cn.rfidcer.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.ProductStandardRate;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductStandardRateDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStandardRateService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class 
ProductStandardRateServiceImpl implements ProductStandardRateService{

	private Logger logger=LoggerFactory.getLogger(ProductStandardRateServiceImpl.class);
	
	@Autowired
	private ProductStandardRateDao rateDao;
	
	@Override
	public List<ProductStandardRate> findAll(Page page, ProductStandardRate rate,String productCategoryId) {
		if(!StringUtils.isEmpty(productCategoryId)){
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			rate.setProductCategory(productCategory);
		}
		return rateDao.findAll(page, rate);
	}

	@Override
	public ResultMsg createOrUpdateProductStandardRate(ProductStandardRate rate, User user) {
		int res=0;
		String msg=null;
		if(StringUtils.isEmpty(rate.getProductStandardRateId())){
			rate.setCreateBy(user.getId());
			rate.setCreateTime(new Timestamp(System.currentTimeMillis()));
			rate.setProductStandardRateId(UUIDGenerator.generatorUUID());
			CommonImportUtils.setCreateAndUpdateTime(rate, user);//设置修改日期
			logger.info("新增产品规格转化："+rate);
			msg="新增产品规格转化";
			res = rateDao.createProductStandardRate(rate);
		}else{
			CommonImportUtils.setUpdateTime(rate, user);//设置修改日期
			logger.info("修改产品规格转化："+rate);
			msg="修改产品规格转化";
			res = rateDao.updateProductStandardRate(rate);
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public ResultMsg delProductStandardRate(ProductStandardRate rate) {
		logger.info("删除产品规格转化,ID:"+rate.getProductStandardRateId());
		int res = rateDao.delProductStandardRate(rate);
		return ResultMsgUtil.getReturnMsg(res, "删除产品规格转化");
	}

}

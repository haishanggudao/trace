package cn.rfidcer.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductStandardDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStandardService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProductStandardServiceImpl implements ProductStandardService {

	private Logger logger=LoggerFactory.getLogger(ProductStandardServiceImpl.class);
	
	@Autowired
	private ProductStandardDao standardDao;
	
	@Override
	public List<ProductStandard> findAll(Page page, ProductStandard productStandard) {
		return standardDao.findAll(page, productStandard);
	}

	@Override
	public ResultMsg createOrUpdateProductStandard(ProductStandard productStandard, User user) {
		int res=0;
		String msg=null;
		ProductStandard standard = standardDao.checkProductStandardExists(productStandard);
		if(StringUtils.isEmpty(productStandard.getProductStandardId())){
			logger.info("新增产品规格："+productStandard);
			if(standard==null){
				msg="新增产品规格";
				productStandard.setProductStandardId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(productStandard, user);//设置修改日期
				res = standardDao.createProductStandard(productStandard);
			}else{
				msg="此产品规格已存在";
				res=-1;
			}
		}else{
			logger.info("修改产品规格："+productStandard);
			if(standard==null){
				msg="修改产品规格";
				CommonImportUtils.setUpdateTime(productStandard, user);//设置修改日期
				res = standardDao.updateProductStandard(productStandard);
			}else{
				msg="此产品规格已存在";
				res=-1;
			}
			
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public ResultMsg delProductStandard(ProductStandard productStandard) {
		logger.info("删除产品规格,ID:"+productStandard.getProductStandardId());
		int res = standardDao.delProductStandard(productStandard);
		return ResultMsgUtil.getReturnMsg(res, "删除产品规格");
	}

	@Override
	public void createAPS(ProductStandard ps) {
		standardDao.insertSelective(ps);
	}


}

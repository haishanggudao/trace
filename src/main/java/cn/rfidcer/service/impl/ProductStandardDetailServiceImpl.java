package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductStandardDetailDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductStandardDetailService;
import cn.rfidcer.service.VerifyInfoService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProductStandardDetailServiceImpl implements ProductStandardDetailService{

	private Logger logger=LoggerFactory.getLogger(ProductStandardDetailServiceImpl.class);
	
	@Autowired
	private ProductStandardDetailDao standardDetailDao;
	 
	@Autowired
	private VerifyInfoService verifyInfoService;
	
	
	@Override
	public List<ProductStandardDetail> findAll(Page page, ProductStandardDetail productStandardDetail) {
		return standardDetailDao.findAll(page, productStandardDetail);
	} 

	@Override
	public ResultMsg createOrUpdateProductStandardDetail(ProductStandardDetail productStandardDetail, User user) {
		int res=0;
		String msg=null;
		//检测产品
		ResultMsg  rmsg = verifyInfoService.verifyProduct(productStandardDetail.getProduct());
		if(rmsg.getCode().equals("0")){
			return rmsg;
		}
		//检测产品规格
		rmsg = verifyInfoService.verifyStandard(productStandardDetail.getProductStandard());
		if(rmsg.getCode().equals("0")){
			return rmsg;
		}
		
		int num = standardDetailDao.findProductStandardDetailByUnique(productStandardDetail);
		String productStandardNum = productStandardDetail.getProductStandardNum();
		if(productStandardNum==null){
			productStandardDetail.setProductStandardNum("");
		}else{
			if(productStandardNum.endsWith(".00")){
				productStandardNum=productStandardNum.substring(0,productStandardNum.length()-3);
			}else if(productStandardNum.endsWith(".0")){
				productStandardNum=productStandardNum.substring(0,productStandardNum.length()-2);
			}else if(productStandardNum.indexOf(".")!=-1 && productStandardNum.endsWith("0")){
				productStandardNum=productStandardNum.substring(0,productStandardNum.length()-1);
			}
		}
		if(StringUtils.isEmpty(productStandardDetail.getProductStandardDetailId())){
			logger.info("新增产品规格明细："+productStandardDetail);
			if(num==0){
				msg="新增产品规格明细";
				productStandardDetail.setProductStandardDetailId(UUIDGenerator.generatorUUID()); 
				CommonImportUtils.setCreateAndUpdateTime(productStandardDetail, user);//设置修改日期	
				// DAO action: insert a new product standard detail
				res = standardDetailDao.insertSelective(productStandardDetail);
			}else{
				msg="此产品规格明细已存在";
				res=-1;
			}
		}else{
			logger.info("修改产品规格明细："+productStandardDetail);
			if(num==0){
				msg="修改产品规格明细";
				CommonImportUtils.setUpdateTime(productStandardDetail, user);//设置修改日期	
				res = standardDetailDao.updateProductStandardDetail(productStandardDetail);
			}else{
				msg="此产品规格明细已存在";
				res=-1;
			}
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public ResultMsg delProductStandardDetail(ProductStandardDetail productStandardDetail) {
		logger.info("删除产品规格明细,ID:"+productStandardDetail.getProductStandardDetailId());
		int res = standardDetailDao.delProductStandardDetail(productStandardDetail);
		return ResultMsgUtil.getReturnMsg(res, "删除产品规格明细");
	}

	@Override
	public void createAPSD(ProductStandardDetail std) {
		standardDetailDao.insertSelective(std);
	}

}

package cn.rfidcer.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.GoodsPrice;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductStandardDetailDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsPriceService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;

@Service
public class GoodsPriceServiceImpl implements GoodsPriceService{

	private Logger logger=LoggerFactory.getLogger(GoodsPriceServiceImpl.class);
	
	@Autowired
	private ProductStandardDetailDao productStandardDetailDao;

	@Override
	public List<ProductStandardDetail> findAll(Page page, ProductStandardDetail productStandardDetail) {
		return productStandardDetailDao.findAll(page, productStandardDetail);
	}
	@Override
	public List<ProductStandardDetail> findNoPriceList(ProductStandardDetail productStandardDetail) {
		return productStandardDetailDao.findNoPriceList( productStandardDetail);
	}
	@Override
	public ResultMsg createOrUpdateGoodsPrice(ProductStandardDetail productStandardDetail, User user) {
		int res=0;
		String msg=null;
		logger.info("修改产品规格明细价格："+productStandardDetail);
		msg="修改产品规格明细价格";
		CommonImportUtils.setUpdateTime(productStandardDetail, user);//设置修改日期	
		res = productStandardDetailDao.updateProductStandardDetail(productStandardDetail);
		return ResultMsgUtil.getReturnMsg(res, msg);
	}
	@Override
	public ResultMsg delGoodsPrice(ProductStandardDetail productStandardDetail, User user) {
		logger.info("删除产品规格明细价格,ID:"+productStandardDetail.getProductStandardDetailId());
		String msg="删除价格";
		CommonImportUtils.setUpdateTime(productStandardDetail, user);//设置修改日期
		BigDecimal price   = new BigDecimal("0.00");
		productStandardDetail.setSalePrice(price);
		productStandardDetail.setPurchasePrice(price);
		int res = productStandardDetailDao.updateProductStandardDetail(productStandardDetail);
		return ResultMsgUtil.getReturnMsg(res, msg);

	}

	@Override
	public List<GoodsPrice> findProductIdList(Page page,ProductStandardDetail productStandardDetail) {
			return productStandardDetailDao.findProductIdList(page,productStandardDetail);
	}

	@Override
	public List<GoodsPrice> findProductStandardIdList(ProductStandardDetail productStandardDetail) {
		return productStandardDetailDao.findProductStandardIdList( productStandardDetail);
	}



}

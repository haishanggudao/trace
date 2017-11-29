package cn.rfidcer.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.GoodsQC;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsQCDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsQCService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class GoodsQCServiceImpl implements GoodsQCService{

	private Logger logger=LoggerFactory.getLogger(GoodsQCServiceImpl.class);
	
	@Autowired
	private GoodsQCDao goodsQCDao;
	
	@Override
	public List<GoodsQC> findAll(Page page,GoodsQC goodsQC) {
		return goodsQCDao.findAll(page,goodsQC);
	}

	@Override
	public ResultMsg createOrUpdateGoodsQC(GoodsQC goodsQC,User user) {
		int res=0;
		String msg=null;
		int num = goodsQCDao.checkGoodsQCExists(goodsQC);
		if(StringUtils.isEmpty(goodsQC.getMaterialQcId())){
			logger.info("新增商品质检信息："+goodsQC);
			if(num==0){
				msg="新增商品质检信息";
				CommonImportUtils.setCreateAndUpdateTime(goodsQC, user);//设置修改日期
				goodsQC.setMaterialQcId(UUIDGenerator.generatorUUID());
				res = goodsQCDao.createGoodsQC(goodsQC);
			}else{
				msg="此商品质检信息已存在";
				res=-1;
			}
			
		}else{
			logger.info("修改商品质检信息："+goodsQC);
			if(num==0){
				msg="修改商品质检信息";
				CommonImportUtils.setUpdateTime(goodsQC, user);//设置修改日期
				res = goodsQCDao.updateGoodsQC(goodsQC);
			}else{
				msg="此商品质检信息已存在";
				res=-1;
			}
			
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public ResultMsg delGoodsQC(GoodsQC goodsQC) {
		logger.info("删除商品质检信息，ID："+goodsQC.getMaterialQcId());
		int res = goodsQCDao.delGoodsQC(goodsQC);
		return ResultMsgUtil.getReturnMsg(res, "删除商品质检信息");
	}

}

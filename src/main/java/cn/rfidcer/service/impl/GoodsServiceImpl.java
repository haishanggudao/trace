package cn.rfidcer.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.ProductStandardDetailDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GoodsService;
import cn.rfidcer.service.SerialNumService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;  
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductStandardDetailDao productStandardDetailDao;
	
	@Autowired
	private SerialNumService serialNumService;

	@Override
	public ResultMsg createOrUpdateGoods(Goods goods, User user) {
		int res = 0;
		String msg=null;
		int num=goodsDao.findByProductidStandarddetailidBatchCompany(goods);
		if (StringUtils.isEmpty(goods.getGoodsId() )) {
			// case: add
			if(num==0){
				msg="新增商品";
				String myUUID = UUIDGenerator.generatorUUID();
				goods.setGoodsId(myUUID);
				CommonImportUtils.setCreateAndUpdateTime(goods, user);//设置修改时间和创建时间
				res = goodsDao.insertSelective(goods);
			}else{
				msg="此商品已存在";
				res=-1;
			}
		} else {
			// case: update
			if(num==0){
				msg="修改商品";
				CommonImportUtils.setUpdateTime(goods, user);//设置修改时间
				res = goodsDao.updateGoods(goods);
			}else{
				msg="此商品已存在";
				res=-1;
			}
			
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public List<Goods> getGoodsList(Page page, Goods goods, String producttype) {
		return goodsDao.getGoodsList(page, goods,producttype);
	}

	@Override
	public ResultMsg delGoods(Goods goods) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			/*
			 * 删除商品改为更改状态为-1
			 */
			goods.setIsDeleted(-1);
			int res = goodsDao.updateGoods(goods);
			resultMsg.setCode(res + "");
			if (res == 1) {
				resultMsg.setMsg("删除商品成功");
			} else {
				resultMsg.setMsg("删除商品失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("-1");
			resultMsg.setMsg("此商品无法删除");
		}
		return resultMsg;
	}

	@Override
	public List<Goods> list(Page page) {
		List<Goods> list = null;
		if(page != null) {
			list = goodsDao.list(page);
		} else {
			list = goodsDao.list();
		}
		if(list != null && !list.isEmpty()) {
			for (Goods goods : list) {
				Product product = productMapper.selectByPrimaryKey(goods.getProduct().getProductId());
				List<ProductStandardDetail> findAll = productStandardDetailDao.findAll(null, goods.getProductStandardDetail());
				if(findAll != null && !findAll.isEmpty()) {
					goods.setProductStandardDetail(findAll.get(0));
				}
				goods.setProduct(product);
				if(product != null) {
					goods.setGoodsName(product.getProductName());
				}
			}
		}
		return list;
	}

	@Override
	public Goods getByKey(String goodsId) {
		Goods goods = goodsDao.getByGoodsId(goodsId);
		if(goods != null) {
			Product product = productMapper.selectByPrimaryKey(goods.getProduct().getProductId());
			List<ProductStandardDetail> findAll = productStandardDetailDao.findAll(null, goods.getProductStandardDetail());
			if(findAll != null && !findAll.isEmpty()) {
				goods.setProductStandardDetail(findAll.get(0));
			}
			goods.setProduct(product);
			goods.setGoodsName(product.getProductName());
		}
		return goods;
	}

	@Override
	public List<Goods> getByProductId(String productId) {
		Goods goods = new Goods();
		goods.setProductId(productId);
		return goodsDao.getGoodsList(goods);
	}

	@Override
	public ResultMsg updateGoodsUsable(Goods goods) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int res = goodsDao.updateGoodsUsable(goods.getGoodsId());
			resultMsg.setCode(res + "");
			if (res == 1) {
				resultMsg.setMsg("更新成功");
			} else {
				resultMsg.setMsg("更新失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("-1");
			resultMsg.setMsg("此商品无法更新");
			e.printStackTrace();
		}
		return resultMsg;
	}
	
	@Override
	public List<Goods> findAllGoodsName(String companyId) {
		List<Goods> lstGoods = goodsDao.findAllGoodsName(companyId);
		return lstGoods;
	}
	
	

	@Override
	public List<Goods> findGoodsByStandardDetailId(Goods goods) {
		List<Goods> list = goodsDao.findGoodsByStandardDetailId(goods.getProductStandardDetailId());
		return list;
	}

	@Override
	public String getGoodsBatchNo() {
		String serialNum = serialNumService.newSerialNum(new CommonVariable("-","goods","goodsBatch","998"));
		String fullSerialNum = StringUtil.formatFillZeroLeft(3, serialNum);
		return DateUtil.format(new Date(),"yyyyMMddHHmmss")+fullSerialNum;
	}

	@Override
	public List<Goods> findGoodsByStandardDetailIdLimit(Goods goods, int limit) {
		return goodsDao.findGoodsByStandardDetailIdLimit(goods.getProductStandardDetailId(),limit);
	}
	
}

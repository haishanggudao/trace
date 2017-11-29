package cn.rfidcer.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.OutstockItem;
import cn.rfidcer.bean.OutstockMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.SaleOrder;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.LogisticsMapper;
import cn.rfidcer.dao.OutstockItemMapper;
import cn.rfidcer.dao.OutstockMainMapper;
import cn.rfidcer.dao.SaleItemMapper;
import cn.rfidcer.dao.SaleOrderMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.GroupDinnerOutStockService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

import com.alibaba.fastjson.JSONObject;

@Service
public class GroupDinnerOutStockServiceImpl implements GroupDinnerOutStockService {
	
	private Logger logger=LoggerFactory.getLogger(GroupDinnerOutStockServiceImpl.class);
	
	@Autowired
	private SaleOrderMapper saleOrderMapper;
	
	@Autowired
	private SaleItemMapper saleItemMapper;
	
	@Autowired
	private OutstockMainMapper outstockMainMapper;
	
	@Autowired
	private OutstockItemMapper outstockItemMapper;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private LogisticsMapper logisticsMapper;
	
	public ResultMsg addOrUpdateOutstockItem(OutstockItem outstockitem,User user,int rowCount) {
		logger.info("invoke addOrUpdate ,id is {0} .", outstockitem.getOutstockItemId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			if(goodsDao.getByGoodsId(outstockitem.getGoodsId())==null){
				resultMsg.setCode("0");
				resultMsg.setMsg("第："+rowCount+"行,商品需要选择不可以随便输入！");
				return resultMsg;
			}
			if (StringUtils.isEmpty(outstockitem.getOutstockItemId())) {
				outstockitem.setOutstockItemId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(outstockitem, user);//设置创建日期和修改日期	
				outstockItemMapper.insert(outstockitem);
			} else {
				CommonImportUtils.setUpdateTime(outstockitem, user);//设置修改日期	
				outstockItemMapper.updateByPrimaryKeySelective(outstockitem);
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}
	
	@Override
	public ResultMsg addOrUpdate(OutstockMain outstockMain,String outstockDetail, User user) {
		
		logger.info("invoke addOrUpdate ,id is {0} .", outstockMain.getOutstockMainId());
		ResultMsg resultMsg = new ResultMsg();
		if(StringUtil.isBlank(outstockMain.getLogisticsId()) || logisticsMapper.checkCountByLogisticsId(outstockMain.getLogisticsId())<1 ){
			resultMsg.setCode("0");
			resultMsg.setMsg("亲,物流企业必须要选择,不可以随便输入的！");
			return resultMsg;
		}
		
		int res=0;
		try {
			logger.info("新增团餐出库单："+outstockMain.getSaleOrder());
			SaleOrder saleOrder = outstockMain.getSaleOrder();
			saleOrder.setCompanyId(outstockMain.getCompanyId());//设置销售单 公司ID
			saleOrder.setLogisticsId(outstockMain.getLogisticsId());//设置销售单 物流企业
			saleOrder.setSaleOrderNo(outstockMain.getOutstockNum());//设置销售单号
			saleOrder.setCustomerId(outstockMain.getCompanyId());

		        
		        
		        
			outstockMain.setOutstockBatchNum(outstockMain.getOutstockNum());//出库批次号
			outstockMain.setOutstockDate(DateUtil.format(saleOrder.getOrderTime()));//出库日期
			outstockMain.setRegistDate(DateUtil.format(saleOrder.getOrderTime()));//登记日期
			if (StringUtils.isEmpty(outstockMain.getOutstockMainId())) {
				
				String saleOrderId = UUIDGenerator.generatorUUID();
				saleOrder.setSaleOrderId(saleOrderId);//销售单ID
				
				CommonImportUtils.setCreateAndUpdateTime(saleOrder, user);//设置创建日期和修改日期
				res = saleOrderMapper.insertSelective(saleOrder);
				if(res ==1){
					outstockMain.setOutstockMainId(UUIDGenerator.generatorUUID());
					outstockMain.setOutstockType("0");
					outstockMain.setStatus("0");
					outstockMain.setSaleOrderId(saleOrderId);//商品出库单上设置销售单ID
					CommonImportUtils.setCreateAndUpdateTime(outstockMain, user);//设置创建日期和修改日期
					res = outstockMainMapper.insertSelective(outstockMain);
				}
			} else {
				CommonImportUtils.setUpdateTime(outstockMain, user);//设置修改日期
				outstockMainMapper.updateByPrimaryKeySelective(outstockMain);
				if(!StringUtils.isEmpty(outstockMain.getSaleOrderId())){
					saleOrder.setSaleOrderId(outstockMain.getSaleOrderId());
					CommonImportUtils.setUpdateTime(saleOrder, user);//设置修改日期
					saleOrderMapper.updateByPrimaryKey(saleOrder);
				}
			}
			int  rowCount = 0;//详细列表的行数
			//商品出库详细信息装入商品出库单
			List<OutstockItem> outstockItem = JSONObject.parseArray(outstockDetail, OutstockItem.class);
			outstockMain.setOutstockItem(outstockItem);
			
			//保存详细
			for(OutstockItem outstockitem : outstockMain.getOutstockItem()){
				rowCount++;
				outstockitem.setOutstockMainId(outstockMain.getOutstockMainId());
				resultMsg = addOrUpdateOutstockItem(outstockitem,user,rowCount);
				//添加详细的时候如果遇到错误弹回错误信息
				if(resultMsg.getCode().equals("0"))
				{
					return resultMsg;
				}
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("保存成功");
	
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		return resultMsg;

	}

	@Override
	public List<OutstockMain> findAllGroupDinnerOutstock(Page page,OutstockMain outstockMain) {
	
		return outstockMainMapper.findAllGroupDinnerOutstock(page, outstockMain);

	}


	@Override
	public ResultMsg delete(OutstockMain outstockMain) {
		logger.info("删除出库单ID：" + outstockMain.getOutstockMainId() +" 销售单ID："+outstockMain.getSaleOrderId() );
		ResultMsg resultMsg = new ResultMsg();
		try {
			outstockMainMapper.deleteByPrimaryKey(outstockMain.getOutstockMainId());
			outstockItemMapper.deleteByOutstockMainId(outstockMain.getOutstockMainId());
			saleItemMapper.deleteByMainid(outstockMain.getSaleOrderId());
			saleOrderMapper.deleteByPrimaryKey(outstockMain.getSaleOrderId());
			resultMsg.setCode("1");
			resultMsg.setMsg("删除成功");
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}
	
}

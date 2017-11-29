package cn.rfidcer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.Trace;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.TraceMapper;
import cn.rfidcer.enums.TraceType;
import cn.rfidcer.service.TraceTypeService;

@Service
public class TraceTypeServiceImpl implements TraceTypeService{

	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private TraceMapper traceMapper;
	
	@Override
	public int insertTraceType(String traceCode, TraceType traceType,
			String createBy) {
		Trace trace = new Trace(traceCode, createBy);
		trace.setType(traceType.getValue());
		return traceMapper.insert(trace);
	}

	@Override
	public int insertTraceTypeByDeliverType(String goodsId, User currentUser,
			String goodsCode) {
		Goods goods = goodsDao.getByGoodsId(goodsId);
		Trace trace = new Trace(goodsCode,currentUser.getId());
		if(goods.getDeliverType().equals("0")){
			trace.setType(TraceType.GOODSDETAIL.getValue());
		}else{
			trace.setType(TraceType.GROUPDINNER.getValue());
		}
		return traceMapper.insert(trace);
	}

}

package cn.rfidcer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.ProductStandardDao;
import cn.rfidcer.service.CommonVariableService;
import cn.rfidcer.service.VerifyInfoService;

@Service
public class VerifyInfoServiceImpl implements VerifyInfoService {

	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductStandardDao productStandardDao;
	@Override
	public ResultMsg verifyProduct(Product product) {
		boolean b = productMapper.checkProductExists(product)>0?true:false;
		ResultMsg rmsg = new ResultMsg();
		rmsg.setCode("1");
		if(!b){
			rmsg.setCode("0");
			rmsg.setMsg("产品选择有误,请选择列表框内的产品！");
			return  rmsg;		
		}
		return rmsg;
	}
	
	@Override
	public ResultMsg verifyStandard(ProductStandard productStandard) {
		boolean b =  productStandardDao.getProductStandardCount(productStandard)>0?true:false;
		ResultMsg rmsg = new ResultMsg();
		rmsg.setCode("1");
		if(!b){
			rmsg.setCode("0");
			rmsg.setMsg("产品规格选择有误,请选择列表框内的规格！");
			return  rmsg;		
		}
		return rmsg;
	}



}
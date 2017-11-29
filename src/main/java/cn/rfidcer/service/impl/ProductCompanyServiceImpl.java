package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.dao.ProductCompanyMapper;
import cn.rfidcer.dto.ProductCompanyParam;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductCompanyService;

import com.alibaba.fastjson.JSONObject;

@Service
public class ProductCompanyServiceImpl extends BaseServiceImpl<ProductCompany> implements ProductCompanyService {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductCompanyMapper productCompanyMapper;

	@Override
	public List<ProductCompany> findAllProductCompanyByProductId(ProductCompany productCompany) {
        List<ProductCompany> lst = productCompanyMapper.findAllProductCompanyByProductId(productCompany);
		return lst;
	}


	@Override
	public ResultMsg deleteByParam(ProductCompany productCompany) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int res = productCompanyMapper.delete(productCompany);
			if (res == 1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("删除成功");
			} else {
				resultMsg.setCode("0");
				resultMsg.setMsg("删除失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}

	@Override
	public ResultMsg add(ProductCompany productCompany) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			int res = productCompanyMapper.insert(productCompany);
			if (res == 1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("添加成功");
			} else {
				resultMsg.setCode("0");
				resultMsg.setMsg("添加失败");
			}
		}catch (DuplicateKeyException e) {
			resultMsg.setCode("0");
			resultMsg.setMsg("已添加过此企业");
		}catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		
		
		
		return resultMsg;			
	}


	@Override
	public List<Product> findAllNotCheckedProducts(Page page, ProductCompanyParam productCompanyParam) {
		return productCompanyMapper.findAllNotCheckedProducts(page,productCompanyParam);
	}


	@Override
	public ResultMsg addProducts(String products, String companyId) {
		ResultMsg resultMsg = new ResultMsg();
		List<Product> list = JSONObject.parseArray(products, Product.class);
		try {
			for (Product product : list) {
				ProductCompany record = new ProductCompany();
				record.setCompanyId(companyId);
				record.setProductId(product.getProductId());
				productCompanyMapper.insert(record);
			}
			resultMsg.setCode("1");
			resultMsg.setMsg("添加成功");
		} catch (Exception e) {
			logger.error("关联供应商产品异常",e);
			resultMsg.setCode("0");
			resultMsg.setMsg("添加异常");
		}
		return resultMsg;
	}

}

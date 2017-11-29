package cn.rfidcer.service.impl.yz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CustomersMapper;
import cn.rfidcer.service.impl.ProductBaseServiceImpl;
import cn.rfidcer.service.yz.ProductYzService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProductYzServiceImpl extends ProductBaseServiceImpl implements ProductYzService  {
	
	private Logger logger = LoggerFactory.getLogger(ProductYzServiceImpl.class);
	@Autowired
	private CustomersMapper  customersMapper;
	
	@Override
	public ResultMsg addOrUpdateProduct(Product product, User user) {
		logger.info("新增或修改产品：" + product);
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		int iIsDuplicatedName = productDao.checkProductNameExists(product);
		if (iIsDuplicatedName == 0) {
			saveProductFile(product);
			if (StringUtils.isEmpty(product.getProductId())) {
				product.setProductId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(product, user);// 设置修改时间和创建时间
				res = productDao.insertSelective(product);
				if(res>=1){
					//添加自身企业
					ProductCompany productCompany = new ProductCompany();
					productCompany.setCompanyId(product.getCompanyId());
					productCompany.setProductId(product.getProductId());
					res = productCompanyMapper.insert(productCompany);
					
					List<Customers>  lstCustomers = customersMapper.findCustomerList(product.getCompanyId());
					for(Customers customers : lstCustomers){
						productCompany = new ProductCompany();
						productCompany.setCompanyId(customers.getCustCompanyId());
						productCompany.setProductId(product.getProductId());
						res = productCompanyMapper.insert(productCompany);
					}
				}
				info = "新增产品";
			} else {
				CommonImportUtils.setUpdateTime(product, user);// 设置修改时间
				res = productDao.updateByPrimaryKeySelective(product);
				info = "修改产品";
			}
		} else {
			resultMsg.setCode("-2");
			resultMsg.setMsg("产品名称已存在");
		}

		if (res == 1) {
			resultMsg.setCode("1");
			resultMsg.setMsg(info + "成功" + " ");
		} else if (info != null) {
			resultMsg.setCode("0");
			resultMsg.setMsg(info + "失败");
		}

		return resultMsg;
	}

	@Override
	public void createAP(Product pc) {
		productDao.insertSelective(pc);
	}
	
}

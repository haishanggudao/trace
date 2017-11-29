package cn.rfidcer.service.impl.yz;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.CustomersMapper;
import cn.rfidcer.dao.ProductCompanyMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.service.yz.SellStoreService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class SellStoreServiceImpl implements SellStoreService {

	private Logger logger = LoggerFactory.getLogger(SellStoreServiceImpl.class);
	
	@Autowired
	private CustomersMapper customersmapper;
	
	@Autowired
	private CompanyMapper companyMapper; 
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductCompanyMapper productCompanyMapper;
	
	@Override
	public ResultMsg addOrUpdate(Customers customers, User user) { 
		ResultMsg resultMsg = new ResultMsg();
		try {
			int num = customersmapper.checkCustomersExistsByCustomersName(customers);
			if(num!=0 && !org.apache.commons.lang3.StringUtils.isEmpty(customers.getCustomerId())){
				resultMsg.setCode("2");
				resultMsg.setMsg("客户企业已存在");
			}else{
				String companyName = customers.getCustCompanyName();
				Company company = companyMapper.selectCompanyByName(companyName);
				if(company == null) {
					company = companyMapper.selectByPrimaryKey(customers.getCustCompanyId());
				}
				logger.info("新建客户企业信息 ");
				if(company==null){
					company=new Company();
					company.setCompanyid(UUIDGenerator.generatorUUID());
					company.setName(companyName);
					CommonImportUtils.setCreateAndUpdateTime(company, user);//设置创建日期和修改日期
					company.setStatus("0");
					companyMapper.insert(company);
				}
				customers.setCustomerAlias(companyName);
				if (StringUtils.isEmpty(customers.getCustomerId())) {
					customers.setCustCompanyId(company.getCompanyid());
					customers.setCustomerId(UUIDGenerator.generatorUUID());
					customers.setStatus("0");
					CommonImportUtils.setCreateAndUpdateTime(customers, user);//设置创建日期和修改日期
					if(!org.apache.commons.lang3.StringUtils.isEmpty(company.getCode())) {
						customers.setCustCode(company.getCode() + RandomStringUtils.randomNumeric(3));
					}
					customersmapper.insertSelective(customers);
					resultMsg.setMsg("新增客户企业成功");
					
					// DAO action: insert productIds and companyIds
					Product myProduct = new Product();
					myProduct.setCompanyId(customers.getCompanyId() );
					List<Product> myProducts = productMapper.getProductList(null, myProduct);
					System.out.println(myProducts.size());
					for (Product product : myProducts) {
						ProductCompany record = new ProductCompany();
						record.setProductId( product.getProductId());
						record.setCompanyId(customers.getCustCompanyId());
						productCompanyMapper.insertSelective(record);
					}
					
				} else {
					if (StringUtils.isEmpty(customers.getCustCode())
							&& !org.apache.commons.lang3.StringUtils.isEmpty(company.getCode())) {
						customers.setCustCode(org.apache.commons.lang3.StringUtils.trimToEmpty(company.getCode())
								+ RandomStringUtils.randomNumeric(3));
					}
					customers.setCustCompanyId(company.getCompanyid());
					CommonImportUtils.setUpdateTime(customers, user);//设置修改日期
					customersmapper.updateByPrimaryKeySelective(customers);
					resultMsg.setMsg("修改客户企业成功");
				}
				
				Company com = customers.getCompany();
				// 2016.06.16 新加需求
				if (company != null && com != null) {
					company.setCode(com.getCode());
					company.setCbusinessaddress(com.getCbusinessaddress());
					company.setCbusinesslicense(com.getCbusinesslicense());
					company.setCbusinessscope(com.getCbusinessscope());
					company.setCcustomercategories(com.getCcustomercategories());
					company.setCeffectivedate1(com.getCeffectivedate1());
					company.setCeffectivedate2(com.getCeffectivedate2());
					company.setChygienelicense(com.getChygienelicense());
					company.setCidnumb(com.getCidnumb());
					company.setClegalpersonaddress(com.getClegalpersonaddress());
					company.setCliquorbusinesslicense(com.getCliquorbusinesslicense());
					company.setCname(com.getCname());
					company.setCnature(com.getCnature());
					company.setCregistrationnumber(com.getCregistrationnumber());
					companyMapper.updateByPrimaryKeySelective(company);
				}
				
				resultMsg.setCode("1");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg("客户信息异常");
			logger.error("客户信息异常",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return resultMsg;
	}
	
}

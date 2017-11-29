package cn.rfidcer.service.impl.fruit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.AreaInfoMapper;
import cn.rfidcer.dao.CustomersMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.AreaInfoService;
import cn.rfidcer.service.fruit.ProductFruitService;
import cn.rfidcer.service.impl.ProductBaseServiceImpl;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProductFruitServiceImpl extends ProductBaseServiceImpl implements ProductFruitService  {
	
	private Logger logger = LoggerFactory.getLogger(ProductFruitServiceImpl.class);
	@Autowired
	private CustomersMapper  customersMapper;
	@Autowired
	private AreaInfoService  areaInfoService;
	@Autowired
	private AreaInfoMapper  areaInfoMapper;
	
	@Override
	public ResultMsg addOrUpdateProduct(Product product, User user) {
		logger.info("新增或修改产品：" + product);
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		int rstArea = areaInfoMapper.findExitId(product.getMadein());
		if(rstArea<1){
			resultMsg.setCode("-3");
			resultMsg.setMsg("请选择正确的产地！");
			return resultMsg;
		}
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
					//添加规格表 ProductStandard
					//添加规格明细 productStandardDetail
					
					ProductStandard productStandard = new ProductStandard();
					productStandard.setProductCategoryId(product.getProductCategoryId());
					productStandard.setProductStandardName(product.getProductStandardName());
					ProductStandard productStandards =  standardDao.checkProductStandardExists(productStandard);
					//检测规格存在
					if(null==productStandards){
						productStandard.setProductStandardId(UUIDGenerator.generatorUUID());
						CommonImportUtils.setCreateAndUpdateTime(productStandard, user);// 设置修改时间和创建时间
						res = standardDao.insertSelective(productStandard);
						if(res>0){
							productStandards = productStandard;
						}
					}
					//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					ProductStandardDetail productStandardDetail = new ProductStandardDetail();
					productStandardDetail.setProductStandard(productStandards);
					productStandardDetail.setProductStandardDetailId(UUIDGenerator.generatorUUID());
					productStandardDetail.setCompanyId(product.getCompanyId());
					productStandardDetail.setSalePrice(product.getSalePrice());
					productStandardDetail.setProduct(product);
					productStandardDetail.setProductStandardNum("1");
					CommonImportUtils.setCreateAndUpdateTime(productStandardDetail, user);// 设置修改时间和创建时间
					res = standardDetailDao.insertSelective(productStandardDetail);
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
				ProductStandard productStandard = new ProductStandard();
				productStandard.setProductCategoryId(product.getProductCategoryId());
				productStandard.setProductStandardName(product.getProductStandardName());
				ProductStandard productStandards =  standardDao.checkProductStandardExists(productStandard);
				//检测规格存在
				if(null==productStandards){
					productStandard.setProductStandardId(UUIDGenerator.generatorUUID());
					CommonImportUtils.setCreateAndUpdateTime(productStandard, user);// 设置修改时间和创建时间
					res = standardDao.insertSelective(productStandard);
					if(res>0){
						productStandards = productStandard;
					}
				}
				ProductStandardDetail productStandardDetail = new ProductStandardDetail();
				productStandardDetail.setProductStandard(productStandards);
				productStandardDetail.setProductStandardDetailId(product.getProductStandardDetailId());
				productStandardDetail.setSalePrice(product.getSalePrice());
				CommonImportUtils.setUpdateTime(productStandardDetail, user);// 设置修改时间
				res = standardDetailDao.updateProductStandardDetail(productStandardDetail);
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
	
	@Override
	public List<Product> findProductListByQuery(Page page, Product product) {
		return productDao.findProductFruitListByQuery(page, product);
	}
	@Override
	public List<Product> getProductList(Page page, Product product) {
		
		if ( StringUtil.isBlank(product.getProductCategoryId()) &&  product.getCategory() != null) { 
			if ( !StringUtil.isBlank(product.getCategory().getProductCategoryId()) ) {
				product.setProductCategoryId(product.getCategory().getProductCategoryId());
			} 
		}
		return productDao.findProductFruitList(page, product);
	}
	@Override
	public ResultMsg delProduct(Product product) {
		logger.info("Del product ID:" + product.getProductId());
		ResultMsg resultMsg = new ResultMsg();
        String productStandardId=product.getProductStandardDetailId();
		String delProductID = product.getProductId();

		try {
			int res = productDao.deleteWithStatusByPrimaryKey(delProductID);
			int resm=standardDetailDao.delProductStandardDetailByProductStandardId(productStandardId);
			
			if (res == 1) {
				if (resm==1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("删除产品成功");
				}
			} else {
				resultMsg.setMsg("删除产品失败");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前产品无法删除");
		}
		return resultMsg;
	}

	
}

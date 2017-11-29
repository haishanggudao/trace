package cn.rfidcer.service.impl;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductCategoryDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductCategoryService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	private Logger logger=LoggerFactory.getLogger(ProductCategoryServiceImpl.class);
	
	@Autowired
	private ProductCategoryDao categoryDao;
	
	@Override
	public List<ProductCategory> findAll(Page page,ProductCategory productCategory) {
		return categoryDao.findAll(page,productCategory);
	} 
	
	@Override
	public ResultMsg createOrUpdateProductCategory(ProductCategory productCategory,User user) {
		int res=0;
		String msg=null;
		if(StringUtils.isEmpty(productCategory.getProductCategoryId())){
			productCategory.setProductCategoryId(UUIDGenerator.generatorUUID());
			CommonImportUtils.setCreateAndUpdateTime(productCategory, user);//设置创建日期和修改日期	
			logger.info("新增产品分类："+productCategory);
			msg="新增产品分类";
			res = categoryDao.createProductCategory(productCategory);
		}else{
			logger.info("修改产品分类："+productCategory);
			msg="修改产品分类";
			CommonImportUtils.setUpdateTime(productCategory, user);//设置修改日期	
			res = categoryDao.updateProductCategory(productCategory);
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}

	@Override
	public ResultMsg delProductCategory(ProductCategory productCategory) {
		logger.info("删除产品分类，ID："+productCategory.getProductCategoryId());
		int res = categoryDao.deleteWithStatusByPrimaryKey(productCategory.getProductCategoryId());
		return ResultMsgUtil.getReturnMsg(res, "删除产品分类");
	}

	@Override
	public ResultMsg updateProductCategory(ProductCategory productCategory) {
		logger.info("修改产品分类，ID："+productCategory);
		int res = categoryDao.updateProductCategory(productCategory);
		return ResultMsgUtil.getReturnMsg(res, "修改产品分类");
	}

	@Override
	public void createOrUpdate(List<ProductCategory> pcs) throws Exception {
		try {
			if (pcs != null && !pcs.isEmpty()) {
				for (ProductCategory productCategory : pcs) {
					categoryDao.createProductCategory(productCategory);
				}
			} 
		} catch (Exception e) {
			throw new Exception("数据保存失败");
		}
	}

	@Override
	public void createAPC(ProductCategory pc) {
		categoryDao.insertSelective(pc);
	}


}

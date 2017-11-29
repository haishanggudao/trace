package cn.rfidcer.service.impl;


import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import cn.rfidcer.bean.ProductDetailMap;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductDetailMapMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductDetailMapService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;

@Service
public class ProductDetailMapServiceImpl implements ProductDetailMapService {
	
	private Logger logger=LoggerFactory.getLogger(ProductDetailMapServiceImpl.class);
	
	@Autowired
	private ProductDetailMapMapper productDetailMapDao;

	@Override
	public List<ProductDetailMap> getProductDetailMapList(Page page, ProductDetailMap productDetailMap) { 
		return productDetailMapDao.getProductDetailMapList(page, productDetailMap);
	}

	@Override
	public ResultMsg addOrUpdateProductDetailMap(ProductDetailMap productDetailMap, User user) { 
		logger.info("新增或修改产品附加属性："+productDetailMap); 
		String info=null;
		int res=0;
		if ( StringUtils.isEmpty(productDetailMap.getProductDetailMapId()) ) {
			String pkUUID = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			productDetailMap.setProductDetailMapId(pkUUID);
			CommonImportUtils.setCreateAndUpdateTime(productDetailMap, user);//设置修改时间和创建时间
			res = productDetailMapDao.insert(productDetailMap);
			info="新增产品附加属性";
		} else { 
			CommonImportUtils.setUpdateTime(productDetailMap, user);//设置修改时间
			res =productDetailMapDao.updateByPrimaryKey(productDetailMap);
			info="修改产品产品附加属性";
		}
		return ResultMsgUtil.getReturnMsg(res, info);
	}

	@Override
	public ResultMsg delProductDetailMap(ProductDetailMap productDetailMap) {
		logger.info("删除产品附加属性，ID："+productDetailMap.getProductDetailMapId() );
		int res = 0;
		ResultMsg resultMsg = new ResultMsg();
		String delProductDetailMapId = productDetailMap.getProductDetailMapId();
		try {
			res=productDetailMapDao.deleteByPrimaryKey( delProductDetailMapId );
			resultMsg.setCode(res+"");
			if(res==1){
				resultMsg.setMsg("删除产品附加属性");
			}else{
				resultMsg.setMsg("删除产品附加属性");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前产品附加属性无法删除");
		}
		return resultMsg;
	}

}

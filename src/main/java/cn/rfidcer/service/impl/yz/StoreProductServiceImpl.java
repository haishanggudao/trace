package cn.rfidcer.service.impl.yz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.Product;
import cn.rfidcer.dao.yz.StoreProductMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.yz.StoreProductService;

@Service
public class StoreProductServiceImpl implements StoreProductService{

	@Autowired
	private StoreProductMapper storeProductMapper;
	
	@Override
	public List<Product> findAllList(Page page, Product product) {
		return storeProductMapper.findAllList(page,product);
	}

}

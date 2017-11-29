package cn.rfidcer.service.yz;

import java.util.List;

import cn.rfidcer.bean.Product;
import cn.rfidcer.interceptor.Page;

public interface StoreProductService {

	List<Product> findAllList(Page page,Product product);
}

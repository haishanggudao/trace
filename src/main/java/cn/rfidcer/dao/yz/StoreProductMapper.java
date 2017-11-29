package cn.rfidcer.dao.yz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Product;
import cn.rfidcer.interceptor.Page;

public interface StoreProductMapper {

	List<Product> findAllList(Page page,@Param("product") Product product);

}

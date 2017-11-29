package cn.rfidcer.dao.yz;

import java.util.List;

import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.yz.YzProduct;
import cn.rfidcer.dto.ProductParam;

public interface YzProductMapper {

	List<YzProduct> getUpdateProductByIdAndTime(ProductParam productParam);
	
	List<ProductCategory> getCategoryByIdAndTime(ProductParam productParam);
}

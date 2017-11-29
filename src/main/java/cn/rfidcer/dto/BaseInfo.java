package cn.rfidcer.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.ResultEntity;
import cn.rfidcer.bean.Supplier;
import cn.rfidcer.bean.yz.YzProduct;

@JsonInclude(value=Include.NON_NULL)
public class BaseInfo implements ResultEntity{

	private List<YzProduct> product;
	
	private List<ProductCategory> productCategory;
	
	private List<Supplier> supplier;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date lastUpdateTime;
	
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public List<YzProduct> getProduct() {
		return product;
	}

	public void setProduct(List<YzProduct> product) {
		this.product = product;
	}

	public List<ProductCategory> getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(List<ProductCategory> productCategory) {
		this.productCategory = productCategory;
	}

	public List<Supplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<Supplier> supplier) {
		this.supplier = supplier;
	}
	
	
}

package cn.rfidcer.bean;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@Table(name = "t_test")
@JsonInclude(value = Include.NON_NULL)
public class TestBean  {
	
	@GeneratedValue(generator = "UUID")
	@Column(name = "productId")
	private String productId;
	@Column(name = "productName")
	private String productName;
	@Column(name = "productCode")
	private String productCode;
	@Column(name = "productShortName")
	private String productShortName;
	
	@Transient
	private int testId; 
	
	public TestBean() { 
	}

	public TestBean(int testId, String productName, String productShortName) { 
		this.productName = productName;
		this.productShortName = productShortName;
		this.testId = testId;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductShortName() {
		return productShortName;
	}
	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}
	
	/**
	 * 获取 #{bare_field_comment}
	 * @return the testId
	 */
	public int getTestId() {
		return testId;
	}
	/**
	 * 设置 #{bare_field_comment}
	 * @param testId the testId to set
	 */
	public void setTestId(int testId) {
		this.testId = testId;
	}
	@Override
	public String toString() {
		return "TestBean [productId=" + productId + ", productName="
				+ productName + ", productCode=" + productCode
				+ ", productShortName=" + productShortName + "]";
	}


}
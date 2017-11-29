package cn.rfidcer.bean;

import java.math.BigDecimal;
import java.util.Date;

public class InstockLog {

	private String id;
	private String goodsId;
	private String companyId;
	private String instockNum;
	private String instockBatchNum;
	private BigDecimal price;
	private Date createTime;
	private String createBy;
	private Date updateTime;
	private String updateBy;
    private BigDecimal num;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getInstockNum() {
		return instockNum;
	}
	public void setInstockNum(String instockNum) {
		this.instockNum = instockNum;
	}
	public String getInstockBatchNum() {
		return instockBatchNum;
	}
	public void setInstockBatchNum(String instockBatchNum) {
		this.instockBatchNum = instockBatchNum;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public BigDecimal getNum() {
		return num;
	}
	public void setNum(BigDecimal num) {
		this.num = num;
	}
}
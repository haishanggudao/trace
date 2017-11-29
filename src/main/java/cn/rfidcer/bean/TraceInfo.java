package cn.rfidcer.bean;

import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.GoodsQCMaterials;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
  * @Title: 追溯信息
  * @author xzm
  * @date 2016年6月29日 上午9:17:31 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
@JsonInclude(value = Include.NON_NULL)
public class TraceInfo extends BaseEntity {
	/**商品信息*/
	private GoodsInfo goodsInfo;
	/**商品质检信息*/
	private GoodsQC goodsQC;
	/**商品质检信息*/
	private GoodsQCMaterials goodsQCInfo;
	/**加工信息*/
	private ProcessMain processMain;
	/**出库信息*/
	private OutstockInfo outstockInfo;
	/**屠宰信息*/
	private Slaughterhouse slaughterhouse;
	/**入库信息*/
	private InstockInfo instockInfo;
	/**企业信息*/
	private Company company;// 零售信息
	/**
	 * 客户信息
	 */
	private Customers customers;
	
	/**获取客户信息
	 * @return the 客户信息
	 */
	public Customers getCustomers() {
		return customers;
	}
	/**设置客户信息
	 * @param customers the 客户信息 to set
	 */
	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	/**
	 * 零售明细
	 */
	private StoreSaleItem storeSaleItem;
	/**
	 * 销售单信息
	 */
	private StoreSaleOrder storeSaleOrder;
	
	/**
	 * 质检报告的图片
	 */
	private Instockqc instockqc;
	
	
	
	/**获取质检报告的图片
	 * @return the 质检报告的图片
	 */
	public Instockqc getYzInstockqc() {
		return instockqc;
	}
	/**设置质检报告的图片
	 * @param yzInstockqc the 质检报告的图片 to set
	 */
	public void setYzInstockqc(Instockqc instockqc) {
		this.instockqc = instockqc;
	}
	/**获取零售明细
	 * @return the 零售明细
	 */
	public StoreSaleItem getStoreSaleItem() {
		return storeSaleItem;
	}
	/**设置零售明细
	 * @param storeSaleItem the 零售明细 to set
	 */
	public void setStoreSaleItem(StoreSaleItem storeSaleItem) {
		this.storeSaleItem = storeSaleItem;
	}
	/**获取销售单信息
	 * @return the 销售单信息
	 */
	public StoreSaleOrder getStoreSaleOrder() {
		return storeSaleOrder;
	}
	/**设置销售单信息
	 * @param storeSaleOrder the 销售单信息 to set
	 */
	public void setStoreSaleOrder(StoreSaleOrder storeSaleOrder) {
		this.storeSaleOrder = storeSaleOrder;
	}
	/**
	 * 获取 商品信息
	 * @return goodsInfo
	 */
	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}
	/**
	 * 设置 商品信息
	 * @param goodsInfo 商品信息
	 */
	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	/**
	 * 获取 商品质检信息
	 * @return goodsQC
	 */
	public GoodsQC getGoodsQC() {
		return goodsQC;
	}
	/**
	 * 设置 商品质检信息
	 * @param goodsQC 商品质检信息
	 */
	public void setGoodsQC(GoodsQC goodsQC) {
		this.goodsQC = goodsQC;
	}
	/**
	 * 获取 加工信息
	 * @return processMain
	 */
	public ProcessMain getProcessMain() {
		return processMain;
	}
	/**
	 * 设置 加工信息
	 * @param processMain 加工信息
	 */
	public void setProcessMain(ProcessMain processMain) {
		this.processMain = processMain;
	}
	/**
	 * 获取 出库信息
	 * @return outstockInfo
	 */
	public OutstockInfo getOutstockInfo() {
		return outstockInfo;
	}
	/**
	 * 设置 出库信息
	 * @param outstockInfo 出库信息
	 */
	public void setOutstockInfo(OutstockInfo outstockInfo) {
		this.outstockInfo = outstockInfo;
	}
	/**
	 * 获取 屠宰信息
	 * @return slaughterhouse
	 */
	public Slaughterhouse getSlaughterhouse() {
		return slaughterhouse;
	}
	/**
	 * 设置 屠宰信息
	 * @param slaughterhouse 屠宰信息
	 */
	public void setSlaughterhouse(Slaughterhouse slaughterhouse) {
		this.slaughterhouse = slaughterhouse;
	}
	/**
	 * 获取 入库信息
	 * @return instockInfo
	 */
	public InstockInfo getInstockInfo() {
		return instockInfo;
	}
	/**
	 * 设置 入库信息
	 * @param instockInfo 入库信息
	 */
	public void setInstockInfo(InstockInfo instockInfo) {
		this.instockInfo = instockInfo;
	}
	/**
	 * 获取 企业信息
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * 设置 企业信息
	 * @param company 企业信息
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public GoodsQCMaterials getGoodsQCInfo() {
		return goodsQCInfo;
	}
	public void setGoodsQCInfo(GoodsQCMaterials goodsQCInfo) {
		this.goodsQCInfo = goodsQCInfo;
	}
	public Instockqc getInstockqc() {
		return instockqc;
	}
	public void setInstockqc(Instockqc instockqc) {
		this.instockqc = instockqc;
	}
	@Override
	public String toString() {
		return "TraceInfo [goodsInfo=" + goodsInfo + ", goodsQC=" + goodsQC
				+ ", processMain=" + processMain + ", outstockInfo="
				+ outstockInfo + ", slaughterhouse=" + slaughterhouse
				+ ", instockInfo=" + instockInfo + ", company=" + company + "]";
	}


}

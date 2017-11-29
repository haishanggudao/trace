package cn.rfidcer.bean.yz;

import java.util.List;

import cn.rfidcer.bean.BaseEntity;
import cn.rfidcer.bean.SaleItem;


/**   
 * @Title: StockOrderInfo.java 
 * @Package cn.rfidcer.bean.yz 
 * @Description: 要货单
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月7日 下午1:44:40 
 * @version V1.0   
*/
public class StockOrderInfo extends BaseEntity {
	/**门店编号*/
	private String storeNo;
	/**客户ID*/
	private String customerId;
	/**企业ID*/
	private String companyId;
	/**要货明细*/
	private List<SaleItem> itemList;
	/**采购单ID*/
	private String purchaseOrderId;

	/**
	 * 获取 门店编号
	 * @return storeNo
	 */
	public String getStoreNo() {
		return storeNo;
	}
	/**
	 * 设置 门店编号
	 * @param storeNo 门店编号
	 */
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	/**
	 * 获取 客户ID
	 * @return customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 设置 客户ID
	 * @param customerId 客户ID
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取 企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 企业ID
	 * @param companyId 企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 要货明细
	 * @return itemList
	 */
	public List<SaleItem> getItemList() {
		return itemList;
	}
	/**
	 * 设置 要货明细
	 * @param itemList 要货明细
	 */
	public void setItemList(List<SaleItem> itemList) {
		this.itemList = itemList;
	}
	
	@Override
	public String toString() {
		return "StockOrderInfo [storeNo=" + storeNo + ", customerId=" + customerId
				+ ", companyId=" + companyId + ", itemList=" + itemList + "]";
	}
	
}

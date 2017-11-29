package cn.rfidcer.bean.yz;

import java.util.List;

/**   
* @Title: WsSale.java 
* @Package cn.rfidcer.bean.yz 
* @Description: iOS-WS-POJO 销售信息, 包括主单和明细 
* @author jie.jia
* @Copyright Copyright
* @date 2016年8月29日 下午2:42:51 
* @version V1.0   
*/
public class WsSale {
	
	/**
	 * 销售单信息
	 */
	private WsSaleOrder saleInfo;
	
	/**
	 * 销售单明细列表
	 */
	private List<WsSaleDetail> saleDetailList;

	/**
	 * 获取 销售单信息
	 * @return the saleInfo
	 */
	public WsSaleOrder getSaleInfo() {
		return saleInfo;
	}

	/**
	 * 设置 销售单信息
	 * @param saleInfo the saleInfo to set
	 */
	public void setSaleInfo(WsSaleOrder saleInfo) {
		this.saleInfo = saleInfo;
	}

	/**
	 * 获取 销售单明细列表
	 * @return the saleDetailList
	 */
	public List<WsSaleDetail> getSaleDetailList() {
		return saleDetailList;
	}

	/**
	 * 设置 销售单明细列表
	 * @param saleDetailList the saleDetailList to set
	 */
	public void setSaleDetailList(List<WsSaleDetail> saleDetailList) {
		this.saleDetailList = saleDetailList;
	}

	 
}

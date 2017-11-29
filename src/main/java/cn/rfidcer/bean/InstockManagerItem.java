package cn.rfidcer.bean;

/**   
  * @Title: #进货登记
  * @author xzm
  * @date 2016年6月27日 下午3:42:54 
  * @Copyright Copyright
  * @version V1.0   
*/
/**   
* @Title: InstockManagerItem.java 
* @Package cn.rfidcer.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author jie.jia
* @Copyright Copyright
* @date 2016年7月14日 上午10:43:26 
* @version V1.0   
*/
public class InstockManagerItem extends BaseEntity{
	/**入库明细单ID*/
	private String instockItemId;
	/**入库数量*/
	private String instockNum;
	/**供应商名称*/
	private String supplierAlias;
	/**入库单*/
	private InstockMain instockMain;
	/**搜索入库日期开始*/
	private String startDate;
	/**搜索入库日期结束*/
	private String endDate;
	/**产品规格明细*/
	private ProductStandardDetail standardDetail;
	/**
	 * 所属companyId;
	 */
	private String companyId;
	/**
	 * 获取 入库明细单ID
	 * @return instockItemId
	 */
	public String getInstockItemId() {
		return instockItemId;
	}
	/**
	 * 设置 入库明细单ID
	 * @param instockItemId 入库明细单ID
	 */
	public void setInstockItemId(String instockItemId) {
		this.instockItemId = instockItemId;
	}
	/**
	 * 获取 入库数量
	 * @return instockNum
	 */
	public String getInstockNum() {
		return instockNum;
	}
	/**
	 * 设置 入库数量
	 * @param instockNum 入库数量
	 */
	public void setInstockNum(String instockNum) {
		this.instockNum = instockNum;
	}
	/**
	 * 获取 供应商名称
	 * @return supplierAlias
	 */
	public String getSupplierAlias() {
		return supplierAlias;
	}
	/**
	 * 设置 供应商名称
	 * @param supplierAlias 供应商名称
	 */
	public void setSupplierAlias(String supplierAlias) {
		this.supplierAlias = supplierAlias;
	}
	/**
	 * 获取 入库单
	 * @return instockMain
	 */
	public InstockMain getInstockMain() {
		return instockMain;
	}
	/**
	 * 设置 入库单
	 * @param instockMain 入库单
	 */
	public void setInstockMain(InstockMain instockMain) {
		this.instockMain = instockMain;
	}
	/**
	 * 获取 搜索入库日期开始
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * 设置 搜索入库日期开始
	 * @param startDate 搜索入库日期开始
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取 搜索入库日期结束
	 * @return endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 设置 搜索入库日期结束
	 * @param endDate 搜索入库日期结束
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取 产品规格明细
	 * @return standardDetail
	 */
	public ProductStandardDetail getStandardDetail() {
		return standardDetail;
	}
	/**
	 * 设置 产品规格明细
	 * @param standardDetail 产品规格明细
	 */
	public void setStandardDetail(ProductStandardDetail standardDetail) {
		this.standardDetail = standardDetail;
	}
	
	/**
	 * 获取 所属companyId;
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 所属companyId;
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InstockManagerItem [instockItemId=" + instockItemId + ", instockNum=" + instockNum + ", supplierAlias="
				+ supplierAlias + ", instockMain=" + instockMain + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", standardDetail=" + standardDetail + ", companyId=" + companyId + "]";
	}
	
	
	
}

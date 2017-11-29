package cn.rfidcer.bean.yz;

/**   
* @Description:羽众酒业产品分类
* @author 席志明
* @Copyright Copyright
* @date 2016年8月29日 下午2:08:46 
* @version V1.0   
*/
public class YzProductType {

	/**
	 * 产品ID
	 */
	private String guid;
	/**
	 * 序号
	 */
	private String typeNo;
	/**
	 * 产品分类名称
	 */
	private String goodsTypeName;
	/**获取产品ID
	 * @return the 产品ID
	 */
	public String getGuid() {
		return guid;
	}
	/**设置产品ID
	 * @param guid the 产品ID to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**获取序号
	 * @return the 序号
	 */
	public String getTypeNo() {
		return typeNo;
	}
	/**设置序号
	 * @param typeNo the 序号 to set
	 */
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	/**获取产品分类名称
	 * @return the 产品分类名称
	 */
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	/**设置产品分类名称
	 * @param goodsTypeName the 产品分类名称 to set
	 */
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	
	
}

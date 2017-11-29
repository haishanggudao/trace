package cn.rfidcer.bean;

/**   
  * @Title: 包装商品明细表
  * @author jgx
  * @date 2016年6月27日 下午6:48:18 
  * @Copyright Copyright
  * @version V1.0   
*/
public class PackageGoodsItem extends BaseEntity {
	/**包装明细ID*/
	private String packageGoodsItemId;
	/**包装ID*/
	private String packageMainId;
	/**商品明细ID*/
	private String goodsDetailId;
	/**#产品信息*/
	private Product product;
	/**#规格明细信息*/
	private ProductStandardDetail productStandardDetail;
	/**#商品批次*/
	private String goodsBatch; // 商品批次
	/**#商品的标识*/
	private String goodsName;
	/**
	 * 获取 包装明细ID
	 * @return packageGoodsItemId
	 */
	public String getPackageGoodsItemId() {
		return packageGoodsItemId;
	}
	/**
	 * 设置 包装明细ID
	 * @param packageGoodsItemId 包装明细ID
	 */
	public void setPackageGoodsItemId(String packageGoodsItemId) {
		this.packageGoodsItemId = packageGoodsItemId;
	}
	/**
	 * 获取 包装ID
	 * @return packageMainId
	 */
	public String getPackageMainId() {
		return packageMainId;
	}
	/**
	 * 设置 包装ID
	 * @param packageMainId 包装ID
	 */
	public void setPackageMainId(String packageMainId) {
		this.packageMainId = packageMainId;
	}
	/**
	 * 获取 商品明细ID
	 * @return goodsDetailId
	 */
	public String getGoodsDetailId() {
		return goodsDetailId;
	}
	/**
	 * 设置 商品明细ID
	 * @param goodsDetailId 商品明细ID
	 */
	public void setGoodsDetailId(String goodsDetailId) {
		this.goodsDetailId = goodsDetailId;
	}
	/**
	 * 获取 #产品信息
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * 设置 #产品信息
	 * @param product #产品信息
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * 获取 #规格明细信息
	 * @return productStandardDetail
	 */
	public ProductStandardDetail getProductStandardDetail() {
		return productStandardDetail;
	}
	/**
	 * 设置 #规格明细信息
	 * @param productStandardDetail #规格明细信息
	 */
	public void setProductStandardDetail(
			ProductStandardDetail productStandardDetail) {
		this.productStandardDetail = productStandardDetail;
	}
	/**
	 * 获取 #商品批次
	 * @return goodsBatch
	 */
	public String getGoodsBatch() {
		return goodsBatch;
	}
	/**
	 * 设置 #商品批次
	 * @param goodsBatch #商品批次
	 */
	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
	}
	/**
	 * 获取 #商品的标识
	 * @return goodsName
	 */
	public String getGoodsName() {
		if (product == null || productStandardDetail == null) {

		} else {
			goodsName = product.getProductName() + "(" + productStandardDetail.getFullStandardName() + ")" + "-"
					+ goodsBatch;
		}

		return goodsName;
	}
	/**
	 * 设置 #商品的标识
	 * @param goodsName #商品的标识
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Override
	public String toString() {
		return "PackageGoodsItem [packageGoodsItemId=" + packageGoodsItemId
				+ ", packageMainId=" + packageMainId + ", goodsDetailId="
				+ goodsDetailId + ", product=" + product
				+ ", productStandardDetail=" + productStandardDetail
				+ ", goodsBatch=" + goodsBatch + ", goodsName=" + goodsName
				+ "]";
	}



}
package cn.rfidcer.bean;

import java.math.BigDecimal;

/**   
  * @Title: 商品表
  * @author jgx
  * @date 2016年6月27日 上午10:31:17 
  * @Copyright Copyright
  * @version V1.0   
*/
public class Goods extends BaseEntity{
	/**商品ID*/
	private String goodsId;
	/**原料批次是否用完，0为未用完，1为用完*/
	private String usable;
	/**所属产品ID*/
	private String productId;
	/**加工ID*/
	private String processMainId;
	/**用户企业ID*/
	private String companyId;
	/**数量*/
	private BigDecimal num;
	/**产品规格明细ID*/
	private String productStandardDetailId;
	/**商品批次*/
	private String goodsBatch;
	/**
	 * 商品等级
	 */
	private String level;
	/**标签类型*/
	private Integer type;
	/**标签编码*/
	private String code;
	/**标签密钥*/
	private String secretKey;
	/**是否删除*/
	private int isDeleted;
	/**#企业*/
	private Company company;
	/**配送类型*/
	private String deliverType;
	/**#配送类型名称*/
	private String deliverTypeName;
	/**地区ID*/
	private String areaInfoId;
	/**屠宰场ID*/
	private String slaughterhouseId;
	/**#产品*/
	private Product product;
	/**#产品规格明细*/
	private ProductStandardDetail productStandardDetail;
	/**#产品名称；用于查询*/
	private String productName;
	/**#商品的标识,用于选择商品*/
	private String goodsName;
	/**#开始创建时间，用于辅助查询*/
	private String createTimeOne;
	/**#结束创建时间，用于辅助查询*/
	private String createTimeTwo;
	/**
	 * 获取 开始创建时间
	 * @return
	 */
	public String getCreateTimeOne() {
		return createTimeOne;
	}
	/**
	 * 设置开始创建时间
	 * @param createTimeOne
	 */
	public void setCreateTimeOne(String createTimeOne) {
		this.createTimeOne = createTimeOne;
	}
	/**
	 * 获取结束创建时间
	 * @return
	 */
	public String getCreateTimeTwo() {
		return createTimeTwo;
	}
	/**
	 * 设置结束创建时间
	 * @param createTimeTwo
	 */
	public void setCreateTimeTwo(String createTimeTwo) {
		this.createTimeTwo = createTimeTwo;
	}
	/**
	 * 获取 商品ID
	 * @return goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置 商品ID
	 * @param goodsId 商品ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取 原料批次是否用完，0为未用完，1为用完
	 * @return usable
	 */
	public String getUsable() {
		return usable;
	}
	/**
	 * 设置 原料批次是否用完，0为未用完，1为用完
	 * @param usable 原料批次是否用完，0为未用完，1为用完
	 */
	public void setUsable(String usable) {
		this.usable = usable;
	}
	/**
	 * 获取 所属产品ID
	 * @return productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置 所属产品ID
	 * @param productId 所属产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取 加工ID
	 * @return processMainId
	 */
	public String getProcessMainId() {
		return processMainId;
	}
	/**
	 * 设置 加工ID
	 * @param processMainId 加工ID
	 */
	public void setProcessMainId(String processMainId) {
		this.processMainId = processMainId;
	}
	/**
	 * 获取 用户企业ID
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 用户企业ID
	 * @param companyId 用户企业ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 数量
	 * @return num
	 */
	public BigDecimal getNum() {
		return num;
	}
	/**
	 * 设置 数量
	 * @param num 数量
	 */
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	/**
	 * 获取 产品规格明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 产品规格明细ID
	 * @param productStandardDetailId 产品规格明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	/**
	 * 获取 商品批次
	 * @return goodsBatch
	 */
	public String getGoodsBatch() {
		return goodsBatch;
	}
	/**
	 * 设置 商品批次
	 * @param goodsBatch 商品批次
	 */
	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
	}
	/**
	 * 获取 标签类型
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置 标签类型
	 * @param type 标签类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取 标签编码
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置 标签编码
	 * @param code 标签编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取 标签密钥
	 * @return secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * 设置 标签密钥
	 * @param secretKey 标签密钥
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	/**
	 * 获取 是否删除
	 * @return isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置 是否删除
	 * @param isDeleted 是否删除
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取 #企业
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * 设置 #企业
	 * @param company #企业
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * 获取 配送类型
	 * @return deliverType
	 */
	public String getDeliverType() {
		return deliverType;
	}
	/**
	 * 设置 配送类型
	 * @param deliverType 配送类型
	 */
	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}
	/**
	 * 获取 #配送类型名称
	 * @return deliverTypeName
	 */
	public String getDeliverTypeName() {
		return deliverTypeName;
	}
	/**
	 * 设置 #配送类型名称
	 * @param deliverTypeName #配送类型名称
	 */
	public void setDeliverTypeName(String deliverTypeName) {
		this.deliverTypeName = deliverTypeName;
	}
	/**
	 * 获取 地区ID
	 * @return areaInfoId
	 */
	public String getAreaInfoId() {
		return areaInfoId;
	}
	/**
	 * 设置 地区ID
	 * @param areaInfoId 地区ID
	 */
	public void setAreaInfoId(String areaInfoId) {
		this.areaInfoId = areaInfoId;
	}
	/**
	 * 获取 屠宰场ID
	 * @return slaughterhouseId
	 */
	public String getSlaughterhouseId() {
		return slaughterhouseId;
	}
	/**
	 * 设置 屠宰场ID
	 * @param slaughterhouseId 屠宰场ID
	 */
	public void setSlaughterhouseId(String slaughterhouseId) {
		this.slaughterhouseId = slaughterhouseId;
	}
	/**
	 * 获取 #产品
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * 设置 #产品
	 * @param product #产品
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * 获取 #产品规格明细
	 * @return productStandardDetail
	 */
	public ProductStandardDetail getProductStandardDetail() {
		return productStandardDetail;
	}
	/**
	 * 设置 #产品规格明细
	 * @param productStandardDetail #产品规格明细
	 */
	public void setProductStandardDetail(
			ProductStandardDetail productStandardDetail) {
		this.productStandardDetail = productStandardDetail;
	}
	/**
	 * 获取 #产品名称；用于查询
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 #产品名称；用于查询
	 * @param productName #产品名称；用于查询
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取 #商品的标识用于选择商品
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
	 * 设置 #商品的标识用于选择商品
	 * @param goodsName #商品的标识用于选择商品
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	/**
	 * 获取 商品等级
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置 商品等级
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", usable=" + usable + ", productId=" + productId + ", processMainId="
				+ processMainId + ", companyId=" + companyId + ", num=" + num + ", productStandardDetailId="
				+ productStandardDetailId + ", goodsBatch=" + goodsBatch + ", level=" + level + ", type=" + type
				+ ", code=" + code + ", secretKey=" + secretKey + ", isDeleted=" + isDeleted + ", company=" + company
				+ ", deliverType=" + deliverType + ", deliverTypeName=" + deliverTypeName + ", areaInfoId=" + areaInfoId
				+ ", slaughterhouseId=" + slaughterhouseId + ", product=" + product + ", productStandardDetail="
				+ productStandardDetail + ", productName=" + productName + ", goodsName=" + goodsName + "]";
	}
	
}

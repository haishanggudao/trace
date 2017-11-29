package cn.rfidcer.bean;

/**   
  * @Title: 商品信息
  * @author jgx
  * @date 2016年6月27日 上午10:35:02 
  * @Copyright Copyright
  * @version V1.0   
*/
public class GoodsInfo {
	/**商品明细ID*/
	private String goodsDetailId;
	/**商品ID*/
	private String goodsId;
	/**产品名称*/
	private String productName;
	/**规格明细名称*/
	private String standardFullName;
	/**商品批次*/
	private String goodsBatch;
	/**商品数量*/
	private String num;
	/**图片连接*/
	private String imgURL;
	/**产品规格名称*/
	private String productStandardName;
	/**产品规格数量*/
	private String productStandardNum;
	/**屠宰场ID*/
	private String slaughterhouseId;
	/**地区名称*/
	private String areaName;
	/**地区信息*/
	private AreaInfo areaInfo;
	/**企业ID*/
	private String companyId;
	/**追溯数量*/
	private int traceCount;
	/**标签类型*/
	private String deliverType;
	/**产品分类名称*/
	private String productCategoryName;
	/**产品城市图片连接*/
	private String publicityImageUrl;
	/**
	 * 包装时间
	 */
	private String createTime;
	/**
	 * 等级
	 */
	private String levelName;
	/**
	 * 入库批次
	 */
	private String secretKey;
	
	/**#保质期*/
	private int shelfLife;
	
	
	public int getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}
	/**获取入库批次
	 * @return the 入库批次
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**设置入库批次
	 * @param secretKey the 入库批次 to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
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
	 * 获取 产品名称
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 产品名称
	 * @param productName 产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取 规格明细名称
	 * @return standardFullName
	 */
	public String getStandardFullName() {
		if (productStandardName != null && productStandardNum != null) {
			standardFullName = productStandardNum + productStandardName;
		}
		return standardFullName;
	}
	/**
	 * 设置 规格明细名称
	 * @param standardFullName 规格明细名称
	 */
	public void setStandardFullName(String standardFullName) {
		this.standardFullName = standardFullName;
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
	 * 获取 商品数量
	 * @return num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * 设置 商品数量
	 * @param num 商品数量
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * 获取 图片连接
	 * @return imgURL
	 */
	public String getImgURL() {
		return imgURL;
	}
	/**
	 * 设置 图片连接
	 * @param imgURL 图片连接
	 */
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	/**
	 * 获取 产品规格名称
	 * @return productStandardName
	 */
	public String getProductStandardName() {
		return productStandardName;
	}
	/**
	 * 设置 产品规格名称
	 * @param productStandardName 产品规格名称
	 */
	public void setProductStandardName(String productStandardName) {
		this.productStandardName = productStandardName;
	}
	/**
	 * 获取 产品规格数量
	 * @return productStandardNum
	 */
	public String getProductStandardNum() {
		return productStandardNum;
	}
	/**
	 * 设置 产品规格数量
	 * @param productStandardNum 产品规格数量
	 */
	public void setProductStandardNum(String productStandardNum) {
		this.productStandardNum = productStandardNum;
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
	 * 获取 地区名称
	 * @return areaName
	 */
	public String getAreaName() {
		if (areaInfo != null) {
			areaName = areaInfo.getCatgName();
		}
		return areaName;
	}
	/**
	 * 设置 地区名称
	 * @param areaName 地区名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取 地区信息
	 * @return areaInfo
	 */
	public AreaInfo getAreaInfo() {
		return areaInfo;
	}
	/**
	 * 设置 地区信息
	 * @param areaInfo 地区信息
	 */
	public void setAreaInfo(AreaInfo areaInfo) {
		this.areaInfo = areaInfo;
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
	 * 获取 追溯数量
	 * @return traceCount
	 */
	public int getTraceCount() {
		return traceCount;
	}
	/**
	 * 设置 追溯数量
	 * @param traceCount 追溯数量
	 */
	public void setTraceCount(int traceCount) {
		this.traceCount = traceCount;
	}
	/**
	 * 获取 标签类型
	 * @return deliverType
	 */
	public String getDeliverType() {
		return deliverType;
	}
	/**
	 * 设置 标签类型
	 * @param deliverType 标签类型
	 */
	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}
	/**
	 * 获取 产品分类名称
	 * @return productCategoryName
	 */
	public String getProductCategoryName() {
		return productCategoryName;
	}
	/**
	 * 设置 产品分类名称
	 * @param productCategoryName 产品分类名称
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	/**
	 * 获取 产品城市图片连接
	 * @return publicityImageUrl
	 */
	public String getPublicityImageUrl() {
		return publicityImageUrl;
	}
	/**
	 * 设置 产品城市图片连接
	 * @param publicityImageUrl 产品城市图片连接
	 */
	public void setPublicityImageUrl(String publicityImageUrl) {
		this.publicityImageUrl = publicityImageUrl;
	}

	/**
	 * 获取 包装时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		// System.out.println(""+createTime+" size is " + createTime.length() + "; .index is " + createTime.lastIndexOf("."));
		if (createTime.lastIndexOf(".") > 18) {
			return createTime.substring(0, createTime.lastIndexOf("."));
		}
		return createTime;
	}
	/**
	 * 设置 包装时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 等级
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}
	/**
	 * 设置 等级
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoodsInfo [goodsDetailId=" + goodsDetailId + ", goodsId=" + goodsId + ", productName=" + productName
				+ ", standardFullName=" + standardFullName + ", goodsBatch=" + goodsBatch + ", num=" + num + ", imgURL="
				+ imgURL + ", productStandardName=" + productStandardName + ", productStandardNum=" + productStandardNum
				+ ", slaughterhouseId=" + slaughterhouseId + ", areaName=" + areaName + ", areaInfo=" + areaInfo
				+ ", companyId=" + companyId + ", traceCount=" + traceCount + ", deliverType=" + deliverType
				+ ", productCategoryName=" + productCategoryName + ", publicityImageUrl=" + publicityImageUrl
				+ ", createTime=" + createTime + ", levelName=" + levelName + ", secretKey=" + secretKey + "]";
	}
	



}

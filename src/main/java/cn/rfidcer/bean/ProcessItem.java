package cn.rfidcer.bean;

import java.math.BigDecimal;

import cn.rfidcer.enums.ProcessItemType;
import cn.rfidcer.enums.ProductType;
import cn.rfidcer.util.StringUtil;

/**   
  * @Title: 加工明细表
  * @author jie.jia
  * @date 2016年6月27日 下午6:54:26 
  * @Copyright Copyright
  * @version V1.0   
*/
public class ProcessItem extends BaseEntity {
	/**加工明细ID*/
	private String processItemId;
	/**加工ID*/
	private String processMainId;
	/**商品ID*/
	private String goodsId;
	/**#商品批次*/
	private String goodsBatch;
	/**加工商品数量*/
	private BigDecimal num;
	/**#加工商品实际数量*/
	private BigDecimal realnum;
	/**加类型：0-主料，1-辅料*/
	private String type;
	/**#产品ID*/
	private String productId;
	/**#产品明细ID*/
	private String productStandardDetailId;
	/**#产品名称*/
	private String productName;
	/**#产品信息*/
	private Product product;
	/**#规格明细*/
	private ProductStandardDetail standardDetail;
	/**#规格名称*/
	private String fullStandardName;
	/**#产品类型*/
	private String productType;
	/**#产品类型名称*/
	private String productTypeName;
	/**原料质检信息*/
	private GoodsQCMaterials materialsQCInfo;
	/**
	 * 所属供应商
	 */
	private Supplier supplier;
	
	/**
	 * 产地
	 */
	private String areaName;
	
	private Origin origin;
	
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	/**
	 * 获取 加工明细ID
	 * @return processItemId
	 */
	public String getProcessItemId() {
		return processItemId;
	}
	/**
	 * 设置 加工明细ID
	 * @param processItemId 加工明细ID
	 */
	public void setProcessItemId(String processItemId) {
		this.processItemId = processItemId;
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
	 * 获取 加工商品数量
	 * @return num
	 */
	public BigDecimal getNum() {
		return num;
	}
	/**
	 * 设置 加工商品数量
	 * @param num 加工商品数量
	 */
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	/**
	 * 获取 #加工商品实际数量
	 * @return realnum
	 */
	public BigDecimal getRealnum() {
		return realnum;
	}
	/**
	 * 设置 #加工商品实际数量
	 * @param realnum #加工商品实际数量
	 */
	public void setRealnum(BigDecimal realnum) {
		this.realnum = realnum;
	}
	/**
	 * 获取 加类型：0-主料，1-辅料
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置 加类型：0-主料，1-辅料
	 * @param type 加类型：0-主料，1-辅料
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取 #产品ID
	 * @return productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置 #产品ID
	 * @param productId #产品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取 #产品明细ID
	 * @return productStandardDetailId
	 */
	public String getProductStandardDetailId() {
		return productStandardDetailId;
	}
	/**
	 * 设置 #产品明细ID
	 * @param productStandardDetailId #产品明细ID
	 */
	public void setProductStandardDetailId(String productStandardDetailId) {
		this.productStandardDetailId = productStandardDetailId;
	}
	/**
	 * 获取 #产品名称
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置 #产品名称
	 * @param productName #产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * 获取 #规格明细
	 * @return standardDetail
	 */
	public ProductStandardDetail getStandardDetail() {
		return standardDetail;
	}
	/**
	 * 设置 #规格明细
	 * @param standardDetail #规格明细
	 */
	public void setStandardDetail(ProductStandardDetail standardDetail) {
		this.standardDetail = standardDetail;
	}
	/**
	 * 获取 #规格名称
	 * @return fullStandardName
	 */
	public String getFullStandardName() {
		if(standardDetail!=null){
			fullStandardName=standardDetail.getFullStandardName();
		}
		return fullStandardName;
	}
	/**
	 * 设置 #规格名称
	 * @param fullStandardName #规格名称
	 */
	public void setFullStandardName(String fullStandardName) {
		this.fullStandardName = fullStandardName;
	}
	/**
	 * 获取 #产品类型
	 * @return productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 设置 #产品类型
	 * @param productType #产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取 #产品类型名称
	 * @return productTypeName
	 */
	public String getProductTypeName() {
		String typeName = "";
		if(!StringUtil.isBlank(this.getProductType()))
		{
			typeName = ProductType.Enums.getName(Integer.parseInt(this.getProductType()));
		}
		this.setProductTypeName(typeName);
		return productTypeName;
	}
	/**
	 * 设置 #产品类型名称
	 * @param productTypeName #产品类型名称
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	/**
	 * 获取 #类型名称
	 * @return typeName
	 */
	public String getTypeName() {
		String strName = "";
		if(!StringUtil.isBlank(this.getType()))
		{
			strName = ProcessItemType.Enums.getName(Integer.parseInt(this.getType()));
		}
		return strName;
	}
	/**
	 * 获取 所属供应商
	 * @return the supplierName
	 */
	public Supplier getSupplier() {
		return supplier;
	}
	/**
	 * 设置 所属供应商
	 * @param supplierName the supplierName to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	/**
	 * 获取 产地
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置 产地
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public GoodsQCMaterials getMaterialsQCInfo() {
		return materialsQCInfo;
	}
	public void setMaterialsQCInfo(GoodsQCMaterials materialsQCInfo) {
		this.materialsQCInfo = materialsQCInfo;
	}
	
	
}
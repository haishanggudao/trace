package cn.rfidcer.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.rfidcer.util.DateSerializer;

/**   
  * @Title: 商品质检信息
  * @author XZM  
  * @date 2016年6月24日 下午3:09:46 
  * @Copyright Copyright
  * @version V1.0   
*/
@JsonInclude(value = Include.NON_NULL)
public class GoodsQC extends BaseEntity {
	/**原料质检ID*/
	private String materialQcId;
	/**商品*/
	private Goods goods;
	/**产地证明编号*/
	private String originNo;
	/**检验检疫证书编号*/
	private String quarantineNo;
	/**质量安全检测*/
	private String inspection;
	/**企业ID，查询用*/
	private String companyId;
	/**检测日期*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inspectionDate;
	/**样品名称*/
	private String sampleName;
	/**检测结果*/
	private String result;
	
	/**
	 * 获取 原料质检ID
	 * @return materialQcId
	 */
	public String getMaterialQcId() {
		return materialQcId;
	}
	/**
	 * 设置 原料质检ID
	 * @param materialQcId 原料质检ID
	 */
	public void setMaterialQcId(String materialQcId) {
		this.materialQcId = materialQcId;
	}
	/**
	 * 获取 商品
	 * @return goods
	 */
	public Goods getGoods() {
		return goods;
	}
	/**
	 * 设置 商品
	 * @param goods 商品
	 */
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	/**
	 * 获取 产地证明编号
	 * @return originNo
	 */
	public String getOriginNo() {
		return originNo;
	}
	/**
	 * 设置 产地证明编号
	 * @param originNo 产地证明编号
	 */
	public void setOriginNo(String originNo) {
		this.originNo = originNo;
	}
	/**
	 * 获取 检验检疫证书编号
	 * @return quarantineNo
	 */
	public String getQuarantineNo() {
		return quarantineNo;
	}
	/**
	 * 设置 检验检疫证书编号
	 * @param quarantineNo 检验检疫证书编号
	 */
	public void setQuarantineNo(String quarantineNo) {
		this.quarantineNo = quarantineNo;
	}
	/**
	 * 获取 质量安全检测
	 * @return inspection
	 */
	public String getInspection() {
		return inspection;
	}
	/**
	 * 设置 质量安全检测
	 * @param inspection 质量安全检测
	 */
	public void setInspection(String inspection) {
		this.inspection = inspection;
	}
	/**
	 * 获取 企业ID，查询用
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置 企业ID，查询用
	 * @param companyId 企业ID，查询用
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取 检测日期
	 * @return inspectionDate
	 */
	@JsonSerialize(using = DateSerializer.class)
	public Date getInspectionDate() {
		return inspectionDate;
	}
	/**
	 * 设置 检测日期
	 * @param inspectionDate 检测日期
	 */
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	/**
	 * 获取 样品名称
	 * @return sampleName
	 */
	public String getSampleName() {
		return sampleName;
	}
	/**
	 * 设置 样品名称
	 * @param sampleName 样品名称
	 */
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	/**
	 * 获取 检测结果
	 * @return result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置 检测结果
	 * @param result 检测结果
	 */
	public void setResult(String result) {
		this.result = result;
	}
}

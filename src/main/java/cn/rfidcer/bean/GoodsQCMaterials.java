package cn.rfidcer.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   商品或商品材料的质检信息
* @Title: GoodsQCMaterials.java 
* @Package cn.rfidcer.bean 
* @Description:
* @author 席志明
* @Copyright Copyright
* @date 2017年10月12日 上午10:05:19 
* @version V1.0   
*/
@Table(name="t_goods_qcmaterials")
@NameStyle(Style.normal)
public class GoodsQCMaterials extends BaseEntity{

	@Id
	private String id;
	private String goodsId;
	private String qcmaterialsURL;
	
	@Transient
	private String productName;
	
	@Transient
	private String productType;
	
	@Transient
	private ProductStandardDetail productStandardDetail;
	
	@Transient
	private String goodsBatch;
	
	@Transient
	private String companyId;
	
	@Transient
	private String[] qcmaterialURL;
	
	@Transient
	private MultipartFile[] qcmaterialURLFiles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getQcmaterialsURL() {
		return qcmaterialsURL;
	}

	public void setQcmaterialsURL(String qcmaterialsURL) {
		this.qcmaterialsURL = qcmaterialsURL;
	}

	public MultipartFile[] getQcmaterialURLFiles() {
		return qcmaterialURLFiles;
	}

	public void setQcmaterialURLFiles(MultipartFile[] qcmaterialURLFiles) {
		this.qcmaterialURLFiles = qcmaterialURLFiles;
	}
	
	public String[] getQcmaterialURL() {
		if(qcmaterialURL == null && !org.apache.commons.lang3.StringUtils.isEmpty(qcmaterialsURL)) {
			qcmaterialURL = qcmaterialsURL.split(",");
		}
		return qcmaterialURL;
	}
	
	public void setQcmaterialURL(String[] qcmaterialURL) {
		this.qcmaterialURL = qcmaterialURL;
		if(qcmaterialURL != null && qcmaterialURL.length > 0) {
			if(!StringUtils.isEmpty(qcmaterialsURL)) {
				List<String> ls1 = new ArrayList<String>(Arrays.asList(qcmaterialsURL.split(",")));
				List<String> ls2 = Arrays.asList(qcmaterialURL);
				ls1.addAll(ls2);
				qcmaterialURL = ls1.toArray(new String[ls1.size()]);
			}
			qcmaterialsURL = StringUtils.arrayToDelimitedString(qcmaterialURL,",");
		}
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getGoodsBatch() {
		return goodsBatch;
	}

	public void setGoodsBatch(String goodsBatch) {
		this.goodsBatch = goodsBatch;
	}

	public ProductStandardDetail getProductStandardDetail() {
		return productStandardDetail;
	}

	public void setProductStandardDetail(ProductStandardDetail productStandardDetail) {
		this.productStandardDetail = productStandardDetail;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
}

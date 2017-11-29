package cn.rfidcer.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Instockqc {
    private String id;
    private String instockMainId;
    private String companyId;
    private String goodsId;
    private String productName;
    private Date instockDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date instockDate1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date instockDate2;
    private String instockBatchNum;
    private String qcmaterialsURL;
	private String[] qcmaterialURL;
	private MultipartFile[] qcmaterialURLFiles;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
	public Date getInstockDate() {
		return instockDate;
	}
	public void setInstockDate(Date instockDate) {
		this.instockDate = instockDate;
	}
	public String getInstockBatchNum() {
		return instockBatchNum;
	}
	public void setInstockBatchNum(String instockBatchNum) {
		this.instockBatchNum = instockBatchNum;
	}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getInstockMainId() {
        return instockMainId;
    }
    public void setInstockMainId(String instockMainId) {
        this.instockMainId = instockMainId;
    }
    public String getQcmaterialsURL() {
        return qcmaterialsURL;
    }
    public void setQcmaterialsURL(String qcmaterialsURL) {
        this.qcmaterialsURL = qcmaterialsURL;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getCreateBy() {
        return createBy;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Date getInstockDate1() {
		return instockDate1;
	}

	public void setInstockDate1(Date instockDate1) {
		this.instockDate1 = instockDate1;
	}

	public Date getInstockDate2() {
		return instockDate2;
	}

	public void setInstockDate2(Date instockDate2) {
		this.instockDate2 = instockDate2;
	}

	public String[] getQcmaterialURL() {
		if(qcmaterialURL == null && !org.apache.commons.lang3.StringUtils.isEmpty(qcmaterialsURL)) {
			qcmaterialURL = qcmaterialsURL.split(",");
		}
		return qcmaterialURL;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setQcmaterialURL(String[] qcmaterialURL) {
		this.qcmaterialURL = qcmaterialURL;
		if(qcmaterialURL != null && qcmaterialURL.length > 0) {
			if(!StringUtils.isEmpty(qcmaterialsURL)) {
				List<String> ls1 = new ArrayList(Arrays.asList(qcmaterialsURL.split(",")));
				List<String> ls2 = Arrays.asList(qcmaterialURL);
				ls1.addAll(ls2);
				qcmaterialURL = ls1.toArray(new String[ls1.size()]);
			}
			qcmaterialsURL = StringUtils.arrayToDelimitedString(qcmaterialURL,",");
		}
	}

	public MultipartFile[] getQcmaterialURLFiles() {
		return qcmaterialURLFiles;
	}

	public void setQcmaterialURLFiles(MultipartFile[] qcmaterialURLFiles) {
		this.qcmaterialURLFiles = qcmaterialURLFiles;
	}
}
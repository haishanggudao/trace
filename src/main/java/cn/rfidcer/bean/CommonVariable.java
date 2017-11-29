package cn.rfidcer.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

/**   
  * @Title: 常用类型资源表 
  * @author jgx
  * @date 2016年6月27日 上午10:34:01 
  * @Copyright Copyright
  * @version V1.0   
*/
@Table(name="t_common_variable")
@NameStyle(Style.normal)
public class CommonVariable extends BaseEntity{
	/**ID*/
	@Id
    private String varId;
    /**企业ID*/
    private String companyId;
    /**组*/
    private String varGroup;
    /**键*/
    private String varName;
    /**值*/
    private String varValue;
    /**排序*/
    private Integer sort;
    
    private String remark;
    
    
    
	public CommonVariable() {}
	public CommonVariable(String companyId, String varGroup, String varName, String varValue) {
		this.companyId = companyId;
		this.varGroup = varGroup;
		this.varName = varName;
		this.varValue = varValue;
	}
	/**
	 * 获取 ID
	 * @return varId
	 */
	public String getVarId() {
		return varId;
	}
	/**
	 * 设置 ID
	 * @param varId ID
	 */
	public void setVarId(String varId) {
		this.varId = varId;
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
	 * 获取 组
	 * @return varGroup
	 */
	public String getVarGroup() {
		return varGroup;
	}
	/**
	 * 设置 组
	 * @param varGroup 组
	 */
	public void setVarGroup(String varGroup) {
		this.varGroup = varGroup;
	}
	/**
	 * 获取 键
	 * @return varName
	 */
	public String getVarName() {
		return varName;
	}
	/**
	 * 设置 键
	 * @param varName 键
	 */
	public void setVarName(String varName) {
		this.varName = varName;
	}
	/**
	 * 获取 值
	 * @return varValue
	 */
	public String getVarValue() {
		return varValue;
	}
	/**
	 * 设置 值
	 * @param varValue 值
	 */
	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}
	/**
	 * 获取 排序
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置 排序
	 * @param sort 排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "CommonVariable [varId=" + varId + ", companyId=" + companyId
				+ ", varGroup=" + varGroup + ", varName=" + varName
				+ ", varValue=" + varValue + ", sort=" + sort + "]";
	}
    
}
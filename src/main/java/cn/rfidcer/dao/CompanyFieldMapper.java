package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.CompanyField;
import cn.rfidcer.interceptor.Page;

/**
 * DAO for the field of company
 * @author jie.jia
 *
 */
public interface CompanyFieldMapper {
     
    int deleteByPrimaryKey(String companyFieldId);

    int insert(CompanyField record);

    int insertSelective(CompanyField record);

    CompanyField selectByPrimaryKey(String companyFieldId);

    int updateByPrimaryKeySelective(CompanyField record);

    int updateByPrimaryKeyWithBLOBs(CompanyField record);

    int updateByPrimaryKey(CompanyField record);
    
    List<CompanyField> list(Page page);
    
    List<CompanyField> list();

	List<CompanyField> listExcept(String exceptid);

	List<CompanyField> findAll(Page page,@Param("companyfield")CompanyField cf);
	
	/**根据领域名称查找领域信息
	 * @param companyField
	 * @return
	 */
	CompanyField findByFiledName(String companyFieldName);
}
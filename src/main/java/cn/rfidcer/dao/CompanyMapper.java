package cn.rfidcer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Company;
import cn.rfidcer.interceptor.Page;

/**
 * DAO of company
 * @author 
 *
 */
public interface CompanyMapper {
	
	/**
	 * 根据企业名称查找企业信息; created by jie.jia at 2016-04-22 11:44
	 * @param company
	 * @return
	 */
	int checkCompanyExistsByCompanyName(Company company);
     
    int deleteByPrimaryKey(String companyid);

    int deleteWithStatusByPrimaryKey(String companyid);
    
    /**
     * insert a new company
     * @param record
     * @return
     */
    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(String companyid);

    /**
     * update the company
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

	List<Company> list(Page page);
	
	List<Company> list();

	List<Company> getCompanys(String userId);

	List<Company> listQueryByCompany(Page page,@Param("company")Company company);
	
	List<Company> listQueryByBlurAnd(Page page,@Param("company")Company company);
	
	List<Company> listQueryByBlurOr(Page page,@Param("company")Company company);
	
	/**检查公司名称是否存在,公司名称唯一
	 * @param companyName
	 * @return
	 */
	Company selectCompanyByName(String companyName);
	
	/**根据企业编码查询企业信息
	 * @param code
	 * @return
	 */
	Company findByCode(String code);
	
	/**追溯码流水号加1
	 * @param companyId
	 * @return
	 */
	int updateTraceSerialNum(String companyId);
	
	/**mac编号加1
	 * @param companyId
	 * @return
	 */
	int updateMacNum(String companyId);

}
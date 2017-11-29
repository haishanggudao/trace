package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.CompanyField;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

public interface CompanyFieldService {

	List<CompanyField> list(Page page);

	ResultMsg deleteByKey(String id);

	List<CompanyField> listExcept(String exceptid);

	List<CompanyField> findAll(Page page, CompanyField cf);

	/**
	 * @param companyField
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdate(CompanyField companyField, User user);
}
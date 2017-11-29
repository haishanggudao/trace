package cn.rfidcer.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.service.CompanyPageService;
import cn.rfidcer.service.CompanyService;

@Aspect
public class IndexInterceptor {

	@Autowired
	private CompanyPageService companyPageService;
	
	@Autowired
	private CompanyService companyService;
	
	@Around(value="execution(public String cn.rfidcer.controller.*.index(..)) && args(..,companyPage)")
	  public Object around(ProceedingJoinPoint point,CompanyPage companyPage) throws Throwable {
		Object proceed = point.proceed();
		Company company = companyService.getCompanyById(companyPage.getCompanyId());
		String companyfieldid = company.getCompanyfieldid();
		if(companyfieldid!=null){
			companyPage.setCompanyId(companyfieldid);
			companyPage=companyPageService.selectByPrimaryKey(companyPage);
			if(companyPage!=null&&companyPage.getPage()!=null){
				return companyPage.getPage();
			}
		}
	    return proceed;
	  }
}

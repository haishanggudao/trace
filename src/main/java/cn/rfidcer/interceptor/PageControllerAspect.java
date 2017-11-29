package cn.rfidcer.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * AOP: 前端easyui分页; updated by jie.jia at 2016-04-01 16:41
 * @author jie.jia 
 */
@Aspect
public class PageControllerAspect {

	@Around(value="execution(public * cn.rfidcer.controller..*.findAll*(..))  && args(page,..) ")
	public Object doAfter(ProceedingJoinPoint  joinPoint,Page page){
		Object proceed=null; 
		try {
			proceed = joinPoint.proceed();
			if(proceed instanceof Map){
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (HashMap<String, Object>)proceed;
				// Map<String,Object> map=new HashMap<String, Object>();
				// map.put("rows", proceed);
				
				map.put("total", page.getTotalNum());
				return map;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return proceed;
	}
	
	
	
}

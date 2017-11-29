package cn.rfidcer.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import cn.rfidcer.bean.TraceInfo;
import cn.rfidcer.service.IPService;

//@Aspect
public class TraceLogAspect {

	@Autowired
	private IPService ipService;
	
	@Pointcut("execution(* cn.rfidcer.controller.HandShakeController.getTraceInfoPage(..))")
	private void log() {
	}
	
	@Around("log()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		try {
			result = pjp.proceed();
			Object[] args = pjp.getArgs();
			ModelMap map=(ModelMap) args[1];
			Object object = map.get("traceInfo");
			if(object!=null){
				TraceInfo traceInfo=(TraceInfo) object;
				if(traceInfo!=null&&traceInfo.getGoodsInfo()!=null){
					ipService.sendIPInfoToMQ((String) args[0], (HttpServletRequest) args[2]);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
}
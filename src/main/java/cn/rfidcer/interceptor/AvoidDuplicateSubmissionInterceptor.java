package cn.rfidcer.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.service.impl.ClientUserServiceImpl;

/**
 * 防止重复提交过滤器
 * @author jie.jia
 * @date 2016-05-09 14:49
 */
public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(ClientUserServiceImpl.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		
		if (handler instanceof HandlerMethod) {  
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			
			AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
			
			if ( annotation != null) {
				boolean needSaveSession = annotation.needSaveToken();
				if (needSaveSession) {
					request.getSession(false).setAttribute(
							"token", UUID.randomUUID().toString());
				}
				
				boolean needRemoveSession = annotation.needRemoveToken();
				if (needRemoveSession) {
					 if (isRepeatSubmit(request)) {
						logger.warn("please don't repeat submit!");
						return false;
					}
					 request.getSession(false).removeAttribute("token");
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);  
		}
	}
	
	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clientToken = request.getParameter("token");
		if (clientToken == null) {
			return true;
		}
		if ( !serverToken.equals(clientToken)) {
			return true;
		}
		return false;
	}

}

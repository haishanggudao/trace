package cn.rfidcer.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.annotation.Constants;
import cn.rfidcer.service.UserService;

public class SysUserFilter extends PathMatchingFilter{

	@Autowired
	private UserService userService;
	
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute(Constants.CURRENT_USER, userService.findUserByname(username));
		return true;
	} 
}

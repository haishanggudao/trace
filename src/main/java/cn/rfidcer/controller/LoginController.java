package cn.rfidcer.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Title: LoginController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller login 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月28日 上午10:34:01 
* @version V1.0
 */
@Controller
public class LoginController {

	private Logger logger=LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value={"/{path}login"})
	public String login(@PathVariable String path,HttpServletRequest req,Model model){
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
		String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名或密码错误";
        }else if(LockedAccountException.class.getName().equals(exceptionClassName)){
        	error = "用户已被锁定";
        }else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
        	error = "连续5次密码错误,账号被锁定10分钟";
        } else if(exceptionClassName != null) {
        	logger.info("exceptionClassName:"+exceptionClassName);
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
		return path+"login";
	}
	
	
	@RequestMapping("/dlogin")
	public String dlogin(@CookieValue(required=false) String redirectUrl){
		if(StringUtils.isEmpty(redirectUrl)){
			redirectUrl="login";
		}
		return redirectUrl;
	}
	
	
}

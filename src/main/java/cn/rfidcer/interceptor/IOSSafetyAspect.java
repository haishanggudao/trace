package cn.rfidcer.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.rfidcer.dto.ResponseData;

/**   
* @Description:IOS授权码验证
* @author 席志明
* @date 2016年8月26日 上午10:49:33 
* @version V1.0   
*/
@Aspect
public class IOSSafetyAspect {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(public * cn.rfidcer.controller.yz.IOSPosController.*(..)) && args(..,code)") 
	public Object callBefore(ProceedingJoinPoint point,String code) throws Throwable{
		logger.info("before ..........:"+code);
		if(code==null||!code.equals("gy7412589630")){
			ResponseData<String> res = new ResponseData<String>();
			res.setResult(0);
			res.setError("授权码错误，非法访问");
			return res;
		}else{
			return point.proceed();
		}
	}
	
}

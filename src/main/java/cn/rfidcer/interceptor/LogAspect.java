package cn.rfidcer.interceptor;
 
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@Aspect
public class LogAspect {
	
	private Logger logger=LoggerFactory.getLogger(LogAspect.class);
	 	
	@Before("execution(public * cn.rfidcer.controller.*.add*(..))")  
    public void foundBefore(JoinPoint joinPoint){  
        // System.out.println("logBefore() aspect logger before...");  
        // System.out.println("hijacked : " + joinPoint.getSignature().getName());
        logger.info( "CRUD logBefore() : " + joinPoint.getSignature().getName() );
		// System.out.println("******");
    }   
	
	@Around("execution(public * cn.rfidcer.controller.*.add*(..))")
	  public Object around(ProceedingJoinPoint point) throws Throwable {
	    long start = System.currentTimeMillis();
	    Object result = point.proceed();
	    logger.info( "CRUD Around() : " +
	      MethodSignature.class.cast(point.getSignature()).getMethod().getName() + "(" + 
	    		  point.getArgs() + "): " +
	    		  result + " in [" + 
	    		  String.valueOf(System.currentTimeMillis() - start)+"]s" );
	    return result;
	  }
	
	@AfterReturning(
			pointcut="execution(public * cn.rfidcer.controller.*.add*(..))",
			returning = "result")  
    public void foundAfter(JoinPoint joinPoint, Object result){  
        // System.out.println("logAfter() aspect logger after...");  
        // System.out.println("hijacked : " + joinPoint.getSignature().getName());
        logger.info( "CRUD logAfter() : " + joinPoint.getSignature().getName() );
        // System.out.println("Method returned value is : " + result);
        logger.info("Method returned value is : " + result);
		// System.out.println("******");
    }  

}

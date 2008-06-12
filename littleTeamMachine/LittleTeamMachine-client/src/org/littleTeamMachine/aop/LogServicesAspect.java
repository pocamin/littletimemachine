package org.littleTeamMachine.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Class permettant de serialiser les object de type Root en dépit de leur listener
 * @author lerouxb
 *
 */
@Aspect
public class LogServicesAspect {
	
	
	static int i = 0;
	
	@Pointcut("execution(* org.littleTeamMachine..*.*(..))")
    public void businessService() {}
	
	@Around("businessService()")
	public Object dolog(ProceedingJoinPoint pjp) throws Throwable {
		int tmp = i ++;
		String args = "(\n";
		for(Object obj : pjp.getArgs()){
			args += "\t";
			if (obj != null){
				args += obj.getClass().getName();
				args += "=";
				args += String.valueOf(obj);
				
			} else args += "null";
			
			args += "\n";
		}
		args += "\n)";
		
		Log log = LogFactory.getLog(pjp.getSignature().getDeclaringType());
		
		log.debug("entering : " + tmp + " " + pjp.getSignature().getName() + args);
		Object point =  pjp.proceed();
		log.debug("exiting  : " + tmp + " " + pjp.getSignature().getName() );

		return point;
    }

	

}

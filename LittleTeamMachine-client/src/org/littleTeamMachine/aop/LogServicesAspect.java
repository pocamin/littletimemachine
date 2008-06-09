package org.littleTeamMachine.aop;

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
	
	
	@Pointcut("execution(* org.littleTeamMachine..*.*(..))")
    public void businessService() {}
	
	@Around("businessService()")
	public Object dolog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("entering : " + pjp.getSignature().getName());
		Object point =  pjp.proceed();
		System.out.println("exiting  : "  + pjp.getSignature().getName());

		return point;
    }

	

}

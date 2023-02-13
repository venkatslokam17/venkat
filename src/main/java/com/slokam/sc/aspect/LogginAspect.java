package com.slokam.sc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {

	//  before,after , around , after throwing ,after returning.
	
	 @Before("execution(* com.slokam.sc.*.*.*(..))")
	 public void beforeMethod(JoinPoint joinPoint ) {
		 String name = joinPoint.getSignature().getName();
		 String className = joinPoint.getTarget().toString();
		 System.out.println("Entered into the method "+name +" in the class "+className);
	 }
	
	@After("execution(* com.slokam.sc.*.*.*(..) )")
	public void afterMethod(JoinPoint joinPoint) {
	     String name = joinPoint.getSignature().getName();
		 String className = joinPoint.getTarget().toString();
		 System.out.println("Exit from the method "+name +" in the class "+className);
	}
	 
	 
}

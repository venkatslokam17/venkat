package com.slokam.sc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class MPAspect {

	
	/*
	 * public void beforeMethod() { long startTime = System.currentTimeMillis(); }
	 * 
	 * public void afterMethod() { long endTime = System.currentTimeMillis(); long
	 * timeTaken = endTime - startTime; }
	 */
	 
	@Around("execution (* com.slokam.sc.*.*.*(..))")
	public Object around(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();
		String name = pjp.getSignature().getName();
		String className = pjp.getTarget().toString();
		Object obj =null;
	     try {
			 obj = pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis(); 
		long timeTaken = endTime - startTime;
		
		System.out.println("Time taken for the method "+name 
				+" in the class "+className +" is "+timeTaken);
		return obj;
	}
	
	@AfterReturning(pointcut = "execution (* com.slokam.sc.*.*.*(..))" ,
			returning = "obj")
	public void afterReturning(JoinPoint jp ,Object  obj) {
	
		String name = jp.getSignature().getName();
		String className = jp.getTarget().toString();
		System.out.println(name+"::"+className+"::"+obj.toString());
		
	}
	  
}

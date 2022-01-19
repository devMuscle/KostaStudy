package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice {//Pointcut(핵심관심사항)에 언제 공통관심사항을 weaving 할지
	private Logger logger = LoggerFactory.getLogger(getClass());
		
	//@Before("execution(* list()) ||  execution(* detail(**))" ) 
	public void beforeLog(){
		logger.error("Before");
	}
	
	@Around("execution(* list(..))")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable{ 
		String pMethodName; 
		pMethodName = pjp.getSignature().getName();//포인트컷메서드이름얻기
		Object []args = pjp.getArgs();
		for(Object arg: args) {
			logger.error("포인트컷메서드 매개변수:" + arg + " 암호화~");
		}
		logger.error("Around 포인트컷메서드("+pMethodName +")호출전");
		
		Object obj = pjp.proceed(); //포인트컷메서드 호출
		logger.error("Around 포인트컷메서드("+pMethodName +")호출후");
		return obj;
	}
}
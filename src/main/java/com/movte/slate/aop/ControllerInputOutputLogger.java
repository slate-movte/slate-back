package com.movte.slate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class ControllerInputOutputLogger {

    // 모든 컨트롤러에 AOP 적용
    @Pointcut("execution(* com.movte.slate..controller.*.*(..))")
    private void cut(){}

    /**
     * 클라이언트로부터 들어온 요청 메시지 출력
     * @param joinPoint
     */
    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        log.info("======= >> method name = {} =======", method.getName());

        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) log.info("no parameter");
        for (Object arg : args) {
            if(arg==null){
                continue;
            }
            log.info("parameter type = {}", arg.getClass().getSimpleName());
            log.info("parameter value = {}", arg);
        }
    }

    /**
     * 클라이언트에게 보내는 응답 메시지 출력
     * @param joinPoint
     * @param returnObj
     */
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {
        Method method = getMethod(joinPoint);
        log.info("======= << method name = {} =======", method.getName());

        if(returnObj==null) return;
        log.info("return type = {}", returnObj.getClass().getSimpleName());
        log.info("return value = {}", returnObj);
    }

    // JointPoint 객체에서 메서드 정보 가져오기
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}

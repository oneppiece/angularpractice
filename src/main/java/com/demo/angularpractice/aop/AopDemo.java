package com.demo.angularpractice.aop;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class AopDemo {
    @Pointcut("execution(public * com.demo.angularpractice.account.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        log.info("SESSION : " + session.getId());
    }

    @AfterReturning(pointcut = "webLog()", returning = "ret")
    public void doAfterReturn(Object ret) throws Throwable {
        log.info("方法的返回值:" + ret);
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.info("异常:" + Throwables.getStackTraceAsString(ex));
    }

    @After("webLog()")
    public void after(JoinPoint jp) {
        log.info("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        log.info("方法环绕start.....");
        try {
            Object o = pjp.proceed();
            log.info("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            log.info("方法环绕异常，结果是 :" + Throwables.getStackTraceAsString(e));
            e.printStackTrace();
            return null;
        }
    }
}

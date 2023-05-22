package com.practice.weather.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void onGetRequest() { }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void onPostRequest() { }

    /* Pointcut 과 매칭되는 메서드의 실행 전, 후에 실행
     *  @Around advice 는 꼭 proceed()가 필요하다. */
    @Around("onGetRequest() || onPostRequest()")
    public Object logAction(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
            return result;
        } finally {
            // HttpServletRequest 로 URI 및 파라미터 목록 출력
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            log.info("{} {}", request.getMethod(), request.getRequestURI());
            Set<String> keySet = request.getParameterMap().keySet();
            log.info("================== parameter scan starting ==================");
            for(String key: keySet) {
                log.info("parameter key = {}", key);
                log.info("parameter value = {}", request.getParameter(key));
            }
            log.info("================== parameter scan finished ==================");
        }
    }
}

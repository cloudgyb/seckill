package com.gyb.seckill.config.cache;

import com.gyb.seckill.service.common.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 缓存注解切面实现
 *
 * @author geng
 * 2021/1/9
 * @see Cacheable
 */
@Aspect
@Component
public class CacheHandleAspect {
    private final RedisService redisService;

    public CacheHandleAspect(RedisService redisService) {
        this.redisService = redisService;
    }

    @Pointcut("@annotation(com.gyb.seckill.config.cache.Cacheable)")
    public void cacheHandlePointCut() {
    }

    @Around("cacheHandlePointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        final Method method = ms.getMethod();
        final Cacheable annotation = method.getAnnotation(Cacheable.class);
        final String cacheNames = annotation.cacheName();
        final String[] key = annotation.key();
        final Object[] parameterValues = pjp.getArgs();
        final String[] parameterNames = ms.getParameterNames();
        String cacheKey = cacheNames;
        if (key.length > 0 && parameterNames != null && parameterNames.length > 0) {
            final HashMap<String, Object> map = new HashMap<>(parameterNames.length);
            for (int i = 0; i < parameterNames.length; i++) {
                map.put(parameterNames[i], parameterValues[i]);
            }
            final StringBuilder sb = new StringBuilder(cacheKey);
            for (String k : key) {
                sb.append(":").append(map.get(k));
            }
            cacheKey = sb.toString();
        }
        //取缓存
        final Object cacheObject = redisService.get(cacheKey, ms.getReturnType());
        if (cacheObject != null)
            return cacheObject;

        final Object proceed = pjp.proceed();
        redisService.set(cacheKey, proceed, annotation.expireTime());
        return proceed;
    }
}

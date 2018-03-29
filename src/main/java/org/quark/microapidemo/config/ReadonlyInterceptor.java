package org.quark.microapidemo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.quark.microapidemo.annotation.Readwrite;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadonlyInterceptor implements Ordered {

    @Pointcut(value="execution(public * *(..))")
    public void anyPublicMethod() { }

    @Around("@annotation(readWrite)")
    public Object proceed(ProceedingJoinPoint pjp, Readwrite readWrite) throws Throwable {

        try {

            DbContextHolder.setDbType(DbType.MASTER);

            Object result = pjp.proceed();

            return result;
        } finally {
            // restore state
            DbContextHolder.clearDbType();
        }
    }

    private int order;

    @Value("1")
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}

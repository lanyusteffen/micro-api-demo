package org.quark.microapidemo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.quark.microapidemo.annotation.Readonly;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadwriteInterceptor implements Ordered {

    @Pointcut(value="execution(public * *(..))")
    public void anyPublicMethod() { }

    @Around("@annotation(readOnly)")
    public Object proceed(ProceedingJoinPoint pjp, Readonly readOnly) throws Throwable {
        try {

            DbContextHolder.setDbType(DbType.SLAVE);

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

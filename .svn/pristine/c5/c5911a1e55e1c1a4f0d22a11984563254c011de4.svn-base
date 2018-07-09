package com.htrj.common.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * aop组件,记录异常信息
 * 
 * @author water
 */
@Component
// 将当前Bean扫描到容器
@Aspect
// 将当前Bean指定为aop组件
public class AopExceptionBean {
	public final Logger	logger	= LoggerFactory.getLogger(getClass());
    
    // 定义切入点表达式
    @Pointcut("within(com.opensource.*.controller..*)")
    public void mypoint() {
    }
    
    /**
     * ex对象是目标对象抛出的异常 参数名与throwing属性信息
     * 
     * @param ex
     */
    // 采用异常通知
    @AfterThrowing(pointcut = "mypoint()", throwing = "ex")
    public void loggerException(Exception ex) {
        // 以文件形式记录异常信息
        logger.error("AOP异常信息", ex);
    }
}

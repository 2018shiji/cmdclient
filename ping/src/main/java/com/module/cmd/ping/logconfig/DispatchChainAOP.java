package com.module.cmd.ping.logconfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class DispatchChainAOP {
    public static final Logger logger = LoggerFactory.getLogger(DispatchChainAOP.class);
    private static final ExecutorService resultExecutor = Executors.newSingleThreadExecutor();
    private static final ExecutorService timeoutExecutor = Executors.newSingleThreadExecutor();
    private static final ExecutorService exceptionExecutor = Executors.newSingleThreadExecutor();

    @Pointcut("execution(com.module.cmd.ping.Navigator.*())")
    public void pointCut(){}

    @Around("pointCut()")
    public void handleControllerMethod(ProceedingJoinPoint joinPoint){
        StopWatch stopWatch = new StopWatch();

        try{
            stopWatch.start();
            logger.info("执行Controller开始：" + joinPoint.getSignature() + "参数：" + Arrays.asList(joinPoint.getArgs()).toString());
            Object result = joinPoint.proceed(joinPoint.getArgs());
            handleApiResponse(result);
            logger.info("执行Controller结束：" + joinPoint.getSignature() + ", 返回值：" + result);

            //当接口请求时间大于3秒时，标记为异常调用时间，并记录入库
            stopWatch.stop();
            if(stopWatch.getLastTaskTimeMillis() > 3000){
                handleTimeoutResponse(result);
            }

        } catch (Throwable throwable){
            handleException(joinPoint, throwable);
        }

    }

    /**异步记录调用结果 */
    private void handleApiResponse(Object result){
        resultExecutor.execute(() -> {
            System.out.println("doing record dispatch working task async.....");
        });
    }

    private void handleTimeoutResponse(Object result){
        timeoutExecutor.execute(() -> {
            System.out.println("doing record timeout working task async.....");
        });
    }

    /**异步记录异常信息 */
    private void handleException(ProceedingJoinPoint joinPoint, Throwable throwable){
        exceptionExecutor.execute(() -> {
            System.out.println("doing record exception working task async...");
        });
    }
    
}

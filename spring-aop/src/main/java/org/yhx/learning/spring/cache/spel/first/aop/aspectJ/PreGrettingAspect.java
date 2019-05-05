package org.yhx.learning.spring.cache.spel.first.aop.aspectJ;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *
 * 必须先@Aspect 无论是通过schedma 配置文件完成还是通过注入实现
 * 其次，在xml中添加   <aop:aspectj-autoproxy />通过aop:aspectj 自动实现注入代理功能
 * 最后如果需要通过注解实现aop,必须添加@Component到指定advice并且在配置文件中添加扫描
 */
@Aspect
@Component
public class PreGrettingAspect {
    @Before("org.yhx.learning.spring.cache.spel.first.aop.aspectJ.PintCutDeclarer.pointcute2(name)")
    public void beforeAdvise(String name){
        System.out.println("How are you "+name);
    }

    @After("org.yhx.learning.spring.cache.spel.first.aop.aspectJ.PintCutDeclarer.pointcute2(name)")
    public void afterAdvise(String name){

        System.out.println("after advise "+name);
    }

    @Around("within(org.yhx.learning..*.*Waiter)&&args(name)")
    public void arroundAdise(ProceedingJoinPoint joinPoint, String name) throws Throwable{
        System.out.println("around advise "+name);
        Object result=joinPoint.proceed(joinPoint.getArgs());

        if(result!=null){
            result+="_test";
        }

        System.out.println("around advise "+name);

    }

    @Around("execution(void org.yhx.learning..*.*Waiter.hasError())")
    public void arroundAdise(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("around advise and throw ");

        try {
            Object result = joinPoint.proceed(joinPoint.getArgs());
        }catch(RuntimeException re){
            System.out.println(re.getMessage());
        }catch (Exception ex){
            System.out.println("真的异常");
        }
    }



//    @AfterThrowing(value = "execution(* org.yhx.learning.spring.aop..*.*Error(..))",throwing = "ex")
//    public void afterThrow(Exception ex){
//
//        try {
//            if (ex instanceof RuntimeException) {
//                System.out.println("i got the runtimeExeption");
//            } else {
//
//            }
//        }catch (Exception ex2){
//            System.out.println("i got the exeption ex2:"+ex2);
//        }
//        throw new RuntimeException("test no run");
//    }

    @AfterReturning(value = "execution(String org.yhx.learning.spring.cache.spel.first.aop.domain.Waiter..*(..))",returning = "retVal")
    public void afterReturning(int retVal){
        System.out.println("AfterReturning returning="+retVal);
    }
}

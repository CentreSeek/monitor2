/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: WeblogAspect
 * Author:   CentreS
 * Date:     2019/6/27 15:23
 * Description: 纪录日志
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.aspect;

/**
 * @Description: 纪录日志
 * @author CentreS
 * @create 2019/6/27
 */

import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.service.LoginStateService;
import com.yjjk.monitor.utility.ResultUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WeblogAspect {

    final static Logger logger = LoggerFactory.getLogger(WeblogAspect.class);

    @Resource
    LoginStateService loginStateService;

    public WeblogAspect() {

    }

    @Pointcut("execution(public * com.yjjk.monitor.controller.*.*(..))")
    private void controllerAspect() {

    }

    @Before(value = "controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("[ip:{}] [url:{}] [method:({}) {}]", request.getRemoteAddr(), request.getRequestURI(),
                request.getMethod(), joinPoint.getSignature());
        logger.info("args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value = "controllerAspect()")
    public synchronized Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        Signature signature = joinPoint.getSignature();
        if (!signature.getName().equals("managerLogin") && !signature.getName().equals("managerLoginOut")) {
            if (token == null) {
                logger.error("登录失败：  token为空");
                return ResultUtil.returnError(ErrorCodeEnum.TOKEN_ERROR);
            } else {
                boolean check = loginStateService.checkLogin(token, request.getRemoteAddr());
                if (!check) {
                    logger.error("登录失败：  token = " + token);
                    return ResultUtil.returnError(ErrorCodeEnum.TOKEN_ERROR);
                }
            }
        }
        return joinPoint.proceed();
    }

    @Around("execution(* com.yjjk.monitor.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("====== 开始执行 {}.{} ======",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());

        // 记录开始时间
        long begin = System.currentTimeMillis();

        // 执行目标 service
        Object result = joinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();
        long takeTime = end - begin;

        if (takeTime > 3000) {
            logger.error("====== 执行结束，耗时：{} 毫秒 ======", takeTime);
        } else if (takeTime > 2000) {
            logger.warn("====== 执行结束，耗时：{} 毫秒 ======", takeTime);
        } else {
            logger.info("====== 执行结束，耗时：{} 毫秒 ======", takeTime);
        }

        return result;
    }
}


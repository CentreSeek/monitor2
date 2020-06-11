package com.yjjk.monitor.exception;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-06-10 16:58:47
 **/

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.utility.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public CommonResult processException(Exception e) {

        /**
         * 未知异常
         */
        logger.error("业务异常信息：[{}]", e.getMessage(), e);
        e.printStackTrace();
        return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);

    }

}

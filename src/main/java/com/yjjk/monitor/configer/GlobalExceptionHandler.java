package com.yjjk.monitor.configer;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-06-10 16:58:47
 **/

import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.net.ConnectException;
import java.text.ParseException;
import java.util.List;


@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //    @ExceptionHandler(value = HttpHostConnectException.class)
//    public CommonResult processConnectionException(HttpHostConnectException e) {
//
//        /**
//         * 未知异常
//         */
//        logger.error("数据服务器连接失败：[{}]", e.getMessage(), e);
//        e.printStackTrace();
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_DATA_SERVICE);
//
//    }
    @ExceptionHandler(value = ConnectException.class)
    public CommonResult processConnectionException(ConnectException e) {

        /**
         * 未知异常
         */
        logger.error("数据服务器连接失败：[{}]", e.getMessage(), e);
        return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_DATA_SERVICE);

    }

    @ExceptionHandler(value = ParseException.class)
    public CommonResult processConnectionException(ParseException e) {

        /**
         * 未知异常
         */
        logger.error("数据解析异常：[{}]", e.getMessage(), e);
        return ResultUtil.returnError(ErrorCodeEnum.DATA_PARSE_ERROR);

    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult processMissingServletRequestParameterException(MissingServletRequestParameterException e) {

        /**
         * 未知异常
         */
        logger.error("参数错误：[{}]", e.getMessage(), e);
        return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);

    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult processConstraintViolationException(ConstraintViolationException e) {

        /**
         * 未知异常
         */
        logger.error("参数错误：[{}]", e.getMessage(), e);
        return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR + ":  " + e.getMessage());

    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResult processBindExceptionException(BindException e) {

        /**
         * 未知异常
         */
        logger.error("参数错误：[{}]", e.getBindingResult().getTarget().toString(), e);
        StringBuilder s = new StringBuilder();
        List<FieldError> allErrors = e.getBindingResult().getFieldErrors();
        if (!StringUtils.isNullorEmpty(allErrors)) {
            for (FieldError error : allErrors) {
                s.append(error.getField()).append(":").append(error.getDefaultMessage()).append(",");
            }
            return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR, s.toString());
        }
        return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult processException(Exception e) {

        /**
         * 未知异常
         */
        logger.error("业务异常信息：[{}]", e.getMessage(), e);
        e.printStackTrace();
        return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return ResultUtil.returnError("302", e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public CommonResult handleMethodArgumentNotValidException(UnexpectedTypeException e) {
        logger.error(e.getMessage(), e);
        return ResultUtil.returnError("302", e.getMessage());
    }

}

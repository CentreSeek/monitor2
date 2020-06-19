package com.yjjk.monitor.configer;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-06-10 16:58:47
 **/

import com.yjjk.monitor.utility.ResultUtil;
import org.apache.http.conn.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;


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
        e.printStackTrace();
        return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_DATA_SERVICE);

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

}

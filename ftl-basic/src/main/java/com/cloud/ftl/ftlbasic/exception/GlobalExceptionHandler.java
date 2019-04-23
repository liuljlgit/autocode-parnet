package com.cloud.ftl.ftlbasic.exception;

import com.cloud.ftl.ftlbasic.webEntity.CodeEnum;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 默认异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResp<Object> defultExcepitonHandler(HttpServletRequest request, HttpServletResponse response,Exception e) {
        logger.info(e.getMessage(),e);
        return RespEntity.error(CodeEnum.EXEC_ERROR);
    }

    /**
     * 业务异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusiException.class)
    @ResponseBody
    public CommonResp<Object> busiExcepitonHandler(HttpServletRequest request, HttpServletResponse response, BusiException e) {
        logger.info(e.getMessage(),e);
        Integer code = e.getCode() == null ? CodeEnum.EXEC_BUSI_ERROR.getCode() : e.getCode();
        String msg = e.getMsg() == null ? CodeEnum.EXEC_BUSI_ERROR.getMsg() : e.getMsg();
        return RespEntity.error(code,msg);
    }

}

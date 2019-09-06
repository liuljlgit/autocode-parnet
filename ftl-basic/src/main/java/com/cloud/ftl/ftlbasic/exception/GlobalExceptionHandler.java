package com.cloud.ftl.ftlbasic.exception;

import com.cloud.ftl.ftlbasic.webEntity.CodeEnum;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 * @author lijun
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BusiException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp busiExcepitonHandler(BusiException ex) {
        log.info(ex.getMessage(),ex);
        Integer code = ex.getCode() == null ? CodeEnum.EXEC_BUSI_ERROR.getCode() : ex.getCode();
        String msg = ex.getMsg() == null ? CodeEnum.EXEC_BUSI_ERROR.getMsg() : ex.getMsg();
        return RespEntity.error(code,msg);
    }

    /**
     * 表单异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp processArgumentBindException(MethodArgumentNotValidException ex) {
        log.error("表单校验异常",ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldError = bindingResult.getFieldErrors();
        CommonResp errorResp = new CommonResp();
        List<CommonResp.ErrorField> errorFields = fieldError.stream().map(e -> {
            CommonResp.ErrorField errorField = new CommonResp.ErrorField();
            errorField.setField(e.getField());
            errorField.setErrMsg(e.getDefaultMessage());
            return errorField;
        }).collect(Collectors.toList());
        errorResp.setCode(CodeEnum.EXEC_PARAM_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_PARAM_ERROR.getMsg());
        errorResp.setErrorFields(errorFields);
        return errorResp;
    }

    /**
     * spring mvc参数校验
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp missingServletRequestParameterException(MissingServletRequestParameterException ex){
        log.error("参数校验异常",ex);
        CommonResp errorResp = new CommonResp();
        errorResp.setCode(CodeEnum.EXEC_SYS_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_SYS_ERROR.getMsg() + ":" + ex.getMessage());
        return errorResp;
    }

    /**
     * 默认异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp defultExcepitonHandler(Exception ex) {
        log.info(ex.getMessage(),ex);
        return RespEntity.error(CodeEnum.EXEC_ERROR);
    }

}

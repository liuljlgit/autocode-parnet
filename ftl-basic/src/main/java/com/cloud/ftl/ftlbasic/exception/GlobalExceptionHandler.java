package com.cloud.ftl.ftlbasic.exception;

import com.cloud.ftl.ftlbasic.webEntity.CodeEnum;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 * @author lijun
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务系统异常
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
     * 功能描述:  捕获 HttpMediaTypeNotSupportedException 异常，并处理，该异常由验证框架抛出
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CommonResp handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error("非法的MediaType请求 : ",ex);
        CommonResp errorResp = new CommonResp();
        errorResp.setCode(CodeEnum.EXEC_NOT_SUPPORT_MEDIATYPE_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_NOT_SUPPORT_MEDIATYPE_ERROR.getMsg());
        return errorResp;
    }

    /**
     * 功能描述:  捕获 HttpRequestMethodNotSupportedException 异常，并处理，该异常由验证框架抛出
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResp handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("非法的http method请求 : ",ex);
        CommonResp errorResp = new CommonResp();
        errorResp.setCode(CodeEnum.EXEC_NOT_SUPPORT_HTTPMETHOD_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_NOT_SUPPORT_HTTPMETHOD_ERROR.getMsg());
        return errorResp;
    }

    /**
     * 功能描述:  捕获 HttpMessageNotReadableException 异常，并处理，该异常由验证框架抛出
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("非法的http请求 : ",ex);
        CommonResp errorResp = new CommonResp();
        errorResp.setCode(CodeEnum.EXEC_NOT_VAILD_HTTP_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_NOT_VAILD_HTTP_ERROR.getMsg());
        return errorResp;
    }

    /**
     * @NotNull
     * 功能描述:  捕获 MethodArgumentNotValidException 异常，并处理，该异常由验证框架抛出
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp missingServletRequestParameterException(MissingServletRequestParameterException ex){
        log.error("参数缺失异常",ex);
        CommonResp errorResp = new CommonResp();
        errorResp.setCode(CodeEnum.EXEC_PARAM_MISS_ERROR.getCode());
        errorResp.setMsg(ex.getParameterName()+" "+CodeEnum.EXEC_PARAM_MISS_ERROR.getMsg());
        return errorResp;
    }

    /**
     * @NotBlank等不符合校验规则异常
     * 功能描述:  捕获 ConstraintViolationException 异常，并处理，该异常由验证框架抛出
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp handleResourceNotFoundException(ConstraintViolationException ex) {
        log.error("参数校验异常 : ",ex);
        List<String> errMsgs = Lists.newArrayList();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            errMsgs.add(violation.getMessage());
        }
        CommonResp errorResp = new CommonResp();
        errorResp.setCode(CodeEnum.EXEC_VAILD_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_VAILD_ERROR.getMsg());
        errorResp.setErrorMsgs(errMsgs);
        return errorResp;
    }

    /**
     * @RequestBody @Vaild entity
     * 功能描述:  捕获 MethodArgumentNotValidException 异常，并处理，该异常由验证框架抛出
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
        errorResp.setCode(CodeEnum.EXEC_FORM_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_FORM_ERROR.getMsg());
        errorResp.setErrorFields(errorFields);
        return errorResp;
    }

    /**
     * 功能描述:  捕获 BadSqlGrammarException 异常，并处理，该异常由MySQL抛出
     * @param ex
     * @return
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp handleMySQLSyntaxErrorException(BadSqlGrammarException ex) {
        log.error("sql异常 : ",ex);
        CommonResp errorResp = new CommonResp();
        errorResp.setCode(CodeEnum.EXEC_SQL_ERROR.getCode());
        errorResp.setMsg(CodeEnum.EXEC_SQL_ERROR.getMsg() + ":" + ex.getSQLException().getMessage());
        return errorResp;
    }

    /**
     * 功能描述:  搂底的异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResp defultExcepitonHandler(Exception ex) {
        log.info("系统异常",ex);
        return RespEntity.error(CodeEnum.EXEC_ERROR);
    }

}

package com.gyb.seckill.config;

import com.gyb.seckill.config.web.NoLoginException;
import com.gyb.seckill.utils.HttpServletUtil;
import com.gyb.seckill.vo.ResponseResult;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author geng
 * 2020/5/9
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        return ResponseResult.error(defaultMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleException(ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String messageTemplate = violation.getMessageTemplate();
            return ResponseResult.error(messageTemplate);
        }
        return null;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleNoLoginException(MissingServletRequestParameterException e){
        log.error(e.getMessage());
        return ResponseResult.error("丢失请求参数！");
    }

    @ExceptionHandler(NoLoginException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object handleNoLoginException(NoLoginException e){
        log.error(e.getMessage());
        if(HttpServletUtil.isAjaxRequest())
            return ResponseResult.error("用户未登录！");
        else{
            return new ModelAndView("/login");
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleException(Exception e){
        log.error("发生异常",e);
        return ResponseResult.error(e);
    }

}

package com.book.es.aspect;


import com.book.es.exception.ValidatorException;
import com.book.es.web.BaseController;
import com.book.es.web.WebResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;

@Component
@Aspect //拦截器
@Order(1)
public class ValidatorAspect implements BaseController {


    @Pointcut("@annotation(com.book.es.annotation.ValidatorAnnotation)")
    public void ePointCut() {
    }


    @Before(value = "ePointCut()")
    public WebResponse validator(JoinPoint joinPoint) throws ValidatorException {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError error = result.getFieldError();
                    throw new ValidatorException(defaultErr().setMsg(error.getDefaultMessage()));
                }
            }
        }
        return ok();
    }
}

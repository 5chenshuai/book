package com.book.es.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})   //次注解只能标注在方法上
@Retention(RetentionPolicy.RUNTIME) //  保留时间一只保留
public @interface ValidatorAnnotation {

}

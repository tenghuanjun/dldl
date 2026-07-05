package com.alibaba.fastjson.support.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.ResponseBody;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@ResponseBody
public @interface ResponseJSONP {
    String callback() default "callback";
}

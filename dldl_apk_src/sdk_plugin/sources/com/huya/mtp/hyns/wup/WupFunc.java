package com.huya.mtp.hyns.wup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WupFunc {
    String servant() default "";

    String value() default "";
}

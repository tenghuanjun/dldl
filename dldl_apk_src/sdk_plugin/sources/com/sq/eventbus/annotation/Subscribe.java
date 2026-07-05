package com.sq.eventbus.annotation;

import com.sq.eventbus.annotation.mode.ThreadMode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface Subscribe {
    boolean isSticky() default false;

    int priority() default 0;

    ThreadMode threadMode() default ThreadMode.POSTING;
}

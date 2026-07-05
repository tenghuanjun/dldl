package com.duowan.live.common.easyxml;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface EasyElementList {
    boolean inline() default false;

    String name() default "";

    Class type() default void.class;
}

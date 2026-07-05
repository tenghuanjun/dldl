package com.bumptech.glide.annotation.compiler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@interface Index {
    String[] extensions() default {};

    String[] modules() default {};
}

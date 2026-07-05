package com.duowan.auk.ui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IATitle {
    public static final int APP_NAME = 1;
    public static final int BUTTON_BACK = 0;
    public static final int BUTTON_SEARCH = 2;

    int background() default 0;

    int button() default 0;

    int title() default 0;

    int[] value() default {-1};
}

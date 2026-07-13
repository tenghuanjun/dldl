package com.nirvana.tools.jsoner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonerTag {
    boolean isExcluded() default false;

    String keyName() default "";
}

package com.volcengine.cloudcore.common.mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface VideoRenderMode {
    public static final int VIDEO_RENDER_MODE_COVER = 2;
    public static final int VIDEO_RENDER_MODE_FILL = 1;
    public static final int VIDEO_RENDER_MODE_FIT = 0;
}

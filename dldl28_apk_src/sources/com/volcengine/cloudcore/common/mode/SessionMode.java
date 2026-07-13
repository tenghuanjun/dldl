package com.volcengine.cloudcore.common.mode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
@Retention(RetentionPolicy.CLASS)
public @interface SessionMode {
    public static final int SESSION_MODE_AFK = 1;
    public static final int SESSION_MODE_BARRAGE = 2;
    public static final int SESSION_MODE_NORMAL = 0;
}

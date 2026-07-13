package com.volcengine.cloudcore.common.mode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface SyncPolicy {
    public static final String CLOSE = "close";
    public static final String OPEN = "open";
    public static final String VIDEO_FOLLOW_AUDIO = "video_follow_audio";
}

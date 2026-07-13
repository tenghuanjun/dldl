package com.volcengine.cloudcore.common.mode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
@Retention(RetentionPolicy.CLASS)
public @interface AudioPlaybackDevice {
    public static final int EARPIECE = 2;
    public static final int HEADSET = 1;
    public static final int HEADSET_BLUETOOTH = 4;
    public static final int HEADSET_USB = 5;
    public static final int NONE = -1;
    public static final int SPEAKERPHONE = 3;
}

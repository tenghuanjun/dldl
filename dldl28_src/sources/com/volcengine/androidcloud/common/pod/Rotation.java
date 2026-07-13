package com.volcengine.androidcloud.common.pod;

import android.text.TextUtils;
import com.volcengine.cloudphone.base.VeVideoFrame;
import com.volcengine.common.SDKContext;

/* JADX INFO: loaded from: classes3.dex */
public enum Rotation {
    LANDSCAPE("landscape", 2),
    PORTRAIT("portrait", 1),
    AUTO_ROTATION("auto_rotation", -1);

    public final String data;
    public final int orientation;

    Rotation(String str, int i) {
        this.data = str;
        this.orientation = i;
    }

    public static Rotation from(int i) {
        return (i == 0 || i == 180) ? PORTRAIT : LANDSCAPE;
    }

    public static Rotation from(String str) {
        Rotation rotation = PORTRAIT;
        if (TextUtils.equals(str, rotation.data)) {
            return rotation;
        }
        Rotation rotation2 = LANDSCAPE;
        if (TextUtils.equals(str, rotation2.data)) {
            return rotation2;
        }
        Rotation rotation3 = AUTO_ROTATION;
        return TextUtils.equals(str, rotation3.data) ? rotation3 : SDKContext.getDisplayRotation();
    }

    public static Rotation valueOf(int i) {
        Rotation rotation = LANDSCAPE;
        if (i == rotation.orientation) {
            return rotation;
        }
        Rotation rotation2 = PORTRAIT;
        return i == rotation2.orientation ? rotation2 : AUTO_ROTATION;
    }

    public int toRotation() {
        if (this.orientation == 1) {
            return 0;
        }
        return VeVideoFrame.VideoRotation.VIDEO_ROTATION_270;
    }
}

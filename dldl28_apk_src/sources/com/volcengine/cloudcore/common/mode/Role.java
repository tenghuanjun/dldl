package com.volcengine.cloudcore.common.mode;

import com.volcengine.androidcloud.common.log.AcLog;

/* JADX INFO: loaded from: classes3.dex */
public enum Role {
    VIEWER(0),
    PLAYER(1);

    private static final String TAG = "ROLE";
    public final int id;

    Role(int i) {
        this.id = i;
    }

    public static Role valueOf(int i) {
        if (i == 0) {
            return VIEWER;
        }
        if (i == 1) {
            return PLAYER;
        }
        AcLog.e(TAG, "unknown id");
        return VIEWER;
    }
}

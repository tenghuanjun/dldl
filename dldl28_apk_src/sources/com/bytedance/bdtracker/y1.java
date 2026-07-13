package com.bytedance.bdtracker;

import com.tencent.connect.common.Constants;

/* JADX INFO: loaded from: classes2.dex */
public final class y1 {
    public static String a(int i) {
        return i != 0 ? i != 1 ? i != 2 ? Constants.APP_VERSION_UNKNOWN : "STATE_DISABLED" : "STATE_ENABLED" : "STATE_DEFAULT";
    }
}

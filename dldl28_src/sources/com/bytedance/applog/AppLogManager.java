package com.bytedance.applog;

import com.bytedance.bdtracker.b;
import com.bytedance.bdtracker.n0;

/* JADX INFO: loaded from: classes2.dex */
public final class AppLogManager {
    public static IAppLogInstance getInstance(String str) {
        if (n0.c(str)) {
            return null;
        }
        return b.a(str);
    }
}

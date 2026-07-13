package com.volcengine.j;

import android.content.Context;
import android.os.Process;

/* JADX INFO: loaded from: classes3.dex */
public class f {
    public static int a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid());
    }

    public static boolean b(Context context, String str) {
        return a(context, str) == 0;
    }
}

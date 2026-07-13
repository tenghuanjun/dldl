package com.bytedance.framwork.core.sdkmonitor;

import android.content.Context;
import com.bytedance.framwork.core.sdklib.util.ProcessUtils;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorHelper {
    private static String sProcessName;
    private static String sShortProcessName;

    public static String getProcessName(Context context) {
        if (sProcessName == null) {
            sProcessName = ProcessUtils.getCurProcessName(context);
        }
        return sProcessName;
    }

    public static String getShortProcessName(Context context) {
        if (sShortProcessName == null) {
            String strReplace = ProcessUtils.getCurProcessName(context).replace(context.getPackageName(), "p").replace(":", "_");
            sShortProcessName = strReplace;
            sShortProcessName = strReplace.replace(".", "_");
        }
        return sShortProcessName;
    }
}

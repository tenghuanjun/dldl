package com.sqwan.common.net.base;

import android.os.Process;
import com.sqwan.common.util.RandomUtils;
import com.sqwan.msdk.api.IMUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestUtil {
    public static String generateRequestId() {
        return IMUrl.OS + "-" + Process.myPid() + "-" + System.currentTimeMillis() + "-" + RandomUtils.randomData(16);
    }

    public static String generateRequestLiveId(long j, String str) {
        return "android-" + Process.myPid() + "-" + j + "-" + str;
    }
}

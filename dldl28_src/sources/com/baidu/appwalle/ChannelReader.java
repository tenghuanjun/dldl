package com.baidu.appwalle;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public final class ChannelReader {
    private ChannelReader() {
    }

    public static String get(Context context) {
        String apkPath = getApkPath(context);
        String raw = TextUtils.isEmpty(apkPath) ? null : getRaw(new File(apkPath));
        if (!TextUtils.isEmpty(raw)) {
            raw = raw.replaceAll("\"", "").replaceAll("#", "");
        }
        try {
            return TextUtils.isEmpty(raw) ? "" : new String(Base64.decode(raw.getBytes(), 2));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getApkPath(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.sourceDir;
        } catch (Exception e) {
            Log.d("异常", e.getMessage());
            return null;
        }
    }

    public static String getRaw(File apkFile) {
        return PayloadReader.getString(apkFile, 1896449981);
    }
}

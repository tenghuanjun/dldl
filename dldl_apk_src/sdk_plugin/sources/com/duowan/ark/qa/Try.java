package com.duowan.ark.qa;

import com.duowan.ark.ArkValue;
import com.duowan.ark.util.KLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Try {

    public interface OnTryInRelease {
        void onTry();
    }

    public static void inReleaseEnv(OnTryInRelease onTryInRelease) {
        if (ArkValue.isSnapshot() || ArkValue.versionCode() == 0) {
            onTryInRelease.onTry();
            return;
        }
        try {
            onTryInRelease.onTry();
        } catch (Exception e) {
            KLog.error(onTryInRelease.getClass(), e);
        }
    }
}

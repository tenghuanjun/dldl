package com.bytedance.sdk.openadsdk;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface AdConfig {
    int getAgeGroup();

    String getAppId();

    String getAppName();

    TTCustomController getCustomController();

    String getData();

    int[] getDirectDownloadNetworkType();

    @Deprecated
    Object getExtra(String str);

    Map<String, Object> getInitExtra();

    String getKeywords();

    int getPluginUpdateConfig();

    int getThemeStatus();

    int getTitleBarTheme();

    boolean isAllowShowNotify();

    boolean isDebug();

    boolean isPaid();

    boolean isSupportMultiProcess();
}

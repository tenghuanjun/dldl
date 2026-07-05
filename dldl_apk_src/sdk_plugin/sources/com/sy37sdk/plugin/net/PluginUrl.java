package com.sy37sdk.plugin.net;

import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PluginUrl {
    public static final String KEY_HOTFIX_CONFIG = "hotfix_config";
    public static final String KEY_REPORT_PLUGIN = "report_plugin";

    @UrlUpdate(KEY_HOTFIX_CONFIG)
    public static String PLUGIN_CONFIG_URL = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/hotfixConf";

    @UrlUpdate(KEY_REPORT_PLUGIN)
    public static String PLUGIN_REPORT_URL = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/reportPluginDownload";
}

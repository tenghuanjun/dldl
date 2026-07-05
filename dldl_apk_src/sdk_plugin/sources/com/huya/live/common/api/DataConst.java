package com.huya.live.common.api;

import com.duowan.auk.ArkValue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DataConst {
    public static final String MY_FAVOR_NUM;
    public static final String SUFFIX_DEFAULT_SHARE_ICON = "/icon/kiwi_share.png";
    public static final String URL_DEFAULT_SHARE = "https://www.huya.com/";
    public static final String URL_PREFIX_QIAN_LONG = "https://yydl.duowan.com/mobile/kiwi/android";
    public static final String URL_PREFIX_V2;
    public static final String URL_PREFIX_V2_DEBUG = "https://58.215.180.150:8001";
    public static final String URL_PREFIX_V2_RELEASE = "https://api-m.huya.com";

    static {
        URL_PREFIX_V2 = ArkValue.debuggable() ? URL_PREFIX_V2_DEBUG : URL_PREFIX_V2_RELEASE;
        MY_FAVOR_NUM = URL_PREFIX_V2 + "/mytab/getPraises";
    }
}

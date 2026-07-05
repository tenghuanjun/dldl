package com.huya.berry.webview;

import com.huya.berry.webview.jssdk.callhandler.CurrentChannelInfo;
import com.huya.berry.webview.jssdk.callhandler.GetAppInfo;
import com.huya.berry.webview.jssdk.callhandler.GetCurrentUserInfo;
import com.huya.berry.webview.jssdk.callhandler.HyUdbSdkCommon;
import com.huya.berry.webview.jssdk.callhandler.Quit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface JssdkConst {
    public static final Class[] callList = {GetAppInfo.class, GetCurrentUserInfo.class, Quit.class, CurrentChannelInfo.class, HyUdbSdkCommon.class};
}

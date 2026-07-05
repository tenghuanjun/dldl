package com.huya.mtp.hyns;

import com.huya.hal.HalConfig;
import com.huya.mtp.api.LogApi;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.hysignal.HalConfigWrapper;
import com.huya.mtp.hyns.rx.NSRxCallAdapterFactory;
import com.huya.mtp.hyns.volley.HttpUrlProtocol;
import com.huya.mtp.hyns.volley.MtpVolleyTransporter;
import com.huya.mtp.hyns.wup.UnipacketProtocol;
import com.huya.mtp.hyns.wup.WupProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSEasy {
    public static final String TAG = "NetService-NSEasy";

    public static void initNSSignal(HalConfigWrapper halConfigWrapper, boolean z, boolean z2) {
        halConfigWrapper.setSimpleConfig(z2, z);
        initNSSignal(halConfigWrapper, new MtpMarsTransporter(halConfigWrapper));
    }

    public static void initNSSignal(HalConfigWrapper halConfigWrapper, MtpMarsTransporter mtpMarsTransporter) {
        final String str;
        final String str2;
        final HalConfig.Builder halConfig = halConfigWrapper.getHalConfig();
        LogApi logApi = MTPApi.LOGGER;
        Object[] objArr = new Object[3];
        objArr[0] = "1.9.105-exvolley";
        objArr[1] = halConfig.isOversea() ? "海外" : "国内";
        objArr[2] = halConfig.isTestEnv() ? "测试" : "正式";
        logApi.info(TAG, "NS版本：%s, 环境：%s，%s", objArr);
        if (halConfig.isOversea()) {
            str = "wsapi.master.live";
            str2 = "52.66.11.39:4434";
        } else {
            str = "cdn.wup.huya.com";
            str2 = "14.116.175.151:4434";
        }
        WupProtocol wupProtocol = new WupProtocol();
        wupProtocol.setTransporter(mtpMarsTransporter);
        NS.initProtocol(WupProtocol.class, wupProtocol);
        ((WupProtocol) NS.getProtocolImpl(WupProtocol.class)).setUrlGetter(new WupProtocol.UrlGetter() { // from class: com.huya.mtp.hyns.NSEasy.1
            @Override // com.huya.mtp.hyns.wup.WupProtocol.UrlGetter
            public String getUrl(String str3, String str4) {
                if (halConfig.isTestEnv()) {
                    return str2;
                }
                return str;
            }
        });
        HttpUrlProtocol httpUrlProtocol = new HttpUrlProtocol();
        httpUrlProtocol.setTransporter(new MtpVolleyTransporter());
        NS.initProtocol(HttpUrlProtocol.class, httpUrlProtocol);
        UnipacketProtocol unipacketProtocol = new UnipacketProtocol();
        unipacketProtocol.setTransporter(mtpMarsTransporter);
        NS.initProtocol(UnipacketProtocol.class, unipacketProtocol);
        ((UnipacketProtocol) NS.getProtocolImpl(UnipacketProtocol.class)).setUrlGetter(new UnipacketProtocol.UrlGetter() { // from class: com.huya.mtp.hyns.NSEasy.2
            @Override // com.huya.mtp.hyns.wup.UnipacketProtocol.UrlGetter
            public String getUrl(String str3, String str4) {
                if (halConfig.isTestEnv()) {
                    return str2;
                }
                return str;
            }
        });
        ((WupProtocol) NS.getProtocolImpl(WupProtocol.class)).addCallAdapterFactory(new NSRxCallAdapterFactory());
    }

    public static void initVolley(boolean z, final boolean z2) {
        final String str;
        final String str2;
        NS.initProtocol(WupProtocol.class, new WupProtocol() { // from class: com.huya.mtp.hyns.NSEasy.3
            {
                setTransporter(new MtpVolleyTransporter());
            }
        });
        if (z) {
            str = "https://wsapi.master.live";
            str2 = "52.66.11.39:4434";
        } else {
            str = "https://cdn.wup.huya.com";
            str2 = "14.116.175.151:4434";
        }
        ((WupProtocol) NS.getProtocolImpl(WupProtocol.class)).setUrlGetter(new WupProtocol.UrlGetter() { // from class: com.huya.mtp.hyns.NSEasy.4
            @Override // com.huya.mtp.hyns.wup.WupProtocol.UrlGetter
            public String getUrl(String str3, String str4) {
                if (z2) {
                    return str2;
                }
                return str;
            }
        });
        ((WupProtocol) NS.getProtocolImpl(WupProtocol.class)).addCallAdapterFactory(new NSRxCallAdapterFactory());
    }
}

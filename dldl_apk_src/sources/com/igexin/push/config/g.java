package com.igexin.push.config;

import android.os.Bundle;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class g {
    public static final String a = "MetaDataConfig";

    private static void a() {
        try {
            Bundle bundle = com.igexin.push.core.e.i.getPackageManager().getApplicationInfo(com.igexin.push.core.e.i.getPackageName(), 128).metaData;
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    if ("PUSH_DOMAIN".equals(str)) {
                        bundle.getString(str);
                        com.igexin.b.a.c.a.a("PUSH_DOMAIN:" + bundle.getString(str), new Object[0]);
                        String string = bundle.getString(str);
                        SDKUrlConfig.setXfrAddressIps(new String[]{"socket://xfr." + string + ":5224"});
                        SDKUrlConfig.getXfrAddress();
                        com.igexin.b.a.c.a.a("XFR_ADDRESS_IPS:" + SDKUrlConfig.getXfrAddress()[0], new Object[0]);
                        SDKUrlConfig.XFR_ADDRESS_BAK = new String[]{"socket://xfr_bak." + string + ":5224"};
                        StringBuilder sb = new StringBuilder("XFR_ADDRESS_IPS_BAK:");
                        sb.append(SDKUrlConfig.XFR_ADDRESS_BAK[0]);
                        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
                        SDKUrlConfig.BI_ADDRESS_IPS = new String[]{"http://bi." + string + "/api.php"};
                        StringBuilder sb2 = new StringBuilder("BI_ADDRESS_IPS:");
                        sb2.append(SDKUrlConfig.BI_ADDRESS_IPS[0]);
                        com.igexin.b.a.c.a.a(sb2.toString(), new Object[0]);
                        SDKUrlConfig.CONFIG_ADDRESS_IPS = new String[]{"http://config." + string + "/api.php"};
                        StringBuilder sb3 = new StringBuilder("CONFIG_ADDRESS_IPS:");
                        sb3.append(SDKUrlConfig.CONFIG_ADDRESS_IPS[0]);
                        com.igexin.b.a.c.a.a(sb3.toString(), new Object[0]);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            com.igexin.b.a.c.a.a(e.toString(), new Object[0]);
        }
    }

    private static void a(String str) {
        SDKUrlConfig.setXfrAddressIps(new String[]{"socket://xfr." + str + ":5224"});
        SDKUrlConfig.getXfrAddress();
        com.igexin.b.a.c.a.a("XFR_ADDRESS_IPS:" + SDKUrlConfig.getXfrAddress()[0], new Object[0]);
        SDKUrlConfig.XFR_ADDRESS_BAK = new String[]{"socket://xfr_bak." + str + ":5224"};
        StringBuilder sb = new StringBuilder("XFR_ADDRESS_IPS_BAK:");
        sb.append(SDKUrlConfig.XFR_ADDRESS_BAK[0]);
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        SDKUrlConfig.BI_ADDRESS_IPS = new String[]{"http://bi." + str + "/api.php"};
        StringBuilder sb2 = new StringBuilder("BI_ADDRESS_IPS:");
        sb2.append(SDKUrlConfig.BI_ADDRESS_IPS[0]);
        com.igexin.b.a.c.a.a(sb2.toString(), new Object[0]);
        SDKUrlConfig.CONFIG_ADDRESS_IPS = new String[]{"http://config." + str + "/api.php"};
        StringBuilder sb3 = new StringBuilder("CONFIG_ADDRESS_IPS:");
        sb3.append(SDKUrlConfig.CONFIG_ADDRESS_IPS[0]);
        com.igexin.b.a.c.a.a(sb3.toString(), new Object[0]);
    }
}

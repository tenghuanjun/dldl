package com.alipay.security.mobile.module.deviceinfo;

import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class e {
    public static final long a = 300000;
    private Context b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private volatile int m = 0;

    private e() {
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01ab A[Catch: Exception -> 0x0210, TryCatch #0 {Exception -> 0x0210, blocks: (B:85:0x01a3, B:87:0x01ab, B:88:0x01ca, B:90:0x01d2, B:92:0x01e0), top: B:102:0x01a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d2 A[Catch: Exception -> 0x0210, TryCatch #0 {Exception -> 0x0210, blocks: (B:85:0x01a3, B:87:0x01ab, B:88:0x01ca, B:90:0x01d2, B:92:0x01e0), top: B:102:0x01a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x021d A[Catch: all -> 0x0227, TRY_LEAVE, TryCatch #9 {all -> 0x0227, blocks: (B:96:0x0214, B:99:0x021d), top: B:120:0x0214 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.alipay.security.mobile.module.deviceinfo.e a(android.content.Context r17) {
        /*
            Method dump skipped, instruction units count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.deviceinfo.e.a(android.content.Context):com.alipay.security.mobile.module.deviceinfo.e");
    }

    public void a(int i) {
        this.m = i;
    }

    public void a(String str) {
        this.c = str;
    }

    public boolean a() {
        return this.m != 0;
    }

    public double b() {
        return this.m;
    }

    public String b(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null || !wifiManager.isWifiEnabled()) {
                return "";
            }
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
            return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
        } catch (Exception unused) {
            return "";
        }
    }

    public void b(String str) {
        this.d = str;
    }

    public List<Map<String, String>> c() {
        WifiManager wifiManager;
        List<ScanResult> scanResults;
        ArrayList arrayList = new ArrayList();
        Context context = this.b;
        if (context == null || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null || (scanResults = wifiManager.getScanResults()) == null) {
            return arrayList;
        }
        for (ScanResult scanResult : scanResults) {
            HashMap map = new HashMap();
            map.put("wifiMac", scanResult.BSSID == null ? "" : scanResult.BSSID);
            map.put("ssid", scanResult.SSID);
            StringBuilder sb = new StringBuilder();
            sb.append(scanResult.level);
            map.put("rssi", sb.toString());
            arrayList.add(map);
        }
        return arrayList;
    }

    public void c(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.f = str;
    }

    public boolean d() {
        LocationManager locationManager;
        Context context = this.b;
        if (context == null || (locationManager = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION)) == null) {
            return false;
        }
        return locationManager.isProviderEnabled("gps");
    }

    public void e(String str) {
        this.g = str;
    }

    public boolean e() {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        WifiManager wifiManager2;
        List<WifiConfiguration> configuredNetworks;
        Context context = this.b;
        if (context == null || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return false;
        }
        Context context2 = this.b;
        String ssid = connectionInfo.getSSID();
        WifiConfiguration wifiConfiguration = null;
        if (context2 != null && ssid != null && (wifiManager2 = (WifiManager) context2.getSystemService("wifi")) != null && (configuredNetworks = wifiManager2.getConfiguredNetworks()) != null) {
            Iterator<WifiConfiguration> it = configuredNetworks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WifiConfiguration next = it.next();
                if (next.SSID.equals(ssid)) {
                    wifiConfiguration = next;
                    break;
                }
            }
        }
        if (wifiConfiguration == null) {
            return false;
        }
        char c = 3;
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            c = 2;
        } else if (!wifiConfiguration.allowedKeyManagement.get(2) && !wifiConfiguration.allowedKeyManagement.get(3)) {
            c = wifiConfiguration.wepKeys[0] != null ? (char) 1 : (char) 0;
        }
        return c != 0;
    }

    public String f() {
        return this.c;
    }

    public void f(String str) {
        this.h = str;
    }

    public String g() {
        return this.d;
    }

    public void g(String str) {
        this.i = str;
    }

    public String h() {
        return this.e;
    }

    public void h(String str) {
        this.j = str;
    }

    public String i() {
        return this.f;
    }

    public void i(String str) {
        this.k = str;
    }

    public String j() {
        return this.g;
    }

    public void j(String str) {
        this.l = str;
    }

    public String k() {
        return this.h;
    }

    public String l() {
        return this.i;
    }

    public String m() {
        return this.j;
    }

    public String n() {
        return this.k;
    }

    public String o() {
        return this.l;
    }
}

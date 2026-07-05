package com.hy;

import android.content.Context;
import com.huya.security.DeviceFingerprintSDK;
import com.huya.security.SdidHandler;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyDeviceProxy {
    public static HyDeviceProxy sHyDeviceProxy = new HyDeviceProxy();
    public ArrayList<HyDeviceWatcher> hyDeviceWatchers = new ArrayList<>();

    public void setUseSmDevice(boolean z) {
    }

    public static HyDeviceProxy instance() {
        return sHyDeviceProxy;
    }

    public void init(Context context) {
        DeviceFingerprintSDK.getInstance().init();
        DeviceFingerprintSDK.getInstance().addSdidHandler(new SdidHandler() { // from class: com.hy.HyDeviceProxy.1
            @Override // com.huya.security.SdidHandler
            public void onSdid(String str) {
                Iterator<HyDeviceWatcher> it = HyDeviceProxy.this.hyDeviceWatchers.iterator();
                while (it.hasNext()) {
                    it.next().notifySafeDeviceId(str, "");
                }
            }
        });
    }

    public void setAppInfoId(String str, String str2, String str3) {
        DeviceFingerprintSDK.getInstance().link(str, str3, str2);
    }

    public void addWatcher(HyDeviceWatcher hyDeviceWatcher) {
        this.hyDeviceWatchers.add(hyDeviceWatcher);
    }

    public String getDeviceid() {
        return DeviceFingerprintSDK.getInstance().getCDID();
    }

    public String getSafeDeviceid() {
        return DeviceFingerprintSDK.getInstance().getSDID();
    }

    public String fastGetSDID() {
        return getSafeDeviceid();
    }
}

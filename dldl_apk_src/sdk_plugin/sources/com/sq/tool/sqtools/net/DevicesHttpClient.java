package com.sq.tool.sqtools.net;

import android.text.TextUtils;
import com.sq.tool.sqtools.detector.log.LogTools;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DevicesHttpClient {
    private DevicesHttpClient() {
    }

    public static void post(String str, String str2, DHttpClient dHttpClient, final DevicesHttpCallback devicesHttpCallback) {
        if (dHttpClient == null) {
            LogTools.sendLog("Can not post as the Http Client is null!");
        } else {
            if (TextUtils.isEmpty(str)) {
                LogTools.sendLog("Can not post as the url is empty!");
                return;
            }
            HashMap map = new HashMap();
            map.put("Content-Type", "application/json;charset:utf-8");
            dHttpClient.postString(str, str2, map, new DevicesHttpCallback() { // from class: com.sq.tool.sqtools.net.DevicesHttpClient.1
                @Override // com.sq.tool.sqtools.net.DevicesHttpCallback
                public void onSuccess(String str3) {
                    LogTools.sendLog("post success response --> " + str3);
                    DevicesHttpCallback devicesHttpCallback2 = devicesHttpCallback;
                    if (devicesHttpCallback2 != null) {
                        devicesHttpCallback2.onSuccess(str3);
                    }
                }

                @Override // com.sq.tool.sqtools.net.DevicesHttpCallback
                public void onFail(int i, String str3) {
                    LogTools.sendLog("post fail code --> " + i + ", message --> " + str3);
                    DevicesHttpCallback devicesHttpCallback2 = devicesHttpCallback;
                    if (devicesHttpCallback2 != null) {
                        devicesHttpCallback2.onFail(i, str3);
                    }
                }
            });
        }
    }
}

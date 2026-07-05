package com.sq.tool.network;

import android.content.Context;
import android.text.TextUtils;
import com.sdk.sq.net.RequestBuilder;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.SQContextWrapper;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class IpHeaderTransformer implements RequestBuilder.HeadersTransformer {
    IpHeaderTransformer() {
    }

    @Override // com.sdk.sq.net.RequestBuilder.HeadersTransformer
    public Map<String, String> transform(Map<String, String> map) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        if (applicationContext == null || !ActivityLifeCycleUtils.getInstance().isForeground()) {
            return map;
        }
        String ipAddress = SensitiveInfoManager.getInstance().getIpAddress(applicationContext);
        String ipV6Address = SensitiveInfoManager.getInstance().getIpV6Address(applicationContext);
        if (TextUtils.isEmpty(ipAddress) && TextUtils.isEmpty(ipV6Address)) {
            return map;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        if (!TextUtils.isEmpty(ipAddress)) {
            map.put("Client-Ipv4", ipAddress);
        }
        if (!TextUtils.isEmpty(ipV6Address)) {
            map.put("Client-Ipv6", ipV6Address);
        }
        return map;
    }
}

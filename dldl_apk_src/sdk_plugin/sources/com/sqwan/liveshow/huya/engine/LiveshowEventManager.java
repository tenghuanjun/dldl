package com.sqwan.liveshow.huya.engine;

import com.huya.berry.client.HuyaBerry;
import com.sqwan.common.util.LogUtil;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowEventManager {
    private final String TAG = getClass().getSimpleName();

    public void onEvent(Map<String, String> map) {
        String str = map.get(HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("事件是");
        stringBuffer.append(str);
        stringBuffer.append(",");
        for (String str2 : map.keySet()) {
            String str3 = map.get(str2);
            stringBuffer.append(str2);
            stringBuffer.append(":");
            stringBuffer.append(str3);
            stringBuffer.append(",");
        }
        LogUtil.i(this.TAG, stringBuffer.toString());
    }
}

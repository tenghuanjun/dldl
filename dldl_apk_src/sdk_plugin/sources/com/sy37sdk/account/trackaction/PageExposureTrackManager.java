package com.sy37sdk.account.trackaction;

import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PageExposureTrackManager {
    private static final int TRACK_TIME_INTERVAL = 60000;

    public static void track(String str, String str2) {
        LogUtil.i("页面曝光" + str + " " + str2);
        HashMap map = new HashMap();
        map.put(SqTrackKey.view_id, str);
        map.put(SqTrackKey.view_name, str2);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_view_show, map);
    }
}

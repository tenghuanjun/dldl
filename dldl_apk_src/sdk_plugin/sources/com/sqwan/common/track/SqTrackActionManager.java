package com.sqwan.common.track;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
@Deprecated
public class SqTrackActionManager {
    private static String TAG = "TrackActionManagerer";
    private static SqTrackActionManager sInstance;
    private Context mContext;
    private String mSdkVersion;

    public void trackAction(SqTrackAction sqTrackAction, boolean z, HashMap<String, String> map, HashMap<String, String> map2) {
    }

    private SqTrackActionManager() {
    }

    public static SqTrackActionManager getInstance() {
        if (sInstance == null) {
            sInstance = new SqTrackActionManager();
        }
        return sInstance;
    }

    public void init(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.mSdkVersion = str;
    }

    public void trackAction(SqTrackAction sqTrackAction) {
        trackAction(sqTrackAction, SqTrackUtil.getLogined(this.mContext));
    }

    public void trackAction(SqTrackAction sqTrackAction, boolean z) {
        trackAction(sqTrackAction, z, (HashMap<String, String>) null);
    }

    public void trackAction(SqTrackAction sqTrackAction, HashMap<String, String> map) {
        trackAction(sqTrackAction, map, "");
    }

    public void trackAction(SqTrackAction sqTrackAction, String str) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ext", str);
        trackAction(sqTrackAction, SqTrackUtil.getLogined(this.mContext), map);
    }

    @Deprecated
    public void trackAction(SqTrackAction sqTrackAction, boolean z, String str) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ext", str);
        trackExtAction(sqTrackAction, z, map);
    }

    private void trackExtAction(SqTrackAction sqTrackAction, boolean z, HashMap<String, String> map) {
        trackAction(sqTrackAction, z, null, map);
    }

    public void trackAction(SqTrackAction sqTrackAction, HashMap<String, String> map, String str) {
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("ext", str);
        trackAction(sqTrackAction, SqTrackUtil.getLogined(this.mContext), map, map2);
    }

    public void trackAction(SqTrackAction sqTrackAction, boolean z, HashMap<String, String> map) {
        trackAction(sqTrackAction, z, map, null);
    }

    public void trackBtn(String str, String str2) {
        HashMap<String, String> map = new HashMap<>();
        map.put(SqTrackKey.btn_id, str);
        map.put("btnTxt", str2);
        map.putAll(roleTrackParam());
        trackAction(SqTrackAction.SDK_BTN_CLICK, map);
    }

    public HashMap<String, String> roleTrackParam() {
        HashMap<String, String> map = new HashMap<>();
        map.put("role_id", SqTrackUtil.getRoleid(this.mContext));
        map.put("role_name", SqTrackUtil.getRolename(this.mContext));
        map.put("role_level", SqTrackUtil.getRolelevel(this.mContext));
        map.put("vip_level", SqTrackUtil.getVipLevel(this.mContext));
        map.put("server_id", SqTrackUtil.getServerid(this.mContext));
        map.put("server_name", SqTrackUtil.getServerName(this.mContext));
        return map;
    }
}

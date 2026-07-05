package com.sq.track;

import android.content.Context;
import com.sqwan.common.mod.track.ITrackMod2;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TrackModImpl2 implements ITrackMod2 {
    private final Context mContext;

    public TrackModImpl2(Context context) {
        this.mContext = context;
    }

    @Override // com.sqwan.common.mod.track.ITrackMod2
    public void init(Context context) {
        ThinkingDataManager2.getInstance().init(context);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod2
    public void track(String str, Map<String, String> map) {
        ThinkingDataManager2.getInstance().track(str, map);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod2
    public void userSet(String str, String str2) {
        ThinkingDataManager2.getInstance().userSet(str, str2);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod2
    public void userSetOnce(String str, String str2) {
        ThinkingDataManager2.getInstance().userSetOnce(str, str2);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod2
    public void setUserId(String str) {
        ThinkingDataManager2.getInstance().setUserId(str);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod2
    public void flush() {
        ThinkingDataManager2.getInstance().flush();
    }
}

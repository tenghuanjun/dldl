package com.sq.track;

import android.content.Context;
import com.sqwan.common.mod.track.ITrackMod;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TrackModImpl implements ITrackMod {
    private final Context mContext;

    public TrackModImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.sqwan.common.mod.track.ITrackMod
    public void init(Context context) {
        ThinkingDataManager.getInstance().init(context);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod
    public void track(String str, HashMap<String, String> map) {
        ThinkingDataManager.getInstance().track(str, map);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod
    public void userSet(String str, String str2) {
        ThinkingDataManager.getInstance().userSet(str, str2);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod
    public void userSetOnce(String str, String str2) {
        ThinkingDataManager.getInstance().userSetOnce(str, str2);
    }

    @Override // com.sqwan.common.mod.track.ITrackMod
    public void setUserId(String str) {
        ThinkingDataManager.getInstance().setUserId(str);
    }
}

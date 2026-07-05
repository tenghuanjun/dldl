package com.sqwan.common.mod.track;

import android.content.Context;
import com.sqwan.common.mod.IModBase;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ITrackMod extends IModBase {
    void init(Context context);

    void setUserId(String str);

    void track(String str, HashMap<String, String> map);

    void userSet(String str, String str2);

    void userSetOnce(String str, String str2);
}

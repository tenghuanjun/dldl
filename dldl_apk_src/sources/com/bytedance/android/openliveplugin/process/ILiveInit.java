package com.bytedance.android.openliveplugin.process;

import android.content.Context;
import com.bytedance.android.live.base.api.ILiveHostContextParam;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ILiveInit {
    void initLiveInMain(ILiveHostContextParam iLiveHostContextParam, String str, Boolean bool);

    void initLiveProcess(Context context);

    void registerSubProcessStub();
}

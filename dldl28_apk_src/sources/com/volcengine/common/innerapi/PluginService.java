package com.volcengine.common.innerapi;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public interface PluginService {
    public static final int STATUS_CODE_SUCCESS = 0;
    public static final String TAG_PLUGIN = "VE_PLUGIN";

    public interface ILoadResultListener {
        void onLoadFailed(int i, String str);

        void onLoadSuccess();
    }

    void addLoadResultListener(ILoadResultListener iLoadResultListener);

    boolean isLoadedClass(String str);

    void load(Context context);

    <T> T loadClass(String str, Object... objArr);

    void removeLoadResultListener(ILoadResultListener iLoadResultListener);
}

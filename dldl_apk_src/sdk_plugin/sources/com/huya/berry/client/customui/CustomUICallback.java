package com.huya.berry.client.customui;

import com.huya.berry.gamesdk.base.BaseCallback;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface CustomUICallback<T extends BaseCallback> {
    void onResultCallback(int i, T t);

    void onResultListCallback(int i, List<T> list);
}

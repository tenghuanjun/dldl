package com.sqwan.common.mod.plugin;

import android.content.Context;
import com.sqwan.common.mod.IModBase;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IPluginMod extends IModBase {
    int getPluginVersion();

    void refresh(Context context);
}

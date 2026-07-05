package com.sqwan.common.mod.account;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ILoginListener {
    void onFailure(int i, String str);

    void onSuccess(Map<String, String> map);
}

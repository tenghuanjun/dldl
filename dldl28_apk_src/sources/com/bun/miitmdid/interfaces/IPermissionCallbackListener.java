package com.bun.miitmdid.interfaces;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IPermissionCallbackListener {
    void onAskAgain(List<String> list);

    void onDenied(List<String> list);

    void onGranted(String[] strArr);
}

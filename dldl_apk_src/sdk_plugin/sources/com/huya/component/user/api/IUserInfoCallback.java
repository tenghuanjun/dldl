package com.huya.component.user.api;

import android.app.FragmentManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IUserInfoCallback {
    void onUserInfoSet(String str);

    void showUserInfoDialog(FragmentManager fragmentManager, long j, String str, String str2, int i);
}

package com.bytedance.bdtracker;

import com.bytedance.applog.profile.UserProfileCallback;

/* JADX INFO: loaded from: classes2.dex */
public class c3 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d3 f229a;

    public c3(d3 d3Var) {
        this.f229a = d3Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        UserProfileCallback userProfileCallback = this.f229a.d;
        if (userProfileCallback != null) {
            userProfileCallback.onSuccess();
        }
    }
}

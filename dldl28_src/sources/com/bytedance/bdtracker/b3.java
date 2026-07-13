package com.bytedance.bdtracker;

import com.bytedance.applog.profile.UserProfileCallback;

/* JADX INFO: loaded from: classes2.dex */
public class b3 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f219a;
    public final /* synthetic */ d3 b;

    public b3(d3 d3Var, int i) {
        this.b = d3Var;
        this.f219a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        UserProfileCallback userProfileCallback = this.b.d;
        if (userProfileCallback != null) {
            userProfileCallback.onFail(this.f219a);
        }
    }
}

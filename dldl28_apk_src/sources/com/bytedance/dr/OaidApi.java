package com.bytedance.dr;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface OaidApi {

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f366a;
        public boolean b;
    }

    String getName();

    a getOaid(Context context);

    boolean support(Context context);
}

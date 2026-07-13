package com.bytedance.bdtracker;

import android.accounts.Account;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class n1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile g4 f295a;
    public Account b;
    public i3 c;

    public g4 a(d dVar, Context context, i1 i1Var) {
        if (this.f295a == null) {
            synchronized (n1.class) {
                if (this.f295a == null) {
                    if (context == null) {
                        throw new IllegalArgumentException("context == null");
                    }
                    if (this.c == null) {
                        this.c = new i3(dVar, context);
                    }
                    if (this.f295a == null) {
                        this.f295a = new c4(dVar, context, i1Var, this.c);
                        if (this.b != null) {
                            ((c4) this.f295a).a(this.b);
                        }
                    }
                }
            }
        }
        return this.f295a;
    }
}

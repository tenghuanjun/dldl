package com.bytedance.bdtracker;

import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class n4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static y3<String> f298a = new a();

    public static class a extends y3<String> {
        @Override // com.bytedance.bdtracker.y3
        public String a(Object[] objArr) {
            return UUID.randomUUID().toString();
        }
    }

    public static String a() {
        return f298a.b(new Object[0]);
    }
}

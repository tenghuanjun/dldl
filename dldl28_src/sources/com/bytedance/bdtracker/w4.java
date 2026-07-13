package com.bytedance.bdtracker;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public final class w4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static v4<String> f336a = new a();

    public static class a extends v4<String> {
        @Override // com.bytedance.bdtracker.v4
        public String a(Object[] objArr) {
            SharedPreferences sharedPreferences = (SharedPreferences) objArr[0];
            String string = sharedPreferences.getString("cdid", "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String string2 = UUID.randomUUID().toString();
            com.bytedance.bdtracker.a.a(sharedPreferences, "cdid", string2);
            return string2;
        }
    }
}

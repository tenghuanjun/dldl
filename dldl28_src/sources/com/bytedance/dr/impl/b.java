package com.bytedance.dr.impl;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.bdtracker.d5;
import com.bytedance.bdtracker.j4;
import com.bytedance.bdtracker.v4;
import com.bytedance.dr.OaidApi;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b<SERVICE> implements OaidApi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f374a;
    public v4<Boolean> b = new a();

    public class a extends v4<Boolean> {
        public a() {
        }

        @Override // com.bytedance.bdtracker.v4
        public Boolean a(Object[] objArr) {
            return Boolean.valueOf(j4.a((Context) objArr[0], b.this.f374a));
        }
    }

    public b(String str) {
        this.f374a = str;
    }

    public abstract Intent a(Context context);

    public abstract d5.b<SERVICE, String> a();

    @Override // com.bytedance.dr.OaidApi
    public OaidApi.a getOaid(Context context) {
        String str = (String) new d5(context, a(context), a()).a();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        OaidApi.a aVar = new OaidApi.a();
        aVar.f366a = str;
        return aVar;
    }

    @Override // com.bytedance.dr.OaidApi
    public boolean support(Context context) {
        if (context == null) {
            return false;
        }
        return this.b.b(context).booleanValue();
    }
}

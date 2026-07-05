package com.unionpay;

import java.util.Comparator;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class f implements Comparator {
    String a;

    f(String str) {
        this.a = "";
        this.a = str;
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        long jOptLong = ((JSONObject) obj).optLong(this.a);
        long jOptLong2 = ((JSONObject) obj2).optLong(this.a);
        if (jOptLong < jOptLong2) {
            return -1;
        }
        return jOptLong > jOptLong2 ? 1 : 0;
    }
}

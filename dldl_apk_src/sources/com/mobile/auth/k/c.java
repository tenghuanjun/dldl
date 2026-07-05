package com.mobile.auth.k;

import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class c extends g {
    private final String a;

    private static class b<K, V> extends HashMap<K, V> {
        private b() {
        }

        public V a(Object obj, V v) {
            return (!containsKey(obj) || get(obj) == null) ? v : get(obj);
        }
    }

    public c(String str) {
        this.a = str;
    }

    @Override // com.mobile.auth.k.g
    public String a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mobile.auth.k.g
    public String a(String str) {
        return null;
    }

    @Override // com.mobile.auth.k.g
    public JSONObject b() {
        return new JSONObject();
    }
}

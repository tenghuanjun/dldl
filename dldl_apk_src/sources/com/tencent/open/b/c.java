package com.tencent.open.b;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class c implements Serializable {
    public final HashMap<String, String> a;

    public c(Bundle bundle) {
        this.a = new HashMap<>();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                this.a.put(str, bundle.getString(str));
            }
        }
    }

    public c(HashMap<String, String> map) {
        this.a = new HashMap<>(map);
    }

    public String toString() {
        return "BaseData{time=" + this.a.get("time") + ", name=" + this.a.get("interface_name") + '}';
    }
}

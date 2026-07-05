package com.alipay.deviceid.module.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class av extends ba {
    String a;
    byte[] b;
    boolean e;
    ArrayList<Header> d = new ArrayList<>();
    private Map<String, String> h = new HashMap();
    String c = "application/x-www-form-urlencoded";

    public av(String str) {
        this.a = str;
    }

    public final String a(String str) {
        Map<String, String> map = this.h;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public final void a(String str, String str2) {
        if (this.h == null) {
            this.h = new HashMap();
        }
        this.h.put(str, str2);
    }

    public final void a(Header header) {
        this.d.add(header);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        av avVar = (av) obj;
        byte[] bArr = this.b;
        if (bArr == null) {
            if (avVar.b != null) {
                return false;
            }
        } else if (!bArr.equals(avVar.b)) {
            return false;
        }
        String str = this.a;
        String str2 = avVar.a;
        if (str == null) {
            if (str2 != null) {
                return false;
            }
        } else if (!str.equals(str2)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Map<String, String> map = this.h;
        int iHashCode = ((map == null || !map.containsKey("id")) ? 1 : this.h.get("id").hashCode() + 31) * 31;
        String str = this.a;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", this.a, this.d);
    }
}

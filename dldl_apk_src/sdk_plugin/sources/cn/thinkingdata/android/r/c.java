package cn.thinkingdata.android.r;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class c extends d {
    private String d;
    private String e;
    public Map<String, Object> f = new HashMap();

    public c(String str) {
        this.d = str;
    }

    public c a(String str, int i) {
        this.f.put(str, Integer.valueOf(i));
        return this;
    }

    public c a(String str, Object obj) {
        this.f.put(str, obj);
        return this;
    }

    public c a(String str, String str2) {
        this.f.put(str, str2);
        return this;
    }

    public Object a(Context context) {
        return f.a().a(context, this);
    }

    public c b(String str) {
        this.e = str;
        return this;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public Object f() {
        return a((Context) null);
    }
}

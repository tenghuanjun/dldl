package cn.thinkingdata.android.utils;

import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface i {

    public static class a extends Exception {
        a(String str) {
            super(str);
        }
    }

    String a(String str, String str2, boolean z, SSLSocketFactory sSLSocketFactory, Map<String, String> map);
}

package com.tencent.open.a;

import android.os.Build;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.i;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static f f1043a;
    private i b;
    private a c;

    protected f() {
        b();
    }

    private void b() {
        String str = "AndroidSDK_" + Build.VERSION.SDK + "_" + com.tencent.open.utils.f.a().b(com.tencent.open.utils.g.a()) + "_" + Build.VERSION.RELEASE;
        try {
            this.c = new e(str);
        } catch (NoClassDefFoundError e) {
            SLog.e("openSDK_LOG.OpenHttpService", "initClient okHttp catch error", e);
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.OpenHttpService", "initClient okHttp catch throwable", th);
        }
        if (this.c == null) {
            this.c = new b(str);
        }
    }

    public static f a() {
        if (f1043a == null) {
            synchronized (f.class) {
                if (f1043a == null) {
                    f1043a = new f();
                }
            }
        }
        f1043a.c();
        return f1043a;
    }

    public void a(i iVar) {
        this.b = iVar;
        c();
    }

    private void c() {
        i iVar = this.b;
        if (iVar == null) {
            return;
        }
        int iA = iVar.a("Common_HttpConnectionTimeout");
        if (iA == 0) {
            iA = 15000;
        }
        int iA2 = this.b.a("Common_SocketConnectionTimeout");
        if (iA2 == 0) {
            iA2 = 30000;
        }
        a(iA, iA2);
    }

    public void a(long j, long j2) {
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(j, j2);
        }
    }

    public g a(String str, Map<String, String> map) throws IOException {
        if (map == null || map.isEmpty()) {
            return a(str, "");
        }
        StringBuilder sb = new StringBuilder("");
        for (String str2 : map.keySet()) {
            String str3 = map.get(str2);
            if (str3 != null) {
                sb.append(URLEncoder.encode(str2, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(str3, "UTF-8"));
                sb.append("&");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return a(str, sb.toString());
    }

    public g a(String str, String str2) throws IOException {
        SLog.i("openSDK_LOG.OpenHttpService", "get.");
        return this.c.a(str, str2);
    }

    public g b(String str, Map<String, String> map) throws IOException {
        SLog.i("openSDK_LOG.OpenHttpService", "post data");
        return this.c.a(str, map);
    }

    public g a(String str, Map<String, String> map, Map<String, byte[]> map2) throws IOException {
        if (map2 == null || map2.size() == 0) {
            return b(str, map);
        }
        return this.c.a(str, map, map2);
    }
}

package com.volcengine.a;

import android.content.Context;
import android.text.TextUtils;
import com.lzy.okgo.cookie.SerializableCookie;
import com.tencent.connect.common.Constants;
import com.volcengine.b.a;
import com.volcengine.b.c;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b implements Runnable {
    private final Context a;
    private final com.volcengine.a.a b;
    private final ExecutorService c;
    private volatile com.volcengine.b.a g;
    private final Queue<com.volcengine.b.a> d = new LinkedBlockingQueue();
    private final Object e = new Object();
    private final Map<String, String> f = new ConcurrentHashMap();
    private final a.b h = new a();

    class a implements a.b {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x008a  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00d5 A[Catch: all -> 0x00e5, Exception -> 0x00e7, TRY_LEAVE, TryCatch #1 {Exception -> 0x00e7, blocks: (B:3:0x0006, B:5:0x000c, B:6:0x002e, B:8:0x0034, B:9:0x0058, B:24:0x008d, B:26:0x00b5, B:27:0x00d5, B:14:0x0073, B:17:0x007d), top: B:37:0x0006, outer: #0 }] */
        @Override // com.volcengine.b.a.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(com.volcengine.b.a r7, java.util.Map<java.lang.String, java.lang.String> r8) {
            /*
                Method dump skipped, instruction units count: 259
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.volcengine.a.b.a.a(com.volcengine.b.a, java.util.Map):void");
        }
    }

    public b(Context context, ExecutorService executorService, com.volcengine.a.a aVar) {
        this.a = context;
        this.b = aVar;
        this.c = executorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> a(Map<String, String> map) {
        map.remove("diagnosis_time");
        map.remove("diagnosis_interrupt");
        map.remove("diagnosis_type");
        return new HashMap(map);
    }

    private void a(com.volcengine.b.a aVar) {
        synchronized (this.e) {
            this.d.add(aVar);
            if (this.g == null) {
                this.c.submit(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> b(Map<String, String> map) {
        map.remove("local_ip");
        map.remove("local_dns");
        map.remove("local_gateway");
        return new HashMap(map);
    }

    private void b() {
        synchronized (this.e) {
            if (this.d.isEmpty()) {
                this.g = null;
            } else {
                this.g = this.d.poll();
                this.g.d();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(Map<String, String> map) {
        if (this.f.containsKey("is_network_connected") && TextUtils.equals(this.f.get("is_network_connected"), Boolean.toString(false))) {
            return "1";
        }
        if (!map.containsKey("ping_hot_host") && !map.containsKey("ping_hot_ip")) {
            return this.f.containsKey("master_host") ? "2" : "2";
        }
        if (!TextUtils.equals(map.get("ping_hot_host"), "0") && !TextUtils.equals(map.get("ping_hot_ip"), "0")) {
            if (TextUtils.equals(map.get("ping_hot_host"), "1") && TextUtils.equals(map.get("ping_hot_ip"), "1")) {
                return Constants.VIA_SHARE_TYPE_INFO;
            }
            if ((this.f.containsKey("master_host") && !this.f.containsKey(SerializableCookie.HOST)) || this.f.containsKey("dns_resolver")) {
                if (map.containsKey("ping_loopback_ip") && TextUtils.equals(map.get("ping_loopback_ip"), "1")) {
                    return "3";
                }
                if (map.containsKey("ping_local_ip") && TextUtils.equals(map.get("ping_local_ip"), "1")) {
                    return Constants.VIA_TO_TYPE_QZONE;
                }
                if (map.containsKey("ping_gateway") && TextUtils.equals(map.get("ping_gateway"), "1")) {
                    return "5";
                }
                if ((map.containsKey("ping_host") || map.containsKey("ping_host_ip") || map.containsKey("ping_dns_cache_ip")) && (TextUtils.equals(map.get("ping_host"), "1") || TextUtils.equals(map.get("ping_host_ip"), "1") || TextUtils.equals(map.get("ping_dns_cache_ip"), "1"))) {
                    return "7";
                }
            }
        }
        return "0";
    }

    public void a() {
        if (this.g != null) {
            this.g.a();
        }
        this.d.clear();
    }

    public Map<String, String> c() {
        return b(new HashMap(this.f));
    }

    public void d(Map<String, String> map) {
        if (this.d.isEmpty()) {
            a(new com.volcengine.b.b(this.a, map, this.f, this.h));
            a(new c(this.a, map, this.f, this.c, this.h));
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        b();
    }
}

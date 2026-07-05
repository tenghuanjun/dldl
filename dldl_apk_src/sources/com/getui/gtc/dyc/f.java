package com.getui.gtc.dyc;

import android.text.TextUtils;
import com.getui.gtc.base.util.ScheduleQueue;
import com.getui.gtc.dyc.b.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class f {
    private e a;
    private g c;
    private final List<com.getui.gtc.dyc.b.c> d;
    private final Map<String, Object> e;

    static class a {
        private static f a = new f();
    }

    private f() {
        this.a = e.a();
        this.c = g.a();
        this.d = new ArrayList();
        this.e = new ConcurrentHashMap();
    }

    public static f a() {
        return a.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(long j, long j2) {
        return System.currentTimeMillis() - j2 > j;
    }

    public Map<String, String> a(final b bVar) {
        try {
            final h hVarA = this.a.a(bVar.b());
            if (hVarA == null || a(bVar.h(), hVarA.a())) {
                ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.dyc.f.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (f.this.e) {
                            if (f.this.e.get(bVar.b()) == null) {
                                f.this.e.put(bVar.b(), new Object());
                            }
                        }
                        synchronized (f.this.e.get(bVar.b())) {
                            h hVarA2 = f.this.a.a(bVar.b());
                            if (hVarA2 != null && ((hVarA == null || (hVarA2.d() != null && !hVarA2.d().equals(hVarA.d()))) && bVar.i() != null)) {
                                bVar.i().a(hVarA == null ? null : hVarA.d(), hVarA2.d());
                            }
                            if (hVarA2 == null || f.this.a(bVar.h(), hVarA2.a())) {
                                if (hVarA2 != null) {
                                    bVar.f(hVarA2.c());
                                }
                                try {
                                    h hVarA3 = f.this.c.a(bVar);
                                    if (hVarA3 != null) {
                                        if (TextUtils.isEmpty(hVarA3.c())) {
                                            if (hVarA2 != null) {
                                                hVarA2.a(hVarA3.a());
                                                f.this.a.a(bVar.b(), hVarA2, hVarA2);
                                            }
                                        } else if (hVarA3.d() != null) {
                                            f.this.a.a(bVar.b(), hVarA2, hVarA3);
                                            if (bVar.i() != null) {
                                                bVar.i().a(hVarA2 == null ? null : hVarA2.d(), hVarA3.d());
                                            }
                                            Iterator it = new ArrayList(f.this.d).iterator();
                                            while (it.hasNext()) {
                                                ((com.getui.gtc.dyc.b.c) it.next()).a(hVarA2 == null ? null : hVarA2.d(), hVarA3.d());
                                            }
                                        }
                                    }
                                } catch (Throwable th) {
                                    String message = th.getMessage();
                                    if (bVar.i() != null) {
                                        bVar.i().b(message);
                                        if (bVar.i() instanceof com.getui.gtc.dyc.b.d) {
                                            ((com.getui.gtc.dyc.b.d) bVar.i()).a(new Exception(th));
                                        }
                                    }
                                    for (com.getui.gtc.dyc.b.c cVar : new ArrayList(f.this.d)) {
                                        cVar.b(message);
                                        if (cVar instanceof com.getui.gtc.dyc.b.d) {
                                            ((com.getui.gtc.dyc.b.d) cVar).a(new Exception(th));
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
            if (hVarA == null) {
                return null;
            }
            return hVarA.d();
        } catch (Throwable th) {
            com.getui.gtc.dyc.a.a.a.a(th);
            return null;
        }
    }

    public Map<String, String> a(String str) {
        h hVarA = this.a.a(str);
        if (hVarA == null) {
            return null;
        }
        return hVarA.d();
    }

    public void a(com.getui.gtc.dyc.b.c cVar) {
        synchronized (this.d) {
            if (!this.d.contains(cVar)) {
                this.d.add(cVar);
            }
        }
    }

    public void a(String str, Map<String, String> map) {
        h hVarA = this.a.a(str);
        Map<String, String> mapD = hVarA.d();
        mapD.clear();
        mapD.putAll(map);
        this.a.a(str, hVarA, hVarA);
    }

    public Map<String, Map<String, String>> c() {
        HashMap<String, h> mapC = this.a.c();
        if (mapC == null || mapC.size() <= 0) {
            return null;
        }
        HashMap map = new HashMap();
        for (Map.Entry<String, h> entry : mapC.entrySet()) {
            h value = entry.getValue();
            if (value.d() != null) {
                map.put(entry.getKey(), value.d());
            }
        }
        return map;
    }

    public void c(com.getui.gtc.dyc.b.c cVar) {
        synchronized (this.d) {
            this.d.remove(cVar);
        }
    }
}

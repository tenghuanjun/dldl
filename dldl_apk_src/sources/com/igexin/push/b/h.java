package com.igexin.push.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.igexin.push.b.a;
import com.igexin.push.b.b;
import com.igexin.push.b.e.AnonymousClass1;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class h {
    private static final String e = b.a + h.class.getName();
    protected long a;
    private Handler i;
    protected final Map<String, e> b = new LinkedHashMap();
    protected final Map<String, d> c = new HashMap();
    private final Object f = new Object();
    private final Object g = new Object();
    protected a d = new a();
    private final Comparator<Map.Entry<String, d>> h = new Comparator<Map.Entry<String, d>>() { // from class: com.igexin.push.b.h.1
        private static int a(Map.Entry<String, d> entry, Map.Entry<String, d> entry2) {
            return (int) (entry.getValue().c() - entry2.getValue().c());
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(Map.Entry<String, d> entry, Map.Entry<String, d> entry2) {
            return (int) (entry.getValue().c() - entry2.getValue().c());
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:6:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public h(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.b.h.<init>(java.lang.String, java.lang.String):void");
    }

    private static d a(JSONObject jSONObject) throws Exception {
        if (!jSONObject.has("domain")) {
            return null;
        }
        d dVar = new d();
        dVar.a(jSONObject.getString("domain"));
        if (jSONObject.has("port")) {
            dVar.b = jSONObject.getInt("port");
        }
        if (jSONObject.has("ip")) {
            dVar.a = jSONObject.getString("ip");
        }
        if (jSONObject.has("consumeTime")) {
            dVar.c = jSONObject.getLong("consumeTime");
        }
        if (jSONObject.has("detectSuccessTime")) {
            dVar.d = jSONObject.getLong("detectSuccessTime");
        }
        if (jSONObject.has("isDomain")) {
            dVar.e = jSONObject.getBoolean("isDomain");
        }
        return dVar;
    }

    private static List<String> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.getJSONObject(i).getString("domain"));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private void a() {
        this.a = 0L;
        if (r()) {
            if (com.igexin.push.core.e.ap != null) {
                com.igexin.push.core.e.e.a().b("null", true);
            }
        } else if (com.igexin.push.core.e.aq != null) {
            com.igexin.push.core.e.e.a().b("null", false);
        }
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        ArrayList arrayList = new ArrayList();
        for (String str : defaultXfrList) {
            d dVar = new d(str, Integer.parseInt(com.igexin.b.a.b.g.a(str)[2]));
            if (defaultXfrList.size() > 1) {
                b(dVar);
            }
            arrayList.add(dVar);
        }
        this.d.b(arrayList);
        defaultXfrList.clear();
    }

    private static List<String> b() {
        return SDKUrlConfig.getDefaultXfrList();
    }

    private void b(d dVar) {
        e eVar = new e();
        eVar.e = c() == b.EnumC0059b.a;
        eVar.a(d());
        eVar.c = dVar;
        synchronized (this.g) {
            this.b.put(dVar.a(), eVar);
        }
    }

    private void b(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str)) {
            a();
            return;
        }
        JSONArray jSONArray = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject == null || jSONObject.length() == 0) {
            a();
            return;
        }
        if (jSONObject.has("lastDetectTime")) {
            try {
                this.a = jSONObject.getLong("lastDetectTime");
            } catch (JSONException unused2) {
            }
        }
        if (Math.abs(System.currentTimeMillis() - this.a) >= b.c) {
            a();
            return;
        }
        if (jSONObject.has("list")) {
            try {
                jSONArray = jSONObject.getJSONArray("list");
            } catch (JSONException unused3) {
            }
        }
        if (jSONArray == null || jSONArray.length() == 0) {
            a();
            return;
        }
        List<String> listA = a(jSONArray);
        if (listA.isEmpty()) {
            a();
            return;
        }
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        ArrayList arrayList = new ArrayList(defaultXfrList);
        arrayList.retainAll(listA);
        if (arrayList.size() == listA.size()) {
            com.igexin.b.a.c.a.a(e + " | db cache xfr == default, use cache", new Object[0]);
            b(jSONArray);
            return;
        }
        com.igexin.b.a.c.a.a(e + " | db cache xfr != default, use default", new Object[0]);
        arrayList.clear();
        defaultXfrList.clear();
        listA.clear();
        a();
    }

    private void b(JSONArray jSONArray) {
        d dVar;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("domain")) {
                    dVar = new d();
                    dVar.a(jSONObject.getString("domain"));
                    if (jSONObject.has("port")) {
                        dVar.b = jSONObject.getInt("port");
                    }
                    if (jSONObject.has("ip")) {
                        dVar.a = jSONObject.getString("ip");
                    }
                    if (jSONObject.has("consumeTime")) {
                        dVar.c = jSONObject.getLong("consumeTime");
                    }
                    if (jSONObject.has("detectSuccessTime")) {
                        dVar.d = jSONObject.getLong("detectSuccessTime");
                    }
                    if (jSONObject.has("isDomain")) {
                        dVar.e = jSONObject.getBoolean("isDomain");
                    }
                } else {
                    dVar = null;
                }
                if (dVar != null) {
                    this.c.put(dVar.a(), dVar);
                } else {
                    try {
                        dVar = d(jSONObject.getString("domain"));
                    } catch (Exception e2) {
                        com.igexin.b.a.c.a.a(e + "|initWithCacheData exception " + e2.toString(), new Object[0]);
                        this.c.clear();
                        a();
                        return;
                    }
                }
                if (dVar != null) {
                    b(dVar);
                    arrayList.add(dVar);
                }
            } catch (Exception e3) {
                com.igexin.b.a.c.a.a(e + "|initWithCacheData exception " + e3.toString(), new Object[0]);
                return;
            }
        }
        this.d.b(arrayList);
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
        }
        if (jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        if (jSONObject.has("loginFailedlCnt")) {
            try {
                this.d.g = jSONObject.getInt("loginFailedlCnt");
            } catch (JSONException unused2) {
            }
        }
        if (jSONObject.has("lastChange2BackupTime")) {
            try {
                this.d.h = jSONObject.getLong("lastChange2BackupTime");
            } catch (JSONException unused3) {
            }
        }
        if (jSONObject.has("lastOfflineTime")) {
            try {
                this.d.i = jSONObject.getLong("lastOfflineTime");
            } catch (JSONException unused4) {
            }
        }
        if (jSONObject.has("domainType")) {
            try {
                this.d.e = a.EnumC0058a.a(jSONObject.getInt("domainType"));
                if (this.d.e == a.EnumC0058a.BACKUP) {
                    this.d.f.set(true);
                }
            } catch (JSONException unused5) {
            }
        }
    }

    private static d d(String str) {
        d dVar = new d();
        String[] strArrA = com.igexin.b.a.b.g.a(str);
        dVar.a(str);
        dVar.b = Integer.parseInt(strArrA[2]);
        return dVar;
    }

    protected static void k() {
        com.igexin.push.core.e.e.a().b("null", true);
        com.igexin.push.core.e.e.a().b("null", false);
    }

    private void p() {
        synchronized (this.f) {
            this.c.clear();
        }
    }

    private boolean q() {
        long jAbs = Math.abs(System.currentTimeMillis() - this.a);
        if (jAbs >= (b.c * 2) - 3600) {
            long j = b.c;
            com.igexin.b.a.c.a.a(e + "|current time - last detect time > " + (b.c / 1000) + " s, detect = true", new Object[0]);
            f.a.set(true);
            return true;
        }
        if (!f.a.getAndSet(true)) {
            long jAbs2 = Math.abs(b.c - jAbs);
            f.g().a(jAbs2, TimeUnit.MILLISECONDS);
            com.igexin.b.a.c.a.a(e + "|set next detect time = " + jAbs2, new Object[0]);
        }
        return false;
    }

    private boolean r() {
        return c() == b.EnumC0059b.b;
    }

    protected final e a(String str) {
        synchronized (this.g) {
            for (Map.Entry<String, e> entry : this.b.entrySet()) {
                if (entry.getKey().equals(str)) {
                    return entry.getValue();
                }
            }
            return null;
        }
    }

    protected final void a(d dVar) {
        synchronized (this.f) {
            this.c.put(dVar.a(), dVar);
        }
        a aVar = this.d;
        synchronized (aVar.d) {
            aVar.b = 0;
            Collections.sort(aVar.c, aVar.k);
        }
    }

    public abstract int c();

    public abstract i d();

    public final void e() {
        long jAbs = Math.abs(System.currentTimeMillis() - this.a);
        boolean z = true;
        if (jAbs >= (b.c * 2) - 3600) {
            long j = b.c;
            com.igexin.b.a.c.a.a(e + "|current time - last detect time > " + (b.c / 1000) + " s, detect = true", new Object[0]);
            f.a.set(true);
        } else {
            if (!f.a.getAndSet(true)) {
                long jAbs2 = Math.abs(b.c - jAbs);
                f.g().a(jAbs2, TimeUnit.MILLISECONDS);
                com.igexin.b.a.c.a.a(e + "|set next detect time = " + jAbs2, new Object[0]);
            }
            z = false;
        }
        if (!z) {
            com.igexin.b.a.c.a.a(e + "|startDetect detect = false, return !!!", new Object[0]);
            return;
        }
        com.igexin.b.a.c.a.a(e + "|startDetect detect = true, start detect !!!", new Object[0]);
        i();
    }

    public final void f() {
        synchronized (this.g) {
            for (Map.Entry<String, e> entry : this.b.entrySet()) {
                entry.getValue().a((i) null);
                entry.getValue().a();
            }
        }
    }

    public final void g() {
        f();
        p();
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        synchronized (this.g) {
            int size = this.b.size();
            if (defaultXfrList.size() < size) {
                int size2 = size - defaultXfrList.size();
                Iterator<Map.Entry<String, e>> it = this.b.entrySet().iterator();
                for (int i = 0; it.hasNext() && i < size2; i++) {
                    it.next().getValue().b();
                    it.remove();
                }
            }
            ArrayList arrayList = new ArrayList(this.b.values());
            this.b.clear();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < defaultXfrList.size(); i2++) {
                d dVar = new d();
                String[] strArrA = com.igexin.b.a.b.g.a(defaultXfrList.get(i2));
                dVar.a(defaultXfrList.get(i2));
                dVar.b = Integer.parseInt(strArrA[2]);
                if (i2 < size) {
                    e eVar = (e) arrayList.get(i2);
                    eVar.c = dVar;
                    this.b.put(dVar.a(), eVar);
                } else {
                    b(dVar);
                }
                arrayList2.add(dVar);
            }
            this.d.b(arrayList2);
        }
    }

    public final void h() {
        f();
        p();
        List<String> defaultXfrList = SDKUrlConfig.getDefaultXfrList();
        synchronized (this.g) {
            Iterator<Map.Entry<String, e>> it = this.b.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().b();
            }
            this.b.clear();
            ArrayList arrayList = new ArrayList();
            d dVar = new d();
            String[] strArrA = com.igexin.b.a.b.g.a(defaultXfrList.get(0));
            dVar.a(defaultXfrList.get(0));
            dVar.b = Integer.parseInt(strArrA[2]);
            arrayList.add(dVar);
            this.d.b(arrayList);
            arrayList.clear();
        }
    }

    public final void i() {
        this.a = System.currentTimeMillis();
        synchronized (this.g) {
            for (Map.Entry<String, e> entry : this.b.entrySet()) {
                entry.getValue().a(d());
                if (entry.getValue().c != null) {
                    entry.getValue().c.b();
                }
                e value = entry.getValue();
                synchronized (i.class) {
                    if (value.d != null) {
                        if (e.a == null) {
                            e.a = new ThreadPoolExecutor(0, 12, 60L, TimeUnit.SECONDS, new SynchronousQueue());
                        }
                        value.b = e.a.submit(value.new AnonymousClass1());
                    }
                }
            }
        }
    }

    public final synchronized void j() {
        this.a = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        synchronized (this.g) {
            try {
                jSONObject.put("lastDetectTime", this.a);
                jSONObject.put("list", jSONArray);
                Iterator<Map.Entry<String, e>> it = this.b.entrySet().iterator();
                while (it.hasNext()) {
                    JSONObject jSONObjectF = it.next().getValue().c.f();
                    if (jSONObjectF != null) {
                        jSONArray.put(jSONObjectF);
                    }
                }
            } catch (Exception unused) {
            }
        }
        if (jSONObject.length() > 0) {
            if (r()) {
                com.igexin.push.core.e.e.a().b(jSONObject.toString(), true);
                return;
            }
            com.igexin.push.core.e.e.a().b(jSONObject.toString(), false);
        }
    }

    protected final synchronized void l() {
        a aVar = this.d;
        a.EnumC0058a enumC0058a = aVar.e;
        com.igexin.b.a.c.a.a(a.a + "|detect success, current type = " + aVar.e, new Object[0]);
        if (aVar.e == a.EnumC0058a.BACKUP) {
            aVar.a(a.EnumC0058a.TRY_NORMAL);
            com.igexin.push.core.d unused = d.a.a;
            com.igexin.push.d.a.a(true);
        }
    }

    public final void m() {
        synchronized (h.class) {
            if (this.i == null) {
                HandlerThread handlerThread = new HandlerThread("NetDetect-T");
                handlerThread.start();
                this.i = new Handler(handlerThread.getLooper());
            }
        }
        this.i.removeCallbacksAndMessages("detToken");
        this.i.postAtTime(new Runnable() { // from class: com.igexin.push.b.h.2
            @Override // java.lang.Runnable
            public final void run() {
                String unused = h.e;
                try {
                    h.this.j();
                } catch (Throwable unused2) {
                }
            }
        }, "detToken", SystemClock.uptimeMillis() + 5000);
    }

    protected final synchronized void n() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("loginFailedlCnt", this.d.g);
            jSONObject.put("lastChange2BackupTime", this.d.h);
            jSONObject.put("lastOfflineTime", this.d.i);
            jSONObject.put("domainType", this.d.e.d);
        } catch (Exception unused) {
        }
        if (jSONObject.length() > 0) {
            if (r()) {
                com.igexin.push.core.e.e.a().a(jSONObject.toString(), true);
                return;
            }
            com.igexin.push.core.e.e.a().a(jSONObject.toString(), false);
        }
    }
}

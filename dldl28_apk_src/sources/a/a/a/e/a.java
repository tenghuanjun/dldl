package a.a.a.e;

import a.a.a.d.c;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f104a = Executors.newFixedThreadPool(5);
    public final Handler b = new Handler(Looper.getMainLooper());
    public volatile boolean c;

    /* JADX INFO: compiled from: HttpManager.java */
    public enum c {
        INSTANCE;


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a f113a = new a();

        c() {
        }
    }

    public synchronized void a(String str, e eVar) {
        if (this.c) {
            return;
        }
        this.c = true;
        a.a.a.g.b.a("HttpManager", "register sdk start");
        this.f104a.execute(new RunnableC0004a(str, eVar));
    }

    public void a(String str, a.a.a.d.a aVar) {
        a.a.a.g.b.a("HttpManager", "report log start eventName:" + aVar.f99a);
        this.f104a.execute(new b(this, aVar, str));
    }

    /* JADX INFO: compiled from: HttpManager.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a.a.a.d.a f111a;
        public final /* synthetic */ String b;

        public b(a aVar, a.a.a.d.a aVar2, String str) {
            this.f111a = aVar2;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            JSONObject jSONObject = new JSONObject();
            a.a.a.b.d.a(jSONObject);
            a.a.a.d.a aVar = this.f111a;
            a.a.a.b.d.a(jSONObject, "actionType", aVar.f99a);
            double d = aVar.b;
            if (d >= 0.0d) {
                a.a.a.b.d.a(jSONObject, "purchaseAmount", d);
            }
            long j = aVar.c;
            if (j > 0) {
                a.a.a.b.d.a(jSONObject, "gameDuration", j);
            }
            if (!TextUtils.isEmpty(aVar.d)) {
                a.a.a.b.d.a(jSONObject, "gameRoleName", aVar.d);
            }
            int i = aVar.e;
            if (i > 0) {
                a.a.a.b.d.a(jSONObject, "gameGrade", i);
            }
            int i2 = aVar.f;
            if (i2 > 0) {
                a.a.a.b.d.a(jSONObject, "vipLevel", i2);
            }
            int i3 = aVar.h;
            if (i3 > 0) {
                a.a.a.b.d.a(jSONObject, "server_type", i3);
            }
            int i4 = aVar.j;
            if (i4 > 0) {
                a.a.a.b.d.a(jSONObject, "behaviorChannel", i4);
            }
            JSONObject jSONObject2 = aVar.g;
            if (!TextUtils.isEmpty("ext_params") && jSONObject2 != null) {
                try {
                    jSONObject.put("ext_params", jSONObject2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            a.a.a.b.d.a(jSONObject, "actionName", aVar.i);
            a.a.a.g.b.c("HttpManager", "report log request json:" + jSONObject.toString());
            new a.a.a.e.c().a(this.b, jSONObject.toString(), new C0007a());
        }

        /* JADX INFO: renamed from: a.a.a.e.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: HttpManager.java */
        public class C0007a implements d {
            public C0007a() {
            }

            @Override // a.a.a.e.d
            public void a(String str) {
                a.a.a.g.b.c("HttpManager", "report log success jsonResult:" + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int iOptInt = jSONObject.optInt("result");
                    String strOptString = jSONObject.optString("error_msg");
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                    if (jSONObjectOptJSONObject == null) {
                        String strOptString2 = jSONObject.optString("data");
                        if (!TextUtils.isEmpty(strOptString2)) {
                            try {
                                jSONObjectOptJSONObject = new JSONObject(strOptString2);
                            } catch (JSONException unused) {
                            }
                        }
                    }
                    c.b bVar = null;
                    if (jSONObjectOptJSONObject != null) {
                        c.b bVar2 = new c.b();
                        bVar2.f103a = jSONObjectOptJSONObject.optString("globalId");
                        bVar2.b = jSONObjectOptJSONObject.optBoolean("checkResult");
                        c.a aVar = bVar2.c;
                        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("extMap");
                        if (aVar == null) {
                            throw null;
                        }
                        if (jSONObjectOptJSONObject2 != null) {
                            aVar.f102a = jSONObjectOptJSONObject2.optBoolean("uploadOriginOaid", false);
                        }
                        bVar = bVar2;
                    }
                    if (1 != iOptInt) {
                        a.a.a.g.b.a("HttpManager", "report log fail error message :" + strOptString);
                    } else {
                        a.a.a.g.b.a("HttpManager", "report log success eventName:" + b.this.f111a.f99a);
                        a.a.a.b.d.a(bVar.c.f102a);
                    }
                } catch (JSONException e) {
                    a.a.a.g.b.c("HttpManager", "report log fail json parse error:" + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override // a.a.a.e.d
            public void a(a.a.a.d.b bVar) {
                a.a.a.g.b.a("HttpManager", "report log fail server error:" + bVar);
            }
        }
    }

    /* JADX INFO: renamed from: a.a.a.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HttpManager.java */
    public class RunnableC0004a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f105a;
        public final /* synthetic */ e b;

        public RunnableC0004a(String str, e eVar) {
            this.f105a = str;
            this.b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            JSONObject jSONObject = new JSONObject();
            a.a.a.b.d.a(jSONObject);
            a.a.a.g.b.c("HttpManager", "register sdk request json:" + jSONObject.toString());
            new a.a.a.e.c().a(this.f105a, jSONObject.toString(), new C0005a());
        }

        /* JADX INFO: renamed from: a.a.a.e.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: HttpManager.java */
        public class C0005a implements a.a.a.e.d {

            /* JADX INFO: renamed from: a.a.a.e.a$a$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: HttpManager.java */
            public class RunnableC0006a implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ a.a.a.d.c f107a;

                public RunnableC0006a(a.a.a.d.c cVar) {
                    this.f107a = cVar;
                }

                @Override // java.lang.Runnable
                public void run() {
                    e eVar = RunnableC0004a.this.b;
                    if (eVar != null) {
                        eVar.a(this.f107a);
                    }
                }
            }

            /* JADX INFO: renamed from: a.a.a.e.a$a$a$b */
            /* JADX INFO: compiled from: HttpManager.java */
            public class b implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ a.a.a.d.c f108a;

                public b(a.a.a.d.c cVar) {
                    this.f108a = cVar;
                }

                @Override // java.lang.Runnable
                public void run() {
                    e eVar = RunnableC0004a.this.b;
                    if (eVar != null) {
                        eVar.a(0, this.f108a.b);
                    }
                }
            }

            /* JADX INFO: renamed from: a.a.a.e.a$a$a$c */
            /* JADX INFO: compiled from: HttpManager.java */
            public class c implements Runnable {
                public c() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    e eVar = RunnableC0004a.this.b;
                    if (eVar != null) {
                        eVar.a(0, "json parse error");
                    }
                }
            }

            /* JADX INFO: renamed from: a.a.a.e.a$a$a$d */
            /* JADX INFO: compiled from: HttpManager.java */
            public class d implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ a.a.a.d.b f110a;

                public d(a.a.a.d.b bVar) {
                    this.f110a = bVar;
                }

                @Override // java.lang.Runnable
                public void run() {
                    e eVar = RunnableC0004a.this.b;
                    if (eVar != null) {
                        a.a.a.d.b bVar = this.f110a;
                        eVar.a(bVar.f100a, bVar.b);
                    }
                }
            }

            public C0005a() {
            }

            @Override // a.a.a.e.d
            public void a(String str) {
                a.a.a.g.b.c("HttpManager", "register sdk success jsonResult:" + str);
                try {
                    a.a.a.d.c cVar = new a.a.a.d.c();
                    cVar.a(new JSONObject(str));
                    if (1 == cVar.f101a) {
                        a.this.b.post(new RunnableC0006a(cVar));
                    } else {
                        a.this.b.post(new b(cVar));
                        a.a.a.g.b.a("HttpManager", "register sdk success fail and error message :" + cVar.b);
                    }
                } catch (JSONException e) {
                    a.a.a.g.b.c("HttpManager", "register sdk fail and json parse error:" + e.getMessage());
                    e.printStackTrace();
                    a.this.b.post(new c());
                }
                a.this.c = false;
            }

            @Override // a.a.a.e.d
            public void a(a.a.a.d.b bVar) {
                a.a.a.g.b.a("HttpManager", "register sdk fail server error:" + bVar);
                a.this.c = false;
                a.this.b.post(new d(bVar));
            }
        }
    }
}

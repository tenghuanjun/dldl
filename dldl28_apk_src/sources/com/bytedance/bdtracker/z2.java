package com.bytedance.bdtracker;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.bytedance.applog.log.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class z2 implements Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f343a;
    public final Handler b;
    public final Map<String, b> c = new HashMap();
    public final Set<String> d = new HashSet();
    public String e = "";

    public class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f344a;
        public final /* synthetic */ String b;
        public final /* synthetic */ boolean c;

        public a(z2 z2Var, Set set, String str, boolean z) {
            this.f344a = set;
            this.b = str;
            this.c = z;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                Iterator it = this.f344a.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
                jSONObject.put("$$APP_ID", this.b);
                jSONObject.put("$$EVENT_LOCAL_ID_ARRAY", jSONArray);
                jSONObject.put("$$UPLOAD_STATUS", this.c ? "success" : "failed");
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f345a;
        public String b;
        public JSONObject c;

        public b(long j, String str, JSONObject jSONObject) {
            this.f345a = j;
            this.b = str;
            this.c = jSONObject;
        }

        public String toString() {
            StringBuilder sbA = com.bytedance.bdtracker.a.a("ProfileDataWrapper{timeStamp=");
            sbA.append(this.f345a);
            sbA.append(", apiName='");
            sbA.append(this.b);
            sbA.append('\'');
            sbA.append(", jsonObject=");
            sbA.append(this.c);
            sbA.append('}');
            return sbA.toString();
        }
    }

    public z2(c0 c0Var) {
        this.f343a = c0Var;
        StringBuilder sbA = com.bytedance.bdtracker.a.a("bd_tracker_profile:");
        sbA.append(c0Var.d.m);
        HandlerThread handlerThread = new HandlerThread(sbA.toString());
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper(), this);
    }

    public final void a(b bVar) {
        if (this.f343a == null) {
            return;
        }
        StringBuilder sbA = com.bytedance.bdtracker.a.a("__profile_");
        sbA.append(bVar.b);
        u3 u3Var = new u3(sbA.toString(), bVar.c.toString());
        ArrayList arrayList = new ArrayList();
        c0 c0Var = this.f343a;
        c0Var.n.a(c0Var.d, u3Var);
        this.f343a.b(u3Var);
        arrayList.add(u3Var);
        this.f343a.c().c.a(arrayList);
        this.b.sendMessageDelayed(this.b.obtainMessage(106), 500L);
    }

    public void a(JSONObject jSONObject) {
        a(105, new b(System.currentTimeMillis(), "append", jSONObject));
    }

    public void b(JSONObject jSONObject) {
        a(103, new b(System.currentTimeMillis(), "increment", jSONObject));
    }

    public void c(JSONObject jSONObject) {
        a(100, new b(System.currentTimeMillis(), "set", jSONObject));
    }

    public void d(JSONObject jSONObject) {
        a(102, new b(System.currentTimeMillis(), "set_once", jSONObject));
    }

    public void e(JSONObject jSONObject) {
        a(104, new b(System.currentTimeMillis(), "unset", jSONObject));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 100:
                b bVar = (b) message.obj;
                this.f343a.d.D.debug(9, "Handle set:{}", bVar);
                String str = this.e;
                boolean zEquals = str != null ? str.equals(this.f343a.d.getSsid()) : false;
                this.e = this.f343a.d.getSsid();
                Iterator<String> itKeys = bVar.c.keys();
                boolean z = false;
                boolean z2 = true;
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!this.c.containsKey(next) || this.c.get(next) == null) {
                        z = true;
                    } else {
                        b bVar2 = this.c.get(next);
                        if (bVar2 != null) {
                            if (System.currentTimeMillis() - bVar2.f345a >= 60000) {
                                z = true;
                            }
                            try {
                                if (!n0.a(bVar.c, bVar2.c, (String) null)) {
                                }
                            } catch (Throwable th) {
                                this.f343a.d.D.error(9, "JSON handle failed", th, new Object[0]);
                            }
                        }
                        this.c.put(next, bVar);
                    }
                    z2 = false;
                    this.c.put(next, bVar);
                }
                if (!zEquals || z || !z2) {
                    this.f343a.d.D.debug(9, "invoke profile set.", new Object[0]);
                    a(bVar);
                }
                return true;
            case 101:
            default:
                return true;
            case 102:
                b bVar3 = (b) message.obj;
                this.f343a.d.D.debug(9, "Handle setOnce:{}", bVar3);
                String str2 = this.e;
                boolean zEquals2 = str2 != null ? str2.equals(this.f343a.d.getSsid()) : false;
                this.e = this.f343a.d.getSsid();
                Iterator<String> itKeys2 = bVar3.c.keys();
                boolean z3 = true;
                while (itKeys2.hasNext()) {
                    String next2 = itKeys2.next();
                    if (!this.d.contains(next2)) {
                        z3 = false;
                    }
                    this.d.add(next2);
                }
                if (!zEquals2 || !z3) {
                    this.f343a.d.D.debug(9, "invoke profile set once.", new Object[0]);
                    a(bVar3);
                }
                return true;
            case 103:
                b bVar4 = (b) message.obj;
                this.f343a.d.D.debug(9, "Handle increment:{}", bVar4);
                a(bVar4);
                return true;
            case 104:
                b bVar5 = (b) message.obj;
                this.f343a.d.D.debug(9, "Handle unset:{}", bVar5);
                a(bVar5);
                return true;
            case 105:
                b bVar6 = (b) message.obj;
                this.f343a.d.D.debug(9, "Handle append:{}", bVar6);
                a(bVar6);
                return true;
            case 106:
                c0 c0Var = this.f343a;
                if (c0Var != null) {
                    c0Var.d.D.debug(9, "Handle flush with dr state:{}", Integer.valueOf(c0Var.i.i()));
                    if (this.f343a.i.i() != 0) {
                        Map<String, List<u3>> mapA = this.f343a.c().a(this.f343a.d.m);
                        if (!mapA.isEmpty()) {
                            HashSet hashSet = new HashSet();
                            for (Map.Entry<String, List<u3>> entry : mapA.entrySet()) {
                                String key = entry.getKey();
                                JSONArray jSONArray = new JSONArray();
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    n0.a(jSONObject, this.f343a.d.getHeader());
                                    boolean zC = n0.c(key);
                                    Object obj = key;
                                    if (zC) {
                                        obj = JSONObject.NULL;
                                    }
                                    jSONObject.put("user_unique_id", obj);
                                    jSONObject.remove("ssid");
                                    JSONObject jSONObject2 = new JSONObject();
                                    for (u3 u3Var : entry.getValue()) {
                                        jSONArray.put(u3Var.h());
                                        if (n0.d(u3Var.i) && !jSONObject.has("ssid")) {
                                            jSONObject.put("ssid", u3Var.i);
                                        }
                                        hashSet.add(u3Var.p);
                                    }
                                    if (this.f343a.a(jSONObject)) {
                                        jSONObject2.put("event_v3", jSONArray);
                                        jSONObject2.put("magic_tag", "ss_app_log");
                                        jSONObject2.put("header", jSONObject);
                                        jSONObject2.put("time_sync", e3.d);
                                        jSONObject2.put("local_time", System.currentTimeMillis() / 1000);
                                        this.f343a.c().a(entry.getValue());
                                        if (this.f343a.d.k.a(new String[]{this.f343a.e().getProfileUri()}, jSONObject2, this.f343a.e) != 200) {
                                            this.f343a.c().c(entry.getValue());
                                            a((Set<String>) hashSet, false);
                                        } else {
                                            a((Set<String>) hashSet, true);
                                        }
                                    } else {
                                        this.f343a.d.D.warn(9, "Register to get ssid by temp header failed.", new Object[0]);
                                    }
                                } catch (Throwable th2) {
                                    this.f343a.d.D.error(9, "Flush failed", th2, new Object[0]);
                                    a((Set<String>) hashSet, false);
                                }
                            }
                        }
                    }
                }
                return true;
        }
    }

    public final void a(int i, b bVar) {
        if (this.f343a.d.x) {
            return;
        }
        Handler handler = this.b;
        handler.sendMessage(handler.obtainMessage(i, bVar));
    }

    public final void a(Set<String> set, boolean z) {
        if (set == null || set.isEmpty()) {
            return;
        }
        a1.a("event_upload_eid", (EventBus.DataFetcher) new a(this, set, this.f343a.d.m, z));
    }
}

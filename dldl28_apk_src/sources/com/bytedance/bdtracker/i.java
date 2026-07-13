package com.bytedance.bdtracker;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.bytedance.applog.IDataObserver;
import com.bytedance.applog.UriConfig;
import com.bytedance.applog.alink.IALinkListener;
import com.volcengine.androidcloud.common.pod.PodInfo;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class i implements Handler.Callback, IDataObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f260a;
    public Handler b;
    public c0 c;
    public h d;
    public int e;
    public p f;
    public int g;
    public String h;
    public final List<String> i;
    public final List<String> j;

    public i(c0 engine) {
        Intrinsics.checkParameterIsNotNull(engine, "engine");
        this.c = engine;
        this.g = 10;
        this.i = CollectionsKt.listOf((Object[]) new String[]{"utm_campaign", "utm_source", "utm_term", "utm_medium", "utm_content"});
        this.j = CollectionsKt.listOf((Object[]) new String[]{"tr_shareuser", "tr_admaster", "tr_param1", "tr_param2", "tr_param3", "tr_param4", "reengagement_window", "reengagement_time", "is_retargeting"});
        HandlerThread handlerThread = new HandlerThread("bd_tracker_alink");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper(), this);
        String spName = b.a(engine.d, "ALINK_CACHE_SP");
        Context contextB = engine.b();
        if (contextB == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
        }
        Intrinsics.checkExpressionValueIsNotNull(spName, "spName");
        this.d = new h((Application) contextB, spName);
        d dVar = engine.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar, "engine.appLog");
        this.f = new p(dVar);
    }

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
    public final void a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        j jVar = (j) this.d.a("deep_link", j.class);
        JSONObject jSONObjectA = jVar != null ? jVar.a() : null;
        if (jSONObjectA != null) {
            for (String str : this.i) {
                jSONObject2.put(str, jSONObjectA.optString(str, null));
            }
            for (String str2 : this.j) {
                if (Intrinsics.areEqual(str2, "is_retargeting")) {
                    jSONObject.put(str2, jSONObjectA.optBoolean(str2) ? 1 : 0);
                } else {
                    jSONObject.put(str2, jSONObjectA.optString(str2, null));
                }
            }
            k1 k1Var = this.c.i;
            if (k1Var != null) {
                k1Var.a("tracer_data", jSONObject);
            }
            k1 k1Var2 = this.c.i;
            if (k1Var2 != null) {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    k1Var2.a(next, (Object) jSONObject2.optString(next));
                }
            }
        }
        String strA = this.d.a("tr_web_ssid");
        if (strA == null || strA.length() == 0) {
            return;
        }
        this.c.d.setHeaderInfo("$tr_web_ssid", strA);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) throws JSONException {
        k queryParam;
        String str;
        k kVar;
        l<m> lVarA;
        m mVar;
        String str2;
        String str3;
        j jVar;
        Integer numValueOf = message != null ? Integer.valueOf(message.what) : null;
        if (numValueOf != null && numValueOf.intValue() == 1) {
            k1 k1Var = this.c.i;
            if (k1Var != null && k1Var.i() == 0) {
                int i = this.e;
                if (i >= this.g) {
                    d dVar = this.c.d;
                    Intrinsics.checkExpressionValueIsNotNull(dVar, "mEngine.appLog");
                    dVar.D.warn(3, "Retried max times to do deep link until AppLog ready", new Object[0]);
                    return true;
                }
                this.e = i + 1;
                d dVar2 = this.c.d;
                Intrinsics.checkExpressionValueIsNotNull(dVar2, "mEngine.appLog");
                dVar2.D.debug(3, "Retry do deep link delay for the {} times...", Integer.valueOf(this.e));
                Handler handler = this.b;
                if (handler == null) {
                    return true;
                }
                handler.sendMessageDelayed(handler.obtainMessage(message.what, message.obj), 500L);
                return true;
            }
            Object obj = message.obj;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.bytedance.applog.alink.model.ALinkQueryParam");
            }
            k kVar2 = (k) obj;
            String strD = kVar2.d();
            if (strD != null && strD.length() != 0) {
                kVar2.l = PodInfo.GAME_TYPE_ANDROID;
                d dVar3 = this.c.d;
                Intrinsics.checkExpressionValueIsNotNull(dVar3, "mEngine.appLog");
                kVar2.a(dVar3.m);
                d dVar4 = this.c.d;
                Intrinsics.checkExpressionValueIsNotNull(dVar4, "mEngine.appLog");
                kVar2.b(dVar4.getDid());
                d dVar5 = this.c.d;
                Intrinsics.checkExpressionValueIsNotNull(dVar5, "mEngine.appLog");
                kVar2.c(dVar5.getSsid());
                d dVar6 = this.c.d;
                Intrinsics.checkExpressionValueIsNotNull(dVar6, "mEngine.appLog");
                kVar2.d(dVar6.getUserUniqueID());
                k1 k1Var2 = this.c.i;
                kVar2.h = k1Var2 != null ? k1Var2.h() : null;
                k1 k1Var3 = this.c.i;
                kVar2.i = k1Var3 != null ? k1Var3.k() : null;
                k1 k1Var4 = this.c.i;
                if (k1Var4 != null) {
                    str2 = null;
                    str3 = (String) k1Var4.a("device_model", (Object) null, (Class<Object>) String.class);
                } else {
                    str2 = null;
                    str3 = null;
                }
                kVar2.n = str3;
                k1 k1Var5 = this.c.i;
                kVar2.m = k1Var5 != null ? (String) k1Var5.a("os_version", str2, (Class<String>) String.class) : str2;
                k1 k1Var6 = this.c.i;
                JSONObject jSONObject = k1Var6 != null ? (JSONObject) k1Var6.a("oaid", str2, (Class<String>) JSONObject.class) : null;
                kVar2.j = jSONObject != null ? jSONObject.optString("id") : null;
                k1 k1Var7 = this.c.i;
                kVar2.k = k1Var7 != null ? (String) k1Var7.a("google_aid", (Object) null, (Class<Object>) String.class) : null;
                UriConfig uriConfigE = this.c.e();
                Intrinsics.checkExpressionValueIsNotNull(uriConfigE, "mEngine.uriConfig");
                String alinkQueryUri = uriConfigE.getAlinkQueryUri();
                l<j> lVarA2 = alinkQueryUri != null ? this.f.a(alinkQueryUri, kVar2) : null;
                if (lVarA2 != null && (jVar = (j) lVarA2.a()) != null) {
                    jVar.s = strD;
                    this.d.a("deep_link", jVar, 2592000000L);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("$link_type", "direct");
                    jSONObject2.put("$deeplink_url", this.h);
                    this.c.d.receive(new q3("$invoke", jSONObject2));
                    a();
                    d dVar7 = this.c.d;
                    Intrinsics.checkExpressionValueIsNotNull(dVar7, "mEngine.appLog");
                    IALinkListener aLinkListener = dVar7.getALinkListener();
                    if (aLinkListener != null) {
                        aLinkListener.onALinkData(jVar.b(), null);
                    }
                }
            }
            return true;
        }
        if (numValueOf == null || numValueOf.intValue() != 0) {
            return true;
        }
        JSONObject jSONObjectA = this.f260a ? q.f308a.a(this.c.b()) : new JSONObject();
        d dVar8 = this.c.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar8, "mEngine.appLog");
        dVar8.D.debug(3, "Start to do defer deeplink with data:{}...", jSONObjectA);
        if (jSONObjectA == null || (queryParam = (k) o.f299a.a(jSONObjectA, k.class)) == null) {
            return true;
        }
        Object obj2 = message.obj;
        if (obj2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        }
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        Intrinsics.checkParameterIsNotNull(queryParam, "queryParam");
        d dVar9 = this.c.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar9, "mEngine.appLog");
        queryParam.a(dVar9.m);
        d dVar10 = this.c.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar10, "mEngine.appLog");
        queryParam.b(dVar10.getDid());
        d dVar11 = this.c.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar11, "mEngine.appLog");
        queryParam.c(dVar11.getSsid());
        d dVar12 = this.c.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar12, "mEngine.appLog");
        queryParam.d(dVar12.getUserUniqueID());
        String strC = queryParam.c();
        if (strC != null && strC.length() != 0) {
            this.c.d.setExternalAbVersion(queryParam.c());
        }
        String strE = queryParam.e();
        if (strE == null || strE.length() == 0) {
            str = "mEngine.appLog";
            kVar = queryParam;
        } else {
            str = "mEngine.appLog";
            kVar = queryParam;
            this.d.a("tr_web_ssid", queryParam.e(), 31536000000L);
        }
        UriConfig uriConfigE2 = this.c.e();
        Intrinsics.checkExpressionValueIsNotNull(uriConfigE2, "mEngine.uriConfig");
        String alinkAttributionUri = uriConfigE2.getAlinkAttributionUri();
        if (alinkAttributionUri != null) {
            p pVar = this.f;
            n nVar = new n();
            k1 k1Var8 = this.c.i;
            if (k1Var8 != null) {
                nVar.b = k1Var8.b();
                nVar.f = PodInfo.GAME_TYPE_ANDROID;
                nVar.e = k1Var8.f();
                nVar.l = k1Var8.h();
                nVar.m = k1Var8.k();
                JSONObject jSONObject3 = (JSONObject) k1Var8.a("oaid", (Object) null, (Class<Object>) JSONObject.class);
                nVar.d = k1Var8.c();
                nVar.n = jSONObject3 != null ? jSONObject3.optString("id") : null;
                nVar.o = (String) k1Var8.a("google_aid", (Object) null, (Class<Object>) String.class);
                nVar.q = (String) k1Var8.a("user_agent", (Object) null, (Class<Object>) String.class);
                nVar.r = (String) k1Var8.a("device_model", (Object) null, (Class<Object>) String.class);
                nVar.s = (String) k1Var8.a("os_version", (Object) null, (Class<Object>) String.class);
                nVar.h = k1Var8.p();
                nVar.i = zBooleanValue;
                nVar.j = k1Var8.o();
                nVar.k = (String) k1Var8.a("channel", (Object) null, (Class<Object>) String.class);
            }
            lVarA = pVar.a(alinkAttributionUri, nVar, kVar);
        } else {
            lVarA = null;
        }
        if (lVarA == null || (mVar = (m) lVarA.a()) == null || !mVar.G) {
            return true;
        }
        mVar.G = false;
        this.d.a("deferred_deep_link", mVar, -1L);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("$link_type", "deferred");
        this.c.d.receive(new q3("$invoke", jSONObject4));
        d dVar13 = this.c.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar13, str);
        IALinkListener aLinkListener2 = dVar13.getALinkListener();
        if (aLinkListener2 == null) {
            return true;
        }
        aLinkListener2.onAttributionData(mVar.b(), null);
        return true;
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onAbVidsChange(String str, String str2) {
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onIdLoaded(String str, String str2, String str3) {
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteAbConfigGet(boolean z, JSONObject jSONObject) {
    }

    @Override // com.bytedance.applog.IDataObserver
    public void onRemoteConfigGet(boolean z, JSONObject jSONObject) {
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0062  */
    @Override // com.bytedance.applog.IDataObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onRemoteIdGet(boolean r1, java.lang.String r2, java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7) throws org.json.JSONException {
        /*
            r0 = this;
            r0.a()
            com.bytedance.bdtracker.h r1 = r0.d
            java.lang.String r2 = "app_cache"
            java.lang.String r1 = r1.a(r2)
            r3 = 0
            if (r1 == 0) goto L17
            int r1 = r1.length()
            if (r1 != 0) goto L15
            goto L17
        L15:
            r1 = 0
            goto L18
        L17:
            r1 = 1
        L18:
            r4 = r1 ^ 1
            if (r1 == 0) goto L23
            com.bytedance.bdtracker.h r5 = r0.d
            r6 = -1
            r5.a(r2, r2, r6)
        L23:
            if (r1 != 0) goto L62
            com.bytedance.bdtracker.c0 r1 = r0.c
            com.bytedance.bdtracker.k1 r2 = r1.i
            r5 = 0
            if (r2 == 0) goto L35
            int r2 = r2.g()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L36
        L35:
            r2 = r5
        L36:
            com.bytedance.bdtracker.k1 r6 = r1.i
            if (r6 == 0) goto L43
            int r6 = r6.n()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            goto L44
        L43:
            r6 = r5
        L44:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r6)
            if (r2 == 0) goto L62
            com.bytedance.bdtracker.i1 r2 = r1.e
            if (r2 == 0) goto L53
            java.lang.String r2 = r2.d()
            goto L54
        L53:
            r2 = r5
        L54:
            com.bytedance.bdtracker.i1 r1 = r1.e
            if (r1 == 0) goto L5c
            java.lang.String r5 = r1.b()
        L5c:
            boolean r1 = android.text.TextUtils.equals(r2, r5)
            if (r1 != 0) goto L71
        L62:
            android.os.Handler r1 = r0.b
            if (r1 == 0) goto L71
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r4)
            android.os.Message r2 = r1.obtainMessage(r3, r2)
            r1.sendMessage(r2)
        L71:
            com.bytedance.bdtracker.c0 r1 = r0.c
            com.bytedance.bdtracker.d r1 = r1.d
            r1.removeDataObserver(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.i.onRemoteIdGet(boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }
}

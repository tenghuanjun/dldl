package com.bytedance.bdtracker;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.IHeaderCustomTimelyCallback;
import com.bytedance.applog.log.EventBus;
import com.bytedance.applog.log.LoggerImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class g0 extends a0 {
    public static final long[] h = {10000};
    public final p2 g;

    public class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f252a;
        public final /* synthetic */ boolean b;

        public a(Set set, boolean z) {
            this.f252a = set;
            this.b = z;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("$$APP_ID", g0.this.f.m);
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.f252a.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
                jSONObject.put("$$EVENT_LOCAL_ID_ARRAY", jSONArray);
                jSONObject.put("$$UPLOAD_STATUS", this.b ? "success" : "failed");
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    public g0(c0 c0Var) {
        super(c0Var);
        this.g = new p2("sender_", c0Var.e);
    }

    public final void a(Set<String> set, boolean z) {
        if (set == null || set.isEmpty()) {
            return;
        }
        a1.a("event_upload_eid", (EventBus.DataFetcher) new a(set, z));
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
    @Override // com.bytedance.bdtracker.a0
    public boolean c() {
        JSONObject jSONObject;
        Bundle bundleA;
        long jCurrentTimeMillis = System.currentTimeMillis();
        h0 h0Var = this.e.n;
        if (h0Var != null && (bundleA = h0Var.a(jCurrentTimeMillis, 50000L)) != null) {
            this.e.d.D.debug(4, "New play session event", new Object[0]);
            this.f.onEventV3("play_session", bundleA, 1);
            this.f.flush();
        }
        k1 k1Var = this.e.i;
        if (k1Var.i() != 0) {
            k1Var.a("access", (Object) i4.a(k1Var.b, this.e.n.c()));
            JSONObject jSONObjectE = k1Var.e();
            Cursor cursorRawQuery = null;
            if (jSONObjectE != null) {
                jSONObject = new JSONObject();
                n0.a(jSONObject, jSONObjectE);
                try {
                    String strA = x4.a(jSONObject.optJSONObject("oaid"));
                    if (!TextUtils.isEmpty(strA)) {
                        jSONObject.put("oaid", strA);
                    }
                } catch (Throwable th) {
                    LoggerImpl.global().error("transferHeaderOaid error", th, new Object[0]);
                }
            } else {
                jSONObject = null;
            }
            if (jSONObject != null) {
                IHeaderCustomTimelyCallback iHeaderCustomTimelyCallback = this.f.v;
                if (iHeaderCustomTimelyCallback != null) {
                    iHeaderCustomTimelyCallback.updateHeader(jSONObject);
                }
                this.e.d.D.debug(4, "Send events with header:{}", jSONObject);
                p3 p3VarC = this.e.c();
                String str = this.f.m;
                p3VarC.a(str, jSONObject);
                p2 p2Var = this.g;
                if (p2Var.a()) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    long j = jCurrentTimeMillis2 - p2Var.f;
                    long[][] jArr = p2.h;
                    int i = p2Var.c;
                    if (j >= jArr[i][0]) {
                        p2Var.d = 1;
                        p2Var.f = jCurrentTimeMillis2;
                    } else {
                        int i2 = p2Var.d;
                        if (i2 >= jArr[i][2]) {
                            return true;
                        }
                        p2Var.d = i2 + 1;
                    }
                }
                c0 c0Var = this.e;
                i1 i1Var = c0Var.e;
                k1 k1Var2 = c0Var.i;
                ArrayList<s3> arrayList = new ArrayList();
                try {
                    cursorRawQuery = p3VarC.f306a.getReadableDatabase().rawQuery("SELECT * FROM packV2 WHERE _app_id= ? ORDER BY _id DESC LIMIT 8", new String[]{str});
                    while (cursorRawQuery.moveToNext()) {
                        s3 s3Var = new s3();
                        s3Var.a(cursorRawQuery);
                        arrayList.add(s3Var);
                    }
                } catch (Throwable th2) {
                    try {
                        p3VarC.b.d.D.error(5, "Query event packs failed", th2, new Object[0]);
                        p3VarC.f306a.a(th2);
                    } finally {
                        n0.a(cursorRawQuery);
                    }
                }
                this.e.d.D.debug(4, "{} packs to be sent", Integer.valueOf(arrayList.size()));
                if (arrayList.size() <= 0) {
                    return true;
                }
                int i3 = 0;
                for (s3 s3Var2 : arrayList) {
                    byte[] bArr = s3Var2.z;
                    if (bArr == null || bArr.length <= 0) {
                        s3Var2.A = 0;
                        i3++;
                    } else {
                        String[] strArrA = this.f.j.a(this.e, k1Var2.e(), s3Var2.l);
                        try {
                            JSONObject jSONObject2 = new JSONObject(new String(s3Var2.z));
                            int iA = this.f.k.a(strArrA, jSONObject2, i1Var);
                            if (iA == 200) {
                                this.g.c();
                                s3Var2.A = 0;
                                i3++;
                                a(s3Var2.l(), true);
                                p3VarC.d(p3VarC.a(jSONObject2.optJSONArray("launch")));
                                p3VarC.d(p3VarC.a(jSONObject2.optJSONArray("terminate")));
                                p3VarC.d(p3VarC.a(jSONObject2.optJSONArray("event_v3")));
                            } else {
                                this.e.d.D.error(4, "Send pack failed:{}", Integer.valueOf(iA));
                                this.g.b();
                                s3Var2.A++;
                                a(s3Var2.l(), false);
                            }
                        } catch (Throwable th3) {
                            this.e.d.D.error(4, "Send pack failed", th3, new Object[0]);
                            a(s3Var2.l(), false);
                        }
                    }
                }
                p3VarC.b(arrayList);
                this.e.d.D.debug(4, "sender successfully send " + i3 + " packs (total: " + arrayList.size() + ")", new Object[0]);
                return true;
            }
            this.e.d.D.error(4, "Header is empty", new Object[0]);
        }
        return false;
    }

    @Override // com.bytedance.bdtracker.a0
    public String d() {
        return "sender";
    }

    @Override // com.bytedance.bdtracker.a0
    public long[] e() {
        return h;
    }

    @Override // com.bytedance.bdtracker.a0
    public boolean f() {
        return true;
    }

    @Override // com.bytedance.bdtracker.a0
    public long g() {
        i1 i1Var = this.e.e;
        return i1Var.a(i1Var.p) ? i1Var.p : i1Var.f.getLong("batch_event_interval", 60000L);
    }
}

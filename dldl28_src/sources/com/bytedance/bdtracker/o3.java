package com.bytedance.bdtracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.event.EventPolicy;
import com.bytedance.applog.event.IEventHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class o3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f301a;
    public final n3 b;

    public o3(c0 c0Var, n3 n3Var) {
        this.f301a = c0Var;
        this.b = n3Var;
    }

    public final EventPolicy a(IEventHandler iEventHandler, int i, String str, j3 j3Var, JSONObject jSONObject) {
        j3Var.h();
        String strE = j3Var.e();
        JSONObject jSONObject2 = new JSONObject();
        if (!TextUtils.isEmpty(strE)) {
            try {
                jSONObject2 = new JSONObject(strE);
            } catch (Throwable unused) {
                this.f301a.d.D.error(5, "Param:[{}] is not a json string", strE);
            }
        }
        if (jSONObject != null) {
            n0.b(jSONObject, jSONObject2);
        }
        EventPolicy eventPolicyOnReceive = iEventHandler.onReceive(i, str, jSONObject2);
        j3Var.o = jSONObject2;
        return eventPolicyOnReceive;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0069 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(com.bytedance.applog.event.IEventHandler r9, com.bytedance.bdtracker.j3 r10) {
        /*
            r8 = this;
            r0 = 1
            if (r9 == 0) goto L6b
            if (r10 == 0) goto L6b
            int r1 = r9.acceptType()
            boolean r2 = r10 instanceof com.bytedance.bdtracker.l3
            if (r2 == 0) goto L23
            r2 = 8
            boolean r1 = com.bytedance.applog.event.EventType.a(r1, r2)
            if (r1 == 0) goto L64
            org.json.JSONObject r7 = r10.o
            r4 = 8
            java.lang.String r5 = "bav2b_click"
            r2 = r8
            r3 = r9
            r6 = r10
            com.bytedance.applog.event.EventPolicy r9 = r2.a(r3, r4, r5, r6, r7)
            goto L65
        L23:
            boolean r2 = r10 instanceof com.bytedance.bdtracker.q3
            if (r2 == 0) goto L34
            boolean r1 = com.bytedance.applog.event.EventType.a(r1, r0)
            if (r1 == 0) goto L64
            r1 = r10
            com.bytedance.bdtracker.q3 r1 = (com.bytedance.bdtracker.q3) r1
            java.lang.String r1 = r1.u
            r4 = 1
            goto L5d
        L34:
            boolean r2 = r10 instanceof com.bytedance.bdtracker.t3
            if (r2 == 0) goto L4c
            r2 = 4
            boolean r1 = com.bytedance.applog.event.EventType.a(r1, r2)
            if (r1 == 0) goto L64
            org.json.JSONObject r7 = r10.o
            r4 = 4
            java.lang.String r5 = "bav2b_page"
        L44:
            r2 = r8
            r3 = r9
            r6 = r10
            com.bytedance.applog.event.EventPolicy r9 = r2.a(r3, r4, r5, r6, r7)
            goto L65
        L4c:
            boolean r2 = r10 instanceof com.bytedance.bdtracker.u3
            if (r2 == 0) goto L64
            r2 = 2
            boolean r1 = com.bytedance.applog.event.EventType.a(r1, r2)
            if (r1 == 0) goto L64
            r1 = r10
            com.bytedance.bdtracker.u3 r1 = (com.bytedance.bdtracker.u3) r1
            java.lang.String r1 = r1.t
            r4 = 2
        L5d:
            java.lang.String r5 = com.bytedance.bdtracker.n0.a(r1)
            org.json.JSONObject r7 = r10.o
            goto L44
        L64:
            r9 = 0
        L65:
            com.bytedance.applog.event.EventPolicy r10 = com.bytedance.applog.event.EventPolicy.DENY
            if (r9 != r10) goto L6b
            r9 = 0
            return r9
        L6b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.o3.a(com.bytedance.applog.event.IEventHandler, com.bytedance.bdtracker.j3):boolean");
    }

    public void a(List<j3> list) {
        SQLiteDatabase writableDatabase;
        InitConfig initConfig;
        if (list == null || list.isEmpty()) {
            return;
        }
        i1 i1Var = this.f301a.e;
        if (i1Var == null || (initConfig = i1Var.c) == null || initConfig.isTrackEventEnabled()) {
            ArrayList<r3> arrayList = new ArrayList(4);
            ArrayList<j3> arrayList2 = new ArrayList(4);
            IEventHandler iEventHandler = this.f301a.d.C;
            try {
                writableDatabase = this.b.getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    ContentValues contentValuesA = null;
                    for (j3 j3Var : list) {
                        if (!a(iEventHandler, j3Var)) {
                            return;
                        }
                        if (TextUtils.isEmpty(j3Var.m)) {
                            j3Var.m = this.f301a.d.m;
                        }
                        this.f301a.D.a(j3Var);
                        String strF = j3Var.f();
                        contentValuesA = j3Var.a(contentValuesA);
                        j3Var.b = writableDatabase.insert(strF, null, contentValuesA);
                        if ("eventv3".equals(j3Var.f())) {
                            arrayList2.add(j3Var);
                        } else if (j3Var instanceof r3) {
                            arrayList.add((r3) j3Var);
                        }
                        a1.a("event_save_db", j3Var);
                    }
                    writableDatabase.setTransactionSuccessful();
                    z1 z1Var = this.b.f297a.q;
                    if (!list.isEmpty() && z1Var != null) {
                        ((d2) z1Var).a(new i2(list.size()));
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        this.f301a.d.D.debug(5, "Insert to table failed", th);
                        if (list.size() > 0 && !(list.get(0) instanceof x3)) {
                            this.b.a(th);
                        }
                    } finally {
                        n0.a(writableDatabase);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                writableDatabase = null;
            }
            try {
                for (j3 j3Var2 : arrayList2) {
                    if ("eventv3".equals(j3Var2.f())) {
                        q3 q3Var = (q3) j3Var2;
                        w0 w0Var = this.f301a.d.c;
                        String str = q3Var.u;
                        String str2 = q3Var.s;
                        w0Var.onEventV3(str, str2 != null ? new JSONObject(str2) : null);
                    }
                }
            } catch (Throwable th3) {
                this.f301a.d.D.debug(5, "Notify event observer failed", th3);
            }
            try {
                for (r3 r3Var : arrayList) {
                    this.f301a.d.b.onSessionStart(r3Var.b, r3Var.e);
                }
            } catch (Throwable th4) {
                this.f301a.d.D.debug(5, "Notify session observer failed ", th4);
            }
        }
    }
}

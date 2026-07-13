package com.bytedance.dr.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import com.bytedance.bdtracker.v4;
import com.bytedance.dr.OaidApi;

/* JADX INFO: loaded from: classes2.dex */
public final class h implements OaidApi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v4<Boolean> f377a = new a(this);

    public class a extends v4<Boolean> {
        public a(h hVar) {
        }

        @Override // com.bytedance.bdtracker.v4
        public Boolean a(Object[] objArr) {
            try {
                PackageManager packageManager = ((Context) objArr[0]).getPackageManager();
                if (packageManager != null) {
                    return Boolean.valueOf(packageManager.resolveContentProvider("com.meizu.flyme.openidsdk", 0) != null);
                }
            } catch (Throwable unused) {
            }
            return false;
        }
    }

    @Override // com.bytedance.dr.OaidApi
    public String getName() {
        return "Meizu";
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    @Override // com.bytedance.dr.OaidApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.bytedance.dr.OaidApi.a getOaid(android.content.Context r8) {
        /*
            r7 = this;
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r2 = android.net.Uri.parse(r0)
            android.content.ContentResolver r1 = r8.getContentResolver()
            r8 = 0
            java.lang.String r0 = "oaid"
            java.lang.String[] r5 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L42
            r6 = 0
            r3 = 0
            r4 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L42
            if (r0 != 0) goto L1e
            com.bytedance.bdtracker.n0.a(r0)
            return r8
        L1e:
            com.bytedance.dr.OaidApi$a r1 = new com.bytedance.dr.OaidApi$a     // Catch: java.lang.Throwable -> L40
            r1.<init>()     // Catch: java.lang.Throwable -> L40
            boolean r2 = r0.isClosed()     // Catch: java.lang.Throwable -> L40
            if (r2 == 0) goto L2b
        L29:
            r2 = r8
            goto L3a
        L2b:
            r0.moveToFirst()     // Catch: java.lang.Throwable -> L40
            java.lang.String r2 = "value"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L40
            if (r2 < 0) goto L29
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Throwable -> L40
        L3a:
            r1.f366a = r2     // Catch: java.lang.Throwable -> L40
            com.bytedance.bdtracker.n0.a(r0)
            return r1
        L40:
            r1 = move-exception
            goto L44
        L42:
            r1 = move-exception
            r0 = r8
        L44:
            com.bytedance.applog.log.IAppLogLogger r2 = com.bytedance.applog.log.LoggerImpl.global()     // Catch: java.lang.Throwable -> L55
            java.lang.String r3 = "getOaid failed"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L55
            r5 = 1
            r2.error(r5, r3, r1, r4)     // Catch: java.lang.Throwable -> L55
            com.bytedance.bdtracker.n0.a(r0)
            return r8
        L55:
            r8 = move-exception
            com.bytedance.bdtracker.n0.a(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.dr.impl.h.getOaid(android.content.Context):com.bytedance.dr.OaidApi$a");
    }

    @Override // com.bytedance.dr.OaidApi
    public boolean support(Context context) {
        if (context == null) {
            return false;
        }
        return this.f377a.b(context).booleanValue();
    }
}

package com.bytedance.pangle.servermanager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class AbsServerManager extends ContentProvider {
    static final String BUNDLE_BINDER = "binder";
    static final String METHOD_QUERY_BINDER = "query_binder";
    static final String PACKAGE_QUERY_BINDER = "package";
    static final String SERVICE_QUERY_BINDER = "service";

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected android.os.IBinder onBind(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -807062458(0xffffffffcfe53446, float:-7.6908165E9)
            r2 = 1
            if (r0 == r1) goto L1a
            r1 = 1984153269(0x7643c6b5, float:9.927033E32)
            if (r0 == r1) goto L10
            goto L24
        L10:
            java.lang.String r0 = "service"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L24
            r4 = 1
            goto L25
        L1a:
            java.lang.String r0 = "package"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L24
            r4 = 0
            goto L25
        L24:
            r4 = -1
        L25:
            if (r4 == 0) goto L30
            if (r4 == r2) goto L2b
            r4 = 0
            return r4
        L2b:
            com.bytedance.pangle.service.a.a r4 = com.bytedance.pangle.service.a.a.b()
            return r4
        L30:
            com.bytedance.pangle.f.a r4 = com.bytedance.pangle.f.a.b()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.servermanager.AbsServerManager.onBind(java.lang.String):android.os.IBinder");
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        IBinder iBinderOnBind;
        if (!METHOD_QUERY_BINDER.equals(str) || (iBinderOnBind = onBind(str2)) == null) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(BUNDLE_BINDER, new a(iBinderOnBind));
        return bundle2;
    }
}

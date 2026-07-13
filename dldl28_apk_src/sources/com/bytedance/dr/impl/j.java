package com.bytedance.dr.impl;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemProperties;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.n0;
import com.bytedance.bdtracker.v4;
import com.bytedance.dr.OaidApi;
import com.bytedance.framwork.core.sdklib.DBHelper;

/* JADX INFO: loaded from: classes2.dex */
public final class j implements OaidApi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final v4<Boolean> f378a = new a();

    public static class a extends v4<Boolean> {
        @Override // com.bytedance.bdtracker.v4
        public Boolean a(Object[] objArr) {
            return Boolean.valueOf("1".equals(j.a("persist.sys.identifierid.supported", "0")));
        }
    }

    public static /* synthetic */ String a(String str, String str2) {
        try {
            return SystemProperties.get(str, str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    @Override // com.bytedance.dr.OaidApi
    public String getName() {
        return "Vivo";
    }

    @Override // com.bytedance.dr.OaidApi
    public OaidApi.a getOaid(Context context) throws Throwable {
        Cursor cursorQuery;
        OaidApi.a aVar = new OaidApi.a();
        Uri uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID");
        String string = null;
        string = null;
        string = null;
        string = null;
        Cursor cursor = null;
        if (uri != null) {
            try {
                cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        try {
                            if (cursorQuery.moveToNext()) {
                                string = cursorQuery.getString(cursorQuery.getColumnIndex(DBHelper.COL_VALUE));
                            }
                        } catch (Exception e) {
                            e = e;
                            LoggerImpl.global().error(1, "Query oaid failed", e, new Object[0]);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        n0.a(cursor);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                cursorQuery = null;
            } catch (Throwable th2) {
                th = th2;
                n0.a(cursor);
                throw th;
            }
            n0.a(cursorQuery);
        }
        aVar.f366a = string;
        return aVar;
    }

    @Override // com.bytedance.dr.OaidApi
    public boolean support(Context context) {
        return f378a.b(new Object[0]).booleanValue();
    }
}

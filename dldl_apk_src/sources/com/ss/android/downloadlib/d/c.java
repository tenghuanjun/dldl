package com.ss.android.downloadlib.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c {
    private static volatile c b;
    private SQLiteDatabase a;

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    private c() {
        try {
            this.a = new b(k.a()).getWritableDatabase();
        } catch (Throwable th) {
            com.ss.android.downloadlib.e.c.a().a(th, "ClickEventHelper");
        }
    }

    public boolean b() {
        return DownloadSetting.obtainGlobal().optInt("click_event_switch", 0) == 1;
    }

    public boolean c() {
        return DownloadSetting.obtainGlobal().optInt("click_event_switch", 0) == 2;
    }

    public void a(long j, String str) {
        SQLiteDatabase sQLiteDatabase = this.a;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String strOptString = new JSONObject(str).optString("req_id");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediationConstant.EXTRA_ADID, Long.valueOf(j));
            contentValues.put("req_id", strOptString);
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            this.a.insert("click_event", null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c(j, str);
    }

    public boolean b(long j, String str) {
        SQLiteDatabase sQLiteDatabase = this.a;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor cursorQuery = null;
        try {
            try {
                String strOptString = new JSONObject(str).optString("req_id");
                if (TextUtils.isEmpty(strOptString)) {
                    return false;
                }
                cursorQuery = this.a.query("click_event", b.a, "time > ? AND ad_id = ? AND req_id = ?", new String[]{String.valueOf(System.currentTimeMillis() - 1209600000), String.valueOf(j), strOptString}, null, null, null, null);
                boolean z = cursorQuery.getCount() > 0;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private void c(long j, String str) {
        SQLiteDatabase sQLiteDatabase = this.a;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String strOptString = new JSONObject(str).optString("req_id");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            this.a.delete("click_event", "time < ? AND ad_id = ? AND req_id = ?", new String[]{String.valueOf(System.currentTimeMillis() - 1209600000), String.valueOf(j), strOptString});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

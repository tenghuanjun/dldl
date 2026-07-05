package com.ishumei.O000O00000OoO.O000O00000oO;

import android.content.ContentValues;
import android.database.Cursor;
import com.huya.statistics.core.StatisticsContent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO {
    private com.ishumei.O000O00000OoO.O000O00000OoO.O0000O000000oO O0000O000000oO = com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000OoO.O0000O000000oO().O000O00000OoO();

    public O000O00000OoO O0000O000000oO() {
        Cursor cursorO0000O000000oO;
        O000O00000OoO o000O00000OoO = null;
        try {
            cursorO0000O000000oO = this.O0000O000000oO.O0000O000000oO("net_error_t", null, null, null, null, "1");
            try {
                if (cursorO0000O000000oO.moveToNext()) {
                    O000O00000OoO o000O00000OoO2 = new O000O00000OoO();
                    String string = cursorO0000O000000oO.getString(0);
                    String string2 = cursorO0000O000000oO.getString(1);
                    String string3 = cursorO0000O000000oO.getString(2);
                    String string4 = cursorO0000O000000oO.getString(3);
                    String string5 = cursorO0000O000000oO.getString(4);
                    o000O00000OoO2.O000O00000o0O(string);
                    o000O00000OoO2.O000O00000OoO(string2);
                    o000O00000OoO2.O000O00000oO(string3);
                    o000O00000OoO2.O000O0000O0oO(string4);
                    o000O00000OoO2.O0000O000000oO(string5);
                    this.O0000O000000oO.O0000O000000oO().delete("net_error_t", null, null);
                    o000O00000OoO = o000O00000OoO2;
                }
                if (cursorO0000O000000oO != null) {
                    try {
                        cursorO0000O000000oO.close();
                    } catch (Exception unused) {
                    }
                }
                return o000O00000OoO;
            } catch (Throwable th) {
                th = th;
                try {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(th);
                    if (cursorO0000O000000oO != null) {
                        try {
                            cursorO0000O000000oO.close();
                        } catch (Exception unused2) {
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    if (cursorO0000O000000oO != null) {
                        try {
                            cursorO0000O000000oO.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            cursorO0000O000000oO = null;
        }
    }

    public void O0000O000000oO(String str, String str2, String str3, String str4) {
        try {
            try {
                this.O0000O000000oO.O0000O000000oO().beginTransaction();
                if (str.length() > 10000) {
                    str = str.substring(0, 10000);
                }
                if (str2.length() > 1000) {
                    str2 = str.substring(0, 1000);
                }
                if (str3.length() > 100) {
                    str3 = str3.substring(0, 100);
                }
                if (str4.length() > 200) {
                    str4 = str3.substring(0, 200);
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("ex", str);
                contentValues.put(StatisticsContent.NET, str3);
                contentValues.put("url", str2);
                contentValues.put("t", "" + System.currentTimeMillis());
                contentValues.put("dns", str4);
                this.O0000O000000oO.O0000O000000oO("net_error_t", contentValues);
                this.O0000O000000oO.O0000O000000oO().delete("net_error_t", null, null);
                this.O0000O000000oO.O0000O000000oO().insert("net_error_t", null, contentValues);
                this.O0000O000000oO.O0000O000000oO().setTransactionSuccessful();
                this.O0000O000000oO.O0000O000000oO().endTransaction();
            } catch (Throwable th) {
                try {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(th);
                    this.O0000O000000oO.O0000O000000oO().endTransaction();
                } catch (Throwable th2) {
                    try {
                        this.O0000O000000oO.O0000O000000oO().endTransaction();
                    } catch (Throwable th3) {
                        com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(th3);
                    }
                    throw th2;
                }
            }
        } catch (Throwable th4) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(th4);
        }
    }
}

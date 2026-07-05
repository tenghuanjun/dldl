package com.ishumei.O000O00000oO;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000oO {
    static final Uri O0000O000000oO = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    static final Uri O000O00000OoO = CallLog.Calls.CONTENT_URI;
    private static O000O00000oO O000O00000oO;
    private Context O000O00000o0O;

    private O000O00000oO() {
        this.O000O00000o0O = null;
        try {
            this.O000O00000o0O = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
        } catch (Exception unused) {
        }
    }

    public static O000O00000oO O0000O000000oO() {
        if (O000O00000oO == null) {
            synchronized (O000O00000oO.class) {
                if (O000O00000oO == null) {
                    O000O00000oO = new O000O00000oO();
                }
            }
        }
        return O000O00000oO;
    }

    private Object O0000O000000oO(Cursor cursor, String str) {
        int columnIndex;
        if (TextUtils.isEmpty(str) || cursor == null || (columnIndex = cursor.getColumnIndex(str)) == -1 || cursor.isNull(columnIndex)) {
            return null;
        }
        int type = cursor.getType(columnIndex);
        if (type == 3) {
            return cursor.getString(columnIndex);
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(columnIndex));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(columnIndex));
        }
        return null;
    }

    public ArrayList<Object> O000O00000OoO() {
        boolean z;
        ArrayList<Object> arrayList = new ArrayList<>();
        Cursor cursorQuery = null;
        try {
            if (this.O000O00000o0O != null) {
                cursorQuery = this.O000O00000o0O.getContentResolver().query(O000O00000OoO, null, null, null, "date desc");
                if (cursorQuery == null) {
                    return arrayList;
                }
                int i = 0;
                while (cursorQuery.moveToNext()) {
                    HashMap map = new HashMap();
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("name"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("number"));
                    long j = cursorQuery.getLong(cursorQuery.getColumnIndex("duration"));
                    long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("date"));
                    int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("type"));
                    if (i2 == 1) {
                        z = false;
                        i2 = 1;
                    } else if (i2 == 2) {
                        z = false;
                        i2 = 0;
                    } else if (i2 != 3) {
                        z = true;
                    } else {
                        z = false;
                        i2 = 2;
                    }
                    if (!z) {
                        map.put("duration", Long.valueOf(j));
                        map.put("name", string);
                        map.put("tel", string2);
                        map.put("time", Long.valueOf(j2));
                        map.put("type", Integer.valueOf(i2));
                        arrayList.add(map);
                        i++;
                        if (i >= 1000) {
                            break;
                        }
                    }
                }
            }
        } catch (SecurityException unused) {
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Contact", "get calllog failed: " + e.getMessage());
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    public ArrayList<Object> O000O00000o0O() {
        ArrayList<Object> arrayList = new ArrayList<>();
        Cursor cursorQuery = null;
        try {
            if (this.O000O00000o0O != null) {
                cursorQuery = this.O000O00000o0O.getContentResolver().query(O0000O000000oO, null, null, null, null);
                if (cursorQuery == null) {
                    return arrayList;
                }
                int i = 0;
                while (cursorQuery.moveToNext()) {
                    HashMap map = new HashMap();
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("display_name"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("data1"));
                    map.put("name", string);
                    map.put("tel", string2);
                    arrayList.add(map);
                    i++;
                    if (i >= 1000) {
                        break;
                    }
                }
            }
        } catch (SecurityException unused) {
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("Contact", "get contact failed: " + e.getMessage());
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.ArrayList<java.lang.Object> O000O00000oO() {
        /*
            Method dump skipped, instruction units count: 460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O000O00000oO.O000O00000oO.O000O00000oO():java.util.ArrayList");
    }
}

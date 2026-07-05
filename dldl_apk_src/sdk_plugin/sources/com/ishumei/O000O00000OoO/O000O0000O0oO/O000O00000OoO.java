package com.ishumei.O000O00000OoO.O000O0000O0oO;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000OoO {
    private com.ishumei.O000O00000OoO.O000O00000OoO.O0000O000000oO O0000O000000oO = com.ishumei.O000O00000OoO.O000O00000OoO.O000O00000OoO.O0000O000000oO().O000O00000OoO();

    public static class O0000O000000oO {
        private int O0000O000000oO;
        private String O000O00000OoO;
        private String O000O00000o0O;

        public O0000O000000oO(int i, String str, String str2) {
            this.O0000O000000oO = i;
            this.O000O00000OoO = str;
            this.O000O00000o0O = str2;
        }

        public int O0000O000000oO() {
            return this.O0000O000000oO;
        }

        public String O000O00000OoO() {
            return this.O000O00000OoO;
        }

        public String O000O00000o0O() {
            return this.O000O00000o0O;
        }
    }

    public void O0000O000000oO(int i) {
        this.O0000O000000oO.O000O00000OoO("upload_checker", "id = ?", new String[]{"" + i});
    }

    public void O0000O000000oO(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.length() > 100000 || str2.length() > 1000) {
            return;
        }
        long jO0000O000000oO = this.O0000O000000oO.O0000O000000oO("upload_checker", null, null);
        if (jO0000O000000oO == -1 || jO0000O000000oO >= 1000) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("upload_data", str);
        contentValues.put("upload_url", str2);
        this.O0000O000000oO.O0000O000000oO("upload_checker", contentValues);
    }

    public List<O0000O000000oO> O000O00000OoO(int i) {
        Cursor cursorO0000O000000oO = this.O0000O000000oO.O0000O000000oO("upload_checker", null, null, null, null, "" + i);
        if (cursorO0000O000000oO == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (cursorO0000O000000oO.moveToNext()) {
            arrayList.add(new O0000O000000oO(cursorO0000O000000oO.getInt(0), cursorO0000O000000oO.getString(1), cursorO0000O000000oO.getString(2)));
        }
        try {
            cursorO0000O000000oO.close();
        } catch (Exception unused) {
        }
        return arrayList;
    }
}

package com.getui.gtc.e;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Base64;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.db.AbstractTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class d extends AbstractTable {
    public String a;
    public long b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public long i;
    public long j;
    public Set<String> k = new HashSet();

    public final void a(String str) {
        if (a(10, str)) {
            this.e = str;
        }
    }

    public final void a(Collection<String> collection) {
        if (collection.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.k);
        arrayList.addAll(collection);
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) arrayList.get(i));
            if (i < size - 1) {
                sb.append(com.igexin.push.core.b.aj);
            }
        }
        if (a(8, sb.toString())) {
            this.k.addAll(collection);
        }
    }

    public final boolean a(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("a", Integer.valueOf(i));
        contentValues.put("b", Long.valueOf(j));
        return replace(null, contentValues) != -1;
    }

    public final boolean a(int i, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("a", Integer.valueOf(i));
        contentValues.put("b", str);
        return replace(null, contentValues) != -1;
    }

    public final void b(String str) {
        try {
            if (a(11, Base64.encodeToString(SecureCryptTools.getInstance().encrypt(str.getBytes()), 0))) {
                this.f = str;
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    public final void c(String str) {
        try {
            if (a(12, Base64.encodeToString(SecureCryptTools.getInstance().encrypt(str.getBytes()), 0))) {
                this.g = str;
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS r (a TEXT PRIMARY KEY, b TEXT)";
    }

    public final void d(String str) {
        if (a(7, str)) {
            this.a = str;
        }
    }

    public final void e(String str) {
        if (TextUtils.isEmpty(str) || !this.k.contains(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.k);
        arrayList.remove(str);
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) arrayList.get(i));
            if (i < size - 1) {
                sb.append(com.igexin.push.core.b.aj);
            }
        }
        if (a(8, sb.toString())) {
            this.k.remove(str);
        }
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "r";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public void initCache() {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getReadableDatabase().query("r", new String[]{"a", "b"}, null, null, null, null, null);
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        int i = cursorQuery.getInt(0);
                        if (i != 4) {
                            switch (i) {
                                case 6:
                                    this.b = cursorQuery.getLong(1);
                                    continue;
                                case 7:
                                    this.a = cursorQuery.getString(1);
                                    continue;
                                case 8:
                                    String string = cursorQuery.getString(1);
                                    if (!TextUtils.isEmpty(string)) {
                                        this.k.addAll(Arrays.asList(string.split(com.igexin.push.core.b.aj)));
                                    } else {
                                        continue;
                                    }
                                    break;
                                case 9:
                                    this.d = cursorQuery.getString(1);
                                    continue;
                                case 10:
                                    this.e = cursorQuery.getString(1);
                                    continue;
                                case 11:
                                    try {
                                        String string2 = cursorQuery.getString(1);
                                        if (!TextUtils.isEmpty(string2)) {
                                            this.f = new String(SecureCryptTools.getInstance().decrypt(Base64.decode(string2, 0)));
                                        } else {
                                            continue;
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        com.getui.gtc.i.c.a.b(th);
                                    }
                                    break;
                                case 12:
                                    try {
                                        String string3 = cursorQuery.getString(1);
                                        if (!TextUtils.isEmpty(string3)) {
                                            this.g = new String(SecureCryptTools.getInstance().decrypt(Base64.decode(string3, 0)));
                                        } else {
                                            continue;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        com.getui.gtc.i.c.a.b(th);
                                    }
                                    break;
                                case 13:
                                    try {
                                        String string4 = cursorQuery.getString(1);
                                        if (!TextUtils.isEmpty(string4)) {
                                            this.h = new String(SecureCryptTools.getInstance().decrypt(Base64.decode(string4, 0)));
                                        } else {
                                            continue;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        com.getui.gtc.i.c.a.b(th);
                                    }
                                    break;
                                case 14:
                                    this.i = cursorQuery.getLong(1);
                                    continue;
                                case 15:
                                    this.j = cursorQuery.getLong(1);
                                    continue;
                                default:
                                    continue;
                            }
                            com.getui.gtc.i.c.a.b(th);
                        } else {
                            this.c = cursorQuery.getString(1);
                        }
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception e) {
                com.getui.gtc.i.c.a.a(e);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th4) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th4;
        }
    }
}

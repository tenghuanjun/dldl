package com.igexin.push.core.e;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.b.a.d.f;
import com.igexin.push.core.b.i;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class d implements a {
    private static final String b = "RALDataManager";
    private static final int c = 318;
    private static final int d = 300;
    private static volatile d e;
    public final List<i> a = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: com.igexin.push.core.e.d$3, reason: invalid class name */
    public class AnonymousClass3 extends com.igexin.push.a.d {
        final /* synthetic */ long a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(ContentValues contentValues, long j) {
            super(contentValues);
            this.a = j;
        }

        @Override // com.igexin.push.a.d
        public final void a_() throws Exception {
            this.d.update(com.igexin.push.core.b.aa, this.h, "id=?", new String[]{String.valueOf(this.a)});
        }
    }

    private d() {
    }

    private int a(byte b2) {
        Iterator<i> it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().c == b2) {
                i++;
            }
        }
        return i;
    }

    public static ContentValues a(i iVar) {
        if (iVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(iVar.a));
        contentValues.put(com.alipay.sdk.packet.e.k, com.igexin.b.b.a.b(iVar.b.getBytes()));
        contentValues.put("type", Byte.valueOf(iVar.c));
        contentValues.put("time", Long.valueOf(iVar.d));
        contentValues.put("send_times", Integer.valueOf(iVar.e));
        return contentValues;
    }

    public static d a() {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d();
                }
            }
        }
        return e;
    }

    private boolean a(long j, long j2) {
        i iVarA = a(j);
        if (iVarA == null) {
            return false;
        }
        iVarA.d = j2;
        iVarA.e++;
        com.igexin.b.a.b.e.a().a((f) new AnonymousClass3(a(iVarA), j), true, true);
        return true;
    }

    private void b(byte b2) {
        i iVar = null;
        try {
            Iterator<i> it = this.a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                i next = it.next();
                if (next.c == b2) {
                    iVar = next;
                    break;
                }
            }
            if (iVar != null) {
                a(iVar.a, true);
            }
        } catch (Throwable unused) {
        }
    }

    private List<i> c() {
        return this.a;
    }

    public final i a(long j) {
        for (i iVar : this.a) {
            if (iVar.a == j) {
                return iVar;
            }
        }
        return null;
    }

    public final void a(final long j, boolean z) {
        i iVarA = a(j);
        if (iVarA != null) {
            this.a.remove(iVarA);
        }
        com.igexin.b.a.b.e.a().a(new com.igexin.push.a.d(a(iVarA)) { // from class: com.igexin.push.core.e.d.2
            @Override // com.igexin.push.a.d
            public final void a_() throws Exception {
                this.d.delete(com.igexin.push.core.b.aa, "id=?", new String[]{String.valueOf(j)});
            }
        }, z, !z);
    }

    @Override // com.igexin.push.core.e.a
    public final void a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = sQLiteDatabase.query(com.igexin.push.core.b.aa, new String[]{"id", com.alipay.sdk.packet.e.k, "type", "time", "send_times"}, null, null, null, null, null);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    long j = cursorQuery.getLong(0);
                    byte b2 = (byte) cursorQuery.getInt(2);
                    long j2 = cursorQuery.getLong(3);
                    int i = cursorQuery.getInt(4);
                    if ((j2 == 0 || jCurrentTimeMillis - j2 <= 259200000) && i < com.igexin.push.config.d.R - 1) {
                        List<i> list = this.a;
                        i iVar = new i(j, new String(com.igexin.b.b.a.c(cursorQuery.getBlob(1))), b2, j2);
                        iVar.e = i;
                        list.add(iVar);
                    } else {
                        a(j, true);
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception unused) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    @Override // com.igexin.push.core.e.a
    public final void b() {
    }

    @Override // com.igexin.push.core.e.a
    public final void b(SQLiteDatabase sQLiteDatabase) {
    }

    public final void b(final i iVar) {
        if (this.a.size() < c || iVar.c == 2 || iVar.c == 7) {
            switch (iVar.c) {
                case 2:
                case 7:
                    b(iVar.c);
                    break;
                case 3:
                    if (a((byte) 3) >= 300) {
                        return;
                    }
                    break;
                case 5:
                    if (a((byte) 5) >= 3) {
                        return;
                    }
                    break;
                case 6:
                    if (a((byte) 6) >= 10) {
                        return;
                    }
                    break;
                case 8:
                    if (a((byte) 8) >= 3) {
                        return;
                    }
                    break;
            }
            this.a.add(iVar);
            com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d(a(iVar)) { // from class: com.igexin.push.core.e.d.1
                @Override // com.igexin.push.a.d
                public final void a_() throws Exception {
                    this.d.replace(com.igexin.push.core.b.aa, null, this.h);
                }
            }, false, true);
        }
    }
}

package com.igexin.push.a;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.b.a.b.e;
import com.igexin.b.a.d.f;
import com.igexin.push.core.d;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a extends f {
    public static int a = 0;
    public static final int b = -980948;
    public static final int g = -2147483639;
    private static final String h = "com.igexin.push.a.a";
    protected SQLiteDatabase c;
    protected Cursor d;
    List<com.igexin.push.core.e.a> e;
    boolean f;

    public a() {
        super(1);
        this.e = new LinkedList();
    }

    private void b(boolean z) {
        this.f = z;
    }

    public final void a(com.igexin.push.core.e.a aVar) {
        this.e.add(aVar);
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        this.c = d.a.a.j.getWritableDatabase();
        this.c.setVersion(5);
        Iterator<com.igexin.push.core.e.a> it = this.e.iterator();
        while (it.hasNext()) {
            it.next();
        }
        for (com.igexin.push.core.e.a aVar : this.e) {
            if (this.f) {
                aVar.b(this.c);
            } else {
                aVar.a(this.c);
            }
        }
        e.a().a(new c());
        e.a().b();
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return -2147483639;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d() {
        super.d();
        this.o = true;
        this.L = true;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d_() {
        super.d_();
        Cursor cursor = this.d;
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.igexin.b.a.d.f
    public final void e() {
    }

    @Override // com.igexin.b.a.d.f
    public final void f() {
    }
}

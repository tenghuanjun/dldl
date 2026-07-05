package com.igexin.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.igexin.a.c;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d {
    private static final String g = "lib";
    protected final Set<String> a;
    protected final c.b b;
    protected final c.a c;
    protected boolean d;
    protected boolean e;
    protected c.d f;

    /* JADX INFO: renamed from: com.igexin.a.d$1, reason: invalid class name */
    final class AnonymousClass1 implements FilenameFilter {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return str.startsWith(this.a);
        }
    }

    protected d() {
        this(new e(), new a());
    }

    private d(c.b bVar, c.a aVar) {
        this.a = new HashSet();
        this.b = bVar;
        this.c = aVar;
    }

    private static File a(Context context) {
        return context.getDir(g, 0);
    }

    private void a(Context context, String str) throws Throwable {
        a(context, str, null, null);
    }

    private void a(Context context, String str, c.InterfaceC0054c interfaceC0054c) throws Throwable {
        a(context, str, null, interfaceC0054c);
    }

    private void a(Context context, String str, String str2) throws Throwable {
        a(context, str, str2, null);
    }

    public static void a(String str, Object... objArr) {
        String.format(Locale.US, str, objArr);
    }

    private void b(Context context, String str, String str2) throws Throwable {
        if (this.a.contains(str) && !this.d) {
            a("%s already loaded previously!", str);
            return;
        }
        try {
            this.b.a(str);
            this.a.add(str);
            a("%s (%s) was loaded normally!", str, str2);
        } catch (UnsatisfiedLinkError e) {
            a("Loading the library normally failed: %s", Log.getStackTraceString(e));
            a("%s (%s) was not loaded normally, re-linking...", str, str2);
            File fileC = c(context, str, str2);
            if (!fileC.exists() || this.d) {
                if (this.d) {
                    a("Forcing a re-link of %s (%s)...", str, str2);
                }
                File dir = context.getDir(g, 0);
                File fileC2 = c(context, str, str2);
                File[] fileArrListFiles = dir.listFiles(new AnonymousClass1(this.b.c(str)));
                if (fileArrListFiles != null) {
                    for (File file : fileArrListFiles) {
                        if (this.d || !file.getAbsolutePath().equals(fileC2.getAbsolutePath())) {
                            file.delete();
                        }
                    }
                }
                this.c.a(context, this.b.a(), this.b.c(str), fileC);
            }
            try {
                if (this.e) {
                    com.igexin.a.a.f fVar = null;
                    try {
                        com.igexin.a.a.f fVar2 = new com.igexin.a.a.f(fileC);
                        try {
                            List<String> listA = fVar2.a();
                            fVar2.close();
                            Iterator<String> it = listA.iterator();
                            while (it.hasNext()) {
                                a(context, this.b.d(it.next()), null, null);
                            }
                        } catch (Throwable th) {
                            th = th;
                            fVar = fVar2;
                            fVar.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (IOException unused) {
            }
            this.b.b(fileC.getAbsolutePath());
            this.a.add(str);
            a("%s (%s) was re-linked!", str, str2);
        }
    }

    private File c(Context context, String str, String str2) {
        String strC = this.b.c(str);
        if (TextUtils.isEmpty(str2)) {
            return new File(context.getDir(g, 0), strC);
        }
        return new File(context.getDir(g, 0), strC + "." + str2);
    }

    private static void d() {
    }

    private void d(Context context, String str, String str2) {
        File dir = context.getDir(g, 0);
        File fileC = c(context, str, str2);
        File[] fileArrListFiles = dir.listFiles(new AnonymousClass1(this.b.c(str)));
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (this.d || !file.getAbsolutePath().equals(fileC.getAbsolutePath())) {
                file.delete();
            }
        }
    }

    public final d a() {
        this.f = null;
        return this;
    }

    public final void a(Context context, String str, String str2, c.InterfaceC0054c interfaceC0054c) throws Throwable {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
        a("Beginning load of %s...", str);
        if (interfaceC0054c == null) {
            b(context, str, str2);
            return;
        }
        try {
            b(context, str, str2);
            interfaceC0054c.a();
        } catch (b e) {
            interfaceC0054c.a(e);
        } catch (UnsatisfiedLinkError e2) {
            interfaceC0054c.a(e2);
        }
    }

    public final d b() {
        this.d = true;
        return this;
    }

    public final d c() {
        this.e = true;
        return this;
    }
}

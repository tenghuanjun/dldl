package com.igexin.a;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {

    public interface a {
        void a(Context context, String[] strArr, String str, File file);
    }

    public interface b {
        void a(String str);

        String[] a();

        void b(String str);

        String c(String str);

        String d(String str);
    }

    /* JADX INFO: renamed from: com.igexin.a.c$c, reason: collision with other inner class name */
    public interface InterfaceC0054c {
        void a();

        void a(Throwable th);
    }

    public interface d {
        void a();
    }

    private c() {
    }

    public static com.igexin.a.d a() {
        return new com.igexin.a.d().a();
    }

    private static void a(Context context, String str) throws Throwable {
        a(context, str, null, null);
    }

    private static void a(Context context, String str, InterfaceC0054c interfaceC0054c) throws Throwable {
        a(context, str, null, interfaceC0054c);
    }

    private static void a(Context context, String str, String str2) throws Throwable {
        a(context, str, str2, null);
    }

    private static void a(Context context, String str, String str2, InterfaceC0054c interfaceC0054c) throws Throwable {
        new com.igexin.a.d().a(context, str, str2, interfaceC0054c);
    }

    private static com.igexin.a.d b() {
        return new com.igexin.a.d().b();
    }

    private static com.igexin.a.d c() {
        return new com.igexin.a.d().c();
    }
}

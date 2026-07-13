package com.tencent.open.log;

import android.text.TextUtils;
import com.tencent.open.log.d;
import com.tencent.open.utils.m;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b {
    private static SimpleDateFormat a = d.C0199d.a("yy.MM.dd.HH");
    private File g;
    private String b = "Tracer.File";
    private int c = Integer.MAX_VALUE;
    private int d = Integer.MAX_VALUE;
    private int e = 4096;
    private long f = 10000;
    private int h = 10;
    private String i = ".log";
    private long j = Long.MAX_VALUE;

    public b(File file, int i, int i2, int i3, String str, long j, int i4, String str2, long j2) {
        a(file);
        b(i);
        a(i2);
        c(i3);
        a(str);
        b(j);
        d(i4);
        b(str2);
        c(j2);
    }

    public File a() {
        return d(System.currentTimeMillis());
    }

    private File d(long j) {
        String strC = c(a(j));
        String strB = m.b();
        if (!TextUtils.isEmpty(strB) || strB != null) {
            try {
                File file = new File(strB, c.o);
                if (!file.exists()) {
                    file.mkdirs();
                }
                return new File(file, strC);
            } catch (Exception e) {
                SLog.e(SLog.TAG, "getWorkFile,get app specific file exception:", e);
            }
        }
        return null;
    }

    public static String a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return new SimpleDateFormat("yy.MM.dd.HH").format(calendar.getTime());
    }

    private String c(String str) {
        return "com.tencent.mobileqq_connectSdk." + str + ".log";
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public int c() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public void b(long j) {
        this.f = j;
    }

    public void a(File file) {
        this.g = file;
    }

    public int d() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public void b(String str) {
        this.i = str;
    }

    public void c(long j) {
        this.j = j;
    }
}

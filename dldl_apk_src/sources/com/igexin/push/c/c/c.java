package com.igexin.push.c.c;

import com.google.zxing.common.StringUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class c extends com.igexin.b.a.d.b {
    public static final int p = 1;
    public static final int q = 2;
    public static final int r = 25;
    public static final int s = 26;
    public static final int t = 27;
    public static final int u = 16;
    public static final int v = 17;
    public static final int w = 33;
    public static final int x = 192;
    public int m;
    public byte n;
    public byte o = 11;

    protected static int a(String str) {
        if (str.equals("UTF-8")) {
            return 1;
        }
        if (str.equals("UTF-16")) {
            return 2;
        }
        if (str.equals("UTF-16BE")) {
            return 16;
        }
        if (str.equals("UTF-16LE")) {
            return 17;
        }
        if (str.equals("GBK")) {
            return 25;
        }
        if (str.equals(StringUtils.GB2312)) {
            return 26;
        }
        if (str.equals("GB18030")) {
            return 27;
        }
        return str.equals("ISO-8859-1") ? 33 : 1;
    }

    protected static String a(byte b) {
        switch (b & 63) {
            case 1:
            default:
                return "UTF-8";
            case 2:
                return "UTF-16";
            case 16:
                return "UTF-16BE";
            case 17:
                return "UTF-16LE";
            case 25:
                return "GBK";
            case 26:
                return StringUtils.GB2312;
            case 27:
                return "GB18030";
            case 33:
                return "ISO-8859-1";
        }
    }

    @Override // com.igexin.b.a.d.a.a
    public void a() {
    }

    public abstract void a(byte[] bArr);

    public abstract byte[] b();

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return this.m;
    }
}

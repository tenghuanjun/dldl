package com.alipay.deviceid.module.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class s {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public s(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public final String toString() {
        StringBuilder sb;
        String strSubstring;
        StringBuilder sb2;
        String strSubstring2;
        StringBuilder sb3;
        String strSubstring3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append(com.igexin.push.core.b.aj + this.a);
        stringBuffer.append(com.igexin.push.core.b.aj + this.b);
        stringBuffer.append(com.igexin.push.core.b.aj + this.c);
        stringBuffer.append(com.igexin.push.core.b.aj + this.d);
        if (e.a(this.e) || this.e.length() < 20) {
            sb = new StringBuilder(com.igexin.push.core.b.aj);
            strSubstring = this.e;
        } else {
            sb = new StringBuilder(com.igexin.push.core.b.aj);
            strSubstring = this.e.substring(0, 20);
        }
        sb.append(strSubstring);
        stringBuffer.append(sb.toString());
        if (e.a(this.f) || this.f.length() < 20) {
            sb2 = new StringBuilder(com.igexin.push.core.b.aj);
            strSubstring2 = this.f;
        } else {
            sb2 = new StringBuilder(com.igexin.push.core.b.aj);
            strSubstring2 = this.f.substring(0, 20);
        }
        sb2.append(strSubstring2);
        stringBuffer.append(sb2.toString());
        if (e.a(this.g) || this.g.length() < 20) {
            sb3 = new StringBuilder(com.igexin.push.core.b.aj);
            strSubstring3 = this.g;
        } else {
            sb3 = new StringBuilder(com.igexin.push.core.b.aj);
            strSubstring3 = this.g.substring(0, 20);
        }
        sb3.append(strSubstring3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }
}

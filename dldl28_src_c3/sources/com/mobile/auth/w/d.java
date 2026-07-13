package com.mobile.auth.w;

import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class d {
    private static volatile d a;
    private int b = 0;
    private int c = 3000;
    private int d = 3000;
    private c e;

    public static d a() {
        try {
            if (a == null) {
                synchronized (d.class) {
                    if (a == null) {
                        a = new d();
                    }
                }
            }
            return a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public d a(c cVar) {
        try {
            this.e = cVar;
            return a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public e b() {
        e eVar;
        c cVar;
        try {
            eVar = new e();
            cVar = this.e;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        if (cVar == null) {
            eVar.a("100008");
            eVar.b("请求参数为空");
            eVar.a(false);
            return eVar;
        }
        if (TextUtils.isEmpty(cVar.getBaseUrl())) {
            eVar.a("100004");
            eVar.b("url 为空");
            eVar.a(false);
            return eVar;
        }
        try {
            if (TextUtils.isEmpty(new URL(this.e.getBaseUrl()).getHost())) {
                eVar.a("100001");
                eVar.b("host 为空");
                eVar.a(false);
                return eVar;
            }
            if (TextUtils.isEmpty(this.e.getMethod()) && TextUtils.isEmpty(this.e.getAction())) {
                eVar.a("100002");
                eVar.b("api 为空");
                eVar.a(false);
                return eVar;
            }
            if (this.e.isSign() && TextUtils.isEmpty(this.e.getAccessKeySecret())) {
                eVar.a("100003");
                eVar.b("未设置secretkey");
                eVar.a(false);
                return eVar;
            }
            try {
                this.e.setRequestMethod(Constants.HTTP_POST);
                this.b = 0;
                String strA = this.e.getBaseUrl().startsWith("https://") ? a.a(this.e, this.c, this.d) : a.a(this.e, this.c, this.d, this.b);
                if (TextUtils.isEmpty(strA) || "{}".equals(strA)) {
                    eVar.a("100007");
                    eVar.b("数据返回错误");
                    eVar.a(false);
                } else {
                    eVar.a("100000");
                    eVar.b("请求成功");
                    eVar.a(true);
                    eVar.c(strA);
                }
                return eVar;
            } catch (IOException e) {
                e.printStackTrace();
                eVar.a("100006");
                eVar.b(e.getLocalizedMessage());
                eVar.a(false);
                return eVar;
            }
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            eVar.a("100001");
            eVar.b("host 为空");
            eVar.a(false);
            return eVar;
        }
        ExceptionProcessor.processException(th);
        return null;
    }
}

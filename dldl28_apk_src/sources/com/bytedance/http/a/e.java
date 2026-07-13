package com.bytedance.http.a;

import com.bytedance.http.HttpDispatcher;
import com.bytedance.http.HttpMethod;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements Interceptor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f385a;

    public e(HttpDispatcher httpDispatcher) {
        this.f385a = httpDispatcher.logger();
    }

    private static void a(HttpResponse httpResponse) {
        com.bytedance.http.b.c.a("== QUICK-HTTP RESPONSE ==");
        com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "%d %s", Integer.valueOf(httpResponse.code()), httpResponse.message()));
        com.bytedance.http.b.c.a("\r\n");
        for (String str : httpResponse.headers().names()) {
            String str2 = httpResponse.headers().get(str);
            if (com.bytedance.http.b.g.c(str2)) {
                com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "%s : %s", str, str2));
            }
        }
        com.bytedance.http.b.c.a("\r\n");
        com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "%s", httpResponse.body()));
        com.bytedance.http.b.c.a("\r\n");
        for (String str3 : httpResponse.extras().keySet()) {
            String str4 = (String) httpResponse.extras().get(str3);
            if (com.bytedance.http.b.g.c(str4)) {
                com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "%s : %s", str3, str4));
            }
        }
        com.bytedance.http.b.c.a("== END ==");
    }

    @Override // com.bytedance.http.Interceptor
    public final HttpResponse intercept(Interceptor.Chain chain) {
        HttpRequest httpRequestRequest = chain.request();
        if (this.f385a) {
            com.bytedance.http.b.c.a("== QUICK-HTTP REQUEST ==");
            com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "url: %s", httpRequestRequest.url().toString()));
            com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "method: %s", httpRequestRequest.method().toString()));
            Iterator it = httpRequestRequest.hosts().iterator();
            while (it.hasNext()) {
                com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "host: %s", (String) it.next()));
            }
            Iterator it2 = httpRequestRequest.pathSegments().iterator();
            while (it2.hasNext()) {
                com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "path segments: %s", (String) it2.next()));
            }
            for (String str : httpRequestRequest.cookies().keySet()) {
                String str2 = (String) httpRequestRequest.cookies().get(str);
                if (com.bytedance.http.b.g.c(str2)) {
                    com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "cookie: %s - %s", str, str2));
                }
            }
            for (String str3 : httpRequestRequest.headers().names()) {
                String str4 = httpRequestRequest.headers().get(str3);
                if (com.bytedance.http.b.g.c(str4)) {
                    com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "header: %s - %s", str3, str4));
                }
            }
            if (httpRequestRequest.method() == HttpMethod.POST && httpRequestRequest.body() != null) {
                com.bytedance.http.b.c.a(String.format(Locale.getDefault(), "body: %s", new String(httpRequestRequest.body())));
            }
            com.bytedance.http.b.c.a("== END ==");
        }
        HttpResponse httpResponseProceed = chain.proceed(httpRequestRequest);
        if (this.f385a) {
            a(httpResponseProceed);
        }
        return httpResponseProceed;
    }

    @Override // com.bytedance.http.Interceptor
    public final String name() {
        return "logger_int";
    }
}

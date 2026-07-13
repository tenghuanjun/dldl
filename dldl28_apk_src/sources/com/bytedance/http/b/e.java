package com.bytedance.http.b;

import com.bytedance.dns.DnsResolver;
import com.bytedance.http.Call;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements Interceptor.Chain {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f392a;
    private final int b;
    private final HttpRequest c;
    private final Call d;
    private final f e;

    public e(List list, int i, HttpRequest httpRequest, Call call, f fVar) {
        this.f392a = list;
        this.b = i;
        this.c = httpRequest;
        this.d = call;
        this.e = fVar;
    }

    private HttpResponse a(HttpRequest httpRequest, f fVar, List list, int i) {
        if (i >= list.size()) {
            throw new AssertionError();
        }
        e eVar = new e(list, i + 1, httpRequest, this.d, fVar);
        Interceptor interceptor = (Interceptor) list.get(i);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.e.e()) {
            c.a(interceptor.name() + ".intercept() - start");
        }
        HttpResponse httpResponseIntercept = interceptor.intercept(eVar);
        if (fVar.e()) {
            httpResponseIntercept.extras().put(interceptor.name(), Long.toString(System.currentTimeMillis() - jCurrentTimeMillis));
            c.a(interceptor.name() + ".intercept() - end");
        }
        return httpResponseIntercept;
    }

    public final HttpResponse a(HttpRequest httpRequest, f fVar) {
        return a(httpRequest, fVar, this.f392a, this.b);
    }

    public final f a() {
        return this.e;
    }

    public final DnsResolver b() {
        return this.e.b();
    }

    @Override // com.bytedance.http.Interceptor.Chain
    public final Call call() {
        return this.d;
    }

    @Override // com.bytedance.http.Interceptor.Chain
    public final HttpResponse proceed(HttpRequest httpRequest) {
        return a(httpRequest, this.e);
    }

    @Override // com.bytedance.http.Interceptor.Chain
    public final HttpRequest request() {
        return this.c;
    }
}

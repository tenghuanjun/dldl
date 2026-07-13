package com.bytedance.http.b;

import com.bytedance.http.HttpMethod;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HttpURLConnection f389a;
    private volatile boolean b;

    public b(URL url) {
        this(url, null, null);
    }

    public b(URL url, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier) {
        this.b = false;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        this.f389a = httpURLConnection;
        if (httpURLConnection instanceof HttpsURLConnection) {
            if (sSLSocketFactory != null) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLSocketFactory);
            }
            if (hostnameVerifier != null) {
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(hostnameVerifier);
            }
        }
    }

    private void j() {
        if (this.b) {
            throw new CancellationException("Canceled");
        }
    }

    @Override // com.bytedance.http.b.a
    public final Map a() {
        j();
        HashMap map = new HashMap();
        for (Map.Entry<String, List<String>> entry : this.f389a.getHeaderFields().entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                StringBuilder sb = new StringBuilder();
                Iterator<String> it = entry.getValue().iterator();
                if (it.hasNext()) {
                    while (true) {
                        sb.append(it.next());
                        if (!it.hasNext()) {
                            break;
                        }
                        sb.append(";");
                    }
                }
                map.put(key, sb.toString());
            }
        }
        return map;
    }

    @Override // com.bytedance.http.b.a
    public final void a(int i) {
        j();
        this.f389a.setConnectTimeout(i);
    }

    @Override // com.bytedance.http.b.a
    public final void a(HttpMethod httpMethod) throws ProtocolException {
        j();
        this.f389a.setRequestMethod(httpMethod.getValue());
    }

    @Override // com.bytedance.http.b.a
    public final void a(String str, String str2) {
        j();
        this.f389a.setRequestProperty(str, str2);
    }

    @Override // com.bytedance.http.b.a
    public final void a(boolean z) {
        j();
        this.f389a.setDoInput(true);
    }

    @Override // com.bytedance.http.b.a
    public final void b() {
        j();
        this.f389a.connect();
    }

    @Override // com.bytedance.http.b.a
    public final void b(int i) {
        j();
        this.f389a.setReadTimeout(i);
    }

    @Override // com.bytedance.http.b.a
    public final void b(boolean z) {
        j();
        this.f389a.setDoOutput(true);
    }

    @Override // com.bytedance.http.b.a
    public final void c() {
        this.b = true;
        this.f389a.disconnect();
    }

    @Override // com.bytedance.http.b.a
    public final void c(boolean z) {
        j();
        this.f389a.setUseCaches(true);
    }

    @Override // com.bytedance.http.b.a
    public final InputStream e() {
        j();
        return this.f389a.getInputStream();
    }

    @Override // com.bytedance.http.b.a
    public final InputStream f() {
        j();
        return this.f389a.getErrorStream();
    }

    @Override // com.bytedance.http.b.a
    public final OutputStream g() {
        j();
        return this.f389a.getOutputStream();
    }

    public final int h() {
        j();
        return this.f389a.getResponseCode();
    }

    public final String i() {
        j();
        return this.f389a.getResponseMessage();
    }
}

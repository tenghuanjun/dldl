package com.bytedance.http.a;

import com.bytedance.http.HttpExtra;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;
import com.lzy.okgo.model.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements Interceptor {
    @Override // com.bytedance.http.Interceptor
    public final HttpResponse intercept(Interceptor.Chain chain) throws IOException {
        String string;
        com.bytedance.http.b.f fVarA = ((com.bytedance.http.b.e) chain).a();
        HttpRequest httpRequestBuild = chain.request().newBuilder().build();
        com.bytedance.http.b.b bVar = (com.bytedance.http.b.b) fVarA.a();
        com.bytedance.http.b.c.a("Prepare to read response");
        int iH = bVar.h();
        String strI = bVar.i();
        com.bytedance.http.b.c.a(iH + "\t" + strI);
        Map mapA = bVar.a();
        for (String str : mapA.keySet()) {
            com.bytedance.http.b.c.a(str + ":\t" + ((String) mapA.get(str)));
        }
        InputStream inputStreamE = iH == 200 ? bVar.e() : bVar.f();
        if (inputStreamE != null) {
            if ("gzip".equalsIgnoreCase((String) mapA.get(HttpHeaders.HEAD_KEY_CONTENT_ENCODING))) {
                inputStreamE = new GZIPInputStream(inputStreamE);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStreamE.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            string = byteArrayOutputStream.toString("UTF-8");
            com.bytedance.http.b.g.a(byteArrayOutputStream);
            com.bytedance.http.b.g.a(inputStreamE);
        } else {
            string = "";
        }
        com.bytedance.http.b.c.a(string);
        HttpResponse.Builder builder = new HttpResponse.Builder();
        builder.code(iH).message(strI).body(string).request(httpRequestBuild);
        for (String str2 : mapA.keySet()) {
            builder.addHeader(str2, (String) mapA.get(str2));
        }
        builder.addExtra(HttpExtra.HTTP_EXTRA_RECEIVED_RESPONSE_AT_MILLIS, Long.toString(System.currentTimeMillis()));
        builder.addExtra("http_response_code", Integer.toString(iH));
        return builder.build();
    }

    @Override // com.bytedance.http.Interceptor
    public final String name() {
        return "cs_int";
    }
}

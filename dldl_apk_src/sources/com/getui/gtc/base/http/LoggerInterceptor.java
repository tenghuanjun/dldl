package com.getui.gtc.base.http;

import com.getui.gtc.base.http.Interceptor;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.Response;
import com.getui.gtc.base.log.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class LoggerInterceptor implements Interceptor {
    public static final int BASIC = 1;
    public static final int BODY = 4;
    public static final int HEADER = 2;
    private int flags;
    private Logger logger;

    public LoggerInterceptor(Logger logger) {
        this(logger, 5);
    }

    public LoggerInterceptor(Logger logger, int i) {
        this.logger = logger;
        this.flags = i;
    }

    @Override // com.getui.gtc.base.http.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Map<String, List<String>> mapHeaders;
        Map<String, String> mapHeaders2;
        Request request = chain.request();
        Request.Builder builderNewBuilder = request.newBuilder();
        StringBuilder sb = new StringBuilder();
        if ((this.flags & 1) == 1) {
            sb.append(request.method() + " " + request.url().toString() + " " + request.tag() + "\n");
        }
        if ((this.flags & 2) == 2 && (mapHeaders2 = request.headers()) != null && mapHeaders2.size() > 0) {
            for (Map.Entry<String, String> entry : mapHeaders2.entrySet()) {
                sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
        if ((this.flags & 4) == 4 && request.body() != null && request.body().contentType().charset() != null) {
            if (request.body().contentLength() > 2147483647L) {
                sb.append("request body content length: " + request.body().contentLength() + "\n");
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                request.body().writeTo(byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                sb.append(new String(byteArray, request.body().contentType().charset()) + "\n");
                builderNewBuilder.body(RequestBody.create(request.body().contentType(), byteArray));
            }
        }
        this.logger.d(sb.toString());
        Response responseProceed = chain.proceed(builderNewBuilder.build());
        Response.Builder builderNewBuilder2 = responseProceed.newBuilder();
        StringBuilder sb2 = new StringBuilder();
        if ((this.flags & 1) == 1) {
            sb2.append(responseProceed.code() + " " + responseProceed.message() + " " + request.url().toString() + " " + request.tag() + "\n");
        }
        if ((this.flags & 2) == 2 && (mapHeaders = responseProceed.headers()) != null && mapHeaders.size() > 0) {
            for (Map.Entry<String, List<String>> entry2 : mapHeaders.entrySet()) {
                if (entry2.getValue() != null && entry2.getValue().size() > 0) {
                    StringBuilder sb3 = new StringBuilder();
                    for (int i = 0; i < entry2.getValue().size(); i++) {
                        sb3.append(entry2.getValue().get(i));
                        if (i < entry2.getValue().size() - 1) {
                            sb3.append("; ");
                        }
                    }
                    sb2.append(entry2.getKey() + ": " + sb3.toString() + "\n");
                }
            }
        }
        if ((this.flags & 4) == 4 && responseProceed.body() != null && responseProceed.body().contentType().charset() != null) {
            if (responseProceed.body().contentLength() > 2147483647L) {
                sb2.append("response body content length: " + responseProceed.body().contentLength() + "\n");
            } else {
                byte[] bArrBytes = responseProceed.body().bytes();
                sb2.append(new String(bArrBytes, responseProceed.body().charset()) + "\n");
                builderNewBuilder2.body(ResponseBody.create(responseProceed.body().contentType(), bArrBytes));
            }
        }
        this.logger.d(sb2.toString());
        return builderNewBuilder2.build();
    }
}

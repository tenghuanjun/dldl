package com.lzy.okgo.interceptor;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.utils.IOUtils;
import com.lzy.okgo.utils.OkLogger;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName(Constants.ENC_UTF_8);
    private java.util.logging.Level colorLevel;
    private Logger logger;
    private volatile Level printLevel = Level.NONE;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public HttpLoggingInterceptor(String str) {
        this.logger = Logger.getLogger(str);
    }

    public void setPrintLevel(Level level) {
        if (this.printLevel == null) {
            throw new NullPointerException("printLevel == null. Use Level.NONE instead.");
        }
        this.printLevel = level;
    }

    public void setColorLevel(java.util.logging.Level level) {
        this.colorLevel = level;
    }

    private void log(String str) {
        this.logger.log(this.colorLevel, str);
    }

    public Response intercept(Interceptor.Chain chain) throws Exception {
        Request request = chain.request();
        if (this.printLevel == Level.NONE) {
            return chain.proceed(request);
        }
        logForRequest(request, chain.connection());
        try {
            return logForResponse(chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - System.nanoTime()));
        } catch (Exception e) {
            log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    private void logForRequest(Request request, Connection connection) throws IOException {
        StringBuilder sb;
        boolean z = this.printLevel == Level.BODY;
        boolean z2 = this.printLevel == Level.BODY || this.printLevel == Level.HEADERS;
        RequestBody requestBodyBody = request.body();
        boolean z3 = requestBodyBody != null;
        try {
            try {
                log("--> " + request.method() + ' ' + request.url() + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1));
                if (z2) {
                    if (z3) {
                        if (requestBodyBody.contentType() != null) {
                            log("\tContent-Type: " + requestBodyBody.contentType());
                        }
                        if (requestBodyBody.contentLength() != -1) {
                            log("\tContent-Length: " + requestBodyBody.contentLength());
                        }
                    }
                    Headers headers = request.headers();
                    int size = headers.size();
                    for (int i = 0; i < size; i++) {
                        String strName = headers.name(i);
                        if (!HttpHeaders.HEAD_KEY_CONTENT_TYPE.equalsIgnoreCase(strName) && !HttpHeaders.HEAD_KEY_CONTENT_LENGTH.equalsIgnoreCase(strName)) {
                            log("\t" + strName + ": " + headers.value(i));
                        }
                    }
                    log(" ");
                    if (z && z3) {
                        if (isPlaintext(requestBodyBody.contentType())) {
                            bodyToString(request);
                        } else {
                            log("\tbody: maybe [binary body], omitted!");
                        }
                    }
                }
                sb = new StringBuilder("--> END ");
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
                sb = new StringBuilder("--> END ");
            }
            sb.append(request.method());
            log(sb.toString());
        } catch (Throwable th) {
            log("--> END " + request.method());
            throw th;
        }
    }

    private Response logForResponse(Response response, long j) {
        Response responseBuild = response.newBuilder().build();
        ResponseBody responseBodyBody = responseBuild.body();
        boolean z = true;
        boolean z2 = this.printLevel == Level.BODY;
        if (this.printLevel != Level.BODY && this.printLevel != Level.HEADERS) {
            z = false;
        }
        try {
            try {
                log("<-- " + responseBuild.code() + ' ' + responseBuild.message() + ' ' + responseBuild.request().url() + " (" + j + "ms）");
                if (z) {
                    Headers headers = responseBuild.headers();
                    int size = headers.size();
                    for (int i = 0; i < size; i++) {
                        log("\t" + headers.name(i) + ": " + headers.value(i));
                    }
                    log(" ");
                    if (z2 && okhttp3.internal.http.HttpHeaders.hasBody(responseBuild)) {
                        if (responseBodyBody == null) {
                            return response;
                        }
                        if (isPlaintext(responseBodyBody.contentType())) {
                            byte[] byteArray = IOUtils.toByteArray(responseBodyBody.byteStream());
                            log("\tbody:" + new String(byteArray, getCharset(responseBodyBody.contentType())));
                            return response.newBuilder().body(ResponseBody.create(responseBodyBody.contentType(), byteArray)).build();
                        }
                        log("\tbody: maybe [binary body], omitted!");
                    }
                }
            } catch (Exception e) {
                OkLogger.printStackTrace(e);
            }
            return response;
        } finally {
            log("<-- END HTTP");
        }
    }

    private static Charset getCharset(MediaType mediaType) {
        Charset charset = mediaType != null ? mediaType.charset(UTF8) : UTF8;
        return charset == null ? UTF8 : charset;
    }

    private static boolean isPlaintext(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        String strSubtype = mediaType.subtype();
        if (strSubtype != null) {
            String lowerCase = strSubtype.toLowerCase();
            if (lowerCase.contains("x-www-form-urlencoded") || lowerCase.contains("json") || lowerCase.contains("xml") || lowerCase.contains("html")) {
                return true;
            }
        }
        return false;
    }

    private void bodyToString(Request request) {
        try {
            RequestBody requestBodyBody = request.newBuilder().build().body();
            if (requestBodyBody == null) {
                return;
            }
            Buffer buffer = new Buffer();
            requestBodyBody.writeTo(buffer);
            log("\tbody:" + buffer.readString(getCharset(requestBodyBody.contentType())));
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        }
    }
}

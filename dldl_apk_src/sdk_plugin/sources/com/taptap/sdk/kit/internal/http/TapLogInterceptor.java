package com.taptap.sdk.kit.internal.http;

import android.util.Log;
import com.huya.mtp.hyns.NSFunction;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.common.track.SqTrackNetDnsKey;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.okhttp3.Headers;
import com.taptap.sdk.okhttp3.Interceptor;
import com.taptap.sdk.okhttp3.MediaType;
import com.taptap.sdk.okhttp3.Request;
import com.taptap.sdk.okhttp3.RequestBody;
import com.taptap.sdk.okhttp3.Response;
import com.taptap.sdk.okhttp3.ResponseBody;
import com.taptap.sdk.okhttp3.internal.http.HttpHeaders;
import com.taptap.sdk.okio.Buffer;
import com.taptap.sdk.okio.BufferedSource;
import com.taptap.sdk.okio.GzipSource;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TapLogInterceptor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0003 !\"B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001c\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0019j\u0002`\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapLogInterceptor;", "Lcom/taptap/sdk/okhttp3/Interceptor;", "enableLog", "", "level", "Lcom/taptap/sdk/kit/internal/http/TapLogInterceptor$Level;", "(ZLcom/taptap/sdk/kit/internal/http/TapLogInterceptor$Level;)V", "bodyHasUnknownEncoding", "headers", "Lcom/taptap/sdk/okhttp3/Headers;", "intercept", "Lcom/taptap/sdk/okhttp3/Response;", "chain", "Lcom/taptap/sdk/okhttp3/Interceptor$Chain;", "isPlaintext", "buffer", "Lcom/taptap/sdk/okio/Buffer;", "log", "", "message", "", "logException", SqTrackNetDnsKey.request, "Lcom/taptap/sdk/okhttp3/Request;", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "logRequest", "logResponse", "startTime", "Lcom/taptap/sdk/kit/internal/http/TapLogInterceptor$LogTime;", "response", "Companion", "Level", "LogTime", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapLogInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final boolean enableLog;
    private final Level level;

    /* JADX INFO: compiled from: TapLogInterceptor.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapLogInterceptor$Level;", "", "(Ljava/lang/String;I)V", "BASIC", "HEADERS", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public enum Level {
        BASIC,
        HEADERS
    }

    public TapLogInterceptor(boolean z, Level level) {
        Intrinsics.checkNotNullParameter(level, "level");
        this.enableLog = z;
        this.level = level;
    }

    /* JADX INFO: compiled from: TapLogInterceptor.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapLogInterceptor$LogTime;", "", "()V", "startNs", "", "tookMs", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class LogTime {
        private final long startNs = System.nanoTime();

        public final long tookMs() {
            return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.startNs);
        }
    }

    @Override // com.taptap.sdk.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Object objM52constructorimpl;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(request, "request");
            logRequest(request);
            Result.m52constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m52constructorimpl(ResultKt.createFailure(th));
        }
        LogTime logTime = new LogTime();
        try {
            Response responseProceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(responseProceed, "chain.proceed(request)");
            try {
                Result.Companion companion3 = Result.INSTANCE;
                logResponse(logTime, responseProceed);
                objM52constructorimpl = Result.m52constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM52constructorimpl = Result.m52constructorimpl(ResultKt.createFailure(th2));
            }
            Throwable thM55exceptionOrNullimpl = Result.m55exceptionOrNullimpl(objM52constructorimpl);
            if (thM55exceptionOrNullimpl != null) {
                TapLogger.logd("TapHttp", "logResponse error: " + thM55exceptionOrNullimpl, thM55exceptionOrNullimpl);
            }
            return responseProceed;
        } catch (Exception e) {
            Intrinsics.checkNotNullExpressionValue(request, "request");
            logException(request, e);
            throw e;
        }
    }

    private final void logException(Request request, Exception e) {
        StringBuilder sb = new StringBuilder("<------ HTTP FAILED: ");
        sb.append(request.method() + ' ');
        sb.append(request.url());
        StringBuilder sb2 = new StringBuilder();
        sb2.append('\n');
        sb2.append(e);
        sb.append(sb2.toString());
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        log(string);
    }

    private final void logRequest(Request request) throws IOException {
        StringBuilder sb = new StringBuilder("------> ");
        sb.append(request.method() + ' ');
        sb.append(request.url());
        RequestBody requestBodyBody = request.body();
        boolean z = requestBodyBody != null;
        if (z) {
            sb.append(" (");
            sb.append(requestBodyBody != null ? Long.valueOf(requestBodyBody.contentLength()) : null);
            sb.append("-byte body)");
        }
        if (this.level == Level.BASIC) {
            if (z) {
                if ((requestBodyBody != null ? requestBodyBody.contentType() : null) != null) {
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                    sb.append("Content-Type: ");
                    sb.append(requestBodyBody.contentType());
                }
                if (!(requestBodyBody != null && requestBodyBody.contentLength() == -1)) {
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                    sb.append("Content-Length: ");
                    sb.append(requestBodyBody != null ? Long.valueOf(requestBodyBody.contentLength()) : null);
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String strName = headers.name(i);
                if (!StringsKt.equals("Content-Type", strName, true) && !StringsKt.equals("Content-Length", strName, true)) {
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                    sb.append(strName);
                    sb.append(": ");
                    sb.append(headers.value(i));
                }
            }
            if (z) {
                Intrinsics.checkNotNullExpressionValue(headers, "headers");
                if (bodyHasUnknownEncoding(headers)) {
                    sb.append("\n------> END " + request.method() + " (encoded body omitted)");
                } else {
                    Buffer buffer = new Buffer();
                    Intrinsics.checkNotNull(requestBodyBody);
                    requestBodyBody.writeTo(buffer);
                    Charset charset = UTF8;
                    MediaType mediaTypeContentType = requestBodyBody.contentType();
                    if (mediaTypeContentType != null) {
                        charset = mediaTypeContentType.charset(UTF8);
                    }
                    if (isPlaintext(buffer)) {
                        sb.append(ShellAdbUtils.COMMAND_LINE_END);
                        sb.append(buffer.readString(charset));
                        sb.append("\n------> END " + request.method() + " (" + requestBodyBody.contentLength() + "-byte body)");
                    } else {
                        sb.append("\n------> END " + request.method() + " (binary " + requestBodyBody.contentLength() + "-byte body omitted)");
                    }
                }
            } else {
                sb.append("\n------> END " + request.method());
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        log(string);
    }

    private final boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get(NSFunction.CONTENT_ENCODING);
        return (str == null || StringsKt.equals(str, "identity", true) || StringsKt.equals(str, NSFunction.GZIP, true)) ? false : true;
    }

    private final void logResponse(LogTime startTime, Response response) throws Throwable {
        ResponseBody responseBodyBody = response.body();
        Request request = response.request();
        long j = startTime.tookMs();
        long jContentLength = responseBodyBody != null ? responseBodyBody.contentLength() : -1L;
        Headers headers = response.headers();
        StringBuilder sb = new StringBuilder("<------ " + response.code() + ' ' + request.url() + " (" + j + "ms)");
        if (this.level == Level.BASIC) {
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String strName = headers.name(i);
                sb.append(ShellAdbUtils.COMMAND_LINE_END);
                sb.append(strName);
                sb.append(": ");
                sb.append(headers.value(i));
            }
        }
        if (this.level == Level.BASIC) {
            if (HttpHeaders.hasBody(response) && responseBodyBody != null) {
                Headers headers2 = response.headers();
                Intrinsics.checkNotNullExpressionValue(headers2, "response.headers()");
                if (bodyHasUnknownEncoding(headers2)) {
                    sb.append("\n<------ END HTTP (encoded body omitted)");
                } else {
                    BufferedSource bufferedSourceSource = responseBodyBody.source();
                    bufferedSourceSource.request(LongCompanionObject.MAX_VALUE);
                    Buffer buffer = bufferedSourceSource.buffer();
                    Long l = null;
                    GzipSource gzipSource = null;
                    if (StringsKt.equals(NSFunction.GZIP, headers.get(NSFunction.CONTENT_ENCODING), true)) {
                        Long lValueOf = Long.valueOf(buffer.size());
                        try {
                            GzipSource gzipSource2 = new GzipSource(buffer.m42clone());
                            try {
                                buffer = new Buffer();
                                buffer.writeAll(gzipSource2);
                                gzipSource2.close();
                                l = lValueOf;
                            } catch (Throwable th) {
                                th = th;
                                gzipSource = gzipSource2;
                                if (gzipSource != null) {
                                    gzipSource.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    Charset charset = UTF8;
                    MediaType mediaTypeContentType = responseBodyBody.contentType();
                    if (mediaTypeContentType != null) {
                        charset = mediaTypeContentType.charset(UTF8);
                    }
                    Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                    if (!isPlaintext(buffer)) {
                        sb.append("\n<------ END HTTP (binary " + buffer.size() + "-byte body omitted)");
                    }
                    if (jContentLength != 0) {
                        sb.append(ShellAdbUtils.COMMAND_LINE_END);
                        sb.append(buffer.m42clone().readString(charset));
                    }
                    if (l != null) {
                        sb.append("\n<------ END HTTP (" + buffer.size() + "-byte, " + l + "-gzipped-byte body)");
                    } else {
                        sb.append("\n<------ END HTTP (" + buffer.size() + "-byte body)");
                    }
                }
            } else {
                sb.append("\n<------ END HTTP");
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        log(string);
    }

    private final boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int utf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(utf8CodePoint) && !Character.isWhitespace(utf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private final void log(String message) {
        if (this.enableLog) {
            Log.d("TapHttp", message);
        }
    }
}

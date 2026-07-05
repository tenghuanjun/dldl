package com.taptap.sdk.kit.internal.http;

import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.bean.TapHttpResponse;
import com.taptap.sdk.kit.internal.bean.TapHttpResponseError;
import com.taptap.sdk.kit.internal.exception.TapTapException;
import com.taptap.sdk.kit.internal.json.TapJson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: TapHttpException.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapHttpException;", "", "()V", "NoServerError", "ParseDataError", "ServerError", "UnknownError", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpException {

    /* JADX INFO: compiled from: TapHttpException.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapHttpException$ServerError;", "Lcom/taptap/sdk/kit/internal/exception/TapTapException;", "httpCode", "", "response", "Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse;", "error", "Lcom/taptap/sdk/kit/internal/bean/TapHttpResponseError;", "(ILcom/taptap/sdk/kit/internal/bean/TapHttpResponse;Lcom/taptap/sdk/kit/internal/bean/TapHttpResponseError;)V", "getError", "()Lcom/taptap/sdk/kit/internal/bean/TapHttpResponseError;", "getHttpCode", "()I", "getResponse", "()Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class ServerError extends TapTapException {
        private final TapHttpResponseError error;
        private final int httpCode;
        private final TapHttpResponse<?> response;

        public final int getHttpCode() {
            return this.httpCode;
        }

        public final TapHttpResponse<?> getResponse() {
            return this.response;
        }

        public final TapHttpResponseError getError() {
            return this.error;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ServerError(int i, TapHttpResponse<?> response, TapHttpResponseError error) {
            super("ServerError(" + error.getMsg() + ')');
            Intrinsics.checkNotNullParameter(response, "response");
            Intrinsics.checkNotNullParameter(error, "error");
            this.httpCode = i;
            this.response = response;
            this.error = error;
        }
    }

    /* JADX INFO: compiled from: TapHttpException.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapHttpException$NoServerError;", "Lcom/taptap/sdk/kit/internal/exception/TapTapException;", "httpCode", "", "httpBody", "", "(ILjava/lang/String;)V", "getHttpBody", "()Ljava/lang/String;", "getHttpCode", "()I", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class NoServerError extends TapTapException {
        private final String httpBody;
        private final int httpCode;

        public final String getHttpBody() {
            return this.httpBody;
        }

        public final int getHttpCode() {
            return this.httpCode;
        }

        public NoServerError(int i, String str) {
            super("NoServerError(code:" + i + ", body:" + str + ')');
            this.httpCode = i;
            this.httpBody = str;
        }
    }

    /* JADX INFO: compiled from: TapHttpException.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapHttpException$ParseDataError;", "Lcom/taptap/sdk/kit/internal/exception/TapTapException;", "httpCode", "", "response", "Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse;", "(ILcom/taptap/sdk/kit/internal/bean/TapHttpResponse;)V", "getHttpCode", "()I", "getResponse", "()Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class ParseDataError extends TapTapException {
        private final int httpCode;
        private final TapHttpResponse<?> response;

        public final int getHttpCode() {
            return this.httpCode;
        }

        public final TapHttpResponse<?> getResponse() {
            return this.response;
        }

        public ParseDataError(int i, TapHttpResponse<?> response) {
            Intrinsics.checkNotNullParameter(response, "response");
            StringBuilder sb = new StringBuilder();
            sb.append("ParseDataError(");
            String strEncodeToString = null;
            try {
                Json json = TapJson.INSTANCE.getJson();
                KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(TapHttpResponse.class, KTypeProjection.INSTANCE.getSTAR()));
                Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                strEncodeToString = json.encodeToString(kSerializerSerializer, response);
            } catch (Exception e) {
                TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
            }
            sb.append(strEncodeToString);
            sb.append(')');
            super(sb.toString());
            this.httpCode = i;
            this.response = response;
        }
    }

    /* JADX INFO: compiled from: TapHttpException.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapHttpException$UnknownError;", "Lcom/taptap/sdk/kit/internal/exception/TapTapException;", "()V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class UnknownError extends TapTapException {
        public UnknownError() {
            super("TapHttpException.UnknownError");
        }
    }
}

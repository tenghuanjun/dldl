package com.taptap.sdk.kit.internal.http.hanlder;

import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.bean.TapHttpResponse;
import com.taptap.sdk.kit.internal.bean.TapHttpResponseError;
import com.taptap.sdk.kit.internal.http.TapHttpException;
import com.taptap.sdk.kit.internal.http.TapTime;
import com.taptap.sdk.kit.internal.json.TapJson;
import com.taptap.sdk.okhttp3.Response;
import com.taptap.sdk.okhttp3.ResponseBody;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;

/* JADX INFO: compiled from: TapHttpParser.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpParser;", "", "()V", "EventParser", "SdkDefaultParser", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpParser {

    /* JADX INFO: compiled from: TapHttpParser.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B/\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006¢\u0006\u0002\u0010\bJ&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000f\u0010\u0010R!\u0010\u0005\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpParser$SdkDefaultParser;", "DataBean", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpParser;", "responseHandler", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpResponseHandler;", "parserResponse", "Lkotlin/Function1;", "Lkotlinx/serialization/json/JsonObject;", "(Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpResponseHandler;Lkotlin/jvm/functions/Function1;)V", "getParserResponse", "()Lkotlin/jvm/functions/Function1;", "parse", "Lkotlin/Result;", "response", "Lcom/taptap/sdk/okhttp3/Response;", "parse-IoAF18A", "(Lcom/taptap/sdk/okhttp3/Response;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class SdkDefaultParser<DataBean> implements ITapHttpParser<DataBean> {
        private final Function1<JsonObject, DataBean> parserResponse;
        private final ITapHttpResponseHandler<DataBean> responseHandler;

        /* JADX WARN: Multi-variable type inference failed */
        public SdkDefaultParser(ITapHttpResponseHandler<DataBean> iTapHttpResponseHandler, Function1<? super JsonObject, ? extends DataBean> parserResponse) {
            Intrinsics.checkNotNullParameter(parserResponse, "parserResponse");
            this.responseHandler = iTapHttpResponseHandler;
            this.parserResponse = parserResponse;
        }

        public /* synthetic */ SdkDefaultParser(ITapHttpResponseHandler iTapHttpResponseHandler, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : iTapHttpResponseHandler, function1);
        }

        public final Function1<JsonObject, DataBean> getParserResponse() {
            return this.parserResponse;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.ITapHttpParser
        /* JADX INFO: renamed from: parse-IoAF18A */
        public Object mo39parseIoAF18A(Response response) {
            Object objDecodeFromString;
            Object objDecodeFromString2;
            Intrinsics.checkNotNullParameter(response, "response");
            int iCode = response.code();
            Object objDecodeFromJsonElement = null;
            if (response.isSuccessful()) {
                ResponseBody responseBodyBody = response.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                TapJson tapJson = TapJson.INSTANCE;
                String str = strString;
                if (str == null || str.length() == 0) {
                    objDecodeFromString2 = null;
                } else {
                    try {
                        Json json = tapJson.getJson();
                        KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(TapHttpResponse.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(JsonObject.class))));
                        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                        objDecodeFromString2 = json.decodeFromString(kSerializerSerializer, strString);
                    } catch (Exception e) {
                        TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
                        objDecodeFromString2 = null;
                    }
                }
                TapHttpResponse<JsonObject> tapHttpResponse = (TapHttpResponse) objDecodeFromString2;
                ITapHttpResponseHandler<DataBean> iTapHttpResponseHandler = this.responseHandler;
                Result<DataBean> resultM40handleResponseCmtIpJM = iTapHttpResponseHandler != null ? iTapHttpResponseHandler.m40handleResponseCmtIpJM(tapHttpResponse) : null;
                if (resultM40handleResponseCmtIpJM != null) {
                    return resultM40handleResponseCmtIpJM.getValue();
                }
                Boolean boolValueOf = tapHttpResponse != null ? Boolean.valueOf(tapHttpResponse.getSuccess()) : null;
                if (Intrinsics.areEqual((Object) boolValueOf, (Object) true)) {
                    TapTime.INSTANCE.calibrateServerTime(tapHttpResponse.getNow());
                    DataBean databeanInvoke = this.parserResponse.invoke(tapHttpResponse.getData());
                    if (databeanInvoke != null) {
                        Result.Companion companion = Result.INSTANCE;
                        return Result.m52constructorimpl(databeanInvoke);
                    }
                    Result.Companion companion2 = Result.INSTANCE;
                    return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.ParseDataError(iCode, tapHttpResponse)));
                }
                if (Intrinsics.areEqual((Object) boolValueOf, (Object) false)) {
                    TapJson tapJson2 = TapJson.INSTANCE;
                    JsonObject data = tapHttpResponse.getData();
                    if (data != null) {
                        try {
                            Json json2 = tapJson2.getJson();
                            KSerializer<Object> kSerializerSerializer2 = SerializersKt.serializer(json2.getSerializersModule(), Reflection.typeOf(TapHttpResponseError.class));
                            Intrinsics.checkNotNull(kSerializerSerializer2, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                            objDecodeFromJsonElement = json2.decodeFromJsonElement(kSerializerSerializer2, data);
                        } catch (Exception e2) {
                            TapLogger.loge$default(TapJson.TAG, null, e2, 2, null);
                        }
                    }
                    TapHttpResponseError tapHttpResponseError = (TapHttpResponseError) objDecodeFromJsonElement;
                    if (tapHttpResponseError != null) {
                        Result.Companion companion3 = Result.INSTANCE;
                        return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.ServerError(iCode, tapHttpResponse, tapHttpResponseError)));
                    }
                    Result.Companion companion4 = Result.INSTANCE;
                    return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.NoServerError(iCode, strString)));
                }
                Result.Companion companion5 = Result.INSTANCE;
                return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.NoServerError(iCode, strString)));
            }
            ResponseBody responseBodyBody2 = response.body();
            String strString2 = responseBodyBody2 != null ? responseBodyBody2.string() : null;
            TapJson tapJson3 = TapJson.INSTANCE;
            String str2 = strString2;
            if (str2 == null || str2.length() == 0) {
                objDecodeFromString = null;
            } else {
                try {
                    Json json3 = tapJson3.getJson();
                    KSerializer<Object> kSerializerSerializer3 = SerializersKt.serializer(json3.getSerializersModule(), Reflection.typeOf(TapHttpResponse.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(JsonObject.class))));
                    Intrinsics.checkNotNull(kSerializerSerializer3, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                    objDecodeFromString = json3.decodeFromString(kSerializerSerializer3, strString2);
                } catch (Exception e3) {
                    TapLogger.loge$default(TapJson.TAG, null, e3, 2, null);
                    objDecodeFromString = null;
                }
            }
            TapHttpResponse tapHttpResponse2 = (TapHttpResponse) objDecodeFromString;
            if (tapHttpResponse2 != null && !tapHttpResponse2.getSuccess()) {
                TapJson tapJson4 = TapJson.INSTANCE;
                JsonElement jsonElement = (JsonElement) tapHttpResponse2.getData();
                if (jsonElement != null) {
                    try {
                        Json json4 = tapJson4.getJson();
                        KSerializer<Object> kSerializerSerializer4 = SerializersKt.serializer(json4.getSerializersModule(), Reflection.typeOf(TapHttpResponseError.class));
                        Intrinsics.checkNotNull(kSerializerSerializer4, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                        objDecodeFromJsonElement = json4.decodeFromJsonElement(kSerializerSerializer4, jsonElement);
                    } catch (Exception e4) {
                        TapLogger.loge$default(TapJson.TAG, null, e4, 2, null);
                    }
                }
                TapHttpResponseError tapHttpResponseError2 = (TapHttpResponseError) objDecodeFromJsonElement;
                if (tapHttpResponseError2 != null) {
                    String error = tapHttpResponseError2.getError();
                    if (!(error == null || error.length() == 0)) {
                        Result.Companion companion6 = Result.INSTANCE;
                        return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.ServerError(iCode, tapHttpResponse2, tapHttpResponseError2)));
                    }
                }
                Result.Companion companion7 = Result.INSTANCE;
                return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.NoServerError(iCode, strString2)));
            }
            Result.Companion companion8 = Result.INSTANCE;
            return Result.m52constructorimpl(ResultKt.createFailure(new TapHttpException.NoServerError(iCode, strString2)));
        }
    }

    /* JADX INFO: compiled from: TapHttpParser.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpParser$EventParser;", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpParser;", "", "()V", "parse", "Lkotlin/Result;", "response", "Lcom/taptap/sdk/okhttp3/Response;", "parse-IoAF18A", "(Lcom/taptap/sdk/okhttp3/Response;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class EventParser implements ITapHttpParser<Boolean> {
        @Override // com.taptap.sdk.kit.internal.http.hanlder.ITapHttpParser
        /* JADX INFO: renamed from: parse-IoAF18A */
        public Object mo39parseIoAF18A(Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            if (response.isSuccessful() || response.code() == 400) {
                Result.Companion companion = Result.INSTANCE;
                return Result.m52constructorimpl(true);
            }
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m52constructorimpl(false);
        }
    }
}

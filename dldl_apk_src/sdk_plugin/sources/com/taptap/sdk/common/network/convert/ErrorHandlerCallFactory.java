package com.taptap.sdk.common.network.convert;

import com.huya.mtp.http.monitor.Stat;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.track.SqTrackNetDnsKey;
import com.taptap.sdk.common.network.convert.ErrorHandlerCallFactory;
import com.taptap.sdk.common.network.data.response.BaseResponse;
import com.taptap.sdk.common.network.data.response.ErrResponse;
import com.taptap.sdk.common.network.throwable.ApiErr;
import com.taptap.sdk.common.network.throwable.TapNetworkException;
import com.taptap.sdk.common.network.utils.FixedTimeUtil;
import com.taptap.sdk.okhttp3.Request;
import com.taptap.sdk.okhttp3.ResponseBody;
import com.taptap.sdk.okio.Timeout;
import com.taptap.sdk.retrofit2.Call;
import com.taptap.sdk.retrofit2.CallAdapter;
import com.taptap.sdk.retrofit2.Callback;
import com.taptap.sdk.retrofit2.Response;
import com.taptap.sdk.retrofit2.Retrofit;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: ErrorHandlerCallFactory.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0003\r\u000e\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0002¢\u0006\u0002\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/common/network/convert/ErrorHandlerCallFactory;", "Lcom/taptap/sdk/retrofit2/CallAdapter$Factory;", "()V", "get", "Lcom/taptap/sdk/retrofit2/CallAdapter;", "returnType", "Ljava/lang/reflect/Type;", "annotations", "", "", "retrofit", "Lcom/taptap/sdk/retrofit2/Retrofit;", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;Lcom/taptap/sdk/retrofit2/Retrofit;)Lcom/taptap/sdk/retrofit2/CallAdapter;", "Companion", "ErrorHandlingCall", "ErrorHandlingCallAdapter", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ErrorHandlerCallFactory extends CallAdapter.Factory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ ErrorHandlerCallFactory(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ErrorHandlerCallFactory() {
    }

    @Override // com.taptap.sdk.retrofit2.CallAdapter.Factory
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Type responseType = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        Intrinsics.checkNotNullExpressionValue(responseType, "responseType");
        return new ErrorHandlingCallAdapter(responseType);
    }

    /* JADX INFO: compiled from: ErrorHandlerCallFactory.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/common/network/convert/ErrorHandlerCallFactory$ErrorHandlingCallAdapter;", "T", "Lcom/taptap/sdk/retrofit2/CallAdapter;", "Lcom/taptap/sdk/retrofit2/Call;", "responseType", "Ljava/lang/reflect/Type;", "(Ljava/lang/reflect/Type;)V", "adapt", "call", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class ErrorHandlingCallAdapter<T> implements CallAdapter<T, Call<T>> {
        private final Type responseType;

        public ErrorHandlingCallAdapter(Type responseType) {
            Intrinsics.checkNotNullParameter(responseType, "responseType");
            this.responseType = responseType;
        }

        @Override // com.taptap.sdk.retrofit2.CallAdapter
        /* JADX INFO: renamed from: responseType, reason: from getter */
        public Type getResponseType() {
            return this.responseType;
        }

        @Override // com.taptap.sdk.retrofit2.CallAdapter
        /* JADX INFO: renamed from: adapt */
        public Call<T> adapt2(Call<T> call) {
            Intrinsics.checkNotNullParameter(call, "call");
            return new ErrorHandlingCall(call);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ErrorHandlerCallFactory.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016J\u0016\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0016\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/taptap/sdk/common/network/convert/ErrorHandlerCallFactory$ErrorHandlingCall;", "T", "Lcom/taptap/sdk/retrofit2/Call;", "delegate", "(Lcom/taptap/sdk/retrofit2/Call;)V", "cancel", "", "clone", "enqueue", "callback", "Lcom/taptap/sdk/retrofit2/Callback;", Stat.EXECUTE_KEY, "Lcom/taptap/sdk/retrofit2/Response;", "isCanceled", "", "isExecuted", "parseBaseResponse", "Lcom/taptap/sdk/common/network/data/response/BaseResponse;", "response", "parseServerError", "Lcom/taptap/sdk/common/network/data/response/ErrResponse;", FunctionRouter.KEY_DATA, "Lkotlinx/serialization/json/JsonElement;", "code", "", SqTrackNetDnsKey.request, "Lcom/taptap/sdk/okhttp3/Request;", "timeout", "Lcom/taptap/sdk/okio/Timeout;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    static final class ErrorHandlingCall<T> implements Call<T> {
        private final Call<T> delegate;

        public ErrorHandlingCall(Call<T> delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
        }

        @Override // com.taptap.sdk.retrofit2.Call
        public Response<T> execute() {
            throw new UnsupportedOperationException("MyCall does not support synchronous execution");
        }

        @Override // com.taptap.sdk.retrofit2.Call
        public void enqueue(final Callback<T> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.delegate.enqueue(new Callback<T>(this) { // from class: com.taptap.sdk.common.network.convert.ErrorHandlerCallFactory$ErrorHandlingCall$enqueue$1
                final /* synthetic */ ErrorHandlerCallFactory.ErrorHandlingCall<T> this$0;

                /* JADX INFO: compiled from: ErrorHandlerCallFactory.kt */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[ApiErr.values().length];
                        try {
                            iArr[ApiErr.INVALID_TIME.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    this.this$0 = this;
                }

                @Override // com.taptap.sdk.retrofit2.Callback
                public void onResponse(Call<T> call, Response<T> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (!response.isSuccessful()) {
                        try {
                            int iCode = response.code();
                            BaseResponse baseResponse = this.this$0.parseBaseResponse(response);
                            ErrResponse serverError = this.this$0.parseServerError(baseResponse.getData(), iCode);
                            if (serverError.getCode() != 0) {
                                if (serverError.getError().length() > 0) {
                                    TapNetworkException.Err err = new TapNetworkException.Err(ApiErr.INSTANCE.from(serverError.getError()), serverError.getCode(), serverError.getMsg());
                                    if (WhenMappings.$EnumSwitchMapping$0[err.getApiErr().ordinal()] == 1) {
                                        FixedTimeUtil.INSTANCE.fix(Integer.valueOf(baseResponse.getNow()));
                                    }
                                    callback.onFailure(call, new TapNetworkException(err, null, 2, null));
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            callback.onFailure(call, th);
                            return;
                        }
                    }
                    callback.onResponse(call, response);
                }

                @Override // com.taptap.sdk.retrofit2.Callback
                public void onFailure(Call<T> call, Throwable error) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(error, "error");
                    callback.onFailure(call, error);
                }
            });
        }

        @Override // com.taptap.sdk.retrofit2.Call
        public boolean isExecuted() {
            return this.delegate.isExecuted();
        }

        @Override // com.taptap.sdk.retrofit2.Call
        public void cancel() {
            this.delegate.cancel();
        }

        @Override // com.taptap.sdk.retrofit2.Call
        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        @Override // com.taptap.sdk.retrofit2.Call
        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Call<T> m31clone() {
            Call<T> callM31clone = this.delegate.m31clone();
            Intrinsics.checkNotNullExpressionValue(callM31clone, "delegate.clone()");
            return new ErrorHandlingCall(callM31clone);
        }

        @Override // com.taptap.sdk.retrofit2.Call
        public Request request() {
            Request request = this.delegate.request();
            Intrinsics.checkNotNullExpressionValue(request, "delegate.request()");
            return request;
        }

        @Override // com.taptap.sdk.retrofit2.Call
        public Timeout timeout() {
            Timeout timeout = this.delegate.timeout();
            Intrinsics.checkNotNullExpressionValue(timeout, "delegate.timeout()");
            return timeout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final BaseResponse parseBaseResponse(Response<T> response) throws TapNetworkException {
            BaseResponse baseResponse;
            int iCode = response.code();
            ResponseBody responseBodyErrorBody = response.errorBody();
            String strString = responseBodyErrorBody != null ? responseBodyErrorBody.string() : null;
            if (strString == null) {
                strString = "";
            }
            try {
                Json.Companion companion = Json.INSTANCE;
                KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(BaseResponse.class));
                Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                baseResponse = (BaseResponse) companion.decodeFromString(kSerializerSerializer, strString);
            } catch (Exception unused) {
                baseResponse = null;
            }
            if (!(baseResponse == null)) {
                return baseResponse;
            }
            throw new TapNetworkException(new TapNetworkException.Err(ApiErr.JSON_PARSE_ERROR, response.code(), "parse errorBody fail, code: " + iCode + ", errorBody: " + strString), null, 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ErrResponse parseServerError(JsonElement data, int code) throws TapNetworkException {
            ErrResponse errResponse;
            try {
                Json.Companion companion = Json.INSTANCE;
                KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(ErrResponse.class));
                Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                errResponse = (ErrResponse) companion.decodeFromJsonElement(kSerializerSerializer, data);
            } catch (Exception unused) {
                errResponse = null;
            }
            if (!(errResponse == null)) {
                return errResponse;
            }
            throw new TapNetworkException(new TapNetworkException.Err(ApiErr.JSON_PARSE_ERROR, code, "parse BaseResponse.data fail, code: " + code), null, 2, null);
        }
    }

    /* JADX INFO: compiled from: ErrorHandlerCallFactory.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/common/network/convert/ErrorHandlerCallFactory$Companion;", "", "()V", "create", "Lcom/taptap/sdk/retrofit2/CallAdapter$Factory;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CallAdapter.Factory create() {
            return new ErrorHandlerCallFactory(null);
        }
    }
}

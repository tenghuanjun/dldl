package com.taptap.sdk.review.internal;

import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.http.call.ITapHttpCall;
import com.taptap.sdk.kit.internal.http.call.TapHttpCall;
import com.taptap.sdk.kit.internal.http.hanlder.ITapHttpResponseHandler;
import com.taptap.sdk.kit.internal.http.hanlder.TapHttpBackoff;
import com.taptap.sdk.kit.internal.http.hanlder.TapHttpParser;
import com.taptap.sdk.kit.internal.http.hanlder.TapHttpRetry;
import com.taptap.sdk.kit.internal.http.param.AbsTapHttpParam;
import com.taptap.sdk.kit.internal.http.param.TapHttpJsonParam;
import com.taptap.sdk.kit.internal.json.TapJson;
import com.taptap.sdk.review.ReviewCrossAppCodeCallback;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonObject;

/* JADX INFO: compiled from: TapReviewApi.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/review/internal/TapReviewApi;", "", "()V", "fetchCrossAppCode", "Lkotlinx/coroutines/Job;", "callback", "Lcom/taptap/sdk/review/ReviewCrossAppCodeCallback;", "tap-review_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapReviewApi {
    public static final TapReviewApi INSTANCE = new TapReviewApi();

    private TapReviewApi() {
    }

    /* JADX INFO: renamed from: com.taptap.sdk.review.internal.TapReviewApi$fetchCrossAppCode$1, reason: invalid class name */
    /* JADX INFO: compiled from: TapReviewApi.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.review.internal.TapReviewApi$fetchCrossAppCode$1", f = "TapReviewApi.kt", i = {}, l = {35}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ReviewCrossAppCodeCallback $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ReviewCrossAppCodeCallback reviewCrossAppCodeCallback, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$callback = reviewCrossAppCodeCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objM36enqueueResultgIAlus$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            ITapHttpResponseHandler iTapHttpResponseHandler = null;
            Object[] objArr = 0;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AbsTapHttpParam<TapHttpJsonParam> absTapHttpParamRetryBackoff = TapReviewApiKt.getReviewHttp().postJson("/sdk-core/v1/cross-app-code").retryBackoff(new TapHttpBackoff.Exponential());
                this.label = 1;
                objM36enqueueResultgIAlus$default = ITapHttpCall.DefaultImpls.m36enqueueResultgIAlus$default(new TapHttpCall(absTapHttpParamRetryBackoff, new TapHttpParser.SdkDefaultParser(iTapHttpResponseHandler, new Function1<JsonObject, TapCrossAppCode>() { // from class: com.taptap.sdk.review.internal.TapReviewApi$fetchCrossAppCode$1$invokeSuspend$$inlined$toCall$1
                    /* JADX WARN: Type inference failed for: r1v2, types: [com.taptap.sdk.review.internal.TapCrossAppCode, java.lang.Object] */
                    @Override // kotlin.jvm.functions.Function1
                    public final TapCrossAppCode invoke(JsonObject jsonObject) {
                        TapJson tapJson = TapJson.INSTANCE;
                        try {
                            if (jsonObject == null) {
                                return null;
                            }
                            Json json = tapJson.getJson();
                            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.nullableTypeOf(TapCrossAppCode.class));
                            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                            return json.decodeFromJsonElement(kSerializerSerializer, jsonObject);
                        } catch (Exception e) {
                            TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
                            return null;
                        }
                    }
                }, i2, objArr == true ? 1 : 0), new TapHttpRetry.SdkDefault(absTapHttpParamRetryBackoff.getBackoffHandler())), 0L, this, 1, null);
                if (objM36enqueueResultgIAlus$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM36enqueueResultgIAlus$default = ((Result) obj).getValue();
            }
            ReviewCrossAppCodeCallback reviewCrossAppCodeCallback = this.$callback;
            if (Result.m59isSuccessimpl(objM36enqueueResultgIAlus$default)) {
                TapCrossAppCode tapCrossAppCode = (TapCrossAppCode) objM36enqueueResultgIAlus$default;
                TapReviewLoggerKt.logInfo("fetchCrossAppCode success: code=" + tapCrossAppCode.getCode());
                if (reviewCrossAppCodeCallback != null) {
                    reviewCrossAppCodeCallback.onCodeResult(tapCrossAppCode.getCode());
                }
            }
            ReviewCrossAppCodeCallback reviewCrossAppCodeCallback2 = this.$callback;
            Throwable thM55exceptionOrNullimpl = Result.m55exceptionOrNullimpl(objM36enqueueResultgIAlus$default);
            if (thM55exceptionOrNullimpl != null) {
                TapReviewLoggerKt.logError("fetchCrossAppCode fail", thM55exceptionOrNullimpl);
                if (reviewCrossAppCodeCallback2 != null) {
                    reviewCrossAppCodeCallback2.onCodeResult(null);
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Job fetchCrossAppCode$default(TapReviewApi tapReviewApi, ReviewCrossAppCodeCallback reviewCrossAppCodeCallback, int i, Object obj) {
        if ((i & 1) != 0) {
            reviewCrossAppCodeCallback = null;
        }
        return tapReviewApi.fetchCrossAppCode(reviewCrossAppCodeCallback);
    }

    public final Job fetchCrossAppCode(ReviewCrossAppCodeCallback callback) {
        return BuildersKt__Builders_commonKt.launch$default(TapReviewApiKt.getReviewScope(), Dispatchers.getIO(), null, new AnonymousClass1(callback, null), 2, null);
    }
}

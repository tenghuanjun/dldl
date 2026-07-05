package com.taptap.sdk.common.network.transform;

import com.sqwan.common.route.FunctionRouter;
import com.taptap.sdk.base.network.transform.BizTransformer;
import com.taptap.sdk.common.network.data.response.BaseResponse;
import com.taptap.sdk.common.network.data.response.ErrResponse;
import com.taptap.sdk.common.network.throwable.ApiErr;
import com.taptap.sdk.common.network.throwable.TapNetworkException;
import com.taptap.sdk.common.network.utils.FixedTimeUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: DefaultBizTransformer.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/common/network/transform/DefaultBizTransformer;", "Lcom/taptap/sdk/base/network/transform/BizTransformer;", "()V", "parseBaseResponse", "Lcom/taptap/sdk/common/network/data/response/BaseResponse;", "jsonStr", "", "parseErrorResponse", "Lcom/taptap/sdk/common/network/data/response/ErrResponse;", FunctionRouter.KEY_DATA, "Lkotlinx/serialization/json/JsonElement;", "transform", "Ljava/io/InputStream;", "origin", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultBizTransformer implements BizTransformer {

    /* JADX INFO: compiled from: DefaultBizTransformer.kt */
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

    @Override // com.taptap.sdk.base.network.transform.BizTransformer
    public InputStream transform(InputStream origin) throws TapNetworkException {
        Intrinsics.checkNotNullParameter(origin, "origin");
        BaseResponse baseResponse = parseBaseResponse(new String(ByteStreamsKt.readBytes(origin), Charsets.UTF_8));
        if (baseResponse.getSuccess()) {
            Json.Companion companion = Json.INSTANCE;
            JsonElement data = baseResponse.getData();
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(JsonElement.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            byte[] bytes = companion.encodeToString(kSerializerSerializer, data).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return new ByteArrayInputStream(bytes);
        }
        ErrResponse errorResponse = parseErrorResponse(baseResponse.getData());
        if (errorResponse.getCode() != 0) {
            if (errorResponse.getError().length() > 0) {
                TapNetworkException.Err err = new TapNetworkException.Err(ApiErr.INSTANCE.from(errorResponse.getError()), errorResponse.getCode(), errorResponse.getMsg());
                if (WhenMappings.$EnumSwitchMapping$0[err.getApiErr().ordinal()] == 1) {
                    FixedTimeUtil.INSTANCE.fix(Integer.valueOf(baseResponse.getNow()));
                }
                throw new TapNetworkException(err, null, 2, null);
            }
        }
        Json.Companion companion2 = Json.INSTANCE;
        JsonElement data2 = baseResponse.getData();
        KSerializer<Object> kSerializerSerializer2 = SerializersKt.serializer(companion2.getSerializersModule(), Reflection.typeOf(JsonElement.class));
        Intrinsics.checkNotNull(kSerializerSerializer2, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        byte[] bytes2 = companion2.encodeToString(kSerializerSerializer2, data2).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(bytes2);
    }

    private final BaseResponse parseBaseResponse(String jsonStr) throws TapNetworkException {
        BaseResponse baseResponse;
        try {
            Json.Companion companion = Json.INSTANCE;
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(BaseResponse.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            baseResponse = (BaseResponse) companion.decodeFromString(kSerializerSerializer, jsonStr);
        } catch (Exception unused) {
            baseResponse = null;
        }
        if (!(baseResponse == null)) {
            return baseResponse;
        }
        throw new TapNetworkException(new TapNetworkException.Err(ApiErr.JSON_PARSE_ERROR, -1, "parse jsonStr fail, jsonStr: " + jsonStr), null, 2, null);
    }

    private final ErrResponse parseErrorResponse(JsonElement data) throws TapNetworkException {
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
        throw new TapNetworkException(new TapNetworkException.Err(ApiErr.JSON_PARSE_ERROR, -1, "parse BaseResponse.data fail, data: " + data), null, 2, null);
    }
}

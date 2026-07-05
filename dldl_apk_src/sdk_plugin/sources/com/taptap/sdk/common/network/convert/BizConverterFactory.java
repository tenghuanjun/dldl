package com.taptap.sdk.common.network.convert;

import com.taptap.sdk.base.network.annotation.Transform;
import com.taptap.sdk.base.network.transform.BizTransformer;
import com.taptap.sdk.common.network.transform.DefaultBizTransformer;
import com.taptap.sdk.okhttp3.ResponseBody;
import com.taptap.sdk.retrofit2.Converter;
import com.taptap.sdk.retrofit2.Retrofit;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: BizConverterFactory.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J9\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/common/network/convert/BizConverterFactory;", "Lcom/taptap/sdk/retrofit2/Converter$Factory;", "()V", "responseBodyConverter", "Lcom/taptap/sdk/retrofit2/Converter;", "Lcom/taptap/sdk/okhttp3/ResponseBody;", "returnType", "Ljava/lang/reflect/Type;", "annotations", "", "", "retrofit", "Lcom/taptap/sdk/retrofit2/Retrofit;", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;Lcom/taptap/sdk/retrofit2/Retrofit;)Lcom/taptap/sdk/retrofit2/Converter;", "BizConverter", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BizConverterFactory extends Converter.Factory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ BizConverterFactory(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private BizConverterFactory() {
    }

    /* JADX INFO: compiled from: BizConverterFactory.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0002B'\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0010\u0010\u0007\u001a\f\u0012\u0004\u0012\u00020\u0003\u0012\u0002\b\u00030\u0002¢\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00062\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000eH\u0002R\u0018\u0010\u0007\u001a\f\u0012\u0004\u0012\u00020\u0003\u0012\u0002\b\u00030\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/common/network/convert/BizConverterFactory$BizConverter;", "T", "Lcom/taptap/sdk/retrofit2/Converter;", "Lcom/taptap/sdk/okhttp3/ResponseBody;", "klass", "Lkotlin/reflect/KClass;", "Lcom/taptap/sdk/base/network/transform/BizTransformer;", "delegate", "(Lkotlin/reflect/KClass;Lcom/taptap/sdk/retrofit2/Converter;)V", "convert", "value", "(Lcom/taptap/sdk/okhttp3/ResponseBody;)Ljava/lang/Object;", "createTransformerByReflect", "clazz", "Ljava/lang/Class;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class BizConverter<T> implements Converter<ResponseBody, T> {
        private final Converter<ResponseBody, ?> delegate;
        private final KClass<? extends BizTransformer> klass;

        public BizConverter(KClass<? extends BizTransformer> klass, Converter<ResponseBody, ?> delegate) {
            Intrinsics.checkNotNullParameter(klass, "klass");
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.klass = klass;
            this.delegate = delegate;
        }

        @Override // com.taptap.sdk.retrofit2.Converter
        public T convert(ResponseBody value) throws IllegalAccessException, InstantiationException {
            Intrinsics.checkNotNullParameter(value, "value");
            BizTransformer bizTransformerCreateTransformerByReflect = createTransformerByReflect(JvmClassMappingKt.getJavaClass((KClass) this.klass));
            InputStream inputStreamByteStream = value.byteStream();
            Intrinsics.checkNotNullExpressionValue(inputStreamByteStream, "value.byteStream()");
            return (T) this.delegate.convert(ResponseBody.create(value.contentType(), ByteStreamsKt.readBytes(bizTransformerCreateTransformerByReflect.transform(inputStreamByteStream))));
        }

        private final BizTransformer createTransformerByReflect(Class<? extends BizTransformer> clazz) throws IllegalAccessException, InstantiationException {
            BizTransformer bizTransformerNewInstance = clazz.newInstance();
            Intrinsics.checkNotNullExpressionValue(bizTransformerNewInstance, "clazz.newInstance()");
            return bizTransformerNewInstance;
        }
    }

    /* JADX INFO: compiled from: BizConverterFactory.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/common/network/convert/BizConverterFactory$Companion;", "", "()V", "create", "Lcom/taptap/sdk/retrofit2/Converter$Factory;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Converter.Factory create() {
            return new BizConverterFactory(null);
        }
    }

    @Override // com.taptap.sdk.retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        ArrayList arrayList = new ArrayList();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Transform) {
                arrayList.add(annotation);
            }
        }
        Transform transform = (Transform) CollectionsKt.firstOrNull((List) arrayList);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(transform != null ? transform.klass() : DefaultBizTransformer.class);
        Converter converterNextResponseBodyConverter = retrofit.nextResponseBodyConverter(this, returnType, annotations);
        if (converterNextResponseBodyConverter == null) {
            return null;
        }
        return new BizConverter(orCreateKotlinClass, converterNextResponseBodyConverter);
    }
}

package com.taptap.sdk.initializer.api.model;

import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.ObjectSerializer;

/* JADX INFO: compiled from: ScreenOrientation.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007HÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/initializer/api/model/ScreenOrientation;", "", "()V", "LANDSCAPE", "", "PORTRAIT", "serializer", "Lkotlinx/serialization/KSerializer;", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final class ScreenOrientation {
    public static final int LANDSCAPE = 1;
    public static final int PORTRAIT = 0;
    public static final ScreenOrientation INSTANCE = new ScreenOrientation();
    private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.taptap.sdk.initializer.api.model.ScreenOrientation$$cachedSerializer$delegate$1
        @Override // kotlin.jvm.functions.Function0
        public final KSerializer<Object> invoke() {
            return new ObjectSerializer("com.taptap.sdk.initializer.api.model.ScreenOrientation", ScreenOrientation.INSTANCE, new Annotation[0]);
        }
    });

    private ScreenOrientation() {
    }

    private final /* synthetic */ Lazy get$cachedSerializer$delegate() {
        return $cachedSerializer$delegate;
    }

    public final KSerializer<ScreenOrientation> serializer() {
        return (KSerializer) get$cachedSerializer$delegate().getValue();
    }
}

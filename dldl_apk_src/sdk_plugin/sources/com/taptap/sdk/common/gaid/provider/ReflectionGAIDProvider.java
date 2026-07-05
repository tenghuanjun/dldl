package com.taptap.sdk.common.gaid.provider;

import android.content.Context;
import com.taptap.sdk.common.gaid.data.model.GAID;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.joor.Reflect;

/* JADX INFO: compiled from: ReflectionGAIDProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/common/gaid/provider/ReflectionGAIDProvider;", "Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;", "()V", "getAdvertisingIdInfo", "", "context", "Landroid/content/Context;", "provideGAID", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ReflectionGAIDProvider implements GAIDProvider {
    @Override // com.taptap.sdk.common.gaid.provider.GAIDProvider
    public Object provideGAID(Context context, Continuation<? super GAID> continuation) {
        try {
            Object advertisingIdInfo = getAdvertisingIdInfo(context);
            String str = (String) Reflect.on(advertisingIdInfo).call("getId").get();
            Boolean isLimitAdTrackingEnabled = (Boolean) Reflect.on(advertisingIdInfo).call("isLimitAdTrackingEnabled").get();
            Intrinsics.checkNotNullExpressionValue(isLimitAdTrackingEnabled, "isLimitAdTrackingEnabled");
            return new GAID(str, isLimitAdTrackingEnabled.booleanValue());
        } catch (Exception unused) {
            return null;
        }
    }

    private final Object getAdvertisingIdInfo(Context context) {
        try {
            return Reflect.onClass("com.google.android.gms.ads.identifier.AdvertisingIdClient").call("getAdvertisingIdInfo", context).get();
        } catch (Exception unused) {
            return null;
        }
    }
}

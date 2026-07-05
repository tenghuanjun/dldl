package com.taptap.sdk.common.gaid.provider;

import android.content.Context;
import com.taptap.sdk.common.gaid.data.model.GAID;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: GAIDProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/common/gaid/provider/GAIDProvider;", "", "provideGAID", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface GAIDProvider {
    Object provideGAID(Context context, Continuation<? super GAID> continuation);
}

package com.taptap.sdk.gid.service.network;

import com.taptap.sdk.gid.data.response.Gid;
import com.taptap.sdk.themis.lite.data.model.OneID;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: GidNetworkService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/gid/service/network/GidNetworkService;", "", "getGid", "Lcom/taptap/sdk/gid/data/response/Gid;", "platform", "", "oneID", "Lcom/taptap/sdk/themis/lite/data/model/OneID;", "(Ljava/lang/String;Lcom/taptap/sdk/themis/lite/data/model/OneID;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface GidNetworkService {
    Object getGid(String str, OneID oneID, Continuation<? super Gid> continuation);
}

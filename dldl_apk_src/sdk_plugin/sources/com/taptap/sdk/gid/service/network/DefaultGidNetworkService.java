package com.taptap.sdk.gid.service.network;

import com.taptap.sdk.gid.data.request.DeviceRequest;
import com.taptap.sdk.gid.data.response.Gid;
import com.taptap.sdk.themis.lite.data.model.OneID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultGidNetworkService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/gid/service/network/DefaultGidNetworkService;", "Lcom/taptap/sdk/gid/service/network/GidNetworkService;", "gidApiService", "Lcom/taptap/sdk/gid/service/network/GidApiService;", "(Lcom/taptap/sdk/gid/service/network/GidApiService;)V", "getGid", "Lcom/taptap/sdk/gid/data/response/Gid;", "platform", "", "oneID", "Lcom/taptap/sdk/themis/lite/data/model/OneID;", "(Ljava/lang/String;Lcom/taptap/sdk/themis/lite/data/model/OneID;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultGidNetworkService implements GidNetworkService {
    private final GidApiService gidApiService;

    public DefaultGidNetworkService(GidApiService gidApiService) {
        Intrinsics.checkNotNullParameter(gidApiService, "gidApiService");
        this.gidApiService = gidApiService;
    }

    @Override // com.taptap.sdk.gid.service.network.GidNetworkService
    public Object getGid(String str, OneID oneID, Continuation<? super Gid> continuation) {
        boolean z = oneID == null;
        if (z) {
            return null;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        Object objQuery = this.gidApiService.query(new DeviceRequest(str, oneID.getData()), continuation);
        return objQuery == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objQuery : (Gid) objQuery;
    }
}

package com.taptap.sdk.initializer.repository.remote;

import com.sqwan.common.track.SqTrackNetDnsKey;
import com.taptap.sdk.base.network.annotation.Module;
import com.taptap.sdk.initializer.data.request.GateKeeperRequest;
import com.taptap.sdk.initializer.data.response.GateKeeper;
import com.taptap.sdk.retrofit2.http.Body;
import com.taptap.sdk.retrofit2.http.POST;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: GatekeeperApiService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/initializer/repository/remote/GatekeeperApiService;", "", "fetchGatekeeper", "Lcom/taptap/sdk/initializer/data/response/GateKeeper;", SqTrackNetDnsKey.request, "Lcom/taptap/sdk/initializer/data/request/GateKeeperRequest;", "(Lcom/taptap/sdk/initializer/data/request/GateKeeperRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Module(name = "TapSDKCore", version = "4.5.7")
public interface GatekeeperApiService {
    @POST("/sdk-core/v1/gatekeeper")
    Object fetchGatekeeper(@Body GateKeeperRequest gateKeeperRequest, Continuation<? super GateKeeper> continuation);
}

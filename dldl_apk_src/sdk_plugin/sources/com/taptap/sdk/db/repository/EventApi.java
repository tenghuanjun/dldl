package com.taptap.sdk.db.repository;

import com.sqwan.common.track.SqTrackNetDnsKey;
import com.taptap.sdk.db.data.request.TrackEventRequest;
import com.taptap.sdk.retrofit2.http.Body;
import com.taptap.sdk.retrofit2.http.Headers;
import com.taptap.sdk.retrofit2.http.POST;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: EventApi.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/db/repository/EventApi;", "", "sendEvents", "", SqTrackNetDnsKey.request, "Lcom/taptap/sdk/db/data/request/TrackEventRequest;", "(Lcom/taptap/sdk/db/data/request/TrackEventRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface EventApi {
    @POST("v2/batch")
    @Headers({"Content-Type: application/json"})
    Object sendEvents(@Body TrackEventRequest trackEventRequest, Continuation<? super Unit> continuation);
}

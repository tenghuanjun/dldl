package com.taptap.sdk.db;

import com.taptap.sdk.core.TapTapEvent;
import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.model.WrapEvent;
import com.taptap.sdk.db.interceptor.Interceptor;
import com.taptap.sdk.db.properties.PredefinedProvider;
import com.taptap.sdk.db.properties.SystemProvider;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;

/* JADX INFO: compiled from: EventContext.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R&\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u0012X¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u00020\u0018X¦\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f0\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0012\u0010$\u001a\u00020%X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0012\u0010(\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0005R\u0012\u0010*\u001a\u00020+X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006."}, d2 = {"Lcom/taptap/sdk/db/EventContext;", "", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "setCurrentUserId", "(Ljava/lang/String;)V", "customExtras", "", "getCustomExtras", "()Ljava/util/Map;", "setCustomExtras", "(Ljava/util/Map;)V", "customOAID", "getCustomOAID", "setCustomOAID", "dynamicPropertiesGetter", "Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;", "getDynamicPropertiesGetter", "()Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;", "setDynamicPropertiesGetter", "(Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;)V", "eventIndex", "Ljava/util/concurrent/atomic/AtomicLong;", "getEventIndex", "()Ljava/util/concurrent/atomic/AtomicLong;", "setEventIndex", "(Ljava/util/concurrent/atomic/AtomicLong;)V", "interceptors", "", "Lcom/taptap/sdk/db/interceptor/Interceptor;", "Lcom/taptap/sdk/db/data/model/WrapEvent;", "Lcom/taptap/sdk/db/data/model/Event;", "getInterceptors", "()Ljava/util/List;", "predefinedProvider", "Lcom/taptap/sdk/db/properties/PredefinedProvider;", "getPredefinedProvider", "()Lcom/taptap/sdk/db/properties/PredefinedProvider;", "sessionId", "getSessionId", "systemProvider", "Lcom/taptap/sdk/db/properties/SystemProvider;", "getSystemProvider", "()Lcom/taptap/sdk/db/properties/SystemProvider;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface EventContext {
    String getCurrentUserId();

    Map<String, Object> getCustomExtras();

    String getCustomOAID();

    TapTapEvent.TapEventDynamicProperties getDynamicPropertiesGetter();

    AtomicLong getEventIndex();

    List<Interceptor<WrapEvent, Event>> getInterceptors();

    PredefinedProvider getPredefinedProvider();

    String getSessionId();

    SystemProvider getSystemProvider();

    void setCurrentUserId(String str);

    void setCustomExtras(Map<String, ? extends Object> map);

    void setCustomOAID(String str);

    void setDynamicPropertiesGetter(TapTapEvent.TapEventDynamicProperties tapEventDynamicProperties);

    void setEventIndex(AtomicLong atomicLong);
}

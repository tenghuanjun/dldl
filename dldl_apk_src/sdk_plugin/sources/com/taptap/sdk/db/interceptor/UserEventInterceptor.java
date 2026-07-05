package com.taptap.sdk.db.interceptor;

import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.model.EventType;
import com.taptap.sdk.db.data.model.WrapEvent;
import com.taptap.sdk.db.interceptor.Interceptor;
import com.taptap.sdk.db.utils.JSONObjectUtils;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: UserEventInterceptor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J0\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0002J%\u0010\u000e\u001a\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcom/taptap/sdk/db/interceptor/UserEventInterceptor;", "Lcom/taptap/sdk/db/interceptor/Interceptor;", "Lcom/taptap/sdk/db/data/model/WrapEvent;", "Lcom/taptap/sdk/db/data/model/Event;", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "buildEvent", "event", "eventProperties", "", "", "", "userId", "intercept", "chain", "Lcom/taptap/sdk/db/interceptor/Interceptor$Chain;", "(Lcom/taptap/sdk/db/interceptor/Interceptor$Chain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class UserEventInterceptor implements Interceptor<WrapEvent, Event> {
    private final TapTapSdkOptions options;

    /* JADX INFO: compiled from: UserEventInterceptor.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EventType.values().length];
            try {
                iArr[EventType.USER_INITIALISE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EventType.USER_UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EventType.USER_ADD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public UserEventInterceptor(TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
    }

    @Override // com.taptap.sdk.db.interceptor.Interceptor
    public Object intercept(Interceptor.Chain<WrapEvent, Event> chain, Continuation<? super Event> continuation) {
        WrapEvent request = chain.getRequest();
        if (request == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        WrapEvent wrapEvent = request;
        int i = WhenMappings.$EnumSwitchMapping$0[wrapEvent.getEventType().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return buildEvent(wrapEvent.getEvent(), JSONObjectUtils.INSTANCE.toMap(wrapEvent.getEvent().getProperties()), wrapEvent.getUserId());
        }
        return chain.process(wrapEvent, continuation);
    }

    private final Event buildEvent(Event event, Map<String, ? extends Object> eventProperties, String userId) {
        HashMap map = new HashMap();
        Pair pair = TuplesKt.to("type", event.getType());
        map.put(pair.getFirst(), pair.getSecond());
        Pair pair2 = TuplesKt.to("client_id", this.options.getClientId());
        map.put(pair2.getFirst(), pair2.getSecond());
        Pair pair3 = TuplesKt.to(Common.System.USER_ID, userId);
        map.put(pair3.getFirst(), pair3.getSecond());
        HashMap map2 = new HashMap();
        map2.putAll(eventProperties);
        Pair pair4 = TuplesKt.to(Common.Predefined.PROPERTIES, map2);
        map.put(pair4.getFirst(), pair4.getSecond());
        return Event.copy$default(event, null, null, null, 0L, new JSONObject(map), 13, null);
    }
}

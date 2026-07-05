package com.taptap.sdk.db.interceptor;

import com.taptap.sdk.core.TapTapEvent;
import com.taptap.sdk.db.EventContext;
import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.model.EventType;
import com.taptap.sdk.db.data.model.WrapEvent;
import com.taptap.sdk.db.interceptor.Interceptor;
import com.taptap.sdk.db.utils.JSONObjectUtils;
import com.taptap.sdk.db.utils.MapUtils2;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: TrackEventInterceptor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JI\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J%\u0010\u0013\u001a\u00020\u00032\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016JF\u0010\u0017\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0018j\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f`\u00192\u0006\u0010\u001a\u001a\u00020\u000f2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/taptap/sdk/db/interceptor/TrackEventInterceptor;", "Lcom/taptap/sdk/db/interceptor/Interceptor;", "Lcom/taptap/sdk/db/data/model/WrapEvent;", "Lcom/taptap/sdk/db/data/model/Event;", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "buildNewEvent", "event", "eventProperties", "", "", "", "userId", "context", "Lcom/taptap/sdk/db/EventContext;", "isAutomatically", "", "(Lcom/taptap/sdk/db/data/model/Event;Ljava/util/Map;Ljava/lang/String;Lcom/taptap/sdk/db/EventContext;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "intercept", "chain", "Lcom/taptap/sdk/db/interceptor/Interceptor$Chain;", "(Lcom/taptap/sdk/db/interceptor/Interceptor$Chain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "provideUserProperties", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "eventContext", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TrackEventInterceptor implements Interceptor<WrapEvent, Event> {
    private final TapTapSdkOptions options;

    /* JADX INFO: compiled from: TrackEventInterceptor.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EventType.values().length];
            try {
                iArr[EventType.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.interceptor.TrackEventInterceptor$buildNewEvent$1, reason: invalid class name */
    /* JADX INFO: compiled from: TrackEventInterceptor.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.interceptor.TrackEventInterceptor", f = "TrackEventInterceptor.kt", i = {0, 0, 0, 0, 0, 0}, l = {61}, m = "buildNewEvent", n = {"this", "event", "eventProperties", "context", "sendProperties", "tapSdkProperties"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TrackEventInterceptor.this.buildNewEvent(null, null, null, null, false, this);
        }
    }

    public TrackEventInterceptor(TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
    }

    @Override // com.taptap.sdk.db.interceptor.Interceptor
    public Object intercept(Interceptor.Chain<WrapEvent, Event> chain, Continuation<? super Event> continuation) throws JSONException {
        WrapEvent request = chain.getRequest();
        if (request == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        WrapEvent wrapEvent = request;
        if (WhenMappings.$EnumSwitchMapping$0[wrapEvent.getEventType().ordinal()] == 1) {
            Event event = wrapEvent.getEvent();
            Map<String, Object> map = JSONObjectUtils.INSTANCE.toMap(wrapEvent.getEvent().getProperties());
            String userId = wrapEvent.getUserId();
            EventContext eventContext = wrapEvent.getEventContext();
            if (eventContext != null) {
                return buildNewEvent(event, map, userId, eventContext, wrapEvent.getIsAutomatically(), continuation);
            }
            throw new IllegalStateException("you must set EventContext by use TapDB#submitEvent method...".toString());
        }
        return chain.process(wrapEvent, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object buildNewEvent(com.taptap.sdk.db.data.model.Event r19, java.util.Map<java.lang.String, ? extends java.lang.Object> r20, java.lang.String r21, com.taptap.sdk.db.EventContext r22, boolean r23, kotlin.coroutines.Continuation<? super com.taptap.sdk.db.data.model.Event> r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.interceptor.TrackEventInterceptor.buildNewEvent(com.taptap.sdk.db.data.model.Event, java.util.Map, java.lang.String, com.taptap.sdk.db.EventContext, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Map<String, Object> provideUserProperties$provideDynamicProperties(TapTapEvent.TapEventDynamicProperties tapEventDynamicProperties) {
        JSONObject dynamicProperties;
        Map<String, Object> map;
        return (tapEventDynamicProperties == null || (dynamicProperties = tapEventDynamicProperties.getDynamicProperties()) == null || (map = JSONObjectUtils.INSTANCE.toMap(dynamicProperties)) == null) ? MapsKt.emptyMap() : map;
    }

    private final HashMap<String, Object> provideUserProperties(EventContext eventContext, Map<String, ? extends Object> eventProperties) {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map2 = map;
        map2.putAll(eventContext.getCustomExtras());
        map2.putAll(MapUtils2.filterRecursively$default(MapUtils2.INSTANCE, provideUserProperties$provideDynamicProperties(eventContext.getDynamicPropertiesGetter()), null, 1, null));
        map2.putAll(eventProperties);
        return map;
    }
}

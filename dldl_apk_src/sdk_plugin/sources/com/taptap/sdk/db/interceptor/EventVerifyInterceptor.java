package com.taptap.sdk.db.interceptor;

import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.model.WrapEvent;
import com.taptap.sdk.db.interceptor.Interceptor;
import com.taptap.sdk.db.utils.JSONObjectUtils;
import com.taptap.sdk.db.utils.MapUtils2;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: EventVerifyInterceptor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \t2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/db/interceptor/EventVerifyInterceptor;", "Lcom/taptap/sdk/db/interceptor/Interceptor;", "Lcom/taptap/sdk/db/data/model/WrapEvent;", "Lcom/taptap/sdk/db/data/model/Event;", "()V", "intercept", "chain", "Lcom/taptap/sdk/db/interceptor/Interceptor$Chain;", "(Lcom/taptap/sdk/db/interceptor/Interceptor$Chain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class EventVerifyInterceptor implements Interceptor<WrapEvent, Event> {
    public static final int EVENT_NAME_MAX_LENGTH = 256;
    public static final int PROPERTIES_MAX_LENGTH = 256;

    @Override // com.taptap.sdk.db.interceptor.Interceptor
    public Object intercept(Interceptor.Chain<WrapEvent, Event> chain, Continuation<? super Event> continuation) {
        WrapEvent request = chain.getRequest();
        if (request == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        WrapEvent wrapEvent = request;
        String name = wrapEvent.getEvent().getName();
        if (wrapEvent.getIsCustomEvent()) {
            String str = name;
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("Event name is null or empty");
            }
            if (name.length() > 256) {
                throw new IllegalStateException("EventName is too long, max length is 256");
            }
        }
        final Map mapFilterRecursively$default = MapUtils2.filterRecursively$default(MapUtils2.INSTANCE, JSONObjectUtils.INSTANCE.toMap(wrapEvent.getEvent().getProperties()), null, 1, null);
        return chain.process(wrapEvent.newBuilder(new Function1<WrapEvent.Builder, Unit>() { // from class: com.taptap.sdk.db.interceptor.EventVerifyInterceptor.intercept.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WrapEvent.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WrapEvent.Builder newBuilder) {
                Intrinsics.checkNotNullParameter(newBuilder, "$this$newBuilder");
                Event event = newBuilder.getEvent();
                if (event != null) {
                    final Map<String, Object> map = mapFilterRecursively$default;
                    newBuilder.setEvent(event.reduce(new Function1<Event, Event>() { // from class: com.taptap.sdk.db.interceptor.EventVerifyInterceptor.intercept.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Event invoke(Event reduce) {
                            Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
                            return Event.copy$default(reduce, null, null, null, 0L, new JSONObject(map), 15, null);
                        }
                    }));
                    return;
                }
                throw new IllegalStateException("Required value was null.".toString());
            }
        }), continuation);
    }
}

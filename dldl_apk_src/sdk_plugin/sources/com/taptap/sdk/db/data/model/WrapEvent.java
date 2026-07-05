package com.taptap.sdk.db.data.model;

import com.huya.berry.client.HuyaBerry;
import com.taptap.sdk.db.EventContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WrapEvent.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B;\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\u001f\u0010\u001a\u001a\u00020\u00002\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\u0002\b\u001eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006 "}, d2 = {"Lcom/taptap/sdk/db/data/model/WrapEvent;", "", "builder", "Lcom/taptap/sdk/db/data/model/WrapEvent$Builder;", "(Lcom/taptap/sdk/db/data/model/WrapEvent$Builder;)V", HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, "Lcom/taptap/sdk/db/data/model/EventType;", "event", "Lcom/taptap/sdk/db/data/model/Event;", "isAutomatically", "", "isCustomEvent", "userId", "", "eventContext", "Lcom/taptap/sdk/db/EventContext;", "(Lcom/taptap/sdk/db/data/model/EventType;Lcom/taptap/sdk/db/data/model/Event;ZZLjava/lang/String;Lcom/taptap/sdk/db/EventContext;)V", "getEvent", "()Lcom/taptap/sdk/db/data/model/Event;", "getEventContext", "()Lcom/taptap/sdk/db/EventContext;", "getEventType", "()Lcom/taptap/sdk/db/data/model/EventType;", "()Z", "getUserId", "()Ljava/lang/String;", "newBuilder", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "Builder", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class WrapEvent {
    private final Event event;
    private final EventContext eventContext;
    private final EventType eventType;
    private final boolean isAutomatically;
    private final boolean isCustomEvent;
    private final String userId;

    public /* synthetic */ WrapEvent(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private WrapEvent(EventType eventType, Event event, boolean z, boolean z2, String str, EventContext eventContext) {
        this.eventType = eventType;
        this.event = event;
        this.isAutomatically = z;
        this.isCustomEvent = z2;
        this.userId = str;
        this.eventContext = eventContext;
    }

    public final EventType getEventType() {
        return this.eventType;
    }

    public final Event getEvent() {
        return this.event;
    }

    /* JADX INFO: renamed from: isAutomatically, reason: from getter */
    public final boolean getIsAutomatically() {
        return this.isAutomatically;
    }

    /* JADX INFO: renamed from: isCustomEvent, reason: from getter */
    public final boolean getIsCustomEvent() {
        return this.isCustomEvent;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final EventContext getEventContext() {
        return this.eventContext;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private WrapEvent(Builder builder) {
        EventType eventType = builder.getEventType();
        if (eventType == null) {
            throw new IllegalArgumentException("eventType must be set".toString());
        }
        Event event = builder.getEvent();
        if (event != null) {
            this(eventType, event, builder.getIsAutomatically(), builder.getIsCustomEvent(), builder.getUserId(), builder.getEventContext());
            return;
        }
        throw new IllegalArgumentException("event must be set".toString());
    }

    public final WrapEvent newBuilder(Function1<? super Builder, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        Builder eventContext = new Builder().setEventType(this.eventType).setEvent(this.event).isAutomatically(this.isAutomatically).isCustomEvent(this.isCustomEvent).setUserId(this.userId).setEventContext(this.eventContext);
        block.invoke(eventContext);
        return eventContext.build();
    }

    /* JADX INFO: compiled from: WrapEvent.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0010J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u001d\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fJ\u0010\u0010\u001f\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\f@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\"\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0003\u001a\u0004\u0018\u00010\u0016@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006 "}, d2 = {"Lcom/taptap/sdk/db/data/model/WrapEvent$Builder;", "", "()V", "<set-?>", "Lcom/taptap/sdk/db/data/model/Event;", "event", "getEvent$tap_db_release", "()Lcom/taptap/sdk/db/data/model/Event;", "Lcom/taptap/sdk/db/EventContext;", "eventContext", "getEventContext$tap_db_release", "()Lcom/taptap/sdk/db/EventContext;", "Lcom/taptap/sdk/db/data/model/EventType;", HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE, "getEventType$tap_db_release", "()Lcom/taptap/sdk/db/data/model/EventType;", "", "isAutomatically", "isAutomatically$tap_db_release", "()Z", "isCustomEvent", "isCustomEvent$tap_db_release", "", "userId", "getUserId$tap_db_release", "()Ljava/lang/String;", "build", "Lcom/taptap/sdk/db/data/model/WrapEvent;", "setEvent", "setEventContext", "setEventType", "setUserId", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private Event event;
        private EventContext eventContext;
        private EventType eventType;
        private boolean isAutomatically;
        private boolean isCustomEvent;
        private String userId;

        /* JADX INFO: renamed from: getEventType$tap_db_release, reason: from getter */
        public final EventType getEventType() {
            return this.eventType;
        }

        /* JADX INFO: renamed from: getEvent$tap_db_release, reason: from getter */
        public final Event getEvent() {
            return this.event;
        }

        /* JADX INFO: renamed from: isAutomatically$tap_db_release, reason: from getter */
        public final boolean getIsAutomatically() {
            return this.isAutomatically;
        }

        /* JADX INFO: renamed from: getEventContext$tap_db_release, reason: from getter */
        public final EventContext getEventContext() {
            return this.eventContext;
        }

        /* JADX INFO: renamed from: getUserId$tap_db_release, reason: from getter */
        public final String getUserId() {
            return this.userId;
        }

        /* JADX INFO: renamed from: isCustomEvent$tap_db_release, reason: from getter */
        public final boolean getIsCustomEvent() {
            return this.isCustomEvent;
        }

        public final Builder setEventType(EventType eventType) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            Builder builder = this;
            builder.eventType = eventType;
            return builder;
        }

        public final Builder setEvent(Event event) {
            Intrinsics.checkNotNullParameter(event, "event");
            Builder builder = this;
            builder.event = event;
            return builder;
        }

        public final Builder isAutomatically(boolean isAutomatically) {
            Builder builder = this;
            builder.isAutomatically = isAutomatically;
            return builder;
        }

        public final Builder isCustomEvent(boolean isCustomEvent) {
            Builder builder = this;
            builder.isCustomEvent = isCustomEvent;
            return builder;
        }

        public final Builder setUserId(String userId) {
            Builder builder = this;
            builder.userId = userId;
            return builder;
        }

        public final Builder setEventContext(EventContext eventContext) {
            Builder builder = this;
            builder.eventContext = eventContext;
            return builder;
        }

        public final WrapEvent build() {
            return new WrapEvent(this, null);
        }
    }
}

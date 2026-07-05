package com.taptap.sdk.db.data.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventTrigger.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/db/data/model/EventTrigger;", "", "()V", "NewEvent", "TimerTick", "Lcom/taptap/sdk/db/data/model/EventTrigger$NewEvent;", "Lcom/taptap/sdk/db/data/model/EventTrigger$TimerTick;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class EventTrigger {
    public /* synthetic */ EventTrigger(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private EventTrigger() {
    }

    /* JADX INFO: compiled from: EventTrigger.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/db/data/model/EventTrigger$NewEvent;", "Lcom/taptap/sdk/db/data/model/EventTrigger;", "event", "Lcom/taptap/sdk/db/data/model/Event;", "(Lcom/taptap/sdk/db/data/model/Event;)V", "getEvent", "()Lcom/taptap/sdk/db/data/model/Event;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class NewEvent extends EventTrigger {
        private final Event event;

        public static /* synthetic */ NewEvent copy$default(NewEvent newEvent, Event event, int i, Object obj) {
            if ((i & 1) != 0) {
                event = newEvent.event;
            }
            return newEvent.copy(event);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Event getEvent() {
            return this.event;
        }

        public final NewEvent copy(Event event) {
            Intrinsics.checkNotNullParameter(event, "event");
            return new NewEvent(event);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NewEvent) && Intrinsics.areEqual(this.event, ((NewEvent) other).event);
        }

        public int hashCode() {
            return this.event.hashCode();
        }

        public String toString() {
            return "NewEvent(event=" + this.event + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NewEvent(Event event) {
            super(null);
            Intrinsics.checkNotNullParameter(event, "event");
            this.event = event;
        }

        public final Event getEvent() {
            return this.event;
        }
    }

    /* JADX INFO: compiled from: EventTrigger.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/taptap/sdk/db/data/model/EventTrigger$TimerTick;", "Lcom/taptap/sdk/db/data/model/EventTrigger;", "()V", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class TimerTick extends EventTrigger {
        public static final TimerTick INSTANCE = new TimerTick();

        private TimerTick() {
            super(null);
        }
    }
}

package com.taptap.sdk.db.event;

import com.sqwan.liveshow.huya.SqR;
import com.taptap.sdk.db.TapDB;
import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.model.EventType;
import com.taptap.sdk.db.data.model.WrapEvent;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: DeviceUpdateEvent.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B%\b\u0002\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/db/event/DeviceUpdateEvent;", "", "builder", "Lcom/taptap/sdk/db/event/DeviceUpdateEvent$Builder;", "(Lcom/taptap/sdk/db/event/DeviceUpdateEvent$Builder;)V", Common.Predefined.PROPERTIES, "", "", "isAutomatically", "", "(Ljava/util/Map;Z)V", SqR.string.submit, "", "Builder", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DeviceUpdateEvent {
    private final boolean isAutomatically;
    private final Map<String, Object> properties;

    public /* synthetic */ DeviceUpdateEvent(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private DeviceUpdateEvent(Map<String, ? extends Object> map, boolean z) {
        this.properties = map;
        this.isAutomatically = z;
    }

    private DeviceUpdateEvent(Builder builder) {
        this((Map<String, ? extends Object>) builder.getProperties(), builder.getIsAutomatically());
    }

    public final void submit() {
        TapDB companion = TapDB.INSTANCE.getInstance();
        WrapEvent.Builder builder = new WrapEvent.Builder();
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        companion.submitEvent(builder.setEvent(new Event(string, null, EventType.DEVICE_UPDATE.getValue(), System.currentTimeMillis(), new JSONObject(this.properties))).setEventType(EventType.DEVICE_UPDATE).isAutomatically(this.isAutomatically).build());
    }

    /* JADX INFO: compiled from: DeviceUpdateEvent.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0007R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/db/event/DeviceUpdateEvent$Builder;", "", Common.Predefined.PROPERTIES, "", "", "(Ljava/util/Map;)V", "<set-?>", "", "isAutomatically", "isAutomatically$tap_db_release", "()Z", "getProperties", "()Ljava/util/Map;", "build", "Lcom/taptap/sdk/db/event/DeviceUpdateEvent;", "value", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private boolean isAutomatically;
        private final Map<String, Object> properties;

        public Builder(Map<String, ? extends Object> properties) {
            Intrinsics.checkNotNullParameter(properties, "properties");
            this.properties = properties;
        }

        public final Map<String, Object> getProperties() {
            return this.properties;
        }

        /* JADX INFO: renamed from: isAutomatically$tap_db_release, reason: from getter */
        public final boolean getIsAutomatically() {
            return this.isAutomatically;
        }

        public final Builder isAutomatically(boolean value) {
            this.isAutomatically = value;
            return this;
        }

        public final DeviceUpdateEvent build() {
            return new DeviceUpdateEvent(this, (DefaultConstructorMarker) null);
        }
    }
}

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
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: UserPlayGameEvent.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B/\b\u0002\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/db/event/UserPlayGameEvent;", "", "builder", "Lcom/taptap/sdk/db/event/UserPlayGameEvent$Builder;", "(Lcom/taptap/sdk/db/event/UserPlayGameEvent$Builder;)V", Common.Predefined.PROPERTIES, "", "", "isAutomatically", "", "userId", "(Ljava/util/Map;ZLjava/lang/String;)V", SqR.string.submit, "", "Builder", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class UserPlayGameEvent {
    private final boolean isAutomatically;
    private final Map<String, Object> properties;
    private final String userId;

    public /* synthetic */ UserPlayGameEvent(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private UserPlayGameEvent(Map<String, ? extends Object> map, boolean z, String str) {
        this.properties = map;
        this.isAutomatically = z;
        this.userId = str;
    }

    private UserPlayGameEvent(Builder builder) {
        this(MapsKt.toMap(builder.getProperties()), builder.getIsAutomatically(), builder.getUserId());
    }

    public final void submit() {
        TapDB companion = TapDB.INSTANCE.getInstance();
        WrapEvent.Builder builder = new WrapEvent.Builder();
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        companion.submitEvent(builder.setEvent(new Event(string, "play_game", EventType.TRACK.getValue(), System.currentTimeMillis(), new JSONObject(this.properties))).setEventType(EventType.TRACK).isAutomatically(this.isAutomatically).setUserId(this.userId).build());
    }

    /* JADX INFO: compiled from: UserPlayGameEvent.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0007J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0004R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/taptap/sdk/db/event/UserPlayGameEvent$Builder;", "", Common.Predefined.PROPERTIES, "", "", "(Ljava/util/Map;)V", "<set-?>", "", "isAutomatically", "isAutomatically$tap_db_release", "()Z", "getProperties", "()Ljava/util/Map;", "userId", "getUserId$tap_db_release", "()Ljava/lang/String;", "build", "Lcom/taptap/sdk/db/event/UserPlayGameEvent;", "value", "setUserId", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private boolean isAutomatically;
        private final Map<String, Object> properties;
        private String userId;

        public Builder(Map<String, ? extends Object> properties) {
            Intrinsics.checkNotNullParameter(properties, "properties");
            this.properties = properties;
        }

        public final Map<String, Object> getProperties() {
            return this.properties;
        }

        /* JADX INFO: renamed from: getUserId$tap_db_release, reason: from getter */
        public final String getUserId() {
            return this.userId;
        }

        /* JADX INFO: renamed from: isAutomatically$tap_db_release, reason: from getter */
        public final boolean getIsAutomatically() {
            return this.isAutomatically;
        }

        public final Builder isAutomatically(boolean value) {
            Builder builder = this;
            builder.isAutomatically = value;
            return builder;
        }

        public final Builder setUserId(String userId) {
            Intrinsics.checkNotNullParameter(userId, "userId");
            Builder builder = this;
            builder.userId = userId;
            return builder;
        }

        public final UserPlayGameEvent build() {
            return new UserPlayGameEvent(this, null);
        }
    }
}

package com.taptap.sdk.db.biz.gameplay;

import android.content.Context;
import com.taptap.sdk.db.biz.gameplay.tracker.GameDurationTracker;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameDurationService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0015B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000f\u001a\u00020\u0000J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/taptap/sdk/db/biz/gameplay/GameDurationService;", "", "builder", "Lcom/taptap/sdk/db/biz/gameplay/GameDurationService$Builder;", "(Lcom/taptap/sdk/db/biz/gameplay/GameDurationService$Builder;)V", "tracker", "Lcom/taptap/sdk/db/biz/gameplay/tracker/GameDurationTracker;", "(Lcom/taptap/sdk/db/biz/gameplay/tracker/GameDurationTracker;)V", "value", "", "userStartTimeAtInMillis", "getUserStartTimeAtInMillis", "()J", "setUserStartTimeAtInMillis", "(J)V", "initialize", "submitOldUserGameDuration", "", "oldUserId", "", "newUserId", "Builder", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GameDurationService {
    private final GameDurationTracker tracker;

    public /* synthetic */ GameDurationService(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private GameDurationService(GameDurationTracker gameDurationTracker) {
        this.tracker = gameDurationTracker;
    }

    public final long getUserStartTimeAtInMillis() {
        return this.tracker.getUserStartTimeAtInMillis();
    }

    public final void setUserStartTimeAtInMillis(long j) {
        this.tracker.setUserStartTimeAtInMillis(j);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private GameDurationService(Builder builder) {
        GameDurationTracker tracker = builder.getTracker();
        if (tracker != null) {
            this(tracker);
            return;
        }
        throw new IllegalStateException("you must call setTracker() to set a tracker first".toString());
    }

    public final GameDurationService initialize() {
        GameDurationService gameDurationService = this;
        gameDurationService.tracker.initialize();
        return gameDurationService;
    }

    public final void submitOldUserGameDuration(String oldUserId, String newUserId) {
        Intrinsics.checkNotNullParameter(oldUserId, "oldUserId");
        this.tracker.submitOldUserGameDurationEvent(oldUserId, newUserId);
    }

    /* JADX INFO: compiled from: GameDurationService.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/db/biz/gameplay/GameDurationService$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "<set-?>", "Lcom/taptap/sdk/db/biz/gameplay/tracker/GameDurationTracker;", "tracker", "getTracker$tap_db_release", "()Lcom/taptap/sdk/db/biz/gameplay/tracker/GameDurationTracker;", "build", "Lcom/taptap/sdk/db/biz/gameplay/GameDurationService;", "setTracker", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private final Context context;
        private GameDurationTracker tracker;

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        public final Context getContext() {
            return this.context;
        }

        /* JADX INFO: renamed from: getTracker$tap_db_release, reason: from getter */
        public final GameDurationTracker getTracker() {
            return this.tracker;
        }

        public final Builder setTracker(GameDurationTracker tracker) {
            Intrinsics.checkNotNullParameter(tracker, "tracker");
            Builder builder = this;
            builder.tracker = tracker;
            return builder;
        }

        public final GameDurationService build() {
            return new GameDurationService(this, null);
        }
    }
}

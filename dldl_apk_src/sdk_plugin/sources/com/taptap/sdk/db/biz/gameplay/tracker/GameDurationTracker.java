package com.taptap.sdk.db.biz.gameplay.tracker;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: GameDurationTracker.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\b\u0010\u000b\u001a\u00020\tH&J#\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000eH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/db/biz/gameplay/tracker/GameDurationTracker;", "", "userStartTimeAtInMillis", "", "getUserStartTimeAtInMillis", "()J", "setUserStartTimeAtInMillis", "(J)V", "initialize", "", "startTracking", "stopTracking", "submitGameDurationEvent", "userId", "", "onlyUserDuration", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitOldUserGameDurationEvent", "oldUserId", "newUserId", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface GameDurationTracker {
    long getUserStartTimeAtInMillis();

    void initialize();

    void setUserStartTimeAtInMillis(long j);

    void startTracking();

    void stopTracking();

    Object submitGameDurationEvent(String str, boolean z, Continuation<? super Unit> continuation);

    void submitOldUserGameDurationEvent(String oldUserId, String newUserId);
}

package com.taptap.sdk.db.biz.gameplay.storage;

import com.taptap.sdk.db.data.model.GameDuration;
import kotlin.Metadata;

/* JADX INFO: compiled from: GameDurationStorage.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u000b\u001a\u00020\u0005H&J\b\u0010\f\u001a\u00020\u0005H&J\u0012\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/db/biz/gameplay/storage/GameDurationStorage;", "", "getGameDuration", "Lcom/taptap/sdk/db/data/model/GameDuration;", "incrementDeviceDuration", "", "duration", "", "incrementUserDuration", "userId", "", "resetDeviceDuration", "resetUserDuration", "sync", "force", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface GameDurationStorage {
    GameDuration getGameDuration();

    void incrementDeviceDuration(long duration);

    void incrementUserDuration(String userId, long duration);

    void resetDeviceDuration();

    void resetUserDuration();

    void sync(boolean force);

    /* JADX INFO: compiled from: GameDurationStorage.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void sync$default(GameDurationStorage gameDurationStorage, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sync");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            gameDurationStorage.sync(z);
        }
    }
}

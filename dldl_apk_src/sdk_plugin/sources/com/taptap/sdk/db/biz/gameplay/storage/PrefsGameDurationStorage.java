package com.taptap.sdk.db.biz.gameplay.storage;

import android.content.SharedPreferences;
import android.util.Log;
import com.taptap.sdk.base.utils.prefs.SharedPreferenceDelegate;
import com.taptap.sdk.db.data.model.GameDuration;
import com.taptap.sdk.db.utils.ListUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: PrefsGameDurationStorage.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0013H\u0016J\u0018\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/taptap/sdk/db/biz/gameplay/storage/PrefsGameDurationStorage;", "Lcom/taptap/sdk/db/biz/gameplay/storage/GameDurationStorage;", "prefs", "Landroid/content/SharedPreferences;", "json", "Lkotlinx/serialization/json/Json;", "(Landroid/content/SharedPreferences;Lkotlinx/serialization/json/Json;)V", "gameDuration", "Lcom/taptap/sdk/db/data/model/GameDuration;", "<set-?>", "", "jsonStr", "getJsonStr", "()Ljava/lang/String;", "setJsonStr", "(Ljava/lang/String;)V", "jsonStr$delegate", "Lcom/taptap/sdk/base/utils/prefs/SharedPreferenceDelegate;", "lastSyncTimeAtInMillis", "", "getGameDuration", "incrementDeviceDuration", "", "duration", "incrementUserDuration", "userId", "loadGameDurationFromDisk", "resetDeviceDuration", "resetUserDuration", "sync", "force", "", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PrefsGameDurationStorage implements GameDurationStorage {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PrefsGameDurationStorage.class, "jsonStr", "getJsonStr()Ljava/lang/String;", 0))};
    public static final String SP_FILE_NAME = "com.taptap.sdk.GameDurationStorage";
    private static final String SP_KEY_GAME_DURATION = "game_duration_data";
    private static final long SYNC_INTERVAL = 30000;
    private static final String TAG = "GameDurationStorage";
    private GameDuration gameDuration;
    private final Json json;

    /* JADX INFO: renamed from: jsonStr$delegate, reason: from kotlin metadata */
    private final SharedPreferenceDelegate jsonStr;
    private long lastSyncTimeAtInMillis;

    public PrefsGameDurationStorage(SharedPreferences prefs, Json json) {
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
        this.jsonStr = new SharedPreferenceDelegate(prefs, SP_KEY_GAME_DURATION, "");
        this.gameDuration = loadGameDurationFromDisk();
    }

    private final String getJsonStr() {
        return (String) this.jsonStr.getValue(this, $$delegatedProperties[0]);
    }

    private final void setJsonStr(String str) {
        this.jsonStr.setValue(this, $$delegatedProperties[0], str);
    }

    @Override // com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage
    public GameDuration getGameDuration() {
        return this.gameDuration;
    }

    @Override // com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage
    public void incrementUserDuration(final String userId, final long duration) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        this.gameDuration = this.gameDuration.reduce(new Function1<GameDuration, GameDuration>() { // from class: com.taptap.sdk.db.biz.gameplay.storage.PrefsGameDurationStorage.incrementUserDuration.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final GameDuration invoke(GameDuration reduce) {
                Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
                ListUtils listUtils = ListUtils.INSTANCE;
                List<GameDuration.User> users = reduce.getUsers();
                final String str = userId;
                Function1<GameDuration.User, Boolean> function1 = new Function1<GameDuration.User, Boolean>() { // from class: com.taptap.sdk.db.biz.gameplay.storage.PrefsGameDurationStorage.incrementUserDuration.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(GameDuration.User element) {
                        Intrinsics.checkNotNullParameter(element, "element");
                        return Boolean.valueOf(Intrinsics.areEqual(element.getId(), str));
                    }
                };
                GameDuration.User user = new GameDuration.User(userId, duration);
                final long j = duration;
                return GameDuration.copy$default(reduce, 0L, listUtils.updateOrInsert(users, function1, user, new Function1<GameDuration.User, GameDuration.User>() { // from class: com.taptap.sdk.db.biz.gameplay.storage.PrefsGameDurationStorage.incrementUserDuration.1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final GameDuration.User invoke(GameDuration.User origin) {
                        Intrinsics.checkNotNullParameter(origin, "origin");
                        return GameDuration.User.copy$default(origin, null, origin.getDuration() + j, 1, null);
                    }
                }), 1, null);
            }
        });
    }

    @Override // com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage
    public void incrementDeviceDuration(final long duration) {
        this.gameDuration = this.gameDuration.reduce(new Function1<GameDuration, GameDuration>() { // from class: com.taptap.sdk.db.biz.gameplay.storage.PrefsGameDurationStorage.incrementDeviceDuration.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final GameDuration invoke(GameDuration reduce) {
                Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
                return GameDuration.copy$default(reduce, reduce.getDeviceDuration() + duration, null, 2, null);
            }
        });
    }

    @Override // com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage
    public void sync(boolean force) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (force || jCurrentTimeMillis - this.lastSyncTimeAtInMillis > SYNC_INTERVAL) {
            try {
                Json json = this.json;
                GameDuration gameDuration = this.gameDuration;
                KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(GameDuration.class));
                Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                setJsonStr(json.encodeToString(kSerializerSerializer, gameDuration));
                this.lastSyncTimeAtInMillis = jCurrentTimeMillis;
                Log.d(TAG, "sync game duration: " + this.gameDuration);
            } catch (Exception e) {
                Log.e(TAG, "failed to serialize game duration data: " + e.getMessage());
            }
        }
    }

    @Override // com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage
    public void resetUserDuration() {
        Log.d(TAG, "resetting user durations");
        this.gameDuration = this.gameDuration.reduce(new Function1<GameDuration, GameDuration>() { // from class: com.taptap.sdk.db.biz.gameplay.storage.PrefsGameDurationStorage.resetUserDuration.1
            @Override // kotlin.jvm.functions.Function1
            public final GameDuration invoke(GameDuration reduce) {
                Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
                return GameDuration.copy$default(reduce, 0L, CollectionsKt.emptyList(), 1, null);
            }
        });
        sync(true);
    }

    @Override // com.taptap.sdk.db.biz.gameplay.storage.GameDurationStorage
    public void resetDeviceDuration() {
        Log.d(TAG, "resetting device duration");
        this.gameDuration = this.gameDuration.reduce(new Function1<GameDuration, GameDuration>() { // from class: com.taptap.sdk.db.biz.gameplay.storage.PrefsGameDurationStorage.resetDeviceDuration.1
            @Override // kotlin.jvm.functions.Function1
            public final GameDuration invoke(GameDuration reduce) {
                Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
                return GameDuration.copy$default(reduce, 0L, null, 2, null);
            }
        });
        sync(true);
    }

    private final GameDuration loadGameDurationFromDisk() {
        Object objM52constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            PrefsGameDurationStorage prefsGameDurationStorage = this;
            Json json = prefsGameDurationStorage.json;
            String jsonStr = prefsGameDurationStorage.getJsonStr();
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(GameDuration.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            objM52constructorimpl = Result.m52constructorimpl((GameDuration) json.decodeFromString(kSerializerSerializer, jsonStr));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM52constructorimpl = Result.m52constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m58isFailureimpl(objM52constructorimpl)) {
            objM52constructorimpl = null;
        }
        GameDuration gameDuration = (GameDuration) objM52constructorimpl;
        return gameDuration == null ? new GameDuration(0L, (List) null, 3, (DefaultConstructorMarker) null) : gameDuration;
    }
}

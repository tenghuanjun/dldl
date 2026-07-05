package com.taptap.sdk.db.data.model;

import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.constants.SqConstants;
import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: GameDuration.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0003()*B5\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u001f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J#\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\u001f\u0010\u001b\u001a\u00020\u00002\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u001d¢\u0006\u0002\b\u001eJ\t\u0010\u001f\u001a\u00020 HÖ\u0001J!\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'HÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006+"}, d2 = {"Lcom/taptap/sdk/db/data/model/GameDuration;", "", "seen1", "", "deviceDuration", "", "users", "", "Lcom/taptap/sdk/db/data/model/GameDuration$User;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(JLjava/util/List;)V", "getDeviceDuration$annotations", "()V", "getDeviceDuration", "()J", "getUsers$annotations", "getUsers", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "reduce", "reducer", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "User", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class GameDuration {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long deviceDuration;
    private final List<User> users;

    public GameDuration() {
        this(0L, (List) null, 3, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GameDuration copy$default(GameDuration gameDuration, long j, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            j = gameDuration.deviceDuration;
        }
        if ((i & 2) != 0) {
            list = gameDuration.users;
        }
        return gameDuration.copy(j, list);
    }

    @SerialName("deviceDuration")
    public static /* synthetic */ void getDeviceDuration$annotations() {
    }

    @SerialName("userDurations")
    public static /* synthetic */ void getUsers$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getDeviceDuration() {
        return this.deviceDuration;
    }

    public final List<User> component2() {
        return this.users;
    }

    public final GameDuration copy(long deviceDuration, List<User> users) {
        Intrinsics.checkNotNullParameter(users, "users");
        return new GameDuration(deviceDuration, users);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GameDuration)) {
            return false;
        }
        GameDuration gameDuration = (GameDuration) other;
        return this.deviceDuration == gameDuration.deviceDuration && Intrinsics.areEqual(this.users, gameDuration.users);
    }

    public int hashCode() {
        return (C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.deviceDuration) * 31) + this.users.hashCode();
    }

    public String toString() {
        return "GameDuration(deviceDuration=" + this.deviceDuration + ", users=" + this.users + ')';
    }

    /* JADX INFO: compiled from: GameDuration.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/db/data/model/GameDuration$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/db/data/model/GameDuration;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<GameDuration> serializer() {
            return GameDuration$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ GameDuration(int i, @SerialName("deviceDuration") long j, @SerialName("userDurations") List list, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, GameDuration$$serializer.INSTANCE.getDescriptor());
        }
        this.deviceDuration = (i & 1) == 0 ? 0L : j;
        if ((i & 2) == 0) {
            this.users = CollectionsKt.emptyList();
        } else {
            this.users = list;
        }
    }

    public GameDuration(long j, List<User> users) {
        Intrinsics.checkNotNullParameter(users, "users");
        this.deviceDuration = j;
        this.users = users;
    }

    @JvmStatic
    public static final void write$Self(GameDuration self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.deviceDuration != 0) {
            output.encodeLongElement(serialDesc, 0, self.deviceDuration);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.users, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 1, new ArrayListSerializer(GameDuration$User$$serializer.INSTANCE), self.users);
        }
    }

    public final long getDeviceDuration() {
        return this.deviceDuration;
    }

    public /* synthetic */ GameDuration(long j, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<User> getUsers() {
        return this.users;
    }

    public final GameDuration reduce(Function1<? super GameDuration, GameDuration> reducer) {
        Intrinsics.checkNotNullParameter(reducer, "reducer");
        return reducer.invoke(copy$default(this, 0L, null, 3, null));
    }

    /* JADX INFO: compiled from: GameDuration.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B/\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J!\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!HÇ\u0001R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006$"}, d2 = {"Lcom/taptap/sdk/db/data/model/GameDuration$User;", "", "seen1", "", SqTrackCommonKey.id, "", "duration", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;JLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;J)V", "getDuration$annotations", "()V", "getDuration", "()J", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    @Serializable
    public static final /* data */ class User {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final long duration;
        private final String id;

        public static /* synthetic */ User copy$default(User user, String str, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = user.id;
            }
            if ((i & 2) != 0) {
                j = user.duration;
            }
            return user.copy(str, j);
        }

        @SerialName("duration")
        public static /* synthetic */ void getDuration$annotations() {
        }

        @SerialName("userId")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getDuration() {
            return this.duration;
        }

        public final User copy(String id, long duration) {
            Intrinsics.checkNotNullParameter(id, "id");
            return new User(id, duration);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof User)) {
                return false;
            }
            User user = (User) other;
            return Intrinsics.areEqual(this.id, user.id) && this.duration == user.duration;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.duration);
        }

        public String toString() {
            return "User(id=" + this.id + ", duration=" + this.duration + ')';
        }

        /* JADX INFO: compiled from: GameDuration.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/db/data/model/GameDuration$User$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/db/data/model/GameDuration$User;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<User> serializer() {
                return GameDuration$User$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ User(int i, @SerialName("userId") String str, @SerialName("duration") long j, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, GameDuration$User$$serializer.INSTANCE.getDescriptor());
            }
            this.id = str;
            this.duration = j;
        }

        public User(String id, long j) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
            this.duration = j;
        }

        @JvmStatic
        public static final void write$Self(User self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            output.encodeStringElement(serialDesc, 0, self.id);
            output.encodeLongElement(serialDesc, 1, self.duration);
        }

        public final String getId() {
            return this.id;
        }

        public final long getDuration() {
            return this.duration;
        }
    }
}

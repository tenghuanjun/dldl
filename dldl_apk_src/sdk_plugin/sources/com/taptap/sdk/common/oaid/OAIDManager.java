package com.taptap.sdk.common.oaid;

import com.taptap.sdk.common.oaid.repository.OAIDRepository;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OAIDManager.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u000e2\u00020\u0001:\u0002\r\u000eB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0013\u0010\n\u001a\u0004\u0018\u00010\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/common/oaid/OAIDManager;", "", "builder", "Lcom/taptap/sdk/common/oaid/OAIDManager$Builder;", "(Lcom/taptap/sdk/common/oaid/OAIDManager$Builder;)V", "repository", "Lcom/taptap/sdk/common/oaid/repository/OAIDRepository;", "(Lcom/taptap/sdk/common/oaid/repository/OAIDRepository;)V", "getCurrentOAID", "", "getOAID", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "Builder", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class OAIDManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile OAIDManager INSTANCE;
    private final OAIDRepository repository;

    public /* synthetic */ OAIDManager(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private OAIDManager(OAIDRepository oAIDRepository) {
        this.repository = oAIDRepository;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private OAIDManager(Builder builder) {
        OAIDRepository repository = builder.getRepository();
        if (repository != null) {
            this(repository);
            return;
        }
        throw new IllegalStateException("you should set oaid repository".toString());
    }

    public final OAIDManager initialize() {
        OAIDManager oAIDManager = this;
        try {
            oAIDManager.repository.initialize();
        } catch (Exception unused) {
        }
        return oAIDManager;
    }

    public final Object getOAID(Continuation<? super String> continuation) {
        return this.repository.getOAID(continuation);
    }

    public final String getCurrentOAID() {
        return this.repository.getCurrentOAID();
    }

    /* JADX INFO: compiled from: OAIDManager.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/common/oaid/OAIDManager$Builder;", "", "()V", "<set-?>", "Lcom/taptap/sdk/common/oaid/repository/OAIDRepository;", "repository", "getRepository$tap_common_release", "()Lcom/taptap/sdk/common/oaid/repository/OAIDRepository;", "build", "Lcom/taptap/sdk/common/oaid/OAIDManager;", "setRepository", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private OAIDRepository repository;

        /* JADX INFO: renamed from: getRepository$tap_common_release, reason: from getter */
        public final OAIDRepository getRepository() {
            return this.repository;
        }

        public final Builder setRepository(OAIDRepository repository) {
            Intrinsics.checkNotNullParameter(repository, "repository");
            Builder builder = this;
            builder.repository = repository;
            return builder;
        }

        public final OAIDManager build() {
            return new OAIDManager(this, null);
        }
    }

    /* JADX INFO: compiled from: OAIDManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/common/oaid/OAIDManager$Companion;", "", "()V", "INSTANCE", "Lcom/taptap/sdk/common/oaid/OAIDManager;", "attach", "instance", "getInstance", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OAIDManager attach(OAIDManager instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            OAIDManager oAIDManager = OAIDManager.INSTANCE;
            if (oAIDManager != null) {
                return oAIDManager;
            }
            synchronized (this) {
                OAIDManager oAIDManager2 = OAIDManager.INSTANCE;
                if (oAIDManager2 == null) {
                    Companion companion = OAIDManager.INSTANCE;
                    OAIDManager.INSTANCE = instance;
                } else {
                    instance = oAIDManager2;
                }
            }
            return instance;
        }

        public final OAIDManager getInstance() {
            OAIDManager oAIDManager;
            synchronized (this) {
                oAIDManager = OAIDManager.INSTANCE;
                if (oAIDManager == null) {
                    throw new IllegalStateException("you must call attach() before using getInstance()".toString());
                }
            }
            return oAIDManager;
        }
    }
}

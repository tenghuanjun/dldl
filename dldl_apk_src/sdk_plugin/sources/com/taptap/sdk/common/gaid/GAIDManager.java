package com.taptap.sdk.common.gaid;

import com.taptap.sdk.common.gaid.data.model.GAID;
import com.taptap.sdk.common.gaid.repository.GAIDRepository;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GAIDManager.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000e2\u00020\u0001:\u0002\r\u000eB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0013\u0010\n\u001a\u0004\u0018\u00010\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/common/gaid/GAIDManager;", "", "builder", "Lcom/taptap/sdk/common/gaid/GAIDManager$Builder;", "(Lcom/taptap/sdk/common/gaid/GAIDManager$Builder;)V", "repository", "Lcom/taptap/sdk/common/gaid/repository/GAIDRepository;", "(Lcom/taptap/sdk/common/gaid/repository/GAIDRepository;)V", "getCurrentGAID", "Lcom/taptap/sdk/common/gaid/data/model/GAID;", "getGAID", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "Builder", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class GAIDManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile GAIDManager INSTANCE;
    private final GAIDRepository repository;

    public /* synthetic */ GAIDManager(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private GAIDManager(GAIDRepository gAIDRepository) {
        this.repository = gAIDRepository;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private GAIDManager(Builder builder) {
        GAIDRepository repository = builder.getRepository();
        if (repository != null) {
            this(repository);
            return;
        }
        throw new IllegalStateException("you should set GAID repository first".toString());
    }

    public final Object getGAID(Continuation<? super GAID> continuation) {
        return this.repository.getGAID(continuation);
    }

    public final GAID getCurrentGAID() {
        return this.repository.getCurrentGAID();
    }

    public final GAIDManager initialize() {
        GAIDManager gAIDManager = this;
        gAIDManager.repository.initialize();
        return gAIDManager;
    }

    /* JADX INFO: compiled from: GAIDManager.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/common/gaid/GAIDManager$Builder;", "", "()V", "<set-?>", "Lcom/taptap/sdk/common/gaid/repository/GAIDRepository;", "repository", "getRepository$tap_common_release", "()Lcom/taptap/sdk/common/gaid/repository/GAIDRepository;", "build", "Lcom/taptap/sdk/common/gaid/GAIDManager;", "setRepository", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private GAIDRepository repository;

        /* JADX INFO: renamed from: getRepository$tap_common_release, reason: from getter */
        public final GAIDRepository getRepository() {
            return this.repository;
        }

        public final Builder setRepository(GAIDRepository repository) {
            Intrinsics.checkNotNullParameter(repository, "repository");
            Builder builder = this;
            builder.repository = repository;
            return builder;
        }

        public final GAIDManager build() {
            return new GAIDManager(this, null);
        }
    }

    /* JADX INFO: compiled from: GAIDManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/common/gaid/GAIDManager$Companion;", "", "()V", "INSTANCE", "Lcom/taptap/sdk/common/gaid/GAIDManager;", "attach", "instance", "getInstance", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GAIDManager attach(GAIDManager instance) {
            Intrinsics.checkNotNullParameter(instance, "instance");
            GAIDManager gAIDManager = GAIDManager.INSTANCE;
            if (gAIDManager != null) {
                return gAIDManager;
            }
            synchronized (this) {
                GAIDManager gAIDManager2 = GAIDManager.INSTANCE;
                if (gAIDManager2 == null) {
                    Companion companion = GAIDManager.INSTANCE;
                    GAIDManager.INSTANCE = instance;
                } else {
                    instance = gAIDManager2;
                }
            }
            return instance;
        }

        public final GAIDManager getInstance() {
            GAIDManager gAIDManager;
            synchronized (this) {
                gAIDManager = GAIDManager.INSTANCE;
                if (gAIDManager == null) {
                    throw new IllegalStateException("you must attach GAIDManager instance first".toString());
                }
            }
            return gAIDManager;
        }
    }
}

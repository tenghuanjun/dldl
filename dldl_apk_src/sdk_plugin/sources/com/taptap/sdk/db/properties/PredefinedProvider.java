package com.taptap.sdk.db.properties;

import com.taptap.sdk.common.services.GidService;
import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.initializer.api.model.RegionType;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import com.taptap.sdk.servicemanager.ServiceManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PredefinedProvider.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J/\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/taptap/sdk/db/properties/PredefinedProvider;", "", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "gidService", "Lcom/taptap/sdk/common/services/GidService;", "provide", "", "", "eventContext", "Lcom/taptap/sdk/db/EventContext;", "isAutomatically", "", "(Lcom/taptap/sdk/db/EventContext;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PredefinedProvider {
    private final GidService gidService;
    private final TapTapSdkOptions options;

    /* JADX INFO: compiled from: PredefinedProvider.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RegionType.values().length];
            try {
                iArr[RegionType.CN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RegionType.GLOBAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.properties.PredefinedProvider$provide$1, reason: invalid class name */
    /* JADX INFO: compiled from: PredefinedProvider.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.properties.PredefinedProvider", f = "PredefinedProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, l = {91, 93, 97, 103}, m = "provide", n = {"this", "eventContext", Common.Predefined.PROPERTIES, "isAutomatically", "this", "eventContext", Common.Predefined.PROPERTIES, "isAutomatically", "this", "eventContext", Common.Predefined.PROPERTIES, "isAutomatically", "this", "eventContext", Common.Predefined.PROPERTIES, "isAutomatically"}, s = {"L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "Z$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PredefinedProvider.this.provide(null, false, this);
        }
    }

    public PredefinedProvider(TapTapSdkOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
        this.gidService = (GidService) ServiceManager.INSTANCE.getService(GidService.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object provide(com.taptap.sdk.db.EventContext r18, boolean r19, kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, ? extends java.lang.Object>> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.properties.PredefinedProvider.provide(com.taptap.sdk.db.EventContext, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

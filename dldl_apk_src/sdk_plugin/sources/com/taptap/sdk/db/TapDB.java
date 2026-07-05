package com.taptap.sdk.db;

import android.content.SharedPreferences;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import com.taptap.sdk.base.utils.lifecycle.TapActivityLifecycleTracker;
import com.taptap.sdk.common.gaid.GAIDManager;
import com.taptap.sdk.common.gaid.data.model.GAID;
import com.taptap.sdk.common.network.NetworkClientKt;
import com.taptap.sdk.common.oaid.OAIDManager;
import com.taptap.sdk.core.TapTapEvent;
import com.taptap.sdk.db.biz.gameplay.GameDurationService;
import com.taptap.sdk.db.biz.gameplay.reporter.DefaultGameDurationReporter;
import com.taptap.sdk.db.biz.gameplay.storage.PrefsGameDurationStorage;
import com.taptap.sdk.db.biz.gameplay.tracker.DefaultGameDurationTracker;
import com.taptap.sdk.db.biz.iap.IAPService;
import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.db.data.model.Event;
import com.taptap.sdk.db.data.model.WrapEvent;
import com.taptap.sdk.db.interceptor.Interceptor;
import com.taptap.sdk.db.interceptor.RealChain;
import com.taptap.sdk.db.processor.EventProcessor;
import com.taptap.sdk.db.properties.PredefinedProvider;
import com.taptap.sdk.db.properties.SystemProvider;
import com.taptap.sdk.db.repository.EventApi;
import com.taptap.sdk.db.repository.EventRepository;
import com.taptap.sdk.db.storage.PersistentQueue;
import com.taptap.sdk.db.utils.MapUtils2;
import com.taptap.sdk.initializer.api.model.RegionType;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: TapDB.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 `2\u00020\u0001:\u0002_`B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B?\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0018\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000e¢\u0006\u0002\u0010\u0012J\u001c\u0010D\u001a\u00020E2\u0014\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\"0!J\u0006\u0010G\u001a\u00020EJ\u0014\u0010H\u001a\u00020E2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00180JJ\u0006\u0010K\u001a\u00020EJ\b\u0010L\u001a\u0004\u0018\u00010\u0018J\b\u0010M\u001a\u0004\u0018\u00010\u0018J\u0013\u0010N\u001a\u0004\u0018\u00010\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010OJ\u0013\u0010P\u001a\u0004\u0018\u00010\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010OJ\u0006\u0010Q\u001a\u00020EJ\u001c\u0010R\u001a\u00020E2\b\u0010S\u001a\u0004\u0018\u00010\u00182\b\u0010T\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010U\u001a\u00020VH\u0002J\u0010\u0010W\u001a\u00020E2\b\u0010X\u001a\u0004\u0018\u00010+J\u0010\u0010Y\u001a\u00020E2\b\u0010Z\u001a\u0004\u0018\u00010\u0018J\u0010\u0010[\u001a\u00020E2\b\u0010\\\u001a\u0004\u0018\u00010\u0018J\u000e\u0010]\u001a\u00020E2\u0006\u0010^\u001a\u00020\u0010R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u00188V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR(\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\"0!X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001b\"\u0004\b)\u0010\u001dR\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u001bR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010C\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006a"}, d2 = {"Lcom/taptap/sdk/db/TapDB;", "Lcom/taptap/sdk/db/EventContext;", "builder", "Lcom/taptap/sdk/db/TapDB$Builder;", "(Lcom/taptap/sdk/db/TapDB$Builder;)V", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "config", "Lcom/taptap/sdk/db/processor/EventProcessor$Config;", "predefinedProvider", "Lcom/taptap/sdk/db/properties/PredefinedProvider;", "systemProvider", "Lcom/taptap/sdk/db/properties/SystemProvider;", "interceptors", "", "Lcom/taptap/sdk/db/interceptor/Interceptor;", "Lcom/taptap/sdk/db/data/model/WrapEvent;", "Lcom/taptap/sdk/db/data/model/Event;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;Lcom/taptap/sdk/db/processor/EventProcessor$Config;Lcom/taptap/sdk/db/properties/PredefinedProvider;Lcom/taptap/sdk/db/properties/SystemProvider;Ljava/util/List;)V", "chain", "Lcom/taptap/sdk/db/interceptor/RealChain;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "<set-?>", "", "currentUserId", "getCurrentUserId", "()Ljava/lang/String;", "setCurrentUserId", "(Ljava/lang/String;)V", "currentUserId$delegate", "Lkotlin/properties/ReadWriteProperty;", "customExtras", "", "", "getCustomExtras", "()Ljava/util/Map;", "setCustomExtras", "(Ljava/util/Map;)V", "customOAID", "getCustomOAID", "setCustomOAID", "dynamicPropertiesGetter", "Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;", "getDynamicPropertiesGetter", "()Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;", "setDynamicPropertiesGetter", "(Lcom/taptap/sdk/core/TapTapEvent$TapEventDynamicProperties;)V", "eventIndex", "Ljava/util/concurrent/atomic/AtomicLong;", "getEventIndex", "()Ljava/util/concurrent/atomic/AtomicLong;", "setEventIndex", "(Ljava/util/concurrent/atomic/AtomicLong;)V", "eventProcessor", "Lcom/taptap/sdk/db/processor/EventProcessor;", "gameDurationService", "Lcom/taptap/sdk/db/biz/gameplay/GameDurationService;", "iapService", "Lcom/taptap/sdk/db/biz/iap/IAPService;", "getInterceptors", "()Ljava/util/List;", "getPredefinedProvider", "()Lcom/taptap/sdk/db/properties/PredefinedProvider;", "sessionId", "getSessionId", "getSystemProvider", "()Lcom/taptap/sdk/db/properties/SystemProvider;", "addCommon", "", Common.Predefined.PROPERTIES, "clearAllCommonProperties", "clearCommonProperties", "keys", "", "flushEvents", "getCurrentGAID", "getCurrentOAID", "getGAID", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOAID", "initialize", "onUserIdChange", "oldValue", "newValue", "provideNetworkApi", "Lcom/taptap/sdk/db/repository/EventApi;", "registerDynamicPropertiesGetter", "propertiesGetter", "setOAID", "value", "setUserId", "userId", "submitEvent", "wrapEvent", "Builder", "Companion", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapDB implements EventContext {
    private static volatile TapDB INSTANCE = null;
    private static final String TAG = "TapDB";
    private final RealChain<WrapEvent, Event> chain;
    private final EventProcessor.Config config;
    private final CoroutineScope coroutineScope;

    /* JADX INFO: renamed from: currentUserId$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty currentUserId;
    private Map<String, ? extends Object> customExtras;
    private String customOAID;
    private TapTapEvent.TapEventDynamicProperties dynamicPropertiesGetter;
    private AtomicLong eventIndex;
    private final EventProcessor eventProcessor;
    private GameDurationService gameDurationService;
    private IAPService iapService;
    private final List<Interceptor<WrapEvent, Event>> interceptors;
    private final TapTapSdkOptions options;
    private final PredefinedProvider predefinedProvider;
    private final String sessionId;
    private final SystemProvider systemProvider;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TapDB.class, "currentUserId", "getCurrentUserId()Ljava/lang/String;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ReadWriteProperty<Object, TapTapSdkOptions> options$delegate = Delegates.INSTANCE.notNull();

    /* JADX INFO: compiled from: TapDB.kt */
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

    /* JADX INFO: renamed from: com.taptap.sdk.db.TapDB$getGAID$1, reason: invalid class name */
    /* JADX INFO: compiled from: TapDB.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.TapDB", f = "TapDB.kt", i = {}, l = {280}, m = "getGAID", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapDB.this.getGAID(this);
        }
    }

    public /* synthetic */ TapDB(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TapDB(TapTapSdkOptions options, EventProcessor.Config config, PredefinedProvider predefinedProvider, SystemProvider systemProvider, List<? extends Interceptor<WrapEvent, Event>> interceptors) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(predefinedProvider, "predefinedProvider");
        Intrinsics.checkNotNullParameter(systemProvider, "systemProvider");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        this.options = options;
        this.config = config;
        this.predefinedProvider = predefinedProvider;
        this.systemProvider = systemProvider;
        this.interceptors = interceptors;
        final Object obj = null;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        Delegates delegates = Delegates.INSTANCE;
        this.currentUserId = new ObservableProperty<String>(obj) { // from class: com.taptap.sdk.db.TapDB$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TapDB tapDB = this;
                tapDB.onUserIdChange(oldValue, newValue);
            }
        };
        this.customExtras = MapsKt.emptyMap();
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        this.sessionId = string;
        this.eventIndex = new AtomicLong(0L);
        EventProcessor.Config config2 = this.config;
        this.eventProcessor = new EventProcessor(config2, new EventRepository(new PersistentQueue(config2), provideNetworkApi()));
        this.chain = new RealChain<>(getInterceptors(), 0, null, 6, null);
    }

    @Override // com.taptap.sdk.db.EventContext
    public PredefinedProvider getPredefinedProvider() {
        return this.predefinedProvider;
    }

    @Override // com.taptap.sdk.db.EventContext
    public SystemProvider getSystemProvider() {
        return this.systemProvider;
    }

    @Override // com.taptap.sdk.db.EventContext
    public List<Interceptor<WrapEvent, Event>> getInterceptors() {
        return this.interceptors;
    }

    @Override // com.taptap.sdk.db.EventContext
    public String getCustomOAID() {
        return this.customOAID;
    }

    @Override // com.taptap.sdk.db.EventContext
    public void setCustomOAID(String str) {
        this.customOAID = str;
    }

    @Override // com.taptap.sdk.db.EventContext
    public String getCurrentUserId() {
        return (String) this.currentUserId.getValue(this, $$delegatedProperties[0]);
    }

    @Override // com.taptap.sdk.db.EventContext
    public void setCurrentUserId(String str) {
        this.currentUserId.setValue(this, $$delegatedProperties[0], str);
    }

    @Override // com.taptap.sdk.db.EventContext
    public Map<String, Object> getCustomExtras() {
        return this.customExtras;
    }

    @Override // com.taptap.sdk.db.EventContext
    public void setCustomExtras(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.customExtras = map;
    }

    @Override // com.taptap.sdk.db.EventContext
    public TapTapEvent.TapEventDynamicProperties getDynamicPropertiesGetter() {
        return this.dynamicPropertiesGetter;
    }

    @Override // com.taptap.sdk.db.EventContext
    public void setDynamicPropertiesGetter(TapTapEvent.TapEventDynamicProperties tapEventDynamicProperties) {
        this.dynamicPropertiesGetter = tapEventDynamicProperties;
    }

    @Override // com.taptap.sdk.db.EventContext
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.taptap.sdk.db.EventContext
    public AtomicLong getEventIndex() {
        return this.eventIndex;
    }

    @Override // com.taptap.sdk.db.EventContext
    public void setEventIndex(AtomicLong atomicLong) {
        Intrinsics.checkNotNullParameter(atomicLong, "<set-?>");
        this.eventIndex = atomicLong;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private TapDB(Builder builder) {
        TapTapSdkOptions options = builder.getOptions();
        EventProcessor.Config config = builder.getConfig();
        if (config == null) {
            throw new IllegalStateException("you must set config for TapDB".toString());
        }
        List<Interceptor<WrapEvent, Event>> interceptors$tap_db_release = builder.getInterceptors$tap_db_release();
        SystemProvider systemProvider = builder.getSystemProvider();
        if (systemProvider == null) {
            throw new IllegalStateException("you must set systemProvider for TapDB".toString());
        }
        PredefinedProvider predefinedProvider = builder.getPredefinedProvider();
        if (predefinedProvider != null) {
            this(options, config, predefinedProvider, systemProvider, interceptors$tap_db_release);
            return;
        }
        throw new IllegalStateException("you must set predefinedProvider for TapDB".toString());
    }

    public final void initialize() {
        INSTANCE = this;
        INSTANCE.setOptions$tap_db_release(this.options);
        GameDurationService.Builder builder = new GameDurationService.Builder(this.options.getContext());
        SharedPreferences sharedPreferences = this.options.getContext().getSharedPreferences(PrefsGameDurationStorage.SP_FILE_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "options.context.getShare…                        )");
        this.gameDurationService = builder.setTracker(new DefaultGameDurationTracker(new PrefsGameDurationStorage(sharedPreferences, Json.INSTANCE), new DefaultGameDurationReporter(), TapActivityLifecycleTracker.INSTANCE, this)).build().initialize();
        this.iapService = new IAPService.Builder(this.options).build().initialize();
    }

    /* JADX INFO: renamed from: com.taptap.sdk.db.TapDB$submitEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapDB.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.taptap.sdk.db.TapDB$submitEvent$1", f = "TapDB.kt", i = {}, l = {Opcodes.INVOKEINTERFACE, Opcodes.INVOKESTATIC}, m = "invokeSuspend", n = {}, s = {})
    static final class C00851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ WrapEvent $wrapEvent;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00851(WrapEvent wrapEvent, Continuation<? super C00851> continuation) {
            super(2, continuation);
            this.$wrapEvent = wrapEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TapDB.this.new C00851(this.$wrapEvent, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            EventProcessor eventProcessor;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Throwable th) {
                Log.e(TapDB.TAG, "submit wrapEvent fail", th);
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                eventProcessor = TapDB.this.eventProcessor;
                RealChain realChain = TapDB.this.chain;
                WrapEvent wrapEvent = this.$wrapEvent;
                final TapDB tapDB = TapDB.this;
                this.L$0 = eventProcessor;
                this.label = 1;
                obj = realChain.process(wrapEvent.newBuilder(new Function1<WrapEvent.Builder, Unit>() { // from class: com.taptap.sdk.db.TapDB.submitEvent.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WrapEvent.Builder builder) {
                        invoke2(builder);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WrapEvent.Builder newBuilder) {
                        Intrinsics.checkNotNullParameter(newBuilder, "$this$newBuilder");
                        String userId = newBuilder.getUserId();
                        if (userId == null) {
                            userId = tapDB.getCurrentUserId();
                        }
                        newBuilder.setUserId(userId);
                        newBuilder.setEventContext(tapDB);
                    }
                }), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                eventProcessor = (EventProcessor) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.L$0 = null;
            this.label = 2;
            if (eventProcessor.submitEvent((Event) obj, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void submitEvent(WrapEvent wrapEvent) {
        Intrinsics.checkNotNullParameter(wrapEvent, "wrapEvent");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C00851(wrapEvent, null), 3, null);
    }

    public final void flushEvents() {
        this.eventProcessor.flushEvents();
    }

    public final void setUserId(String userId) {
        setCurrentUserId(userId);
    }

    public final void setOAID(String value) {
        if (!this.options.getDisableReflectionOAID()) {
            Log.e(TAG, "setOAID is not allowed when disableReflectionOAID is false");
        }
        setCustomOAID(value);
    }

    public final void addCommon(Map<String, ? extends Object> properties) {
        Intrinsics.checkNotNullParameter(properties, "properties");
        setCustomExtras(MapsKt.plus(getCustomExtras(), MapUtils2.filterRecursively$default(MapUtils2.INSTANCE, properties, null, 1, null)));
    }

    public final void registerDynamicPropertiesGetter(TapTapEvent.TapEventDynamicProperties propertiesGetter) {
        setDynamicPropertiesGetter(propertiesGetter);
    }

    public final void clearCommonProperties(Set<String> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Map<String, Object> customExtras = getCustomExtras();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : customExtras.entrySet()) {
            if (!keys.contains(entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        setCustomExtras(linkedHashMap);
    }

    public final void clearAllCommonProperties() {
        setCustomExtras(MapsKt.emptyMap());
    }

    public final Object getOAID(Continuation<? super String> continuation) {
        if (!this.options.getDisableReflectionOAID()) {
            return OAIDManager.INSTANCE.getInstance().getOAID(continuation);
        }
        return getCustomOAID();
    }

    public final String getCurrentOAID() {
        if (!this.options.getDisableReflectionOAID()) {
            return OAIDManager.INSTANCE.getInstance().getCurrentOAID();
        }
        return getCustomOAID();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getGAID(kotlin.coroutines.Continuation<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.taptap.sdk.db.TapDB.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r5
            com.taptap.sdk.db.TapDB$getGAID$1 r0 = (com.taptap.sdk.db.TapDB.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            com.taptap.sdk.db.TapDB$getGAID$1 r0 = new com.taptap.sdk.db.TapDB$getGAID$1
            r0.<init>(r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L44
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            com.taptap.sdk.common.gaid.GAIDManager$Companion r5 = com.taptap.sdk.common.gaid.GAIDManager.INSTANCE
            com.taptap.sdk.common.gaid.GAIDManager r5 = r5.getInstance()
            r0.label = r3
            java.lang.Object r5 = r5.getGAID(r0)
            if (r5 != r1) goto L44
            return r1
        L44:
            com.taptap.sdk.common.gaid.data.model.GAID r5 = (com.taptap.sdk.common.gaid.data.model.GAID) r5
            if (r5 != 0) goto L4a
            r5 = 0
            return r5
        L4a:
            java.lang.String r5 = r5.getAndroidAdvertiserIdValue()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.TapDB.getGAID(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String getCurrentGAID() {
        GAID currentGAID = GAIDManager.INSTANCE.getInstance().getCurrentGAID();
        if (currentGAID == null) {
            return null;
        }
        return currentGAID.getAndroidAdvertiserIdValue();
    }

    private static final String provideNetworkApi$getBaseUrl(TapDB tapDB) {
        int i = WhenMappings.$EnumSwitchMapping$0[tapDB.options.getRegion().ordinal()];
        if (i == 1) {
            return "https://e.tapdb.net";
        }
        if (i == 2) {
            return "https://e.tapdb.ap-sg.tapapis.com";
        }
        throw new NoWhenBranchMatchedException();
    }

    private final EventApi provideNetworkApi() {
        Object objCreate = NetworkClientKt.provideRetrofit(provideNetworkApi$getBaseUrl(this), this.options).create(EventApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(EventApi::class.java)");
        return (EventApi) objCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onUserIdChange(String oldValue, String newValue) {
        GameDurationService gameDurationService;
        GameDurationService gameDurationService2;
        Log.d(TAG, "userId changed from " + oldValue + " to " + newValue);
        if (oldValue != null && !Intrinsics.areEqual(oldValue, newValue) && (gameDurationService2 = this.gameDurationService) != null) {
            gameDurationService2.submitOldUserGameDuration(oldValue, newValue);
        }
        String currentUserId = getCurrentUserId();
        if ((currentUserId == null || currentUserId.length() == 0) || (gameDurationService = this.gameDurationService) == null) {
            return;
        }
        gameDurationService.setUserStartTimeAtInMillis(System.currentTimeMillis());
    }

    /* JADX INFO: compiled from: TapDB.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u001b\u001a\u00020\u00002\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0013J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0017R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tRB\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\n2\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\n@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u0013@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0005\u001a\u0004\u0018\u00010\u0017@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006#"}, d2 = {"Lcom/taptap/sdk/db/TapDB$Builder;", "", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "<set-?>", "Lcom/taptap/sdk/db/processor/EventProcessor$Config;", "config", "getConfig$tap_db_release", "()Lcom/taptap/sdk/db/processor/EventProcessor$Config;", "", "Lcom/taptap/sdk/db/interceptor/Interceptor;", "Lcom/taptap/sdk/db/data/model/WrapEvent;", "Lcom/taptap/sdk/db/data/model/Event;", "interceptors", "getInterceptors$tap_db_release", "()Ljava/util/List;", "getOptions", "()Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "Lcom/taptap/sdk/db/properties/PredefinedProvider;", "predefinedProvider", "getPredefinedProvider$tap_db_release", "()Lcom/taptap/sdk/db/properties/PredefinedProvider;", "Lcom/taptap/sdk/db/properties/SystemProvider;", "systemProvider", "getSystemProvider$tap_db_release", "()Lcom/taptap/sdk/db/properties/SystemProvider;", "addInterceptor", "interceptor", "build", "Lcom/taptap/sdk/db/TapDB;", "configuration", "setPredefinedProvider", Common.Predefined.SUB_PROVIDER, "setSystemProvider", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private EventProcessor.Config config;
        private List<? extends Interceptor<WrapEvent, Event>> interceptors;
        private final TapTapSdkOptions options;
        private PredefinedProvider predefinedProvider;
        private SystemProvider systemProvider;

        public Builder(TapTapSdkOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            this.options = options;
            this.interceptors = CollectionsKt.emptyList();
        }

        public final TapTapSdkOptions getOptions() {
            return this.options;
        }

        /* JADX INFO: renamed from: getConfig$tap_db_release, reason: from getter */
        public final EventProcessor.Config getConfig() {
            return this.config;
        }

        public final List<Interceptor<WrapEvent, Event>> getInterceptors$tap_db_release() {
            return this.interceptors;
        }

        /* JADX INFO: renamed from: getPredefinedProvider$tap_db_release, reason: from getter */
        public final PredefinedProvider getPredefinedProvider() {
            return this.predefinedProvider;
        }

        /* JADX INFO: renamed from: getSystemProvider$tap_db_release, reason: from getter */
        public final SystemProvider getSystemProvider() {
            return this.systemProvider;
        }

        public final Builder configuration(EventProcessor.Config config) {
            Intrinsics.checkNotNullParameter(config, "config");
            Builder builder = this;
            builder.config = config;
            return builder;
        }

        public final Builder addInterceptor(Interceptor<WrapEvent, Event> interceptor) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            Builder builder = this;
            builder.interceptors = CollectionsKt.plus((Collection<? extends Interceptor<WrapEvent, Event>>) builder.interceptors, interceptor);
            return builder;
        }

        public final Builder setPredefinedProvider(PredefinedProvider provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Builder builder = this;
            builder.predefinedProvider = provider;
            return builder;
        }

        public final Builder setSystemProvider(SystemProvider provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Builder builder = this;
            builder.systemProvider = provider;
            return builder;
        }

        public final TapDB build() {
            return new TapDB(this, null);
        }
    }

    /* JADX INFO: compiled from: TapDB.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/db/TapDB$Companion;", "", "()V", "INSTANCE", "Lcom/taptap/sdk/db/TapDB;", "TAG", "", "<set-?>", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "options", "getOptions$tap_db_release", "()Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "setOptions$tap_db_release", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "options$delegate", "Lkotlin/properties/ReadWriteProperty;", "getInstance", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Companion.class, "options", "getOptions$tap_db_release()Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", 0))};

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TapTapSdkOptions getOptions$tap_db_release() {
            return (TapTapSdkOptions) TapDB.options$delegate.getValue(this, $$delegatedProperties[0]);
        }

        public final void setOptions$tap_db_release(TapTapSdkOptions tapTapSdkOptions) {
            Intrinsics.checkNotNullParameter(tapTapSdkOptions, "<set-?>");
            TapDB.options$delegate.setValue(this, $$delegatedProperties[0], tapTapSdkOptions);
        }

        public final TapDB getInstance() {
            TapDB tapDB;
            synchronized (this) {
                tapDB = TapDB.INSTANCE;
                if (tapDB == null) {
                    throw new IllegalStateException("TapDB is not initialized, please call TapDB.Builder(context).build().initialize() first".toString());
                }
            }
            return tapDB;
        }
    }
}

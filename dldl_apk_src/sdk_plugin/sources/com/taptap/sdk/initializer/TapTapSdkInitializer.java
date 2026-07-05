package com.taptap.sdk.initializer;

import android.content.Context;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.taptap.sdk.common.gaid.GAIDManager;
import com.taptap.sdk.common.gaid.helper.GAIDHelper;
import com.taptap.sdk.common.gaid.provider.ReflectionGAIDProvider;
import com.taptap.sdk.common.gaid.provider.ServiceGAIDProvider;
import com.taptap.sdk.common.gaid.repository.GAIDRepository;
import com.taptap.sdk.common.oaid.OAIDManager;
import com.taptap.sdk.common.oaid.cert.AssetOAIDCertProvider;
import com.taptap.sdk.common.oaid.cert.CompositeOAIDCertProvider;
import com.taptap.sdk.common.oaid.cert.OAIDCertProvider;
import com.taptap.sdk.common.oaid.cert.StringOAIDCertProvider;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import com.taptap.sdk.common.oaid.repository.OAIDRepository;
import com.taptap.sdk.common.services.OpenLogService;
import com.taptap.sdk.core.DBService;
import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.initializer.api.model.Language;
import com.taptap.sdk.initializer.api.model.RegionType;
import com.taptap.sdk.initializer.api.option.TapTapSdkOptions;
import com.taptap.sdk.initializer.data.response.GateKeeper;
import com.taptap.sdk.initializer.manager.GatekeeperManager;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.TapTapKit;
import com.taptap.sdk.kit.internal.utils.localize.TapLocalizeUtil;
import com.taptap.sdk.servicemanager.ServiceManager;
import com.taptap.sdk.servicemanager.utils.ServiceManagerComponent;
import com.taptap.sdk.startup.StartUp;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapTapSdkInitializer.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/taptap/sdk/initializer/TapTapSdkInitializer;", "", "builder", "Lcom/taptap/sdk/initializer/TapTapSdkInitializer$Builder;", "(Lcom/taptap/sdk/initializer/TapTapSdkInitializer$Builder;)V", "options", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "(Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;)V", "dbService", "Lcom/taptap/sdk/core/DBService;", "getDbService", "()Lcom/taptap/sdk/core/DBService;", "dbService$delegate", "Lkotlin/Lazy;", "gatekeeperManager", "Lcom/taptap/sdk/initializer/manager/GatekeeperManager;", "openLogService", "Lcom/taptap/sdk/common/services/OpenLogService;", "getOpenLogService", "()Lcom/taptap/sdk/common/services/OpenLogService;", "openLogService$delegate", "getCurrentGateKeeper", "Lcom/taptap/sdk/initializer/data/response/GateKeeper;", "initialize", "Builder", "Companion", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapTapSdkInitializer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile TapTapSdkInitializer INSTANCE;

    /* JADX INFO: renamed from: dbService$delegate, reason: from kotlin metadata */
    private final Lazy dbService;
    private GatekeeperManager gatekeeperManager;

    /* JADX INFO: renamed from: openLogService$delegate, reason: from kotlin metadata */
    private final Lazy openLogService;
    private final TapTapSdkOptions options;

    public /* synthetic */ TapTapSdkInitializer(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    private TapTapSdkInitializer(TapTapSdkOptions tapTapSdkOptions) {
        this.options = tapTapSdkOptions;
        ServiceManagerComponent serviceManagerComponent = ServiceManagerComponent.INSTANCE;
        this.openLogService = LazyKt.lazy(new Function0<OpenLogService>() { // from class: com.taptap.sdk.initializer.TapTapSdkInitializer$special$$inlined$inject$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final OpenLogService invoke() {
                ServiceManager.Service service = ServiceManager.INSTANCE.getService(OpenLogService.class);
                if (service != null) {
                    return (OpenLogService) service;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.taptap.sdk.common.services.OpenLogService");
            }
        });
        ServiceManagerComponent serviceManagerComponent2 = ServiceManagerComponent.INSTANCE;
        this.dbService = LazyKt.lazy(new Function0<DBService>() { // from class: com.taptap.sdk.initializer.TapTapSdkInitializer$special$$inlined$inject$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DBService invoke() {
                ServiceManager.Service service = ServiceManager.INSTANCE.getService(DBService.class);
                if (service != null) {
                    return (DBService) service;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.taptap.sdk.core.DBService");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OpenLogService getOpenLogService() {
        return (OpenLogService) this.openLogService.getValue();
    }

    private final DBService getDbService() {
        return (DBService) this.dbService.getValue();
    }

    private TapTapSdkInitializer(Builder builder) {
        this(builder.getOptions());
    }

    public final TapTapSdkInitializer initialize() {
        final TapTapSdkInitializer tapTapSdkInitializer = this;
        TapTapKit.INSTANCE.initialize(tapTapSdkInitializer.options);
        if (!tapTapSdkInitializer.options.getDisableReflectionOAID()) {
            OAIDManager.INSTANCE.attach(new OAIDManager.Builder().setRepository(new OAIDRepository(new CompositeOAIDCertProvider(CollectionsKt.listOf((Object[]) new OAIDCertProvider[]{new StringOAIDCertProvider(tapTapSdkInitializer.options.getOaidCert()), new AssetOAIDCertProvider(tapTapSdkInitializer.options.getContext())})), new OAIDHelper(tapTapSdkInitializer.options.getContext()))).build().initialize());
        }
        if (tapTapSdkInitializer.options.getRegion() == RegionType.GLOBAL) {
            GAIDManager.INSTANCE.attach(new GAIDManager.Builder().setRepository(new GAIDRepository(new GAIDHelper.Builder(tapTapSdkInitializer.options.getContext()).setReflectionGAIDProvider(new ReflectionGAIDProvider()).setServiceGAIDProvider(new ServiceGAIDProvider()).build())).build().initialize());
        }
        TapLocalizeUtil.INSTANCE.initialize(tapTapSdkInitializer.options.getRegion(), tapTapSdkInitializer.options.getPreferredLanguage().getLanguage());
        new StartUp.Builder(tapTapSdkInitializer.options.getContext()).setOptions(tapTapSdkInitializer.options).build().initialize();
        TapLogger.INSTANCE.setEnableLog(tapTapSdkInitializer.options.getEnableLog());
        tapTapSdkInitializer.gatekeeperManager = new GatekeeperManager.Builder(tapTapSdkInitializer.options).addOnGateKeeperChangeListener(new GatekeeperManager.OnGateKeeperChangeListener() { // from class: com.taptap.sdk.initializer.TapTapSdkInitializer$initialize$1$1
            @Override // com.taptap.sdk.initializer.manager.GatekeeperManager.OnGateKeeperChangeListener
            public void onGateKeeperChange(GateKeeper newValue) {
                Intrinsics.checkNotNullParameter(newValue, "newValue");
                this.$this_apply.getOpenLogService().enableHeartbeat(newValue.getSwitch().getHeartbeat());
            }
        }).build().initialize();
        if (!tapTapSdkInitializer.options.getDisableAutoLogDeviceLogin()) {
            tapTapSdkInitializer.getDbService().sendDeviceLoginEvent(tapTapSdkInitializer.options);
        }
        return tapTapSdkInitializer;
    }

    public final GateKeeper getCurrentGateKeeper() {
        GatekeeperManager gatekeeperManager = this.gatekeeperManager;
        GateKeeper currentGateKeeper = gatekeeperManager != null ? gatekeeperManager.getCurrentGateKeeper() : null;
        if (currentGateKeeper != null) {
            return currentGateKeeper;
        }
        throw new IllegalStateException("gatekeeperManager is null, please initialize it first".toString());
    }

    /* JADX INFO: compiled from: TapTapSdkInitializer.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0016J\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001dJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0016J\u0010\u0010$\u001a\u00020\u00002\b\u0010%\u001a\u0004\u0018\u00010\u001dJ\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(J\u0010\u0010)\u001a\u00020\u00002\b\u0010*\u001a\u0004\u0018\u00010\u001dJ\u000e\u0010+\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0016J\u0010\u0010,\u001a\u00020\u00002\b\u0010-\u001a\u0004\u0018\u00010.J\u000e\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u000201J\u000e\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020\rJ\u000e\u00104\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0016J\u000e\u00105\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u00066"}, d2 = {"Lcom/taptap/sdk/initializer/TapTapSdkInitializer$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "<set-?>", "Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "options", "getOptions$tap_initializer_release", "()Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "", "getScreenOrientation$tap_initializer_release", "()I", "setScreenOrientation$tap_initializer_release", "(I)V", "build", "Lcom/taptap/sdk/initializer/TapTapSdkInitializer;", "disableAutoLogDeviceLogin", "value", "", "disableReflectionOAID", "enableLog", "setAutoIAPEventEnabled", "enable", "setChannel", "channel", "", "setClientId", "clientId", "setClientToken", "clientToken", "setEnableToast", "enabled", "setGameVersion", "gameVersion", "setLanguage", "language", "Lcom/taptap/sdk/initializer/api/model/Language;", "setOAIDCert", "oaidCert", "setOverrideBuiltInParameters", "setProperties", Common.Predefined.PROPERTIES, "Lorg/json/JSONObject;", "setRegion", "region", "Lcom/taptap/sdk/initializer/api/model/RegionType;", "setScreenOrientation", "orientation", "setShowSwitchAccount", "setUseAgeRange", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private final Context context;
        private TapTapSdkOptions options;
        private int screenOrientation;

        public Builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.options = new TapTapSdkOptions(context, null, null, null, null, null, false, false, null, null, false, null, false, false, false, false, false, 0, 262142, null);
            this.screenOrientation = 1;
        }

        public final Context getContext() {
            return this.context;
        }

        /* JADX INFO: renamed from: getOptions$tap_initializer_release, reason: from getter */
        public final TapTapSdkOptions getOptions() {
            return this.options;
        }

        /* JADX INFO: renamed from: getScreenOrientation$tap_initializer_release, reason: from getter */
        public final int getScreenOrientation() {
            return this.screenOrientation;
        }

        public final void setScreenOrientation$tap_initializer_release(int i) {
            this.screenOrientation = i;
        }

        public final Builder setClientId(String clientId) {
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : clientId, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder enableLog(boolean enableLog) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : enableLog, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setOAIDCert(String oaidCert) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : oaidCert, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setRegion(RegionType region) {
            Intrinsics.checkNotNullParameter(region, "region");
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : region, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setClientToken(String clientToken) {
            Intrinsics.checkNotNullParameter(clientToken, "clientToken");
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : clientToken, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setLanguage(Language language) {
            Intrinsics.checkNotNullParameter(language, "language");
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : language, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setProperties(JSONObject properties) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : properties, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setChannel(String channel) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : channel, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setAutoIAPEventEnabled(boolean enable) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : enable, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setOverrideBuiltInParameters(boolean enable) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : enable, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setGameVersion(String gameVersion) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : gameVersion, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setEnableToast(boolean enabled) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : enabled, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setShowSwitchAccount(boolean enabled) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : enabled, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setUseAgeRange(boolean enabled) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : enabled, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder disableReflectionOAID(boolean value) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : value, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : false, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder disableAutoLogDeviceLogin(boolean value) {
            Builder builder = this;
            TapTapSdkOptions tapTapSdkOptions = builder.options;
            builder.options = tapTapSdkOptions.copy((196607 & 1) != 0 ? tapTapSdkOptions.context : null, (196607 & 2) != 0 ? tapTapSdkOptions.clientId : null, (196607 & 4) != 0 ? tapTapSdkOptions.clientToken : null, (196607 & 8) != 0 ? tapTapSdkOptions.region : null, (196607 & 16) != 0 ? tapTapSdkOptions.channel : null, (196607 & 32) != 0 ? tapTapSdkOptions.gameVersion : null, (196607 & 64) != 0 ? tapTapSdkOptions.autoIAPEventEnabled : false, (196607 & 128) != 0 ? tapTapSdkOptions.overrideBuiltInParameters : false, (196607 & 256) != 0 ? tapTapSdkOptions.properties : null, (196607 & 512) != 0 ? tapTapSdkOptions.oaidCert : null, (196607 & 1024) != 0 ? tapTapSdkOptions.enableLog : false, (196607 & 2048) != 0 ? tapTapSdkOptions.preferredLanguage : null, (196607 & 4096) != 0 ? tapTapSdkOptions.useAgeRange : false, (196607 & 8192) != 0 ? tapTapSdkOptions.showSwitchAccount : false, (196607 & 16384) != 0 ? tapTapSdkOptions.enableToast : false, (196607 & 32768) != 0 ? tapTapSdkOptions.disableReflectionOAID : false, (196607 & 65536) != 0 ? tapTapSdkOptions.disableAutoLogDeviceLogin : value, (196607 & 131072) != 0 ? tapTapSdkOptions.screenOrientation : 0);
            return builder;
        }

        public final Builder setScreenOrientation(int orientation) {
            Builder builder = this;
            builder.screenOrientation = orientation;
            return builder;
        }

        public final TapTapSdkInitializer build() {
            if (!(this.options.getClientId().length() > 0)) {
                throw new IllegalStateException("clientId 不可为空".toString());
            }
            if (!(this.options.getClientToken().length() > 0)) {
                throw new IllegalStateException("clientToken 不可为空".toString());
            }
            return new TapTapSdkInitializer(this, null);
        }
    }

    /* JADX INFO: compiled from: TapTapSdkInitializer.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/initializer/TapTapSdkInitializer$Companion;", "", "()V", "INSTANCE", "Lcom/taptap/sdk/initializer/TapTapSdkInitializer;", "getINSTANCE", "()Lcom/taptap/sdk/initializer/TapTapSdkInitializer;", "setINSTANCE", "(Lcom/taptap/sdk/initializer/TapTapSdkInitializer;)V", "getInstance", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TapTapSdkInitializer getINSTANCE() {
            return TapTapSdkInitializer.INSTANCE;
        }

        public final void setINSTANCE(TapTapSdkInitializer tapTapSdkInitializer) {
            TapTapSdkInitializer.INSTANCE = tapTapSdkInitializer;
        }

        public final TapTapSdkInitializer getInstance() {
            TapTapSdkInitializer instance = getINSTANCE();
            if (instance != null) {
                return instance;
            }
            throw new IllegalStateException("INSTANCE is null, please attach INSTANCE before using it".toString());
        }
    }
}

package com.taptap.sdk.core;

import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.taptap.sdk.core.serialize.JSONObjectSerializer;
import com.taptap.sdk.db.constant.Common;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapTapSdkOptions.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bE\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 h2\u00020\u0001:\u0002ghB³\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u000b\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u000b\u0012\b\b\u0001\u0010\u0014\u001a\u00020\u000b\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0018B\u0095\u0001\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003¢\u0006\u0002\u0010\u0019J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u000bHÆ\u0003J\t\u0010N\u001a\u00020\u0012HÆ\u0003J\t\u0010O\u001a\u00020\u000bHÆ\u0003J\t\u0010P\u001a\u00020\u000bHÆ\u0003J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u000bHÆ\u0003J\t\u0010W\u001a\u00020\u000bHÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u009d\u0001\u0010Z\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u0003HÆ\u0001J\u0013\u0010[\u001a\u00020\u000b2\b\u0010\\\u001a\u0004\u0018\u00010]HÖ\u0003J\t\u0010^\u001a\u00020\u0003HÖ\u0001J\t\u0010_\u001a\u00020\u0005HÖ\u0001J!\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u00002\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020fHÇ\u0001R$\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR&\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001b\u001a\u0004\b&\u0010\"R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u001b\u001a\u0004\b(\u0010\"R$\u0010\u0014\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b)\u0010\u001b\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR$\u0010\u0013\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b,\u0010\u001b\u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001fR$\u0010\u0010\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b/\u0010\u001b\u001a\u0004\b0\u0010\u001d\"\u0004\b1\u0010\u001fR&\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b2\u0010\u001b\u001a\u0004\b3\u0010\"\"\u0004\b4\u0010$R&\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b5\u0010\u001b\u001a\u0004\b6\u0010\"\"\u0004\b7\u0010$R$\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b8\u0010\u001b\u001a\u0004\b9\u0010\u001d\"\u0004\b:\u0010\u001fR$\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b;\u0010\u001b\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R&\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b@\u0010\u001b\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bE\u0010\u001b\u001a\u0004\bF\u0010GR$\u0010\u0015\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bH\u0010\u001b\u001a\u0004\bI\u0010G\"\u0004\bJ\u0010K¨\u0006i"}, d2 = {"Lcom/taptap/sdk/core/TapTapSdkOptions;", "Lcom/taptap/sdk/core/TapTapSdkBaseOptions;", "seen1", "", "clientId", "", "clientToken", "region", "channel", "gameVersion", "autoIAPEventEnabled", "", "overrideBuiltInParameters", Common.Predefined.PROPERTIES, "Lorg/json/JSONObject;", "oaidCert", "enableLog", "preferredLanguage", "Lcom/taptap/sdk/core/TapTapLanguage;", "disableReflectionOAID", "disableAutoLogDeviceLogin", RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ZZLorg/json/JSONObject;Ljava/lang/String;ZLcom/taptap/sdk/core/TapTapLanguage;ZZILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ZZLorg/json/JSONObject;Ljava/lang/String;ZLcom/taptap/sdk/core/TapTapLanguage;ZZI)V", "getAutoIAPEventEnabled$annotations", "()V", "getAutoIAPEventEnabled", "()Z", "setAutoIAPEventEnabled", "(Z)V", "getChannel$annotations", "getChannel", "()Ljava/lang/String;", "setChannel", "(Ljava/lang/String;)V", "getClientId$annotations", "getClientId", "getClientToken$annotations", "getClientToken", "getDisableAutoLogDeviceLogin$annotations", "getDisableAutoLogDeviceLogin", "setDisableAutoLogDeviceLogin", "getDisableReflectionOAID$annotations", "getDisableReflectionOAID", "setDisableReflectionOAID", "getEnableLog$annotations", "getEnableLog", "setEnableLog", "getGameVersion$annotations", "getGameVersion", "setGameVersion", "getOaidCert$annotations", "getOaidCert", "setOaidCert", "getOverrideBuiltInParameters$annotations", "getOverrideBuiltInParameters", "setOverrideBuiltInParameters", "getPreferredLanguage$annotations", "getPreferredLanguage", "()Lcom/taptap/sdk/core/TapTapLanguage;", "setPreferredLanguage", "(Lcom/taptap/sdk/core/TapTapLanguage;)V", "getProperties$annotations", "getProperties", "()Lorg/json/JSONObject;", "setProperties", "(Lorg/json/JSONObject;)V", "getRegion$annotations", "getRegion", "()I", "getScreenOrientation$annotations", "getScreenOrientation", "setScreenOrientation", "(I)V", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class TapTapSdkOptions implements TapTapSdkBaseOptions {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean autoIAPEventEnabled;
    private String channel;
    private final String clientId;
    private final String clientToken;
    private boolean disableAutoLogDeviceLogin;
    private boolean disableReflectionOAID;
    private boolean enableLog;
    private String gameVersion;
    private String oaidCert;
    private boolean overrideBuiltInParameters;
    private TapTapLanguage preferredLanguage;
    private JSONObject properties;
    private final int region;
    private int screenOrientation;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i) {
        this(clientId, clientToken, i, (String) null, (String) null, false, false, (JSONObject) null, (String) null, false, (TapTapLanguage) null, false, false, 0, 16376, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str) {
        this(clientId, clientToken, i, str, (String) null, false, false, (JSONObject) null, (String) null, false, (TapTapLanguage) null, false, false, 0, 16368, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2) {
        this(clientId, clientToken, i, str, str2, false, false, (JSONObject) null, (String) null, false, (TapTapLanguage) null, false, false, 0, 16352, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z) {
        this(clientId, clientToken, i, str, str2, z, false, (JSONObject) null, (String) null, false, (TapTapLanguage) null, false, false, 0, 16320, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2) {
        this(clientId, clientToken, i, str, str2, z, z2, (JSONObject) null, (String) null, false, (TapTapLanguage) null, false, false, 0, 16256, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2, JSONObject jSONObject) {
        this(clientId, clientToken, i, str, str2, z, z2, jSONObject, (String) null, false, (TapTapLanguage) null, false, false, 0, 16128, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2, JSONObject jSONObject, String str3) {
        this(clientId, clientToken, i, str, str2, z, z2, jSONObject, str3, false, (TapTapLanguage) null, false, false, 0, 15872, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2, JSONObject jSONObject, String str3, boolean z3) {
        this(clientId, clientToken, i, str, str2, z, z2, jSONObject, str3, z3, (TapTapLanguage) null, false, false, 0, 15360, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2, JSONObject jSONObject, String str3, boolean z3, TapTapLanguage preferredLanguage) {
        this(clientId, clientToken, i, str, str2, z, z2, jSONObject, str3, z3, preferredLanguage, false, false, 0, 14336, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2, JSONObject jSONObject, String str3, boolean z3, TapTapLanguage preferredLanguage, boolean z4) {
        this(clientId, clientToken, i, str, str2, z, z2, jSONObject, str3, z3, preferredLanguage, z4, false, 0, 12288, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2, JSONObject jSONObject, String str3, boolean z3, TapTapLanguage preferredLanguage, boolean z4, boolean z5) {
        this(clientId, clientToken, i, str, str2, z, z2, jSONObject, str3, z3, preferredLanguage, z4, z5, 0, 8192, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
    }

    @SerialName("autoIAPEventEnabled")
    public static /* synthetic */ void getAutoIAPEventEnabled$annotations() {
    }

    @SerialName("channel")
    public static /* synthetic */ void getChannel$annotations() {
    }

    @SerialName("clientId")
    public static /* synthetic */ void getClientId$annotations() {
    }

    @SerialName("clientToken")
    public static /* synthetic */ void getClientToken$annotations() {
    }

    @SerialName("disableAutoLogDeviceLogin")
    public static /* synthetic */ void getDisableAutoLogDeviceLogin$annotations() {
    }

    @SerialName("disableReflectionOAID")
    public static /* synthetic */ void getDisableReflectionOAID$annotations() {
    }

    @SerialName("enableLog")
    public static /* synthetic */ void getEnableLog$annotations() {
    }

    @SerialName("gameVersion")
    public static /* synthetic */ void getGameVersion$annotations() {
    }

    @SerialName("oaidCert")
    public static /* synthetic */ void getOaidCert$annotations() {
    }

    @SerialName("overrideBuiltInParameters")
    public static /* synthetic */ void getOverrideBuiltInParameters$annotations() {
    }

    @SerialName("preferredLanguage")
    public static /* synthetic */ void getPreferredLanguage$annotations() {
    }

    @SerialName(Common.Predefined.PROPERTIES)
    @Serializable(with = JSONObjectSerializer.class)
    public static /* synthetic */ void getProperties$annotations() {
    }

    @SerialName("region")
    public static /* synthetic */ void getRegion$annotations() {
    }

    @SerialName(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION)
    public static /* synthetic */ void getScreenOrientation$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getClientId() {
        return this.clientId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getEnableLog() {
        return this.enableLog;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final TapTapLanguage getPreferredLanguage() {
        return this.preferredLanguage;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getDisableReflectionOAID() {
        return this.disableReflectionOAID;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getDisableAutoLogDeviceLogin() {
        return this.disableAutoLogDeviceLogin;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final int getScreenOrientation() {
        return this.screenOrientation;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getClientToken() {
        return this.clientToken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getRegion() {
        return this.region;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getChannel() {
        return this.channel;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getGameVersion() {
        return this.gameVersion;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getAutoIAPEventEnabled() {
        return this.autoIAPEventEnabled;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getOverrideBuiltInParameters() {
        return this.overrideBuiltInParameters;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final JSONObject getProperties() {
        return this.properties;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getOaidCert() {
        return this.oaidCert;
    }

    public final TapTapSdkOptions copy(String clientId, String clientToken, int region, String channel, String gameVersion, boolean autoIAPEventEnabled, boolean overrideBuiltInParameters, JSONObject properties, String oaidCert, boolean enableLog, TapTapLanguage preferredLanguage, boolean disableReflectionOAID, boolean disableAutoLogDeviceLogin, int screenOrientation) {
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
        return new TapTapSdkOptions(clientId, clientToken, region, channel, gameVersion, autoIAPEventEnabled, overrideBuiltInParameters, properties, oaidCert, enableLog, preferredLanguage, disableReflectionOAID, disableAutoLogDeviceLogin, screenOrientation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TapTapSdkOptions)) {
            return false;
        }
        TapTapSdkOptions tapTapSdkOptions = (TapTapSdkOptions) other;
        return Intrinsics.areEqual(this.clientId, tapTapSdkOptions.clientId) && Intrinsics.areEqual(this.clientToken, tapTapSdkOptions.clientToken) && this.region == tapTapSdkOptions.region && Intrinsics.areEqual(this.channel, tapTapSdkOptions.channel) && Intrinsics.areEqual(this.gameVersion, tapTapSdkOptions.gameVersion) && this.autoIAPEventEnabled == tapTapSdkOptions.autoIAPEventEnabled && this.overrideBuiltInParameters == tapTapSdkOptions.overrideBuiltInParameters && Intrinsics.areEqual(this.properties, tapTapSdkOptions.properties) && Intrinsics.areEqual(this.oaidCert, tapTapSdkOptions.oaidCert) && this.enableLog == tapTapSdkOptions.enableLog && this.preferredLanguage == tapTapSdkOptions.preferredLanguage && this.disableReflectionOAID == tapTapSdkOptions.disableReflectionOAID && this.disableAutoLogDeviceLogin == tapTapSdkOptions.disableAutoLogDeviceLogin && this.screenOrientation == tapTapSdkOptions.screenOrientation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v18, types: [int] */
    /* JADX WARN: Type inference failed for: r1v22, types: [int] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public int hashCode() {
        int iHashCode = ((((this.clientId.hashCode() * 31) + this.clientToken.hashCode()) * 31) + this.region) * 31;
        String str = this.channel;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.gameVersion;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        boolean z = this.autoIAPEventEnabled;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode3 + r1) * 31;
        boolean z2 = this.overrideBuiltInParameters;
        ?? r12 = z2;
        if (z2) {
            r12 = 1;
        }
        int i2 = (i + r12) * 31;
        JSONObject jSONObject = this.properties;
        int iHashCode4 = (i2 + (jSONObject == null ? 0 : jSONObject.hashCode())) * 31;
        String str3 = this.oaidCert;
        int iHashCode5 = (iHashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z3 = this.enableLog;
        ?? r13 = z3;
        if (z3) {
            r13 = 1;
        }
        int iHashCode6 = (((iHashCode5 + r13) * 31) + this.preferredLanguage.hashCode()) * 31;
        boolean z4 = this.disableReflectionOAID;
        ?? r14 = z4;
        if (z4) {
            r14 = 1;
        }
        int i3 = (iHashCode6 + r14) * 31;
        boolean z5 = this.disableAutoLogDeviceLogin;
        return ((i3 + (z5 ? 1 : z5)) * 31) + this.screenOrientation;
    }

    public String toString() {
        return "TapTapSdkOptions(clientId=" + this.clientId + ", clientToken=" + this.clientToken + ", region=" + this.region + ", channel=" + this.channel + ", gameVersion=" + this.gameVersion + ", autoIAPEventEnabled=" + this.autoIAPEventEnabled + ", overrideBuiltInParameters=" + this.overrideBuiltInParameters + ", properties=" + this.properties + ", oaidCert=" + this.oaidCert + ", enableLog=" + this.enableLog + ", preferredLanguage=" + this.preferredLanguage + ", disableReflectionOAID=" + this.disableReflectionOAID + ", disableAutoLogDeviceLogin=" + this.disableAutoLogDeviceLogin + ", screenOrientation=" + this.screenOrientation + ')';
    }

    /* JADX INFO: compiled from: TapTapSdkOptions.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/core/TapTapSdkOptions$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/core/TapTapSdkOptions;", "tap-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<TapTapSdkOptions> serializer() {
            return TapTapSdkOptions$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TapTapSdkOptions(int i, @SerialName("clientId") String str, @SerialName("clientToken") String str2, @SerialName("region") int i2, @SerialName("channel") String str3, @SerialName("gameVersion") String str4, @SerialName("autoIAPEventEnabled") boolean z, @SerialName("overrideBuiltInParameters") boolean z2, @SerialName(Common.Predefined.PROPERTIES) @Serializable(with = JSONObjectSerializer.class) JSONObject jSONObject, @SerialName("oaidCert") String str5, @SerialName("enableLog") boolean z3, @SerialName("preferredLanguage") TapTapLanguage tapTapLanguage, @SerialName("disableReflectionOAID") boolean z4, @SerialName("disableAutoLogDeviceLogin") boolean z5, @SerialName(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION) int i3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, TapTapSdkOptions$$serializer.INSTANCE.getDescriptor());
        }
        this.clientId = str;
        this.clientToken = str2;
        this.region = i2;
        if ((i & 8) == 0) {
            this.channel = null;
        } else {
            this.channel = str3;
        }
        if ((i & 16) == 0) {
            this.gameVersion = null;
        } else {
            this.gameVersion = str4;
        }
        if ((i & 32) == 0) {
            this.autoIAPEventEnabled = false;
        } else {
            this.autoIAPEventEnabled = z;
        }
        if ((i & 64) == 0) {
            this.overrideBuiltInParameters = false;
        } else {
            this.overrideBuiltInParameters = z2;
        }
        if ((i & 128) == 0) {
            this.properties = null;
        } else {
            this.properties = jSONObject;
        }
        if ((i & 256) == 0) {
            this.oaidCert = null;
        } else {
            this.oaidCert = str5;
        }
        if ((i & 512) == 0) {
            this.enableLog = false;
        } else {
            this.enableLog = z3;
        }
        this.preferredLanguage = (i & 1024) == 0 ? TapTapLanguage.AUTO : tapTapLanguage;
        if ((i & 2048) == 0) {
            this.disableReflectionOAID = true;
        } else {
            this.disableReflectionOAID = z4;
        }
        if ((i & 4096) == 0) {
            this.disableAutoLogDeviceLogin = false;
        } else {
            this.disableAutoLogDeviceLogin = z5;
        }
        if ((i & 8192) == 0) {
            this.screenOrientation = 1;
        } else {
            this.screenOrientation = i3;
        }
    }

    public TapTapSdkOptions(String clientId, String clientToken, int i, String str, String str2, boolean z, boolean z2, JSONObject jSONObject, String str3, boolean z3, TapTapLanguage preferredLanguage, boolean z4, boolean z5, int i2) {
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
        this.clientId = clientId;
        this.clientToken = clientToken;
        this.region = i;
        this.channel = str;
        this.gameVersion = str2;
        this.autoIAPEventEnabled = z;
        this.overrideBuiltInParameters = z2;
        this.properties = jSONObject;
        this.oaidCert = str3;
        this.enableLog = z3;
        this.preferredLanguage = preferredLanguage;
        this.disableReflectionOAID = z4;
        this.disableAutoLogDeviceLogin = z5;
        this.screenOrientation = i2;
    }

    @JvmStatic
    public static final void write$Self(TapTapSdkOptions self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.clientId);
        output.encodeStringElement(serialDesc, 1, self.clientToken);
        output.encodeIntElement(serialDesc, 2, self.region);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.channel != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.channel);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.gameVersion != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.gameVersion);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.autoIAPEventEnabled) {
            output.encodeBooleanElement(serialDesc, 5, self.autoIAPEventEnabled);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.overrideBuiltInParameters) {
            output.encodeBooleanElement(serialDesc, 6, self.overrideBuiltInParameters);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.properties != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, JSONObjectSerializer.INSTANCE, self.properties);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.oaidCert != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.oaidCert);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.enableLog) {
            output.encodeBooleanElement(serialDesc, 9, self.enableLog);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.preferredLanguage != TapTapLanguage.AUTO) {
            output.encodeSerializableElement(serialDesc, 10, TapTapLanguage.INSTANCE.serializer(), self.preferredLanguage);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || !self.disableReflectionOAID) {
            output.encodeBooleanElement(serialDesc, 11, self.disableReflectionOAID);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.disableAutoLogDeviceLogin) {
            output.encodeBooleanElement(serialDesc, 12, self.disableAutoLogDeviceLogin);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || self.screenOrientation != 1) {
            output.encodeIntElement(serialDesc, 13, self.screenOrientation);
        }
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientToken() {
        return this.clientToken;
    }

    public final int getRegion() {
        return this.region;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final void setChannel(String str) {
        this.channel = str;
    }

    public final String getGameVersion() {
        return this.gameVersion;
    }

    public final void setGameVersion(String str) {
        this.gameVersion = str;
    }

    public final boolean getAutoIAPEventEnabled() {
        return this.autoIAPEventEnabled;
    }

    public final void setAutoIAPEventEnabled(boolean z) {
        this.autoIAPEventEnabled = z;
    }

    public final boolean getOverrideBuiltInParameters() {
        return this.overrideBuiltInParameters;
    }

    public final void setOverrideBuiltInParameters(boolean z) {
        this.overrideBuiltInParameters = z;
    }

    public final JSONObject getProperties() {
        return this.properties;
    }

    public final void setProperties(JSONObject jSONObject) {
        this.properties = jSONObject;
    }

    public final String getOaidCert() {
        return this.oaidCert;
    }

    public final void setOaidCert(String str) {
        this.oaidCert = str;
    }

    public final boolean getEnableLog() {
        return this.enableLog;
    }

    public final void setEnableLog(boolean z) {
        this.enableLog = z;
    }

    public /* synthetic */ TapTapSdkOptions(String str, String str2, int i, String str3, String str4, boolean z, boolean z2, JSONObject jSONObject, String str5, boolean z3, TapTapLanguage tapTapLanguage, boolean z4, boolean z5, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i3 & 8) != 0 ? null : str3, (i3 & 16) != 0 ? null : str4, (i3 & 32) != 0 ? false : z, (i3 & 64) != 0 ? false : z2, (i3 & 128) != 0 ? null : jSONObject, (i3 & 256) != 0 ? null : str5, (i3 & 512) != 0 ? false : z3, (i3 & 1024) != 0 ? TapTapLanguage.AUTO : tapTapLanguage, (i3 & 2048) != 0 ? true : z4, (i3 & 4096) != 0 ? false : z5, (i3 & 8192) != 0 ? 1 : i2);
    }

    public final TapTapLanguage getPreferredLanguage() {
        return this.preferredLanguage;
    }

    public final void setPreferredLanguage(TapTapLanguage tapTapLanguage) {
        Intrinsics.checkNotNullParameter(tapTapLanguage, "<set-?>");
        this.preferredLanguage = tapTapLanguage;
    }

    public final boolean getDisableReflectionOAID() {
        return this.disableReflectionOAID;
    }

    public final void setDisableReflectionOAID(boolean z) {
        this.disableReflectionOAID = z;
    }

    public final boolean getDisableAutoLogDeviceLogin() {
        return this.disableAutoLogDeviceLogin;
    }

    public final void setDisableAutoLogDeviceLogin(boolean z) {
        this.disableAutoLogDeviceLogin = z;
    }

    public final int getScreenOrientation() {
        return this.screenOrientation;
    }

    public final void setScreenOrientation(int i) {
        this.screenOrientation = i;
    }
}

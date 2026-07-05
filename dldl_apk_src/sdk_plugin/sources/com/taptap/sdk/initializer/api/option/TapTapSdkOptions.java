package com.taptap.sdk.initializer.api.option;

import android.content.Context;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.taptap.sdk.db.constant.Common;
import com.taptap.sdk.initializer.api.model.Language;
import com.taptap.sdk.initializer.api.model.RegionType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapTapSdkOptions.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b2\b\u0086\b\u0018\u00002\u00020\u0001B¿\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\f\u0012\b\b\u0002\u0010\u0016\u001a\u00020\f\u0012\b\b\u0002\u0010\u0017\u001a\u00020\f\u0012\b\b\u0002\u0010\u0018\u001a\u00020\f\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\t\u00105\u001a\u00020\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00107\u001a\u00020\fHÆ\u0003J\t\u00108\u001a\u00020\u0013HÆ\u0003J\t\u00109\u001a\u00020\fHÆ\u0003J\t\u0010:\u001a\u00020\fHÆ\u0003J\t\u0010;\u001a\u00020\fHÆ\u0003J\t\u0010<\u001a\u00020\fHÆ\u0003J\t\u0010=\u001a\u00020\fHÆ\u0003J\t\u0010>\u001a\u00020\u001aHÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\bHÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010D\u001a\u00020\fHÆ\u0003J\t\u0010E\u001a\u00020\fHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u000fHÆ\u0003JÅ\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\u001aHÆ\u0001J\u0013\u0010H\u001a\u00020\f2\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010J\u001a\u00020\u001aHÖ\u0001J\t\u0010K\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0018\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0011\u0010\u0017\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0011\u0010\u0011\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0011\u0010\u0016\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\u0015\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001dR\u0011\u0010\u0014\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001d¨\u0006L"}, d2 = {"Lcom/taptap/sdk/initializer/api/option/TapTapSdkOptions;", "", "context", "Landroid/content/Context;", "clientId", "", "clientToken", "region", "Lcom/taptap/sdk/initializer/api/model/RegionType;", "channel", "gameVersion", "autoIAPEventEnabled", "", "overrideBuiltInParameters", Common.Predefined.PROPERTIES, "Lorg/json/JSONObject;", "oaidCert", "enableLog", "preferredLanguage", "Lcom/taptap/sdk/initializer/api/model/Language;", "useAgeRange", "showSwitchAccount", "enableToast", "disableReflectionOAID", "disableAutoLogDeviceLogin", RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/taptap/sdk/initializer/api/model/RegionType;Ljava/lang/String;Ljava/lang/String;ZZLorg/json/JSONObject;Ljava/lang/String;ZLcom/taptap/sdk/initializer/api/model/Language;ZZZZZI)V", "getAutoIAPEventEnabled", "()Z", "getChannel", "()Ljava/lang/String;", "getClientId", "getClientToken", "getContext", "()Landroid/content/Context;", "getDisableAutoLogDeviceLogin", "getDisableReflectionOAID", "getEnableLog", "getEnableToast", "getGameVersion", "getOaidCert", "getOverrideBuiltInParameters", "getPreferredLanguage", "()Lcom/taptap/sdk/initializer/api/model/Language;", "getProperties", "()Lorg/json/JSONObject;", "getRegion", "()Lcom/taptap/sdk/initializer/api/model/RegionType;", "getScreenOrientation", "()I", "getShowSwitchAccount", "getUseAgeRange", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final /* data */ class TapTapSdkOptions {
    private final boolean autoIAPEventEnabled;
    private final String channel;
    private final String clientId;
    private final String clientToken;
    private final Context context;
    private final boolean disableAutoLogDeviceLogin;
    private final boolean disableReflectionOAID;
    private final boolean enableLog;
    private final boolean enableToast;
    private final String gameVersion;
    private final String oaidCert;
    private final boolean overrideBuiltInParameters;
    private final Language preferredLanguage;
    private final JSONObject properties;
    private final RegionType region;
    private final int screenOrientation;
    private final boolean showSwitchAccount;
    private final boolean useAgeRange;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getOaidCert() {
        return this.oaidCert;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getEnableLog() {
        return this.enableLog;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Language getPreferredLanguage() {
        return this.preferredLanguage;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getUseAgeRange() {
        return this.useAgeRange;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final boolean getShowSwitchAccount() {
        return this.showSwitchAccount;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getEnableToast() {
        return this.enableToast;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getDisableReflectionOAID() {
        return this.disableReflectionOAID;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getDisableAutoLogDeviceLogin() {
        return this.disableAutoLogDeviceLogin;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final int getScreenOrientation() {
        return this.screenOrientation;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getClientId() {
        return this.clientId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getClientToken() {
        return this.clientToken;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final RegionType getRegion() {
        return this.region;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getChannel() {
        return this.channel;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getGameVersion() {
        return this.gameVersion;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getAutoIAPEventEnabled() {
        return this.autoIAPEventEnabled;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getOverrideBuiltInParameters() {
        return this.overrideBuiltInParameters;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final JSONObject getProperties() {
        return this.properties;
    }

    public final TapTapSdkOptions copy(Context context, String clientId, String clientToken, RegionType region, String channel, String gameVersion, boolean autoIAPEventEnabled, boolean overrideBuiltInParameters, JSONObject properties, String oaidCert, boolean enableLog, Language preferredLanguage, boolean useAgeRange, boolean showSwitchAccount, boolean enableToast, boolean disableReflectionOAID, boolean disableAutoLogDeviceLogin, int screenOrientation) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
        return new TapTapSdkOptions(context, clientId, clientToken, region, channel, gameVersion, autoIAPEventEnabled, overrideBuiltInParameters, properties, oaidCert, enableLog, preferredLanguage, useAgeRange, showSwitchAccount, enableToast, disableReflectionOAID, disableAutoLogDeviceLogin, screenOrientation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TapTapSdkOptions)) {
            return false;
        }
        TapTapSdkOptions tapTapSdkOptions = (TapTapSdkOptions) other;
        return Intrinsics.areEqual(this.context, tapTapSdkOptions.context) && Intrinsics.areEqual(this.clientId, tapTapSdkOptions.clientId) && Intrinsics.areEqual(this.clientToken, tapTapSdkOptions.clientToken) && this.region == tapTapSdkOptions.region && Intrinsics.areEqual(this.channel, tapTapSdkOptions.channel) && Intrinsics.areEqual(this.gameVersion, tapTapSdkOptions.gameVersion) && this.autoIAPEventEnabled == tapTapSdkOptions.autoIAPEventEnabled && this.overrideBuiltInParameters == tapTapSdkOptions.overrideBuiltInParameters && Intrinsics.areEqual(this.properties, tapTapSdkOptions.properties) && Intrinsics.areEqual(this.oaidCert, tapTapSdkOptions.oaidCert) && this.enableLog == tapTapSdkOptions.enableLog && this.preferredLanguage == tapTapSdkOptions.preferredLanguage && this.useAgeRange == tapTapSdkOptions.useAgeRange && this.showSwitchAccount == tapTapSdkOptions.showSwitchAccount && this.enableToast == tapTapSdkOptions.enableToast && this.disableReflectionOAID == tapTapSdkOptions.disableReflectionOAID && this.disableAutoLogDeviceLogin == tapTapSdkOptions.disableAutoLogDeviceLogin && this.screenOrientation == tapTapSdkOptions.screenOrientation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15, types: [int] */
    /* JADX WARN: Type inference failed for: r1v21, types: [int] */
    /* JADX WARN: Type inference failed for: r1v25, types: [int] */
    /* JADX WARN: Type inference failed for: r1v27, types: [int] */
    /* JADX WARN: Type inference failed for: r1v29, types: [int] */
    /* JADX WARN: Type inference failed for: r1v31, types: [int] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v48 */
    /* JADX WARN: Type inference failed for: r1v49 */
    /* JADX WARN: Type inference failed for: r1v50 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public int hashCode() {
        int iHashCode = ((((((this.context.hashCode() * 31) + this.clientId.hashCode()) * 31) + this.clientToken.hashCode()) * 31) + this.region.hashCode()) * 31;
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
        boolean z4 = this.useAgeRange;
        ?? r14 = z4;
        if (z4) {
            r14 = 1;
        }
        int i3 = (iHashCode6 + r14) * 31;
        boolean z5 = this.showSwitchAccount;
        ?? r15 = z5;
        if (z5) {
            r15 = 1;
        }
        int i4 = (i3 + r15) * 31;
        boolean z6 = this.enableToast;
        ?? r16 = z6;
        if (z6) {
            r16 = 1;
        }
        int i5 = (i4 + r16) * 31;
        boolean z7 = this.disableReflectionOAID;
        ?? r17 = z7;
        if (z7) {
            r17 = 1;
        }
        int i6 = (i5 + r17) * 31;
        boolean z8 = this.disableAutoLogDeviceLogin;
        return ((i6 + (z8 ? 1 : z8)) * 31) + this.screenOrientation;
    }

    public String toString() {
        return "TapTapSdkOptions(context=" + this.context + ", clientId=" + this.clientId + ", clientToken=" + this.clientToken + ", region=" + this.region + ", channel=" + this.channel + ", gameVersion=" + this.gameVersion + ", autoIAPEventEnabled=" + this.autoIAPEventEnabled + ", overrideBuiltInParameters=" + this.overrideBuiltInParameters + ", properties=" + this.properties + ", oaidCert=" + this.oaidCert + ", enableLog=" + this.enableLog + ", preferredLanguage=" + this.preferredLanguage + ", useAgeRange=" + this.useAgeRange + ", showSwitchAccount=" + this.showSwitchAccount + ", enableToast=" + this.enableToast + ", disableReflectionOAID=" + this.disableReflectionOAID + ", disableAutoLogDeviceLogin=" + this.disableAutoLogDeviceLogin + ", screenOrientation=" + this.screenOrientation + ')';
    }

    public TapTapSdkOptions(Context context, String clientId, String clientToken, RegionType region, String str, String str2, boolean z, boolean z2, JSONObject jSONObject, String str3, boolean z3, Language preferredLanguage, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(clientToken, "clientToken");
        Intrinsics.checkNotNullParameter(region, "region");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
        this.context = context;
        this.clientId = clientId;
        this.clientToken = clientToken;
        this.region = region;
        this.channel = str;
        this.gameVersion = str2;
        this.autoIAPEventEnabled = z;
        this.overrideBuiltInParameters = z2;
        this.properties = jSONObject;
        this.oaidCert = str3;
        this.enableLog = z3;
        this.preferredLanguage = preferredLanguage;
        this.useAgeRange = z4;
        this.showSwitchAccount = z5;
        this.enableToast = z6;
        this.disableReflectionOAID = z7;
        this.disableAutoLogDeviceLogin = z8;
        this.screenOrientation = i;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientToken() {
        return this.clientToken;
    }

    public /* synthetic */ TapTapSdkOptions(Context context, String str, String str2, RegionType regionType, String str3, String str4, boolean z, boolean z2, JSONObject jSONObject, String str5, boolean z3, Language language, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? "" : str, (i2 & 4) == 0 ? str2 : "", (i2 & 8) != 0 ? RegionType.CN : regionType, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? false : z, (i2 & 128) != 0 ? false : z2, (i2 & 256) != 0 ? null : jSONObject, (i2 & 512) == 0 ? str5 : null, (i2 & 1024) != 0 ? false : z3, (i2 & 2048) != 0 ? Language.AUTO : language, (i2 & 4096) != 0 ? false : z4, (i2 & 8192) != 0 ? false : z5, (i2 & 16384) != 0 ? false : z6, (i2 & 32768) != 0 ? true : z7, (i2 & 65536) == 0 ? z8 : false, (i2 & 131072) == 0 ? i : 1);
    }

    public final RegionType getRegion() {
        return this.region;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final String getGameVersion() {
        return this.gameVersion;
    }

    public final boolean getAutoIAPEventEnabled() {
        return this.autoIAPEventEnabled;
    }

    public final boolean getOverrideBuiltInParameters() {
        return this.overrideBuiltInParameters;
    }

    public final JSONObject getProperties() {
        return this.properties;
    }

    public final String getOaidCert() {
        return this.oaidCert;
    }

    public final boolean getEnableLog() {
        return this.enableLog;
    }

    public final Language getPreferredLanguage() {
        return this.preferredLanguage;
    }

    public final boolean getUseAgeRange() {
        return this.useAgeRange;
    }

    public final boolean getShowSwitchAccount() {
        return this.showSwitchAccount;
    }

    public final boolean getEnableToast() {
        return this.enableToast;
    }

    public final boolean getDisableReflectionOAID() {
        return this.disableReflectionOAID;
    }

    public final boolean getDisableAutoLogDeviceLogin() {
        return this.disableAutoLogDeviceLogin;
    }

    public final int getScreenOrientation() {
        return this.screenOrientation;
    }
}

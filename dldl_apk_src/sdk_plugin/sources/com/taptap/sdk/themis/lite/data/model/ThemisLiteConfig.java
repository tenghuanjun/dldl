package com.taptap.sdk.themis.lite.data.model;

import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThemisLiteConfig.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/taptap/sdk/themis/lite/data/model/ThemisLiteConfig;", "", "appID", "", "timeout", "", "preOneID", "initThemisTapaid", "", "initGADID", "(Ljava/lang/String;JLjava/lang/String;ZZ)V", "getAppID", "()Ljava/lang/String;", "getInitGADID", "()Z", "getInitThemisTapaid", "getPreOneID", "getTimeout", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "tap-themis-lite_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final /* data */ class ThemisLiteConfig {
    private final String appID;
    private final boolean initGADID;
    private final boolean initThemisTapaid;
    private final String preOneID;
    private final long timeout;

    public ThemisLiteConfig() {
        this(null, 0L, null, false, false, 31, null);
    }

    public static /* synthetic */ ThemisLiteConfig copy$default(ThemisLiteConfig themisLiteConfig, String str, long j, String str2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = themisLiteConfig.appID;
        }
        if ((i & 2) != 0) {
            j = themisLiteConfig.timeout;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            str2 = themisLiteConfig.preOneID;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            z = themisLiteConfig.initThemisTapaid;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = themisLiteConfig.initGADID;
        }
        return themisLiteConfig.copy(str, j2, str3, z3, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAppID() {
        return this.appID;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTimeout() {
        return this.timeout;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPreOneID() {
        return this.preOneID;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getInitThemisTapaid() {
        return this.initThemisTapaid;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getInitGADID() {
        return this.initGADID;
    }

    public final ThemisLiteConfig copy(String appID, long timeout, String preOneID, boolean initThemisTapaid, boolean initGADID) {
        Intrinsics.checkNotNullParameter(appID, "appID");
        Intrinsics.checkNotNullParameter(preOneID, "preOneID");
        return new ThemisLiteConfig(appID, timeout, preOneID, initThemisTapaid, initGADID);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ThemisLiteConfig)) {
            return false;
        }
        ThemisLiteConfig themisLiteConfig = (ThemisLiteConfig) other;
        return Intrinsics.areEqual(this.appID, themisLiteConfig.appID) && this.timeout == themisLiteConfig.timeout && Intrinsics.areEqual(this.preOneID, themisLiteConfig.preOneID) && this.initThemisTapaid == themisLiteConfig.initThemisTapaid && this.initGADID == themisLiteConfig.initGADID;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    public int hashCode() {
        int iHashCode = ((((this.appID.hashCode() * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.timeout)) * 31) + this.preOneID.hashCode()) * 31;
        boolean z = this.initThemisTapaid;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        boolean z2 = this.initGADID;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "ThemisLiteConfig(appID=" + this.appID + ", timeout=" + this.timeout + ", preOneID=" + this.preOneID + ", initThemisTapaid=" + this.initThemisTapaid + ", initGADID=" + this.initGADID + ')';
    }

    public ThemisLiteConfig(String appID, long j, String preOneID, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(appID, "appID");
        Intrinsics.checkNotNullParameter(preOneID, "preOneID");
        this.appID = appID;
        this.timeout = j;
        this.preOneID = preOneID;
        this.initThemisTapaid = z;
        this.initGADID = z2;
    }

    public /* synthetic */ ThemisLiteConfig(String str, long j, String str2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? OAIDHelper.TIMEOUT : j, (i & 4) == 0 ? str2 : "", (i & 8) != 0 ? true : z, (i & 16) != 0 ? false : z2);
    }

    public final String getAppID() {
        return this.appID;
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final String getPreOneID() {
        return this.preOneID;
    }

    public final boolean getInitThemisTapaid() {
        return this.initThemisTapaid;
    }

    public final boolean getInitGADID() {
        return this.initGADID;
    }
}

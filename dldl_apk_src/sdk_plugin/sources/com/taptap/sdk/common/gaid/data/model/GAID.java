package com.taptap.sdk.common.gaid.data.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GAID.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/taptap/sdk/common/gaid/data/model/GAID;", "", "androidAdvertiserIdValue", "", "isTrackingLimited", "", "(Ljava/lang/String;Z)V", "getAndroidAdvertiserIdValue", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final /* data */ class GAID {
    private final String androidAdvertiserIdValue;
    private final boolean isTrackingLimited;

    public static /* synthetic */ GAID copy$default(GAID gaid, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gaid.androidAdvertiserIdValue;
        }
        if ((i & 2) != 0) {
            z = gaid.isTrackingLimited;
        }
        return gaid.copy(str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAndroidAdvertiserIdValue() {
        return this.androidAdvertiserIdValue;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsTrackingLimited() {
        return this.isTrackingLimited;
    }

    public final GAID copy(String androidAdvertiserIdValue, boolean isTrackingLimited) {
        return new GAID(androidAdvertiserIdValue, isTrackingLimited);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GAID)) {
            return false;
        }
        GAID gaid = (GAID) other;
        return Intrinsics.areEqual(this.androidAdvertiserIdValue, gaid.androidAdvertiserIdValue) && this.isTrackingLimited == gaid.isTrackingLimited;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        String str = this.androidAdvertiserIdValue;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        boolean z = this.isTrackingLimited;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "GAID(androidAdvertiserIdValue=" + this.androidAdvertiserIdValue + ", isTrackingLimited=" + this.isTrackingLimited + ')';
    }

    public GAID(String str, boolean z) {
        this.androidAdvertiserIdValue = str;
        this.isTrackingLimited = z;
    }

    public final String getAndroidAdvertiserIdValue() {
        return this.androidAdvertiserIdValue;
    }

    public final boolean isTrackingLimited() {
        return this.isTrackingLimited;
    }
}

package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameDetailExtraBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003Jc\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0007HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006("}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameDetailExtraBean;", "", "authInfo", "", "filing", "fitAge", CommonConstants.key_gameId, "", "information", "policy", "size", "authority", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthInfo", "()Ljava/lang/String;", "getAuthority", "getFiling", "getFitAge", "getGameId", "()I", "getInformation", "getPolicy", "getSize", "getVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GameDetailExtraBean {
    public static final int $stable = 0;
    private final String authInfo;
    private final String authority;
    private final String filing;
    private final String fitAge;
    private final int gameId;
    private final String information;
    private final String policy;
    private final String size;
    private final String version;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAuthInfo() {
        return this.authInfo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getFiling() {
        return this.filing;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFitAge() {
        return this.fitAge;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getInformation() {
        return this.information;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPolicy() {
        return this.policy;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getAuthority() {
        return this.authority;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    public final GameDetailExtraBean copy(String authInfo, String filing, String fitAge, int gameId, String information, String policy, String size, String authority, String version) {
        Intrinsics.checkNotNullParameter(authInfo, "authInfo");
        Intrinsics.checkNotNullParameter(filing, "filing");
        Intrinsics.checkNotNullParameter(fitAge, "fitAge");
        Intrinsics.checkNotNullParameter(information, "information");
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(authority, "authority");
        Intrinsics.checkNotNullParameter(version, "version");
        return new GameDetailExtraBean(authInfo, filing, fitAge, gameId, information, policy, size, authority, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GameDetailExtraBean)) {
            return false;
        }
        GameDetailExtraBean gameDetailExtraBean = (GameDetailExtraBean) other;
        return Intrinsics.areEqual(this.authInfo, gameDetailExtraBean.authInfo) && Intrinsics.areEqual(this.filing, gameDetailExtraBean.filing) && Intrinsics.areEqual(this.fitAge, gameDetailExtraBean.fitAge) && this.gameId == gameDetailExtraBean.gameId && Intrinsics.areEqual(this.information, gameDetailExtraBean.information) && Intrinsics.areEqual(this.policy, gameDetailExtraBean.policy) && Intrinsics.areEqual(this.size, gameDetailExtraBean.size) && Intrinsics.areEqual(this.authority, gameDetailExtraBean.authority) && Intrinsics.areEqual(this.version, gameDetailExtraBean.version);
    }

    public int hashCode() {
        return (((((((((((((((this.authInfo.hashCode() * 31) + this.filing.hashCode()) * 31) + this.fitAge.hashCode()) * 31) + this.gameId) * 31) + this.information.hashCode()) * 31) + this.policy.hashCode()) * 31) + this.size.hashCode()) * 31) + this.authority.hashCode()) * 31) + this.version.hashCode();
    }

    public String toString() {
        return "GameDetailExtraBean(authInfo=" + this.authInfo + ", filing=" + this.filing + ", fitAge=" + this.fitAge + ", gameId=" + this.gameId + ", information=" + this.information + ", policy=" + this.policy + ", size=" + this.size + ", authority=" + this.authority + ", version=" + this.version + ")";
    }

    public GameDetailExtraBean(String authInfo, String filing, String fitAge, int i, String information, String policy, String size, String authority, String version) {
        Intrinsics.checkNotNullParameter(authInfo, "authInfo");
        Intrinsics.checkNotNullParameter(filing, "filing");
        Intrinsics.checkNotNullParameter(fitAge, "fitAge");
        Intrinsics.checkNotNullParameter(information, "information");
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(authority, "authority");
        Intrinsics.checkNotNullParameter(version, "version");
        this.authInfo = authInfo;
        this.filing = filing;
        this.fitAge = fitAge;
        this.gameId = i;
        this.information = information;
        this.policy = policy;
        this.size = size;
        this.authority = authority;
        this.version = version;
    }

    public final String getAuthInfo() {
        return this.authInfo;
    }

    public final String getFiling() {
        return this.filing;
    }

    public final String getFitAge() {
        return this.fitAge;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getInformation() {
        return this.information;
    }

    public final String getPolicy() {
        return this.policy;
    }

    public final String getSize() {
        return this.size;
    }

    public final String getAuthority() {
        return this.authority;
    }

    public final String getVersion() {
        return this.version;
    }
}

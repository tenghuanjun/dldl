package com.cy.yyjia.zhe28.domain;

import com.tencent.connect.common.Constants;
import com.tencent.open.SocialOperation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WxLoginResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jn\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0005HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WxLoginResult;", "", Constants.PARAM_ACCESS_TOKEN, "", Constants.PARAM_EXPIRES_IN, "", "openid", "refresh_token", Constants.PARAM_SCOPE, SocialOperation.GAME_UNION_ID, "errcode", "errmsg", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccess_token", "()Ljava/lang/String;", "getErrcode", "getErrmsg", "getExpires_in", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOpenid", "getRefresh_token", "getScope", "getUnionid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/cy/yyjia/zhe28/domain/WxLoginResult;", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class WxLoginResult {
    public static final int $stable = 0;
    private final String access_token;
    private final String errcode;
    private final String errmsg;
    private final Integer expires_in;
    private final String openid;
    private final String refresh_token;
    private final String scope;
    private final String unionid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccess_token() {
        return this.access_token;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getExpires_in() {
        return this.expires_in;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOpenid() {
        return this.openid;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getRefresh_token() {
        return this.refresh_token;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getScope() {
        return this.scope;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUnionid() {
        return this.unionid;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getErrcode() {
        return this.errcode;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getErrmsg() {
        return this.errmsg;
    }

    public final WxLoginResult copy(String access_token, Integer expires_in, String openid, String refresh_token, String scope, String unionid, String errcode, String errmsg) {
        return new WxLoginResult(access_token, expires_in, openid, refresh_token, scope, unionid, errcode, errmsg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WxLoginResult)) {
            return false;
        }
        WxLoginResult wxLoginResult = (WxLoginResult) other;
        return Intrinsics.areEqual(this.access_token, wxLoginResult.access_token) && Intrinsics.areEqual(this.expires_in, wxLoginResult.expires_in) && Intrinsics.areEqual(this.openid, wxLoginResult.openid) && Intrinsics.areEqual(this.refresh_token, wxLoginResult.refresh_token) && Intrinsics.areEqual(this.scope, wxLoginResult.scope) && Intrinsics.areEqual(this.unionid, wxLoginResult.unionid) && Intrinsics.areEqual(this.errcode, wxLoginResult.errcode) && Intrinsics.areEqual(this.errmsg, wxLoginResult.errmsg);
    }

    public int hashCode() {
        String str = this.access_token;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.expires_in;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.openid;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.refresh_token;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.scope;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.unionid;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.errcode;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.errmsg;
        return iHashCode7 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "WxLoginResult(access_token=" + this.access_token + ", expires_in=" + this.expires_in + ", openid=" + this.openid + ", refresh_token=" + this.refresh_token + ", scope=" + this.scope + ", unionid=" + this.unionid + ", errcode=" + this.errcode + ", errmsg=" + this.errmsg + ")";
    }

    public WxLoginResult(String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.access_token = str;
        this.expires_in = num;
        this.openid = str2;
        this.refresh_token = str3;
        this.scope = str4;
        this.unionid = str5;
        this.errcode = str6;
        this.errmsg = str7;
    }

    public final String getAccess_token() {
        return this.access_token;
    }

    public /* synthetic */ WxLoginResult(String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? 0 : num, str2, str3, str4, str5, (i & 64) != 0 ? "" : str6, (i & 128) != 0 ? "" : str7);
    }

    public final Integer getExpires_in() {
        return this.expires_in;
    }

    public final String getOpenid() {
        return this.openid;
    }

    public final String getRefresh_token() {
        return this.refresh_token;
    }

    public final String getScope() {
        return this.scope;
    }

    public final String getUnionid() {
        return this.unionid;
    }

    public final String getErrcode() {
        return this.errcode;
    }

    public final String getErrmsg() {
        return this.errmsg;
    }
}

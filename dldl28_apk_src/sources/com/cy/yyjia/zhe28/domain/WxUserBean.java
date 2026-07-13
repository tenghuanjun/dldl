package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialOperation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WxUserBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J~\u0010%\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\fHÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010¨\u0006,"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WxUserBean;", "", "city", "", "country", "headimgurl", "nickname", "openid", "privilege", "", "province", "sex", "", SocialOperation.GAME_UNION_ID, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getCity", "()Ljava/lang/String;", "getCountry", "getHeadimgurl", "getNickname", "getOpenid", "getPrivilege", "()Ljava/util/List;", "getProvince", "getSex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUnionid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/cy/yyjia/zhe28/domain/WxUserBean;", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class WxUserBean {
    public static final int $stable = 8;
    private final String city;
    private final String country;
    private final String headimgurl;
    private final String nickname;
    private final String openid;
    private final List<String> privilege;
    private final String province;
    private final Integer sex;
    private final String unionid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getHeadimgurl() {
        return this.headimgurl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getNickname() {
        return this.nickname;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOpenid() {
        return this.openid;
    }

    public final List<String> component6() {
        return this.privilege;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getProvince() {
        return this.province;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getSex() {
        return this.sex;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getUnionid() {
        return this.unionid;
    }

    public final WxUserBean copy(String city, String country, String headimgurl, String nickname, String openid, List<String> privilege, String province, Integer sex, String unionid) {
        Intrinsics.checkNotNullParameter(privilege, "privilege");
        return new WxUserBean(city, country, headimgurl, nickname, openid, privilege, province, sex, unionid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WxUserBean)) {
            return false;
        }
        WxUserBean wxUserBean = (WxUserBean) other;
        return Intrinsics.areEqual(this.city, wxUserBean.city) && Intrinsics.areEqual(this.country, wxUserBean.country) && Intrinsics.areEqual(this.headimgurl, wxUserBean.headimgurl) && Intrinsics.areEqual(this.nickname, wxUserBean.nickname) && Intrinsics.areEqual(this.openid, wxUserBean.openid) && Intrinsics.areEqual(this.privilege, wxUserBean.privilege) && Intrinsics.areEqual(this.province, wxUserBean.province) && Intrinsics.areEqual(this.sex, wxUserBean.sex) && Intrinsics.areEqual(this.unionid, wxUserBean.unionid);
    }

    public int hashCode() {
        String str = this.city;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.country;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.headimgurl;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.nickname;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.openid;
        int iHashCode5 = (((iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.privilege.hashCode()) * 31;
        String str6 = this.province;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num = this.sex;
        int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        String str7 = this.unionid;
        return iHashCode7 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "WxUserBean(city=" + this.city + ", country=" + this.country + ", headimgurl=" + this.headimgurl + ", nickname=" + this.nickname + ", openid=" + this.openid + ", privilege=" + this.privilege + ", province=" + this.province + ", sex=" + this.sex + ", unionid=" + this.unionid + ")";
    }

    public WxUserBean(String str, String str2, String str3, String str4, String str5, List<String> privilege, String str6, Integer num, String str7) {
        Intrinsics.checkNotNullParameter(privilege, "privilege");
        this.city = str;
        this.country = str2;
        this.headimgurl = str3;
        this.nickname = str4;
        this.openid = str5;
        this.privilege = privilege;
        this.province = str6;
        this.sex = num;
        this.unionid = str7;
    }

    public /* synthetic */ WxUserBean(String str, String str2, String str3, String str4, String str5, List list, String str6, Integer num, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, list, (i & 64) != 0 ? "" : str6, (i & 128) != 0 ? 0 : num, (i & 256) != 0 ? "" : str7);
    }

    public final String getCity() {
        return this.city;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getHeadimgurl() {
        return this.headimgurl;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final String getOpenid() {
        return this.openid;
    }

    public final List<String> getPrivilege() {
        return this.privilege;
    }

    public final String getProvince() {
        return this.province;
    }

    public final Integer getSex() {
        return this.sex;
    }

    public final String getUnionid() {
        return this.unionid;
    }
}

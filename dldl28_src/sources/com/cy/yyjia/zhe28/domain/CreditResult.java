package com.cy.yyjia.zhe28.domain;

import com.tencent.connect.common.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: CreditResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\b\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010\u0014J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\bHÆ\u0003J\t\u0010)\u001a\u00020\bHÆ\u0003J\t\u0010*\u001a\u00020\bHÆ\u0003J\t\u0010+\u001a\u00020\bHÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\bHÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\bHÆ\u0003J©\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u0003HÆ\u0001J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\u0003HÖ\u0001J\t\u0010<\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0011\u0010\u000e\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\u0010\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0011\u0010\u0012\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016¨\u0006="}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CreditResult;", "", "adminUid", "", "credit", "creditId", "currCredit", "dateline", "", "experience", "extend", "id", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "location", Constants.PARAM_PLATFORM, "receive", "rewardType", "type", "typeName", "uid", "(IIIILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getAdminUid", "()I", "getCredit", "getCreditId", "getCurrCredit", "getDateline", "()Ljava/lang/String;", "getExperience", "getExtend", "getId", "getIp", "getLocation", "getPlatform", "getReceive", "getRewardType", "getType", "getTypeName", "getUid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class CreditResult {
    public static final int $stable = 0;
    private final int adminUid;
    private final int credit;
    private final int creditId;
    private final int currCredit;
    private final String dateline;
    private final int experience;
    private final String extend;
    private final int id;
    private final String ip;
    private final String location;
    private final String platform;
    private final String receive;
    private final String rewardType;
    private final String type;
    private final String typeName;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAdminUid() {
        return this.adminUid;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getReceive() {
        return this.receive;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getRewardType() {
        return this.rewardType;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getTypeName() {
        return this.typeName;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getCredit() {
        return this.credit;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCreditId() {
        return this.creditId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getCurrCredit() {
        return this.currCredit;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getExperience() {
        return this.experience;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getExtend() {
        return this.extend;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getIp() {
        return this.ip;
    }

    public final CreditResult copy(int adminUid, int credit, int creditId, int currCredit, String dateline, int experience, String extend, int id, String ip, String location, String platform, String receive, String rewardType, String type, String typeName, int uid) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(extend, "extend");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(receive, "receive");
        Intrinsics.checkNotNullParameter(rewardType, "rewardType");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(typeName, "typeName");
        return new CreditResult(adminUid, credit, creditId, currCredit, dateline, experience, extend, id, ip, location, platform, receive, rewardType, type, typeName, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CreditResult)) {
            return false;
        }
        CreditResult creditResult = (CreditResult) other;
        return this.adminUid == creditResult.adminUid && this.credit == creditResult.credit && this.creditId == creditResult.creditId && this.currCredit == creditResult.currCredit && Intrinsics.areEqual(this.dateline, creditResult.dateline) && this.experience == creditResult.experience && Intrinsics.areEqual(this.extend, creditResult.extend) && this.id == creditResult.id && Intrinsics.areEqual(this.ip, creditResult.ip) && Intrinsics.areEqual(this.location, creditResult.location) && Intrinsics.areEqual(this.platform, creditResult.platform) && Intrinsics.areEqual(this.receive, creditResult.receive) && Intrinsics.areEqual(this.rewardType, creditResult.rewardType) && Intrinsics.areEqual(this.type, creditResult.type) && Intrinsics.areEqual(this.typeName, creditResult.typeName) && this.uid == creditResult.uid;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.adminUid * 31) + this.credit) * 31) + this.creditId) * 31) + this.currCredit) * 31) + this.dateline.hashCode()) * 31) + this.experience) * 31) + this.extend.hashCode()) * 31) + this.id) * 31) + this.ip.hashCode()) * 31) + this.location.hashCode()) * 31) + this.platform.hashCode()) * 31) + this.receive.hashCode()) * 31) + this.rewardType.hashCode()) * 31) + this.type.hashCode()) * 31) + this.typeName.hashCode()) * 31) + this.uid;
    }

    public String toString() {
        return "CreditResult(adminUid=" + this.adminUid + ", credit=" + this.credit + ", creditId=" + this.creditId + ", currCredit=" + this.currCredit + ", dateline=" + this.dateline + ", experience=" + this.experience + ", extend=" + this.extend + ", id=" + this.id + ", ip=" + this.ip + ", location=" + this.location + ", platform=" + this.platform + ", receive=" + this.receive + ", rewardType=" + this.rewardType + ", type=" + this.type + ", typeName=" + this.typeName + ", uid=" + this.uid + ")";
    }

    public CreditResult(int i, int i2, int i3, int i4, String dateline, int i5, String extend, int i6, String ip, String location, String platform, String receive, String rewardType, String type, String typeName, int i7) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(extend, "extend");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(receive, "receive");
        Intrinsics.checkNotNullParameter(rewardType, "rewardType");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(typeName, "typeName");
        this.adminUid = i;
        this.credit = i2;
        this.creditId = i3;
        this.currCredit = i4;
        this.dateline = dateline;
        this.experience = i5;
        this.extend = extend;
        this.id = i6;
        this.ip = ip;
        this.location = location;
        this.platform = platform;
        this.receive = receive;
        this.rewardType = rewardType;
        this.type = type;
        this.typeName = typeName;
        this.uid = i7;
    }

    public final int getAdminUid() {
        return this.adminUid;
    }

    public final int getCredit() {
        return this.credit;
    }

    public final int getCreditId() {
        return this.creditId;
    }

    public final int getCurrCredit() {
        return this.currCredit;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final int getExperience() {
        return this.experience;
    }

    public final String getExtend() {
        return this.extend;
    }

    public final int getId() {
        return this.id;
    }

    public final String getIp() {
        return this.ip;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getReceive() {
        return this.receive;
    }

    public final String getRewardType() {
        return this.rewardType;
    }

    public final String getType() {
        return this.type;
    }

    public final String getTypeName() {
        return this.typeName;
    }

    public final int getUid() {
        return this.uid;
    }
}

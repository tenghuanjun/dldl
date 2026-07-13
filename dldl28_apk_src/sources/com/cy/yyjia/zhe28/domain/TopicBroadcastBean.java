package com.cy.yyjia.zhe28.domain;

import java.text.SimpleDateFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TopicBroadcastBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003¢\u0006\u0002\u0010\u0011J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0001HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u008b\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u00103\u001a\u00020\u0005J\t\u00104\u001a\u00020\u0003HÖ\u0001J\t\u00105\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013¨\u00066"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicBroadcastBean;", "", "credit", "", "dateline", "", "express", "id", "lottery_id", "lottery_num", "msg", "name", "rewad_id", "reward_type", "status", "type", "uid", "(ILjava/lang/String;Ljava/lang/Object;IIILjava/lang/String;Ljava/lang/String;IIIII)V", "getCredit", "()I", "getDateline", "()Ljava/lang/String;", "getExpress", "()Ljava/lang/Object;", "getId", "getLottery_id", "getLottery_num", "getMsg", "getName", "getRewad_id", "getReward_type", "getStatus", "getType", "getUid", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getTimeStr", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class TopicBroadcastBean {
    public static final int $stable = 8;
    private final int credit;
    private final String dateline;
    private final Object express;
    private final int id;
    private final int lottery_id;
    private final int lottery_num;
    private final String msg;
    private final String name;
    private final int rewad_id;
    private final int reward_type;
    private final int status;
    private final int type;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCredit() {
        return this.credit;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getReward_type() {
        return this.reward_type;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Object getExpress() {
        return this.express;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getLottery_id() {
        return this.lottery_id;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getLottery_num() {
        return this.lottery_num;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getRewad_id() {
        return this.rewad_id;
    }

    public final TopicBroadcastBean copy(int credit, String dateline, Object express, int id, int lottery_id, int lottery_num, String msg, String name, int rewad_id, int reward_type, int status, int type, int uid) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(express, "express");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(name, "name");
        return new TopicBroadcastBean(credit, dateline, express, id, lottery_id, lottery_num, msg, name, rewad_id, reward_type, status, type, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TopicBroadcastBean)) {
            return false;
        }
        TopicBroadcastBean topicBroadcastBean = (TopicBroadcastBean) other;
        return this.credit == topicBroadcastBean.credit && Intrinsics.areEqual(this.dateline, topicBroadcastBean.dateline) && Intrinsics.areEqual(this.express, topicBroadcastBean.express) && this.id == topicBroadcastBean.id && this.lottery_id == topicBroadcastBean.lottery_id && this.lottery_num == topicBroadcastBean.lottery_num && Intrinsics.areEqual(this.msg, topicBroadcastBean.msg) && Intrinsics.areEqual(this.name, topicBroadcastBean.name) && this.rewad_id == topicBroadcastBean.rewad_id && this.reward_type == topicBroadcastBean.reward_type && this.status == topicBroadcastBean.status && this.type == topicBroadcastBean.type && this.uid == topicBroadcastBean.uid;
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.credit * 31) + this.dateline.hashCode()) * 31) + this.express.hashCode()) * 31) + this.id) * 31) + this.lottery_id) * 31) + this.lottery_num) * 31) + this.msg.hashCode()) * 31) + this.name.hashCode()) * 31) + this.rewad_id) * 31) + this.reward_type) * 31) + this.status) * 31) + this.type) * 31) + this.uid;
    }

    public String toString() {
        return "TopicBroadcastBean(credit=" + this.credit + ", dateline=" + this.dateline + ", express=" + this.express + ", id=" + this.id + ", lottery_id=" + this.lottery_id + ", lottery_num=" + this.lottery_num + ", msg=" + this.msg + ", name=" + this.name + ", rewad_id=" + this.rewad_id + ", reward_type=" + this.reward_type + ", status=" + this.status + ", type=" + this.type + ", uid=" + this.uid + ")";
    }

    public TopicBroadcastBean(int i, String dateline, Object express, int i2, int i3, int i4, String msg, String name, int i5, int i6, int i7, int i8, int i9) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(express, "express");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(name, "name");
        this.credit = i;
        this.dateline = dateline;
        this.express = express;
        this.id = i2;
        this.lottery_id = i3;
        this.lottery_num = i4;
        this.msg = msg;
        this.name = name;
        this.rewad_id = i5;
        this.reward_type = i6;
        this.status = i7;
        this.type = i8;
        this.uid = i9;
    }

    public final int getCredit() {
        return this.credit;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final Object getExpress() {
        return this.express;
    }

    public final int getId() {
        return this.id;
    }

    public final int getLottery_id() {
        return this.lottery_id;
    }

    public final int getLottery_num() {
        return this.lottery_num;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final String getName() {
        return this.name;
    }

    public final int getRewad_id() {
        return this.rewad_id;
    }

    public final int getReward_type() {
        return this.reward_type;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int getType() {
        return this.type;
    }

    public final int getUid() {
        return this.uid;
    }

    public final String getTimeStr() {
        String str = new SimpleDateFormat("MM-dd").format(Long.valueOf(Long.parseLong(this.dateline + "000")));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}

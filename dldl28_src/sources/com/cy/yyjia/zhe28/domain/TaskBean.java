package com.cy.yyjia.zhe28.domain;

import android.text.TextUtils;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: TaskBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b]\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001Bé\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\u0006\u0010\u001b\u001a\u00020\u0005\u0012\u0006\u0010\u001c\u001a\u00020\u0005\u0012\u0006\u0010\u001d\u001a\u00020\u0005\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010 J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\t\u0010Q\u001a\u00020\u0005HÆ\u0003J\t\u0010R\u001a\u00020\u0003HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\t\u0010T\u001a\u00020\u0005HÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\u0005HÆ\u0003J\t\u0010\\\u001a\u00020\u0005HÆ\u0003J\t\u0010]\u001a\u00020\u0005HÆ\u0003J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\t\u0010_\u001a\u00020\u0005HÆ\u0003J\t\u0010`\u001a\u00020\u0005HÆ\u0003J¥\u0002\u0010a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010b\u001a\u00020c2\b\u0010d\u001a\u0004\u0018\u00010eHÖ\u0003J\u0006\u0010f\u001a\u00020\u0005J\u0006\u0010g\u001a\u00020\u0005J\u0006\u0010h\u001a\u00020\u0005J\t\u0010i\u001a\u00020\u0003HÖ\u0001J\t\u0010j\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R&\u0010)\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00058G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010$\"\u0004\b+\u0010,R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010$R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010$R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\"R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\"R\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010$R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010$\"\u0004\b5\u0010,R\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010$R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010$R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010$R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010$R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010$R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010$R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010$R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010$R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\"R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b@\u0010$R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010$R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\"R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010$R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bD\u0010$¨\u0006k"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TaskBean;", "Landroidx/databinding/BaseObservable;", "credit", "", "cycle", "", "dateline", SocialConstants.PARAM_COMMENT, "endTime", "experience", "id", "name", "opName", "pic", Constants.PARAM_PLATFORM, "remark", "rewardType", "shortDesc", "sort", Constant.START_TIME, "status", "giveType", "giveTypeName", "welfare", "times", "log_id", "type", "num", "payMoney", "money", "target", "current", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCredit", "()I", "getCurrent", "()Ljava/lang/String;", "getCycle", "getDateline", "getDescription", DBHelper.COL_VALUE, "done", "getDone", "setDone", "(Ljava/lang/String;)V", "getEndTime", "getExperience", "getGiveType", "getGiveTypeName", "getId", "getLog_id", "getMoney", "getName", "setName", "getNum", "getOpName", "getPayMoney", "getPic", "getPlatform", "getRemark", "getRewardType", "getShortDesc", "getSort", "getStartTime", "getStatus", "getTarget", "getTimes", "getType", "getWelfare", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "getBtnText", "getCreditText", "getProgress", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class TaskBean extends BaseObservable {
    public static final int $stable = 8;
    private final int credit;
    private final String current;
    private final String cycle;
    private final String dateline;
    private final String description;
    private String done;
    private final String endTime;
    private final String experience;
    private final String giveType;
    private final String giveTypeName;
    private final int id;
    private final int log_id;
    private final String money;
    private String name;
    private final String num;
    private final String opName;
    private final String payMoney;
    private final String pic;
    private final String platform;
    private final String remark;
    private final String rewardType;
    private final String shortDesc;
    private final int sort;
    private final int startTime;
    private final String status;
    private final String target;
    private final int times;
    private final String type;
    private final String welfare;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCredit() {
        return this.credit;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getPic() {
        return this.pic;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getRemark() {
        return this.remark;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getRewardType() {
        return this.rewardType;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getShortDesc() {
        return this.shortDesc;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getSort() {
        return this.sort;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getGiveType() {
        return this.giveType;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getGiveTypeName() {
        return this.giveTypeName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCycle() {
        return this.cycle;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getWelfare() {
        return this.welfare;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final int getTimes() {
        return this.times;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final int getLog_id() {
        return this.log_id;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getNum() {
        return this.num;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getPayMoney() {
        return this.payMoney;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final String getTarget() {
        return this.target;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final String getCurrent() {
        return this.current;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getExperience() {
        return this.experience;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getOpName() {
        return this.opName;
    }

    public final TaskBean copy(int credit, String cycle, String dateline, String description, String endTime, String experience, int id, String name, String opName, String pic, String platform, String remark, String rewardType, String shortDesc, int sort, int startTime, String status, String giveType, String giveTypeName, String welfare, int times, int log_id, String type, String num, String payMoney, String money, String target, String current) {
        Intrinsics.checkNotNullParameter(cycle, "cycle");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(experience, "experience");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(opName, "opName");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(rewardType, "rewardType");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(giveType, "giveType");
        Intrinsics.checkNotNullParameter(giveTypeName, "giveTypeName");
        Intrinsics.checkNotNullParameter(welfare, "welfare");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(num, "num");
        Intrinsics.checkNotNullParameter(payMoney, "payMoney");
        Intrinsics.checkNotNullParameter(money, "money");
        return new TaskBean(credit, cycle, dateline, description, endTime, experience, id, name, opName, pic, platform, remark, rewardType, shortDesc, sort, startTime, status, giveType, giveTypeName, welfare, times, log_id, type, num, payMoney, money, target, current);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskBean)) {
            return false;
        }
        TaskBean taskBean = (TaskBean) other;
        return this.credit == taskBean.credit && Intrinsics.areEqual(this.cycle, taskBean.cycle) && Intrinsics.areEqual(this.dateline, taskBean.dateline) && Intrinsics.areEqual(this.description, taskBean.description) && Intrinsics.areEqual(this.endTime, taskBean.endTime) && Intrinsics.areEqual(this.experience, taskBean.experience) && this.id == taskBean.id && Intrinsics.areEqual(this.name, taskBean.name) && Intrinsics.areEqual(this.opName, taskBean.opName) && Intrinsics.areEqual(this.pic, taskBean.pic) && Intrinsics.areEqual(this.platform, taskBean.platform) && Intrinsics.areEqual(this.remark, taskBean.remark) && Intrinsics.areEqual(this.rewardType, taskBean.rewardType) && Intrinsics.areEqual(this.shortDesc, taskBean.shortDesc) && this.sort == taskBean.sort && this.startTime == taskBean.startTime && Intrinsics.areEqual(this.status, taskBean.status) && Intrinsics.areEqual(this.giveType, taskBean.giveType) && Intrinsics.areEqual(this.giveTypeName, taskBean.giveTypeName) && Intrinsics.areEqual(this.welfare, taskBean.welfare) && this.times == taskBean.times && this.log_id == taskBean.log_id && Intrinsics.areEqual(this.type, taskBean.type) && Intrinsics.areEqual(this.num, taskBean.num) && Intrinsics.areEqual(this.payMoney, taskBean.payMoney) && Intrinsics.areEqual(this.money, taskBean.money) && Intrinsics.areEqual(this.target, taskBean.target) && Intrinsics.areEqual(this.current, taskBean.current);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((this.credit * 31) + this.cycle.hashCode()) * 31) + this.dateline.hashCode()) * 31) + this.description.hashCode()) * 31) + this.endTime.hashCode()) * 31) + this.experience.hashCode()) * 31) + this.id) * 31) + this.name.hashCode()) * 31) + this.opName.hashCode()) * 31) + this.pic.hashCode()) * 31) + this.platform.hashCode()) * 31) + this.remark.hashCode()) * 31) + this.rewardType.hashCode()) * 31) + this.shortDesc.hashCode()) * 31) + this.sort) * 31) + this.startTime) * 31) + this.status.hashCode()) * 31) + this.giveType.hashCode()) * 31) + this.giveTypeName.hashCode()) * 31) + this.welfare.hashCode()) * 31) + this.times) * 31) + this.log_id) * 31) + this.type.hashCode()) * 31) + this.num.hashCode()) * 31) + this.payMoney.hashCode()) * 31) + this.money.hashCode()) * 31;
        String str = this.target;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.current;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TaskBean(credit=" + this.credit + ", cycle=" + this.cycle + ", dateline=" + this.dateline + ", description=" + this.description + ", endTime=" + this.endTime + ", experience=" + this.experience + ", id=" + this.id + ", name=" + this.name + ", opName=" + this.opName + ", pic=" + this.pic + ", platform=" + this.platform + ", remark=" + this.remark + ", rewardType=" + this.rewardType + ", shortDesc=" + this.shortDesc + ", sort=" + this.sort + ", startTime=" + this.startTime + ", status=" + this.status + ", giveType=" + this.giveType + ", giveTypeName=" + this.giveTypeName + ", welfare=" + this.welfare + ", times=" + this.times + ", log_id=" + this.log_id + ", type=" + this.type + ", num=" + this.num + ", payMoney=" + this.payMoney + ", money=" + this.money + ", target=" + this.target + ", current=" + this.current + ")";
    }

    public final int getCredit() {
        return this.credit;
    }

    public final String getCycle() {
        return this.cycle;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getEndTime() {
        return this.endTime;
    }

    public final String getExperience() {
        return this.experience;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getOpName() {
        return this.opName;
    }

    public final String getPic() {
        return this.pic;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getRemark() {
        return this.remark;
    }

    public final String getRewardType() {
        return this.rewardType;
    }

    public final String getShortDesc() {
        return this.shortDesc;
    }

    public final int getSort() {
        return this.sort;
    }

    public final int getStartTime() {
        return this.startTime;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getGiveType() {
        return this.giveType;
    }

    public final String getGiveTypeName() {
        return this.giveTypeName;
    }

    public final String getWelfare() {
        return this.welfare;
    }

    public final int getTimes() {
        return this.times;
    }

    public final int getLog_id() {
        return this.log_id;
    }

    public final String getType() {
        return this.type;
    }

    public final String getNum() {
        return this.num;
    }

    public final String getPayMoney() {
        return this.payMoney;
    }

    public final String getMoney() {
        return this.money;
    }

    public final String getTarget() {
        return this.target;
    }

    public final String getCurrent() {
        return this.current;
    }

    public TaskBean(int i, String cycle, String dateline, String description, String endTime, String experience, int i2, String name, String opName, String pic, String platform, String remark, String rewardType, String shortDesc, int i3, int i4, String status, String giveType, String giveTypeName, String welfare, int i5, int i6, String type, String num, String payMoney, String money, String str, String str2) {
        Intrinsics.checkNotNullParameter(cycle, "cycle");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(experience, "experience");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(opName, "opName");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(remark, "remark");
        Intrinsics.checkNotNullParameter(rewardType, "rewardType");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(giveType, "giveType");
        Intrinsics.checkNotNullParameter(giveTypeName, "giveTypeName");
        Intrinsics.checkNotNullParameter(welfare, "welfare");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(num, "num");
        Intrinsics.checkNotNullParameter(payMoney, "payMoney");
        Intrinsics.checkNotNullParameter(money, "money");
        this.credit = i;
        this.cycle = cycle;
        this.dateline = dateline;
        this.description = description;
        this.endTime = endTime;
        this.experience = experience;
        this.id = i2;
        this.name = name;
        this.opName = opName;
        this.pic = pic;
        this.platform = platform;
        this.remark = remark;
        this.rewardType = rewardType;
        this.shortDesc = shortDesc;
        this.sort = i3;
        this.startTime = i4;
        this.status = status;
        this.giveType = giveType;
        this.giveTypeName = giveTypeName;
        this.welfare = welfare;
        this.times = i5;
        this.log_id = i6;
        this.type = type;
        this.num = num;
        this.payMoney = payMoney;
        this.money = money;
        this.target = str;
        this.current = str2;
        this.done = "";
    }

    @Bindable
    public final String getDone() {
        return this.done;
    }

    public final void setDone(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.done = value;
        notifyPropertyChanged(28);
    }

    public final String getBtnText() {
        String str = this.done;
        return Intrinsics.areEqual(str, "not_receive") ? "待领取" : Intrinsics.areEqual(str, BooleanUtils.YES) ? "已完成" : "未完成";
    }

    public final String getCreditText() {
        if (StringsKt.contains$default((CharSequence) this.name, (CharSequence) "签到", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) this.name, (CharSequence) "充值送积分", false, 2, (Object) null) || Double.parseDouble(this.num) == 0.0d) {
            return "";
        }
        return "+" + this.num + this.giveTypeName;
    }

    public final String getProgress() {
        if (!TextUtils.isEmpty(this.target) && !Intrinsics.areEqual(this.target, "0")) {
            return "（" + this.current + "/" + this.target + "）";
        }
        return "";
    }
}

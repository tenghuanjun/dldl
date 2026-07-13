package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: FeedbackRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003Ji\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\tHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010*\u001a\u00020\u0005J\u0006\u0010+\u001a\u00020\u0005J\u0006\u0010,\u001a\u00020\u0005J\t\u0010-\u001a\u00020\tHÖ\u0001J\t\u0010.\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017¨\u0006/"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/FeedbackRecordBean;", "", "createTime", "", SocialConstants.PARAM_APP_DESC, "", "files", "", "id", "", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "ipaddress", "status", "type", "uid", "(JLjava/lang/String;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;III)V", "getCreateTime", "()J", "getDesc", "()Ljava/lang/String;", "getFiles", "()Ljava/util/List;", "getId", "()I", "getIp", "getIpaddress", "getStatus", "getType", "getUid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getStatusText", "getTimeStr", "getTypeText", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class FeedbackRecordBean {
    public static final int $stable = 8;
    private final long createTime;
    private final String desc;
    private final List<String> files;
    private final int id;
    private final String ip;
    private final String ipaddress;
    private final int status;
    private final int type;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    public final List<String> component3() {
        return this.files;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIp() {
        return this.ip;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getIpaddress() {
        return this.ipaddress;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    public final FeedbackRecordBean copy(long createTime, String desc, List<String> files, int id, String ip, String ipaddress, int status, int type, int uid) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(files, "files");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(ipaddress, "ipaddress");
        return new FeedbackRecordBean(createTime, desc, files, id, ip, ipaddress, status, type, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeedbackRecordBean)) {
            return false;
        }
        FeedbackRecordBean feedbackRecordBean = (FeedbackRecordBean) other;
        return this.createTime == feedbackRecordBean.createTime && Intrinsics.areEqual(this.desc, feedbackRecordBean.desc) && Intrinsics.areEqual(this.files, feedbackRecordBean.files) && this.id == feedbackRecordBean.id && Intrinsics.areEqual(this.ip, feedbackRecordBean.ip) && Intrinsics.areEqual(this.ipaddress, feedbackRecordBean.ipaddress) && this.status == feedbackRecordBean.status && this.type == feedbackRecordBean.type && this.uid == feedbackRecordBean.uid;
    }

    public int hashCode() {
        return (((((((((((((((GMTitleBean$$ExternalSyntheticBackport0.m(this.createTime) * 31) + this.desc.hashCode()) * 31) + this.files.hashCode()) * 31) + this.id) * 31) + this.ip.hashCode()) * 31) + this.ipaddress.hashCode()) * 31) + this.status) * 31) + this.type) * 31) + this.uid;
    }

    public String toString() {
        return "FeedbackRecordBean(createTime=" + this.createTime + ", desc=" + this.desc + ", files=" + this.files + ", id=" + this.id + ", ip=" + this.ip + ", ipaddress=" + this.ipaddress + ", status=" + this.status + ", type=" + this.type + ", uid=" + this.uid + ")";
    }

    public FeedbackRecordBean(long j, String desc, List<String> files, int i, String ip, String ipaddress, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(files, "files");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(ipaddress, "ipaddress");
        this.createTime = j;
        this.desc = desc;
        this.files = files;
        this.id = i;
        this.ip = ip;
        this.ipaddress = ipaddress;
        this.status = i2;
        this.type = i3;
        this.uid = i4;
    }

    public final long getCreateTime() {
        return this.createTime;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final List<String> getFiles() {
        return this.files;
    }

    public final int getId() {
        return this.id;
    }

    public final String getIp() {
        return this.ip;
    }

    public final String getIpaddress() {
        return this.ipaddress;
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

    public final String getTypeText() {
        int i = this.type;
        if (i == 1) {
            return "功能异常";
        }
        if (i == 2) {
            return "产品建议";
        }
        if (i == 3) {
            return "支付异常";
        }
        if (i == 4) {
            return "安全问题";
        }
        return "其它问题";
    }

    public final String getStatusText() {
        if (this.status == 1) {
            return "已查看";
        }
        return "未查看";
    }

    public final String getTimeStr() {
        String str = new SimpleDateFormat("yyyy-MM-dd HHmm").format(new Date(this.createTime * ((long) 1000)));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}

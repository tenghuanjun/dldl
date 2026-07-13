package com.cy.yyjia.zhe28.domain;

import android.text.Html;
import android.text.Spanned;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.mobile.auth.gatewayauth.Constant;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WelfareEventDetailBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0003¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u008b\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u00102\u001a\u000203J\t\u00104\u001a\u00020\u0003HÖ\u0001J\t\u00105\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013¨\u00066"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareEventDetailBean;", "", "commentNum", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", "createTime", "endTime", MetricsSQLiteCacheKt.METRICS_END_TIME, "gameIds", "id", "pic", Constant.START_TIME, MetricsSQLiteCacheKt.METRICS_START_TIME, "statusStr", "title", "viewNum", "(ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getCommentNum", "()I", "getContent", "()Ljava/lang/String;", "getCreateTime", "getEndTime", "getEnd_time", "getGameIds", "getId", "getPic", "getStartTime", "getStart_time", "getStatusStr", "getTitle", "getViewNum", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getMainText", "Landroid/text/Spanned;", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class WelfareEventDetailBean {
    public static final int $stable = 0;
    private final int commentNum;
    private final String content;
    private final int createTime;
    private final int endTime;
    private final String end_time;
    private final String gameIds;
    private final int id;
    private final String pic;
    private final int startTime;
    private final String start_time;
    private final String statusStr;
    private final String title;
    private final int viewNum;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCommentNum() {
        return this.commentNum;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getStart_time() {
        return this.start_time;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getStatusStr() {
        return this.statusStr;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getViewNum() {
        return this.viewNum;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEnd_time() {
        return this.end_time;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getGameIds() {
        return this.gameIds;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPic() {
        return this.pic;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getStartTime() {
        return this.startTime;
    }

    public final WelfareEventDetailBean copy(int commentNum, String content, int createTime, int endTime, String end_time, String gameIds, int id, String pic, int startTime, String start_time, String statusStr, String title, int viewNum) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(end_time, "end_time");
        Intrinsics.checkNotNullParameter(gameIds, "gameIds");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(start_time, "start_time");
        Intrinsics.checkNotNullParameter(statusStr, "statusStr");
        Intrinsics.checkNotNullParameter(title, "title");
        return new WelfareEventDetailBean(commentNum, content, createTime, endTime, end_time, gameIds, id, pic, startTime, start_time, statusStr, title, viewNum);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WelfareEventDetailBean)) {
            return false;
        }
        WelfareEventDetailBean welfareEventDetailBean = (WelfareEventDetailBean) other;
        return this.commentNum == welfareEventDetailBean.commentNum && Intrinsics.areEqual(this.content, welfareEventDetailBean.content) && this.createTime == welfareEventDetailBean.createTime && this.endTime == welfareEventDetailBean.endTime && Intrinsics.areEqual(this.end_time, welfareEventDetailBean.end_time) && Intrinsics.areEqual(this.gameIds, welfareEventDetailBean.gameIds) && this.id == welfareEventDetailBean.id && Intrinsics.areEqual(this.pic, welfareEventDetailBean.pic) && this.startTime == welfareEventDetailBean.startTime && Intrinsics.areEqual(this.start_time, welfareEventDetailBean.start_time) && Intrinsics.areEqual(this.statusStr, welfareEventDetailBean.statusStr) && Intrinsics.areEqual(this.title, welfareEventDetailBean.title) && this.viewNum == welfareEventDetailBean.viewNum;
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.commentNum * 31) + this.content.hashCode()) * 31) + this.createTime) * 31) + this.endTime) * 31) + this.end_time.hashCode()) * 31) + this.gameIds.hashCode()) * 31) + this.id) * 31) + this.pic.hashCode()) * 31) + this.startTime) * 31) + this.start_time.hashCode()) * 31) + this.statusStr.hashCode()) * 31) + this.title.hashCode()) * 31) + this.viewNum;
    }

    public String toString() {
        return "WelfareEventDetailBean(commentNum=" + this.commentNum + ", content=" + this.content + ", createTime=" + this.createTime + ", endTime=" + this.endTime + ", end_time=" + this.end_time + ", gameIds=" + this.gameIds + ", id=" + this.id + ", pic=" + this.pic + ", startTime=" + this.startTime + ", start_time=" + this.start_time + ", statusStr=" + this.statusStr + ", title=" + this.title + ", viewNum=" + this.viewNum + ")";
    }

    public WelfareEventDetailBean(int i, String content, int i2, int i3, String end_time, String gameIds, int i4, String pic, int i5, String start_time, String statusStr, String title, int i6) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(end_time, "end_time");
        Intrinsics.checkNotNullParameter(gameIds, "gameIds");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(start_time, "start_time");
        Intrinsics.checkNotNullParameter(statusStr, "statusStr");
        Intrinsics.checkNotNullParameter(title, "title");
        this.commentNum = i;
        this.content = content;
        this.createTime = i2;
        this.endTime = i3;
        this.end_time = end_time;
        this.gameIds = gameIds;
        this.id = i4;
        this.pic = pic;
        this.startTime = i5;
        this.start_time = start_time;
        this.statusStr = statusStr;
        this.title = title;
        this.viewNum = i6;
    }

    public final int getCommentNum() {
        return this.commentNum;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getCreateTime() {
        return this.createTime;
    }

    public final int getEndTime() {
        return this.endTime;
    }

    public final String getEnd_time() {
        return this.end_time;
    }

    public final String getGameIds() {
        return this.gameIds;
    }

    public final int getId() {
        return this.id;
    }

    public final String getPic() {
        return this.pic;
    }

    public final int getStartTime() {
        return this.startTime;
    }

    public final String getStart_time() {
        return this.start_time;
    }

    public final String getStatusStr() {
        return this.statusStr;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getViewNum() {
        return this.viewNum;
    }

    public final Spanned getMainText() {
        Spanned spannedFromHtml = Html.fromHtml(this.content);
        Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
        return spannedFromHtml;
    }
}

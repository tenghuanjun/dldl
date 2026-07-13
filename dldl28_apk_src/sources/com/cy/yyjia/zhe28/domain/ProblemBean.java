package com.cy.yyjia.zhe28.domain;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProblemBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00000\fHÆ\u0003J_\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ProblemBean;", "", "catagoryId", "", "createtime", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "id", "status", "title", "viewNum", "more", "", "(ILjava/lang/String;Ljava/lang/String;IILjava/lang/String;ILjava/util/List;)V", "getCatagoryId", "()I", "getContent", "()Ljava/lang/String;", "getCreatetime", "getId", "getMore", "()Ljava/util/List;", "getStatus", "getTitle", "getViewNum", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ProblemBean {
    public static final int $stable = 8;
    private final int catagoryId;
    private final String content;
    private final String createtime;
    private final int id;
    private final List<ProblemBean> more;
    private final int status;
    private final String title;
    private final int viewNum;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCatagoryId() {
        return this.catagoryId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCreatetime() {
        return this.createtime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getViewNum() {
        return this.viewNum;
    }

    public final List<ProblemBean> component8() {
        return this.more;
    }

    public final ProblemBean copy(int catagoryId, String createtime, String content, int id, int status, String title, int viewNum, List<ProblemBean> more) {
        Intrinsics.checkNotNullParameter(createtime, "createtime");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(more, "more");
        return new ProblemBean(catagoryId, createtime, content, id, status, title, viewNum, more);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProblemBean)) {
            return false;
        }
        ProblemBean problemBean = (ProblemBean) other;
        return this.catagoryId == problemBean.catagoryId && Intrinsics.areEqual(this.createtime, problemBean.createtime) && Intrinsics.areEqual(this.content, problemBean.content) && this.id == problemBean.id && this.status == problemBean.status && Intrinsics.areEqual(this.title, problemBean.title) && this.viewNum == problemBean.viewNum && Intrinsics.areEqual(this.more, problemBean.more);
    }

    public int hashCode() {
        return (((((((((((((this.catagoryId * 31) + this.createtime.hashCode()) * 31) + this.content.hashCode()) * 31) + this.id) * 31) + this.status) * 31) + this.title.hashCode()) * 31) + this.viewNum) * 31) + this.more.hashCode();
    }

    public String toString() {
        return "ProblemBean(catagoryId=" + this.catagoryId + ", createtime=" + this.createtime + ", content=" + this.content + ", id=" + this.id + ", status=" + this.status + ", title=" + this.title + ", viewNum=" + this.viewNum + ", more=" + this.more + ")";
    }

    public ProblemBean(int i, String createtime, String content, int i2, int i3, String title, int i4, List<ProblemBean> more) {
        Intrinsics.checkNotNullParameter(createtime, "createtime");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(more, "more");
        this.catagoryId = i;
        this.createtime = createtime;
        this.content = content;
        this.id = i2;
        this.status = i3;
        this.title = title;
        this.viewNum = i4;
        this.more = more;
    }

    public final int getCatagoryId() {
        return this.catagoryId;
    }

    public final String getCreatetime() {
        return this.createtime;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getId() {
        return this.id;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getViewNum() {
        return this.viewNum;
    }

    public final List<ProblemBean> getMore() {
        return this.more;
    }
}

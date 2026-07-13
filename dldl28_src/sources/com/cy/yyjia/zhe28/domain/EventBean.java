package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b4\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001:\u0001DB\u008d\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\n\u0012\u0006\u0010\u0011\u001a\u00020\n\u0012\u0006\u0010\u0012\u001a\u00020\n\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\u0014\u001a\u00020\n\u0012\u0006\u0010\u0015\u001a\u00020\u0006\u0012\u0006\u0010\u0016\u001a\u00020\u0006¢\u0006\u0002\u0010\u0017J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\nHÆ\u0003J\t\u0010.\u001a\u00020\nHÆ\u0003J\t\u0010/\u001a\u00020\nHÆ\u0003J\t\u00100\u001a\u00020\nHÆ\u0003J\t\u00101\u001a\u00020\nHÆ\u0003J\t\u00102\u001a\u00020\nHÆ\u0003J\t\u00103\u001a\u00020\u0006HÆ\u0003J\t\u00104\u001a\u00020\u0006HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0006HÆ\u0003J\t\u00107\u001a\u00020\bHÆ\u0003J\t\u00108\u001a\u00020\nHÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\nHÆ\u0003J\t\u0010;\u001a\u00020\u0006HÆ\u0003J\t\u0010<\u001a\u00020\nHÆ\u0003J³\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u0006HÆ\u0001J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010A\u001a\u00020\nJ\t\u0010B\u001a\u00020\u0006HÖ\u0001J\t\u0010C\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0011\u0010\u0014\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0011\u0010\u0016\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0011\u0010\u0010\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b'\u0010 R\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u0011\u0010\u0012\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u0011\u0010\u0013\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001c¨\u0006E"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/EventBean;", "", "actTimeE", "", "actTimeS", "catagoryId", "", "category", "Lcom/cy/yyjia/zhe28/domain/EventBean$Category;", "date_md_text", "", "dateline", "fixedCatagory", "id", "pic", "recommendType", "sapplyType", "status", "subTitle", "title", SocialConstants.PARAM_APP_DESC, "viewNum", "is_apply", "(JJILcom/cy/yyjia/zhe28/domain/EventBean$Category;Ljava/lang/String;JLjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getActTimeE", "()J", "getActTimeS", "getCatagoryId", "()I", "getCategory", "()Lcom/cy/yyjia/zhe28/domain/EventBean$Category;", "getDate_md_text", "()Ljava/lang/String;", "getDateline", "getDesc", "getFixedCatagory", "getId", "getPic", "getRecommendType", "getSapplyType", "getStatus", "getSubTitle", "getTitle", "getViewNum", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getApplyTypeText", "hashCode", "toString", "Category", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class EventBean {
    public static final int $stable = 0;
    private final long actTimeE;
    private final long actTimeS;
    private final int catagoryId;
    private final Category category;
    private final String date_md_text;
    private final long dateline;
    private final String desc;
    private final String fixedCatagory;
    private final int id;
    private final int is_apply;
    private final String pic;
    private final String recommendType;
    private final String sapplyType;
    private final String status;
    private final String subTitle;
    private final String title;
    private final int viewNum;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getActTimeE() {
        return this.actTimeE;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getRecommendType() {
        return this.recommendType;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSapplyType() {
        return this.sapplyType;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSubTitle() {
        return this.subTitle;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getViewNum() {
        return this.viewNum;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getIs_apply() {
        return this.is_apply;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getActTimeS() {
        return this.actTimeS;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCatagoryId() {
        return this.catagoryId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Category getCategory() {
        return this.category;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDate_md_text() {
        return this.date_md_text;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getFixedCatagory() {
        return this.fixedCatagory;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPic() {
        return this.pic;
    }

    public final EventBean copy(long actTimeE, long actTimeS, int catagoryId, Category category, String date_md_text, long dateline, String fixedCatagory, int id, String pic, String recommendType, String sapplyType, String status, String subTitle, String title, String desc, int viewNum, int is_apply) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(date_md_text, "date_md_text");
        Intrinsics.checkNotNullParameter(fixedCatagory, "fixedCatagory");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(recommendType, "recommendType");
        Intrinsics.checkNotNullParameter(sapplyType, "sapplyType");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(desc, "desc");
        return new EventBean(actTimeE, actTimeS, catagoryId, category, date_md_text, dateline, fixedCatagory, id, pic, recommendType, sapplyType, status, subTitle, title, desc, viewNum, is_apply);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventBean)) {
            return false;
        }
        EventBean eventBean = (EventBean) other;
        return this.actTimeE == eventBean.actTimeE && this.actTimeS == eventBean.actTimeS && this.catagoryId == eventBean.catagoryId && Intrinsics.areEqual(this.category, eventBean.category) && Intrinsics.areEqual(this.date_md_text, eventBean.date_md_text) && this.dateline == eventBean.dateline && Intrinsics.areEqual(this.fixedCatagory, eventBean.fixedCatagory) && this.id == eventBean.id && Intrinsics.areEqual(this.pic, eventBean.pic) && Intrinsics.areEqual(this.recommendType, eventBean.recommendType) && Intrinsics.areEqual(this.sapplyType, eventBean.sapplyType) && Intrinsics.areEqual(this.status, eventBean.status) && Intrinsics.areEqual(this.subTitle, eventBean.subTitle) && Intrinsics.areEqual(this.title, eventBean.title) && Intrinsics.areEqual(this.desc, eventBean.desc) && this.viewNum == eventBean.viewNum && this.is_apply == eventBean.is_apply;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((GMTitleBean$$ExternalSyntheticBackport0.m(this.actTimeE) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.actTimeS)) * 31) + this.catagoryId) * 31) + this.category.hashCode()) * 31) + this.date_md_text.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.dateline)) * 31) + this.fixedCatagory.hashCode()) * 31) + this.id) * 31) + this.pic.hashCode()) * 31) + this.recommendType.hashCode()) * 31) + this.sapplyType.hashCode()) * 31) + this.status.hashCode()) * 31) + this.subTitle.hashCode()) * 31) + this.title.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.viewNum) * 31) + this.is_apply;
    }

    public String toString() {
        return "EventBean(actTimeE=" + this.actTimeE + ", actTimeS=" + this.actTimeS + ", catagoryId=" + this.catagoryId + ", category=" + this.category + ", date_md_text=" + this.date_md_text + ", dateline=" + this.dateline + ", fixedCatagory=" + this.fixedCatagory + ", id=" + this.id + ", pic=" + this.pic + ", recommendType=" + this.recommendType + ", sapplyType=" + this.sapplyType + ", status=" + this.status + ", subTitle=" + this.subTitle + ", title=" + this.title + ", desc=" + this.desc + ", viewNum=" + this.viewNum + ", is_apply=" + this.is_apply + ")";
    }

    public EventBean(long j, long j2, int i, Category category, String date_md_text, long j3, String fixedCatagory, int i2, String pic, String recommendType, String sapplyType, String status, String subTitle, String title, String desc, int i3, int i4) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(date_md_text, "date_md_text");
        Intrinsics.checkNotNullParameter(fixedCatagory, "fixedCatagory");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(recommendType, "recommendType");
        Intrinsics.checkNotNullParameter(sapplyType, "sapplyType");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(desc, "desc");
        this.actTimeE = j;
        this.actTimeS = j2;
        this.catagoryId = i;
        this.category = category;
        this.date_md_text = date_md_text;
        this.dateline = j3;
        this.fixedCatagory = fixedCatagory;
        this.id = i2;
        this.pic = pic;
        this.recommendType = recommendType;
        this.sapplyType = sapplyType;
        this.status = status;
        this.subTitle = subTitle;
        this.title = title;
        this.desc = desc;
        this.viewNum = i3;
        this.is_apply = i4;
    }

    public final long getActTimeE() {
        return this.actTimeE;
    }

    public final long getActTimeS() {
        return this.actTimeS;
    }

    public final int getCatagoryId() {
        return this.catagoryId;
    }

    public final Category getCategory() {
        return this.category;
    }

    public final String getDate_md_text() {
        return this.date_md_text;
    }

    public final long getDateline() {
        return this.dateline;
    }

    public final String getFixedCatagory() {
        return this.fixedCatagory;
    }

    public final int getId() {
        return this.id;
    }

    public final String getPic() {
        return this.pic;
    }

    public final String getRecommendType() {
        return this.recommendType;
    }

    public final String getSapplyType() {
        return this.sapplyType;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final int getViewNum() {
        return this.viewNum;
    }

    public final int is_apply() {
        return this.is_apply;
    }

    public final String getApplyTypeText() {
        String str = this.sapplyType;
        if (Intrinsics.areEqual(str, "1")) {
            return "自动发送";
        }
        if (Intrinsics.areEqual(str, "2")) {
            if (this.is_apply == 1) {
                return "已申请";
            }
            return "立即申请";
        }
        return "";
    }

    /* JADX INFO: compiled from: EventBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/EventBean$Category;", "", "id", "", "name", "", "orderBy", "pid", "subName", "(ILjava/lang/String;IILjava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getOrderBy", "getPid", "getSubName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Category {
        public static final int $stable = 0;
        private final int id;
        private final String name;
        private final int orderBy;
        private final int pid;
        private final String subName;

        public static /* synthetic */ Category copy$default(Category category, int i, String str, int i2, int i3, String str2, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = category.id;
            }
            if ((i4 & 2) != 0) {
                str = category.name;
            }
            String str3 = str;
            if ((i4 & 4) != 0) {
                i2 = category.orderBy;
            }
            int i5 = i2;
            if ((i4 & 8) != 0) {
                i3 = category.pid;
            }
            int i6 = i3;
            if ((i4 & 16) != 0) {
                str2 = category.subName;
            }
            return category.copy(i, str3, i5, i6, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getOrderBy() {
            return this.orderBy;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getPid() {
            return this.pid;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSubName() {
            return this.subName;
        }

        public final Category copy(int id, String name, int orderBy, int pid, String subName) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(subName, "subName");
            return new Category(id, name, orderBy, pid, subName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Category)) {
                return false;
            }
            Category category = (Category) other;
            return this.id == category.id && Intrinsics.areEqual(this.name, category.name) && this.orderBy == category.orderBy && this.pid == category.pid && Intrinsics.areEqual(this.subName, category.subName);
        }

        public int hashCode() {
            return (((((((this.id * 31) + this.name.hashCode()) * 31) + this.orderBy) * 31) + this.pid) * 31) + this.subName.hashCode();
        }

        public String toString() {
            return "Category(id=" + this.id + ", name=" + this.name + ", orderBy=" + this.orderBy + ", pid=" + this.pid + ", subName=" + this.subName + ")";
        }

        public Category(int i, String name, int i2, int i3, String subName) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(subName, "subName");
            this.id = i;
            this.name = name;
            this.orderBy = i2;
            this.pid = i3;
            this.subName = subName;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final int getOrderBy() {
            return this.orderBy;
        }

        public final int getPid() {
            return this.pid;
        }

        public final String getSubName() {
            return this.subName;
        }
    }
}

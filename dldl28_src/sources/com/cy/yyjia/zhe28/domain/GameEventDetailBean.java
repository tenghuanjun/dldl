package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameEventDetailBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b@\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0001NB¯\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\u0006\u0010\u0015\u001a\u00020\u0006\u0012\u0006\u0010\u0016\u001a\u00020\u0006\u0012\u0006\u0010\u0017\u001a\u00020\u0006\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u001aJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0006HÆ\u0003J\t\u00105\u001a\u00020\u0006HÆ\u0003J\t\u00106\u001a\u00020\u0006HÆ\u0003J\t\u00107\u001a\u00020\u0006HÆ\u0003J\t\u00108\u001a\u00020\u0006HÆ\u0003J\t\u00109\u001a\u00020\u0006HÆ\u0003J\t\u0010:\u001a\u00020\u0006HÆ\u0003J\t\u0010;\u001a\u00020\u0006HÆ\u0003J\t\u0010<\u001a\u00020\u0006HÆ\u0003J\t\u0010=\u001a\u00020\u0006HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010A\u001a\u00020\u0006HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\tHÆ\u0003J\t\u0010D\u001a\u00020\u0006HÆ\u0003J\t\u0010E\u001a\u00020\u0006HÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003JÝ\u0001\u0010H\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010L\u001a\u00020\u0003HÖ\u0001J\t\u0010M\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001fR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001fR\u0011\u0010\u0017\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001c¨\u0006O"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameEventDetailBean;", "", "actTimeE", "", "actTimeS", "autoShow", "", "catagoryId", "category", "Lcom/cy/yyjia/zhe28/domain/GameEventDetailBean$Category;", "dateline", "fixedCatagory", CommonConstants.key_gameId, "id", CommonConstants.KEY_MESSAGE, "pic", "recommendType", "sapplyType", "sourceName", "sourceUrl", "status", "subTitle", "title", "userName", "viewNum", "url", "(IILjava/lang/String;ILcom/cy/yyjia/zhe28/domain/GameEventDetailBean$Category;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getActTimeE", "()I", "getActTimeS", "getAutoShow", "()Ljava/lang/String;", "getCatagoryId", "getCategory", "()Lcom/cy/yyjia/zhe28/domain/GameEventDetailBean$Category;", "getDateline", "getFixedCatagory", "getGameId", "getId", "getMessage", "getPic", "getRecommendType", "getSapplyType", "getSourceName", "getSourceUrl", "getStatus", "getSubTitle", "getTitle", "getUrl", "getUserName", "getViewNum", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "Category", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GameEventDetailBean {
    public static final int $stable = 0;
    private final int actTimeE;
    private final int actTimeS;
    private final String autoShow;
    private final int catagoryId;
    private final Category category;
    private final String dateline;
    private final String fixedCatagory;
    private final int gameId;
    private final int id;
    private final String message;
    private final String pic;
    private final String recommendType;
    private final String sapplyType;
    private final String sourceName;
    private final String sourceUrl;
    private final String status;
    private final String subTitle;
    private final String title;
    private final String url;
    private final String userName;
    private final int viewNum;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getActTimeE() {
        return this.actTimeE;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPic() {
        return this.pic;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getRecommendType() {
        return this.recommendType;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSapplyType() {
        return this.sapplyType;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getSourceName() {
        return this.sourceName;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getSourceUrl() {
        return this.sourceUrl;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getSubTitle() {
        return this.subTitle;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getUserName() {
        return this.userName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getActTimeS() {
        return this.actTimeS;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final int getViewNum() {
        return this.viewNum;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAutoShow() {
        return this.autoShow;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getCatagoryId() {
        return this.catagoryId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Category getCategory() {
        return this.category;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getFixedCatagory() {
        return this.fixedCatagory;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final GameEventDetailBean copy(int actTimeE, int actTimeS, String autoShow, int catagoryId, Category category, String dateline, String fixedCatagory, int gameId, int id, String message, String pic, String recommendType, String sapplyType, String sourceName, String sourceUrl, String status, String subTitle, String title, String userName, int viewNum, String url) {
        Intrinsics.checkNotNullParameter(autoShow, "autoShow");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(fixedCatagory, "fixedCatagory");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(recommendType, "recommendType");
        Intrinsics.checkNotNullParameter(sapplyType, "sapplyType");
        Intrinsics.checkNotNullParameter(sourceName, "sourceName");
        Intrinsics.checkNotNullParameter(sourceUrl, "sourceUrl");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(userName, "userName");
        return new GameEventDetailBean(actTimeE, actTimeS, autoShow, catagoryId, category, dateline, fixedCatagory, gameId, id, message, pic, recommendType, sapplyType, sourceName, sourceUrl, status, subTitle, title, userName, viewNum, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GameEventDetailBean)) {
            return false;
        }
        GameEventDetailBean gameEventDetailBean = (GameEventDetailBean) other;
        return this.actTimeE == gameEventDetailBean.actTimeE && this.actTimeS == gameEventDetailBean.actTimeS && Intrinsics.areEqual(this.autoShow, gameEventDetailBean.autoShow) && this.catagoryId == gameEventDetailBean.catagoryId && Intrinsics.areEqual(this.category, gameEventDetailBean.category) && Intrinsics.areEqual(this.dateline, gameEventDetailBean.dateline) && Intrinsics.areEqual(this.fixedCatagory, gameEventDetailBean.fixedCatagory) && this.gameId == gameEventDetailBean.gameId && this.id == gameEventDetailBean.id && Intrinsics.areEqual(this.message, gameEventDetailBean.message) && Intrinsics.areEqual(this.pic, gameEventDetailBean.pic) && Intrinsics.areEqual(this.recommendType, gameEventDetailBean.recommendType) && Intrinsics.areEqual(this.sapplyType, gameEventDetailBean.sapplyType) && Intrinsics.areEqual(this.sourceName, gameEventDetailBean.sourceName) && Intrinsics.areEqual(this.sourceUrl, gameEventDetailBean.sourceUrl) && Intrinsics.areEqual(this.status, gameEventDetailBean.status) && Intrinsics.areEqual(this.subTitle, gameEventDetailBean.subTitle) && Intrinsics.areEqual(this.title, gameEventDetailBean.title) && Intrinsics.areEqual(this.userName, gameEventDetailBean.userName) && this.viewNum == gameEventDetailBean.viewNum && Intrinsics.areEqual(this.url, gameEventDetailBean.url);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((((((((((((((((((((((this.actTimeE * 31) + this.actTimeS) * 31) + this.autoShow.hashCode()) * 31) + this.catagoryId) * 31) + this.category.hashCode()) * 31) + this.dateline.hashCode()) * 31) + this.fixedCatagory.hashCode()) * 31) + this.gameId) * 31) + this.id) * 31) + this.message.hashCode()) * 31) + this.pic.hashCode()) * 31) + this.recommendType.hashCode()) * 31) + this.sapplyType.hashCode()) * 31) + this.sourceName.hashCode()) * 31) + this.sourceUrl.hashCode()) * 31) + this.status.hashCode()) * 31) + this.subTitle.hashCode()) * 31) + this.title.hashCode()) * 31) + this.userName.hashCode()) * 31) + this.viewNum) * 31;
        String str = this.url;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "GameEventDetailBean(actTimeE=" + this.actTimeE + ", actTimeS=" + this.actTimeS + ", autoShow=" + this.autoShow + ", catagoryId=" + this.catagoryId + ", category=" + this.category + ", dateline=" + this.dateline + ", fixedCatagory=" + this.fixedCatagory + ", gameId=" + this.gameId + ", id=" + this.id + ", message=" + this.message + ", pic=" + this.pic + ", recommendType=" + this.recommendType + ", sapplyType=" + this.sapplyType + ", sourceName=" + this.sourceName + ", sourceUrl=" + this.sourceUrl + ", status=" + this.status + ", subTitle=" + this.subTitle + ", title=" + this.title + ", userName=" + this.userName + ", viewNum=" + this.viewNum + ", url=" + this.url + ")";
    }

    public GameEventDetailBean(int i, int i2, String autoShow, int i3, Category category, String dateline, String fixedCatagory, int i4, int i5, String message, String pic, String recommendType, String sapplyType, String sourceName, String sourceUrl, String status, String subTitle, String title, String userName, int i6, String str) {
        Intrinsics.checkNotNullParameter(autoShow, "autoShow");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(fixedCatagory, "fixedCatagory");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(recommendType, "recommendType");
        Intrinsics.checkNotNullParameter(sapplyType, "sapplyType");
        Intrinsics.checkNotNullParameter(sourceName, "sourceName");
        Intrinsics.checkNotNullParameter(sourceUrl, "sourceUrl");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(subTitle, "subTitle");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(userName, "userName");
        this.actTimeE = i;
        this.actTimeS = i2;
        this.autoShow = autoShow;
        this.catagoryId = i3;
        this.category = category;
        this.dateline = dateline;
        this.fixedCatagory = fixedCatagory;
        this.gameId = i4;
        this.id = i5;
        this.message = message;
        this.pic = pic;
        this.recommendType = recommendType;
        this.sapplyType = sapplyType;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.status = status;
        this.subTitle = subTitle;
        this.title = title;
        this.userName = userName;
        this.viewNum = i6;
        this.url = str;
    }

    public final int getActTimeE() {
        return this.actTimeE;
    }

    public final int getActTimeS() {
        return this.actTimeS;
    }

    public final String getAutoShow() {
        return this.autoShow;
    }

    public final int getCatagoryId() {
        return this.catagoryId;
    }

    public final Category getCategory() {
        return this.category;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final String getFixedCatagory() {
        return this.fixedCatagory;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final int getId() {
        return this.id;
    }

    public final String getMessage() {
        return this.message;
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

    public final String getSourceName() {
        return this.sourceName;
    }

    public final String getSourceUrl() {
        return this.sourceUrl;
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

    public final String getUserName() {
        return this.userName;
    }

    public final int getViewNum() {
        return this.viewNum;
    }

    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: compiled from: GameEventDetailBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameEventDetailBean$Category;", "", "id", "", "name", "", "orderBy", "pid", "subName", "(ILjava/lang/String;IILjava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getOrderBy", "getPid", "getSubName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
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

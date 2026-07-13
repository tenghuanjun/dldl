package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TopicBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TopicBean;", "", "color", "", "id", "", SocialConstants.PARAM_IMG_URL, "status", "title", "url", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getColor", "()Ljava/lang/String;", "getId", "()I", "getImg", "getStatus", "getTitle", "getUrl", "setUrl", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class TopicBean {
    public static final int $stable = 8;
    private final String color;
    private final int id;
    private final String img;
    private final String status;
    private final String title;
    private String url;

    public static /* synthetic */ TopicBean copy$default(TopicBean topicBean, String str, int i, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = topicBean.color;
        }
        if ((i2 & 2) != 0) {
            i = topicBean.id;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            str2 = topicBean.img;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = topicBean.status;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            str4 = topicBean.title;
        }
        String str8 = str4;
        if ((i2 & 32) != 0) {
            str5 = topicBean.url;
        }
        return topicBean.copy(str, i3, str6, str7, str8, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getColor() {
        return this.color;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getImg() {
        return this.img;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final TopicBean copy(String color, int id, String img, String status, String title, String url) {
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(url, "url");
        return new TopicBean(color, id, img, status, title, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TopicBean)) {
            return false;
        }
        TopicBean topicBean = (TopicBean) other;
        return Intrinsics.areEqual(this.color, topicBean.color) && this.id == topicBean.id && Intrinsics.areEqual(this.img, topicBean.img) && Intrinsics.areEqual(this.status, topicBean.status) && Intrinsics.areEqual(this.title, topicBean.title) && Intrinsics.areEqual(this.url, topicBean.url);
    }

    public int hashCode() {
        return (((((((((this.color.hashCode() * 31) + this.id) * 31) + this.img.hashCode()) * 31) + this.status.hashCode()) * 31) + this.title.hashCode()) * 31) + this.url.hashCode();
    }

    public String toString() {
        return "TopicBean(color=" + this.color + ", id=" + this.id + ", img=" + this.img + ", status=" + this.status + ", title=" + this.title + ", url=" + this.url + ")";
    }

    public TopicBean(String color, int i, String img, String status, String title, String url) {
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(url, "url");
        this.color = color;
        this.id = i;
        this.img = img;
        this.status = status;
        this.title = title;
        this.url = url;
    }

    public final String getColor() {
        return this.color;
    }

    public final int getId() {
        return this.id;
    }

    public final String getImg() {
        return this.img;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }
}

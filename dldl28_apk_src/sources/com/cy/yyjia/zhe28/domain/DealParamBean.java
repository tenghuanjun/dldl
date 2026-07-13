package com.cy.yyjia.zhe28.domain;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealParamBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b¢\u0006\u0002\u0010\u0010J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\t\u0010/\u001a\u00020\u000bHÆ\u0003J\t\u00100\u001a\u00020\u000bHÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u000bHÆ\u0003J\t\u00108\u001a\u00020\u000bHÆ\u0003J\u0081\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000bHÆ\u0001J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u000bHÖ\u0001J\t\u0010>\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u000f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001a\u0010\u000e\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010\u001cR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0012\"\u0004\b(\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001a\"\u0004\b*\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001a\"\u0004\b,\u0010\u001c¨\u0006?"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DealParamBean;", "", "type", "", "order", "sort", "keyword", "min", "max", "server", "siteaccount", "", "isregress", "isopen", ImageSelector.POSITION, "isdolo", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIII)V", "getIsdolo", "()I", "setIsdolo", "(I)V", "getIsopen", "setIsopen", "getIsregress", "setIsregress", "getKeyword", "()Ljava/lang/String;", "setKeyword", "(Ljava/lang/String;)V", "getMax", "setMax", "getMin", "setMin", "getOrder", "setOrder", "getPosition", "setPosition", "getServer", "setServer", "getSiteaccount", "setSiteaccount", "getSort", "setSort", "getType", "setType", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DealParamBean {
    public static final int $stable = 8;
    private int isdolo;
    private int isopen;
    private int isregress;
    private String keyword;
    private String max;
    private String min;
    private String order;
    private int position;
    private String server;
    private int siteaccount;
    private String sort;
    private String type;

    public DealParamBean() {
        this(null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 4095, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getIsopen() {
        return this.isopen;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getIsdolo() {
        return this.isdolo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getOrder() {
        return this.order;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSort() {
        return this.sort;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getKeyword() {
        return this.keyword;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getMin() {
        return this.min;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getMax() {
        return this.max;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getServer() {
        return this.server;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getSiteaccount() {
        return this.siteaccount;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getIsregress() {
        return this.isregress;
    }

    public final DealParamBean copy(String type, String order, String sort, String keyword, String min, String max, String server, int siteaccount, int isregress, int isopen, int position, int isdolo) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(order, "order");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        Intrinsics.checkNotNullParameter(min, "min");
        Intrinsics.checkNotNullParameter(max, "max");
        Intrinsics.checkNotNullParameter(server, "server");
        return new DealParamBean(type, order, sort, keyword, min, max, server, siteaccount, isregress, isopen, position, isdolo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DealParamBean)) {
            return false;
        }
        DealParamBean dealParamBean = (DealParamBean) other;
        return Intrinsics.areEqual(this.type, dealParamBean.type) && Intrinsics.areEqual(this.order, dealParamBean.order) && Intrinsics.areEqual(this.sort, dealParamBean.sort) && Intrinsics.areEqual(this.keyword, dealParamBean.keyword) && Intrinsics.areEqual(this.min, dealParamBean.min) && Intrinsics.areEqual(this.max, dealParamBean.max) && Intrinsics.areEqual(this.server, dealParamBean.server) && this.siteaccount == dealParamBean.siteaccount && this.isregress == dealParamBean.isregress && this.isopen == dealParamBean.isopen && this.position == dealParamBean.position && this.isdolo == dealParamBean.isdolo;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.type.hashCode() * 31) + this.order.hashCode()) * 31) + this.sort.hashCode()) * 31) + this.keyword.hashCode()) * 31) + this.min.hashCode()) * 31) + this.max.hashCode()) * 31) + this.server.hashCode()) * 31) + this.siteaccount) * 31) + this.isregress) * 31) + this.isopen) * 31) + this.position) * 31) + this.isdolo;
    }

    public String toString() {
        return "DealParamBean(type=" + this.type + ", order=" + this.order + ", sort=" + this.sort + ", keyword=" + this.keyword + ", min=" + this.min + ", max=" + this.max + ", server=" + this.server + ", siteaccount=" + this.siteaccount + ", isregress=" + this.isregress + ", isopen=" + this.isopen + ", position=" + this.position + ", isdolo=" + this.isdolo + ")";
    }

    public DealParamBean(String type, String order, String sort, String keyword, String min, String max, String server, int i, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(order, "order");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(keyword, "keyword");
        Intrinsics.checkNotNullParameter(min, "min");
        Intrinsics.checkNotNullParameter(max, "max");
        Intrinsics.checkNotNullParameter(server, "server");
        this.type = type;
        this.order = order;
        this.sort = sort;
        this.keyword = keyword;
        this.min = min;
        this.max = max;
        this.server = server;
        this.siteaccount = i;
        this.isregress = i2;
        this.isopen = i3;
        this.position = i4;
        this.isdolo = i5;
    }

    public /* synthetic */ DealParamBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? "" : str, (i6 & 2) != 0 ? "dateline" : str2, (i6 & 4) != 0 ? SocialConstants.PARAM_APP_DESC : str3, (i6 & 8) != 0 ? "" : str4, (i6 & 16) != 0 ? "" : str5, (i6 & 32) != 0 ? "" : str6, (i6 & 64) == 0 ? str7 : "", (i6 & 128) != 0 ? 0 : i, (i6 & 256) != 0 ? 0 : i2, (i6 & 512) != 0 ? 0 : i3, (i6 & 1024) != 0 ? 0 : i4, (i6 & 2048) == 0 ? i5 : 0);
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }

    public final String getOrder() {
        return this.order;
    }

    public final void setOrder(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.order = str;
    }

    public final String getSort() {
        return this.sort;
    }

    public final void setSort(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sort = str;
    }

    public final String getKeyword() {
        return this.keyword;
    }

    public final void setKeyword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.keyword = str;
    }

    public final String getMin() {
        return this.min;
    }

    public final void setMin(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.min = str;
    }

    public final String getMax() {
        return this.max;
    }

    public final void setMax(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.max = str;
    }

    public final String getServer() {
        return this.server;
    }

    public final void setServer(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.server = str;
    }

    public final int getSiteaccount() {
        return this.siteaccount;
    }

    public final void setSiteaccount(int i) {
        this.siteaccount = i;
    }

    public final int getIsregress() {
        return this.isregress;
    }

    public final void setIsregress(int i) {
        this.isregress = i;
    }

    public final int getIsopen() {
        return this.isopen;
    }

    public final void setIsopen(int i) {
        this.isopen = i;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    public final int getIsdolo() {
        return this.isdolo;
    }

    public final void setIsdolo(int i) {
        this.isdolo = i;
    }
}

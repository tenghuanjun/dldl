package com.cy.yyjia.zhe28.domain;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\bHÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ShareInfo;", "", "title", "", "describe", "url", "imgUrl", "pic", "Ljava/io/File;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V", "getDescribe", "()Ljava/lang/String;", "setDescribe", "(Ljava/lang/String;)V", "getImgUrl", "setImgUrl", "getPic", "()Ljava/io/File;", "setPic", "(Ljava/io/File;)V", "getTitle", "setTitle", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ShareInfo {
    public static final int $stable = 8;
    private String describe;
    private String imgUrl;
    private File pic;
    private String title;
    private String url;

    public ShareInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ ShareInfo copy$default(ShareInfo shareInfo, String str, String str2, String str3, String str4, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = shareInfo.title;
        }
        if ((i & 2) != 0) {
            str2 = shareInfo.describe;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = shareInfo.url;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = shareInfo.imgUrl;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            file = shareInfo.pic;
        }
        return shareInfo.copy(str, str5, str6, str7, file);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescribe() {
        return this.describe;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getImgUrl() {
        return this.imgUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final File getPic() {
        return this.pic;
    }

    public final ShareInfo copy(String title, String describe, String url, String imgUrl, File pic) {
        return new ShareInfo(title, describe, url, imgUrl, pic);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShareInfo)) {
            return false;
        }
        ShareInfo shareInfo = (ShareInfo) other;
        return Intrinsics.areEqual(this.title, shareInfo.title) && Intrinsics.areEqual(this.describe, shareInfo.describe) && Intrinsics.areEqual(this.url, shareInfo.url) && Intrinsics.areEqual(this.imgUrl, shareInfo.imgUrl) && Intrinsics.areEqual(this.pic, shareInfo.pic);
    }

    public int hashCode() {
        String str = this.title;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.describe;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.url;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.imgUrl;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        File file = this.pic;
        return iHashCode4 + (file != null ? file.hashCode() : 0);
    }

    public String toString() {
        return "ShareInfo(title=" + this.title + ", describe=" + this.describe + ", url=" + this.url + ", imgUrl=" + this.imgUrl + ", pic=" + this.pic + ")";
    }

    public ShareInfo(String str, String str2, String str3, String str4, File file) {
        this.title = str;
        this.describe = str2;
        this.url = str3;
        this.imgUrl = str4;
        this.pic = file;
    }

    public /* synthetic */ ShareInfo(String str, String str2, String str3, String str4, File file, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) == 0 ? str4 : "", (i & 16) != 0 ? null : file);
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getDescribe() {
        return this.describe;
    }

    public final void setDescribe(String str) {
        this.describe = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final String getImgUrl() {
        return this.imgUrl;
    }

    public final void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public final File getPic() {
        return this.pic;
    }

    public final void setPic(File file) {
        this.pic = file;
    }
}

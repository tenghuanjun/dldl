package com.cy.yyjia.zhe28.domain;

import android.content.Intent;
import android.view.View;
import com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BbsBannerBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsBannerBean;", "", "id", "", "image", "", "post_id", "title", "(ILjava/lang/String;ILjava/lang/String;)V", "getId", "()I", "getImage", "()Ljava/lang/String;", "getPost_id", "getTitle", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toBbs", "", "v", "Landroid/view/View;", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BbsBannerBean {
    public static final int $stable = 0;
    private final int id;
    private final String image;
    private final int post_id;
    private final String title;

    public static /* synthetic */ BbsBannerBean copy$default(BbsBannerBean bbsBannerBean, int i, String str, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bbsBannerBean.id;
        }
        if ((i3 & 2) != 0) {
            str = bbsBannerBean.image;
        }
        if ((i3 & 4) != 0) {
            i2 = bbsBannerBean.post_id;
        }
        if ((i3 & 8) != 0) {
            str2 = bbsBannerBean.title;
        }
        return bbsBannerBean.copy(i, str, i2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPost_id() {
        return this.post_id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final BbsBannerBean copy(int id, String image, int post_id, String title) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(title, "title");
        return new BbsBannerBean(id, image, post_id, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BbsBannerBean)) {
            return false;
        }
        BbsBannerBean bbsBannerBean = (BbsBannerBean) other;
        return this.id == bbsBannerBean.id && Intrinsics.areEqual(this.image, bbsBannerBean.image) && this.post_id == bbsBannerBean.post_id && Intrinsics.areEqual(this.title, bbsBannerBean.title);
    }

    public int hashCode() {
        return (((((this.id * 31) + this.image.hashCode()) * 31) + this.post_id) * 31) + this.title.hashCode();
    }

    public String toString() {
        return "BbsBannerBean(id=" + this.id + ", image=" + this.image + ", post_id=" + this.post_id + ", title=" + this.title + ")";
    }

    public BbsBannerBean(int i, String image, int i2, String title) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = i;
        this.image = image;
        this.post_id = i2;
        this.title = title;
    }

    public final int getId() {
        return this.id;
    }

    public final String getImage() {
        return this.image;
    }

    public final int getPost_id() {
        return this.post_id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void toBbs(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intent intent = new Intent(v.getContext(), (Class<?>) BbsDetailActivity.class);
        intent.putExtra("activityId", this.id);
        intent.putExtra("id", this.post_id);
        v.getContext().startActivity(intent);
    }
}

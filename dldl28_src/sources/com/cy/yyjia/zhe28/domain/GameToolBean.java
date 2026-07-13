package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameToolBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u000eHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\fHÆ\u0003Jm\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010/\u001a\u00020\u001d2\b\u00100\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\u000eHÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0011¨\u00064"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameToolBean;", "Landroidx/databinding/BaseObservable;", SocialConstants.PARAM_APP_DESC, "", "bgDesc", "icon", "title", "bg", "bgColor", "color", "video", "link", "Lcom/cy/yyjia/zhe28/domain/BtnBean;", "isDot", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/BtnBean;I)V", "getBg", "()Ljava/lang/String;", "getBgColor", "getBgDesc", "getColor", "getDesc", "getIcon", "()I", "setDot", "(I)V", "getLink", "()Lcom/cy/yyjia/zhe28/domain/BtnBean;", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getTitle", "getVideo", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GameToolBean extends BaseObservable {
    public static final int $stable = 8;
    private final String bg;
    private final String bgColor;
    private final String bgDesc;
    private final String color;
    private final String desc;
    private final String icon;
    private int isDot;
    private final BtnBean link;
    private boolean selected;
    private final String title;
    private final String video;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getIsDot() {
        return this.isDot;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getBgDesc() {
        return this.bgDesc;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBg() {
        return this.bg;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getBgColor() {
        return this.bgColor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getColor() {
        return this.color;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getVideo() {
        return this.video;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final BtnBean getLink() {
        return this.link;
    }

    public final GameToolBean copy(String desc, String bgDesc, String icon, String title, String bg, String bgColor, String color, String video, BtnBean link, int isDot) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(bgDesc, "bgDesc");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(bg, "bg");
        Intrinsics.checkNotNullParameter(bgColor, "bgColor");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(video, "video");
        Intrinsics.checkNotNullParameter(link, "link");
        return new GameToolBean(desc, bgDesc, icon, title, bg, bgColor, color, video, link, isDot);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GameToolBean)) {
            return false;
        }
        GameToolBean gameToolBean = (GameToolBean) other;
        return Intrinsics.areEqual(this.desc, gameToolBean.desc) && Intrinsics.areEqual(this.bgDesc, gameToolBean.bgDesc) && Intrinsics.areEqual(this.icon, gameToolBean.icon) && Intrinsics.areEqual(this.title, gameToolBean.title) && Intrinsics.areEqual(this.bg, gameToolBean.bg) && Intrinsics.areEqual(this.bgColor, gameToolBean.bgColor) && Intrinsics.areEqual(this.color, gameToolBean.color) && Intrinsics.areEqual(this.video, gameToolBean.video) && Intrinsics.areEqual(this.link, gameToolBean.link) && this.isDot == gameToolBean.isDot;
    }

    public int hashCode() {
        return (((((((((((((((((this.desc.hashCode() * 31) + this.bgDesc.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.title.hashCode()) * 31) + this.bg.hashCode()) * 31) + this.bgColor.hashCode()) * 31) + this.color.hashCode()) * 31) + this.video.hashCode()) * 31) + this.link.hashCode()) * 31) + this.isDot;
    }

    public String toString() {
        return "GameToolBean(desc=" + this.desc + ", bgDesc=" + this.bgDesc + ", icon=" + this.icon + ", title=" + this.title + ", bg=" + this.bg + ", bgColor=" + this.bgColor + ", color=" + this.color + ", video=" + this.video + ", link=" + this.link + ", isDot=" + this.isDot + ")";
    }

    public /* synthetic */ GameToolBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, BtnBean btnBean, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, btnBean, (i2 & 512) != 0 ? 0 : i);
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getBgDesc() {
        return this.bgDesc;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getBg() {
        return this.bg;
    }

    public final String getBgColor() {
        return this.bgColor;
    }

    public final String getColor() {
        return this.color;
    }

    public final String getVideo() {
        return this.video;
    }

    public final BtnBean getLink() {
        return this.link;
    }

    public final int isDot() {
        return this.isDot;
    }

    public final void setDot(int i) {
        this.isDot = i;
    }

    public GameToolBean(String desc, String bgDesc, String icon, String title, String bg, String bgColor, String color, String video, BtnBean link, int i) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(bgDesc, "bgDesc");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(bg, "bg");
        Intrinsics.checkNotNullParameter(bgColor, "bgColor");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(video, "video");
        Intrinsics.checkNotNullParameter(link, "link");
        this.desc = desc;
        this.bgDesc = bgDesc;
        this.icon = icon;
        this.title = title;
        this.bg = bg;
        this.bgColor = bgColor;
        this.color = color;
        this.video = video;
        this.link = link;
        this.isDot = i;
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }
}

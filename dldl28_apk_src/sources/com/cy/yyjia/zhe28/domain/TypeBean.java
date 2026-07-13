package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003JE\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010 \u001a\u00020\u00132\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00138G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f¨\u0006%"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TypeBean;", "Landroidx/databinding/BaseObservable;", "id", "", "name", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "tips", SocialConstants.PARAM_IMG_URL, "icon", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getIcon", "getId", "()I", "getImg", "getName", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getTips", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class TypeBean extends BaseObservable {
    public static final int $stable = 8;
    private final String content;
    private final String icon;
    private final int id;
    private final String img;

    @SerializedName(alternate = {"cate_name", "title"}, value = "name")
    private final String name;
    private boolean selected;
    private final String tips;

    public static /* synthetic */ TypeBean copy$default(TypeBean typeBean, int i, String str, String str2, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = typeBean.id;
        }
        if ((i2 & 2) != 0) {
            str = typeBean.name;
        }
        String str6 = str;
        if ((i2 & 4) != 0) {
            str2 = typeBean.content;
        }
        String str7 = str2;
        if ((i2 & 8) != 0) {
            str3 = typeBean.tips;
        }
        String str8 = str3;
        if ((i2 & 16) != 0) {
            str4 = typeBean.img;
        }
        String str9 = str4;
        if ((i2 & 32) != 0) {
            str5 = typeBean.icon;
        }
        return typeBean.copy(i, str6, str7, str8, str9, str5);
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
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTips() {
        return this.tips;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getImg() {
        return this.img;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    public final TypeBean copy(int id, String name, String content, String tips, String img, String icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(tips, "tips");
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(icon, "icon");
        return new TypeBean(id, name, content, tips, img, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypeBean)) {
            return false;
        }
        TypeBean typeBean = (TypeBean) other;
        return this.id == typeBean.id && Intrinsics.areEqual(this.name, typeBean.name) && Intrinsics.areEqual(this.content, typeBean.content) && Intrinsics.areEqual(this.tips, typeBean.tips) && Intrinsics.areEqual(this.img, typeBean.img) && Intrinsics.areEqual(this.icon, typeBean.icon);
    }

    public int hashCode() {
        return (((((((((this.id * 31) + this.name.hashCode()) * 31) + this.content.hashCode()) * 31) + this.tips.hashCode()) * 31) + this.img.hashCode()) * 31) + this.icon.hashCode();
    }

    public String toString() {
        return "TypeBean(id=" + this.id + ", name=" + this.name + ", content=" + this.content + ", tips=" + this.tips + ", img=" + this.img + ", icon=" + this.icon + ")";
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getTips() {
        return this.tips;
    }

    public final String getImg() {
        return this.img;
    }

    public /* synthetic */ TypeBean(int i, String str, String str2, String str3, String str4, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, str4, (i2 & 32) != 0 ? "" : str5);
    }

    public final String getIcon() {
        return this.icon;
    }

    public TypeBean(int i, String name, String content, String tips, String img, String icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(tips, "tips");
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.id = i;
        this.name = name;
        this.content = content;
        this.tips = tips;
        this.img = img;
        this.icon = icon;
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

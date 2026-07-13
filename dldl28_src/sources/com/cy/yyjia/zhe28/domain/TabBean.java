package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TabBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B5\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003JD\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020\u00062\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020\tHÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0004R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000f\"\u0004\b\u0012\u0010\u0004R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0004R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR&\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006,"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/TabBean;", "Landroidx/databinding/BaseObservable;", "name", "", "(Ljava/lang/String;)V", "selected", "", "(Ljava/lang/String;Z)V", ImageSelector.POSITION, "", SocialConstants.PARAM_APP_DESC, "iconResource", "icon", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "setDesc", "getIcon", "setIcon", "getIconResource", "()Ljava/lang/Integer;", "setIconResource", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getName", "setName", "getPosition", "()I", "getSelected", "()Z", "setSelected", "(Z)V", "component1", "component2", "component3", "component4", "component5", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/cy/yyjia/zhe28/domain/TabBean;", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class TabBean extends BaseObservable {
    public static final int $stable = 8;
    private String desc;
    private String icon;
    private Integer iconResource;
    private String name;
    private final int position;
    private boolean selected;

    public static /* synthetic */ TabBean copy$default(TabBean tabBean, int i, String str, String str2, Integer num, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = tabBean.position;
        }
        if ((i2 & 2) != 0) {
            str = tabBean.name;
        }
        String str4 = str;
        if ((i2 & 4) != 0) {
            str2 = tabBean.desc;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            num = tabBean.iconResource;
        }
        Integer num2 = num;
        if ((i2 & 16) != 0) {
            str3 = tabBean.icon;
        }
        return tabBean.copy(i, str4, str5, num2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getIconResource() {
        return this.iconResource;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    public final TabBean copy(int position, String name, String desc, Integer iconResource, String icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desc, "desc");
        return new TabBean(position, name, desc, iconResource, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TabBean)) {
            return false;
        }
        TabBean tabBean = (TabBean) other;
        return this.position == tabBean.position && Intrinsics.areEqual(this.name, tabBean.name) && Intrinsics.areEqual(this.desc, tabBean.desc) && Intrinsics.areEqual(this.iconResource, tabBean.iconResource) && Intrinsics.areEqual(this.icon, tabBean.icon);
    }

    public int hashCode() {
        int iHashCode = ((((this.position * 31) + this.name.hashCode()) * 31) + this.desc.hashCode()) * 31;
        Integer num = this.iconResource;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.icon;
        return iHashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "TabBean(position=" + this.position + ", name=" + this.name + ", desc=" + this.desc + ", iconResource=" + this.iconResource + ", icon=" + this.icon + ")";
    }

    public final int getPosition() {
        return this.position;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final void setDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.desc = str;
    }

    public /* synthetic */ TabBean(int i, String str, String str2, Integer num, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? 0 : num, (i2 & 16) != 0 ? "" : str3);
    }

    public final Integer getIconResource() {
        return this.iconResource;
    }

    public final void setIconResource(Integer num) {
        this.iconResource = num;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final void setIcon(String str) {
        this.icon = str;
    }

    public TabBean(int i, String name, String desc, Integer num, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desc, "desc");
        this.position = i;
        this.name = name;
        this.desc = desc;
        this.iconResource = num;
        this.icon = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TabBean(String name) {
        this(0, name, "", null, null, 24, null);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TabBean(String name, boolean z) {
        this(0, name, "", null, null, 24, null);
        Intrinsics.checkNotNullParameter(name, "name");
        setSelected(z);
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

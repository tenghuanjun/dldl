package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YunTipBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR&\u0010\f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\t¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunTipBean;", "Landroidx/databinding/BaseObservable;", SocialConstants.PARAM_APP_DESC, "", "id", "", "title", "(Ljava/lang/String;ILjava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getId", "()I", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getTitle", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class YunTipBean extends BaseObservable {
    public static final int $stable = 8;
    private final String desc;
    private final int id;
    private boolean selected;
    private final String title;

    public static /* synthetic */ YunTipBean copy$default(YunTipBean yunTipBean, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = yunTipBean.desc;
        }
        if ((i2 & 2) != 0) {
            i = yunTipBean.id;
        }
        if ((i2 & 4) != 0) {
            str2 = yunTipBean.title;
        }
        return yunTipBean.copy(str, i, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final YunTipBean copy(String desc, int id, String title) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(title, "title");
        return new YunTipBean(desc, id, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof YunTipBean)) {
            return false;
        }
        YunTipBean yunTipBean = (YunTipBean) other;
        return Intrinsics.areEqual(this.desc, yunTipBean.desc) && this.id == yunTipBean.id && Intrinsics.areEqual(this.title, yunTipBean.title);
    }

    public int hashCode() {
        return (((this.desc.hashCode() * 31) + this.id) * 31) + this.title.hashCode();
    }

    public String toString() {
        return "YunTipBean(desc=" + this.desc + ", id=" + this.id + ", title=" + this.title + ")";
    }

    public final String getDesc() {
        return this.desc;
    }

    public final int getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public YunTipBean(String desc, int i, String title) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(title, "title");
        this.desc = desc;
        this.id = i;
        this.title = title;
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

package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainTabBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00158G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/MainTabBean;", "Landroidx/databinding/BaseObservable;", "id", "", "nav_name", "", "nav_template", "topic", "Lcom/cy/yyjia/zhe28/domain/TopicBean;", "cates", "", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "(ILjava/lang/String;ILcom/cy/yyjia/zhe28/domain/TopicBean;Ljava/util/List;)V", "getCates", "()Ljava/util/List;", "getId", "()I", "getNav_name", "()Ljava/lang/String;", "getNav_template", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getTopic", "()Lcom/cy/yyjia/zhe28/domain/TopicBean;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MainTabBean extends BaseObservable {
    public static final int $stable = 8;
    private final List<TypeBean> cates;
    private final int id;
    private final String nav_name;
    private final int nav_template;
    private boolean selected;
    private final TopicBean topic;

    public final int getId() {
        return this.id;
    }

    public final String getNav_name() {
        return this.nav_name;
    }

    public final int getNav_template() {
        return this.nav_template;
    }

    public final TopicBean getTopic() {
        return this.topic;
    }

    public final List<TypeBean> getCates() {
        return this.cates;
    }

    public MainTabBean(int i, String nav_name, int i2, TopicBean topicBean, List<TypeBean> cates) {
        Intrinsics.checkNotNullParameter(nav_name, "nav_name");
        Intrinsics.checkNotNullParameter(cates, "cates");
        this.id = i;
        this.nav_name = nav_name;
        this.nav_template = i2;
        this.topic = topicBean;
        this.cates = cates;
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

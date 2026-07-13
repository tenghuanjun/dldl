package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MonthlyTaskNavBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001\bB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/MonthlyTaskNavBean;", "", "list", "", "Lcom/cy/yyjia/zhe28/domain/MonthlyTaskNavBean$ListBean;", "(Ljava/util/List;)V", "getList", "()Ljava/util/List;", "ListBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MonthlyTaskNavBean {
    public static final int $stable = 8;
    private final List<ListBean> list;

    public MonthlyTaskNavBean(List<ListBean> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    public final List<ListBean> getList() {
        return this.list;
    }

    /* JADX INFO: compiled from: MonthlyTaskNavBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/MonthlyTaskNavBean$ListBean;", "Landroidx/databinding/BaseObservable;", "type", "", "name", "", "(ILjava/lang/String;)V", "getName", "()Ljava/lang/String;", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getType", "()I", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ListBean extends BaseObservable {
        public static final int $stable = 8;
        private final String name;
        private boolean selected;
        private final int type;

        public final int getType() {
            return this.type;
        }

        public final String getName() {
            return this.name;
        }

        public ListBean(int i, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.type = i;
            this.name = name;
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
}

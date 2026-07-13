package com.cy.yyjia.zhe28.domain;

import com.cy.yyjia.zhe28.domain.FudaiIndexBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FudaiRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/FudaiRecordBean;", "", "list", "", "Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$List;", "user", "Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;", "(Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;)V", "getList", "()Ljava/util/List;", "getUser", "()Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$User;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class FudaiRecordBean {
    public static final int $stable = 8;
    private final List<FudaiIndexBean.List> list;
    private final FudaiIndexBean.User user;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FudaiRecordBean copy$default(FudaiRecordBean fudaiRecordBean, List list, FudaiIndexBean.User user, int i, Object obj) {
        if ((i & 1) != 0) {
            list = fudaiRecordBean.list;
        }
        if ((i & 2) != 0) {
            user = fudaiRecordBean.user;
        }
        return fudaiRecordBean.copy(list, user);
    }

    public final List<FudaiIndexBean.List> component1() {
        return this.list;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FudaiIndexBean.User getUser() {
        return this.user;
    }

    public final FudaiRecordBean copy(List<FudaiIndexBean.List> list, FudaiIndexBean.User user) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(user, "user");
        return new FudaiRecordBean(list, user);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FudaiRecordBean)) {
            return false;
        }
        FudaiRecordBean fudaiRecordBean = (FudaiRecordBean) other;
        return Intrinsics.areEqual(this.list, fudaiRecordBean.list) && Intrinsics.areEqual(this.user, fudaiRecordBean.user);
    }

    public int hashCode() {
        return (this.list.hashCode() * 31) + this.user.hashCode();
    }

    public String toString() {
        return "FudaiRecordBean(list=" + this.list + ", user=" + this.user + ")";
    }

    public FudaiRecordBean(List<FudaiIndexBean.List> list, FudaiIndexBean.User user) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(user, "user");
        this.list = list;
        this.user = user;
    }

    public final List<FudaiIndexBean.List> getList() {
        return this.list;
    }

    public final FudaiIndexBean.User getUser() {
        return this.user;
    }
}

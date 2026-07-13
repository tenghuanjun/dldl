package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CardModuleBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u0004\u0018\u00010\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CardModuleBean;", "", "modules", "", "Lcom/cy/yyjia/zhe28/domain/CardInfoBean;", "(Ljava/util/List;)V", "getModules", "()Ljava/util/List;", "getSelectedModule", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CardModuleBean {
    public static final int $stable = 8;
    private final List<CardInfoBean> modules;

    public CardModuleBean(List<CardInfoBean> modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        this.modules = modules;
    }

    public final List<CardInfoBean> getModules() {
        return this.modules;
    }

    public final CardInfoBean getSelectedModule() {
        for (CardInfoBean cardInfoBean : this.modules) {
            if (cardInfoBean.getSelected()) {
                return cardInfoBean;
            }
        }
        return null;
    }
}

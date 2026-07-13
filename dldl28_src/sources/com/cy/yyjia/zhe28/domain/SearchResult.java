package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SearchResult;", "", "list", "Lcom/cy/yyjia/zhe28/domain/PageBean;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Lcom/cy/yyjia/zhe28/domain/PageBean;)V", "getList", "()Lcom/cy/yyjia/zhe28/domain/PageBean;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SearchResult {
    public static final int $stable = 8;
    private final PageBean<GameBean> list;

    public SearchResult(PageBean<GameBean> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    public final PageBean<GameBean> getList() {
        return this.list;
    }
}

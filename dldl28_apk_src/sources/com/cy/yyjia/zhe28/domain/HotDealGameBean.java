package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HotDealGameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/HotDealGameBean;", "", "games", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Ljava/util/List;)V", "getGames", "()Ljava/util/List;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HotDealGameBean {
    public static final int $stable = 8;
    private final List<GameBean> games;

    public HotDealGameBean(List<GameBean> games) {
        Intrinsics.checkNotNullParameter(games, "games");
        this.games = games;
    }

    public final List<GameBean> getGames() {
        return this.games;
    }
}

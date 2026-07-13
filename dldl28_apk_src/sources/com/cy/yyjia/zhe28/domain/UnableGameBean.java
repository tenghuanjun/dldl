package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnableGameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/UnableGameBean;", "", "game_name", "", "icon", "(Ljava/lang/String;Ljava/lang/String;)V", "getGame_name", "()Ljava/lang/String;", "getIcon", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UnableGameBean {
    public static final int $stable = 0;
    private final String game_name;
    private final String icon;

    public UnableGameBean(String game_name, String icon) {
        Intrinsics.checkNotNullParameter(game_name, "game_name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.game_name = game_name;
        this.icon = icon;
    }

    public final String getGame_name() {
        return this.game_name;
    }

    public final String getIcon() {
        return this.icon;
    }
}
